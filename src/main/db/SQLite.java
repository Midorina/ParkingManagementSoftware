package main.db;

import main.ParkingLot;
import main.ParkingSpot;
import main.Vehicle;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SQLite {
    private Connection c;
    private final DateTimeFormatter SQLiteDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    public SQLite(String dbName) {
        try {
            c = DriverManager.getConnection("jdbc:sqlite:" + dbName);

            Statement stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS parked_cars " +
                    "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "LICENSE_PLATE     TEXT                NOT NULL," +
                    "PARKED_SPOT       TEXT                             NOT NULL," +
                    "ENTRY_DATE        datetime DEFAULT current_timestamp NOT NULL," +
                    "DEPARTURE_DATE    datetime);";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
    }

    public void assignParkedCarsToSpots(ParkingLot parkingLot) {
        try (Statement stmt = c.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM parked_cars WHERE DEPARTURE_DATE is NULL;");

            while (rs.next()) {
                int id = rs.getInt("ID");
                String licensePlate = rs.getString("LICENSE_PLATE");
                ParkingSpot parkedSpot = parkingLot.getParkingSpotWithCode(rs.getString("PARKED_SPOT"));
                LocalDateTime entryDate = LocalDateTime.parse(rs.getString("ENTRY_DATE"), SQLiteDateFormatter);

                Vehicle vehicle = new Vehicle(id, licensePlate, parkedSpot, entryDate, null);
                parkedSpot.setParkedVehicle(vehicle);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
    }

    public Vehicle insertNewCar(String licensePlate, ParkingSpot parkedSpot) throws Exception {
        // for some reason I couldn't make it work with multiple queries at once
        // so we're having to make them separate
        String sql1 = "INSERT INTO parked_cars(LICENSE_PLATE,PARKED_SPOT) VALUES(?,?);";
        String sql2 = "SELECT last_insert_rowid();";
        int ID;

        try (PreparedStatement pstmt = c.prepareStatement(sql1);
             PreparedStatement pstmt2 = c.prepareStatement(sql2)) {
            pstmt.setString(1, licensePlate);
            pstmt.setString(2, parkedSpot.getSpotCode());
            pstmt.executeUpdate();

            ResultSet rs = pstmt2.executeQuery();
            ID = rs.getInt("last_insert_rowid()");
        }

        return new Vehicle(ID, licensePlate, parkedSpot, LocalDateTime.now(), null);
    }

    public void updateVehicle(Vehicle vehicle) throws Exception {
        String sql = "UPDATE parked_cars SET LICENSE_PLATE=?, PARKED_SPOT=?, ENTRY_DATE=?, DEPARTURE_DATE=? WHERE id=?;";

        try (PreparedStatement pstmt = c.prepareStatement(sql)) {
            pstmt.setString(1, vehicle.getLicensePlate());
            pstmt.setString(2, vehicle.getParkedSpot().getSpotCode());
            pstmt.setString(3, vehicle.getEntryDate().format(SQLiteDateFormatter));
            pstmt.setString(4, vehicle.getDepartureDate().format(SQLiteDateFormatter));
            pstmt.setInt(5, vehicle.getDbID());

            pstmt.executeUpdate();
        }
    }
}

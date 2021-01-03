package main.db;

import java.sql.*;
import java.util.ArrayList;

import main.parking_lot.ParkedVehicle;
import main.parking_lot.ParkingSpot;

public class SQLite {
    private Connection c;

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
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public ArrayList<ParkedVehicle> getParkedCars() {
        ArrayList<ParkedVehicle> cars = new ArrayList<ParkedVehicle>();

        try {
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM parked_cars WHERE DEPARTURE_DATE is NULL;");

            while (rs.next()) {
                int id = rs.getInt("ID");
                String licensePlate = rs.getString("LICENSE_PLATE");
                ParkingSpot parkedSpot = new ParkingSpot(rs.getString("PARKED_SPOT"));
                String entryDate = rs.getString("ENTRY_DATE");

                cars.add(new ParkedVehicle(id, licensePlate, parkedSpot, entryDate, null));
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        return cars;
    }

}

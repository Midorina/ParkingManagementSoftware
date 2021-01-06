package main.parking_lot;

import main.db.SQLite;
import main.gui.ParkingSpot;

import java.time.Duration;
import java.time.LocalDateTime;

public class Vehicle {
    private final int dbID;
    private final String licensePlate;
    private final LocalDateTime entryDate;
    private ParkingSpot parkedSpot;
    private LocalDateTime departureDate;

    public Vehicle(int dbID,
                   String licensePlate,
                   ParkingSpot parkedSpot,
                   LocalDateTime entryDate,
                   LocalDateTime departureDate) {
        this.dbID = dbID;
        this.licensePlate = licensePlate;

        this.parkedSpot = parkedSpot;
        this.entryDate = entryDate;
        this.departureDate = departureDate;
    }

    public boolean isCurrentlyParked() {
        return this.departureDate == null;
    }


    public int getDbID() {
        return this.dbID;
    }

    public ParkingSpot getParkedSpot() {
        return this.parkedSpot;
    }

    public LocalDateTime getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDateTime departureDate) {
        this.departureDate = departureDate;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setParkedSpot(ParkingSpot parkedSpot) {
        this.parkedSpot = parkedSpot;
    }

    public LocalDateTime getEntryDate() {
        return entryDate;
    }

    public Duration unParkAndGetDuration(SQLite db) throws Exception {
        departureDate = LocalDateTime.now();


        db.updateVehicle(this);

        parkedSpot.setParkedVehicle(null);

        return Duration.between(entryDate, departureDate);
    }
}

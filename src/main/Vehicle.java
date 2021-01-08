package main;

import main.db.SQLite;

import java.time.Duration;
import java.time.LocalDateTime;

public class Vehicle {
    private final int dbID;
    private final String licensePlate;
    private ParkingSpot parkedSpot;
    private final LocalDateTime entryDate;
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


    public int getDbID() {
        return this.dbID;
    }

    public ParkingSpot getParkedSpot() {
        return this.parkedSpot;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public LocalDateTime getEntryDate() {
        return entryDate;
    }

    public LocalDateTime getDepartureDate() {
        return departureDate;
    }


    public String getEntryDateString() {
        return entryDate.format(ParkingLot.dateFormatter);
    }

    public String getDepartureDateString() {
        return departureDate.format(ParkingLot.dateFormatter);
    }


    public Duration getDurationUntilDeparture() {
        return Duration.between(entryDate, departureDate);
    }

    public Duration getCurrentDuration() {
        return Duration.between(entryDate, LocalDateTime.now());
    }

    public double getFeeUntilDeparture() {
        return getDurationUntilDeparture().toMinutes() * ParkingLot.feePerMinute;
    }

    public double getCurrentFee() {
        return getCurrentDuration().toMinutes() * ParkingLot.feePerMinute;
    }

    public double unparkAndGetFee(SQLite db) throws Exception {
        unpark(db);
        return getFeeUntilDeparture();
    }

    public void setDepartureDate(LocalDateTime departureDate) {
        this.departureDate = departureDate;
    }

    public void setParkedSpot(ParkingSpot parkedSpot) {
        this.parkedSpot = parkedSpot;
    }

    public void unpark(SQLite db) throws Exception {
        setDepartureDate(LocalDateTime.now());
        db.updateVehicle(this);

        parkedSpot.removeVehicle();
    }

    public boolean isCurrentlyParked() {
        return this.departureDate == null;
    }
}

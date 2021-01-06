package main.parking_lot;

import main.db.SQLite;
import main.gui.ParkingLot;
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

    public void unpark(SQLite db) throws Exception {
        departureDate = LocalDateTime.now();

        db.updateVehicle(this);

        parkedSpot.setParkedVehicle(null);
    }

    public double unparkAndGetFee(SQLite db) throws Exception {
        unpark(db);
        return getFeeUntilDeparture();
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

    public String getEntryDateString() {
        return entryDate.format(ParkingLot.dateFormatter);
    }

    public String getDepartureDateString() {
        return departureDate.format(ParkingLot.dateFormatter);
    }
}

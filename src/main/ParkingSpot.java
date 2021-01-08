package main;

import javax.swing.*;

public class ParkingSpot extends JCheckBox {
    private final String spotCode;
    private Vehicle vehicle;

    public ParkingSpot(String spotCode) {
        super(spotCode);
        this.spotCode = spotCode;
    }

    public String getSpotCode() {
        return spotCode;
    }

    public Vehicle getParkedVehicle() {
        return this.vehicle;
    }

    public void setParkedVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void removeVehicle() {
        this.vehicle = null;
    }

    public boolean isFree() {
        return vehicle == null;
    }

    public void park(Vehicle vehicle) throws Exception {
        if (!this.isFree()) {
            throw new Exception("Spot isn't free!");
        }

        this.vehicle = vehicle;
        vehicle.setParkedSpot(this);
    }
}

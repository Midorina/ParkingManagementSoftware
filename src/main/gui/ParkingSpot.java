package main.gui;

import main.parking_lot.Vehicle;

import javax.swing.*;
import java.awt.*;

public class ParkingSpot extends JCheckBox {
    private final String spotCode;
    private Vehicle vehicle;

    public ParkingSpot(String spotCode) {
        super(spotCode);
        this.spotCode = spotCode;
    }

    public boolean isFree() {
        return vehicle == null;
    }

    public void park(Vehicle v) throws Exception {
        if (!this.isFree()) {
            throw new Exception("Spot isn't free!");
        }

        this.vehicle = v;
        if (v.getParkedSpot() != this) {
            v.setParkedSpot(this);
        }

        this.setBackground(Color.RED);
        this.setText(v.getLicensePlate());
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

    public String getSpotCode() {
        return spotCode;
    }
}

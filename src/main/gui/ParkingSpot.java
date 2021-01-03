package main.gui;

import main.parking_lot.ParkedVehicle;

import javax.swing.*;

public class ParkingSpot extends JCheckBox {
    private final String spotName;
    private ParkedVehicle parkedVehicle;

    public ParkingSpot(String spotName) {
        super(spotName);
        this.spotName = spotName;
    }

    public boolean isFree() {
        return parkedVehicle == null;
    }

    public boolean park(ParkedVehicle v) {
        if (!this.isFree()) {
            return false;
        }

        this.parkedVehicle = v;
        if (v.getParkedSpot() != this) {
            v.setParkedSpot(this);
        }

        return true;
    }

    public ParkedVehicle getParkedVehicle() {
        return this.parkedVehicle;
    }

    public void removeVehicle() {
        this.parkedVehicle = null;
    }

    public String getSpotName() {
        return spotName;
    }
}

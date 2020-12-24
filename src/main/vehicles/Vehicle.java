package main.vehicles;


import main.parking_lot.ParkingSpot;

public abstract class Vehicle {
    protected ParkingSpot parkedSpot = null;
    protected VehicleSize size;

    public VehicleSize getSize() {
        return size;
    }

    public void parkinSpot(ParkingSpot spot) {
        this.parkedSpot = spot;
        if (spot.getParkedVehicle() != this) {
            spot.park(this);
        }
    }

    public void clearSpot() {
        this.parkedSpot.removeVehicle();
        this.parkedSpot  = null;
    }

    public boolean canFitinSpot(ParkingSpot spot) {
        return spot.canFitVehicle(this);
    }

    public boolean isParked() {
        return parkedSpot != null;
    }

    public ParkingSpot getParkedSpot() {
        return parkedSpot;
    }
}






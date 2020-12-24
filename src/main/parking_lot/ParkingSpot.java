package main.parking_lot;

import main.vehicles.Vehicle;
import main.vehicles.VehicleSize;

public class ParkingSpot {
    private Vehicle parkedVehicle;
    private VehicleSize spotSize;
    private ParkingLotFloor parkingLotFloor;
    private int row;
    private int spotNumber;

    public ParkingSpot(ParkingLotFloor lvl, int r, int n,
                       VehicleSize s) {
        this.spotSize = s;
        this.row = r;
        this.spotNumber = n;
        this.parkingLotFloor = lvl;
    }

    public boolean isFree() {
        return parkedVehicle == null;
    }

    public boolean canFitVehicle(Vehicle vehicle) {
        return vehicle.getSize() == this.spotSize;
    }

    public boolean vehicleCanPark(Vehicle v) {
        return isFree() && vehicleCanPark(v);
    }

    public boolean park(Vehicle v) {
        if (!this.vehicleCanPark(v)) {
            return false;
        }

        this.parkedVehicle = v;
        if (v.getParkedSpot() != this) {
            v.parkinSpot(this);
        }

        return true;
    }

    public int getRow() {
        return this.row;
    }

    public int getSpotNumber() {
        return this.spotNumber;
    }

    public ParkingLotFloor getParkingLotFloor() {
        return this.parkingLotFloor;
    }

    public Vehicle getParkedVehicle() {
        return this.parkedVehicle;
    }

    public void removeVehicle() {
        this.parkedVehicle = null;
    }
}

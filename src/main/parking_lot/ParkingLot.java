package main.parking_lot;

import main.vehicles.Vehicle;

public class ParkingLot {
    private ParkingLotFloor[] parkingLotFloors;

    public ParkingLot(int numberofLevels) {
        this.parkingLotFloors = new ParkingLotFloor[numberofLevels];

        for (int i = 0; i < numberofLevels; i++) {
            parkingLotFloors[i] = new ParkingLotFloor(i);
        }
    };

    public ParkingLot() {
        this(5);
    };

    // park in a spot, return false if failed
    public boolean parkVehicle(Vehicle vehicle) {
        for (ParkingLotFloor lvl: this.parkingLotFloors) {
            ParkingSpot spot = lvl.getRandomAvailableSpot(vehicle);
            if (spot != null) {
                vehicle.parkinSpot(spot);
                return true;
            }
        }
        return false;
    }
}

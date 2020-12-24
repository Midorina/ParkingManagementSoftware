package main.parking_lot;

import main.vehicles.Vehicle;
import main.vehicles.VehicleSize;

import java.util.ArrayList;
import java.util.Random;

public class ParkingLotFloor {
    private int floor;
    private ArrayList<ParkingSpot> spots;
    private static final int ROWS_PER_LEVEL = 5;
    private static final int SPOTS_PER_ROW = 5;

    public ParkingLotFloor(int flr) {
        this.floor = flr;
        this.spots = new ArrayList<ParkingSpot>();

        for(int row=0; row<ROWS_PER_LEVEL; row++) {
            for (int spot=0; spot<SPOTS_PER_ROW; spot++) {
                spots.add(new ParkingSpot(this, row, spot, VehicleSize.Automobile));
            }
        }
    }

    private ArrayList<ParkingSpot> getAllAvailableSpots(Vehicle v) {
        ArrayList<ParkingSpot> _availableSpots = new ArrayList<ParkingSpot>();

        for (ParkingSpot spot: this.spots) {
            if (spot.vehicleCanPark(v)) {
                _availableSpots.add(spot);
            }
        }

        return _availableSpots;
    }

    public ParkingSpot getRandomAvailableSpot(Vehicle v) {
        ArrayList<ParkingSpot> arr = this.getAllAvailableSpots(v);
        int rnd = new Random().nextInt(arr.size());
        return arr.get(rnd);
    }
}

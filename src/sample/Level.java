package sample;

import java.util.ArrayList;
import java.util.Random;

public class Level {
    private int floor;
    private ArrayList<ParkingSpot> spots;
    private static final int SPOTS_PER_ROW = 10;

    public Level(int flr) {
        this.floor = flr;
        // todo: this
//        for(int i=0; i<SPOTS_PER_ROW; i++) {
//            spots.add(new ParkingSpot(this, ))
//        }
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

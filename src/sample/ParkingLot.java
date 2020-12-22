package sample;

public class ParkingLot {
    private Level[] levels;

    public ParkingLot(int numberofLevels) {
        this.levels = new Level[numberofLevels];

        for (int i = 0; i < numberofLevels; i++) {
            levels[i] = new Level(i);
        }
    };

    public ParkingLot() {
        this(5);
    };

    // park in a spot, return false if failed
    public boolean parkVehicle(Vehicle vehicle) {
        for (Level lvl: this.levels) {
            ParkingSpot spot = lvl.getRandomAvailableSpot(vehicle);
            if (spot != null) {
                vehicle.parkinSpot(spot);
                return true;
            }
        }
        return false;
    }
}

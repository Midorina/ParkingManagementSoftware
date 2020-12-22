package sample;

public class ParkingSpot {
    private Vehicle parkedVehicle;
    private VehicleSize spotSize;
    private Level level;
    private int row;
    private int spotNumber;

    public ParkingSpot(Level lvl, int r, int n,
                       VehicleSize s) {
        this.spotSize = s;
        this.row = r;
        this.spotNumber = n;
        this.level = lvl;
    }

    public boolean isFree() {
        return parkedVehicle == null;
    }

    boolean canFitVehicle(Vehicle vehicle) {
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
        if (v.parkedSpot != this) {
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

    public Level getLevel() {
        return this.level;
    }

    public Vehicle getParkedVehicle() {
        return this.parkedVehicle;
    }

    void removeVehicle() {
        this.parkedVehicle = null;
    }
}

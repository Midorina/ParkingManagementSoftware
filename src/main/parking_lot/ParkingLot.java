package main.parking_lot;

import com.sun.javafx.geom.Rectangle;
import javafx.application.Application;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import main.vehicles.Vehicle;

public class ParkingLot extends Application {
    private ParkingLotFloor[] parkingLotFloors;

    public ParkingLot(int numberofLevels) {
        this.parkingLotFloors = new ParkingLotFloor[numberofLevels];

        for (int i = 0; i < numberofLevels; i++) {
            parkingLotFloors[i] = new ParkingLotFloor(i);
        }
    }

    public ParkingLot() {
        this(5);
    }

    // park in a spot, return false if failed
    public boolean parkVehicle(Vehicle vehicle) {
        for (ParkingLotFloor lvl: this.parkingLotFloors) {
            ParkingSpot spot = lvl.getRandomAvailableSpot(vehicle);
            // if an empty spot is found, park
            if (spot != null) {
                vehicle.parkinSpot(spot);
                return true;
            }
        }
        return false;
    }

    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();

        Rectangle parkingSpotObj = new Rectangle();
    }
}

package main.parking_lot;

public class ParkedVehicle {
	private final int dbID;
	private final String licensePlate;

	private ParkingSpot parkedSpot;
    private final String entryDate;
    private String departureDate;
	
	public ParkedVehicle() {
		dbID = 0;
		licensePlate = null;
	    parkedSpot = null;
		entryDate = null;
		departureDate = null;
	}
	
	public ParkedVehicle(int dbID,
                         String licensePlate,
                         ParkingSpot parkedSpot,
                         String entryDate,
                         String departureDate) {
		this.dbID = dbID;
		this.licensePlate = licensePlate;

		this.parkedSpot = parkedSpot;
		this.entryDate = entryDate;
		this.departureDate = departureDate;
	}

	public boolean isCurrentlyParked() {
	    return this.departureDate == null;
    }


    public int getDbID(){
	    return this.dbID;
    }

    public ParkingSpot getParkedSpot() {
        return this.parkedSpot;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setParkedSpot(ParkingSpot parkedSpot) {
        this.parkedSpot = parkedSpot;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }
}

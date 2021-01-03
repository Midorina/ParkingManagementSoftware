package main.parking_lot;

public class carpark {
	public String carnum;
	public String name;
   	public String cellno;
    	public String placeno;
    	public String startTime;
   	public String duration;
    	public String date;
	
	public carpark() {
		carnum = null;
		name = null;
		cellno = null;
		placeno = null;
		startTime = null;
		duration = null;
		date = null;
	}
	
	public carpark(String carnum,String name,String cellno,String placeno,String startTime,String duration,String date) {
		this.carnum = carnum;
		this.name = name;
		this.cellno = cellno;
		this.placeno = placeno;
		this.startTime = startTime;
		this.duration = duration;
		this.date = date;
	}

	public String toString() {
		return carnum +","+name+","+cellno+","+placeno+","+startTime+","+duration+","+date;
	}

}

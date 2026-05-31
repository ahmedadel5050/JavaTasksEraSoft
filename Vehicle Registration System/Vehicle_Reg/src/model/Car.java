package model;

public class Car extends Vehicle{

	private int numberOfDoors = 4 ;

	public int getNumberOfDoors() {
        return numberOfDoors;
    }
	

	
	public Car(String plateNumber, String ownerName, int registrationYear, String status, int numberOfDoors) {
        super(plateNumber, ownerName, "Car", registrationYear, status);
        this.numberOfDoors = numberOfDoors;
    }

	public Car(String plateNumber, String ownerName, String vehicleType, int registrationYear, String status) {
        super(plateNumber, ownerName, vehicleType, registrationYear, status);        
    }
	
	public String getRegistrationLabel() { 
		return "Passenger Car — Doors: " + this.getNumberOfDoors() ;
	}

	@Override
	public String toString() {
		return super.toString()  + " | Doors: " + this.getNumberOfDoors(); 
	};
	
	
	
}

package model;

public class Truck extends Vehicle{
	
	private double cargoCapacityTons;

	public double getCargoCapacityTons() {
		return cargoCapacityTons;
	}
	
	public Truck (String plateNumber, String ownerName, int registrationYear, String status , double cargoCapacityTons ) {
        super(plateNumber, ownerName, "Truck", registrationYear, status);  
        this.cargoCapacityTons = cargoCapacityTons;
    }

	public Truck (String plateNumber, String ownerName, String vehicleType , int registrationYear, String status  ) {
        super(plateNumber, ownerName, vehicleType, registrationYear, status);          
    }
	
	
	public String getRegistrationLabel() { 
		return "Commercial Truck — Cargo: " + this.getCargoCapacityTons() + " tons";
	};
	

	@Override
	public String toString() {
		return super.toString() + this.getCargoCapacityTons() ; 
	};
	
}

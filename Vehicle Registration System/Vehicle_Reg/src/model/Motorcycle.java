package model;

public class Motorcycle extends Vehicle{
	private String engineType ; // ("Sport", "Cruiser", "Off-Road").

	public String getEngineType() {
		return engineType;
	}
	

	public Motorcycle (String plateNumber, String ownerName,  int registrationYear, String status , String engineType ) {
        super(plateNumber, ownerName, "Motorcycle", registrationYear, status);  
        this.engineType = engineType;
    }

	public Motorcycle (String plateNumber, String ownerName,  String vehicleType, int registrationYear, String status  ) {
        super(plateNumber, ownerName, vehicleType , registrationYear, status);  
    }
	
	public String getRegistrationLabel() { 
		return "Motorcycle — Engine: Sport";
	};
	
	
	@Override
	public String toString() {
		return super.toString() + this.getEngineType() ; 
	};	
	
	
}

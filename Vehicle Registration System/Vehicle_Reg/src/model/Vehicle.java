package model;

import java.util.Objects;

public abstract class Vehicle {


	private final String plateNumber;
	private String ownerName ;
	private String vehicleType;
	private int registrationYear ;
	private String status ; //"ACTIVE" or "EXPIRED"

	
	public String getPlateNumber() {
		return plateNumber;
	}
	public String getOwnerName() {
		return ownerName;
	}	
	public String getVehicleType() {
		return vehicleType;
	}
	
	public int getRegistrationYear() {
		return registrationYear;
	}	

	public String getStatus() {
		return status;
	}

	
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public void setStatus(String status) {
		this.status = status;
	}	
	

	
	
	public Vehicle (String plateNumber, String ownerName, String vehicleType, int registrationYear, String status) {
        this.plateNumber = plateNumber;
        this.ownerName = ownerName;
        this.vehicleType = vehicleType;
        this.registrationYear = registrationYear;
        this.status = status;
    }
	
	
	
	 @Override
	    public String toString() {
	        return String.format("[%s] | %s | Owner: %s | Year: %d | Status: %s",
	                plateNumber, vehicleType, ownerName, registrationYear, status.toUpperCase());
	    }
	
 
	 @Override
	 public boolean equals(Object o) {
	     if (this == o) 
	    	 return true; // same reference
	     if (o == null || getClass() != o.getClass()) 
	    	 return false; // null or different class

	     Vehicle vehicle = (Vehicle) o;
	     return plateNumber.equalsIgnoreCase(vehicle.plateNumber);
	 }

	 @Override
	 public int hashCode() {
//	     return plateNumber.toLowerCase().hashCode();
	     return Objects.hash(plateNumber.toLowerCase());
	 }
	 
	 
	public abstract String getRegistrationLabel();
	 
}

package exception;

//Throw VehicleNotFoundException when searching for a plate that doesn't exist
//Key Concept: Custom exceptions make error messages meaningful. Instead of 'NullPointerException', 
// the user sees 'No vehicle found with plate: XYZ-999'.

public class VehicleNotFoundException extends RuntimeException{

	public VehicleNotFoundException(String plateNumber) {
		super("No vehicle found with plate: " + plateNumber);
	}
	
}

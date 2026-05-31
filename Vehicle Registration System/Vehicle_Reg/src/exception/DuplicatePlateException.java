package exception;

//Throw DuplicatePlateException when registering a plate that already exists
public class DuplicatePlateException extends RuntimeException {

	public DuplicatePlateException(String plateNumber) {
		super("Plate number already registered: " + plateNumber);
	}
	
}

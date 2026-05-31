package util;

import java.time.Year;
import exception.InvalidInputException;

public class InputValidator {

    // Validate plate number
    public static String validatePlateNumber(String plateNumber) {
        if (plateNumber == null || plateNumber.trim().isEmpty()) {
            throw new InvalidInputException("Plate number must not be empty");
        }
        if (!plateNumber.matches("[A-Z0-9-]{3,10}")) {
            throw new InvalidInputException("Plate number must be 3-10 alphanumeric characters");
        }
        return plateNumber.toUpperCase();
    }

    // Validate owner name
    public static String validateOwnerName(String ownerName) {
        if (ownerName == null || ownerName.trim().isEmpty()) {
            throw new InvalidInputException("Owner name must not be empty");
        }
        if (ownerName.length() < 3) {
            throw new InvalidInputException("Owner name must be at least 3 characters long");
        }
        if (!ownerName.matches("[A-Za-z ]+")) {
            throw new InvalidInputException("Owner name must contain only letters");
        }
        return ownerName.trim();
    }

    // Validate vehicle type
    public static String validateVehicleType(String vehicleType) {
        if (vehicleType == null) {
            throw new InvalidInputException("Vehicle type must not be null");
        }
        String type = vehicleType.trim().toLowerCase();
        if (!(type.equals("car") || type.equals("truck") || type.equals("motorcycle"))) {
            throw new InvalidInputException("Invalid type. Use: Car, Truck, Motorcycle");
        }
        return type.substring(0,1).toUpperCase() + type.substring(1); // normalize to proper case
    }

    // Validate registration year
    public static int validateRegistrationYear(int year) {
        int currentYear = Year.now().getValue();
        if (year < 1990 || year > currentYear) {
            throw new InvalidInputException("Year must be between 1990 and " + currentYear);
        }
        return year;
    }
}

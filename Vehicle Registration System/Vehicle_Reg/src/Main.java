import java.util.InputMismatchException;
import java.util.Scanner;

import exception.DuplicatePlateException;
import exception.InvalidInputException;
import exception.VehicleNotFoundException;
import model.Car;
import model.Motorcycle;
import model.Truck;
import model.Vehicle;
import service.RegistrationService;
import util.InputValidator;

public class Main {

	private static void printMenu() {
        System.out.println("========================================");
        System.out.println("VEHICLE REGISTRATION SYSTEM v1.0");
        System.out.println("========================================");
        System.out.println("1. Register New Vehicle");
        System.out.println("2. Search Vehicle by Plate");
        System.out.println("3. Update Owner Name");
        System.out.println("4. Delete Vehicle");
        System.out.println("5. List All Vehicles");
        System.out.println("6. Filter by Vehicle Type");
        System.out.println("7. Show Owner History");
        System.out.println("8. Show Expired Registrations");
        System.out.println("9. Statistics Report");
        System.out.println("0. Exit");
        System.out.println("========================================");
        System.out.print("Enter your choice: ");
    }
	
	
	public static void main(String[] args) {
//		Vehicle v1 = new Car("ABC-001", "Sara Ahmed", 2022, "ACTIVE", 4); 		
//		Vehicle v2 = new Truck("TRK-099", "Ali Hassan", 2020, "ACTIVE", 8.5); 
//		Vehicle v3 = new Motorcycle("MOT-555", "Omar Said", 2023, "ACTIVE", "Sport"); 
//		System.out.println(v1.getRegistrationLabel()); 
//		System.out.println(v2.getRegistrationLabel());	
		
//		Vehicle a = new Car("abc-001", "Ali", 2021, "ACTIVE", 4); 
//		Vehicle b = new Car("ABC-001", "Sara", 2022, "ACTIVE", 2); 
//		System.out.println(a.equals(b)); // Expected: true 
//		System.out.println(a.hashCode() == b.hashCode()); // Expected: true


		Scanner sc = new Scanner(System.in);
        RegistrationService service = new RegistrationService();
 
        service.registerVehicle(new Car("CAR-001", "Ahmed Ali",    2019, "EXPIRED", 4)); service.registerVehicle(new Car("CAR-002", "Sara Kamel",   2023, "ACTIVE",  2)); service.registerVehicle(new Truck("TRK-001", "Mohamed Said", 2021, "ACTIVE", 
        		10.0)); service.registerVehicle(new Truck("TRK-002", "Laila Nour",   2017, "EXPIRED", 
        		5.5)); 
        		service.registerVehicle(new Motorcycle("MOT-001", "Omar Fathi", 2022, "ACTIVE", 
        		"Sport")); service.registerVehicle(new Motorcycle("MOT-002", "Nadia Hamed", 2024, "ACTIVE", 
        		"Cruiser")); 

        
        while (true) {
            printMenu();
            String plate ;
            String type ;
            String owner ;
            
            int choice = -1;
            try {
                choice = sc.nextInt();
                sc.nextLine(); // consume newline
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine(); // clear bad input
                continue;
            }

            try {
                switch (choice) {
                    case 1:
//                        System.out.print("Enter plate number: ");
//                        plate = sc.nextLine();
//                        System.out.print("Enter owner name: ");
//                        String owner = sc.nextLine();
//                        System.out.print("Enter vehicle type: ");
//                        type = sc.nextLine();
//                        System.out.print("Enter registration year: ");
//                        int year = sc.nextInt();
//                        sc.nextLine(); // consume newline
//                        System.out.print("Enter status (ACTIVE/EXPIRED): ");
//                        String status = sc.nextLine();
//
//                        Vehicle v = new Vehicle(plate, owner, type, year, status) {
//                            @Override
//                            public String getRegistrationLabel() {
//                                return "Generic Vehicle Registration";
//                            }
//                        };
//
//                        service.registerVehicle(v);
//                        System.out.println("Vehicle registered successfully.");
//                        break;
                    	try {
                            System.out.print("Enter plate number: ");
                            plate = InputValidator.validatePlateNumber(sc.nextLine());

                            System.out.print("Enter owner name: ");
                            owner = InputValidator.validateOwnerName(sc.nextLine());

                            System.out.print("Enter vehicle type (Car/Truck/Motorcycle): ");
                            type = InputValidator.validateVehicleType(sc.nextLine());

                            System.out.print("Enter registration year: ");
                            int year = InputValidator.validateRegistrationYear(sc.nextInt());
                            sc.nextLine(); // consume newline

                            Vehicle v ; //= new Vehicle(plate, owner, type, year, "ACTIVE");
                            switch (type.toLowerCase()) {
                            case "car":
                                v = new Car(plate, owner, type, year, "ACTIVE");
                                break;
                            case "truck":
                                v = new Truck(plate, owner, type, year, "ACTIVE");
                                break;
                            case "motorcycle":
                                v = new Motorcycle(plate, owner, type, year, "ACTIVE");
                                break;
                            default:
                                throw new InvalidInputException("Invalid vehicle type: " + type);
                        }
                            service.registerVehicle(v);
                            System.out.println("Vehicle registered successfully!");

                        } catch (InvalidInputException e) {
                            System.out.println("Input Error: " + e.getMessage());
                        } catch (DuplicatePlateException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                        
                    case 2:
                        System.out.print("Enter plate number: ");
                        plate = sc.nextLine();
                        Vehicle found = service.findByPlate(plate);
                        System.out.println("Found: " + found);
                        break;

                    case 3:
                        System.out.print("Enter plate number: ");
                        plate = sc.nextLine();
                        System.out.print("Enter new owner name: ");
                        owner = sc.nextLine();
                        service.updateOwner(plate, owner);
                        System.out.println("Owner updated successfully.");
                        break;

                    case 4:
                        System.out.print("Enter plate number: ");
                        plate = sc.nextLine();
                        service.deleteVehicle(plate);
                        System.out.println("Vehicle deleted successfully.");
                        break;

                    case 5:
                        System.out.println("All Registered Vehicles:");
                        for (Vehicle vehicle : service.getAllVehicles()) {
                            System.out.println(vehicle);
                        }
                        break;

                    case 6:
//                        System.out.print("Enter vehicle type to filter: ");
//                        type = sc.nextLine();
//                        System.out.println("Vehicles of type " + type + ":");
//                        for (Vehicle vehicle : service.filterByType(type)) {
//                            System.out.println(vehicle);
//                        }
//                        break;

                        System.out.print("Enter vehicle type to filter: ");
                        type = sc.nextLine();
                        System.out.println("Vehicles of type " + type + ":");
                        service.filterByType(type).forEach(System.out::println);
                        break;
                        
                    case 7:
                        System.out.print("Enter plate number: ");
                        plate = sc.nextLine();
                        System.out.println(service.getOwnerHistory(plate));
                        break;
//                        System.out.print("Enter owner name to search: ");
//                        owner = sc.nextLine();
//                        System.out.println("Vehicles owned by " + owner + ":");
//                        service.getVehiclesByOwner(owner).forEach(System.out::println);
//                        break;
                        
                    case 8:
//                        System.out.println("Expired Registrations:");
//                        for (Vehicle vehicle : service.getExpiredRegistrations()) {
//                            System.out.println(vehicle);
//                        }
//                        break;

                        System.out.print("Enter current year: ");
                        int currentYear = Integer.parseInt(sc.nextLine());
                        System.out.println("Expired Registrations (older than 5 years):");
                        service.getExpiredRegistrations(currentYear).forEach(System.out::println);
                        break;
                        
                    case 9:
                        System.out.println("Statistics Report:");
//                        System.out.println(service.getStatisticsReport());
                        service.printStatistics();
                        break;
//                        System.out.print("Sort ascending? (true/false): ");
//                        boolean ascending = Boolean.parseBoolean(sc.nextLine());
//                        System.out.println("Vehicles sorted by registration year:");
//                        service.getSortedByYear(ascending).forEach(System.out::println);
//                        break;                        
                    case 0:
                        System.out.println("Exiting system. Goodbye!");
                        sc.close();
                        return;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (DuplicatePlateException | VehicleNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
    }
	
	
		


}

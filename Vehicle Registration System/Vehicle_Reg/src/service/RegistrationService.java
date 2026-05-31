package service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import exception.DuplicatePlateException;
import exception.VehicleNotFoundException;
import model.Vehicle;

public class RegistrationService {
	private List<Vehicle> vehicleList = new ArrayList<>();
	private Map<String, Vehicle> plateIndex = new HashMap<>();
	private Set<String> registeredPlates = new HashSet<>(); 


	 public void registerVehicle(Vehicle v) {
	        // Normalize plate number to uppercase
	        String normalizedPlate = v.getPlateNumber().toUpperCase();

	        // Check if plate already exists
	        if (registeredPlates.contains(normalizedPlate)) {
	            throw new DuplicatePlateException(normalizedPlate);
	        }

	        // Add to all collections
	        vehicleList.add(v);
	        plateIndex.put(normalizedPlate, v);
	        registeredPlates.add(normalizedPlate);
	    }
	 
	 
	  public Vehicle findByPlate(String plate) {
	        // Normalize plate to uppercase
	        String normalizedPlate = plate.toUpperCase();

	        // Lookup in plateIndex
	        Vehicle vehicle = plateIndex.get(normalizedPlate);

	        if (vehicle == null) {
	            throw new VehicleNotFoundException(normalizedPlate);
	        }

	        return vehicle;
	    }	 
	
	  
	    public void deleteVehicle(String plate) {
	        // Find the vehicle (throws VehicleNotFoundException if not found)
	        Vehicle vehicle = findByPlate(plate);

	        // Normalize plate to uppercase for consistency
	        String normalizedPlate = plate.toUpperCase();

	        // Remove from all collections
	        vehicleList.remove(vehicle);
	        plateIndex.remove(normalizedPlate);
	        registeredPlates.remove(normalizedPlate);
	    }
	    
	    public void updateOwner(String plate, String newOwner) {
	        // Find the vehicle (throws VehicleNotFoundException if not found)
	        Vehicle vehicle = findByPlate(plate);

	        // Update the owner name
	        vehicle.setOwnerName(newOwner);
	    }
	    
	    public List<Vehicle> getAllVehicles() {
	        // Return an unmodifiable view of the vehicle list
	        return Collections.unmodifiableList(vehicleList);
	    }

	    
	    // Option 6: Filter by vehicle type
	    public List<Vehicle> filterByType(String type) {
	        return vehicleList.stream()
	                .filter(v -> v.getVehicleType().equalsIgnoreCase(type))
	                .collect(Collectors.toList());
	    }
	    
	    // Option 7: Show owner history (simple version: just current owner)
	    // If you want full history, you’d need to track changes separately.
	    public String getOwnerHistory(String plate) {
	        Vehicle v = findByPlate(plate);
	        return "Owner history for " + plate + ": " + v.getOwnerName();
	    }
	    
	    // Option 8: Show expired registrations
	    public List<Vehicle> getExpiredRegistrations(int currentYear) {
//	        return vehicleList.stream()
//	                .filter(v -> v.getStatus().equalsIgnoreCase("EXPIRED"))
//	                .collect(Collectors.toList());
	        return vehicleList.stream() .filter(v -> (currentYear - v.getRegistrationYear()) > 5) .sorted(Comparator.comparingInt(Vehicle::getRegistrationYear)) .collect(Collectors.toList());
	    }
	    
	    
	    // Option 9: Statistics report
	    public String getStatisticsReport() {
	        long total = vehicleList.size();
	        long active = vehicleList.stream().filter(v -> v.getStatus().equalsIgnoreCase("ACTIVE")).count();
	        long expired = total - active;

	        return String.format("Total Vehicles: %d | Active: %d | Expired: %d",
	                total, active, expired);
	    }

	    
	    
	    public List<Vehicle> getVehiclesByOwner(String ownerName) {
	        return vehicleList.stream()
	                .filter(v -> v.getOwnerName() != null &&
	                             v.getOwnerName().toLowerCase()
	                               .contains(ownerName.toLowerCase()))
	                .collect(Collectors.toList());
	    }

	    public List<Vehicle> getSortedByYear(boolean ascending) {
	        Comparator<Vehicle> comp = Comparator.comparingInt(Vehicle::getRegistrationYear);
	        if (!ascending) {
	            comp = comp.reversed();
	        }
	        return vehicleList.stream()
	                .sorted(comp)
	                .collect(Collectors.toList());
	    }

	    
	    
	 // Method 5: Print full statistics report
	    public void printStatistics() {
	        // 1. Summary statistics for registration years
	        IntSummaryStatistics stats = vehicleList.stream()
	                .mapToInt(Vehicle::getRegistrationYear)
	                .summaryStatistics();

	        // 2. Count vehicles by type
	        Map<String, Long> byType = vehicleList.stream()
	                .collect(Collectors.groupingBy(
	                        Vehicle::getVehicleType,
	                        Collectors.counting()
	                ));

	        // 3. Partition vehicles by ACTIVE vs EXPIRED
	        Map<Boolean, Long> byStatus = vehicleList.stream()
	                .collect(Collectors.partitioningBy(
	                        v -> v.getStatus().equalsIgnoreCase("ACTIVE"),
	                        Collectors.counting()
	                ));

	        // 4. Print formatted report
	        System.out.println("========== REGISTRATION STATISTICS ==========");
	        System.out.printf("Total Vehicles : %d%n", stats.getCount());
	        System.out.printf("Average Year   : %d%n", (int) stats.getAverage());
	        System.out.printf("Newest Vehicle : %d%n", stats.getMax());
	        System.out.printf("Oldest Vehicle : %d%n", stats.getMin());
	        System.out.println("----------------------------------------------");
	        System.out.println("Vehicles by Type:");
	        byType.forEach((type, count) ->
	                System.out.printf("%-10s : %d%n", type, count));
	        System.out.println("----------------------------------------------");
	        System.out.printf("ACTIVE vehicles : %d%n", byStatus.getOrDefault(true, 0L));
	        System.out.printf("EXPIRED vehicles: %d%n", byStatus.getOrDefault(false, 0L));
	        System.out.println("==============================================");
	    }

	    
}

package com.vehicle.reg;

import java.util.ArrayList;
import java.util.List;

/**
 * -----------------------------------------------------------------------------
 * VehicleManager.java
 *
 * Central manager for storing and managing all registered vehicles.
 * Provides methods to add, find, delete, and return the full list.
 * Used by all GUI components (forms, views, search, delete).
 * -----------------------------------------------------------------------------
 */
public class VehicleManager {

    // Master list of vehicles stored in memory
    private final List<Car> vehicles;

    public VehicleManager() {
        vehicles = new ArrayList<>();
    }

    /**
     * Adds a new car to the list if VIN and plate number are unique.
     *
     * @param car Car object to add
     * @return true if added successfully, false if duplicate
     */
    public boolean addCar(Car car) {
        for (Car existing : vehicles) {
            if (existing.getVin().equalsIgnoreCase(car.getVin()) ||
                    existing.getPlateNumber().equalsIgnoreCase(car.getPlateNumber())) {
                return false; // Duplicate VIN or plate number
            }
        }
        vehicles.add(car);
        return true;
    }

    /**
     * Returns the current list of all registered vehicles.
     */
    public List<Car> getAllCars() {
        return vehicles;
    }

    /**
     * Searches for a car by VIN or plate number.
     * @param query VIN or plate (case-insensitive)
     * @return Car if found, or null
     */
    public Car findCar(String query) {
        for (Car car : vehicles) {
            if (car.getVin().equalsIgnoreCase(query) ||
                    car.getPlateNumber().equalsIgnoreCase(query)) {
                return car;
            }
        }
        return null; // Not found
    }

    /**
     * Deletes a car from the list by VIN.
     * @param vin VIN of the car to remove
     * @return true if removed, false if not found
     */
    public boolean deleteCar(String vin) {
        for (Car car : vehicles) {
            if (car.getVin().equalsIgnoreCase(vin)) {
                vehicles.remove(car);
                return true;
            }
        }
        return false; // VIN not found
    }
}

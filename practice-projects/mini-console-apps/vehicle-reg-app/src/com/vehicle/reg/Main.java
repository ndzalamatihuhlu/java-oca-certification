/**
 * --------------------------------------------------------------
 * Vehicle Reg – Console Application
 * Author: Ndzalama Tihuhlu
 * Released: 20 June 2025
 * Updated: 7 July 2025
 * Institution: FMTALI
 * <pr>
 * A simple Java console-based system for capturing, viewing,
 * searching, and managing registered vehicle data. Designed to
 * support core programming concepts such as object encapsulation,
 * flow control, input validation, and list operations.
 * <p>
 * Functionalities:
 * - Add new vehicle
 * - Display all registered vehicles
 * - Search by VIN or plate number
 * - Delete by VIN
 * - Show summary count and total mileage
 * --------------------------------------------------------------
 */

package com.vehicle.reg;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        ArrayList<Car> cars = new ArrayList<>(); // vehicle storage list
        int menuOption;

        // =================== APPLICATION HEADER ===================
        System.out.println("====================================================");
        System.out.println("       VEHICLE REGISTRATION SYSTEM (CONSOLE APP)   ");
        System.out.println("====================================================");
        System.out.println("Welcome! This system allows you to register, search,");
        System.out.println("view and manage vehicle details in a secure format.");
        System.out.println("----------------------------------------------------\n");

        // ========== MAIN APPLICATION LOOP ========== //
        do {
            printMenu();

            // Validate input for numeric option
            while (!input.hasNextInt()) {
                System.out.print("Enter a valid number from the menu: ");
                input.next();
            }
            menuOption = input.nextInt();
            input.nextLine(); // Consume newline

            switch (menuOption) {

                // =================== 1. REGISTER VEHICLE ===================
                case 1:
                    Car car = new Car();

                    System.out.println("\n-- Register a New Vehicle --");
                    System.out.println("Please follow the prompts to capture all vehicle details.\n");

                    // --- Make ---
                    System.out.print("Enter the vehicle make (e.g. Toyota, BMW, Hyundai): ");
                    String make = input.nextLine().trim();
                    while (make.isEmpty()) {
                        System.out.print("Make cannot be empty. Enter Make: ");
                        make = input.nextLine().trim();
                    }
                    car.setMake(make);

                    // --- Model ---
                    System.out.print("Enter the vehicle model (e.g. Corolla, i20, M4): ");
                    String model = input.nextLine().trim();
                    while (model.isEmpty()) {
                        System.out.print("Model cannot be empty. Enter Model: ");
                        model = input.nextLine().trim();
                    }
                    car.setModel(model);

                    // --- VIN ---
                    String vin;
                    do {
                        System.out.print("Enter VIN (17 characters, e.g. A1B2C3D4E5F6G7H8J): ");
                        vin = input.nextLine().trim();
                        if (vin.length() != 17) {
                            System.out.println("Error: VIN must be exactly 17 characters. Try again.\n");
                        }
                    } while (vin.length() != 17);
                    car.setVin(vin);

                    // --- Plate ---
                    int plateChoice;
                    do {
                        System.out.println("\nSelect a license plate format:");
                        System.out.println("1 - Old Format (e.g. ABC123GP)");
                        System.out.println("2 - New Format (e.g. AB12CDGP)");
                        System.out.print("Your selection: ");
                        while (!input.hasNextInt()) {
                            System.out.print("Enter 1 or 2 only: ");
                            input.next();
                        }
                        plateChoice = input.nextInt();
                        input.nextLine();
                    } while (plateChoice != 1 && plateChoice != 2);

                    System.out.print("Enter full license plate (e.g. JHN675GP): ");
                    String plate = input.nextLine().trim().toUpperCase();
                    car.setPlateNumber(plate);

                    // --- Mileage ---
                    System.out.print("Enter mileage in kilometres (e.g. 85200): ");
                    while (!input.hasNextInt()) {
                        System.out.print("Please enter a valid number: ");
                        input.next();
                    }
                    car.setMileage(input.nextInt());

                    // --- Year ---
                    System.out.print("Enter year of manufacture (e.g. 2020): ");
                    while (!input.hasNextInt()) {
                        System.out.print("Please enter a valid year: ");
                        input.next();
                    }
                    car.setYear(input.nextInt());
                    input.nextLine();

                    // --- Confirm before saving ---
                    System.out.println("\n--- Confirm Vehicle Entry ---");
                    System.out.println("Make       : " + car.getMake());
                    System.out.println("Model      : " + car.getModel());
                    System.out.println("VIN        : " + car.getVin());
                    System.out.println("Plate      : " + car.getPlateNumber());
                    System.out.println("Mileage    : " + car.getMileage());
                    System.out.println("Year       : " + car.getYear());
                    System.out.print("Save this vehicle? (yes/no): ");
                    String confirm = input.nextLine().trim().toLowerCase();
                    if (confirm.equals("yes")) {
                        cars.add(car);
                        System.out.println("Vehicle registered successfully. Returning to menu...\n");
                    } else {
                        System.out.println("Vehicle entry cancelled. Returning to menu...\n");
                    }
                    break;

                // =================== 2. VIEW VEHICLES ===================
                case 2:
                    System.out.println("\n================ REGISTERED VEHICLES ===============");
                    if (cars.isEmpty()) {
                        System.out.println("[No vehicles registered yet. Please use option 1 to begin.]\n");
                    } else {
                        int count = 1;
                        int totalMileage = 0;
                        for (Car c : cars) {
                            // Display vehicle info with numbering and separators
                            System.out.println("\nVehicle #" + count++);
                            System.out.println("Make         : " + c.getMake());
                            System.out.println("Model        : " + c.getModel());
                            System.out.println("VIN          : " + c.getVin());
                            System.out.println("Plate Number : " + c.getPlateNumber());
                            System.out.println("Mileage      : " + c.getMileage() + " km");
                            System.out.println("Year         : " + c.getYear());
                            System.out.println("----------------------------------------------------");
                            totalMileage += c.getMileage();
                        }
                        // Display summary info
                        System.out.println("\nTotal Vehicles Registered: " + cars.size());
                        System.out.println("Total Combined Mileage  : " + totalMileage + " km\n");
                    }
                    break;

                // =================== 3. SEARCH VEHICLE ===================
                case 3:
                    System.out.println("\n-- Search a Vehicle --");
                    System.out.print("Enter VIN or Plate Number to search (case-insensitive): ");
                    String search = input.nextLine().trim().toUpperCase();
                    boolean found = false;
                    for (Car c : cars) {
                        if (c.getVin().equalsIgnoreCase(search) || c.getPlateNumber().equalsIgnoreCase(search)) {
                            System.out.println("\nVehicle Found:");
                            System.out.println("Make   : " + c.getMake());
                            System.out.println("Model  : " + c.getModel());
                            System.out.println("VIN    : " + c.getVin());
                            System.out.println("Plate  : " + c.getPlateNumber());
                            System.out.println("Mileage: " + c.getMileage());
                            System.out.println("Year   : " + c.getYear());
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("No matching vehicle found with given VIN or Plate.\n");
                    }
                    break;

                // =================== 4. DELETE VEHICLE ===================
                case 4:
                    System.out.println("\n-- Remove a Vehicle Record --");
                    System.out.print("Enter VIN of the vehicle to delete: ");
                    String deleteVin = input.nextLine().trim();
                    boolean removed = false;
                    for (int i = 0; i < cars.size(); i++) {
                        if (cars.get(i).getVin().equalsIgnoreCase(deleteVin)) {
                            cars.remove(i);
                            System.out.println("Vehicle record removed successfully.\n");
                            removed = true;
                            break;
                        }
                    }
                    if (!removed) {
                        System.out.println("No vehicle with that VIN was found. Nothing deleted.\n");
                    }
                    break;

                // =================== 5. EXIT SYSTEM ===================
                case 5:
                    System.out.println("\nThank you for using Vehicle Reg. Your data session is now closed.");
                    System.out.println("====================================================\n");
                    break;

                // =================== INVALID MENU INPUT ===================
                default:
                    System.out.println("Invalid selection. Please choose from the listed options only.\n");
            }

        } while (menuOption != 5);
    }

    /**
     * Displays the main navigation menu with available actions.
     */
    public static void printMenu() {
        System.out.println("\n------------------ MAIN MENU ------------------");
        System.out.println("1. Register a new vehicle");
        System.out.println("2. View all registered vehicles");
        System.out.println("3. Search vehicle by VIN or Plate");
        System.out.println("4. Delete vehicle by VIN");
        System.out.println("5. Exit");
        System.out.print("\nSelect an option to proceed: ");
    }
}

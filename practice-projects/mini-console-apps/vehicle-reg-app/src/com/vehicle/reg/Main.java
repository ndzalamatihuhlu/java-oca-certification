/**
 * ---------------------------------------------------------------------
 * Vehicle Reg – Console Application
 * Author: Ndzalama Tihuhlu
 * Released: 20 June 2025
 * Updated: 8 July 2025
 * Institution: FMTALI
 *
 * A console-based application to capture, view, search, and manage
 * vehicle registration details. Demonstrates core programming concepts:
 * - Object-Oriented Programming (encapsulation with Car class)
 * - Loops and flow control (while/do-while, switch)
 * - Input validation and error handling
 * - Java collections and list operations
 * - Regex pattern matching for format validation
 * ---------------------------------------------------------------------
 */

package com.vehicle.reg;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in); // Input reader
        ArrayList<Car> cars = new ArrayList<>(); // Vehicle storage
        int menuOption;

        // App Title
        System.out.println("====================================================");
        System.out.println("       VEHICLE REGISTRATION SYSTEM (CONSOLE APP)   ");
        System.out.println("====================================================");
        System.out.println("Welcome! This system allows you to register, search,");
        System.out.println("view and manage vehicle details in a secure format.");
        System.out.println("----------------------------------------------------");

        // ===================== MAIN MENU =====================
        do {
            printMenu();
            menuOption = getValidatedMenuOption(input);

            switch (menuOption) {
                case 1 -> {
                    // =========== REGISTER VEHICLE ============
                    Car car = new Car();
                    System.out.println("\n-- Register a New Vehicle --");

                    // Make
                    System.out.print("Enter make (e.g. Toyota): ");
                    car.setMake(getNonEmptyInput(input));

                    // Model
                    System.out.print("Enter model (e.g. Corolla): ");
                    car.setModel(getNonEmptyInput(input));

                    // VIN
                    String vin;
                    while (true) {
                        System.out.print("Enter 17-char VIN (e.g. A1B2C3D4E5F6G7H8I): ");
                        vin = input.nextLine().trim().toUpperCase();

                        if (vin.length() == 17 && vin.matches("[A-Z0-9]{17}")) {
                            if (vinExists(cars, vin)) {
                                System.out.println("A vehicle with this VIN already exists.");
                            } else {
                                break;
                            }
                        } else {
                            System.out.println("Invalid VIN format. Must be 17 letters/numbers.");
                        }
                    }
                    car.setVin(vin);

                    // Plate Format
                    int plateChoice;
                    do {
                        System.out.println("\nSelect plate format:");
                        System.out.println("1 - Old Format (e.g. ABC123GP)");
                        System.out.println("2 - New Format (e.g. AB12CDGP)");
                        System.out.print("Your choice: ");
                        plateChoice = getIntInput(input, 1, 2);
                    } while (plateChoice != 1 && plateChoice != 2);

                    // Plate Number
                    String platePattern = (plateChoice == 1)
                            ? "[A-Z]{3}[0-9]{3}GP"
                            : "[A-Z]{2}[0-9]{2}[A-Z]{2}GP";
                    String plate;
                    while (true) {
                        System.out.print("Enter plate number (e.g. ABC123GP): ");
                        plate = input.nextLine().trim().toUpperCase();

                        if (Pattern.matches(platePattern, plate)) {
                            if (plateExists(cars, plate)) {
                                System.out.println("A vehicle with this plate already exists.");
                            } else {
                                break;
                            }
                        } else {
                            System.out.println("Invalid plate format. Follow selected format exactly.");
                        }
                    }
                    car.setPlateNumber(plate);

                    // Mileage
                    System.out.print("Enter mileage (0–1,000,000): ");
                    car.setMileage(getIntInput(input, 0, 1_000_000));

                    // Year
                    System.out.print("Enter year of manufacture (1900–2025): ");
                    car.setYear(getIntInput(input, 1900, 2025));

                    // Confirm and Save
                    System.out.println("\nConfirm vehicle details:");
                    printVehicleDetails(car);
                    System.out.print("Save this vehicle? (yes/no): ");
                    if (input.nextLine().trim().equalsIgnoreCase("yes")) {
                        cars.add(car);
                        System.out.println("Vehicle saved successfully.\n");
                    } else {
                        System.out.println("Vehicle not saved.\n");
                    }
                }

                case 2 -> {
                    // ======== VIEW VEHICLES =========
                    System.out.println("\n====== REGISTERED VEHICLES ======");
                    if (cars.isEmpty()) {
                        System.out.println("No vehicles registered.\n");
                    } else {
                        int count = 1;
                        int totalMileage = 0;
                        for (Car c : cars) {
                            System.out.println("\nVehicle #" + count++);
                            printVehicleDetails(c);
                            totalMileage += c.getMileage();
                        }
                        System.out.println("\nTotal Registered: " + cars.size());
                        System.out.println("Combined Mileage: " + totalMileage + " km");
                    }
                }

                case 3 -> {
                    // ======= SEARCH VEHICLE =======
                    System.out.print("\nEnter VIN or Plate to search: ");
                    String searchKey = input.nextLine().trim().toUpperCase();
                    boolean found = false;

                    for (Car c : cars) {
                        if (c.getVin().equalsIgnoreCase(searchKey)
                                || c.getPlateNumber().equalsIgnoreCase(searchKey)) {
                            System.out.println("\nVehicle found:");
                            printVehicleDetails(c);
                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        System.out.println("No vehicle found with that VIN or plate.");
                    }
                }

                case 4 -> {
                    // ======= DELETE VEHICLE =======
                    System.out.print("\nEnter VIN to delete: ");
                    String deleteVin = input.nextLine().trim().toUpperCase();
                    Car toDelete = null;

                    for (Car c : cars) {
                        if (c.getVin().equalsIgnoreCase(deleteVin)) {
                            toDelete = c;
                            break;
                        }
                    }

                    if (toDelete != null) {
                        System.out.print("Confirm deletion (yes/no): ");
                        if (input.nextLine().trim().equalsIgnoreCase("yes")) {
                            cars.remove(toDelete);
                            System.out.println("Vehicle deleted.\n");
                        } else {
                            System.out.println("Deletion cancelled.\n");
                        }
                    } else {
                        System.out.println("VIN not found. Nothing deleted.\n");
                    }
                }

                case 5 -> System.out.println("\nExiting Vehicle Reg. Goodbye!");

                default -> System.out.println("Invalid choice. Please enter option 1–5.\n");
            }

        } while (menuOption != 5);
    }

    // ------------------ HELPER METHODS -------------------

    public static void printMenu() {
        System.out.println("\n---------------- MAIN MENU ----------------");
        System.out.println("1. Register a new vehicle");
        System.out.println("2. View all registered vehicles");
        System.out.println("3. Search vehicle by VIN or Plate");
        System.out.println("4. Delete vehicle by VIN");
        System.out.println("5. Exit");
        System.out.print("Select option: ");
    }

    public static int getValidatedMenuOption(Scanner input) {
        while (true) {
            if (input.hasNextInt()) {
                int option = input.nextInt();
                input.nextLine();
                if (option >= 1 && option <= 5) return option;
            } else {
                input.next(); // discard invalid input
            }
            System.out.print("Enter a valid option (1–5): ");
        }
    }

    public static String getNonEmptyInput(Scanner input) {
        String text;
        while (true) {
            text = input.nextLine().trim();
            if (!text.isEmpty()) return text;
            System.out.print("Input cannot be empty. Try again: ");
        }
    }

    public static int getIntInput(Scanner input, int min, int max) {
        while (true) {
            if (input.hasNextInt()) {
                int value = input.nextInt();
                input.nextLine();
                if (value >= min && value <= max) return value;
                else System.out.print("Enter between " + min + " and " + max + ": ");
            } else {
                System.out.print("Invalid number. Try again: ");
                input.next(); // discard invalid input
            }
        }
    }

    public static void printVehicleDetails(Car c) {
        System.out.println("Make         : " + c.getMake());
        System.out.println("Model        : " + c.getModel());
        System.out.println("VIN          : " + c.getVin());
        System.out.println("Plate Number : " + c.getPlateNumber());
        System.out.println("Mileage      : " + c.getMileage() + " km");
        System.out.println("Year         : " + c.getYear());
        System.out.println("-------------------------------------------");
    }

    // Checks if a VIN is already registered
    public static boolean vinExists(ArrayList<Car> cars, String vin) {
        return cars.stream().anyMatch(c -> c.getVin().equalsIgnoreCase(vin));
    }

    // Checks if a plate number is already registered
    public static boolean plateExists(ArrayList<Car> cars, String plate) {
        return cars.stream().anyMatch(c -> c.getPlateNumber().equalsIgnoreCase(plate));
    }
}

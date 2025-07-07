/**
 * ----------------------------------------------
 * Project: Vehicle Registration Console App
 * Practical Project 2 – FMTALI Java SE 8 Training
 * Author: Ndzalama Tihuhlu
 * Release Date: 20 June 2025
 * Description:
 *   This class defines the structure of a vehicle
 *   that is to be registered through the console app.
 *   It includes properties such as make, model, VIN,
 *   license plate, year, and mileage.
 * ----------------------------------------------
 */

package com.vehicle.reg;

/**
 * A class that defines the structure and behaviour
 * of a registered vehicle object in the system.
 *
 * This class uses encapsulation principles by keeping
 * all fields private and exposing access via getters
 * and setters.
 */
public class Car {

    // =======================
    // Fields (Vehicle Details)
    // =======================

    /** The make of the vehicle (e.g., Toyota, BMW) */
    private String make;

    /** The model of the vehicle (e.g., M4, Corolla) */
    private String model;

    /** The unique Vehicle Identification Number (VIN), must be exactly 17 characters */
    private String vin;

    /** The license plate number (GP only, either old or new format) */
    private String plateNumber;

    /** The year in which the vehicle was manufactured */
    private int year;

    /** The mileage of the vehicle in kilometers */
    private int mileage;

    // =======================
    // Accessors (Getters/Setters)
    // =======================

    /**
     * Returns the vehicle's make.
     * @return make of the vehicle
     */
    public String getMake() {
        return make;
    }

    /**
     * Sets the vehicle's make.
     * @param make brand name of the vehicle
     */
    public void setMake(String make) {
        this.make = make;
    }

    /**
     * Returns the vehicle's model.
     * @return model of the vehicle
     */
    public String getModel() {
        return model;
    }

    /**
     * Sets the vehicle's model.
     * @param model model name of the vehicle
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Returns the vehicle's VIN.
     * @return 17-character VIN
     */
    public String getVin() {
        return vin;
    }

    /**
     * Sets the vehicle's VIN.
     * Assumes VIN is already validated before being set.
     * @param vin vehicle identification number
     */
    public void setVin(String vin) {
        this.vin = vin;
    }

    /**
     * Returns the vehicle's license plate.
     * @return license plate number
     */
    public String getPlateNumber() {
        return plateNumber;
    }

    /**
     * Sets the vehicle's license plate number.
     * @param plateNumber old or new format GP plate
     */
    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    /**
     * Returns the year the vehicle was manufactured.
     * @return manufacture year
     */
    public int getYear() {
        return year;
    }

    /**
     * Sets the vehicle's manufacture year.
     * @param year a valid year (e.g., 2020, 2025)
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Returns the mileage on the vehicle.
     * @return mileage in kilometers
     */
    public int getMileage() {
        return mileage;
    }

    /**
     * Sets the mileage of the vehicle.
     * @param mileage distance travelled in kilometers
     */
    public void setMileage(int mileage) {
        this.mileage = mileage;
    }
}

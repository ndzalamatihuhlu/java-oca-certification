package com.vehicle.reg;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.regex.Pattern;

/**
 * -----------------------------------------------------------------------------
 * RegisterForm.java
 * Improved layout version: Inputs now spaced and aligned properly
 * Author: Ndzalama Tihuhlu
 * -----------------------------------------------------------------------------
 */
public class RegisterForm extends JDialog {

    private JTextField makeField, modelField, vinField, plateField, mileageField, yearField;
    private JButton saveButton, cancelButton;
    private VehicleManager vehicleManager;

    public RegisterForm(JFrame parent, VehicleManager manager) {
        super(parent, "Register New Vehicle", true);
        this.vehicleManager = manager;

        setSize(450, 400);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        // ============ Header Title ============
        JLabel title = new JLabel("Register New Vehicle", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        add(title, BorderLayout.NORTH);

        // ============ Form Panel with GridBag ============
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 10, 30));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // padding
        gbc.anchor = GridBagConstraints.WEST;

        // Utility function to add each row
        addRow(formPanel, gbc, 0, "Make:", makeField = new JTextField(20));
        addRow(formPanel, gbc, 1, "Model:", modelField = new JTextField(20));
        addRow(formPanel, gbc, 2, "VIN (17 chars):", vinField = new JTextField(20));
        addRow(formPanel, gbc, 3, "Plate Number:", plateField = new JTextField(20));
        addRow(formPanel, gbc, 4, "Mileage (km):", mileageField = new JTextField(20));
        addRow(formPanel, gbc, 5, "Year:", yearField = new JTextField(20));

        add(formPanel, BorderLayout.CENTER);

        // ============ Buttons Panel ============
        JPanel buttonPanel = new JPanel();
        saveButton = new JButton("Save");
        cancelButton = new JButton("Cancel");
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Cancel Action
        cancelButton.addActionListener(e -> dispose());

        // Save Action
        saveButton.addActionListener((ActionEvent e) -> {
            if (validateInput()) {
                Car car = new Car();
                car.setMake(makeField.getText().trim());
                car.setModel(modelField.getText().trim());
                car.setVin(vinField.getText().trim().toUpperCase());
                car.setPlateNumber(plateField.getText().trim().toUpperCase());
                car.setMileage(Integer.parseInt(mileageField.getText().trim()));
                car.setYear(Integer.parseInt(yearField.getText().trim()));

                if (vehicleManager.addCar(car)) {
                    JOptionPane.showMessageDialog(this, "Vehicle registered successfully.");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "VIN or Plate already exists.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    /**
     * Adds a label and field row to the form panel.
     */
    private void addRow(JPanel panel, GridBagConstraints gbc, int y, String label, JTextField field) {
        gbc.gridx = 0;
        gbc.gridy = y;
        panel.add(new JLabel(label), gbc);

        gbc.gridx = 1;
        panel.add(field, gbc);
    }

    /**
     * Input validation logic for all fields.
     */
    private boolean validateInput() {
        if (makeField.getText().trim().isEmpty() || modelField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Make and Model cannot be empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        String vin = vinField.getText().trim();
        if (vin.length() != 17 || !Pattern.matches("[A-HJ-NPR-Z0-9]{17}", vin)) {
            JOptionPane.showMessageDialog(this, "VIN must be 17 characters (A-Z, 0-9 only).", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        String plate = plateField.getText().trim();
        if (!Pattern.matches("[A-Z0-9]{6,8}", plate)) {
            JOptionPane.showMessageDialog(this, "Enter a valid license plate number.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        try {
            int mileage = Integer.parseInt(mileageField.getText().trim());
            if (mileage < 0 || mileage > 2_000_000) throw new NumberFormatException();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Mileage must be a number between 0 and 2,000,000.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        try {
            int year = Integer.parseInt(yearField.getText().trim());
            if (year < 1950 || year > 2025) throw new NumberFormatException();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Enter a valid manufacturing year (1950–2025).", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }
}

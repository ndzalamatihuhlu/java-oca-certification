package com.vehicle.reg;

import javax.swing.*;
import java.awt.*;

/**
 * -----------------------------------------------------------------------------
 * VehicleReg GUI Application
 * Author: Ndzalama Tihuhlu
 * Updated GUI Build: 8 July 2025
 *
 * This is the main application frame which acts as the Home/Menu screen.
 * It allows users to navigate to Register, View, Search, Delete and Exit options.
 * -----------------------------------------------------------------------------
 */
public class MainFrame extends JFrame {

    // Manager instance to handle vehicle operations (store, search, delete)
    private final VehicleManager manager = new VehicleManager();

    public MainFrame() {
        // Set up the main window (frame)
        setTitle("Vehicle Registration System");
        setSize(400, 450);
        setLocationRelativeTo(null); // Center the frame on screen
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // App Title/Header
        JLabel title = new JLabel("Vehicle Reg System", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 22));
        add(title, BorderLayout.NORTH);

        // Create interactive buttons for each action
        JButton registerBtn = new JButton("Register Vehicle");
        JButton viewBtn = new JButton("View Registered Vehicles");
        JButton searchBtn = new JButton("Search Vehicle");
        JButton deleteBtn = new JButton("Delete Vehicle");
        JButton statsBtn = new JButton("View Stats");
        JButton exitBtn = new JButton("Exit");

        // Panel to arrange the buttons vertically with spacing
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(6, 1, 10, 10)); // Now 6 rows for 6 buttons
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        // Add buttons to the panel in order
        buttonPanel.add(registerBtn);
        buttonPanel.add(viewBtn);
        buttonPanel.add(searchBtn);
        buttonPanel.add(deleteBtn);
        buttonPanel.add(statsBtn);
        buttonPanel.add(exitBtn);

        // Add the button panel to the center of the frame
        add(buttonPanel, BorderLayout.CENTER);

        // Exit button with confirmation dialog
        exitBtn.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to exit?", "Exit Confirmation",
                    JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        // Register button opens the RegisterForm dialog
        registerBtn.addActionListener(e -> {
            RegisterForm form = new RegisterForm(this, manager);
            form.setVisible(true);
        });

        // View button opens the TableView dialog
        viewBtn.addActionListener(e -> {
            TableView view = new TableView(this, manager);
            view.setVisible(true);
        });

        // Search button allows user to input VIN or plate and shows vehicle info
        searchBtn.addActionListener(e -> {
            String query = JOptionPane.showInputDialog(this, "Enter VIN or Plate:", "Search Vehicle", JOptionPane.QUESTION_MESSAGE);
            if (query != null && !query.trim().isEmpty()) {
                Car found = manager.findCar(query.trim());
                if (found != null) {
                    String info = "Make: " + found.getMake() +
                            "\nModel: " + found.getModel() +
                            "\nVIN: " + found.getVin() +
                            "\nPlate: " + found.getPlateNumber() +
                            "\nMileage: " + found.getMileage() + " km" +
                            "\nYear: " + found.getYear();
                    JOptionPane.showMessageDialog(this, info, "Vehicle Found", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "No vehicle found.", "Search Result", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        // Delete button allows user to delete vehicle by VIN with confirmation
        deleteBtn.addActionListener(e -> {
            String vin = JOptionPane.showInputDialog(this, "Enter VIN to delete:", "Delete Vehicle", JOptionPane.WARNING_MESSAGE);
            if (vin != null && !vin.trim().isEmpty()) {
                boolean success = manager.deleteCar(vin.trim());
                if (success) {
                    JOptionPane.showMessageDialog(this, "Vehicle deleted successfully.", "Deleted", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Vehicle not found.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Stats button opens the ExtendedFeatures dialog for insights
        statsBtn.addActionListener(e -> {
            ExtendedFeatures stats = new ExtendedFeatures(this, manager);
            stats.setVisible(true);
        });
    }

    // Entry point: launches the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }
}

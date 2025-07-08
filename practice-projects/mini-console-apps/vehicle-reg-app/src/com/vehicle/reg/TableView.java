package com.vehicle.reg;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * -----------------------------------------------------------------------------
 * TableView.java
 * Purpose: Displays all registered vehicles in a clean JTable
 * Author: Ndzalama Tihuhlu
 * -----------------------------------------------------------------------------
 */
public class TableView extends JDialog {

    public TableView(JFrame parent, VehicleManager manager) {
        super(parent, "Registered Vehicles", true);
        setSize(700, 300);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        // Column headers for the vehicle table
        String[] columns = {"Make", "Model", "VIN", "Plate", "Mileage (km)", "Year"};

        // Fetch list of vehicles and convert to row format
        List<Car> cars = manager.getAllCars();
        String[][] rows = new String[cars.size()][6];

        for (int i = 0; i < cars.size(); i++) {
            Car c = cars.get(i);
            rows[i][0] = c.getMake();
            rows[i][1] = c.getModel();
            rows[i][2] = c.getVin();
            rows[i][3] = c.getPlateNumber();
            rows[i][4] = String.valueOf(c.getMileage());
            rows[i][5] = String.valueOf(c.getYear());
        }

        // Table model and table creation
        DefaultTableModel model = new DefaultTableModel(rows, columns);
        JTable table = new JTable(model);

        // Add table to scrollable pane
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Close button below the table
        JButton closeBtn = new JButton("Close");
        closeBtn.addActionListener(e -> dispose());

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(closeBtn);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}

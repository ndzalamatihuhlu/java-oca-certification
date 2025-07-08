package com.vehicle.reg;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * -----------------------------------------------------------------------------
 * ExtendedFeatures.java
 * Purpose: Provides additional features to the Vehicle Reg app
 * Author: Ndzalama Tihuhlu
 * -----------------------------------------------------------------------------
 */
public class ExtendedFeatures extends JDialog {

    private final VehicleManager manager;
    private JTextArea statsArea;

    public ExtendedFeatures(JFrame parent, VehicleManager manager) {
        super(parent, "Vehicle Insights & Stats", true);
        this.manager = manager;

        setSize(500, 400);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        // Header
        JLabel header = new JLabel("Vehicle Stats & Insights", SwingConstants.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 18));
        add(header, BorderLayout.NORTH);

        // Text area to show stats
        statsArea = new JTextArea();
        statsArea.setEditable(false);
        statsArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(statsArea);
        add(scrollPane, BorderLayout.CENTER);

        // Button panel
        JPanel bottom = new JPanel();
        JButton refreshBtn = new JButton("Refresh Stats");
        JButton closeBtn = new JButton("Close");
        bottom.add(refreshBtn);
        bottom.add(closeBtn);
        add(bottom, BorderLayout.SOUTH);

        // Action to refresh
        refreshBtn.addActionListener((ActionEvent e) -> showStats());

        // Close button
        closeBtn.addActionListener(e -> dispose());

        // Load initial data
        showStats();
    }

    /**
     * Generates and displays vehicle statistics
     */
    private void showStats() {
        StringBuilder sb = new StringBuilder();

        int total = manager.getAllCars().size();
        int mileageSum = 0;
        int oldestYear = Integer.MAX_VALUE;
        int newestYear = Integer.MIN_VALUE;

        for (Car c : manager.getAllCars()) {
            mileageSum += c.getMileage();
            oldestYear = Math.min(oldestYear, c.getYear());
            newestYear = Math.max(newestYear, c.getYear());
        }

        sb.append("Total Registered Vehicles: ").append(total).append("\n");
        sb.append("Combined Mileage: ").append(mileageSum).append(" km\n");

        if (total > 0) {
            sb.append("Average Mileage: ").append(mileageSum / total).append(" km\n");
            sb.append("Oldest Vehicle Year: ").append(oldestYear).append("\n");
            sb.append("Newest Vehicle Year: ").append(newestYear).append("\n");
        }

        statsArea.setText(sb.toString());
    }
}

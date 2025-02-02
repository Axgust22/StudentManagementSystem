// package com.example.Testing;

// import java.awt.BorderLayout;
// import java.awt.Color;
// import java.awt.Dimension;
// import java.awt.Font;
// import java.awt.Graphics;
// import java.awt.Graphics2D;
// import java.awt.GridLayout;
// import java.awt.RenderingHints;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
// import java.util.ArrayList;
// import java.util.HashSet;
// import java.util.List;
// import java.util.Set;
// import java.util.HashMap;
// import java.util.Map;
// import javax.swing.*;
// import javax.swing.border.LineBorder;
// import javax.swing.table.DefaultTableModel;

// public class testing7 extends JFrame {
// private static final ArrayList<RoundedButton> newButtons = new ArrayList<>();
// private Set<String> deletedCourses = new HashSet<>(); // Track deleted
// courses
// private int courseCounter = 1; // Track the course number
// private Map<String, RoundedButton> courseButtonMap = new HashMap<>();
// private DefaultTableModel model;
// private DefaultTableModel originalModel;

// class RoundedButton extends JButton {
// private int radius;

// public RoundedButton(String text, int radius) {
// super(text);
// this.radius = radius;
// setContentAreaFilled(false);
// setFocusPainted(false);
// }

// protected void paintComponent(Graphics g) {
// Graphics2D g2 = (Graphics2D) g.create();
// g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
// RenderingHints.VALUE_ANTIALIAS_ON);

// // Fill the button background
// g2.setColor(getBackground());
// g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);

// // Draw the button text
// super.paintComponent(g2);
// g2.dispose();
// }

// protected void paintBorder(Graphics g) {
// Graphics2D g2 = (Graphics2D) g.create();
// g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
// RenderingHints.VALUE_ANTIALIAS_ON);

// // Draw the rounded border
// g2.setColor(getForeground());
// g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
// g2.dispose();
// }
// }

// public testing7() {
// setSize(1800, 800);
// setLayout(null);
// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
// Color buttonColor = Color.decode("#62A6FF");

// JPanel p1 = new JPanel();
// p1.setBounds(0, 0, 1800, 800);
// p1.setBackground(Color.white);
// p1.setLayout(null);
// add(p1);

// JPanel p2 = new JPanel();
// p2.setBounds(30, 120, 1500, 630);
// p2.setBackground(Color.white);
// p2.setBorder(new LineBorder(Color.black, 2));
// p2.setLayout(null);
// p1.add(p2);

// JPanel p3 = new JPanel();
// p3.setBounds(5, 5, 300, 620);
// p3.setBackground(Color.white);
// p3.setBorder(new LineBorder(Color.black, 2));
// p3.setLayout(null);
// p2.add(p3);

// JPanel p4 = new JPanel();
// p4.setBounds(310, 5, 1185, 620);
// p4.setBackground(Color.white);
// p4.setBorder(new LineBorder(Color.black, 2));
// p4.setLayout(null);
// p2.add(p4);

// JPanel p5 = new JPanel();
// p5.setBounds(8, 85, 1170, 470);
// p5.setBackground(Color.LIGHT_GRAY);
// p5.setBorder(new LineBorder(Color.BLACK, 2));
// p5.setLayout(new BorderLayout());
// p4.add(p5);

// String[] column = {
// "StudentName", "StundentID", "Gender", "Age", "Year", "Acedemy Year",
// "Attendance"
// };
// model = new DefaultTableModel(null, column);
// originalModel = model; // Save the original model
// JTable jt = new JTable(model);
// JScrollPane sp = new JScrollPane(jt);
// p5.add(sp);

// JLabel l1 = new JLabel("STUDENT MANAGEMENT SYSTEM");
// l1.setBounds(30, 20, 400, 30);
// l1.setFont(new Font("Time now Roman", Font.BOLD, 24));
// p1.add(l1);

// RoundedButton b1 = new RoundedButton("Student Information", 20);
// b1.setBounds(30, 80, 250, 40);
// b1.setFont(new Font("Arial", Font.PLAIN, 18));
// b1.setForeground(Color.black);
// b1.setBackground(buttonColor);
// p1.add(b1);

// RoundedButton b2 = new RoundedButton("Student Course", 20);
// b2.setBounds(300, 80, 250, 40);
// b2.setForeground(Color.black);
// b2.setFont(new Font("Arial", Font.PLAIN, 18));
// b2.setBackground(buttonColor);
// p1.add(b2);

// b2.addActionListener(e -> {
// new testing8(model).setVisible(true);
// });

// RoundedButton b3 = new RoundedButton("Student Score", 20);
// b3.setBounds(570, 80, 250, 40);
// b3.setForeground(Color.black);
// b3.setFont(new Font("Arial", Font.PLAIN, 18));
// b3.setBackground(buttonColor);
// p1.add(b3);

// JLabel lb1 = new JLabel("Course: ");
// lb1.setBounds(10, 10, 200, 30);
// lb1.setFont(new Font("Arial", Font.PLAIN, 18));
// p4.add(lb1);

// JLabel l2 = new JLabel("Search Student Course:");
// l2.setBounds(10, 45, 200, 30);
// l2.setFont(new Font("Arial", Font.PLAIN, 18));
// p4.add(l2);

// JTextField t1 = new JTextField();
// t1.setBounds(210, 45, 220, 30);
// t1.setBorder(new LineBorder(Color.BLACK, 2));
// t1.setFont(new Font("Arial", Font.PLAIN, 18));
// p4.add(t1);

// RoundedButton b4 = new RoundedButton("Search", 20);
// b4.setBounds(450, 45, 150, 30);
// b4.setForeground(Color.BLACK);
// b4.setFont(new Font("Arial", Font.PLAIN, 18));
// b4.setBackground(buttonColor);
// p4.add(b4);

// b4.addActionListener(e -> {
// b4.setBackground(Color.yellow);
// String searchID = t1.getText().trim();
// if (!searchID.isEmpty()) {
// DefaultTableModel tempModel = new DefaultTableModel(null, column);
// boolean found = false;
// for (int i = 0; i < model.getRowCount(); i++) {
// if (model.getValueAt(i, 1).toString().equalsIgnoreCase(searchID)) {
// tempModel.addRow(new Object[] {
// model.getValueAt(i, 0),
// model.getValueAt(i, 1),
// model.getValueAt(i, 2),
// model.getValueAt(i, 3),
// model.getValueAt(i, 4),
// model.getValueAt(i, 5),
// model.getValueAt(i, 6),

// });
// found = true;
// }
// }

// if (!found) {
// JOptionPane.showMessageDialog(this, "StudentID not found!");
// } else {
// jt.setModel(tempModel);
// }
// } else {
// jt.setModel(model); // Restore original model when search field is empty
// JOptionPane.showMessageDialog(this, "Please enter Student ID to search!");
// }
// });

// RoundedButton b5 = new RoundedButton("Back", 20);
// b5.setBounds(630, 45, 150, 30);
// b5.setForeground(Color.black);
// b5.setFont(new Font("Arial", Font.PLAIN, 18));
// b5.setBackground(Color.LIGHT_GRAY);
// p4.add(b5);

// b5.addActionListener(e -> {
// b5.setBackground(Color.yellow);
// jt.setModel(originalModel); // Restore the original model
// t1.setText(""); // Clear the search text field
// JOptionPane.showMessageDialog(this, "Table restored to original state!");
// });

// RoundedButton b6 = new RoundedButton("New", 20);
// b6.setBounds(20, 555, 75, 40);
// b6.setForeground(Color.black);
// b6.setBackground(buttonColor);
// p3.add(b6);

// RoundedButton b7 = new RoundedButton("Delete", 20);
// b7.setBounds(110, 555, 75, 40);
// b7.setForeground(Color.black);
// b7.setBackground(buttonColor);
// p3.add(b7);

// b7.addActionListener(new ActionListener() {

// @Override
// public void actionPerformed(ActionEvent e) {

// if (!newButtons.isEmpty()) {
// RoundedButton lastButton = newButtons.remove(newButtons.size() - 1);
// p3.remove(lastButton);
// revalidate();
// repaint();
// }
// }
// });

// RoundedButton bb7 = new RoundedButton("Update", 20);
// bb7.setBounds(210, 555, 75, 40);
// bb7.setForeground(Color.black);
// bb7.setBackground(buttonColor);
// p3.add(bb7);

// RoundedButton B1 = new RoundedButton("Add Students", 20);
// B1.setBounds(1030, 10, 150, 40);
// B1.setForeground(Color.black);
// B1.setVisible(false);
// B1.setBackground(buttonColor);
// p4.add(B1);

// B1.addActionListener(new ActionListener() {
// public void actionPerformed(ActionEvent e) {
// B1.setBackground(Color.yellow);
// new testing8(model).setVisible(true);
// }
// });

// bb7.addActionListener(new ActionListener() {
// public void actionPerformed(ActionEvent e) {
// if (!newButtons.isEmpty()) {
// String newName = JOptionPane.showInputDialog(this, "Enter new name for the
// button:");
// if (newName != null && !newName.trim().isEmpty()) {
// RoundedButton lastButton = newButtons.get(newButtons.size() - 1);
// lastButton.setText(newName);
// revalidate();
// repaint();
// }
// }

// }
// });

// RoundedButton bb1 = new RoundedButton("OOP", 20);
// bb1.setBounds(0, 0, 300, 40);
// bb1.setForeground(Color.black);
// bb1.setBackground(Color.LIGHT_GRAY);
// p3.add(bb1);

// bb1.addActionListener(new ActionListener() {
// public void actionPerformed(ActionEvent e) {
// lb1.setText("Course: OOP");
// B1.setVisible(true);
// bb1.setBackground(Color.yellow);
// }
// });

// RoundedButton b8 = new RoundedButton("Update", 20);
// b8.setBounds(80, 565, 150, 40);
// b8.setForeground(Color.black);
// b8.setFont(new Font("Arial", Font.PLAIN, 18));
// b8.setBackground(buttonColor);
// p4.add(b8);

// b8.addActionListener(e -> {
// b8.setBackground(Color.red);
// int selectedRow = jt.getSelectedRow(); // Get the selected row index
// if (selectedRow != -1) { // Check if a row is selected
// // Get the current values of the selected row
// String studentNameS = model.getValueAt(selectedRow, 0).toString();
// String StudentId = model.getValueAt(selectedRow, 1).toString();
// String Gender = model.getValueAt(selectedRow, 2).toString();
// String Age = model.getValueAt(selectedRow, 3).toString();
// String Year = model.getValueAt(selectedRow, 4).toString();
// String academyYear = model.getValueAt(selectedRow, 5).toString();
// String Attendence = model.getValueAt(selectedRow, 6).toString();

// // Create text fields with existing student data for editing
// JTextField SN = new JTextField(studentNameS);
// JTextField SI = new JTextField(StudentId);
// JTextField G = new JTextField(Gender);
// JTextField A = new JTextField(Age);
// JTextField Y = new JTextField(Year);
// JTextField AC = new JTextField(academyYear);
// JTextField AT = new JTextField(Attendence);
// // Create a panel with GridLayout to organize components
// JPanel panel = new JPanel();
// panel.setLayout(new GridLayout(10, 2, 20, 20)); // 10 rows, 2 columns
// panel.add(new JLabel("StudentName:"));
// panel.add(SN);
// panel.add(new JLabel("StudentID:"));
// panel.add(SI);
// panel.add(new JLabel("Gender:"));
// panel.add(G);
// panel.add(new JLabel("Age:"));
// panel.add(A);
// panel.add(new JLabel("Year:"));
// panel.add(Y);
// panel.add(new JLabel("Academy Year:"));
// panel.add(AC);
// panel.add(new JLabel("Attendance:"));
// panel.add(AT);

// panel.setPreferredSize(new Dimension(600, 500));

// // Show dialog to modify selected student data
// int option = JOptionPane.showConfirmDialog(null, panel, "Update Student",
// JOptionPane.OK_CANCEL_OPTION);

// if (option == JOptionPane.OK_OPTION) {
// // Validate that all fields are filled
// if (SN.getText().isEmpty() || SI.getText().isEmpty() ||
// G.getText().isEmpty() || A.getText().isEmpty() ||
// Y.getText().isEmpty() || AC.getText().isEmpty() ||
// AT.getText().isEmpty()) {
// JOptionPane.showMessageDialog(this, "Please fill in all the fields before
// submitting.");
// } else {
// // Update the row in the model with the new data
// model.setValueAt(SN.getText(), selectedRow, 0);
// model.setValueAt(SI.getText(), selectedRow, 1);
// model.setValueAt(G.getText(), selectedRow, 2);
// model.setValueAt(A.getText(), selectedRow, 3);
// model.setValueAt(Y.getText(), selectedRow, 4);
// model.setValueAt(AC.getText(), selectedRow, 5);
// model.setValueAt(AT.getText(), selectedRow, 6);
// // Show success message
// JOptionPane.showMessageDialog(this, "Student updated successfully!");

// // Optionally, reset the form or close the dialog
// panel.setVisible(false);
// }
// }
// } else {
// JOptionPane.showMessageDialog(this, "Please select a row to update!");
// }
// });

// RoundedButton b9 = new RoundedButton("Delete", 20);
// b9.setBounds(397, 565, 150, 40);
// b9.setForeground(Color.black);
// b9.setFont(new Font("Arial", Font.PLAIN, 18));
// b9.setBackground(buttonColor);
// p4.add(b9);

// b9.addActionListener(e -> {
// b9.setBackground(Color.red);
// int selectedRow = jt.getSelectedRow();
// if (selectedRow != -1) {
// model.removeRow(selectedRow);
// } else {
// JOptionPane.showMessageDialog(this, "Please select a row to delete!");
// }
// });

// RoundedButton b10 = new RoundedButton("Clear Form", 20);
// b10.setBounds(714, 565, 150, 40);
// b10.setForeground(Color.black);
// b10.setFont(new Font("Arial", Font.PLAIN, 18));
// b10.setBackground(buttonColor);
// p4.add(b10);

// b10.addActionListener(e -> {
// b10.setBackground(Color.red);
// // Clear the search text field
// t1.setText("");

// // Clear the table by removing all rows
// model.setRowCount(0);

// // Optionally, reset any JComboBoxes or other form fields if necessary
// JOptionPane.showMessageDialog(this, "Form cleared successfully!");
// });
// RoundedButton b11 = new RoundedButton("Lay Out", 20);
// b11.setBounds(1021, 565, 150, 40);
// b11.setForeground(Color.black);
// b11.setFont(new Font("Arial", Font.PLAIN, 18));
// b11.setBackground(buttonColor);
// p4.add(b11);

// b6.addActionListener(new ActionListener() {
// public void actionPerformed(ActionEvent e) {
// // Create a new RoundedButton dynamically
// RoundedButton newButton = new RoundedButton("New Button " +
// (newButtons.size() + 1), 20);
// int yPosition = 50 + (newButtons.size() * 50); // Position each new button
// below the previous one
// newButton.setBounds(0, yPosition, 300, 40);
// newButton.setForeground(Color.BLACK);
// newButton.setBackground(Color.LIGHT_GRAY);

// // Add this new button to the list and panel p3
// newButtons.add(newButton);
// p3.add(newButton);

// // Add ActionListener to the new button to update the label
// newButton.addActionListener(new ActionListener() {
// public void actionPerformed(ActionEvent e) {
// newButton.setBackground(Color.cyan);
// B1.setVisible(true);
// lb1.setText("Course: " + newButton.getText()); // Update label to the
// button's text

// }
// });

// // Revalidate and repaint the panel to reflect changes
// revalidate();
// repaint();
// }
// });

// b11.addActionListener(e -> dispose());

// }

// public static void main(String[] args) {
// new testing8().setVisible(true);
// }
// }

package com.example.Testing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class testing7 extends JFrame {
    private static final ArrayList<RoundedButton> newButtons = new ArrayList<>();
    private Set<String> deletedCourses = new HashSet<>(); // Track deleted courses
    private int courseCounter = 1; // Track the course number
    private Map<String, RoundedButton> courseButtonMap = new HashMap<>();
    private DefaultTableModel model;
    private DefaultTableModel originalModel;
    private RoundedButton selectedButton = null;

    class RoundedButton extends JButton {
        private int radius;

        public RoundedButton(String text, int radius) {
            super(text);
            this.radius = radius;
            setContentAreaFilled(false);
            setFocusPainted(false);
        }

        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Fill the button background
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);

            // Draw the button text
            super.paintComponent(g2);
            g2.dispose();
        }

        protected void paintBorder(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Draw the rounded border
            g2.setColor(getForeground());
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
            g2.dispose();
        }
    }

    public testing7() {
        setSize(1800, 800);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Color buttonColor = Color.decode("#62A6FF");

        JPanel p1 = new JPanel();
        p1.setBounds(0, 0, 1800, 800);
        p1.setBackground(Color.white);
        p1.setLayout(null);
        add(p1);

        JPanel p2 = new JPanel();
        p2.setBounds(30, 120, 1500, 630);
        p2.setBackground(Color.white);
        p2.setBorder(new LineBorder(Color.black, 2));
        p2.setLayout(null);
        p1.add(p2);

        // JPanel p3 = new JPanel();
        // p3.setBounds(5, 5, 300, 620);
        // p3.setBackground(Color.white);
        // p3.setBorder(new LineBorder(Color.black, 2));
        // p3.setLayout(null);
        // p2.add(p3);

        JPanel p4 = new JPanel();
        p4.setBounds(310, 5, 1185, 620);
        p4.setBackground(Color.white);
        p4.setBorder(new LineBorder(Color.black, 2));
        p4.setLayout(null);
        p2.add(p4);

        JPanel p5 = new JPanel();
        p5.setBounds(8, 85, 1170, 470);
        p5.setBackground(Color.LIGHT_GRAY);
        p5.setBorder(new LineBorder(Color.BLACK, 2));
        p5.setLayout(new BorderLayout());
        p4.add(p5);

        String[] column = {
                "StudentName", "StundentID", "Gender", "Age", "Year", "Acedemy Year",
                "Attendence"
        };
        model = new DefaultTableModel(null, column);
        originalModel = model; // Save the original model
        JTable jt = new JTable(model);
        JScrollPane sp = new JScrollPane(jt);
        p5.add(sp);

        JLabel l1 = new JLabel("STUDENT MANAGEMENT SYSTEM");
        l1.setBounds(30, 20, 400, 30);
        l1.setFont(new Font("Time now Roman", Font.BOLD, 24));
        p1.add(l1);

        RoundedButton b1 = new RoundedButton("Student Information", 20);
        b1.setBounds(30, 80, 250, 40);
        b1.setFont(new Font("Arial", Font.PLAIN, 18));
        b1.setForeground(Color.black);
        b1.setBackground(buttonColor);
        p1.add(b1);

        RoundedButton b2 = new RoundedButton("Student Course", 20);
        b2.setBounds(300, 80, 250, 40);
        b2.setForeground(Color.black);
        b2.setFont(new Font("Arial", Font.PLAIN, 18));
        b2.setBackground(buttonColor);
        p1.add(b2);

        b2.addActionListener(e ->

        new testing8(model).setVisible(true));

        RoundedButton b3 = new RoundedButton("Student Score", 20);
        b3.setBounds(570, 80, 250, 40);
        b3.setForeground(Color.black);
        b3.setFont(new Font("Arial", Font.PLAIN, 18));
        b3.setBackground(buttonColor);
        p1.add(b3);

        JLabel lb1 = new JLabel("Course: ");
        lb1.setBounds(10, 10, 200, 30);
        lb1.setFont(new Font("Arial", Font.PLAIN, 18));
        p4.add(lb1);

        JLabel l2 = new JLabel("Search Student Course:");
        l2.setBounds(10, 45, 200, 30);
        l2.setFont(new Font("Arial", Font.PLAIN, 18));
        p4.add(l2);

        JTextField t1 = new JTextField();
        t1.setBounds(210, 45, 220, 30);
        t1.setBorder(new LineBorder(Color.BLACK, 2));
        t1.setFont(new Font("Arial", Font.PLAIN, 18));
        p4.add(t1);

        RoundedButton b4 = new RoundedButton("Search", 20);
        b4.setBounds(450, 45, 150, 30);
        b4.setForeground(Color.BLACK);
        b4.setFont(new Font("Arial", Font.PLAIN, 18));
        b4.setBackground(buttonColor);
        p4.add(b4);

        b4.addActionListener(e -> {
            b4.setBackground(Color.yellow);
            String searchID = t1.getText().trim();
            if (!searchID.isEmpty()) {
                DefaultTableModel tempModel = new DefaultTableModel(null, column);
                boolean found = false;
                for (int i = 0; i < model.getRowCount(); i++) {
                    if (model.getValueAt(i, 1).toString().equalsIgnoreCase(searchID)) {
                        tempModel.addRow(new Object[] {
                                model.getValueAt(i, 0),
                                model.getValueAt(i, 1),
                                model.getValueAt(i, 2),
                                model.getValueAt(i, 3),
                                model.getValueAt(i, 4),
                                model.getValueAt(i, 5),
                                model.getValueAt(i, 6),
                                model.getValueAt(i, 7),

                        });
                        found = true;
                    }
                }

                if (!found) {
                    JOptionPane.showMessageDialog(this, "StudentID not found!");
                } else {
                    jt.setModel(tempModel);
                }
            } else {
                jt.setModel(model); // Restore original model when search field is empty
                JOptionPane.showMessageDialog(this, "Please enter Student ID to search!");
            }
        });

        RoundedButton b5 = new RoundedButton("Back", 20);
        b5.setBounds(630, 45, 150, 30);
        b5.setForeground(Color.black);
        b5.setFont(new Font("Arial", Font.PLAIN, 18));
        b5.setBackground(Color.LIGHT_GRAY);
        p4.add(b5);

        b5.addActionListener(e -> {
            b5.setBackground(Color.yellow);
            jt.setModel(originalModel); // Restore the original model
            t1.setText(""); // Clear the search text field
            JOptionPane.showMessageDialog(this, "Table restored to original state!");
        });

        JPanel p3 = new JPanel();
        p3.setLayout(new BoxLayout(p3, BoxLayout.Y_AXIS)); // Use BoxLayout for vertical arrangement
        p3.setBackground(Color.white);
        p3.setBorder(new LineBorder(Color.black, 2));
        // p3.setLayout(null);

        // Wrap p3 in a JScrollPane
        JScrollPane scrollPane = new JScrollPane(p3, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(5, 5, 300, 620);
        p2.add(scrollPane);

        // Add multiple course
        // Create a panel to hold the action buttons (New, Delete, Update)
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS)); // Horizontal arrangement
        buttonPanel.setBackground(Color.white);

        // Add buttons to the button panel
        RoundedButton b6 = new RoundedButton("New", 20);
        b6.setPreferredSize(new Dimension(75, 40)); // Ensure consistent size
        b6.setForeground(Color.black);
        b6.setBackground(buttonColor);
        buttonPanel.add(b6);

        buttonPanel.add(Box.createRigidArea(new Dimension(10, 0))); // Add spacing between buttons

        RoundedButton b7 = new RoundedButton("Delete", 20);
        b7.setPreferredSize(new Dimension(75, 40));
        b7.setForeground(Color.black);
        b7.setBackground(buttonColor);
        buttonPanel.add(b7);

        buttonPanel.add(Box.createRigidArea(new Dimension(10, 0))); // Add spacing between buttons

        RoundedButton bb7 = new RoundedButton("Update", 20);
        bb7.setPreferredSize(new Dimension(75, 40));
        bb7.setForeground(Color.black);
        bb7.setBackground(buttonColor);
        buttonPanel.add(bb7);

        // Add the button panel to p3
        p3.add(buttonPanel);

        // Action listeners remain unchanged
        b6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RoundedButton newButton = new RoundedButton("New Button " + (newButtons.size() + 1), 20);
                newButton.setPreferredSize(new Dimension(280, 40)); // Ensure consistent button size
                newButton.setForeground(Color.BLACK);
                newButton.setBackground(Color.LIGHT_GRAY);

                newButtons.add(newButton);
                p3.add(newButton); // Add button to p3
                p3.revalidate(); // Revalidate p3 to update layout
                p3.repaint(); // Repaint p3 to reflect changes

                // Adjust the size of p3 to fit new components
                p3.setPreferredSize(new Dimension(300, newButtons.size() * 50));

                newButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        selectedButton = newButton;
                        lb1.setText("Course: " + newButton.getText());
                    }
                });
            }
        });

        b7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (selectedButton != null) {
                    newButtons.remove(selectedButton); // Remove from list
                    p3.remove(selectedButton); // Remove from panel
                    selectedButton = null; // Reset the selected button
                    p3.revalidate();
                    p3.repaint();
                    JOptionPane.showMessageDialog(null, "Button deleted successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a button to delete first!", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        bb7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (selectedButton != null) {
                    String newName = JOptionPane.showInputDialog(null, "Enter new name for the button:",
                            selectedButton.getText());
                    if (newName != null && !newName.trim().isEmpty()) {
                        selectedButton.setText(newName); // Update the button text
                        lb1.setText("Course: " + newName); // Update the label with the new button name
                        p3.revalidate();
                        p3.repaint();
                        JOptionPane.showMessageDialog(null, "Button updated successfully!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Name cannot be empty!", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a button to update first!", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Add student in the course
        RoundedButton B1 = new RoundedButton("Add Students", 20);
        B1.setBounds(1030, 10, 150, 40);
        B1.setForeground(Color.black);
        B1.setBackground(buttonColor);
        p4.add(B1);

        B1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                B1.setBackground(Color.yellow);
                new testing8(model).setVisible(true);
            }
        });

        JLabel lb3 = new JLabel("Add Course");
        lb3.setBounds(90, 10, 300, 30);
        lb3.setFont(new Font(getName(), Font.BOLD, 20));
        lb3.setForeground(Color.black);
        p3.add(lb3);

        RoundedButton b8 = new RoundedButton("Update", 20);
        b8.setBounds(80, 565, 150, 40);
        b8.setForeground(Color.black);
        b8.setFont(new Font("Arial", Font.PLAIN, 18));
        b8.setBackground(buttonColor);
        p4.add(b8);

        b8.addActionListener(e -> {
            b8.setBackground(Color.red);
            int selectedRow = jt.getSelectedRow(); // Get the selected row index
            if (selectedRow != -1) { // Check if a row is selected
                // Get the current values of the selected row
                String studentNameS = model.getValueAt(selectedRow, 0).toString();
                String StudentId = model.getValueAt(selectedRow, 1).toString();
                String Gender = model.getValueAt(selectedRow, 2).toString();
                String Age = model.getValueAt(selectedRow, 3).toString();
                String Year = model.getValueAt(selectedRow, 4).toString();
                String academyYear = model.getValueAt(selectedRow, 5).toString();
                String Attendance = model.getValueAt(selectedRow, 6).toString();

                // Create text fields with existing student data for editing
                JTextField SN = new JTextField(studentNameS);
                JTextField SI = new JTextField(StudentId);
                JTextField G = new JTextField(Gender);
                JTextField A = new JTextField(Age);
                JTextField Y = new JTextField(Year);
                JTextField AC = new JTextField(academyYear);
                JTextField AT = new JTextField(Attendance);
                // Create a panel with GridLayout to organize components
                JPanel panel = new JPanel();
                panel.setLayout(new GridLayout(10, 2, 20, 20)); // 10 rows, 2 columns
                panel.add(new JLabel("StudentName:"));
                panel.add(SN);
                panel.add(new JLabel("StudentID:"));
                panel.add(SI);
                panel.add(new JLabel("Gender:"));
                panel.add(G);
                panel.add(new JLabel("Age:"));
                panel.add(A);
                panel.add(new JLabel("Year:"));
                panel.add(Y);
                panel.add(new JLabel("Academy Year:"));
                panel.add(AC);
                panel.add(new JLabel("Attendance :"));
                panel.add(AT);

                panel.setPreferredSize(new Dimension(600, 500));

                // Show dialog to modify selected student data
                int option = JOptionPane.showConfirmDialog(null, panel, "Update Student", JOptionPane.OK_CANCEL_OPTION);

                if (option == JOptionPane.OK_OPTION) {
                    // Validate that all fields are filled
                    if (SN.getText().isEmpty() || SI.getText().isEmpty() ||
                            G.getText().isEmpty() || A.getText().isEmpty() ||
                            Y.getText().isEmpty() || AC.getText().isEmpty() ||
                            AT.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Please fill in all the fields before submitting.");
                    } else {
                        // Update the row in the model with the new data
                        model.setValueAt(SN.getText(), selectedRow, 0);
                        model.setValueAt(SI.getText(), selectedRow, 1);
                        model.setValueAt(G.getText(), selectedRow, 2);
                        model.setValueAt(A.getText(), selectedRow, 3);
                        model.setValueAt(Y.getText(), selectedRow, 4);
                        model.setValueAt(AC.getText(), selectedRow, 5);
                        model.setValueAt(AT.getText(), selectedRow, 6);
                        // Show success message
                        JOptionPane.showMessageDialog(this, "Student updated successfully!");

                        // Optionally, reset the form or close the dialog
                        panel.setVisible(false);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a row to update!");
            }
        });

        RoundedButton b9 = new RoundedButton("Delete", 20);
        b9.setBounds(397, 565, 150, 40);
        b9.setForeground(Color.black);
        b9.setFont(new Font("Arial", Font.PLAIN, 18));
        b9.setBackground(buttonColor);
        p4.add(b9);

        b9.addActionListener(e -> {
            b9.setBackground(Color.red);
            int selectedRow = jt.getSelectedRow();
            if (selectedRow != -1) {
                model.removeRow(selectedRow);
            } else {
                JOptionPane.showMessageDialog(this, "Please select a row to delete!");
            }
        });

        RoundedButton b10 = new RoundedButton("Clear Form", 20);
        b10.setBounds(714, 565, 150, 40);
        b10.setForeground(Color.black);
        b10.setFont(new Font("Arial", Font.PLAIN, 18));
        b10.setBackground(buttonColor);
        p4.add(b10);

        b10.addActionListener(e -> {
            b10.setBackground(Color.red);
            // Clear the search text field
            t1.setText("");

            // Clear the table by removing all rows
            model.setRowCount(0);

            // Optionally, reset any JComboBoxes or other form fields if necessary
            JOptionPane.showMessageDialog(this, "Form cleared successfully!");
        });
        RoundedButton b11 = new RoundedButton("Log Out", 20);
        b11.setBounds(1021, 565, 150, 40);
        b11.setForeground(Color.black);
        b11.setFont(new Font("Arial", Font.PLAIN, 18));
        b11.setBackground(buttonColor);
        p4.add(b11);
        b11.addActionListener(e -> dispose());

    }

    public static void main(String[] args) {
        new testing7().setVisible(true);
    }
}

//
// bb7.addActionListener(new ActionListener() {
// public void actionPerformed(ActionEvent e) {
// if (!newButtons.isEmpty()) {
// String newName = JOptionPane.showInputDialog(null, "Enter new name for the
// button:");
// if (newName != null && !newName.trim().isEmpty()) {
// RoundedButton lastButton = newButtons.get(newButtons.size() - 1);
// lastButton.setText(newName); // Update the button text
// lb1.setText("Course: " + newName); // Update the label with the new button
// name
// revalidate();
// repaint();
// } else {
// JOptionPane.showMessageDialog(null, "Name cannot be empty!", "Error",
// JOptionPane.ERROR_MESSAGE);
// }
// } else {
// JOptionPane.showMessageDialog(null, "No buttons to update!", "Error",
// JOptionPane.ERROR_MESSAGE);
// }
// }
// });

// b7.addActionListener(new ActionListener() {
//
// @Override
// public void actionPerformed(ActionEvent e) {
//
// if (!newButtons.isEmpty()) {
// RoundedButton lastButton = newButtons.remove(newButtons.size() - 1);
// p3.remove(lastButton);
// revalidate();
// repaint();
// }
// }
// });

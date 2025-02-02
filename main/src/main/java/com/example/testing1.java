package com.example;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class testing1 extends JFrame {
    private DefaultTableModel model;
    private DefaultTableModel originalModel; // Store the original model

    public testing1() {
        setSize(1500, 800);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel p1 = new JPanel();
        p1.setBounds(0, 0, 1500, 800);
        p1.setBackground(Color.white);
        p1.setLayout(null);
        add(p1);

        JLabel l1 = new JLabel("COURSE MANAGEMENT");
        l1.setBounds(30, 20, 800, 40);
        l1.setFont(new Font("poppins", Font.BOLD, 36));
        p1.add(l1);

        Color buttonColor = Color.decode("#8FB6FF");

        JButton b1 = new JButton("Student Information");
        b1.setBounds(30, 80, 250, 40);
        b1.setFont(new Font("Arial", Font.PLAIN, 18));
        b1.setBackground(buttonColor);
        p1.add(b1);

        JButton b2 = new JButton("Student Course");
        b2.setBounds(300, 80, 250, 40);
        b2.setFont(new Font("Arial", Font.PLAIN, 18));
        b2.setBackground(buttonColor);
        p1.add(b2);

        JPanel p2 = new JPanel();
        p2.setBounds(30, 120, 1450, 600);
        p2.setBackground(Color.WHITE);
        p2.setBorder(new LineBorder(Color.BLACK, 2));
        p2.setLayout(null);
        p1.add(p2);

        JLabel l3 = new JLabel("Search Student Course:");
        l3.setBounds(30, 20, 200, 30);
        l3.setFont(new Font("Arial", Font.PLAIN, 18));
        p2.add(l3);

        JTextField t1 = new JTextField();
        t1.setBounds(250, 20, 500, 30);
        t1.setBorder(new LineBorder(Color.BLACK, 2));
        t1.setFont(new Font("Arial", Font.PLAIN, 18));
        p2.add(t1);

        JButton b3 = new JButton("Search");
        b3.setBounds(770, 20, 150, 30);
        b3.setFont(new Font("Arial", Font.PLAIN, 18));
        b3.setBackground(buttonColor);
        p2.add(b3);

        JButton b4 = new JButton("Back");
        b4.setBounds(940, 20, 150, 30);
        b4.setFont(new Font("Arial", Font.PLAIN, 18));
        b4.setBackground(buttonColor);
        p2.add(b4);

        JPanel p3 = new JPanel();
        p3.setBounds(20, 70, 1420, 450);
        p3.setBackground(Color.LIGHT_GRAY);
        p3.setBorder(new LineBorder(Color.BLACK, 2));
        p3.setLayout(new BorderLayout());
        p2.add(p3);

        // Define columns for Semester 1 and Semester 2
        String[] column = {
                "StudentID", "Year", "Semester 1", "Course 1", "Course 2", "Course 3", "Course 4",
                "Course 5", "Semester 2", "Course 6", "Course 7", "Course 8", "Course 9", "Course 10"
        };
        model = new DefaultTableModel(null, column);
        originalModel = model; // Save the original model
        JTable jt = new JTable(model);
        JScrollPane sp = new JScrollPane(jt);
        p3.add(sp);

        JButton b5 = new JButton("Add");
        b5.setBounds(20, 530, 150, 40);
        b5.setFont(new Font("Arial", Font.PLAIN, 18));
        b5.setBackground(buttonColor);
        p2.add(b5);

        JButton b6 = new JButton("Update");
        b6.setBounds(280, 530, 150, 40);
        b6.setFont(new Font("Arial", Font.PLAIN, 18));
        b6.setBackground(buttonColor);
        p2.add(b6);

        JButton b7 = new JButton("Delete");
        b7.setBounds(550, 530, 150, 40);
        b7.setFont(new Font("Arial", Font.PLAIN, 18));
        b7.setBackground(buttonColor);
        p2.add(b7);

        JButton b8 = new JButton("Clear Form");
        b8.setBounds(800, 530, 150, 40);
        b8.setFont(new Font("Arial", Font.PLAIN, 18));
        b8.setBackground(buttonColor);
        p2.add(b8);

        // Add functionality
        b5.addActionListener(e -> {
            // Example data for adding a row
            Object[] newRow = {
                    "S123", "1", "1", "Math", "Physics", "Chemistry", "Biology", "English", "2",
                    "CS101", "CS102", "CS103", "CS104", "CS105"
            };
            model.addRow(newRow);
        });

        // Update functionality
        b6.addActionListener(e -> {
            int selectedRow = jt.getSelectedRow();
            if (selectedRow != -1) {
                // Create dialog for Semester 1 and Semester 2 courses
                JTextField stuID = new JTextField((String) model.getValueAt(selectedRow, 0));
                JComboBox<String> yearCombo = new JComboBox<>(new String[] { "1", "2", "3", "4" });
                JComboBox<String> sem1Courses = new JComboBox<>(new String[] { "Math", "Physics", "Chemistry" });
                JComboBox<String> sem2Courses = new JComboBox<>(new String[] { "CS101", "CS102", "CS103" });

                JPanel panel = new JPanel(new GridLayout(6, 2));
                panel.add(new JLabel("Student ID:"));
                panel.add(stuID);
                panel.add(new JLabel("Year:"));
                panel.add(yearCombo);
                panel.add(new JLabel("Semester 1 Course:"));
                panel.add(sem1Courses);
                panel.add(new JLabel("Semester 2 Course:"));
                panel.add(sem2Courses);

                int option = JOptionPane.showConfirmDialog(null, panel, "Update Student Courses",
                        JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    model.setValueAt(stuID.getText(), selectedRow, 0);
                    model.setValueAt(yearCombo.getSelectedItem(), selectedRow, 1);
                    model.setValueAt(sem1Courses.getSelectedItem(), selectedRow, 3);
                    model.setValueAt(sem2Courses.getSelectedItem(), selectedRow, 9);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a row to update!");
            }
        });

        // Delete functionality
        b7.addActionListener(e -> {
            int selectedRow = jt.getSelectedRow();
            if (selectedRow != -1) {
                model.removeRow(selectedRow);
            } else {
                JOptionPane.showMessageDialog(this, "Please select a row to delete!");
            }
        });

        // Clear form functionality
        b8.addActionListener(e -> {
            t1.setText(""); // Clear the search field
            model.setRowCount(0); // Clear the table
            JOptionPane.showMessageDialog(this, "Form cleared successfully!");
        });

        add(p1);
    }

    public static void main(String[] args) {
        new testing1().setVisible(true);
    }
}

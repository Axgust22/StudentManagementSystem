package com.example.Testing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class testing2 extends JFrame {
    private DefaultTableModel model;
    private DefaultTableModel originalModel; // Store the original model

    public testing2() {
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

        String[] column = {
                "StudentID", "Year", "Semester", "Course 1", "Course 2", "Course 3", "Course 4",
                "Course 5"
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

        JButton b10 = new JButton("Lay Out");
        b10.setBounds(1290, 530, 150, 40);
        b10.setFont(new Font("Arial", Font.PLAIN, 18));
        b10.setBackground(buttonColor);
        p2.add(b10);

        b3.addActionListener(e -> {
            String searchID = t1.getText().trim();
            if (!searchID.isEmpty()) {
                DefaultTableModel tempModel = new DefaultTableModel(null, column);
                boolean found = false;
                for (int i = 0; i < model.getRowCount(); i++) {
                    if (model.getValueAt(i, 0).toString().equalsIgnoreCase(searchID)) {
                        tempModel.addRow(new Object[] {
                                model.getValueAt(i, 0),
                                model.getValueAt(i, 1),
                                model.getValueAt(i, 2),
                                model.getValueAt(i, 3),
                                model.getValueAt(i, 4),
                                model.getValueAt(i, 5),
                                model.getValueAt(i, 6),
                                model.getValueAt(i, 7)
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

        // Update the Back button to restore the original model
        b4.addActionListener(e -> {
            jt.setModel(originalModel); // Restore the original model
            t1.setText(""); // Clear the search text field
            JOptionPane.showMessageDialog(this, "Table restored to original state!");
        });

        b5.addActionListener(e -> new testing1(model).setVisible(true));

        b6.addActionListener(e -> {
            int selectedRow = jt.getSelectedRow();
            if (selectedRow != -1) {
                // Get the selected row's data
                String studentID = (String) model.getValueAt(selectedRow, 0);
                String year = (String) model.getValueAt(selectedRow, 1);
                String semester = (String) model.getValueAt(selectedRow, 2);
                String course1 = (String) model.getValueAt(selectedRow, 3);
                String course2 = (String) model.getValueAt(selectedRow, 4);
                String course3 = (String) model.getValueAt(selectedRow, 5);
                String course4 = (String) model.getValueAt(selectedRow, 6);
                String course5 = (String) model.getValueAt(selectedRow, 7);

                // Create JTextField for Student ID
                JTextField stu = new JTextField(studentID, 20); // Set width to 20 columns

                // Create JComboBoxes for Year, Semester, and Courses
                String[] years = { "Select Year", "1", "2", "3", "4", "5" };
                JComboBox<String> c1 = new JComboBox<>(years);
                c1.setSelectedItem(year);

                String[] semesters = { "Select Semester", "1", "2" };
                JComboBox<String> c2 = new JComboBox<>(semesters);
                c2.setSelectedItem(semester);

                String[] Course1 = { "Select Course 1", "Management & Accounting", "Marketing", "Analysis 3",
                        "Computer Architecture", "Statistic", "Automaty Theory", "Advanced Computer Architecture",
                        "Distributed System", "System Architecture Design" };
                JComboBox<String> c3 = new JComboBox<>(Course1);
                c3.setSelectedItem(course1);

                String[] Course2 = { "Select Course 2", "Mechanic", "Calculate 2", "Data Structure And Programming 1",
                        "Equation Differences", "Soft Skill", "Database", "Compilation", "Internat Program 2",
                        "Artificial Intelligence" };
                JComboBox<String> c4 = new JComboBox<>(Course2);
                c4.setSelectedItem(course2);

                String[] Course3 = { "Select Course 3", "Mathematics 1", "Environment", "Electricity",
                        "Data Structure And Programming 2", "Information System Analysis And Design", "MALAB",
                        "Network 1", "Network 2", "Image Processing" };
                JComboBox<String> c5 = new JComboBox<>(Course3);
                c5.setSelectedItem(course3);

                String[] Course4 = { "Select Course 4", "History", "Thermodynamics", "Linear Algebra",
                        "General Chemistry", "Interactive Design", "Human Resource Management", "Physics",
                        "Digital System", "Research Methodology" };
                JComboBox<String> c6 = new JComboBox<>(Course4);
                c6.setSelectedItem(course4);

                String[] Course5 = { "Select Course 5", "Biochemistry", "Data System Analysis", "Java",
                        "Software Engineering", "Algorithm", "Web Application", "Development and Web" };
                JComboBox<String> c7 = new JComboBox<>(Course5);
                c7.setSelectedItem(course5);

                // Create a JPanel with a GridLayout to organize components
                JPanel panel = new JPanel();
                panel.setLayout(new GridLayout(9, 2, 15, 15)); // 9 rows, 2 columns, with 10px horizontal and vertical
                                                               // gaps

                // Add components to the panel
                panel.add(new JLabel("Student ID:"));
                panel.add(stu);
                panel.add(new JLabel("Year:"));
                panel.add(c1);
                panel.add(new JLabel("Semester:"));
                panel.add(c2);
                panel.add(new JLabel("Course 1:"));
                panel.add(c3);
                panel.add(new JLabel("Course 2:"));
                panel.add(c4);
                panel.add(new JLabel("Course 3:"));
                panel.add(c5);
                panel.add(new JLabel("Course 4:"));
                panel.add(c6);
                panel.add(new JLabel("Course 5:"));
                panel.add(c7);

                // Set a larger font for better visibility
                Font font = new Font("Arial", Font.PLAIN, 16);
                for (Component comp : panel.getComponents()) {
                    if (comp instanceof JTextField) {
                        comp.setFont(font);
                    } else if (comp instanceof JComboBox) {
                        comp.setFont(font);
                    } else if (comp instanceof JLabel) {
                        comp.setFont(font);
                    }
                }

                // Set preferred size for the panel to make the dialog box bigger
                panel.setPreferredSize(new Dimension(600, 400)); // Set the size of the dialog

                // Show dialog
                int option = JOptionPane.showConfirmDialog(null, panel, "Update Course", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    model.setValueAt(stu.getText(), selectedRow, 0);
                    model.setValueAt(c1.getSelectedItem(), selectedRow, 1);
                    model.setValueAt(c2.getSelectedItem(), selectedRow, 2);
                    model.setValueAt(c3.getSelectedItem(), selectedRow, 3);
                    model.setValueAt(c4.getSelectedItem(), selectedRow, 4);
                    model.setValueAt(c5.getSelectedItem(), selectedRow, 5);
                    model.setValueAt(c6.getSelectedItem(), selectedRow, 6);
                    model.setValueAt(c7.getSelectedItem(), selectedRow, 7);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a row to update!");
            }

        });

        b7.addActionListener(e -> {
            int selectedRow = jt.getSelectedRow();
            if (selectedRow != -1) {
                model.removeRow(selectedRow);
            } else {
                JOptionPane.showMessageDialog(this, "Please select a row to delete!");
            }
        });
        b8.addActionListener(e -> {
            // Clear the search text field
            t1.setText("");

            // Clear the table by removing all rows
            model.setRowCount(0);

            // Optionally, reset any JComboBoxes or other form fields if necessary
            JOptionPane.showMessageDialog(this, "Form cleared successfully!");
        });
        // b9.addActionListener(e -> {
        // jt.setModel(model); // Reset table model to the original model
        // t1.setText(""); // Clear the search text field
        // JOptionPane.showMessageDialog(this, "Table restored to original state!");
        // });

        b10.addActionListener(e -> dispose());
    }

    public static void main(String[] args) {
        new testing2().setVisible(true);
    }
}

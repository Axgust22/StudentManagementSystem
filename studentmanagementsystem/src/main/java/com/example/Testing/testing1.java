package com.example.Testing;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class testing1 extends JFrame {
    public DefaultTableModel model;

    public testing1(DefaultTableModel tableModel) {
        this.model = tableModel;

        setSize(600, 600);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel p1 = new JPanel();
        p1.setBounds(3, 3, 583, 559);
        p1.setBackground(Color.white);
        p1.setBorder(new LineBorder(Color.BLACK, 2));
        p1.setLayout(null);
        add(p1);

        JLabel l1 = new JLabel("ADD STUDETN COURSE");
        l1.setBounds(130, 20, 300, 30);
        l1.setFont(new Font("Popoit", Font.BOLD, 24));
        p1.add(l1);

        JLabel l2 = new JLabel("StudentID :");
        l2.setBounds(30, 80, 200, 30);
        p1.add(l2);

        JTextField t1 = new JTextField();
        t1.setBounds(120, 85, 250, 25);
        t1.setBorder(new LineBorder(Color.black, 2));
        p1.add(t1);

        JLabel l3 = new JLabel("Year           :");
        l3.setBounds(30, 125, 200, 30);
        p1.add(l3);

        JComboBox<String> c1 = new JComboBox<>(new String[] { " Select Year", " 1 ", " 2 ", " 3 ", " 4 ", " 5 " });
        c1.setBounds(120, 130, 250, 25);
        p1.add(c1);

        JLabel l4 = new JLabel("Semester  :");
        l4.setBounds(30, 165, 200, 30);
        p1.add(l4);

        JComboBox<String> c2 = new JComboBox<>(new String[] { " Select Semester", " 1 ", " 2 " });
        c2.setBounds(120, 170, 250, 25);
        p1.add(c2);

        JLabel l5 = new JLabel("Course 1   :");
        l5.setBounds(30, 205, 200, 30);
        p1.add(l5);

        JComboBox<String> c3 = new JComboBox<>(new String[] { " Select Course 1", " Management & Accounting ",
                " Marketing ", " Analysis 3 ", " Computer Architecture ", " Statistic ", " Automaty Theory ",
                " Advanced Computer Architecture", " Distributed System", "System Architecture Design" });
        c3.setBounds(120, 210, 250, 25);
        p1.add(c3);

        JLabel l6 = new JLabel("Course 2   :");
        l6.setBounds(30, 245, 200, 30);
        p1.add(l6);

        JComboBox<String> c4 = new JComboBox<>(new String[] { " Select Course 2", " Mechanic  ", " Calculate 2 ",
                " Data Structure And Programming 1", " Equation Differences ", " Soft Skill ", " Database",
                " Compilation",
                " Internat Program 2", " Artificial Intelligence" });
        c4.setBounds(120, 250, 250, 25);
        p1.add(c4);

        JLabel l7 = new JLabel("Course 3   :");
        l7.setBounds(30, 285, 200, 30);
        p1.add(l7);

        JComboBox<String> c5 = new JComboBox<>(new String[] { " Select Course 3", " Mathematics 1 ", " Environment",
                " Electricity ", " Data Structure And Programming 2 ", " Information System Analysis And Design ",
                " MALAB ", " Network 1", " NetWork 2", " Image Processing" });
        c5.setBounds(120, 290, 250, 25);
        p1.add(c5);

        JLabel l8 = new JLabel("Course 4   :");
        l8.setBounds(30, 325, 200, 30);
        p1.add(l8);

        JComboBox<String> c6 = new JComboBox<>(new String[] { " Select Course 4", " History ", " Thermodynamics ",
                " Linear algebra ", " Probability ", " Object Obriented Programming ", " Web Design",
                " Human Computer Interaction", " System And Network Administration", " IT Project Management" });
        c6.setBounds(120, 330, 250, 25);
        p1.add(c6);

        JLabel l9 = new JLabel("Course 5   :");
        l9.setBounds(30, 365, 200, 30);
        p1.add(l9);

        JComboBox<String> c7 = new JComboBox<>(new String[] { " Select Course 5", " Philosophy ", " Computer Science ",
                " Null ", " Algorithms and Programmign ", " Theoretical Computer Science", " Operation System",
                " Introduction to Mobile App Dev", " Natural Language Processing" });
        c7.setBounds(120, 370, 250, 25);
        p1.add(c7);

        JButton b1 = new JButton("Back");
        b1.setBounds(20, 450, 100, 40);
        b1.setBackground(Color.decode("#8FB6FF"));
        p1.add(b1);

        b1.addActionListener(e -> {
            dispose();
        });

        JButton b2 = new JButton("Save");
        b2.setBounds(400, 450, 100, 40);
        b2.setBackground(Color.decode("#8FB6FF"));
        p1.add(b2);

        b2.addActionListener(e -> {
            String StudentID = t1.getText();
            String year = (String) c1.getSelectedItem();
            String semester = (String) c2.getSelectedItem();
            String course1 = (String) c3.getSelectedItem();
            String course2 = (String) c4.getSelectedItem();
            String course3 = (String) c5.getSelectedItem();
            String course4 = (String) c6.getSelectedItem();
            String course5 = (String) c7.getSelectedItem();

            if (StudentID.isEmpty() || year.equals("Select Year") || semester.equals("Select Semester")) {
                JOptionPane.showMessageDialog(this, "Please fill all required fields!", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Add data to the table model
            model.addRow(new Object[] { StudentID, year, semester, course1, course2, course3, course4, course5 });
            JOptionPane.showMessageDialog(this, "Course added successfully!");
            dispose(); // Close the window after adding data

        });

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DefaultTableModel model = new DefaultTableModel(new String[] { "StudentID", "Year", "Semester", "Course 1",
                    "Course 2", "Course 3", "Course 4", "Course 5" }, 0);
            new testing1(model).setVisible(true);
        });
    }

}
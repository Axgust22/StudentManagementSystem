package com.example.Testing;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class test5 extends JFrame {
        public DefaultTableModel model;

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

        public test5(DefaultTableModel tableModel) {
                this.model = tableModel;

                setSize(600, 800); // Increased height for Semester 2 fields
                setLayout(null);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                JPanel p1 = new JPanel();
                p1.setBounds(2, 2, 584, 759); // Adjusted height
                p1.setBackground(Color.white);
                p1.setBorder(new LineBorder(Color.BLACK, 2));
                p1.setLayout(null);
                add(p1);

                JLabel l1 = new JLabel("ADD STUDENT COURSE");
                l1.setBounds(130, 20, 300, 30);
                l1.setFont(new Font("Poppins", Font.BOLD, 24));
                p1.add(l1);

                // Semester 1 Fields
                JLabel l2 = new JLabel("StudentID:");
                l2.setBounds(30, 80, 200, 30);
                p1.add(l2);

                JTextField t1 = new JTextField();
                t1.setBounds(120, 85, 250, 25);
                t1.setBorder(new LineBorder(Color.black, 2));
                p1.add(t1);

                JLabel l3 = new JLabel("Year:");
                l3.setBounds(30, 125, 200, 30);
                p1.add(l3);

                JComboBox<String> c1 = new JComboBox<>(new String[] { "Select Year", "1", "2", "3", "4", "5" });
                c1.setBounds(120, 130, 250, 25);
                p1.add(c1);

                JLabel l4 = new JLabel("Semester 1:");
                l4.setBounds(30, 165, 200, 30);
                p1.add(l4);

                JComboBox<String> c2 = new JComboBox<>(new String[] { "1" });
                c2.setBounds(120, 170, 250, 25);
                p1.add(c2);

                JLabel l5 = new JLabel("Course 1:");
                l5.setBounds(30, 205, 200, 30);
                p1.add(l5);

                JComboBox<String> c3 = new JComboBox<>(new String[] { "Select Course 1",
                                "Management & Accounting", // 1
                                "Analysis 3", // 2
                                "Statistics", // 3
                                "Advanced Computer Architecture",
                                "System Architecture Design" });
                c3.setBounds(120, 210, 250, 25);
                p1.add(c3);

                JLabel l6 = new JLabel("Course 2:");
                l6.setBounds(30, 245, 200, 30);
                p1.add(l6);

                JComboBox<String> c4 = new JComboBox<>(new String[] { "Select Course 2",
                                "Mathematics 1", // 1
                                "Mechanics 2", // 2
                                "Soft Skills", // 3
                                "Compilation", // 4
                                "Artificial Intelligence" // 5
                });
                c4.setBounds(120, 250, 250, 25);
                p1.add(c4);

                JLabel l7 = new JLabel("Course 3:");
                l7.setBounds(30, 285, 200, 30);
                p1.add(l7);

                JComboBox<String> c5 = new JComboBox<>(new String[] {
                                "Select Course 3",
                                "Philosophy", // 1
                                "Electricity", // 2
                                "Algorithms And Programming", // 3
                                "Network 1", // 4
                                "Image Processing" // 5
                });
                c5.setBounds(120, 290, 250, 25);
                p1.add(c5);

                JLabel l8 = new JLabel("Course 4:");
                l8.setBounds(30, 325, 200, 30);
                p1.add(l8);

                JComboBox<String> c6 = new JComboBox<>(new String[] {
                                "Select Course 4",
                                "History", // 1
                                "Linear Algebra", // 2
                                "Object-Oriented Programming", // 3
                                "Human Computer Interaction", // 4
                                "IT Project Management" });// 5
                c6.setBounds(120, 330, 250, 25);
                p1.add(c6);

                JLabel l9 = new JLabel("Course 5:");
                l9.setBounds(30, 365, 200, 30);
                p1.add(l9);

                JComboBox<String> c7 = new JComboBox<>(new String[] { "Select Course 5",
                                "Null", // 1
                                "Data Structures And Programming 1", // 2
                                "Information System Analysis and Design", // 3
                                "Operation System", // 4
                                "Natural Language Processing"// 5
                });
                c7.setBounds(120, 370, 250, 25);
                p1.add(c7);

                // Semester 2 Fields
                JLabel l10 = new JLabel("Semester 2 :");
                l10.setBounds(30, 405, 200, 30);
                p1.add(l10);

                JComboBox<String> c8 = new JComboBox<>(new String[] { "2" });
                c8.setBounds(120, 410, 250, 25);
                p1.add(c8);

                JLabel l11 = new JLabel("Course 6:");
                l11.setBounds(30, 445, 200, 30);
                p1.add(l11);

                JComboBox<String> c9 = new JComboBox<>(new String[] {
                                "Select Course 6",
                                "Marketing", // 1
                                "Computer Architecture", // 2
                                "Database", // 3
                                "NetWork 2", // 4
                                "Final Year Internship"// 5

                });
                c9.setBounds(120, 450, 250, 25);
                p1.add(c9);

                JLabel l12 = new JLabel("Course 7:");
                l12.setBounds(30, 485, 200, 30);
                p1.add(l12);

                JComboBox<String> c10 = new JComboBox<>(new String[] {
                                "Select Course 7",
                                "Calculus 2", // 1
                                "Data Structures And Programming 2", // 2
                                "Automata Theory", // 3
                                "Distributed System", // 4
                                "Final Year Internship"// 5

                });
                c10.setBounds(120, 490, 250, 25);
                p1.add(c10);

                JLabel l13 = new JLabel("Course 8:");
                l13.setBounds(30, 525, 200, 30);
                p1.add(l13);

                JComboBox<String> c11 = new JComboBox<>(new String[] {
                                "Select Course 8",
                                "Environment", // 1
                                "Differentail Equations", // 2
                                "Theoretical Computer Science", // 3
                                "Internet Programming 2", // 4
                                "Final Year Internship"// 5

                });
                c11.setBounds(120, 530, 250, 25);
                p1.add(c11);

                JLabel l14 = new JLabel("Course 9:");
                l14.setBounds(30, 565, 200, 30);
                p1.add(l14);

                JComboBox<String> c12 = new JComboBox<>(new String[] {
                                "Select Course 9",
                                "Thermodynamics", // 1
                                "Probability", // 2
                                "MATLAB", // 3
                                "System And Network Administraction", // 4
                                "Final Year Internship"// 5
                });
                c12.setBounds(120, 570, 250, 25);
                p1.add(c12);

                JLabel l15 = new JLabel("Course 10:");
                l15.setBounds(30, 605, 200, 30);
                p1.add(l15);

                JComboBox<String> c13 = new JComboBox<>(new String[] {
                                "Select Course 10",
                                "Computer Science", // 1
                                "Null", // 2
                                "Web Design", // 3
                                "Software Develop And IT Operation ", // 4
                                "Final Year Internship"// 5
                });
                c13.setBounds(120, 610, 250, 25);
                p1.add(c13);

                RoundedButton b1 = new RoundedButton("Back", 20);
                b1.setBounds(20, 680, 100, 40);
                b1.setForeground(Color.black);
                b1.setBackground(Color.LIGHT_GRAY);
                p1.add(b1);

                b1.addActionListener(e -> dispose());

                RoundedButton b2 = new RoundedButton("Save", 20);
                b2.setBounds(400, 680, 100, 40);
                b2.setForeground(Color.white);
                b2.setBackground(Color.blue);
                p1.add(b2);

                b2.addActionListener(e -> {
                        String StudentID = t1.getText();
                        String year = (String) c1.getSelectedItem();
                        String sem1 = (String) c2.getSelectedItem();
                        String sem2 = (String) c8.getSelectedItem();
                        String course1 = (String) c3.getSelectedItem();
                        String course2 = (String) c4.getSelectedItem();
                        String course3 = (String) c5.getSelectedItem();
                        String course4 = (String) c6.getSelectedItem();
                        String course5 = (String) c7.getSelectedItem();
                        String course6 = (String) c9.getSelectedItem();
                        String course7 = (String) c10.getSelectedItem();
                        String course8 = (String) c11.getSelectedItem();
                        String course9 = (String) c12.getSelectedItem();
                        String course10 = (String) c13.getSelectedItem();

                        if (StudentID.isEmpty() || year.equals("Select Year")) {
                                JOptionPane.showMessageDialog(this, "Please fill all required fields!", "Error",
                                                JOptionPane.ERROR_MESSAGE);
                                return;
                        }

                        // Add data to the table model
                        model.addRow(new Object[] {
                                        StudentID, year, sem1, course1, course2, course3, course4, course5,
                                        sem2, course6, course7, course8, course9, course10
                        });

                        JOptionPane.showMessageDialog(this, "Course added successfully!");
                        dispose();
                });
        }

        public static void main(String[] args) {
                SwingUtilities.invokeLater(() -> {
                        DefaultTableModel model = new DefaultTableModel(new String[] {
                                        "StudentID", "Year", "Semester 1", "Course 1", "Course 2", "Course 3",
                                        "Course 4",
                                        "Course 5", "Semester 2", "Course 6", "Course 7", "Course 8", "Course 9",
                                        "Course 10"
                        }, 0);
                        new test5(model).setVisible(true);
                });
        }
}
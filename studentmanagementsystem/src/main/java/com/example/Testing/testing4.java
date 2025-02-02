package com.example.Testing;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class testing4 extends JFrame {
    private DefaultTableModel model;
    private DefaultTableModel originalModel; // Store the original model

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

    public testing4() {
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

        Color buttonColor = Color.blue;

        RoundedButton b1 = new RoundedButton("Student Information", 20);
        b1.setBounds(30, 80, 250, 40);
        b1.setForeground(Color.white);
        b1.setFont(new Font("Arial", Font.PLAIN, 18));
        b1.setBackground(buttonColor);
        p1.add(b1);

        RoundedButton b2 = new RoundedButton("Student Course", 20);
        b2.setBounds(300, 80, 250, 40);
        b2.setForeground(Color.white);
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

        RoundedButton b3 = new RoundedButton("Search", 20);
        b3.setBounds(770, 15, 150, 40);
        b3.setForeground(Color.white);
        b3.setFont(new Font("Arial", Font.PLAIN, 18));
        b3.setBackground(buttonColor);
        p2.add(b3);

        RoundedButton b4 = new RoundedButton("Back", 20);
        b4.setBounds(940, 15, 150, 40);
        b4.setForeground(Color.black);
        b4.setFont(new Font("Arial", Font.PLAIN, 18));
        b4.setBackground(Color.LIGHT_GRAY);
        p2.add(b4);

        JPanel p3 = new JPanel();
        p3.setBounds(20, 70, 1420, 450);
        p3.setBackground(Color.LIGHT_GRAY);
        p3.setBorder(new LineBorder(Color.BLACK, 2));
        p3.setLayout(new BorderLayout());
        p2.add(p3);

        String[] column = {
                "StudentID", "Year", "Semester 1", "Course 1", "Course 2", "Course 3", "Course 4",
                "Course 5", "Semester 2", "Course 6", "Course 7", "Course 8", "Course 9",
                "Course 10"
        };
        model = new DefaultTableModel(null, column);
        originalModel = model; // Save the original model
        JTable jt = new JTable(model);
        JScrollPane sp = new JScrollPane(jt);
        p3.add(sp);

        RoundedButton b5 = new RoundedButton("Add", 20);
        b5.setBounds(85, 530, 150, 40);
        b5.setForeground(Color.white);
        b5.setFont(new Font("Arial", Font.PLAIN, 18));
        b5.setBackground(buttonColor);
        p2.add(b5);

        RoundedButton b6 = new RoundedButton("Update", 20);
        b6.setBounds(360, 530, 150, 40);
        b6.setForeground(Color.white);
        b6.setFont(new Font("Arial", Font.PLAIN, 18));
        b6.setBackground(buttonColor);
        p2.add(b6);

        RoundedButton b7 = new RoundedButton("Delete", 20);
        b7.setBounds(660, 530, 150, 40);
        b7.setForeground(Color.white);
        b7.setFont(new Font("Arial", Font.PLAIN, 18));
        b7.setBackground(buttonColor);
        p2.add(b7);

        RoundedButton b8 = new RoundedButton("Clear Form", 20);
        b8.setBounds(950, 530, 150, 40);
        b8.setForeground(Color.white);
        b8.setFont(new Font("Arial", Font.PLAIN, 18));
        b8.setBackground(buttonColor);
        p2.add(b8);

        RoundedButton b10 = new RoundedButton("Log Out", 20);
        b10.setBounds(1220, 530, 150, 40);
        b10.setForeground(Color.white);
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
                                model.getValueAt(i, 7),
                                model.getValueAt(i, 8),
                                model.getValueAt(i, 9),
                                model.getValueAt(i, 10),
                                model.getValueAt(i, 11),
                                model.getValueAt(i, 12),
                                model.getValueAt(i, 13)
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

        b5.addActionListener(e -> new test5(model).setVisible(true));

        b6.addActionListener(e -> {
            int selectedRow = jt.getSelectedRow();
            if (selectedRow != -1) {
                // Get data for Semester 1 and Semester 2
                String studentID = (String) model.getValueAt(selectedRow, 0);
                String year = (String) model.getValueAt(selectedRow, 1);
                String semester1 = (String) model.getValueAt(selectedRow, 2);
                String course1 = (String) model.getValueAt(selectedRow, 3);
                String course2 = (String) model.getValueAt(selectedRow, 4);
                String course3 = (String) model.getValueAt(selectedRow, 5);
                String course4 = (String) model.getValueAt(selectedRow, 6);
                String course5 = (String) model.getValueAt(selectedRow, 7);
                String semester2 = (String) model.getValueAt(selectedRow, 8);
                String course6 = (String) model.getValueAt(selectedRow, 9);
                String course7 = (String) model.getValueAt(selectedRow, 10);
                String course8 = (String) model.getValueAt(selectedRow, 11);
                String course9 = (String) model.getValueAt(selectedRow, 12);
                String course10 = (String) model.getValueAt(selectedRow, 13);

                JTextField stu = new JTextField(studentID, 20);

                // Create JComboBoxes for Year, Semester, and Courses
                String[] years = { "Select Year", "1", "2", "3", "4", "5" };
                JComboBox<String> y1 = new JComboBox<>(years);
                y1.setSelectedItem(year);

                String[] semesters1 = { "1", };
                JComboBox<String> s1 = new JComboBox<>(semesters1);
                s1.setSelectedItem(semester1);

                String[] Course1 = { "Select Course 1", " Management & Accounting", "Analysis ", "Statistics",
                        "Advanced Computer Architecture", "System Architecture Design" };
                JComboBox<String> c1 = new JComboBox<>(Course1);
                c1.setSelectedItem(course1);

                String[] Course2 = { "Select Course 2",
                        "Mathematics 1", // 1
                        "Mechanics 2", // 2
                        "Soft Skills", // 3
                        "Compilation", // 4
                        "Artificial Intelligence" // 5
                };
                JComboBox<String> c2 = new JComboBox<>(Course2);
                c2.setSelectedItem(course2);

                String[] Course3 = { "Select Course 3",
                        "Philosophy", // 1
                        "Electricity", // 2
                        "Algorithms And Programming", // 3
                        "Network 1", // 4
                        "Image Processing" // 5
                };
                JComboBox<String> c3 = new JComboBox<>(Course3);
                c3.setSelectedItem(course3);

                String[] Course4 = {
                        "Select Course 4",
                        "History", // 1
                        "Linear Algebra", // 2
                        "Object-Oriented Programming", // 3
                        "Human Computer Interaction", // 4
                        "IT Project Management" // 5
                };
                JComboBox<String> c4 = new JComboBox<>(Course4);
                c4.setSelectedItem(course4);

                String[] Course5 = { "Select Course 5",
                        "Null", // 1
                        "Data Structures And Programming 1", // 2
                        "Information System Analysis and Design", // 3
                        "Operation System", // 4
                        "Natural Language Processing"// 5
                };
                JComboBox<String> c5 = new JComboBox<>(Course5);
                c5.setSelectedItem(course5);

                String[] semesters2 = { "2", };
                JComboBox<String> s2 = new JComboBox<>(semesters2);
                s2.setSelectedItem(semester2);

                String[] Course6 = { "Select Course 6",
                        "Marketing", // 1
                        "Computer Architecture", // 2
                        "Database", // 3
                        "NetWork 2", // 4
                        "Final Year Internship"// 5
                };
                JComboBox<String> c6 = new JComboBox<>(Course6);
                c6.setSelectedItem(course6);

                String[] Course7 = { "Select Course 7",
                        "Calculus 2", // 1
                        "Data Structures And Programming 2", // 2
                        "Automata Theory", // 3
                        "Distributed System", // 4
                        "Final Year Internship"// 5
                };
                JComboBox<String> c7 = new JComboBox<>(Course7);
                c7.setSelectedItem(course7);

                String[] Course8 = { "Select Course 8",
                        "Environment", // 1
                        "Differentail Equations", // 2
                        "Theoretical Computer Science", // 3
                        "Internet Programming 2", // 4
                        "Final Year Internship"// 5
                };
                JComboBox<String> c8 = new JComboBox<>(Course8);
                c8.setSelectedItem(course8);

                String[] Course9 = { "Select Course 9",
                        "Thermodynamics", // 1
                        "Probability", // 2
                        "MATLAB", // 3
                        "System And Network Administraction", // 4
                        "Final Year Internship"// 5
                };
                JComboBox<String> c9 = new JComboBox<>(Course9);
                c9.setSelectedItem(course9);

                String[] Course10 = {
                        "Select Course 10",
                        "Computer Science", // 1
                        "Null", // 2
                        "Web Design", // 3
                        "Software Develop And IT Operation ", // 4
                        "Final Year Internship"// 5
                };
                JComboBox<String> c10 = new JComboBox<>(Course10);
                c10.setSelectedItem(course10);

                // Panel for Semester 2 fields
                JPanel panel = new JPanel(new GridLayout(14, 2, 15, 15));
                panel.add(new JLabel("Student ID:"));
                panel.add(stu);
                panel.add(new JLabel("Year:"));
                panel.add(y1);
                panel.add(new JLabel("Semester :"));
                panel.add(s1);
                panel.add(new JLabel("Course 1:"));
                panel.add(c1);
                panel.add(new JLabel("Course 2:"));
                panel.add(c2);
                panel.add(new JLabel("Course 3:"));
                panel.add(c3);
                panel.add(new JLabel("Course 4:"));
                panel.add(c4);
                panel.add(new JLabel("Course 5:"));
                panel.add(c5);
                panel.add(new JLabel("Semester :"));
                panel.add(s2);
                panel.add(new JLabel("Course 6:"));
                panel.add(c6);
                panel.add(new JLabel("Course 7:"));
                panel.add(c7);
                panel.add(new JLabel("Course 8:"));
                panel.add(c8);
                panel.add(new JLabel("Course 9:"));
                panel.add(c9);
                panel.add(new JLabel("Course 10:"));
                panel.add(c10);

                panel.setPreferredSize(new Dimension(500, 600));
                // Dialog Box for Updates
                int option = JOptionPane.showConfirmDialog(null, panel, "Update Semester 2",
                        JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    model.setValueAt(stu.getText(), selectedRow, 0);
                    model.setValueAt(y1.getSelectedItem(), selectedRow, 1);
                    model.setValueAt(s1.getSelectedItem(), selectedRow, 2);
                    model.setValueAt(c1.getSelectedItem(), selectedRow, 3);
                    model.setValueAt(c2.getSelectedItem(), selectedRow, 4);
                    model.setValueAt(c3.getSelectedItem(), selectedRow, 5);
                    model.setValueAt(c4.getSelectedItem(), selectedRow, 6);
                    model.setValueAt(c5.getSelectedItem(), selectedRow, 7);
                    model.setValueAt(s2.getSelectedItem(), selectedRow, 8);
                    model.setValueAt(c6.getSelectedItem(), selectedRow, 9);
                    model.setValueAt(c7.getSelectedItem(), selectedRow, 10);
                    model.setValueAt(c8.getSelectedItem(), selectedRow, 11);
                    model.setValueAt(c9.getSelectedItem(), selectedRow, 12);
                    model.setValueAt(c10.getSelectedItem(), selectedRow, 13);
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

        b10.addActionListener(e -> {
            // new MainSystem().setVisible(true);
            dispose();
        });
    }

    public static void main(String[] args) {
        new testing4().setVisible(true);
    }
}
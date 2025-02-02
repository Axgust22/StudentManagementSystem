package com.example.Testing;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class testing7 extends JFrame {
    private DefaultTableModel model;
    private DefaultTableModel originalModel;
    private ArrayList<RoundedButton> newButtons = new ArrayList<>();
    private JPanel p1, p3, p4, p5;
    private JLabel lb1;
    private RoundedButton B1;
    private Color buttonColor = Color.LIGHT_GRAY;

    public testing7() {
        setTitle("Student Management System");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        p1 = new JPanel();
        p1.setBounds(0, 0, 1200, 150);
        p1.setLayout(null);
        add(p1);

        p3 = new JPanel();
        p3.setBounds(0, 150, 1200, 600);
        p3.setLayout(null);
        add(p3);

        p4 = new JPanel();
        p4.setBounds(0, 150, 1200, 600);
        p4.setLayout(null);
        add(p4);

        p5 = new JPanel();
        p5.setBounds(8, 85, 1170, 470);
        p5.setBackground(Color.LIGHT_GRAY);
        p5.setBorder(new LineBorder(Color.BLACK, 2));
        p5.setLayout(new BorderLayout());
        p4.add(p5);

        String[] column = {
                "StudentName", "StudentID", "Gender", "Age", "Year", "Academy Year",
                "Attendance"
        };
        model = new DefaultTableModel(null, column);
        originalModel = model; // Save the original model
        JTable jt = new JTable(model);
        JScrollPane sp = new JScrollPane(jt);
        p5.add(sp);

        JLabel l1 = new JLabel("STUDENT MANAGEMENT SYSTEM");
        l1.setBounds(30, 20, 400, 30);
        l1.setFont(new Font("Times New Roman", Font.BOLD, 24)); // Corrected font name
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

        b2.addActionListener(e -> {
            new testing8(model).setVisible(true); // Ensure testing8 class is defined and imported
        });

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

        RoundedButton b6 = new RoundedButton("New", 20);
        b6.setBounds(20, 555, 75, 40);
        b6.setForeground(Color.black);
        b6.setBackground(buttonColor);
        p3.add(b6);

        b6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Create a new button with a unique name
                String newButtonName = "New Button " + (newButtons.size() + 1);

                // Create a new RoundedButton
                RoundedButton newButton = new RoundedButton(newButtonName, 20);
                newButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Center alignment in BoxLayout
                newButton.setPreferredSize(new Dimension(280, 40)); // Set consistent button size
                newButton.setMaximumSize(new Dimension(280, 40)); // Ensure size constraints
                newButton.setForeground(Color.BLACK);
                newButton.setBackground(Color.LIGHT_GRAY);

                // Add this new button to the list and panel p3
                newButtons.add(newButton);
                p3.add(newButton);

                // Add ActionListener to the new button to update the label
                newButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        newButton.setBackground(Color.cyan);
                        B1.setVisible(true);
                        lb1.setText("Course: " + newButton.getText()); // Update label to the button's text
                    }
                });

                // Revalidate and repaint the panel to reflect changes
                p3.revalidate();
                p3.repaint();
            }
        });

        RoundedButton b7 = new RoundedButton("Delete", 20);
        b7.setBounds(110, 555, 75, 40);
        b7.setForeground(Color.black);
        b7.setBackground(buttonColor);
        p3.add(b7);

        b7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Find the selected button (highlighted in cyan)
                RoundedButton selectedButton = null;

                for (RoundedButton button : newButtons) {
                    if (button.getBackground().equals(Color.CYAN)) {
                        selectedButton = button;
                        break;
                    }
                }

                if (selectedButton != null) {
                    // Remove the selected button from the list and panel
                    newButtons.remove(selectedButton);
                    p3.remove(selectedButton);

                    // Refresh the panel
                    p3.revalidate();
                    p3.repaint();

                    JOptionPane.showMessageDialog(null, "Button deleted successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "No button selected for deletion!", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        RoundedButton bb7 = new RoundedButton("Update", 20);
        bb7.setBounds(210, 555, 75, 40);
        bb7.setForeground(Color.black);
        bb7.setBackground(buttonColor);
        p3.add(bb7);

        bb7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RoundedButton selectedButton = null;

                // Find the selected button (highlighted in cyan)
                for (RoundedButton button : newButtons) {
                    if (button.getBackground().equals(Color.cyan)) {
                        selectedButton = button;
                        break;
                    }
                }

                if (selectedButton != null) {
                    String newName = JOptionPane.showInputDialog(null,
                            "Enter new name for the button:");
                    if (newName != null && !newName.trim().isEmpty()) {
                        selectedButton.setText(newName); // Update the button's text
                        lb1.setText("Course: " + newName); // Update the label with the new button name
                        p3.revalidate();
                        p3.repaint();
                    } else {
                        JOptionPane.showMessageDialog(null, "Name cannot be empty!", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No button selected!", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        B1 = new RoundedButton("Add Students", 20);
        B1.setBounds(1030, 10, 150, 40);
        B1.setForeground(Color.black);
        B1.setVisible(false);
        B1.setBackground(buttonColor);
        p4.add(B1);

        B1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                B1.setBackground(Color.yellow);
                new testing8(model).setVisible(true);
            }
        });

        RoundedButton b11 = new RoundedButton("Exit", 20);
        b11.setBounds(1030, 60, 150, 40);
        b11.setForeground(Color.black);
        b11.setBackground(buttonColor);
        p4.add(b11);

        b11.addActionListener(e -> dispose());

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
                String Attendence = model.getValueAt(selectedRow, 6).toString();

                // Create text fields with existing student data for editing
                JTextField SN = new JTextField(studentNameS);
                JTextField SI = new JTextField(StudentId);
                JTextField G = new JTextField(Gender);
                JTextField A = new JTextField(Age);
                JTextField Y = new JTextField(Year);
                JTextField AC = new JTextField(academyYear);
                JTextField AT = new JTextField(Attendence);
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
                panel.add(new JLabel("Attendance:"));
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
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a row to update!");
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new testing7().setVisible(true);
        });
    }
}
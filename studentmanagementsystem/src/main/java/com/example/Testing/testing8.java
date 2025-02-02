package com.example.Testing;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class testing8 extends JFrame {
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

    public testing8(DefaultTableModel tableModel) {
        this.model = tableModel;

        setSize(500, 500);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel p1 = new JPanel();
        p1.setBounds(3, 3, 482, 458);
        p1.setBackground(Color.white);
        p1.setBorder(new LineBorder(Color.BLACK, 2));
        p1.setLayout(null);
        add(p1);

        JLabel l1 = new JLabel("ADD STUDENT");
        l1.setBounds(180, 20, 300, 30);
        l1.setFont(new Font("Popoit", Font.BOLD, 24));
        p1.add(l1);

        JLabel l2 = new JLabel("StudentName :");
        l2.setBounds(30, 80, 200, 30);
        p1.add(l2);
        JTextField t1 = new JTextField();
        t1.setBounds(130, 85, 250, 25);
        t1.setBorder(new LineBorder(Color.black, 2));
        p1.add(t1);

        JLabel l3 = new JLabel("StudentID:");
        l3.setBounds(30, 125, 200, 30);
        p1.add(l3);
        JTextField t2 = new JTextField();
        t2.setBounds(130, 130, 250, 25);
        t2.setBorder(new LineBorder(Color.black, 2));
        p1.add(t2);

        JLabel l4 = new JLabel("Gender :");
        l4.setBounds(30, 165, 200, 30);
        p1.add(l4);
        JTextField t3 = new JTextField();
        t3.setBounds(130, 170, 250, 25);
        t3.setBorder(new LineBorder(Color.black, 2));
        p1.add(t3);

        JLabel l5 = new JLabel("Age  :");
        l5.setBounds(30, 205, 200, 30);
        p1.add(l5);
        JTextField t4 = new JTextField();
        t4.setBounds(130, 210, 250, 25);
        t4.setBorder(new LineBorder(Color.black, 2));
        p1.add(t4);

        JLabel l6 = new JLabel("Year  :");
        l6.setBounds(30, 245, 200, 30);
        p1.add(l6);
        JTextField t5 = new JTextField();
        t5.setBounds(130, 250, 250, 25);
        t5.setBorder(new LineBorder(Color.black, 2));
        p1.add(t5);

        JLabel l7 = new JLabel("Academy Year :");
        l7.setBounds(30, 285, 200, 30);
        p1.add(l7);
        JTextField t6 = new JTextField();
        t6.setBounds(130, 290, 250, 25);
        t6.setBorder(new LineBorder(Color.black, 2));
        p1.add(t6);

        JLabel l8 = new JLabel("Attendance :");
        l8.setBounds(30, 325, 200, 30);
        p1.add(l8);
        JTextField t7 = new JTextField();
        t7.setBounds(130, 330, 250, 25);
        t7.setBorder(new LineBorder(Color.black, 2));
        p1.add(t7);

        RoundedButton b1 = new RoundedButton("Back", 20);
        b1.setBounds(40, 400, 100, 40);
        b1.setBackground(Color.lightGray);
        b1.setForeground(Color.black);
        p1.add(b1);
        b1.addActionListener(e -> dispose());

        RoundedButton b2 = new RoundedButton("Save", 20);
        b2.setBounds(300, 400, 100, 40);
        b2.setBackground(Color.BLUE);
        b2.setForeground(Color.white);
        p1.add(b2);
        b2.addActionListener(e -> {
            b2.setBackground(Color.red);
            String StudentName = t1.getText();
            String StudentID = t2.getText();
            String Gender = t3.getText();
            String AcademyYear = t6.getText();
            String Attendence = t7.getText();
            int Age, Year;

            try {
                String ageInput = t4.getText().trim();
                Age = Integer.parseInt(ageInput);
                if (Age <= 0) {
                    JOptionPane.showMessageDialog(null, "Age must be a positive number.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid number for Age.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                String yearInput = t5.getText().trim();
                Year = Integer.parseInt(yearInput);
                if (Year <= 0) {
                    JOptionPane.showMessageDialog(null, "Year must be a positive number.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid number for Year.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            model.addRow(new Object[] {
                    StudentName, StudentID, Gender, Age, Year, AcademyYear, Attendence
            });

            JOptionPane.showMessageDialog(this, "Student added successfully!");
            dispose();
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DefaultTableModel model = new DefaultTableModel(
                    new String[] { "StudentName", "StudentID", "Gender", "Age", "Year", "AcademyYear", "Attendence" },
                    0);
            new testing8(model).setVisible(true);
        });
    }
}

package com.example.Testing;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class test2 extends JFrame {
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

            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);

            super.paintComponent(g2);
            g2.dispose();
        }

        protected void paintBorder(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2.setColor(getForeground());
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
            g2.dispose();
        }
    }

    public test2(DefaultTableModel tableModel) {
        this.model = tableModel;

        setSize(500, 650);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setBounds(3, 3, 482, 608);
        panel.setBackground(Color.white);
        panel.setBorder(new LineBorder(Color.BLACK, 2));
        panel.setLayout(null);
        add(panel);

        JLabel titleLabel = new JLabel("ADD STUDENT");
        titleLabel.setBounds(180, 20, 300, 30);
        titleLabel.setFont(new Font("Popoit", Font.BOLD, 24));
        panel.add(titleLabel);

        String[] labels = { "Student Name:", "Student ID:", "Gender:", "Age:", "Year:", "Academy Year:", "Course 1:",
                "Course 2:", "Course 3:" };
        JTextField[] textFields = new JTextField[labels.length];

        int yPosition = 80;
        for (int i = 0; i < labels.length; i++) {
            JLabel label = new JLabel(labels[i]);
            label.setBounds(30, yPosition, 200, 30);
            panel.add(label);

            textFields[i] = new JTextField();
            textFields[i].setBounds(130, yPosition + 5, 250, 25);
            textFields[i].setBorder(new LineBorder(Color.black, 2));
            panel.add(textFields[i]);

            yPosition += 40;
        }

        RoundedButton backButton = new RoundedButton("Back", 20);
        backButton.setBounds(40, 550, 100, 40);
        backButton.setBackground(Color.lightGray);
        backButton.setForeground(Color.black);
        panel.add(backButton);
        backButton.addActionListener(e -> dispose());

        RoundedButton saveButton = new RoundedButton("Save", 20);
        saveButton.setBounds(300, 550, 100, 40);
        saveButton.setBackground(Color.BLUE);
        saveButton.setForeground(Color.white);
        panel.add(saveButton);

        saveButton.addActionListener(e -> {
            String studentName = textFields[0].getText();
            String studentID = textFields[1].getText();
            String gender = textFields[2].getText();
            int age, year;
            double course1, course2, course3, gpa;

            try {
                age = Integer.parseInt(textFields[3].getText().trim());
                if (age <= 0)
                    throw new NumberFormatException();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid positive number for Age.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                year = Integer.parseInt(textFields[4].getText().trim());
                if (year <= 0)
                    throw new NumberFormatException();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid positive number for Year.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                course1 = Double.parseDouble(textFields[6].getText().trim());
                course2 = Double.parseDouble(textFields[7].getText().trim());
                course3 = Double.parseDouble(textFields[8].getText().trim());
                if (course1 < 0 || course2 < 0 || course3 < 0)
                    throw new NumberFormatException();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid scores for all courses.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            gpa = (course1 + course2 + course3) / 3;

            model.addRow(new Object[] { studentName, studentID, gender, age, year, textFields[5].getText(),
                    String.format("%.2f", gpa) });

            JOptionPane.showMessageDialog(this, "Student added successfully with GPA: " + String.format("%.2f", gpa));
            dispose();
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DefaultTableModel model = new DefaultTableModel(
                    new String[] { "Student Name", "Student ID", "Gender", "Age", "Year", "Academy Year", "GPA" }, 0);
            new test2(model).setVisible(true);
        });
    }
}

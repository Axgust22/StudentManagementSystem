package com.example;

import javax.swing.*;

import com.example.Admin.AdminLogin;
import com.example.Student.StudentLogin;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainSystem extends JFrame {
    public MainSystem() {
        setSize(800, 800);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Student Management System");
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(0, 0, 800, 800);
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(null);
        add(mainPanel);

        JLabel logoLabel = new JLabel();
        logoLabel.setBounds(300, 30, 220, 220);

        ImageIcon icon = new ImageIcon("Image/ITC.jpg");
        Image image = icon.getImage();
        Image scaledImage = image.getScaledInstance(220, 220, Image.SCALE_SMOOTH);
        icon = new ImageIcon(scaledImage);

        logoLabel.setIcon(icon);
        mainPanel.add(logoLabel);

        JLabel titleLabel = new JLabel("Student Management System");
        titleLabel.setBounds(100, 260, 600, 50);
        titleLabel.setFont(new Font("Poppins", Font.PLAIN, 32));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(titleLabel);

        JButton loginButton = new RoundedButton("Admin", Color.WHITE, Color.BLACK, 20);
        loginButton.setBounds(300, 400, 200, 45);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new AdminLogin().setVisible(true);
            }
        });
        mainPanel.add(loginButton);

        JButton signUpButton = new RoundedButton("Student", Color.decode("#8FB6FF"), Color.BLACK, 20);
        signUpButton.setBounds(300, 480, 200, 45);
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new StudentLogin().setVisible(true);
            }
        });
        mainPanel.add(signUpButton);
    }

    // Custom RoundedButton class
    static class RoundedButton extends JButton {
        private final Color fillColor;
        private final Color borderColor;
        private final int radius;

        public RoundedButton(String text, Color fillColor, Color borderColor, int radius) {
            super(text);
            this.fillColor = fillColor;
            this.borderColor = borderColor;
            this.radius = radius;
            setContentAreaFilled(false);
            setBorderPainted(false);
            setFocusPainted(false);
            setOpaque(false);
            setFont(new Font("Poppins", Font.PLAIN, 16));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(fillColor);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
            g2.setColor(borderColor);
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
            g2.setColor(getForeground());
            FontMetrics fm = g2.getFontMetrics();
            int stringWidth = fm.stringWidth(getText());
            int stringHeight = fm.getAscent();
            g2.drawString(getText(), (getWidth() - stringWidth) / 2, (getHeight() + stringHeight) / 2 - 2);
            g2.dispose();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainSystem frame = new MainSystem();
            frame.setVisible(true);
        });
    }
}
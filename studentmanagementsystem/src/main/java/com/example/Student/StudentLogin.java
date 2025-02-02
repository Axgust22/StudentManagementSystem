package com.example.Student;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import com.example.MainSystem;

public class StudentLogin extends JFrame {
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

    public StudentLogin() {
        setSize(580, 640);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Student Management System");
        setLocationRelativeTo(null);

        // Define color constants
        final Color BUTTON_COLOR = Color.decode("#8FB6FF");
        final Color BUTTON_BORDER_COLOR = Color.BLACK;

        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(1, 1, 584, 611);
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(null);
        add(mainPanel);

        // Logo
        JLabel logoLabel = new JLabel();
        logoLabel.setBounds(180, 30, 220, 220);

        // Load and scale the image
        ImageIcon icon = new ImageIcon("Image/ITC.jpg");
        Image image = icon.getImage();
        Image scaledImage = image.getScaledInstance(220, 220, Image.SCALE_SMOOTH);
        icon = new ImageIcon(scaledImage);
        logoLabel.setIcon(icon);
        mainPanel.add(logoLabel);

        // Title
        JLabel titleLabel = new JLabel("Student");
        titleLabel.setBounds(185, 260, 200, 50);
        titleLabel.setFont(new Font("Poppins", Font.PLAIN, 32));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(titleLabel);

        // LOG IN button
        RoundedButton loginButton = new RoundedButton("Login", BUTTON_COLOR, BUTTON_BORDER_COLOR, 20);
        loginButton.setBounds(190, 330, 200, 45);
        loginButton.setForeground(Color.BLACK);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Login().setVisible(true);
            }
        });
        mainPanel.add(loginButton);

        // BACK button
        RoundedButton backButton = new RoundedButton("Back", Color.WHITE, BUTTON_BORDER_COLOR, 20);
        backButton.setBounds(190, 400, 200, 45);
        backButton.setForeground(Color.BLACK);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new MainSystem().setVisible(true);
            }
        });
        mainPanel.add(backButton);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new StudentLogin().setVisible(true);
        });
    }
}

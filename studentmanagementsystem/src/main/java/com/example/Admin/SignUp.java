package com.example.Admin;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import org.bson.Document;

import com.example.MainSystem;
import com.example.HandleSystem.Admin;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class SignUp extends JFrame {
    public SignUp() {
        // Frame settings
        setTitle("Student Management System");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set Look and Feel to ensure consistent rendering across platforms
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Main panel with vertical BoxLayout for centering
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20)); // Add padding around the edges

        // Title Label
        JLabel titleLabel = new JLabel("SIGN UP");
        titleLabel.setFont(new Font("Poppins", Font.BOLD, 32));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Add spacing

        // Form panel for inputs
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Spacing between components
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Name label and field
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("Poppins", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(nameLabel, gbc);

        RoundedTextField nameField = new RoundedTextField(20);
        nameField.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        gbc.gridy = 0;
        formPanel.add(nameField, gbc);

        // Email label and field
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Poppins", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(emailLabel, gbc);

        RoundedTextField emailField = new RoundedTextField(20);
        emailField.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        gbc.gridy = 1;
        formPanel.add(emailField, gbc);

        // Password label and field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Poppins", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(passwordLabel, gbc);

        RoundedPasswordField passwordField = new RoundedPasswordField(20);
        passwordField.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        gbc.gridy = 2;
        formPanel.add(passwordField, gbc);

        mainPanel.add(formPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Add spacing

        // Buttons panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0)); // Spacing between buttons

        // Back button
        JButton backButton = new RoundedButton("Back", Color.WHITE, Color.BLACK, 20);
        backButton.setPreferredSize(new Dimension(100, 40));
        backButton.addActionListener(e -> {
            dispose();
            new MainSystem().setVisible(true);
        });
        buttonPanel.add(backButton);

        // Enter button
        JButton enterButton = new RoundedButton("Sign Up", Color.decode("#8FB6FF"), Color.BLACK, 20);
        enterButton.setPreferredSize(new Dimension(100, 40));
        enterButton.addActionListener(e -> {
            String name = nameField.getText();
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());

            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!email.matches("^[a-zA-Z0-9._%+-]+@gmail\\.com$")) {
                JOptionPane.showMessageDialog(this, "Please enter a valid @gmail.com email address.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validate password
            if (password.length() < 8) {
                JOptionPane.showMessageDialog(this, "Password must be at least 8 characters long.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Check for at least one uppercase letter
            if (!password.matches(".*[A-Z].*")) {
                JOptionPane.showMessageDialog(this, "Password must contain at least one uppercase letter.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Check for at least one lowercase letter
            if (!password.matches(".*[a-z].*")) {
                JOptionPane.showMessageDialog(this, "Password must contain at least one lowercase letter.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Check for at least one digit
            if (!password.matches(".*\\d.*")) {
                JOptionPane.showMessageDialog(this, "Password must contain at least one number.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Check for at least one special character
            if (!password.matches(".*[!@#$%^&*(),.?\":{}|<>].*")) {
                JOptionPane.showMessageDialog(this, "Password must contain at least one special character.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            try (MongoClient mongoClient = MongoClients.create(
                    "mongodb+srv://Admin:admin12345@adminsystem.kedip.mongodb.net/?retryWrites=true&w=majority")) {
                MongoDatabase database = mongoClient.getDatabase("AdminSystem");
                MongoCollection<Document> collection = database.getCollection("SignUp");

                // Check if the username already exists
                Document query = new Document("name", name);
                if (collection.find(query).first() != null) {
                    JOptionPane.showMessageDialog(this, "Username already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Create the Admin object
                    Admin admin = new Admin(name, email, password);
                    // Insert into the database
                    Document newUser = new Document("name", admin.getName())
                            .append("email", admin.getEmail())
                            .append("password", admin.getPassword()); // Save plain-text password
                    collection.insertOne(newUser);
                    JOptionPane.showMessageDialog(this, "Sign-up successful!", "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    dispose();

                    new MainSystem().setVisible(true);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Database connection failed!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        buttonPanel.add(enterButton);

        mainPanel.add(buttonPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Add main panel to frame
        add(mainPanel);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Custom RoundedTextField
    static class RoundedTextField extends JTextField {
        private final int radius;

        public RoundedTextField(int radius) {
            super();
            this.radius = radius;
            setOpaque(false);
            setBorder(new EmptyBorder(5, 10, 5, 10));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.WHITE); // Background color
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
            g2.setColor(Color.GRAY); // Border color
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
            g2.dispose();
            super.paintComponent(g);
        }
    }

    // Custom RoundedPasswordField
    static class RoundedPasswordField extends JPasswordField {
        private final int radius;

        public RoundedPasswordField(int radius) {
            super();
            this.radius = radius;
            setOpaque(false);
            setBorder(new EmptyBorder(5, 10, 5, 10));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.WHITE);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
            g2.setColor(Color.GRAY);
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
            g2.dispose();
            super.paintComponent(g);
        }
    }

    // Custom RoundedButton
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
            setFont(new Font("poppins", Font.PLAIN, 16));
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
        new SignUp();
    }
}

package com.example.Admin;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import org.bson.Document;

import com.example.HandleSystem.Admin;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.awt.*;

public class CreateNewLogin extends JFrame {

    public CreateNewLogin() {
        // Frame settings
        setTitle("Student Management System");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set Look and Feel
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Main panel with vertical BoxLayout for centering
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Title Label
        JLabel titleLabel = new JLabel("CREATE NEW LOGIN");
        titleLabel.setFont(new Font("Poppins", Font.BOLD, 32));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Form panel for inputs
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // New name label and field
        JLabel newNameLabel = new JLabel("Name");
        newNameLabel.setFont(new Font("Poppins", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(newNameLabel, gbc);

        RoundedTextField newNameField = new RoundedTextField(20);
        newNameField.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        gbc.gridy = 0;
        formPanel.add(newNameField, gbc);

        // Email label and field
        JLabel emailLabel = new JLabel("Email");
        emailLabel.setFont(new Font("Poppins", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(emailLabel, gbc);

        RoundedTextField emailField = new RoundedTextField(20);
        emailField.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        gbc.gridy = 1;
        formPanel.add(emailField, gbc);

        // New Password label and field
        JLabel newPasswordLabel = new JLabel("New password");
        newPasswordLabel.setFont(new Font("Poppins", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(newPasswordLabel, gbc);

        RoundedPasswordField newPasswordField = new RoundedPasswordField(20);
        newPasswordField.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        gbc.gridy = 2;
        formPanel.add(newPasswordField, gbc);

        mainPanel.add(formPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Buttons panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));

        // Back button
        JButton backButton = new RoundedButton("Back", Color.WHITE, Color.BLACK, 20);
        backButton.setPreferredSize(new Dimension(100, 40));
        backButton.addActionListener(e -> {
            dispose();
            new LogIn().setVisible(true);
        });
        buttonPanel.add(backButton);

        // Enter button
        JButton enterButton = new RoundedButton("Enter", Color.decode("#8FB6FF"), Color.BLACK, 20);
        enterButton.setPreferredSize(new Dimension(100, 40));

        enterButton.addActionListener(e -> {
            String name = newNameField.getText();
            String email = emailField.getText();
            String password = new String(newPasswordField.getPassword());

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

            if (password.length() < 8) {
                JOptionPane.showMessageDialog(this, "Password must be at least 8 characters long.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!password.matches(".*[A-Z].*")) {
                JOptionPane.showMessageDialog(this, "Password must contain at least one uppercase letter.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!password.matches(".*[a-z].*")) {
                JOptionPane.showMessageDialog(this, "Password must contain at least one lowercase letter.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!password.matches(".*\\d.*")) {
                JOptionPane.showMessageDialog(this, "Password must contain at least one number.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

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

                Document query = new Document("email", email).append("name", name);
                Document user = collection.find(query).first();

                if (user != null) {
                    Admin admin = new Admin(user.getString("name"), user.getString("email"),
                            user.getString("password"));

                    if (admin.resetPassword(name, email, password)) {
                        Document update = new Document("$set", new Document("password", admin.getPassword()));
                        collection.updateOne(query, update);

                        JOptionPane.showMessageDialog(this, "Password reset successful!", "Success",
                                JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                        new LogIn().setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(this, "Failed to reset password. Invalid email or username!",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "User not found!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                System.err.println("Error occurred during password reset: " + ex.getMessage());
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
            g2.setColor(Color.WHITE);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
            g2.setColor(Color.GRAY);
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
            setFont(new Font("Arial", Font.PLAIN, 16));
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
        new CreateNewLogin();
    }
}

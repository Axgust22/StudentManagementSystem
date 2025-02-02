package com.example.Student;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import com.example.CourseManagement.CourseStudent;
import com.example.HandleSystem.Stu;
import com.mongodb.client.*;
import org.bson.Document;

public class Login extends JFrame {
    public Login() {
        // Frame settings
        setTitle("Student Management System");
        setSize(400, 400);
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
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Title Label
        JLabel titleLabel = new JLabel("LOG IN");
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

        // Username label and field
        JLabel usernameLabel = new JLabel("ID");
        usernameLabel.setFont(new Font("Poppins", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(usernameLabel, gbc);

        RoundedTextField usernameField = new RoundedTextField(20);
        usernameField.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        gbc.gridy = 0;
        formPanel.add(usernameField, gbc);

        // Password label and field
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Poppins", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(passwordLabel, gbc);

        RoundedPasswordField passwordField = new RoundedPasswordField(20);
        passwordField.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        gbc.gridy = 1;
        formPanel.add(passwordField, gbc);

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
            new StudentLogin().setVisible(true);
        });
        buttonPanel.add(backButton);

        // Enter button
        JButton enterButton = new RoundedButton("Enter", Color.decode("#8FB6FF"), Color.BLACK, 20);
        enterButton.setPreferredSize(new Dimension(100, 40));
        enterButton.addActionListener(e -> {
            String userID = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (userID.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try (MongoClient mongoClient = MongoClients.create(
                    "mongodb+srv://Admin:admin12345@adminsystem.kedip.mongodb.net/?retryWrites=true&w=majority")) {
                MongoDatabase database = mongoClient.getDatabase("AdminSystem");
                MongoCollection<Document> collection = database.getCollection("Student");

                Document query = new Document("Id", userID);
                Document user = collection.find(query).first();

                if (user != null) {
                    Stu student = new Stu(user.getString("Id"), user.getString("password"));
                    if (student.login(userID, password)) {
                        JOptionPane.showMessageDialog(this, "Login successful!", "Success",
                                JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                        new CourseStudent().setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(this, "Invalid password!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "User not found!", "Error", JOptionPane.ERROR_MESSAGE);
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

    // Custom JButton with rounded corners
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

    // Custom JTextField with rounded corners
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

    // Custom JPasswordField with rounded corners
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

    public static void main(String[] args) {
        new Login().setVisible(true);
    }
}

package com.example.Testing;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

import com.example.MainSystem;
import com.example.StudentManagement.StudentManagementSystem;

public class Testing extends JFrame {
    public Testing() {
        setTitle("Student Management System");
        setSize(2000, 1000);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel p1 = new JPanel();
        p1.setBounds(0, 0, 2000, 1000);
        p1.setBackground(Color.WHITE);
        p1.setLayout(null);
        add(p1);

        JLabel l1 = new JLabel("STUDENT MANAGEMENT SYSTEM");
        l1.setBounds(30, 20, 600, 40);
        l1.setFont(new Font("Arial", Font.BOLD, 36));
        p1.add(l1);

        Color buttonColor = Color.decode("#8FB6FF");

        JButton b1 = new JButton("Student Information");
        b1.setBounds(30, 80, 250, 40);
        b1.setFont(new Font("Arial", Font.PLAIN, 18));
        b1.setBackground(buttonColor);
        p1.add(b1);

        JButton b2 = new JButton("Student Course");
        b2.setBounds(300, 80, 250, 40);
        b2.setFont(new Font("Arial", Font.PLAIN, 18));
        b2.setBackground(buttonColor);
        p1.add(b2);

        JPanel p2 = new JPanel();
        p2.setBounds(10, 140, 1980, 750);
        p2.setBackground(Color.WHITE);
        p2.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        p2.setLayout(null);
        p1.add(p2);

        JLabel l3 = new JLabel("Search Student:");
        l3.setBounds(30, 20, 200, 30);
        l3.setFont(new Font("Arial", Font.PLAIN, 18));
        p2.add(l3);

        JTextField t1 = new JTextField();
        t1.setBounds(250, 20, 500, 30);
        t1.setFont(new Font("Arial", Font.PLAIN, 18));
        p2.add(t1);

        JButton b3 = new JButton("Search");
        b3.setBounds(770, 20, 150, 30);
        b3.setFont(new Font("Arial", Font.PLAIN, 18));
        b3.setBackground(buttonColor);
        p2.add(b3);

        JButton b4 = new JButton("Refresh");
        b4.setBounds(940, 20, 150, 30);
        b4.setFont(new Font("Arial", Font.PLAIN, 18));
        b4.setBackground(buttonColor);
        p2.add(b4);

        JPanel p3 = new JPanel();
        p3.setBounds(20, 70, 1940, 580);
        p3.setBackground(Color.LIGHT_GRAY);
        p3.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        p3.setLayout(new BorderLayout());
        p2.add(p3);

        String[] column = {
                "ID", "Name", "Age", "Sex", "Major", "Year",
                "Academic Year", "Email", "Phone Number", "Address"
        };
        DefaultTableModel model = new DefaultTableModel(null, column);
        JTable jt = new JTable(model);
        JScrollPane sp = new JScrollPane(jt);
        sp.setBounds(30, 80, 1880, 570);
        p3.add(sp);

        JButton b5 = new JButton("Add");
        b5.setBounds(30, 670, 150, 40);
        b5.setFont(new Font("Arial", Font.PLAIN, 18));
        b5.setBackground(buttonColor);
        p2.add(b5);

        JButton b6 = new JButton("Update");
        b6.setBounds(220, 670, 150, 40);
        b6.setFont(new Font("Arial", Font.PLAIN, 18));
        b6.setBackground(buttonColor);
        p2.add(b6);

        JButton b7 = new JButton("Delete");
        b7.setBounds(410, 670, 150, 40);
        b7.setFont(new Font("Arial", Font.PLAIN, 18));
        b7.setBackground(buttonColor);
        p2.add(b7);

        JButton b8 = new JButton("Clear Form");
        b8.setBounds(600, 670, 200, 40);
        b8.setFont(new Font("Arial", Font.PLAIN, 18));
        b8.setBackground(buttonColor);
        p2.add(b8);

        JButton b9 = new JButton("Log Out");
        b9.setBounds(820, 670, 150, 40);
        b9.setFont(new Font("Arial", Font.PLAIN, 18));
        b9.setBackground(buttonColor);
        p2.add(b9);

        // Event Listeners
        b5.addActionListener(e -> {
            model.addRow(new Object[] { "", "", "", "", "", "", "", "", "", "" });
        });

        b6.addActionListener(e -> {
            int selectedRow = jt.getSelectedRow();
            if (selectedRow != -1) {
                JOptionPane.showMessageDialog(this, "Row updated successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Please select a row to update!");
            }
        });

        b7.addActionListener(e -> {
            int selectedRow = jt.getSelectedRow();
            if (selectedRow != -1) {
                model.removeRow(selectedRow);
                JOptionPane.showMessageDialog(this, "Row deleted successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Please select a row to delete!");
            }
        });

        b8.addActionListener(e -> t1.setText(""));

        b9.addActionListener(e -> {
            dispose();
            JOptionPane.showMessageDialog(this, "Logged out successfully!");
            new MainSystem().setVisible(true);
        });

        b4.addActionListener(e -> {
            t1.setText("");
            model.setRowCount(0); // Clear all rows
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new StudentManagementSystem().setVisible(true);
        });
    }
}

// enterButton.addActionListener(e -> {
// String newName = newNameField.getText();
// String newEmail = emailField.getText();
// String newPassword = new String(newPasswordField.getPassword());

// // Validate input
// if (newName.isEmpty() || newEmail.isEmpty() || newPassword.isEmpty()) {
// JOptionPane.showMessageDialog(this, "Please fill in all fields!", "Error",
// JOptionPane.ERROR_MESSAGE);
// return;
// }

// try (MongoClient mongoClient = MongoClients.create(
// "mongodb+srv://Admin:admin12345@adminsystem.kedip.mongodb.net/?retryWrites=true&w=majority&appName=AdminSystem"))
// {
// MongoDatabase database = mongoClient.getDatabase("AdminSystem");
// MongoCollection<Document> collection = database.getCollection("CreateNew");

// // Check if the username already exists
// Document query = new Document("newName", newName);
// if (collection.find(query).first() != null) {
// JOptionPane.showMessageDialog(this, "Username already exists!", "Error",
// JOptionPane.ERROR_MESSAGE);
// } else {
// // Create and insert the new user document
// Document newUser = new Document("newName", newName)
// .append("newEmail", newEmail)
// .append("newPassword", newPassword);
// collection.insertOne(newUser);

// JOptionPane.showMessageDialog(this, "Create new login successful!",
// "Success",
// JOptionPane.INFORMATION_MESSAGE);
// dispose();
// new StudentManagementSystem().setVisible(true);
// }
// } catch (MongoException mongoEx) {
// mongoEx.printStackTrace();
// JOptionPane.showMessageDialog(this, "Database error: " +
// mongoEx.getMessage(), "Error",
// JOptionPane.ERROR_MESSAGE);
// }
// });

// enterButton.addActionListener(e -> {
// String username = usernameField.getText().toLowerCase(); // Lowercase for
// consistency
// String password = new String(passwordField.getPassword());

// if (username.isEmpty() || password.isEmpty()) {
// JOptionPane.showMessageDialog(this, "Please fill in all fields!", "Error",
// JOptionPane.ERROR_MESSAGE);
// return;
// }

// // MongoDB connection
// try (MongoClient mongoClient = MongoClients.create(
// "mongodb+srv://Admin:admin12345@adminsystem.kedip.mongodb.net/?retryWrites=true&w=majority&appName=AdminSystem"))
// {
// MongoDatabase database = mongoClient.getDatabase("AdminSystem");
// MongoCollection<Document> collection1 = database.getCollection("SignUp");
// MongoCollection<Document> collection2 = database.getCollection("CreateNew");

// // Correct query
// Document query = new Document("name", username).append("password", password);
// Document user1 = collection1.find(query).first();
// Document user2 = collection2.find(query).first();

// if (user1 != null || user2 != null) {
// JOptionPane.showMessageDialog(this, "Login successful!", "Success",
// JOptionPane.INFORMATION_MESSAGE);
// dispose();
// new StudentManagementSystem().setVisible(true);
// } else {
// JOptionPane.showMessageDialog(this, "Invalid username or password!", "Error",
// JOptionPane.ERROR_MESSAGE);
// }
// } catch (Exception ex) {
// ex.printStackTrace();
// JOptionPane.showMessageDialog(this, "Database connection failed!", "Error",
// JOptionPane.ERROR_MESSAGE);
// }
// });
package com.example;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class testing {
    public static void main(String[] args) {
        // Create the main JFrame (window)
        JFrame frame = new JFrame("MongoDB User Input");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(5, 2, 10, 10)); // Create a grid layout with 5 rows, 2 columns

        // Create UI components
        JLabel nameLabel = new JLabel("Enter your name:");
        JTextField nameField = new JTextField();

        JLabel ageLabel = new JLabel("Enter your age:");
        JTextField ageField = new JTextField();

        JLabel heightLabel = new JLabel("Enter your height (in meters):");
        JTextField heightField = new JTextField();

        JButton submitButton = new JButton("Submit");
        JButton clearButton = new JButton("Clear");

        // Add components to the frame
        frame.add(nameLabel);
        frame.add(nameField);

        frame.add(ageLabel);
        frame.add(ageField);

        frame.add(heightLabel);
        frame.add(heightField);

        frame.add(submitButton);
        frame.add(clearButton);

        // ActionListener for Submit Button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String ageText = ageField.getText();
                String heightText = heightField.getText();

                // Validate user input
                if (name.isEmpty() || ageText.isEmpty() || heightText.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "All fields must be filled!", "Input Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    int age = Integer.parseInt(ageText);
                    float height = Float.parseFloat(heightText);

                    // Connect to MongoDB and insert data
                    try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
                        MongoDatabase database = mongoClient.getDatabase("userDatabase");
                        MongoCollection<Document> collection = database.getCollection("userCollection");

                        // Create a document
                        Document userDocument = new Document("name", name)
                                .append("age", age)
                                .append("height", height);

                        // Insert the document
                        collection.insertOne(userDocument);

                        JOptionPane.showMessageDialog(frame, "Data successfully stored in MongoDB!");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(frame, "Error connecting to MongoDB: " + ex.getMessage(),
                                "Database Error", JOptionPane.ERROR_MESSAGE);
                    }

                    // Clear the input fields
                    nameField.setText("");
                    ageField.setText("");
                    heightField.setText("");

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Age and height must be numeric!", "Input Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // ActionListener for Clear Button
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Clear all fields
                nameField.setText("");
                ageField.setText("");
                heightField.setText("");
            }
        });

        // Make the frame visible
        frame.setVisible(true);
    }
}

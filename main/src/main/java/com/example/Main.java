package com.example;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create a JFrame (main window)
        JFrame frame = new JFrame("Student system");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Connect to MongoDB
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase database = mongoClient.getDatabase("Teacher");
        MongoCollection<Document> collection = database.getCollection("Student");
        // MongoCollection<Document> collection =
        // database.getCollection("userDatabase");

        // Fetch data from MongoDB
        List<Document> documents = collection.find().into(new ArrayList<>());

        // Define column names
        String[] columnNames = { "_id", "Name", "Age", "Study", "Hobbies/Favorite" };

        // Populate table data
        Object[][] tableData = new Object[documents.size()][5];
        for (int i = 0; i < documents.size(); i++) {
            Document doc = documents.get(i);
            tableData[i][0] = doc.getObjectId("_id").toString();
            tableData[i][1] = doc.getString("name");
            tableData[i][2] = doc.getInteger("age", 0); // Default to 0 if null
            tableData[i][3] = doc.getString("study");
            tableData[i][4] = doc.containsKey("hobbies") ? doc.getString("hobbies") : doc.getString("favorite");
        }

        // Create a JTable with the data
        JTable table = new JTable(new DefaultTableModel(tableData, columnNames));
        JScrollPane scrollPane = new JScrollPane(table); // Add a scroll pane for large data
        frame.add(scrollPane);

        // Show the frame
        frame.setVisible(true);

        // Close MongoDB connection when the app is closed
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                mongoClient.close();
            }
        });
    }
}
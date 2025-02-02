package com.example.Testing;
// package com.example;

// import java.awt.Color;
// import java.awt.Font;
// import javax.swing.*;
// import org.bson.Document;

// import com.example.StudentManagement.StudentManagementSystem;
// import com.mongodb.client.MongoClient;
// import com.mongodb.client.MongoClients;
// import com.mongodb.client.MongoCollection;
// import com.mongodb.client.MongoDatabase;
// import java.util.Arrays;

// class DeleteStudent extends JFrame {
// public DeleteStudent() {
// setSize(400, 400);
// setLayout(null);
// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
// setTitle("Student Information");

// JPanel p1 = new JPanel();
// p1.setBounds(0, 0, 400, 400);
// p1.setBackground(Color.white);
// p1.setLayout(null);
// add(p1);

// JLabel l1 = new JLabel("Delete Information Of Student");
// l1.setBounds(50, 20, 300, 30);
// l1.setFont(new Font("Poppins", Font.BOLD, 18));
// p1.add(l1);

// JLabel l2 = new JLabel("ID :");
// l2.setBounds(10, 100, 150, 30);
// l2.setFont(new Font("Poppins", Font.PLAIN, 14));
// p1.add(l2);

// JTextField t1 = new JTextField();
// t1.setBounds(120, 100, 200, 30);
// p1.add(t1);

// JLabel l3 = new JLabel("Name : ");
// l3.setBounds(10, 180, 150, 30);
// l3.setFont(new Font("Poppins", Font.PLAIN, 14));
// p1.add(l3);

// JTextField t2 = new JTextField();
// t2.setBounds(120, 180, 200, 30);
// p1.add(t2);

// JButton b1 = new JButton("Back");
// b1.setBounds(120, 250, 80, 30);
// b1.setFont(new Font("Poppins", Font.PLAIN, 14));
// p1.add(b1);
// b1.addActionListener(e -> dispose());

// JButton b2 = new JButton("Delete");
// b2.setBounds(250, 250, 80, 30);
// b2.setBackground(Color.decode("#8FB6FF"));
// p1.add(b2);

// b2.addActionListener(e -> {
// String id = t1.getText();
// String name = t2.getText();

// if (id.isEmpty() && name.isEmpty()) {
// JOptionPane.showMessageDialog(this, "Please provide at least one field (ID or
// Name) to delete!",
// "Warning", JOptionPane.WARNING_MESSAGE);
// return;
// }

// try (MongoClient mongoClient = MongoClients.create(
// "mongodb+srv://Admin:admin12345@adminsystem.kedip.mongodb.net/?retryWrites=true&w=majority&appName=AdminSystem"))
// {

// MongoDatabase database = mongoClient.getDatabase("AdminSystem");
// MongoCollection<Document> collection =
// database.getCollection("StudentManagement");

// // Build the query for deletion
// Document query = new Document();
// if (!id.isEmpty() && !name.isEmpty()) {
// query.append("$or", Arrays.asList(
// new Document("Id", id),
// new Document("Name", name)));
// } else if (!id.isEmpty()) {
// query.append("Id", id);
// } else if (!name.isEmpty()) {
// query.append("Name", name);
// }

// // Perform deletion
// long deletedCount = collection.deleteOne(query).getDeletedCount();

// if (deletedCount > 0) {
// JOptionPane.showMessageDialog(this, "Student record deleted successfully!",
// "Success",
// JOptionPane.INFORMATION_MESSAGE);
// StudentManagementSystem.reloadTableData();
// dispose();
// } else {
// JOptionPane.showMessageDialog(this, "No matching student record found!",
// "Info",
// JOptionPane.INFORMATION_MESSAGE);
// }

// } catch (Exception ex) {
// ex.printStackTrace();
// JOptionPane.showMessageDialog(this, "Database connection failed!", "Error",
// JOptionPane.ERROR_MESSAGE);
// }
// });
// }

// public static void main(String[] args) {
// new DeleteStudent().setVisible(true);
// }
// }

package com.example.Testing;

public class hi {

}

// public class CourseManagement extends JFrame {
// private DefaultTableModel model;
// private DefaultTableModel originalModel; // Store the original model

// class RoundedButton extends JButton {
// private int radius;

// public RoundedButton(String text, int radius) {
// super(text);
// this.radius = radius;
// setContentAreaFilled(false);
// setFocusPainted(false);
// }

// protected void paintComponent(Graphics g) {
// Graphics2D g2 = (Graphics2D) g.create();
// g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
// RenderingHints.VALUE_ANTIALIAS_ON);

// // Fill the button background
// g2.setColor(getBackground());
// g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);

// // Draw the button text
// super.paintComponent(g2);
// g2.dispose();
// }

// protected void paintBorder(Graphics g) {
// Graphics2D g2 = (Graphics2D) g.create();
// g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
// RenderingHints.VALUE_ANTIALIAS_ON);

// // Draw the rounded border
// g2.setColor(getForeground());
// g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
// g2.dispose();
// }
// }

// public CourseManagement() {
// setSize(1500, 800);
// setLayout(null);
// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

// JPanel p1 = new JPanel();
// p1.setBounds(0, 0, 1500, 800);
// p1.setBackground(Color.white);
// p1.setLayout(null);
// add(p1);

// JLabel l1 = new JLabel("COURSE MANAGEMENT");
// l1.setBounds(30, 20, 800, 40);
// l1.setFont(new Font("poppins", Font.BOLD, 36));
// p1.add(l1);

// JLabel lb2 = new JLabel() {
// @Override
// protected void paintComponent(Graphics g) {
// super.paintComponent(g);
// ImageIcon icon = new ImageIcon("Image/ITC.jpg");
// Image img = icon.getImage();

// // Create a circular clip
// Graphics2D g2d = (Graphics2D) g;
// g2d.setClip(new java.awt.geom.Ellipse2D.Double(0, 0, getWidth(),
// getHeight()));
// g2d.drawImage(img, 0, 0, getWidth(), getHeight(), this);
// }
// };
// lb2.setBounds(1350, 0, 120, 120);
// p1.add(lb2);

// Color buttonColor = Color.decode("#8FB6FF");

// RoundedButton b1 = new RoundedButton("Student Information", 20);
// b1.setBounds(30, 80, 250, 40);
// b1.setForeground(Color.black);
// b1.setFont(new Font("Arial", Font.PLAIN, 18));
// b1.setBackground(buttonColor);
// p1.add(b1);
// b1.addActionListener(e -> {
// dispose();
// new StudentManagementSystem().setVisible(true);
// });

// RoundedButton b2 = new RoundedButton("Student Course", 20);
// b2.setBounds(300, 80, 250, 40);
// b2.setForeground(Color.black);
// b2.setFont(new Font("Arial", Font.PLAIN, 18));
// b2.setBackground(buttonColor);
// p1.add(b2);

// RoundedButton bb3 = new RoundedButton("Student Score", 20);
// bb3.setBounds(570, 80, 250, 40);
// bb3.setForeground(Color.black);
// bb3.setFont(new Font("Arial", Font.PLAIN, 18));
// bb3.setBackground(buttonColor);
// p1.add(bb3);
// bb3.addActionListener(e -> {
// dispose();
// new ScoreManagement().setVisible(true);
// });

// JPanel p2 = new JPanel();
// p2.setBounds(30, 120, 1450, 600);
// p2.setBackground(Color.WHITE);
// p2.setBorder(new LineBorder(Color.BLACK, 2));
// p2.setLayout(null);
// p1.add(p2);

// JLabel l3 = new JLabel("Search Student Course:");
// l3.setBounds(30, 20, 200, 30);
// l3.setFont(new Font("Arial", Font.PLAIN, 18));
// p2.add(l3);

// JTextField t1 = new JTextField();
// t1.setBounds(250, 20, 500, 30);
// t1.setBorder(new LineBorder(Color.BLACK, 2));
// t1.setFont(new Font("Arial", Font.PLAIN, 18));
// p2.add(t1);

// RoundedButton b3 = new RoundedButton("Search", 20);
// b3.setBounds(770, 15, 150, 40);
// b3.setForeground(Color.black);
// b3.setFont(new Font("Arial", Font.PLAIN, 18));
// b3.setBackground(buttonColor);
// p2.add(b3);

// RoundedButton b4 = new RoundedButton("Back", 20);
// b4.setBounds(940, 15, 150, 40);
// b4.setForeground(Color.black);
// b4.setFont(new Font("Arial", Font.PLAIN, 18));
// b4.setBackground(Color.LIGHT_GRAY);
// p2.add(b4);

// JPanel p3 = new JPanel();
// p3.setBounds(20, 70, 1420, 450);
// p3.setBackground(Color.LIGHT_GRAY);
// p3.setBorder(new LineBorder(Color.BLACK, 2));
// p3.setLayout(new BorderLayout());
// p2.add(p3);

// String[] column = {
// "StudentID", "Year", "Semester 1", "Course 1", "Course 2", "Course 3",
// "Course 4",
// "Course 5", "Semester 2", "Course 6", "Course 7", "Course 8", "Course 9",
// "Course 10"
// };
// model = new DefaultTableModel(null, column);
// originalModel = model; // Save the original model
// JTable jt = new JTable(model);
// JScrollPane sp = new JScrollPane(jt);
// p3.add(sp);

// RoundedButton b5 = new RoundedButton("Add", 20);
// b5.setBounds(85, 530, 150, 40);
// b5.setForeground(Color.black);
// b5.setFont(new Font("Arial", Font.PLAIN, 18));
// b5.setBackground(buttonColor);
// p2.add(b5);

// RoundedButton b6 = new RoundedButton("Update", 20);
// b6.setBounds(360, 530, 150, 40);
// b6.setForeground(Color.black);
// b6.setFont(new Font("Arial", Font.PLAIN, 18));
// b6.setBackground(buttonColor);
// p2.add(b6);

// RoundedButton b7 = new RoundedButton("Delete", 20);
// b7.setBounds(660, 530, 150, 40);
// b7.setForeground(Color.black);
// b7.setFont(new Font("Arial", Font.PLAIN, 18));
// b7.setBackground(buttonColor);
// p2.add(b7);

// RoundedButton b8 = new RoundedButton("Clear Form", 20);
// b8.setBounds(950, 530, 150, 40);
// b8.setForeground(Color.black);
// b8.setFont(new Font("Arial", Font.PLAIN, 18));
// b8.setBackground(buttonColor);
// p2.add(b8);

// RoundedButton b10 = new RoundedButton("Log Out", 20);
// b10.setBounds(1220, 530, 150, 40);
// b10.setForeground(Color.black);
// b10.setFont(new Font("Arial", Font.PLAIN, 18));
// b10.setBackground(buttonColor);
// p2.add(b10);

// b3.addActionListener(e -> {
// String searchID = t1.getText().trim();
// if (searchID == null || searchID.isEmpty()) {
// JOptionPane.showMessageDialog(this, "Please enter a valid Student ID!");
// return;
// }

// try (MongoClient mongoClient = MongoClients.create(
// "mongodb+srv://Admin:admin12345@adminsystem.kedip.mongodb.net/?retryWrites=true&w=majority"))
// {
// MongoDatabase database = mongoClient.getDatabase("AdminSystem");
// MongoCollection<Document> collection =
// database.getCollection("CourseManagement");

// Document query = new Document("studentID", searchID);
// Document result = collection.find(query).first();

// if (result != null) {
// // Safely retrieve and convert values
// Course course = new Course(
// result.getString("studentID"),
// parseInteger(result.get("year")), // Safely parse
// parseInteger(result.get("semester1")), // Safely parse
// result.getString("course1"),
// result.getString("course2"),
// result.getString("course3"),
// result.getString("course4"),
// result.getString("course5"),
// parseInteger(result.get("semester2")), // Safely parse
// result.getString("course6"),
// result.getString("course7"),
// result.getString("course8"),
// result.getString("course9"),
// result.getString("course10"));

// // Update table with the search result
// DefaultTableModel model = (DefaultTableModel) jt.getModel();
// model.setRowCount(0);
// model.addRow(new Object[] {
// course.getStudentID(),
// course.getStudentYear(),
// course.getStudentSemester1(),
// course.getStudentCourse1(),
// course.getStudentCourse2(),
// course.getStudentCourse3(),
// course.getStudentCourse4(),
// course.getStudentCourse5(),
// course.getStudentSemester2(),
// course.getStudentCourse6(),
// course.getStudentCourse7(),
// course.getStudentCourse8(),
// course.getStudentCourse9(),
// course.getStudentCourse10()
// });

// JOptionPane.showMessageDialog(this, "Search completed!");
// } else {
// JOptionPane.showMessageDialog(this,
// "No course found for Student ID: " + searchID);
// }
// } catch (Exception ex) {
// ex.printStackTrace();
// JOptionPane.showMessageDialog(this, "Database connection failed: " +
// ex.getMessage(),
// "Error",
// JOptionPane.ERROR_MESSAGE);
// }
// });

// // Update the Back button to restore the original model
// b4.addActionListener(e -> {
// try {
// // Reload all courses into the table
// loadAllCourses(); // Reload the data from the database
// t1.setText(""); // Clear the search text field
// JOptionPane.showMessageDialog(this, "Table restored to original data!");
// } catch (Exception ex) {
// ex.printStackTrace();
// JOptionPane.showMessageDialog(this,
// "Failed to reload original data: " + ex.getMessage(),
// "Error",
// JOptionPane.ERROR_MESSAGE);
// }
// });

// b5.addActionListener(e -> new AddCourse(model).setVisible(true));//
// b5.addActionListener(e -> new
// // AddCourse2(model).setVisible(true));

// b6.addActionListener(e -> {
// int selectedRow = jt.getSelectedRow();
// if (selectedRow != -1) {
// try {
// // Retrieve data from JTable
// String studentID = model.getValueAt(selectedRow, 0) != null
// ? model.getValueAt(selectedRow, 0).toString()
// : "";
// String year = model.getValueAt(selectedRow, 1) != null
// ? model.getValueAt(selectedRow, 1).toString()
// : "";
// String semester1 = model.getValueAt(selectedRow, 2) != null
// ? model.getValueAt(selectedRow, 2).toString()
// : "";
// String course1 = model.getValueAt(selectedRow, 3) != null
// ? model.getValueAt(selectedRow, 3).toString()
// : "";
// String course2 = model.getValueAt(selectedRow, 4) != null
// ? model.getValueAt(selectedRow, 4).toString()
// : "";
// String course3 = model.getValueAt(selectedRow, 5) != null
// ? model.getValueAt(selectedRow, 5).toString()
// : "";
// String course4 = model.getValueAt(selectedRow, 6) != null
// ? model.getValueAt(selectedRow, 6).toString()
// : "";
// String course5 = model.getValueAt(selectedRow, 7) != null
// ? model.getValueAt(selectedRow, 7).toString()
// : "";
// String semester2 = model.getValueAt(selectedRow, 8) != null
// ? model.getValueAt(selectedRow, 8).toString()
// : "";
// String course6 = model.getValueAt(selectedRow, 9) != null
// ? model.getValueAt(selectedRow, 9).toString()
// : "";
// String course7 = model.getValueAt(selectedRow, 10) != null
// ? model.getValueAt(selectedRow, 10).toString()
// : "";
// String course8 = model.getValueAt(selectedRow, 11) != null
// ? model.getValueAt(selectedRow, 11).toString()
// : "";
// String course9 = model.getValueAt(selectedRow, 12) != null
// ? model.getValueAt(selectedRow, 12).toString()
// : "";
// String course10 = model.getValueAt(selectedRow, 13) != null
// ? model.getValueAt(selectedRow, 13).toString()
// : "";

// // Create JComboBoxes and JTextFields for editing
// JTextField stu = new JTextField(studentID, 20);
// JComboBox<String> y1 = new JComboBox<>(
// new String[] { "Select Year", "1", "2", "3", "4", "5" });
// y1.setSelectedItem(year);

// JComboBox<String> s1 = new JComboBox<>(new String[] { "1" });
// s1.setSelectedItem(semester1);

// JComboBox<String> c1 = new JComboBox<>(
// new String[] { "Select Course 1", "Management & Accounting",
// "Advanced Computer Architecture" });
// c1.setSelectedItem(course1);

// JComboBox<String> c2 = new JComboBox<>(
// new String[] { "Select Course 2", "Mathematics 1",
// "Artificial Intelligence" });
// c2.setSelectedItem(course2);

// JComboBox<String> c3 = new JComboBox<>(
// new String[] { "Select Course 3", "Electricity",
// "Algorithms And Programming" });
// c3.setSelectedItem(course3);

// JComboBox<String> c4 = new JComboBox<>(new String[] { "Select Course 4",
// "Object-Oriented Programming", "Human Computer Interaction" });
// c4.setSelectedItem(course4);

// JComboBox<String> c5 = new JComboBox<>(new String[] { "Select Course 5",
// "Information System Analysis and Design", "Operation System" });
// c5.setSelectedItem(course5);

// JComboBox<String> s2 = new JComboBox<>(new String[] { "2" });
// s2.setSelectedItem(semester2);

// JComboBox<String> c6 = new JComboBox<>(
// new String[] { "Database", "Network 2" });
// c6.setSelectedItem(course6);

// JComboBox<String> c7 = new JComboBox<>(
// new String[] { "Distributed System", "Automata Theory" });
// c7.setSelectedItem(course7);

// JComboBox<String> c8 = new JComboBox<>(
// new String[] { "Theoretical Computer Science",
// "Internet Programming 2" });
// c8.setSelectedItem(course8);

// JComboBox<String> c9 = new JComboBox<>(
// new String[] { "System And Network Administration", "MATLAB" });
// c9.setSelectedItem(course9);

// JComboBox<String> c10 = new JComboBox<>(
// new String[] { "Software Development And IT Operation",
// "Web Design" });
// c10.setSelectedItem(course10);

// // Create panel
// JPanel panel = new JPanel(new GridLayout(15, 2, 15, 15));
// panel.add(new JLabel("Student ID:"));
// panel.add(stu);
// panel.add(new JLabel("Year:"));
// panel.add(y1);
// panel.add(new JLabel("Semester 1:"));
// panel.add(s1);
// panel.add(new JLabel("Course 1:"));
// panel.add(c1);
// panel.add(new JLabel("Course 2:"));
// panel.add(c2);
// panel.add(new JLabel("Course 3:"));
// panel.add(c3);
// panel.add(new JLabel("Course 4:"));
// panel.add(c4);
// panel.add(new JLabel("Course 5:"));
// panel.add(c5);
// panel.add(new JLabel("Semester 2:"));
// panel.add(s2);
// panel.add(new JLabel("Course 6:"));
// panel.add(c6);
// panel.add(new JLabel("Course 7:"));
// panel.add(c7);
// panel.add(new JLabel("Course 8:"));
// panel.add(c8);
// panel.add(new JLabel("Course 9:"));
// panel.add(c9);
// panel.add(new JLabel("Course 10:"));
// panel.add(c10);

// // Show dialog
// int option = JOptionPane.showConfirmDialog(null, panel, "Update Semester 2",
// JOptionPane.OK_CANCEL_OPTION);

// if (option == JOptionPane.OK_OPTION) {
// // Use your MongoDB connection string
// String connectionString =
// "mongodb+srv://Admin:admin12345@adminsystem.kedip.mongodb.net/?retryWrites=true&w=majority";

// try (MongoClient mongoClient = MongoClients.create(connectionString)) {
// MongoDatabase database = mongoClient.getDatabase("AdminSystem");
// MongoCollection<Document> collection = database
// .getCollection("CourseManagement");

// // Update MongoDB
// Document query = new Document("studentID", studentID);
// Document update = new Document("$set", new Document()
// .append("year", y1.getSelectedItem())
// .append("semester1", s1.getSelectedItem())
// .append("course1", c1.getSelectedItem())
// .append("course2", c2.getSelectedItem())
// .append("course3", c3.getSelectedItem())
// .append("course4", c4.getSelectedItem())
// .append("course5", c5.getSelectedItem())
// .append("semester2", s2.getSelectedItem())
// .append("course6", c6.getSelectedItem())
// .append("course7", c7.getSelectedItem())
// .append("course8", c8.getSelectedItem())
// .append("course9", c9.getSelectedItem())
// .append("course10", c10.getSelectedItem()));

// collection.updateOne(query, update);
// JOptionPane.showMessageDialog(null,
// "Courses updated successfully!");

// // Update JTable
// model.setValueAt(y1.getSelectedItem(), selectedRow, 1);
// model.setValueAt(s1.getSelectedItem(), selectedRow, 2);
// model.setValueAt(c1.getSelectedItem(), selectedRow, 3);
// model.setValueAt(c2.getSelectedItem(), selectedRow, 4);
// model.setValueAt(c3.getSelectedItem(), selectedRow, 5);
// model.setValueAt(c4.getSelectedItem(), selectedRow, 6);
// model.setValueAt(c5.getSelectedItem(), selectedRow, 7);
// model.setValueAt(s2.getSelectedItem(), selectedRow, 8);
// model.setValueAt(c6.getSelectedItem(), selectedRow, 9);
// model.setValueAt(c7.getSelectedItem(), selectedRow, 10);
// model.setValueAt(c8.getSelectedItem(), selectedRow, 11);
// model.setValueAt(c9.getSelectedItem(), selectedRow, 12);
// model.setValueAt(c10.getSelectedItem(), selectedRow, 13);
// }
// }
// } catch (Exception ex) {
// ex.printStackTrace();
// JOptionPane.showMessageDialog(null,
// "Error updating courses: " + ex.getMessage(), "Error",
// JOptionPane.ERROR_MESSAGE);
// }
// } else {
// JOptionPane.showMessageDialog(null, "Please select a row to update!");
// }
// });
// b7.addActionListener(e -> {
// int selectedRow = jt.getSelectedRow(); // Get the selected row
// if (selectedRow != -1) {
// // Create a Course object for the selected row
// Course courseToDelete = new Course(
// (String) model.getValueAt(selectedRow, 0), // studentID
// Integer.parseInt(model.getValueAt(selectedRow, 1).toString()), // year
// Integer.parseInt(model.getValueAt(selectedRow, 2).toString()), // semester1
// (String) model.getValueAt(selectedRow, 3), // course1
// (String) model.getValueAt(selectedRow, 4), // course2
// (String) model.getValueAt(selectedRow, 5), // course3
// (String) model.getValueAt(selectedRow, 6), // course4
// (String) model.getValueAt(selectedRow, 7), // course5
// Integer.parseInt(model.getValueAt(selectedRow, 8).toString()), // semester2
// (String) model.getValueAt(selectedRow, 9), // course6
// (String) model.getValueAt(selectedRow, 10), // course7
// (String) model.getValueAt(selectedRow, 11), // course8
// (String) model.getValueAt(selectedRow, 12), // course9
// (String) model.getValueAt(selectedRow, 13) // course10
// );

// // Delete the record from MongoDB using the Course object
// try (MongoClient mongoClient = MongoClients.create(
// "mongodb+srv://Admin:admin12345@adminsystem.kedip.mongodb.net/?retryWrites=true&w=majority"))
// {
// MongoDatabase database = mongoClient.getDatabase("AdminSystem");
// MongoCollection<Document> collection = database
// .getCollection("CourseManagement");

// // Delete query based on student ID
// Document query = new Document("studentID", courseToDelete.getStudentID());
// collection.deleteOne(query); // Delete the document from the database

// // Remove the row from JTable
// model.removeRow(selectedRow);

// JOptionPane.showMessageDialog(this, "Record deleted successfully!");
// } catch (Exception ex) {
// ex.printStackTrace();
// JOptionPane.showMessageDialog(this,
// "Failed to delete record: " + ex.getMessage(), "Error",
// JOptionPane.ERROR_MESSAGE);
// }
// } else {
// JOptionPane.showMessageDialog(this, "Please select a row to delete!");
// }
// });

// b8.addActionListener(e -> {
// // Clear the search text field
// t1.setText("");

// // Clear all rows from the JTable
// model.setRowCount(0);

// // Clear all records from MongoDB (optional)
// try (MongoClient mongoClient = MongoClients.create(
// "mongodb+srv://Admin:admin12345@adminsystem.kedip.mongodb.net/?retryWrites=true&w=majority"))
// {
// MongoDatabase database = mongoClient.getDatabase("AdminSystem");
// MongoCollection<Document> collection =
// database.getCollection("CourseManagement");

// // Confirm before clearing all records
// int confirm = JOptionPane.showConfirmDialog(
// this,
// "Are you sure you want to clear all records from the database?",
// "Confirm Clear",
// JOptionPane.YES_NO_OPTION);

// if (confirm == JOptionPane.YES_OPTION) {
// // Delete all documents from the collection
// collection.deleteMany(new Document());
// JOptionPane.showMessageDialog(this, "All records cleared successfully!");
// }
// } catch (Exception ex) {
// ex.printStackTrace();
// JOptionPane.showMessageDialog(this, "Failed to clear records: " +
// ex.getMessage(),
// "Error",
// JOptionPane.ERROR_MESSAGE);
// }
// });

// b10.addActionListener(e -> {
// dispose();
// new MainSystem().setVisible(true);
// });
// loadAllCourses();
// }

// private List<Object[]> originalData = new ArrayList<>();

// // Utility method to safely parse an integer from an object
// private int parseInteger(Object value) {
// if (value == null) {
// return 0; // Return default value for null
// }
// try {
// if (value instanceof Integer) {
// return (Integer) value; // Return directly if it's already an Integer
// }
// if (value instanceof String) {
// return Integer.parseInt((String) value); // Convert from String to Integer
// }
// } catch (NumberFormatException ex) {
// System.err.println("Failed to parse integer from value: " + value);
// }
// return 0; // Return default value if parsing fails
// }

// private void loadAllCourses() {
// model.setRowCount(0); // Clear existing rows in the table
// originalData.clear(); // Clear previously stored original data

// try (MongoClient mongoClient = MongoClients.create(
// "mongodb+srv://Admin:admin12345@adminsystem.kedip.mongodb.net/?retryWrites=true&w=majority"))
// {
// // Connect to the database and collection
// MongoDatabase database = mongoClient.getDatabase("AdminSystem");
// MongoCollection<Document> collection =
// database.getCollection("CourseManagement");

// // Fetch all documents from the collection
// try (MongoCursor<Document> cursor = collection.find().iterator()) {
// while (cursor.hasNext()) {
// Document doc = cursor.next();

// // Safely convert fields to integers or strings
// Object[] rowData = {
// doc.getString("studentID"), // Always a string
// parseInteger(doc.get("year")), // Convert safely
// parseInteger(doc.get("semester1")), // Convert safely
// doc.getString("course1"),
// doc.getString("course2"),
// doc.getString("course3"),
// doc.getString("course4"),
// doc.getString("course5"),
// parseInteger(doc.get("semester2")), // Convert safely
// doc.getString("course6"),
// doc.getString("course7"),
// doc.getString("course8"),
// doc.getString("course9"),
// doc.getString("course10")
// };

// // Add the row data to the table model
// model.addRow(rowData);

// // Save original data for potential reset
// originalData.add(rowData);
// }
// }
// } catch (Exception ex) {
// // Print stack trace and show error dialog
// ex.printStackTrace();
// JOptionPane.showMessageDialog(this, "Failed to load course data: " +
// ex.getMessage(),
// "Error", JOptionPane.ERROR_MESSAGE);
// }
// }

// public static void main(String[] args) {
// new CourseManagement().setVisible(true);
// }
// }

// package com.example.ScoreManagement;

// import java.awt.Color;
// import java.awt.Font;
// import java.awt.Graphics;
// import java.awt.Graphics2D;
// import java.awt.RenderingHints;

// import javax.swing.*;
// import javax.swing.border.LineBorder;
// import javax.swing.table.DefaultTableModel;

// public class AddScore extends JFrame {
// public DefaultTableModel model;

// class RoundedButton extends JButton {
// private int radius;

// public RoundedButton(String text, int radius) {
// super(text);
// this.radius = radius;
// setContentAreaFilled(false);
// setFocusPainted(false);
// }

// protected void paintComponent(Graphics g) {
// Graphics2D g2 = (Graphics2D) g.create();
// g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
// RenderingHints.VALUE_ANTIALIAS_ON);

// g2.setColor(getBackground());
// g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);

// super.paintComponent(g2);
// g2.dispose();
// }

// protected void paintBorder(Graphics g) {
// Graphics2D g2 = (Graphics2D) g.create();
// g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
// RenderingHints.VALUE_ANTIALIAS_ON);

// g2.setColor(getForeground());
// g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
// g2.dispose();
// }
// }

// public AddScore(DefaultTableModel tableModel) {
// this.model = tableModel;

// setSize(570, 700);
// setLayout(null);
// setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

// JPanel panel = new JPanel();
// panel.setBounds(10, 10, 550, 650);
// panel.setBackground(Color.white);
// panel.setBorder(new LineBorder(Color.BLACK, 2));
// panel.setLayout(null);
// add(panel);

// JLabel titleLabel = new JLabel("ADD SCORE OF STUDENT");
// titleLabel.setBounds(130, 20, 400, 30);
// titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
// panel.add(titleLabel);

// String[] labels = {
// "Student Name", "Student ID", "Course 1", "Score 1",
// "Course 2", "Score 2", "Course 3", "Score 3",
// "Course 4", "Score 4", "Course 5", "Score 5"
// };
// JTextField[] textFields = new JTextField[labels.length];

// int yPosition = 80;
// for (int i = 0; i < labels.length; i++) {
// JLabel label = new JLabel(labels[i] + ":");
// label.setBounds(30, yPosition, 100, 30);
// panel.add(label);

// textFields[i] = new JTextField();
// textFields[i].setBounds(150, yPosition + 5, 250, 25);
// textFields[i].setBorder(new LineBorder(Color.BLACK, 2));
// panel.add(textFields[i]);

// yPosition += 40;
// }

// RoundedButton backButton = new RoundedButton("Back", 20);
// backButton.setBounds(40, 580, 100, 40);
// backButton.setBackground(Color.lightGray);
// backButton.setForeground(Color.black);
// panel.add(backButton);
// backButton.addActionListener(e -> dispose());

// RoundedButton saveButton = new RoundedButton("Save", 20);
// saveButton.setBounds(400, 580, 100, 40);
// saveButton.setBackground(Color.BLUE);
// saveButton.setForeground(Color.white);
// panel.add(saveButton);

// saveButton.addActionListener(e -> {
// try {
// // Validate required fields
// for (int i = 0; i < labels.length; i++) {
// if (textFields[i].getText().trim().isEmpty()) {
// JOptionPane.showMessageDialog(this, "All fields are required!", "Error",
// JOptionPane.ERROR_MESSAGE);
// return;
// }
// }

// for (int i = 2; i < labels.length; i += 2) { // Course fields at 2, 4, 6, 8,
// 10
// String course = textFields[i].getText().trim();
// if (!course.matches("[A-Za-z ]+")) {
// JOptionPane.showMessageDialog(this, "Invalid course name: " + course
// + ". Only letters and spaces are allowed.", "Error",
// JOptionPane.ERROR_MESSAGE);
// return;
// }
// }

// String studentName = textFields[0].getText().trim();
// String studentID = textFields[1].getText().trim();
// String course1 = textFields[2].getText().trim();
// double score1 = Double.parseDouble(textFields[3].getText().trim());
// String course2 = textFields[4].getText().trim();
// double score2 = Double.parseDouble(textFields[5].getText().trim());
// String course3 = textFields[6].getText().trim();
// double score3 = Double.parseDouble(textFields[7].getText().trim());
// String course4 = textFields[8].getText().trim();
// double score4 = Double.parseDouble(textFields[9].getText().trim());
// String course5 = textFields[10].getText().trim();
// double score5 = Double.parseDouble(textFields[11].getText().trim());

// // Calculate Total Point and GPA
// double totalPoint = score1 + score2 + score3 + score4 + score5;
// double gpa = totalPoint / 5;

// // Add new row to the table model
// model.addRow(new Object[] {
// studentName, studentID,
// course1, score1,
// course2, score2,
// course3, score3,
// course4, score4,
// course5, score5,
// String.format("%.2f", totalPoint),
// String.format("%.2f", gpa)
// });

// JOptionPane.showMessageDialog(this, "Student added successfully!");
// dispose(); // Close the window
// } catch (NumberFormatException ex) {
// JOptionPane.showMessageDialog(this, "Please enter valid numeric scores!",
// "Error",
// JOptionPane.ERROR_MESSAGE);
// }

// });
// }

// public static void main(String[] args) {
// SwingUtilities.invokeLater(() -> {
// DefaultTableModel model = new DefaultTableModel(
// new String[] {
// "Student Name", "Student ID", "Course 1", "Score 1",
// "Course 2", "Score 2", "Course 3", "Score 3",
// "Course 4", "Score 4", "Course 5", "Score 5",
// "Total Point", "GPA"
// }, 0);
// new AddScore(model).setVisible(true);
// });
// }
// }
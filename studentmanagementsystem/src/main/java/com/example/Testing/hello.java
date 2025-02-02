package com.example.Testing;

public class hello {
    public static void main(String[] args) {
        // RoundedButton b6 = new RoundedButton("New", 20);
        // b6.setBounds(5, 45, 85, 40);
        // b6.setForeground(Color.black);
        // b6.setBackground(buttonColor);
        // p6.add(b6);

        // b6.addActionListener(new ActionListener() {
        // public void actionPerformed(ActionEvent e) {
        // RoundedButton newButton = new RoundedButton("New Button " +
        // (newButtons.size() + 1),
        // 20);

        // // Set button width to match the parent panel's width
        // int parentWidth = scrollPane.getViewport().getWidth();
        // newButton.setPreferredSize(new Dimension(parentWidth - 20, 40)); // Full
        // width with some
        // // margin
        // newButton.setMaximumSize(new Dimension(parentWidth - 20, 40)); // Prevent
        // expansion
        // newButton.setForeground(Color.BLACK);
        // newButton.setBackground(Color.LIGHT_GRAY);

        // // Add an ActionListener to the new button to update the selectedButton
        // newButton.addActionListener(new ActionListener() {
        // public void actionPerformed(ActionEvent e) {
        // selectedButton = newButton; // Update the selected button
        // lb1.setText("Course: " + newButton.getText()); // Update the label with
        // // the button name
        // }

        // });
        // // Add the button to the list and panel
        // newButtons.add(newButton);
        // p3.add(newButton);

        // // Revalidate and repaint to update the layout
        // p3.revalidate();
        // p3.repaint();
        // }
        // });

        // RoundedButton b7 = new RoundedButton("Delete", 20);
        // b7.setBounds(108, 45, 85, 40);
        // b7.setForeground(Color.black);
        // b7.setBackground(buttonColor);
        // p6.add(b7);

        // b7.addActionListener(new ActionListener() {
        // public void actionPerformed(ActionEvent e) {
        // if (selectedButton != null) {
        // newButtons.remove(selectedButton); // Remove from list
        // p3.remove(selectedButton); // Remove from panel
        // selectedButton = null; // Reset the selected button
        // p3.revalidate();
        // p3.repaint();
        // JOptionPane.showMessageDialog(null, "Button deleted successfully!");
        // } else {
        // JOptionPane.showMessageDialog(null, "Please select a button to delete
        // first!",
        // "Error",
        // JOptionPane.ERROR_MESSAGE);
        // }
        // }
        // });

        // RoundedButton bb7 = new RoundedButton("Update", 20);
        // bb7.setBounds(210, 45, 85, 40);
        // bb7.setForeground(Color.black);
        // bb7.setBackground(buttonColor);
        // p6.add(bb7);

        // bb7.addActionListener(new ActionListener() {
        // public void actionPerformed(ActionEvent e) {
        // if (selectedButton != null) {
        // String newName = JOptionPane.showInputDialog(null,
        // "Enter new name for the button:",
        // selectedButton.getText());
        // if (newName != null && !newName.trim().isEmpty()) {
        // selectedButton.setText(newName); // Update the button text
        // lb1.setText("Course: " + newName); // Update the label with the new
        // // button name
        // p3.revalidate();
        // p3.repaint();
        // JOptionPane.showMessageDialog(null, "Button updated successfully!");

        // } else {
        // JOptionPane.showMessageDialog(null, "Name cannot be empty!", "Error",
        // JOptionPane.ERROR_MESSAGE);
        // }
        // } else {
        // JOptionPane.showMessageDialog(null, "Please select a button to update
        // first!",
        // "Error",
        // JOptionPane.ERROR_MESSAGE);
        // }
        // }
        // });

        // JPanel p3 = new JPanel();
        // p3.setLayout(new BoxLayout(p3, BoxLayout.Y_AXIS)); // Use BoxLayout for
        // vertical arrangement
        // p3.setBackground(Color.white);
        // p3.setBorder(new LineBorder(Color.black, 2));

        // // Wrap p3 in a JScrollPane
        // JScrollPane scrollPane = new JScrollPane(p3,
        // JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
        // JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        // scrollPane.setBounds(5, 100, 300, 525);
        // p2.add(scrollPane);

        // Add ActionListener for "New" button
        // b6.addActionListener(new ActionListener() {
        // public void actionPerformed(ActionEvent e) {
        // String courseNames = JOptionPane.showInputDialog(null,
        // "Enter course name: ");
        // if (courseNames != null && !courseNames.trim().isEmpty()) {
        // try (MongoClient mongoClient = MongoClients.create(
        // "mongodb+srv://Admin:admin12345@adminsystem.kedip.mongodb.net/?retryWrites=true&w=majority"))
        // {
        // MongoDatabase database = mongoClient.getDatabase("AdminSystem");
        // MongoCollection<Document> collection = database
        // .getCollection("CourseManagement");

        // String[] courseArray = courseNames.split(",");
        // for (String courseName : courseArray) {
        // Document course = new Document("courseName", courseName.trim())
        // .append("students", new ArrayList<>());
        // collection.insertOne(course);

        // // Create a button for each course and display in the UI
        // RoundedButton courseButton = new RoundedButton(
        // courseName.trim(), 20);
        // courseButton.setPreferredSize(new Dimension(250, 40)); // Fixed
        // // button
        // // size
        // courseButton.setMaximumSize(new Dimension(250, 40));
        // courseButton.setForeground(Color.BLACK);
        // courseButton.setBackground(buttonColor);
        // newButtons.add(courseButton);
        // p3.add(courseButton);
        // p3.revalidate();
        // p3.repaint();

        // // Add ActionListener to the new button
        // courseButton.addActionListener(e2 -> {
        // selectedButton = courseButton; // Update the
        // // selectedButton
        // // reference
        // lb1.setText("Course: " + courseButton.getText()); // Update
        // // the
        // // label
        // // to
        // // show
        // // the
        // // selected
        // // course
        // p4.revalidate(); // Refresh UI
        // p4.repaint();
        // });

        // // Automatically select the newly added course
        // selectedButton = courseButton;
        // lb1.setText("Course: " + courseButton.getText());
        // }

        // JOptionPane.showMessageDialog(null, "Courses added successfully!");
        // } catch (Exception ex) {
        // JOptionPane.showMessageDialog(null,
        // "Failed to save to database: " + ex.getMessage(),
        // "Error",
        // JOptionPane.ERROR_MESSAGE);
        // }
        // } else {
        // JOptionPane.showMessageDialog(null, "Course names cannot be empty!", "Error",
        // JOptionPane.ERROR_MESSAGE);
        // }
        // }
        // });

        // LoadCourse from database when a user click on Student course it just load the
        // course of a user that had add in mongodb
        // private void loadCoursesFromDatabase() {
        // try (MongoClient mongoClient = MongoClients.create(
        // "mongodb+srv://Admin:admin12345@adminsystem.kedip.mongodb.net/?retryWrites=true&w=majority"))
        // {
        // MongoDatabase database = mongoClient.getDatabase("AdminSystem");
        // MongoCollection<Document> collection =
        // database.getCollection("CourseManagement");

        // p3.removeAll(); // Clear existing buttons from the UI
        // courseButtonMap.clear(); // Clear the map

        // Color buttonColor = Color.decode("#8FB6FF");

        // for (Document doc : collection.find()) {
        // String courseName = doc.getString("courseName");

        // RoundedButton courseButton = new RoundedButton(courseName, 20);
        // courseButton.setPreferredSize(new Dimension(250, 40));
        // courseButton.setMaximumSize(new Dimension(250, 40));
        // courseButton.setForeground(Color.BLACK);
        // courseButton.setBackground(buttonColor);

        // courseButtonMap.put(courseName, courseButton);
        // p3.add(courseButton);
        // p3.revalidate();
        // p3.repaint();

        // courseButton.addActionListener(e -> {
        // selectedButton = courseButton;
        // lb1.setText("Course: " + courseButton.getText());
        // clearAndReloadTable(courseButton.getText()); // Reload table for selected
        // course
        // });

        // }
        // } catch (Exception ex) {
        // JOptionPane.showMessageDialog(null, "Failed to load courses: " +
        // ex.getMessage(), "Error",
        // JOptionPane.ERROR_MESSAGE);
        // }
        // }

        // loadStudentForCourse if a user click on each course.
        // private void loadStudentsForCourse(String courseName) {
        // try (MongoClient mongoClient = MongoClients.create(
        // "mongodb+srv://Admin:admin12345@adminsystem.kedip.mongodb.net/?retryWrites=true&w=majority"))
        // {
        // MongoDatabase database = mongoClient.getDatabase("AdminSystem");
        // MongoCollection<Document> collection =
        // database.getCollection("CourseManagement");

        // // Clear the table
        // model.setRowCount(0);

        // // Find the course in the database
        // Document course = collection.find(new Document("courseName",
        // courseName)).first();
        // if (course != null) {
        // ArrayList<Document> students = (ArrayList<Document>) course.get("students");
        // if (students != null && !students.isEmpty()) {
        // // Add students to the table
        // for (Document student : students) {
        // model.addRow(new Object[] {
        // student.getString("StudentName"),
        // student.getString("StudentID"),
        // student.getString("Gender"),
        // student.getInteger("Age"),
        // student.getInteger("Year"),
        // student.getString("AcademyYear"),
        // student.getString("Attendance")
        // });
        // }
        // } else {
        // // Course exists but has no students
        // JOptionPane.showMessageDialog(this,
        // "No students available for the selected course.", "Info",
        // JOptionPane.INFORMATION_MESSAGE);
        // }
        // } else {
        // // Course does not exist (shouldn't happen if the course was just added)
        // JOptionPane.showMessageDialog(this, "Course not found in the database.",
        // "Error",
        // JOptionPane.ERROR_MESSAGE);
        // }
        // } catch (Exception ex) {
        // JOptionPane.showMessageDialog(this, "Failed to load students: " +
        // ex.getMessage(), "Error",
        // JOptionPane.ERROR_MESSAGE);
        // }
        // }

        // private void clearAndReloadTable(String courseName) {
        // model.setRowCount(0); // Clear the table
        // loadStudentsForCourse(courseName); // Load students for the selected course
        // }

        // b4.addActionListener(e -> {
        // String searchID = t1.getText().trim();
        // if (!searchID.isEmpty()) {
        // DefaultTableModel tempModel = new DefaultTableModel(null, column);
        // boolean found = false;
        // for (int i = 0; i < model.getRowCount(); i++) {
        // if (model.getValueAt(i, 1).toString().equalsIgnoreCase(searchID)) {
        // tempModel.addRow(new Object[] {
        // model.getValueAt(i, 0),
        // model.getValueAt(i, 1),
        // model.getValueAt(i, 2),
        // model.getValueAt(i, 3),
        // model.getValueAt(i, 4),
        // model.getValueAt(i, 5),
        // model.getValueAt(i, 6),
        // model.getValueAt(i, 7),

        // });
        // found = true;
        // }
        // }

        // if (!found) {
        // JOptionPane.showMessageDialog(this, "StudentID not found!");
        // } else {
        // jt.setModel(tempModel);
        // }
        // } else {
        // jt.setModel(model); // Restore original model when search field is empty
        // JOptionPane.showMessageDialog(this, "Please enter Student ID to search!");
        // }
        // });

        // b2.addActionListener(e -> {
        // try {
        // // Validate fields and save student (existing logic)
        // String studentName = t1.getText();
        // String studentID = t2.getText();
        // String gender = t3.getText();
        // int age = Integer.parseInt(t4.getText().trim());
        // int year = Integer.parseInt(t5.getText().trim());
        // String academyYear = t6.getText();
        // String attendance = t7.getText();

        // if (studentName.isEmpty() || studentID.isEmpty() || gender.isEmpty()
        // || academyYear.isEmpty() || attendance.isEmpty()) {
        // JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error",
        // JOptionPane.ERROR_MESSAGE);
        // return;
        // }

        // if (age <= 0 || year <= 0) {
        // JOptionPane.showMessageDialog(this, "Age and Year must be positive numbers.",
        // "Error",
        // JOptionPane.ERROR_MESSAGE);
        // return;
        // }

        // try (MongoClient mongoClient = MongoClients.create(
        // "mongodb+srv://Admin:admin12345@adminsystem.kedip.mongodb.net/?retryWrites=true&w=majority"))
        // {
        // MongoDatabase database = mongoClient.getDatabase("AdminSystem");
        // MongoCollection<Document> collection = database
        // .getCollection("CourseManagement");

        // // Add the student to the selected course
        // Document studentDoc = new Document("StudentID", studentID)
        // .append("StudentName", studentName)
        // .append("Gender", gender)
        // .append("Age", age)
        // .append("Year", year)
        // .append("AcademyYear", academyYear)
        // .append("Attendance", attendance);

        // Document query = new Document("courseName", selectedCourseName);
        // Document update = new Document("$push", new Document("students",
        // studentDoc));

        // collection.updateOne(query, update);

        // JOptionPane.showMessageDialog(this,
        // "Student added successfully to " + selectedCourseName + "!");
        // dispose();
        // } catch (Exception ex) {
        // JOptionPane.showMessageDialog(this,
        // "Failed to save to database: " + ex.getMessage(), "Error",
        // JOptionPane.ERROR_MESSAGE);
        // }

        // // Clear fields after successful save
        // t1.setText("");
        // t2.setText("");
        // t3.setText("");
        // t4.setText("");
        // t5.setText("");
        // t6.setText("");
        // t7.setText("");

        // } catch (NumberFormatException ex) {
        // JOptionPane.showMessageDialog(this, "Please enter valid numbers for Age and
        // Year.",
        // "Error",
        // JOptionPane.ERROR_MESSAGE);
        // }
        // });

        // b2.addActionListener(e -> {
        // try {
        // // Collect input data
        // String studentName = t1.getText();
        // String studentID = t2.getText();
        // String gender = t3.getText();
        // int age = Integer.parseInt(t4.getText().trim());
        // int year = Integer.parseInt(t5.getText().trim());
        // String academyYear = t6.getText();
        // String attendance = t7.getText();

        // if (studentID.isEmpty() || studentName.isEmpty() || gender.isEmpty()
        // || academyYear.isEmpty() || attendance.isEmpty()) {
        // JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error",
        // JOptionPane.ERROR_MESSAGE);
        // }

        // // Validate data
        // if (age <= 0 || year <= 0) {
        // JOptionPane.showMessageDialog(null, "Age and Year must be positive numbers.",
        // "Error",
        // JOptionPane.ERROR_MESSAGE);
        // return;
        // }

        // // Create StuCourse object
        // StuCourse student = new StuCourse(studentID, studentName, gender, age, year,
        // academyYear, attendance);

        // // Add to table model
        // model.addRow(new Object[] { studentName, studentID, gender, age, year,
        // academyYear,
        // attendance });

        // // Save to MongoDB
        // try (MongoClient mongoClient = MongoClients.create(
        // "mongodb+srv://Admin:admin12345@adminsystem.kedip.mongodb.net/?retryWrites=true&w=majority"))
        // {
        // MongoDatabase database = mongoClient.getDatabase("AdminSystem");
        // MongoCollection<Document> collection = database
        // .getCollection("CourseManagement");

        // collection.createIndex(Indexes.ascending("ID"),
        // new IndexOptions().unique(true));

        // // Convert StuCourse to MongoDB Document
        // Document document = new Document("ID", student.getStudentID())
        // .append("Name", student.getStudentName())
        // .append("Gender", student.getStudentGender())
        // .append("Age", student.getStudentAge())
        // .append("Year", student.getStudentYear())
        // .append("AcademyYear", student.getStudentAcademy())
        // .append("Attendance", student.getStudentAttendance());

        // // Insert document into collection
        // collection.insertOne(document);

        // JOptionPane.showMessageDialog(this, "Student added successfully!");
        // } catch (Exception ex) {
        // JOptionPane.showMessageDialog(null,
        // "Failed to save to database: " + ex.getMessage(), "Error",
        // JOptionPane.ERROR_MESSAGE);
        // }

        // // Clear input fields
        // t1.setText("");
        // t2.setText("");
        // t3.setText("");
        // t4.setText("");
        // t5.setText("");
        // t6.setText("");
        // t7.setText("");

        // } catch (NumberFormatException ex) {
        // JOptionPane.showMessageDialog(null, "Please enter valid numbers for Age and
        // Year.",
        // "Error",
        // JOptionPane.ERROR_MESSAGE);
        // }
        // });
    }
}

// b6.addActionListener(new ActionListener() {
// public void actionPerformed(ActionEvent e) {
// String courseNames = JOptionPane.showInputDialog(null,
// "Enter course names separated by commas:");
// if (courseNames != null && !courseNames.trim().isEmpty()) {
// try (MongoClient mongoClient = MongoClients.create(
// "mongodb+srv://Admin:admin12345@adminsystem.kedip.mongodb.net/?retryWrites=true&w=majority"))
// {
// MongoDatabase database = mongoClient.getDatabase("AdminSystem");
// MongoCollection<Document> collection = database
// .getCollection("CourseManagement");

// String[] courseArray = courseNames.split(",");
// for (String courseName : courseArray) {
// String trimmedCourseName = courseName.trim();
// if (trimmedCourseName.isEmpty())
// continue;
// // Add course to the database
// Document course = new Document("courseName", trimmedCourseName)
// .append("students", new ArrayList<>());
// collection.insertOne(course);

// // Create a button for the new course
// RoundedButton courseButton = new RoundedButton(
// trimmedCourseName, 20);
// courseButton.setPreferredSize(new Dimension(250, 40));
// courseButton.setMaximumSize(new Dimension(250, 40));
// courseButton.setForeground(Color.BLACK);
// courseButton.setBackground(Color.decode("#8FB6FF"));
// newButtons.add(courseButton);
// p3.add(courseButton);
// p3.revalidate();
// p3.repaint();

// // Add ActionListener for the new course button
// courseButton.addActionListener(e2 -> {
// selectedButton = courseButton;
// lb1.setText("Course: " + courseButton.getText());
// clearAndReloadTable(courseButton.getText());

// });

// // Automatically select the newly added course
// selectedButton = courseButton;
// lb1.setText("Course: " + trimmedCourseName);

// // Clear the table for the new course
// clearAndReloadTable(trimmedCourseName);
// }

// JOptionPane.showMessageDialog(null, "Courses added successfully!");
// } catch (Exception ex) {
// JOptionPane.showMessageDialog(null,
// "Failed to save to database: " + ex.getMessage(),
// "Error", JOptionPane.ERROR_MESSAGE);
// }
// } else {
// JOptionPane.showMessageDialog(null, "Course names cannot be empty!", "Error",
// JOptionPane.ERROR_MESSAGE);
// }
// }
// });

// private void loadStudents() {
// try (MongoClient mongoClient = MongoClients.create(
// "mongodb+srv://Admin:admin12345@adminsystem.kedip.mongodb.net/?retryWrites=true&w=majority"))
// {
// MongoDatabase database = mongoClient.getDatabase("AdminSystem");
// MongoCollection<Document> studentCollection =
// database.getCollection("StudentManagement");
// MongoCollection<Document> courseCollection =
// database.getCollection("CourseManagement");

// // Get the list of student IDs already in the course
// Document course = courseCollection.find(new Document("courseName",
// selectedCourseName)).first();
// List<String> enrolledStudentIDs = new ArrayList<>();
// if (course != null && course.containsKey("students")) {
// List<Document> students = (List<Document>) course.get("students");
// for (Document student : students) {
// enrolledStudentIDs.add(student.getString("StudentID"));
// }
// }

// // Clear existing rows in the table
// tableModel.setRowCount(0);

// // Fetch students not already in the course
// FindIterable<Document> students = studentCollection.find();
// for (Document student : students) {
// String studentID = student.getString("ID");
// if (!enrolledStudentIDs.contains(studentID)) {
// tableModel.addRow(new Object[] {
// false, // Checkbox column
// studentID,
// student.getString("Name"),
// student.getString("Gender") != null
// ? student.getString("Gender")
// : student.getString("Sex"),
// student.getInteger("Age"),
// student.getInteger("Year"),
// student.getString("Major"),
// student.getString("AcademyYear")
// });
// }
// }
// } catch (Exception ex) {
// ex.printStackTrace();
// JOptionPane.showMessageDialog(this, "Failed to load students: " +
// ex.getMessage(), "Error",
// JOptionPane.ERROR_MESSAGE);
// }
// }

// package com.example.CourseManagement;

// import java.awt.Color;
// import java.awt.Font;

// import javax.swing.*;
// import javax.swing.border.LineBorder;
// import javax.swing.table.DefaultTableModel;

// import org.bson.Document;

// import com.example.System.Course;
// import com.example.System.StuCourse;
// import com.mongodb.client.FindIterable;
// import com.mongodb.client.MongoClient;
// import com.mongodb.client.MongoClients;
// import com.mongodb.client.MongoCollection;
// import com.mongodb.client.MongoDatabase;
// import com.mongodb.client.model.Filters;
// import com.mongodb.client.model.IndexOptions;
// import com.mongodb.client.model.Indexes;

// import java.awt.Graphics;
// import java.awt.Graphics2D;
// import java.awt.RenderingHints;
// import java.util.ArrayList;

// public class AddCourse extends JFrame {

// public DefaultTableModel model;
// private String selectedCourseName; // Store the selected course name

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

// public AddCourse(DefaultTableModel tableModel, String selectedCourseName) {
// this.model = tableModel;
// this.selectedCourseName = selectedCourseName;

// setSize(502, 500);
// setLayout(null);
// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

// JPanel p1 = new JPanel();
// p1.setBounds(10, 5, 482, 458);
// p1.setBackground(Color.white);
// p1.setBorder(new LineBorder(Color.BLACK, 2));
// p1.setLayout(null);
// add(p1);

// JLabel l1 = new JLabel("ADD STUDENT");
// l1.setBounds(150, 20, 300, 30);
// l1.setFont(new Font("Poppins", Font.BOLD, 24));
// p1.add(l1);

// JLabel l2 = new JLabel("StudentName");
// l2.setBounds(30, 80, 200, 30);
// p1.add(l2);
// JTextField t1 = new JTextField();
// t1.setBounds(130, 85, 250, 25);
// t1.setBorder(new LineBorder(Color.black, 2));
// p1.add(t1);

// JLabel l3 = new JLabel("StudentID");
// l3.setBounds(30, 125, 200, 30);
// p1.add(l3);
// JTextField t2 = new JTextField();
// t2.setBounds(130, 130, 250, 25);
// t2.setBorder(new LineBorder(Color.black, 2));
// p1.add(t2);

// JLabel l4 = new JLabel("Gender");
// l4.setBounds(30, 165, 200, 30);
// p1.add(l4);
// JTextField t3 = new JTextField();
// t3.setBounds(130, 170, 250, 25);
// t3.setBorder(new LineBorder(Color.black, 2));
// p1.add(t3);

// JLabel l5 = new JLabel("Age");
// l5.setBounds(30, 205, 200, 30);
// p1.add(l5);
// JTextField t4 = new JTextField();
// t4.setBounds(130, 210, 250, 25);
// t4.setBorder(new LineBorder(Color.black, 2));
// p1.add(t4);

// JLabel l6 = new JLabel("Year");
// l6.setBounds(30, 245, 200, 30);
// p1.add(l6);
// JTextField t5 = new JTextField();
// t5.setBounds(130, 250, 250, 25);
// t5.setBorder(new LineBorder(Color.black, 2));
// p1.add(t5);

// JLabel l7 = new JLabel("Academy Year");
// l7.setBounds(30, 285, 200, 30);
// p1.add(l7);
// JTextField t6 = new JTextField();
// t6.setBounds(130, 290, 250, 25);
// t6.setBorder(new LineBorder(Color.black, 2));
// p1.add(t6);

// JLabel l8 = new JLabel("Attendance");
// l8.setBounds(30, 325, 200, 30);
// p1.add(l8);
// JTextField t7 = new JTextField();
// t7.setBounds(130, 330, 250, 25);
// t7.setBorder(new LineBorder(Color.black, 2));
// p1.add(t7);

// RoundedButton b1 = new RoundedButton("Back", 20);
// b1.setBounds(45, 400, 100, 40);
// b1.setBackground(Color.lightGray);
// b1.setForeground(Color.black);
// p1.add(b1);
// b1.addActionListener(e -> dispose());

// RoundedButton b2 = new RoundedButton("Save", 20);
// b2.setBounds(335, 400, 100, 40);
// b2.setBackground(Color.decode("#8FB6FF"));
// b2.setForeground(Color.black);
// p1.add(b2);
// // b2.addActionListener(e -> {
// // try {
// // String studentName = t1.getText();
// // String studentID = t2.getText();
// // String gender = t3.getText();
// // int age = Integer.parseInt(t4.getText().trim());
// // int year = Integer.parseInt(t5.getText().trim());
// // String academyYear = t6.getText();
// // String attendance = t7.getText();

// // if (studentName.isEmpty() || studentID.isEmpty() || gender.isEmpty()
// // || academyYear.isEmpty() || attendance.isEmpty()) {
// // JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error",
// // JOptionPane.ERROR_MESSAGE);
// // return;
// // }

// // StuCourse student = new StuCourse(studentID, studentName, gender, age,
// year,
// // academyYear, attendance);

// // Course course = new Course(selectedCourseName);
// // course.addStudent(student);

// // // Save to database
// // try (MongoClient mongoClient = MongoClients.create(
// //
// "mongodb+srv://Admin:admin12345@adminsystem.kedip.mongodb.net/?retryWrites=true&w=majority"))
// // {
// // MongoDatabase database = mongoClient.getDatabase("AdminSystem");
// // MongoCollection<Document> collection = database
// // .getCollection("CourseManagement");

// // Document studentDoc = new Document("StudentID", student.getStudentID())
// // .append("StudentName", student.getStudentName())
// // .append("Gender", student.getStudentGender())
// // .append("Age", student.getStudentAge())
// // .append("Year", student.getStudentYear())
// // .append("AcademyYear", student.getStudentAcademy())
// // .append("Attendance", student.getStudentAttendance());

// // Document query = new Document("courseName", selectedCourseName);
// // Document update = new Document("$push", new Document("students",
// // studentDoc));

// // collection.updateOne(query, update);
// // }

// // JOptionPane.showMessageDialog(this, "Student added successfully!");
// // dispose();
// // } catch (Exception ex) {
// // JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error",
// // JOptionPane.ERROR_MESSAGE);
// // }
// // });

// b2.addActionListener(e -> {
// try {
// // Retrieve input values
// String studentName = t1.getText().trim();
// String studentID = t2.getText().trim();
// String gender = t3.getText().trim();
// String academyYear = t6.getText().trim();
// String attendance = t7.getText().trim();

// // Validate fields are not empty
// if (studentName.isEmpty() || studentID.isEmpty() || gender.isEmpty() ||
// academyYear.isEmpty() || attendance.isEmpty()) {
// JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error",
// JOptionPane.ERROR_MESSAGE);
// return;
// }

// // Validate age
// int age;
// try {
// age = Integer.parseInt(t4.getText().trim());
// if (age <= 0) {
// JOptionPane.showMessageDialog(this, "Age must be a positive number.",
// "Error", JOptionPane.ERROR_MESSAGE);
// return;
// }
// } catch (NumberFormatException ex) {
// JOptionPane.showMessageDialog(this, "Please enter a valid number for Age.",
// "Error", JOptionPane.ERROR_MESSAGE);
// return;
// }

// // Validate year
// int year;
// try {
// year = Integer.parseInt(t5.getText().trim());
// if (year <= 0) {
// JOptionPane.showMessageDialog(this, "Year must be a positive number.",
// "Error", JOptionPane.ERROR_MESSAGE);
// return;
// }
// } catch (NumberFormatException ex) {
// JOptionPane.showMessageDialog(this, "Please enter a valid number for Year.",
// "Error", JOptionPane.ERROR_MESSAGE);
// return;
// }

// // Create student and course objects
// StuCourse student = new StuCourse(studentID, studentName, gender, age, year,
// academyYear, attendance);
// Course course = new Course(selectedCourseName);

// // MongoDB operations
// try (MongoClient mongoClient = MongoClients.create(
// "mongodb+srv://Admin:admin12345@adminsystem.kedip.mongodb.net/?retryWrites=true&w=majority"))
// {
// MongoDatabase database = mongoClient.getDatabase("AdminSystem");
// MongoCollection<Document> collection = database
// .getCollection("CourseManagement");

// // Check if student with the same ID already exists in the course
// Document query = new Document("courseName", selectedCourseName)
// .append("students.StudentID", studentID);
// long count = collection.countDocuments(query);

// if (count > 0) {
// JOptionPane.showMessageDialog(this,
// "Student with this ID already exists in the course.",
// "Error",
// JOptionPane.ERROR_MESSAGE);
// return;
// }

// MongoCollection<Document> scourCollection = database
// .getCollection("StudentManagement");
// FindIterable<Document> documents = scourCollection
// .find(Filters.eq("status", "inactive"));
// MongoCollection<Document> targetCollection = database
// .getCollection("CourseManagement");
// targetCollection.insertMany(documents.into(new ArrayList<>()));
// // Add student to the course
// Document studentDoc = new Document("StudentID", student.getStudentID())
// .append("StudentName", student.getStudentName())
// .append("Gender", student.getStudentGender())
// .append("Age", student.getStudentAge())
// .append("Year", student.getStudentYear())
// .append("AcademyYear", student.getStudentAcademy())
// .append("Attendance", student.getStudentAttendance());

// Document courseQuery = new Document("courseName", selectedCourseName);
// Document update = new Document("$push", new Document("students",
// studentDoc));

// collection.updateOne(courseQuery, update);

// JOptionPane.showMessageDialog(this, "Student added successfully!");
// dispose();
// }
// } catch (Exception ex) {
// JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error",
// JOptionPane.ERROR_MESSAGE);
// }
// });

// }

// public static void main(String[] args) {
// SwingUtilities.invokeLater(() -> {
// DefaultTableModel model = new DefaultTableModel(
// new String[] { "StudentName", "StudentID", "Gender", "Age", "Year",
// "AcademyYear", "Attendance" },
// 0);
// new AddCourse(model, "OOP").setVisible(true);
// });
// }
// }
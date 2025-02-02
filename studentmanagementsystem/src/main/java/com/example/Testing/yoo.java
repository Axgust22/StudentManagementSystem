package com.example.Testing;

public class yoo {
    // package com.example.ScoreManagement;

    // import com.mongodb.client.MongoClient;
    // import com.mongodb.client.MongoClients;
    // import com.mongodb.client.MongoCollection;
    // import com.mongodb.client.MongoDatabase;
    // import com.mongodb.client.model.UpdateOptions;

    // import org.bson.Document;

    // import javax.swing.*;
    // import javax.swing.border.LineBorder;
    // import javax.swing.table.DefaultTableModel;
    // import java.awt.*;
    // import java.util.ArrayList;
    // import java.util.List;

    // public class AddScore extends JFrame {
    // private DefaultTableModel model;
    // private JTextField studentNameField;
    // private JTextField studentIDField;
    // private List<String> courses;
    // private JTextField[] scoreFields;

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
    // this.courses = new ArrayList<>();

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

    // JLabel nameLabel = new JLabel("Student Name:");
    // nameLabel.setBounds(30, 70, 100, 30);
    // panel.add(nameLabel);

    // studentNameField = new JTextField();
    // studentNameField.setBounds(150, 70, 250, 25);
    // studentNameField.setBorder(new LineBorder(Color.BLACK, 2));
    // panel.add(studentNameField);

    // JLabel idLabel = new JLabel("Student ID:");
    // idLabel.setBounds(30, 110, 100, 30);
    // panel.add(idLabel);

    // studentIDField = new JTextField();
    // studentIDField.setBounds(150, 110, 250, 25);
    // studentIDField.setBorder(new LineBorder(Color.BLACK, 2));
    // panel.add(studentIDField);

    // RoundedButton fetchCoursesButton = new RoundedButton("Fetch Courses", 20);
    // fetchCoursesButton.setBounds(150, 150, 150, 30);
    // fetchCoursesButton.setBackground(Color.BLUE);
    // fetchCoursesButton.setForeground(Color.WHITE);
    // panel.add(fetchCoursesButton);

    // // Scrollable course panel
    // // Relevant part of AddScore.java

    // JPanel coursePanel = new JPanel();
    // coursePanel.setLayout(new BoxLayout(coursePanel, BoxLayout.Y_AXIS)); //
    // Vertical layout for compact
    // // fields
    // JScrollPane scrollPane = new JScrollPane(coursePanel);
    // scrollPane.setBounds(30, 200, 480, 350); // Adjust height for compact display
    // scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    // scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    // panel.add(scrollPane);

    // fetchCoursesButton.addActionListener(e -> {
    // try {
    // String studentName = studentNameField.getText().trim();
    // String studentID = studentIDField.getText().trim();

    // if (studentName.isEmpty() || studentID.isEmpty()) {
    // throw new IllegalArgumentException("Student Name and ID are required.");
    // }

    // courses = fetchCoursesFromDatabase(studentID, studentName);
    // System.out.println("Courses fetched: " + courses); // Debugging log

    // if (courses == null || courses.isEmpty()) {
    // throw new IllegalArgumentException("No courses found for the given
    // student.");
    // }

    // coursePanel.removeAll(); // Clear previous fields

    // // Create input fields for each course
    // scoreFields = new JTextField[courses.size()];
    // for (int i = 0; i < courses.size(); i++) {
    // JPanel row = new JPanel();
    // row.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5)); // Compact row layout

    // JLabel courseLabel = new JLabel(courses.get(i) + ":");
    // courseLabel.setPreferredSize(new Dimension(200, 20)); // Consistent label
    // width
    // row.add(courseLabel);

    // JTextField scoreField = new JTextField();
    // scoreField.setPreferredSize(new Dimension(200, 25)); // Consistent field
    // width
    // scoreField.setBorder(new LineBorder(Color.BLACK, 1));
    // row.add(scoreField);

    // coursePanel.add(row);
    // scoreFields[i] = scoreField;
    // }

    // coursePanel.revalidate();
    // coursePanel.repaint();

    // } catch (Exception ex) {
    // JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error",
    // JOptionPane.ERROR_MESSAGE);
    // }
    // });

    // RoundedButton saveButton = new RoundedButton("Save", 20);
    // saveButton.setBounds(400, 580, 100, 40);
    // saveButton.setBackground(Color.BLUE);
    // saveButton.setForeground(Color.WHITE);
    // panel.add(saveButton);

    // saveButton.addActionListener(e -> {
    // try {
    // String studentName = studentNameField.getText().trim();
    // String studentID = studentIDField.getText().trim();

    // if (studentName.isEmpty() || studentID.isEmpty()) {
    // throw new IllegalArgumentException("Student Name and ID are required.");
    // }

    // double[] scores = new double[courses.size()];
    // for (int i = 0; i < courses.size(); i++) {
    // String scoreText = scoreFields[i].getText().trim();
    // if (scoreText.isEmpty()) {
    // throw new IllegalArgumentException(
    // "Score for course " + courses.get(i) + " is required.");
    // }
    // scores[i] = Double.parseDouble(scoreText);
    // if (scores[i] < 0 || scores[i] > 100) {
    // throw new IllegalArgumentException("Score for course " + courses.get(i)
    // + " must be between 0 and 100.");
    // }
    // }

    // saveStudentDataToDatabase(studentID, studentName, courses, scores);

    // JOptionPane.showMessageDialog(this, "Data saved successfully!");
    // dispose();
    // } catch (NumberFormatException ex) {
    // JOptionPane.showMessageDialog(this, "Please enter valid numeric scores!",
    // "Error",
    // JOptionPane.ERROR_MESSAGE);
    // } catch (Exception ex) {
    // JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error",
    // JOptionPane.ERROR_MESSAGE);
    // }
    // });

    // RoundedButton backButton = new RoundedButton("Back", 20);
    // backButton.setBounds(40, 580, 100, 40);
    // backButton.setBackground(Color.LIGHT_GRAY);
    // backButton.setForeground(Color.BLACK);
    // panel.add(backButton);

    // backButton.addActionListener(e -> dispose());
    // }

    // private List<String> fetchCoursesFromDatabase(String studentID, String
    // studentName) {
    // List<String> courses = new ArrayList<>();
    // try (MongoClient mongoClient = MongoClients.create(
    // "mongodb+srv://Admin:admin12345@adminsystem.kedip.mongodb.net/?retryWrites=true&w=majority"))
    // {
    // MongoDatabase database = mongoClient.getDatabase("AdminSystem");
    // MongoCollection<Document> collection =
    // database.getCollection("CourseManagement");

    // // Query to match the student within the students array
    // Document query = new Document("students", new Document("$elemMatch",
    // new Document("StudentID", studentID).append("StudentName", studentName)));

    // // Find all documents matching the query
    // for (Document result : collection.find(query)) {
    // System.out.println("Query Result: " + result.toJson()); // Debugging log

    // // Extract courseName for the matching document
    // String courseName = result.getString("courseName");
    // if (courseName != null) {
    // courses.add(courseName); // Add the course name to the list
    // }
    // }

    // if (courses.isEmpty()) {
    // System.err.println("No courses found for the given student.");
    // }
    // } catch (Exception e) {
    // e.printStackTrace();
    // JOptionPane.showMessageDialog(this, "Database Error: " + e.getMessage(),
    // "Error",
    // JOptionPane.ERROR_MESSAGE);
    // }
    // return courses;
    // }

    // private void saveStudentDataToDatabase(String studentID, String studentName,
    // List<String> courseNames,
    // double[] scores) {
    // try (MongoClient mongoClient = MongoClients.create(
    // "mongodb+srv://Admin:admin12345@adminsystem.kedip.mongodb.net/?retryWrites=true&w=majority"))
    // {
    // MongoDatabase database = mongoClient.getDatabase("AdminSystem");
    // MongoCollection<Document> collection =
    // database.getCollection("ScoreManagement");

    // // Prepare the list of courses with scores
    // List<Document> courses = new ArrayList<>();
    // double totalScore = 0;

    // for (int i = 0; i < courseNames.size(); i++) {
    // totalScore += scores[i];
    // Document course = new Document("CourseName", courseNames.get(i))
    // .append("Score", scores[i]); // Add course and its score
    // courses.add(course);
    // }

    // // Calculate GPA
    // double gpa = totalScore / courseNames.size();

    // // Create the student document
    // Document studentData = new Document()
    // .append("StudentID", studentID)
    // .append("StudentName", studentName)
    // .append("Courses", courses) // Store list of courses
    // .append("TotalScore", totalScore) // Add total score
    // .append("GPA", gpa); // Add GPA

    // // Use upsert to insert or update the student data
    // collection.updateOne(
    // new Document("StudentID", studentID), // Filter by StudentID
    // new Document("$set", studentData), // Update the data
    // new UpdateOptions().upsert(true)); // Insert if not exists

    // System.out.println("Student data saved successfully!");
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // }

    // public static void main(String[] args) {
    // SwingUtilities.invokeLater(() -> {
    // DefaultTableModel model = new DefaultTableModel(
    // new String[] { "Student Name", "Student ID", "Course", "Score", "Total
    // Point",
    // "GPA" },
    // 0);
    // new AddScore(model).setVisible(true);
    // });
    // }
    // }
}

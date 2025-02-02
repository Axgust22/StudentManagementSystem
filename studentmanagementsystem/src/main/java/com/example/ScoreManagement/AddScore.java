package com.example.ScoreManagement;

import com.example.HandleSystem.Score;
import com.example.HandleSystem.StuCourse;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;

import org.bson.Document;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AddScore extends JFrame {
        private DefaultTableModel model;
        private JTextField studentNameField;
        private JTextField studentIDField;
        private List<String> courses;
        private JTextField[] scoreFields;
        private StuCourse currentStudent;
        private ScoreManagement scoreManagement;
        private Score studentScore;

        class RoundedButton extends JButton {
                private int radius;

                public RoundedButton(String text, int radius) {
                        super(text);
                        this.radius = radius;
                        setContentAreaFilled(false);
                        setFocusPainted(false);
                }

                protected void paintComponent(Graphics g) {
                        Graphics2D g2 = (Graphics2D) g.create();
                        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                        g2.setColor(getBackground());
                        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
                        super.paintComponent(g2);
                        g2.dispose();
                }

                protected void paintBorder(Graphics g) {
                        Graphics2D g2 = (Graphics2D) g.create();
                        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                        // Draw the rounded border
                        g2.setColor(getForeground());
                        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
                        g2.dispose();
                }
        }

        public AddScore(DefaultTableModel tableModel, String semester, ScoreManagement scoreManagement) {
                this.model = tableModel;
                this.courses = new ArrayList<>();
                this.scoreManagement = scoreManagement;
                setSize(570, 700);
                setTitle("Add Score - " + semester);
                setLayout(null);
                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                JPanel panel = new JPanel();
                panel.setBounds(10, 10, 550, 650);
                panel.setBackground(Color.white);
                panel.setBorder(new LineBorder(Color.BLACK, 2));
                panel.setLayout(null);
                add(panel);

                JLabel titleLabel = new JLabel("ADD SCORE OF STUDENT");
                titleLabel.setBounds(130, 20, 400, 30);
                titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
                panel.add(titleLabel);

                JLabel nameLabel = new JLabel("Student Name");
                nameLabel.setBounds(30, 70, 100, 30);
                panel.add(nameLabel);

                studentNameField = new JTextField();
                studentNameField.setBounds(150, 70, 250, 25);
                studentNameField.setBorder(new LineBorder(Color.BLACK, 2));
                panel.add(studentNameField);

                JLabel idLabel = new JLabel("Student ID");
                idLabel.setBounds(30, 110, 100, 30);
                panel.add(idLabel);

                studentIDField = new JTextField();
                studentIDField.setBounds(150, 110, 250, 25);
                studentIDField.setBorder(new LineBorder(Color.BLACK, 2));
                panel.add(studentIDField);

                RoundedButton fetchCoursesButton = new RoundedButton("Fetch Courses", 20);
                fetchCoursesButton.setBounds(205, 150, 150, 30);
                fetchCoursesButton.setBackground(Color.decode("#8FB6FF"));
                fetchCoursesButton.setForeground(Color.BLACK);
                panel.add(fetchCoursesButton);

                JPanel coursePanel = new JPanel();
                coursePanel.setLayout(new BoxLayout(coursePanel, BoxLayout.Y_AXIS));
                JScrollPane scrollPane = new JScrollPane(coursePanel);
                scrollPane.setBounds(35, 200, 480, 350);
                scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                panel.add(scrollPane);

                fetchCoursesButton.addActionListener(e -> {
                        try {
                                String studentName = studentNameField.getText().trim();
                                String studentID = studentIDField.getText().trim();

                                if (studentName.isEmpty() || studentID.isEmpty()) {
                                        throw new IllegalArgumentException("Student Name and ID are required.");
                                }

                                courses = fetchCoursesFromDatabase(studentID, studentName);
                                if (courses.isEmpty()) {
                                        throw new IllegalArgumentException("No courses found for the given student.");
                                }

                                studentScore = new Score(studentID, studentName);

                                coursePanel.removeAll();
                                scoreFields = new JTextField[courses.size()];
                                for (int i = 0; i < courses.size(); i++) {
                                        JPanel row = new JPanel();
                                        row.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

                                        JLabel courseLabel = new JLabel(courses.get(i) + ":");
                                        courseLabel.setPreferredSize(new Dimension(200, 20));
                                        row.add(courseLabel);

                                        JTextField scoreField = new JTextField();
                                        scoreField.setPreferredSize(new Dimension(200, 25));
                                        scoreField.setBorder(new LineBorder(Color.BLACK, 1));
                                        row.add(scoreField);

                                        coursePanel.add(row);
                                        scoreFields[i] = scoreField;
                                }

                                coursePanel.revalidate();
                                coursePanel.repaint();

                        } catch (Exception ex) {
                                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error",
                                                JOptionPane.ERROR_MESSAGE);
                        }
                });

                RoundedButton saveButton = new RoundedButton("Save", 20);
                saveButton.setBounds(405, 580, 100, 40);
                saveButton.setBackground(Color.decode("#8FB6FF"));
                saveButton.setForeground(Color.BLACK);
                panel.add(saveButton);
                saveButton.addActionListener(e -> {
                        try {
                                String studentID = studentIDField.getText().trim();
                                String studentName = studentNameField.getText().trim();

                                if (studentID.isEmpty() || studentName.isEmpty()) {
                                        throw new IllegalArgumentException("Student ID and Name are required.");
                                }

                                int year = Integer.parseInt(semester.split(" - ")[0].replaceAll("[^0-9]", ""));
                                int sem = Integer.parseInt(semester.split(" - ")[1].replaceAll("[^0-9]", ""));

                                for (int i = 0; i < courses.size(); i++) {
                                        String scoreText = scoreFields[i].getText().trim();
                                        if (scoreText.isEmpty()) {
                                                throw new IllegalArgumentException(
                                                                "Score for course " + courses.get(i) + " is required.");
                                        }
                                        double score = Double.parseDouble(scoreText);
                                        studentScore.addOrUpdateScore(year, sem, courses.get(i), score);
                                }

                                saveStudentDataToDatabase(year, sem, studentScore);

                                scoreManagement.refreshTableData(String.valueOf(year), String.valueOf(sem));

                                JOptionPane.showMessageDialog(this, "Data saved successfully!", "Success",
                                                JOptionPane.INFORMATION_MESSAGE);

                                dispose();
                        } catch (Exception ex) {
                                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error",
                                                JOptionPane.ERROR_MESSAGE);
                        }
                });

                RoundedButton backButton = new RoundedButton("Back", 20);
                backButton.setBounds(40, 580, 100, 40);
                backButton.setBackground(Color.WHITE);
                backButton.setForeground(Color.BLACK);
                panel.add(backButton);

                backButton.addActionListener(e -> dispose());
        }

        private List<String> fetchCoursesFromDatabase(String studentID, String studentName) {
                List<String> courses = new ArrayList<>();
                try (MongoClient mongoClient = MongoClients.create(
                                "mongodb+srv://Admin:admin12345@adminsystem.kedip.mongodb.net/?retryWrites=true&w=majority")) {
                        MongoDatabase database = mongoClient.getDatabase("AdminSystem");
                        MongoCollection<Document> collection = database.getCollection("CourseManagement");

                        Document query = new Document("students", new Document("$elemMatch",
                                        new Document("StudentID", studentID).append("StudentName", studentName)));

                        for (Document result : collection.find(query)) {
                                String courseName = result.getString("courseName");
                                if (courseName != null) {
                                        courses.add(courseName);
                                }

                                if (currentStudent == null) {
                                        currentStudent = new StuCourse(
                                                        studentID,
                                                        studentName,
                                                        result.getString("studentGender") != null
                                                                        ? result.getString("studentGender")
                                                                        : "Unknown",
                                                        result.getInteger("studentAge") != null
                                                                        ? result.getInteger("studentAge")
                                                                        : 0,
                                                        result.getInteger("studentYear") != null
                                                                        ? result.getInteger("studentYear")
                                                                        : 0,
                                                        result.getString("studentMajor") != null
                                                                        ? result.getString("studentMajor")
                                                                        : "Unknown",
                                                        result.getString("studentAcademy") != null
                                                                        ? result.getString("studentAcademy")
                                                                        : "Unknown");
                                }
                        }

                } catch (Exception e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(this, "Database Error: " + e.getMessage(), "Error",
                                        JOptionPane.ERROR_MESSAGE);
                }
                return courses;
        }

        private void saveStudentDataToDatabase(int year, int semester, Score studentScore) {
                try (MongoClient mongoClient = MongoClients.create(
                                "mongodb+srv://Admin:admin12345@adminsystem.kedip.mongodb.net/?retryWrites=true&w=majority")) {

                        MongoDatabase database = mongoClient.getDatabase("AdminSystem");
                        MongoCollection<Document> collection = database.getCollection("ScoreManagement");

                        List<Document> courses = new ArrayList<>();
                        double totalScore = 0;

                        for (Map.Entry<String, Double> entry : studentScore.getScores(year, semester).entrySet()) {
                                double score = entry.getValue();
                                courses.add(new Document("CourseName", entry.getKey()).append("Score", score));
                                totalScore += score;
                        }

                        double gpa = studentScore.calculateGPA(year, semester);

                        System.out.println("📌 Saving Total Score: " + totalScore);

                        Document query = new Document("StudentID", studentScore.getStudentID())
                                        .append("AcademicRecords.Year", year)
                                        .append("AcademicRecords.Semester", semester);

                        Document existingStudent = collection.find(query).first();

                        if (existingStudent != null) {
                                Document update = new Document("$set",
                                                new Document("AcademicRecords.$.Courses", courses)
                                                                .append("AcademicRecords.$.TotalScore", totalScore)
                                                                .append("AcademicRecords.$.GPA", gpa));
                                collection.updateOne(query, update);
                        } else {
                                Document academicRecord = new Document()
                                                .append("Year", year)
                                                .append("Semester", semester)
                                                .append("Courses", courses)
                                                .append("TotalScore", totalScore)
                                                .append("GPA", gpa);

                                Document pushUpdate = new Document("$push",
                                                new Document("AcademicRecords", academicRecord))
                                                .append("$set", new Document("StudentName",
                                                                studentScore.getStudentName()));

                                collection.updateOne(new Document("StudentID", studentScore.getStudentID()), pushUpdate,
                                                new UpdateOptions().upsert(true));
                        }

                        JOptionPane.showMessageDialog(this, "Data saved successfully!", "Success",
                                        JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, "Error saving data: " + e.getMessage(), "Error",
                                        JOptionPane.ERROR_MESSAGE);
                        e.printStackTrace();
                }
        }

        public static void main(String[] args) {
                SwingUtilities.invokeLater(() -> {
                        DefaultTableModel model = new DefaultTableModel(
                                        new String[] { "Student Name", "Student ID", "Course", "Score", "Total Point",
                                                        "GPA" },
                                        0);

                        ScoreManagement scoreManagement = new ScoreManagement(); // Create ScoreManagement instance
                        new AddScore(model, "Year 1 - Semester 1", scoreManagement).setVisible(true); // Pass
                                                                                                      // `scoreManagement`
                });
        }

}

// private void addNewCourseToExistingStudents(String year, String semester,
// String newCourseName) {
// try (MongoClient mongoClient = MongoClients.create(
// "mongodb+srv://Admin:admin12345@adminsystem.kedip.mongodb.net/?retryWrites=true&w=majority"))
// {
// MongoDatabase database = mongoClient.getDatabase("AdminSystem");
// MongoCollection<Document> collection =
// database.getCollection("ScoreManagement");

// // Query for all students with the specific year and semester
// Document query = new Document("AcademicRecords",
// new Document("$elemMatch",
// new Document("Year", year).append("Semester", semester)));

// // Add the new course with a default score to the matching records
// List<Document> students = collection.find(query).into(new ArrayList<>());
// for (Document student : students) {
// List<Document> academicRecords = (List<Document>)
// student.get("AcademicRecords");
// for (Document record : academicRecords) {
// if (record.getString("Year").equals(year)
// && record.getString("Semester").equals(semester)) {
// List<Document> courses = (List<Document>) record.get("Courses");
// boolean courseExists = courses.stream()
// .anyMatch(course -> newCourseName.equals(
// course.getString("CourseName")));
// if (!courseExists) {
// courses.add(new Document("CourseName", newCourseName)
// .append("Score", 0));
// }
// }
// }
// // Update the student document
// collection.updateOne(new Document("_id", student.get("_id")),
// new Document("$set", new Document("AcademicRecords", academicRecords)));
// }
// } catch (Exception e) {
// e.printStackTrace();
// JOptionPane.showMessageDialog(this, "Error updating existing students: " +
// e.getMessage(),
// "Error", JOptionPane.ERROR_MESSAGE);
// }
// }
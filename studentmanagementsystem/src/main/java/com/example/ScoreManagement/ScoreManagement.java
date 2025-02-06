package com.example.ScoreManagement;

import javax.swing.*;

import javax.swing.border.LineBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import com.example.MainSystem;
import com.example.CourseManagement.CourseManagement;
import com.example.HandleSystem.Score;
import com.example.StudentManagement.StudentManagementSystem;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.result.UpdateResult;

import org.bson.Document;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.HashMap;
import java.util.Map;

public class ScoreManagement extends JFrame {
    private static final ArrayList<RoundedButton> newButtons = new ArrayList<>();
    private Set<String> deletedCourses = new HashSet<>();
    private int courseCounter = 1;
    private Map<String, RoundedButton> courseButtonMap = new HashMap<>();
    private DefaultTableModel model;
    private DefaultTableModel originalModel;
    private Vector<String> columnIdentifiers;
    private Color buttonColor = Color.decode("#8FB6FF");
    private String currentSemester = "";
    private List<String> columnList = new ArrayList<>(
            Arrays.asList("StudentID", "StudentName", "Total Point", "GPA"));

    private JPanel p5;
    private JTable jt;
    private Set<String> cachedColumnNames = null;
    private JPanel p4;
    private JButton B1;

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

    public ScoreManagement() {
        setSize(1560, 800);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Color buttonColor = Color.decode("#8FB6FF");

        JPanel p1 = new JPanel();
        p1.setBounds(0, 0, 1800, 800);
        p1.setBackground(Color.white);
        p1.setLayout(null);
        add(p1);

        JPanel p2 = new JPanel();
        p2.setBounds(30, 120, 1500, 630);
        p2.setBackground(Color.white);
        p2.setBorder(new LineBorder(Color.black, 2));
        p2.setLayout(null);
        p1.add(p2);

        JPanel p3 = new JPanel();
        p3.setBounds(5, 5, 300, 620);
        p3.setBackground(Color.white);
        p3.setBorder(new LineBorder(Color.black, 2));
        p3.setLayout(null);
        p2.add(p3);

        JPanel p4 = new JPanel();
        p4.setBounds(310, 5, 1185, 620);
        p4.setBackground(Color.white);
        p4.setBorder(new LineBorder(Color.black, 2));
        p4.setLayout(null);
        p2.add(p4);

        p5 = new JPanel();
        p5.setBounds(8, 85, 1170, 470);
        p5.setBackground(Color.WHITE);
        p5.setBorder(new LineBorder(Color.BLACK, 2));
        p5.setLayout(new BorderLayout());
        p4.add(p5);

        JLabel l1 = new JLabel("STUDENT SCORE");
        l1.setBounds(30, 20, 800, 40);
        l1.setFont(new Font("Time now Roman", Font.BOLD, 36));
        p1.add(l1);

        JLabel lb2 = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon("Image/ITC.jpg");
                Image img = icon.getImage();
                Graphics2D g2d = (Graphics2D) g;
                g2d.setClip(new java.awt.geom.Ellipse2D.Double(0, 0, getWidth(), getHeight()));
                g2d.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            }
        };

        lb2.setBounds(1400, 0, 120, 120);
        p1.add(lb2);

        RoundedButton b1 = new RoundedButton("Student Information", 20);
        b1.setBounds(30, 80, 250, 40);
        b1.setFont(new Font("Arial", Font.PLAIN, 18));
        b1.setForeground(Color.black);
        b1.setBackground(buttonColor);
        p1.add(b1);
        b1.addActionListener(e -> {
            new StudentManagementSystem().setVisible(true);
            dispose();
        });

        RoundedButton b2 = new RoundedButton("Student Course", 20);
        b2.setBounds(300, 80, 250, 40);
        b2.setForeground(Color.black);
        b2.setFont(new Font("Arial", Font.PLAIN, 18));
        b2.setBackground(buttonColor);
        p1.add(b2);

        b2.addActionListener(e -> {
            new CourseManagement().setVisible(true);
            dispose();
        });

        RoundedButton b3 = new RoundedButton("Student Score", 20);
        b3.setBounds(570, 80, 250, 40);
        b3.setForeground(Color.black);
        b3.setFont(new Font("Arial", Font.PLAIN, 18));
        b3.setBackground(buttonColor);
        p1.add(b3);

        JLabel l2 = new JLabel("Search Student Score:");
        l2.setBounds(10, 45, 200, 30);
        l2.setFont(new Font("Arial", Font.PLAIN, 18));
        p4.add(l2);

        JTextField t1 = new JTextField();
        t1.setBounds(210, 45, 220, 30);
        t1.setBorder(new LineBorder(Color.BLACK, 2));
        t1.setFont(new Font("Arial", Font.PLAIN, 18));
        p4.add(t1);
        RoundedButton b5 = new RoundedButton("Back", 20);
        b5.setBounds(630, 45, 150, 30);
        b5.setForeground(Color.BLACK);
        b5.setFont(new Font("poppins", Font.PLAIN, 18));
        b5.setBackground(Color.WHITE);
        p4.add(b5);

        b5.addActionListener(e -> {
            try (MongoClient mongoClient = MongoClients.create(
                    "mongodb+srv://Admin:admin12345@adminsystem.kedip.mongodb.net/?retryWrites=true&w=majority")) {
                MongoDatabase database = mongoClient.getDatabase("AdminSystem");
                MongoCollection<Document> collection = database.getCollection("ScoreManagement");

                List<Document> allStudents = collection.find().into(new ArrayList<>());
                List<String> columnNames = new ArrayList<>();
                columnNames.add("StudentID");
                columnNames.add("StudentName");
                Set<String> courseSet = new LinkedHashSet<>();

                for (Document student : allStudents) {
                    List<Document> academicRecords = student.getList("AcademicRecords", Document.class);
                    if (academicRecords != null) {
                        for (Document record : academicRecords) {
                            List<Document> courses = record.getList("Courses", Document.class);
                            if (courses != null) {
                                for (Document course : courses) {
                                    courseSet.add(course.getString("CourseName") + " Score");
                                }
                            }
                        }
                    }
                }
                columnNames.addAll(courseSet);
                columnNames.add("Total Point");
                columnNames.add("GPA");

                DefaultTableModel tempModel = new DefaultTableModel(null, columnNames.toArray());

                for (Document student : allStudents) {
                    String studentID = student.getString("StudentID");
                    String studentName = student.getString("StudentName");
                    List<Object> rowData = new ArrayList<>();
                    rowData.add(studentID);
                    rowData.add(studentName);

                    double totalScore = 0;
                    int courseCount = 0;

                    for (String courseColumn : courseSet) {
                        String courseName = courseColumn.replace(" Score", "");
                        boolean found = false;

                        List<Document> academicRecords = student.getList("AcademicRecords", Document.class);
                        if (academicRecords != null) {
                            for (Document record : academicRecords) {
                                List<Document> courses = record.getList("Courses", Document.class);
                                if (courses != null) {
                                    for (Document course : courses) {
                                        if (courseName.equals(course.getString("CourseName"))) {
                                            Object score = course.get("Score");
                                            double scoreValue = (score instanceof Integer)
                                                    ? ((Integer) score).doubleValue()
                                                    : (score instanceof Double) ? (Double) score : 0.0;
                                            rowData.add(scoreValue);
                                            totalScore += scoreValue;
                                            courseCount++;
                                            found = true;
                                            break;
                                        }
                                    }
                                }
                                if (found)
                                    break;
                            }
                        }

                        if (!found)
                            rowData.add("N/A");
                    }
                    double gpa = courseCount > 0 ? totalScore / courseCount : 0;
                    rowData.add(totalScore);
                    rowData.add(gpa);

                    tempModel.addRow(rowData.toArray());
                }

                jt.setModel(tempModel);
                t1.setText("");
                JOptionPane.showMessageDialog(null, "Table restored to full dataset!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error connecting to MongoDB: " + ex.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });

        RoundedButton b4 = new RoundedButton("Search", 20);
        b4.setBounds(450, 45, 150, 30);
        b4.setForeground(Color.BLACK);
        b4.setFont(new Font("Arial", Font.PLAIN, 18));
        b4.setBackground(Color.decode("#8FB6FF"));
        p4.add(b4);

        b4.addActionListener(e -> {
            String searchID = t1.getText().trim();

            if (!searchID.isEmpty()) {
                try (MongoClient mongoClient = MongoClients.create(
                        "mongodb+srv://Admin:admin12345@adminsystem.kedip.mongodb.net/?retryWrites=true&w=majority")) {
                    MongoDatabase database = mongoClient.getDatabase("AdminSystem");
                    MongoCollection<Document> collection = database.getCollection("ScoreManagement");

                    Document query = new Document("StudentID", searchID);
                    Document student = collection.find(query).first();

                    if (student != null) {

                        List<String> columnNames = new ArrayList<>();
                        columnNames.add("StudentID");
                        columnNames.add("StudentName");

                        Score studentScore = new Score(student.getString("StudentID"),
                                student.getString("StudentName"));
                        List<Document> academicRecords = student.getList("AcademicRecords", Document.class);

                        Set<String> courseSet = new LinkedHashSet<>();
                        if (academicRecords != null) {
                            for (Document record : academicRecords) {
                                List<Document> courses = record.getList("Courses", Document.class);
                                if (courses != null) {
                                    for (Document course : courses) {
                                        studentScore.addOrUpdateScore(record.getInteger("Year"),
                                                record.getInteger("Semester"),
                                                course.getString("CourseName"),
                                                course.getDouble("Score"));
                                        courseSet.add(course.getString("CourseName") + " Score");
                                    }
                                }
                            }
                        }

                        columnNames.addAll(courseSet);
                        columnNames.add("Total Point");
                        columnNames.add("GPA");

                        DefaultTableModel tempModel = new DefaultTableModel(null, columnNames.toArray());

                        List<Object> rowData = new ArrayList<>();
                        rowData.add(studentScore.getStudentID());
                        rowData.add(studentScore.getStudentName());

                        double totalScore = 0;
                        int courseCount = 0;

                        for (String courseColumn : courseSet) {
                            String courseName = courseColumn.replace(" Score", "");

                            boolean found = false;
                            for (Document record : academicRecords) {
                                List<Document> courses = record.getList("Courses", Document.class);
                                if (courses != null) {
                                    for (Document course : courses) {
                                        if (courseName.equals(course.getString("CourseName"))) {

                                            double score = course.getDouble("Score");
                                            rowData.add(score);
                                            totalScore += score;
                                            courseCount++;
                                            found = true;
                                            break;
                                        }
                                    }
                                }
                                if (found)
                                    break;
                            }

                            if (!found) {
                                rowData.add("N/A");
                            }
                        }

                        double gpa = courseCount > 0 ? totalScore / courseCount : 0;
                        rowData.add(totalScore);
                        rowData.add(gpa);

                        tempModel.addRow(rowData.toArray());

                        jt.setModel(tempModel);
                    } else {
                        JOptionPane.showMessageDialog(this, "Student ID not found!", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error connecting to MongoDB: " + ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please enter Student ID to search!", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        RoundedButton b8 = new RoundedButton("Update", 20);
        b8.setBounds(80, 565, 150, 40);
        b8.setForeground(Color.black);
        b8.setFont(new Font("Arial", Font.PLAIN, 18));
        b8.setBackground(buttonColor);
        p4.add(b8);
        b8.addActionListener(e -> {
            System.out.println("Update Button Clicked!");

            int selectedRow = jt.getSelectedRow();
            int selectedColumn = jt.getSelectedColumn();

            if (selectedRow == -1 || selectedColumn <= 1) {
                JOptionPane.showMessageDialog(null, "Please select a valid course score to update!", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            Pattern pattern = Pattern.compile("Year (\\d+) - Semester (\\d+)");
            Matcher matcher = pattern.matcher(currentSemester);

            if (!matcher.find()) {
                JOptionPane.showMessageDialog(null, "Invalid semester format. Please select a valid semester.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            int year = Integer.parseInt(matcher.group(1));
            int semester = Integer.parseInt(matcher.group(2));

            String studentID = jt.getValueAt(selectedRow, 0).toString();
            String courseName = jt.getColumnName(selectedColumn).replace(" Score", "").trim();
            String currentScore = jt.getValueAt(selectedRow, selectedColumn).toString();

            JTextField scoreField = new JTextField(currentScore);
            JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
            panel.add(new JLabel("Course:"));
            panel.add(new JLabel(courseName));
            panel.add(new JLabel("New Score:"));
            panel.add(scoreField);

            int option = JOptionPane.showConfirmDialog(null, panel, "Update Course Score", JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE);

            if (option == JOptionPane.OK_OPTION) {
                String newScoreInput = scoreField.getText().trim();

                if (!newScoreInput.matches("\\d+(\\.\\d+)?")) {
                    JOptionPane.showMessageDialog(null, "Invalid score value! Please enter numeric values only.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                double newScore = Double.parseDouble(newScoreInput);

                try (MongoClient mongoClient = MongoClients.create(
                        "mongodb+srv://Admin:admin12345@adminsystem.kedip.mongodb.net/?retryWrites=true&w=majority")) {
                    MongoDatabase database = mongoClient.getDatabase("AdminSystem");
                    MongoCollection<Document> collection = database.getCollection("ScoreManagement");

                    Document studentQuery = new Document("StudentID", studentID);
                    Document studentData = collection.find(studentQuery).first();

                    if (studentData == null) {
                        JOptionPane.showMessageDialog(null, "Student data not found!", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    Score studentScore = new Score(studentID, studentData.getString("StudentName"));
                    List<Document> academicRecords = studentData.getList("AcademicRecords", Document.class);

                    for (Document record : academicRecords) {
                        int recordYear = record.getInteger("Year");
                        int recordSemester = record.getInteger("Semester");
                        for (Document course : record.getList("Courses", Document.class)) {
                            studentScore.addOrUpdateScore(recordYear, recordSemester, course.getString("CourseName"),
                                    course.getDouble("Score"));
                        }
                    }

                    studentScore.updateScore(year, semester, courseName, newScore);
                    double newTotalScore = studentScore.getTotalScore(year, semester);
                    double newGPA = studentScore.calculateGPA(year, semester);

                    List<Document> updatedAcademicRecords = new ArrayList<>();
                    Map<String, List<Document>> semesterMap = new HashMap<>();

                    for (Object[] row : studentScore.toTableData()) {
                        int rowYear = (int) row[2];
                        int rowSemester = (int) row[3];
                        String rowCourse = (String) row[4];
                        double rowScore = (double) row[5];

                        String key = rowYear + "-" + rowSemester;
                        semesterMap.putIfAbsent(key, new ArrayList<>());

                        semesterMap.get(key).add(new Document("CourseName", rowCourse).append("Score", rowScore));
                    }

                    for (Map.Entry<String, List<Document>> entry : semesterMap.entrySet()) {
                        String[] yearSem = entry.getKey().split("-");
                        int currentYear = Integer.parseInt(yearSem[0]);
                        int currentSemester = Integer.parseInt(yearSem[1]);

                        if (currentYear == year && currentSemester == semester) {
                            updatedAcademicRecords.add(new Document("Year", currentYear)
                                    .append("Semester", currentSemester)
                                    .append("Courses", entry.getValue())
                                    .append("TotalScore", newTotalScore)
                                    .append("GPA", newGPA));
                        } else {
                            updatedAcademicRecords.add(new Document("Year", currentYear)
                                    .append("Semester", currentSemester)
                                    .append("Courses", entry.getValue())
                                    .append("TotalScore",
                                            entry.getValue().stream().mapToDouble(course -> course.getDouble("Score"))
                                                    .sum())
                                    .append("GPA", entry.getValue().stream()
                                            .mapToDouble(course -> course.getDouble("Score")).average().orElse(0)));
                        }
                    }

                    Document update = new Document("$set", new Document("AcademicRecords", updatedAcademicRecords));
                    collection.updateOne(studentQuery, update);

                    jt.setValueAt(newScore, selectedRow, selectedColumn);
                    int totalScoreIndex = -1;
                    int gpaIndex = -1;

                    for (int i = 0; i < jt.getColumnCount(); i++) {
                        if (jt.getColumnName(i).equals("Total Point")) {
                            totalScoreIndex = i;
                        }
                        if (jt.getColumnName(i).equals("GPA")) {
                            gpaIndex = i;
                        }
                    }

                    if (totalScoreIndex != -1) {
                        jt.setValueAt(newTotalScore, selectedRow, totalScoreIndex);
                    } else {
                        System.out.println("Column 'Total Point' not found!");
                    }

                    if (gpaIndex != -1) {
                        jt.setValueAt(newGPA, selectedRow, gpaIndex);
                    } else {
                        System.out.println("Column 'GPA' not found!");
                    }

                    DefaultTableModel model = (DefaultTableModel) jt.getModel();
                    model.fireTableDataChanged();
                    jt.revalidate();
                    jt.repaint();

                    JOptionPane.showMessageDialog(null, "Score updated successfully!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error updating score: " + ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });

        RoundedButton b9 = new RoundedButton("Delete", 20);
        b9.setBounds(397, 565, 150, 40);
        b9.setForeground(Color.black);
        b9.setFont(new Font("Arial", Font.PLAIN, 18));
        b9.setBackground(buttonColor);
        p4.add(b9);

        b9.addActionListener(e -> {
            int selectedRow = jt.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select a row to delete!", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            String studentID = model.getValueAt(selectedRow, 0).toString();
            String semester = currentSemester;

            if (semester.isEmpty() || studentID.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Student ID or semester is not set. Please try again!", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to delete all data for Student ID: " + studentID + " in " + semester + "?",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);

            if (confirm == JOptionPane.YES_OPTION) {
                try (MongoClient mongoClient = MongoClients.create(
                        "mongodb+srv://Admin:admin12345@adminsystem.kedip.mongodb.net/?retryWrites=true&w=majority")) {
                    MongoDatabase database = mongoClient.getDatabase("AdminSystem");
                    MongoCollection<Document> collection = database.getCollection("ScoreManagement");

                    String[] semesterParts = semester.split(" - ");
                    if (semesterParts.length != 2) {
                        JOptionPane.showMessageDialog(this, "Invalid semester format. Please try again.", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    int year = Integer.parseInt(semesterParts[0].replaceAll("[^0-9]", "").trim());
                    int semesterNumber = Integer.parseInt(semesterParts[1].replaceAll("[^0-9]", "").trim());

                    Document query = new Document("StudentID", studentID);
                    Document studentData = collection.find(query).first();

                    if (studentData == null) {
                        JOptionPane.showMessageDialog(this, "Student data not found!", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    Score studentScore = new Score(studentID, studentData.getString("StudentName"));
                    List<Document> academicRecords = studentData.getList("AcademicRecords", Document.class);

                    boolean semesterFound = false;
                    for (Document record : academicRecords) {
                        int recordYear = record.getInteger("Year");
                        int recordSemester = record.getInteger("Semester");

                        if (recordYear == year && recordSemester == semesterNumber) {
                            academicRecords.remove(record);
                            semesterFound = true;
                            break;
                        }
                    }

                    if (!semesterFound) {
                        JOptionPane.showMessageDialog(this, "No matching semester data found!", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    double newTotalScore = studentScore.getTotalScore(year, semesterNumber);
                    double newGPA = studentScore.calculateGPA(year, semesterNumber);

                    Document updatedRecord = new Document("AcademicRecords", academicRecords);
                    UpdateResult result = collection.updateOne(query, new Document("$set", updatedRecord));

                    studentData = collection.find(query).first();
                    if (studentData != null) {
                        List<Document> updatedRecords = studentData.getList("AcademicRecords", Document.class);
                        if (updatedRecords == null || updatedRecords.isEmpty()) {
                            collection.deleteOne(query);
                        }
                    }

                    if (result.getModifiedCount() > 0) {
                        ((DefaultTableModel) jt.getModel()).removeRow(selectedRow);
                        JOptionPane.showMessageDialog(this, "Semester data deleted successfully!");
                    } else {
                        JOptionPane.showMessageDialog(this, "No matching semester found to delete!", "Info",
                                JOptionPane.INFORMATION_MESSAGE);
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error deleting semester data: " + ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });

        RoundedButton b10 = new RoundedButton("Clear Form", 20);
        b10.setBounds(714, 565, 150, 40);
        b10.setForeground(Color.black);
        b10.setFont(new Font("Arial", Font.PLAIN, 18));
        b10.setBackground(buttonColor);
        p4.add(b10);

        b10.addActionListener(e -> {
            String semester = JOptionPane.showInputDialog(this, "Enter Semester (e.g., Semester 1):");
            String year = JOptionPane.showInputDialog(this, "Enter Year (e.g., Year 1):");

            if (semester == null || year == null || semester.trim().isEmpty() || year.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Year and Semester must be provided to delete records!", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            int semesterInt;
            int yearInt;
            try {
                semesterInt = Integer.parseInt(semester.replaceAll("[^0-9]", "").trim());
                yearInt = Integer.parseInt(year.replaceAll("[^0-9]", "").trim());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input! Year and Semester should be numerical.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to delete all data (Courses, TotalScore, and GPA) for " + yearInt + " - "
                            + semesterInt + " across all students?",
                    "Confirm Deletion",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                try (MongoClient mongoClient = MongoClients.create(
                        "mongodb+srv://Admin:admin12345@adminsystem.kedip.mongodb.net/?retryWrites=true&w=majority")) {
                    MongoDatabase database = mongoClient.getDatabase("AdminSystem");
                    MongoCollection<Document> collection = database.getCollection("ScoreManagement");

                    Document query = new Document("AcademicRecords",
                            new Document("$elemMatch",
                                    new Document("Year", yearInt).append("Semester", semesterInt)));

                    List<Document> studentsToUpdate = collection.find(query).into(new ArrayList<>());

                    if (studentsToUpdate.isEmpty()) {
                        JOptionPane.showMessageDialog(this,
                                "No records found for " + yearInt + " - " + semesterInt + " to delete.", "Info",
                                JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }

                    for (Document studentData : studentsToUpdate) {
                        String studentID = studentData.getString("StudentID");
                        Score studentScore = new Score(studentID, studentData.getString("StudentName"));

                        List<Document> academicRecords = studentData.getList("AcademicRecords", Document.class);
                        List<Document> updatedAcademicRecords = new ArrayList<>();

                        for (Document record : academicRecords) {
                            int recordYear = record.getInteger("Year");
                            int recordSemester = record.getInteger("Semester");

                            if (recordYear == yearInt && recordSemester == semesterInt) {
                                studentScore.removeSemester(recordYear, recordSemester);
                                continue;
                            }

                            updatedAcademicRecords.add(record);
                        }

                        if (updatedAcademicRecords.isEmpty()) {
                            collection.deleteOne(new Document("StudentID", studentID));
                            continue;
                        }

                        double newTotalScore = studentScore.getTotalScore(yearInt, semesterInt);
                        double newGPA = studentScore.calculateGPA(yearInt, semesterInt);

                        Document update = new Document("$set", new Document("AcademicRecords", updatedAcademicRecords)
                                .append("TotalScore", newTotalScore).append("GPA", newGPA));

                        collection.updateOne(new Document("StudentID", studentID), update);
                    }

                    JOptionPane.showMessageDialog(this,
                            "All records for Year " + yearInt + " - " + semesterInt
                                    + " have been deleted successfully!");

                    model.setRowCount(0);
                    jt.revalidate();
                    jt.repaint();

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Failed to delete records: " + ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });

        RoundedButton B1 = new RoundedButton("Add Students", 20);
        B1.setBounds(1030, 10, 150, 40);
        B1.setForeground(Color.black);
        B1.setVisible(false);
        B1.setBackground(buttonColor);
        p4.add(B1);

        JLabel lb3 = new JLabel("Year 1");
        lb3.setBounds(130, 10, 300, 30);
        lb3.setFont(new Font(getName(), Font.BOLD, 16));
        lb3.setForeground(Color.black);
        p3.add(lb3);

        RoundedButton BB1 = new RoundedButton("Semester 1", 20);
        BB1.setBounds(30, 40, 240, 40);
        BB1.setForeground(Color.black);
        BB1.setBackground(buttonColor);
        p3.add(BB1);

        RoundedButton BB2 = new RoundedButton("Semester 2", 20);
        BB2.setBounds(30, 84, 240, 40);
        BB2.setForeground(Color.black);
        BB2.setBackground(buttonColor);
        p3.add(BB2);

        JLabel lb4 = new JLabel("Year 2");
        lb4.setBounds(130, 130, 100, 30);
        lb4.setFont(new Font(getName(), Font.BOLD, 16));
        lb4.setForeground(Color.black);
        p3.add(lb4);

        RoundedButton BB4 = new RoundedButton("Semester 1", 20);
        BB4.setBounds(30, 165, 240, 40);
        BB4.setForeground(Color.black);
        BB4.setBackground(buttonColor);
        p3.add(BB4);

        RoundedButton BB5 = new RoundedButton("Semester 2", 20);
        BB5.setBounds(30, 209, 240, 40);
        BB5.setForeground(Color.black);
        BB5.setBackground(buttonColor);
        p3.add(BB5);

        JLabel lb5 = new JLabel("Year 3");
        lb5.setBounds(130, 250, 100, 30);
        lb5.setFont(new Font(getName(), Font.BOLD, 16));
        lb5.setForeground(Color.black);
        p3.add(lb5);

        RoundedButton BB6 = new RoundedButton("Semester 1", 20);
        BB6.setBounds(30, 280, 240, 40);
        BB6.setForeground(Color.black);
        BB6.setBackground(buttonColor);
        p3.add(BB6);

        RoundedButton BB7 = new RoundedButton("Semester 2", 20);
        BB7.setBounds(30, 324, 240, 40);
        BB7.setForeground(Color.black);
        BB7.setBackground(buttonColor);
        p3.add(BB7);

        JLabel lb6 = new JLabel("Year 4");
        lb6.setBounds(130, 370, 100, 30);
        lb6.setFont(new Font(getName(), Font.BOLD, 16));
        lb6.setForeground(Color.black);
        p3.add(lb6);

        RoundedButton BB8 = new RoundedButton("Semester 1", 20);
        BB8.setBounds(30, 400, 240, 40);
        BB8.setForeground(Color.black);
        BB8.setBackground(buttonColor);
        p3.add(BB8);

        RoundedButton BB9 = new RoundedButton("Semester 2", 20);
        BB9.setBounds(30, 444, 240, 40);
        BB9.setForeground(Color.black);
        BB9.setBackground(buttonColor);
        p3.add(BB9);

        JLabel lb7 = new JLabel("Year 5");
        lb7.setBounds(130, 490, 100, 30);
        lb7.setFont(new Font(getName(), Font.BOLD, 16));
        lb7.setForeground(Color.black);
        p3.add(lb7);

        RoundedButton BB10 = new RoundedButton("Semester 1", 20);
        BB10.setBounds(30, 520, 240, 40);
        BB10.setForeground(Color.black);
        BB10.setBackground(buttonColor);
        p3.add(BB10);

        RoundedButton BB11 = new RoundedButton("Semester 2", 20);
        BB11.setBounds(30, 564, 240, 40);
        BB11.setForeground(Color.black);
        BB11.setBackground(buttonColor);
        p3.add(BB11);

        RoundedButton b11 = new RoundedButton("Log Out", 20);
        b11.setBounds(1021, 565, 150, 40);
        b11.setForeground(Color.black);
        b11.setFont(new Font("Arial", Font.PLAIN, 18));
        b11.setBackground(buttonColor);
        p4.add(b11);

        JLabel year1 = new JLabel();
        JLabel year2 = new JLabel();
        JLabel year3 = new JLabel();
        JLabel year4 = new JLabel();
        JLabel year5 = new JLabel();

        B1.addActionListener(e -> {
            if (!currentSemester.isEmpty()) {
                AddScore addScore = new AddScore(model, currentSemester, this);
                addScore.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Please select a semester first!", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        // Year 1
        BB1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                p4.remove(year1);
                p4.remove(year2);
                p4.remove(year3);
                p4.remove(year4);
                p4.remove(year5);

                year1.setText("Year 1 - Semester 1");
                year1.setBounds(10, 10, 300, 30);
                year1.setFont(new Font("Arial", Font.BOLD, 24));

                p4.add(year1);
                currentSemester = "Year 1 - Semester 1"; // Update the selected semester
                System.out.println("Selected Semester: " + currentSemester);
                B1.setVisible(true);
                p4.revalidate();
                p4.repaint();

                // Initialize the table structure first
                // initializeDynamicTable();
                // Refresh the table data for the selected semester
                // refreshTableData("Year 1", "Semester 1");
                currentSemester = "Year 1 - Semester 1"; // Update the selected semester
                loadDataAndInitializeTable("Year 1", "Semester 1");
            }
        });

        BB2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                p4.remove(year1);
                p4.remove(year2);
                p4.remove(year3);
                p4.remove(year4);
                p4.remove(year5);

                year1.setText("Year 1 - Semester 2");
                year1.setBounds(10, 10, 300, 30);
                year1.setFont(new Font("Arial", Font.BOLD, 24));

                p4.add(year1);
                currentSemester = "Year 1 - Semester 2"; // Update the selected semester
                System.out.println("Selected Semester: " + currentSemester);
                B1.setVisible(true);
                p4.revalidate();
                p4.repaint();

                // Initialize the table structure first
                // initializeDynamicTable();
                // Refresh the table data for the selected semester
                // refreshTableData("Year 1", "Semester 2");
                currentSemester = "Year 1 - Semester 2"; // Update the selected semester
                loadDataAndInitializeTable("Year 1", "Semester 2");
            }
        });

        // Year 2
        BB4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                p4.remove(year1);
                p4.remove(year2);
                p4.remove(year3);
                p4.remove(year4);
                p4.remove(year5);

                year2.setText("Year 2 - Semester 1");
                year2.setBounds(10, 10, 300, 30);
                year2.setFont(new Font("Arial", Font.BOLD, 24));

                p4.add(year2);
                currentSemester = "Year 2 - Semester 1"; // Update the selected semester
                System.out.println("Selected Semester: " + currentSemester);
                B1.setVisible(true);
                p4.revalidate();
                p4.repaint();

                // Initialize the table structure first
                // initializeDynamicTable();
                // Refresh the table data for the selected semester
                // refreshTableData("Year 2", "Semester 1");
                currentSemester = "Year 2 - Semester 1"; // Update the selected semester
                loadDataAndInitializeTable("Year 2", "Semester 1");
            }
        });

        BB5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                p4.remove(year1);
                p4.remove(year2);
                p4.remove(year3);
                p4.remove(year4);
                p4.remove(year5);

                year2.setText("Year 2 - Semester 2");
                year2.setBounds(10, 10, 300, 30);
                year2.setFont(new Font("Arial", Font.BOLD, 24));

                p4.add(year2);
                currentSemester = "Year 2 - Semester 2"; // Update the selected semester
                System.out.println("Selected Semester: " + currentSemester);
                B1.setVisible(true);
                p4.revalidate();
                p4.repaint();

                // Initialize the table structure first
                // initializeDynamicTable();
                // Refresh the table data for the selected semester
                // refreshTableData("Year 2", "Semester 2");
                currentSemester = "Year 2 - Semester 2"; // Update the selected semester
                loadDataAndInitializeTable("Year 2", "Semester 2");
            }
        });

        // Year 3
        BB6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                p4.remove(year1);
                p4.remove(year2);
                p4.remove(year3);
                p4.remove(year4);
                p4.remove(year5);

                year3.setText("Year 3 - Semester 1");
                year3.setBounds(10, 10, 300, 30);
                year3.setFont(new Font("Arial", Font.BOLD, 24));

                p4.add(year3);
                currentSemester = "Year 3 - Semester 1"; // Update the selected semester
                System.out.println("Selected Semester: " + currentSemester);
                B1.setVisible(true);
                p4.revalidate();
                p4.repaint();

                // Initialize the table structure first
                // initializeDynamicTable();
                // Refresh the table data for the selected semester
                // refreshTableData("Year 3", "Semester 1");
                currentSemester = "Year 3 - Semester 1"; // Update the selected semester
                loadDataAndInitializeTable("Year 3", "Semester 1");
            }
        });

        BB7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                p4.remove(year1);
                p4.remove(year2);
                p4.remove(year3);
                p4.remove(year4);
                p4.remove(year5);

                year3.setText("Year 3 - Semester 2");
                year3.setBounds(10, 10, 300, 30);
                year3.setFont(new Font("Arial", Font.BOLD, 24));

                p4.add(year3);
                currentSemester = "Year 3 - Semester 2"; // Update the selected semester
                System.out.println("Selected Semester: " + currentSemester);
                B1.setVisible(true);
                p4.revalidate();
                p4.repaint();

                // Initialize the table structure first
                // initializeDynamicTable();
                // Refresh the table data for the selected semester
                // refreshTableData("Year 3", "Semester 2");
                currentSemester = "Year 3 - Semester 2"; // Update the selected semester
                loadDataAndInitializeTable("Year 3", "Semester 2");
            }
        });

        // Year 4
        BB8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                p4.remove(year1);
                p4.remove(year2);
                p4.remove(year3);
                p4.remove(year4);
                p4.remove(year5);

                year4.setText("Year 4 - Semester 1");
                year4.setBounds(10, 10, 300, 30);
                year4.setFont(new Font("Arial", Font.BOLD, 24));

                p4.add(year4);
                currentSemester = "Year 4 - Semester 1"; // Update the selected semester
                System.out.println("Selected Semester: " + currentSemester);
                B1.setVisible(true);
                p4.revalidate();
                p4.repaint();

                // Initialize the table structure first
                // initializeDynamicTable();
                // Refresh the table data for the selected semester
                // refreshTableData("Year 4", "Semester 1");
                currentSemester = "Year 4 - Semester 1"; // Update the selected semester
                loadDataAndInitializeTable("Year 4", "Semester 1");
            }
        });

        BB9.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                p4.remove(year1);
                p4.remove(year2);
                p4.remove(year3);
                p4.remove(year4);
                p4.remove(year5);

                year4.setText("Year 4 - Semester 2");
                year4.setBounds(10, 10, 300, 30);
                year4.setFont(new Font("Arial", Font.BOLD, 24));

                p4.add(year4);
                currentSemester = "Year 4 - Semester 2"; // Update the selected semester
                System.out.println("Selected Semester: " + currentSemester);
                B1.setVisible(true);
                p4.revalidate();
                p4.repaint();

                // Initialize the table structure first
                // initializeDynamicTable();
                // Refresh the table data for the selected semester
                // refreshTableData("Year 4", "Semester 2");
                currentSemester = "Year 4 - Semester 2"; // Update the selected semester
                loadDataAndInitializeTable("Year 4", "Semester 2");
            }
        });
        // Year 5
        BB10.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                p4.remove(year1);
                p4.remove(year2);
                p4.remove(year3);
                p4.remove(year4);
                p4.remove(year5);

                year5.setText("Year 5 - Semester 1");
                year5.setBounds(10, 10, 300, 30);
                year5.setFont(new Font("Arial", Font.BOLD, 24));

                p4.add(year5);
                currentSemester = "Year 5 - Semester 1"; // Update the selected semester
                System.out.println("Selected Semester: " + currentSemester);
                B1.setVisible(true);
                p4.revalidate();
                p4.repaint();

                // Initialize the table structure first
                // initializeDynamicTable();
                // Refresh the table data for the selected semester
                // refreshTableData("Year 5", "Semester 1");
                currentSemester = "Year 5 - Semester 1"; // Update the selected semester
                loadDataAndInitializeTable("Year 5", "Semester 1");

            }
        });

        BB11.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                p4.remove(year1);
                p4.remove(year2);
                p4.remove(year3);
                p4.remove(year4);
                p4.remove(year5);

                year5.setText("Year 5 - Semester 2");
                year5.setBounds(10, 10, 300, 30);
                year5.setFont(new Font("Arial", Font.BOLD, 24));

                p4.add(year5);
                currentSemester = "Year 5 - Semester 2"; // Update the selected semester
                System.out.println("Selected Semester: " + currentSemester);
                B1.setVisible(true);
                p4.revalidate();
                p4.repaint();

                // Initialize the table structure first
                // initializeDynamicTable();
                // Refresh the table data for the selected semester
                // refreshTableData("Year 5", "Semester 2");
                currentSemester = "Year 5 - Semester 2"; // Update the selected semester
                loadDataAndInitializeTable("Year 5", "Semester 2");

            }
        });

        b11.addActionListener(e -> {
            new MainSystem().setVisible(true);
            dispose();
        });

    }

    private List<String> getDynamicColumns(String year, String semester) {
        List<String> columnNames = new ArrayList<>();
        columnNames.add("StudentID");
        columnNames.add("StudentName");

        try (MongoClient mongoClient = MongoClients.create(
                "mongodb+srv://Admin:admin12345@adminsystem.kedip.mongodb.net/?retryWrites=true&w=majority")) {
            MongoDatabase database = mongoClient.getDatabase("AdminSystem");
            MongoCollection<Document> collection = database.getCollection("ScoreManagement");

            Set<String> courseNames = new LinkedHashSet<>();
            Document query = new Document("AcademicRecords",
                    new Document("$elemMatch", new Document("Year", Integer.parseInt(year))
                            .append("Semester", Integer.parseInt(semester))));

            for (Document doc : collection.find(query)) {
                List<Document> records = doc.getList("AcademicRecords", Document.class);
                for (Document record : records) {
                    if (record.getInteger("Year").toString().equals(year)
                            && record.getInteger("Semester").toString().equals(semester)) {
                        List<Document> courses = record.getList("Courses", Document.class);
                        for (Document course : courses) {
                            String courseName = course.getString("CourseName") + " Score";
                            courseNames.add(courseName);
                        }
                    }
                }
            }

            columnNames.addAll(courseNames);
            columnNames.add("Total Point");
            columnNames.add("GPA");

            System.out.println("Extracted Columns: " + columnNames);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return columnNames;
    }

    private void loadDataAndInitializeTable(String year, String semester) {
        System.out.println("Loading data for: " + year + " | " + semester);

        p5.removeAll();

        List<String> columns = getDynamicColumns(year, semester);
        System.out.println("Table Columns: " + columns);

        model = new DefaultTableModel(null, columns.toArray());
        jt = new JTable(model);
        JScrollPane sp = new JScrollPane(jt);
        p5.setLayout(new BorderLayout());
        p5.add(sp, BorderLayout.CENTER);

        if (originalModel == null) {
            Vector<String> columnIdentifiers = new Vector<>();
            for (int i = 0; i < model.getColumnCount(); i++) {
                columnIdentifiers.add(model.getColumnName(i));
            }
            originalModel = new DefaultTableModel(model.getDataVector(), columnIdentifiers);
        }

        refreshTableData(year, semester);

        p5.revalidate();
        p5.repaint();
    }

    public void refreshTableData(String year, String semester) {
        if (model == null) {
            System.out.println("⚠️ Table model is not initialized!");
            return;
        }

        model.setRowCount(0);

        try (MongoClient mongoClient = MongoClients.create(
                "mongodb+srv://Admin:admin12345@adminsystem.kedip.mongodb.net/?retryWrites=true&w=majority")) {
            MongoDatabase database = mongoClient.getDatabase("AdminSystem");
            MongoCollection<Document> collection = database.getCollection("ScoreManagement");

            String numericYear = year.replaceAll("\\D+", "");
            String numericSemester = semester.replaceAll("\\D+", "");

            int yearInt = Integer.parseInt(numericYear);
            int semesterInt = Integer.parseInt(numericSemester);

            Document query = new Document("AcademicRecords",
                    new Document("$elemMatch", new Document("Year", yearInt)
                            .append("Semester", semesterInt)));

            System.out.println("🔍 Querying MongoDB for Year: " + yearInt + " | Semester: " + semesterInt);

            List<String> columns = getDynamicColumns(numericYear, numericSemester);
            model.setColumnIdentifiers(columns.toArray());

            FindIterable<Document> results = collection.find(query);

            for (Document doc : results) {
                String studentID = doc.getString("StudentID");
                String studentName = doc.getString("StudentName");

                System.out.println("✅ Found student: " + studentName + " (ID: " + studentID + ")");

                List<Document> academicRecords = doc.getList("AcademicRecords", Document.class);
                for (Document record : academicRecords) {
                    if (record.getInteger("Year") == yearInt &&
                            record.getInteger("Semester") == semesterInt) {

                        List<Document> courses = record.getList("Courses", Document.class);
                        double totalScore = record.getDouble("TotalScore");
                        double gpa = record.getDouble("GPA");

                        System.out.println("Courses found: " + courses);
                        System.out.println("Total Score: " + totalScore + " | GPA: " + gpa);

                        List<Object> rowData = new ArrayList<>();
                        rowData.add(studentID);
                        rowData.add(studentName);

                        Map<String, Double> courseScores = new HashMap<>();
                        for (Document course : courses) {
                            String courseName = course.getString("CourseName") + " Score";
                            Double score = course.getDouble("Score");
                            courseScores.put(courseName, score);
                            System.out.println("Mapped Course: " + courseName + " -> " + score);
                        }

                        for (int i = 2; i < columns.size() - 2; i++) {
                            rowData.add(courseScores.getOrDefault(columns.get(i), 0.0));
                        }

                        rowData.add(totalScore);
                        rowData.add(gpa);

                        model.addRow(rowData.toArray());

                        System.out.println("Added row: " + rowData);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        jt.revalidate();
        jt.repaint();
        p5.revalidate();
        p5.repaint();
    }

    public static void main(String[] args) {
        new ScoreManagement().setVisible(true);
    }
}

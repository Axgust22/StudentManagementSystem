package com.example.CourseManagement;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import com.example.MainSystem;
import com.example.HandleSystem.StuCourse;
import com.example.ScoreManagement.ScoreStudent;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.HashMap;
import java.util.Map;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

public class CourseStudent extends JFrame {
    private static final ArrayList<RoundedButton> newButtons = new ArrayList<>();
    private Set<String> deletedCourses = new HashSet<>();
    private Map<String, RoundedButton> courseButtonMap = new HashMap<>();
    private DefaultTableModel model;
    private DefaultTableModel originalModel;
    private RoundedButton selectedButton = null;
    private JPanel p3;
    private final JLabel lb1;

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
            g2.setColor(getForeground());
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
            g2.dispose();
        }
    }

    public CourseStudent() {
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

        JPanel p4 = new JPanel();
        p4.setBounds(510, 5, 985, 620);
        p4.setBackground(Color.white);
        p4.setBorder(new LineBorder(Color.black, 2));
        p4.setLayout(null);
        p2.add(p4);

        JPanel p5 = new JPanel();
        p5.setBounds(8, 85, 970, 530);
        p5.setBackground(Color.LIGHT_GRAY);
        p5.setBorder(new LineBorder(Color.BLACK, 2));
        p5.setLayout(new BorderLayout());
        p4.add(p5);

        String[] column = {
                "StudentID", "StudentName", "Gender", "Age", "Year", "Major",
                "Academy Year", "Email", "Address", "Phone Number"
        };
        model = new DefaultTableModel(null, column);
        originalModel = model;

        JTable jt = new JTable(model);
        jt.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);

        JScrollPane sp = new JScrollPane(jt);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        p5.setLayout(new BorderLayout());
        p5.add(sp, BorderLayout.CENTER);

        JLabel l1 = new JLabel("STUDENT COURSE");
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

        RoundedButton b2 = new RoundedButton("Student Course", 20);
        b2.setBounds(30, 80, 250, 40);
        b2.setForeground(Color.black);
        b2.setFont(new Font("Arial", Font.PLAIN, 18));
        b2.setBackground(buttonColor);
        p1.add(b2);

        RoundedButton b3 = new RoundedButton("Student Score", 20);
        b3.setBounds(300, 80, 250, 40);
        b3.setForeground(Color.black);
        b3.setFont(new Font("Arial", Font.PLAIN, 18));
        b3.setBackground(buttonColor);
        p1.add(b3);
        b3.addActionListener(e -> {
            new ScoreStudent().setVisible(true);
            dispose();
        });

        JPanel p6 = new JPanel();
        p6.setBounds(5, 5, 500, 90);
        p6.setBackground(Color.white);
        p6.setBorder(new LineBorder(Color.black, 2));
        p6.setLayout(null);
        p2.add(p6);

        p3 = new JPanel();
        p3.setLayout(new BoxLayout(p3, BoxLayout.Y_AXIS));
        p3.setBackground(Color.white);
        p3.setBorder(new LineBorder(Color.black, 2));

        JScrollPane scrollPane = new JScrollPane(p3, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(100, 100, 270, 525);
        p2.add(scrollPane);

        lb1 = new JLabel("Course: ");
        lb1.setBounds(10, 10, 300, 30);
        lb1.setFont(new Font("Arial", Font.PLAIN, 18));
        p4.add(lb1);

        JLabel l3 = new JLabel("Search Course:");
        l3.setBounds(10, 50, 250, 30);
        l3.setFont(new Font("Arial", Font.PLAIN, 18));
        p6.add(l3);

        JTextField t2 = new JTextField();
        t2.setBounds(140, 50, 170, 30);
        t2.setBorder(new LineBorder(Color.BLACK, 2));
        t2.setFont(new Font("Arial", Font.PLAIN, 18));
        p6.add(t2);

        RoundedButton b12 = new RoundedButton("Search Course", 20);
        b12.setBounds(320, 45, 170, 40);
        b12.setForeground(Color.BLACK);
        b12.setFont(new Font("Arial", Font.PLAIN, 18));
        b12.setBackground(buttonColor);
        p6.add(b12);

        b12.addActionListener(e -> {
            String courseQuery = t2.getText().trim();
            if (!courseQuery.isEmpty()) {
                currentStudents.clear();
                model.setRowCount(0);

                try (MongoClient mongoClient = MongoClients.create(
                        "mongodb+srv://Admin:admin12345@adminsystem.kedip.mongodb.net/?retryWrites=true&w=majority")) {
                    MongoDatabase database = mongoClient.getDatabase("AdminSystem");
                    MongoCollection<Document> collection = database.getCollection("CourseManagement");

                    Document course = collection.find(new Document("courseName", courseQuery)).first();

                    if (course != null) {
                        Object studentsObj = course.get("students");
                        if (studentsObj instanceof List<?>) {
                            List<Document> students = (List<Document>) studentsObj;

                            if (!students.isEmpty()) {
                                for (Document studentDoc : students) {
                                    // Extract student details
                                    String studentID = studentDoc.getString("StudentID") != null
                                            ? studentDoc.getString("StudentID")
                                            : "N/A";
                                    String studentName = studentDoc.getString("StudentName") != null
                                            ? studentDoc.getString("StudentName")
                                            : "N/A";
                                    String studentGender = studentDoc.getString("Gender") != null
                                            ? studentDoc.getString("Gender")
                                            : studentDoc.getString("Sex");
                                    if (studentGender == null || studentGender.trim().isEmpty()) {
                                        studentGender = "Not Specified";
                                    }
                                    int studentAge = studentDoc.getInteger("Age", 0);
                                    int studentYear = studentDoc.getInteger("Year", 0);
                                    String studentMajor = studentDoc.getString("Major") != null
                                            ? studentDoc.getString("Major")
                                            : "N/A";
                                    String studentAcademy = studentDoc.getString("AcademyYear") != null
                                            ? studentDoc.getString("AcademyYear")
                                            : "N/A";
                                    String studentEmail = studentDoc.getString("Email") != null
                                            ? studentDoc.getString("Email")
                                            : "N/A";
                                    String studentAddress = studentDoc.getString("Address") != null
                                            ? studentDoc.getString("Address")
                                            : "N/A";
                                    String studentPhoneNumber = studentDoc.getString("PhoneNumber") != null
                                            ? studentDoc.getString("PhoneNumber")
                                            : "N/A";

                                    currentStudents.add(new StuCourse(
                                            studentID, studentName, studentAge, studentGender, studentMajor,
                                            studentYear,
                                            studentAcademy, studentEmail, studentAddress, studentPhoneNumber));

                                    model.addRow(new Object[] {
                                            studentID,
                                            studentName,
                                            studentGender,
                                            studentAge,
                                            studentYear,
                                            studentMajor,
                                            studentAcademy,
                                            studentEmail,
                                            studentAddress,
                                            studentPhoneNumber
                                    });
                                }
                                lb1.setText("Course: " + courseQuery);
                                JOptionPane.showMessageDialog(p4, "Students loaded successfully!", "Success",
                                        JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(p4, "No students found for the course.", "Info",
                                        JOptionPane.INFORMATION_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(p4, "Unexpected data format for students.", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(p4, "Course not found.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(p4, "Error searching for course: " + ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(p4, "Course name cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JLabel lb3 = new JLabel("List of Courses");
        lb3.setBounds(150, 10, 300, 30);
        lb3.setFont(new Font(getName(), Font.BOLD, 20));
        lb3.setForeground(Color.black);
        p6.add(lb3);

        JLabel l2 = new JLabel("Search Student Course by ID:");
        l2.setBounds(10, 45, 250, 30);
        l2.setFont(new Font("Arial", Font.PLAIN, 18));
        p4.add(l2);

        JTextField t1 = new JTextField();
        t1.setBounds(260, 45, 220, 30);
        t1.setBorder(new LineBorder(Color.BLACK, 2));
        t1.setFont(new Font("Arial", Font.PLAIN, 18));
        p4.add(t1);

        RoundedButton b4 = new RoundedButton("Search", 20);
        b4.setBounds(495, 40, 150, 40);
        b4.setForeground(Color.BLACK);
        b4.setFont(new Font("Arial", Font.PLAIN, 18));
        b4.setBackground(buttonColor);
        p4.add(b4);
        b4.addActionListener(e -> {
            String searchQuery = t1.getText().trim();
            if (!searchQuery.isEmpty()) {
                DefaultTableModel tempModel = new DefaultTableModel(null, column);
                boolean found = false;

                for (StuCourse student : currentStudents) {
                    if ((student.getStudentName() != null
                            && student.getStudentName().toLowerCase().contains(searchQuery.toLowerCase())) ||
                            (student.getStudentID() != null && student.getStudentID().equalsIgnoreCase(searchQuery))) {

                        String courseName = findCourseByStudentID(student.getStudentID());
                        tempModel.addRow(new Object[] {
                                student.getStudentID(),
                                student.getStudentName(),
                                student.getStudentGender(),
                                student.getStudentAge(),
                                student.getStudentYear(),
                                student.getStudentMajor(),
                                student.getStudentAcademy(),
                                student.getStudentEmail(),
                                student.getStudentAddress(),
                                student.getStudentPhoneNumber(),
                                courseName
                        });
                        found = true;
                    }
                }

                if (!found) {
                    JOptionPane.showMessageDialog(p4, "No matching students found!");
                } else {
                    jt.setModel(tempModel);
                    jt.revalidate();
                    jt.repaint();
                }
            } else {
                JOptionPane.showMessageDialog(p4, "Search field is empty!");
            }
        });

        RoundedButton b5 = new RoundedButton("Back", 20);
        b5.setBounds(650, 40, 150, 40);
        b5.setForeground(Color.black);
        b5.setFont(new Font("Arial", Font.PLAIN, 18));
        b5.setBackground(Color.WHITE);
        p4.add(b5);
        b5.addActionListener(e -> {
            jt.setModel(originalModel);
            t1.setText("");
            JOptionPane.showMessageDialog(this, "Table restored to original state!");
        });

        RoundedButton b11 = new RoundedButton("Log Out", 20);
        b11.setBounds(810, 40, 150, 40);
        b11.setForeground(Color.black);
        b11.setFont(new Font("Arial", Font.PLAIN, 18));
        b11.setBackground(buttonColor);
        p4.add(b11);
        b11.addActionListener(e -> {
            new MainSystem().setVisible(true);
            dispose();
        });

        loadCoursesFromDatabase();
    }

    private void loadCoursesFromDatabase() {
        try (MongoClient mongoClient = MongoClients.create(
                "mongodb+srv://Admin:admin12345@adminsystem.kedip.mongodb.net/?retryWrites=true&w=majority")) {
            MongoDatabase database = mongoClient.getDatabase("AdminSystem");
            MongoCollection<Document> collection = database.getCollection("CourseManagement");

            p3.removeAll();
            courseButtonMap.clear();

            Color buttonColor = Color.decode("#8FB6FF");

            for (Document doc : collection.find()) {
                String courseName = doc.getString("courseName");

                // Create a course button
                RoundedButton courseButton = new RoundedButton(courseName, 20);
                courseButton.setPreferredSize(new Dimension(250, 40));
                courseButton.setMaximumSize(new Dimension(250, 40));
                courseButton.setForeground(Color.BLACK);
                courseButton.setBackground(buttonColor);

                courseButtonMap.put(courseName, courseButton);
                p3.add(courseButton);
                p3.revalidate();
                p3.repaint();

                courseButton.addActionListener(e -> {
                    selectedButton = courseButton;
                    lb1.setText("Course: " + courseName);
                    loadStudentsForCourse(courseName);
                });
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Failed to load courses: " + ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private List<StuCourse> currentStudents = new ArrayList<>();

    private void loadStudentsForCourse(String courseName) {
        currentStudents.clear();
        model.setRowCount(0);

        try (MongoClient mongoClient = MongoClients.create(
                "mongodb+srv://Admin:admin12345@adminsystem.kedip.mongodb.net/?retryWrites=true&w=majority")) {
            MongoDatabase database = mongoClient.getDatabase("AdminSystem");
            MongoCollection<Document> collection = database.getCollection("CourseManagement");

            // Find the course in the database
            Document course = collection.find(new Document("courseName", courseName)).first();
            if (course == null) {
                JOptionPane.showMessageDialog(this, "Course not found in the database.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            Object studentsObj = course.get("students");
            if (studentsObj instanceof List<?>) {
                List<Document> students = (List<Document>) studentsObj;

                if (!students.isEmpty()) {
                    for (Document studentDoc : students) {
                        String studentID = studentDoc.getString("StudentID") != null
                                ? studentDoc.getString("StudentID")
                                : "N/A";
                        String studentName = studentDoc.getString("StudentName") != null
                                ? studentDoc.getString("StudentName")
                                : "N/A";
                        String studentGender = studentDoc.getString("Gender") != null
                                ? studentDoc.getString("Gender")
                                : studentDoc.getString("Sex");
                        if (studentGender == null || studentGender.trim().isEmpty()) {
                            studentGender = "Not Specified";
                        }
                        int studentAge = studentDoc.getInteger("Age", 0);

                        int studentYear = studentDoc.getInteger("Year", 0);
                        String studentMajor = studentDoc.getString("Major") != null
                                ? studentDoc.getString("Major")
                                : "N/A";
                        String studentAcademy = studentDoc.getString("AcademyYear") != null
                                ? studentDoc.getString("AcademyYear")
                                : "N/A";
                        String studentEmail = studentDoc.getString("Email") != null
                                ? studentDoc.getString("Email")
                                : "N/A";
                        String studentAddress = studentDoc.getString("Address") != null
                                ? studentDoc.getString("Address")
                                : "N/A";
                        String studentPhoneNumber = studentDoc.getString("PhoneNumber") != null
                                ? studentDoc.getString("PhoneNumber")
                                : "N/A";

                        System.out.println("Student Details: " +
                                "Student ID: " + studentID +
                                ", Name: " + studentName +
                                ", Gender: " + studentGender);

                        currentStudents.add(new StuCourse(
                                studentID, studentName, studentAge, studentGender,
                                studentMajor, studentYear, studentAcademy, studentEmail,
                                studentAddress, studentPhoneNumber));
                        model.addRow(new Object[] {
                                studentID,
                                studentName,
                                studentGender,
                                studentAge,
                                studentYear,
                                studentMajor,
                                studentAcademy,
                                studentEmail,
                                studentAddress,
                                studentPhoneNumber
                        });
                    }
                } else {
                    JOptionPane.showMessageDialog(this,
                            "No students available for the selected course.", "Info",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Unexpected data format for students.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load students: " + ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private String findCourseByStudentID(String studentID) {
        try (MongoClient mongoClient = MongoClients.create(
                "mongodb+srv://Admin:admin12345@adminsystem.kedip.mongodb.net/?retryWrites=true&w=majority")) {
            MongoDatabase database = mongoClient.getDatabase("AdminSystem");
            MongoCollection<Document> collection = database.getCollection("CourseManagement");

            Document course = collection.find(new Document("students", studentID)).first();

            if (course != null) {
                return course.getString("courseName");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "Unknown";
    }

    public static void main(String[] args) {
        new CourseStudent().setVisible(true);
    }
}

// RoundedButton b6 = new RoundedButton("", 20);
// p6.add(b6);

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

// String[] courseArray = courseNames.split("\\s*,\\s*");
// List<String> trimmedCourses = Arrays.stream(courseArray)
// .filter(name -> !name.trim().isEmpty())
// .map(String::trim)
// .toList();

// if (trimmedCourses.isEmpty()) {
// JOptionPane.showMessageDialog(null,
// "No valid course names entered.", "Error",
// JOptionPane.ERROR_MESSAGE);
// return;
// }

// List<Document> existingCourses = collection.find(
// new Document("courseName",
// new Document("$in", trimmedCourses)))
// .into(new ArrayList<>());

// Set<String> existingCourseNames = existingCourses.stream()
// .map(doc -> doc.getString("courseName"))
// .collect(Collectors.toSet());

// List<String> duplicateCourses = new ArrayList<>();
// List<String> addedCourses = new ArrayList<>();

// for (String courseName : trimmedCourses) {
// if (existingCourseNames.contains(courseName)) {
// duplicateCourses.add(courseName);
// continue;
// }

// Document course = new Document("courseName", courseName)
// .append("students", new ArrayList<>());
// collection.insertOne(course);
// addedCourses.add(courseName);

// RoundedButton courseButton = new RoundedButton(courseName, 20);
// courseButton.setPreferredSize(new Dimension(250, 40));
// courseButton.setMaximumSize(new Dimension(250, 40));
// courseButton.setForeground(Color.BLACK);
// courseButton.setBackground(Color.decode("#8FB6FF"));
// newButtons.add(courseButton);
// p3.add(courseButton);
// p3.revalidate();
// p3.repaint();

// courseButton.addActionListener(e2 -> {
// selectedButton = courseButton;
// lb1.setText("Course: " + courseButton.getText());
// lb1.setToolTipText("Course: " + courseButton.getText());
// clearAndReloadTable(courseButton.getText());
// });

// selectedButton = courseButton;
// lb1.setText("Course: " + courseName);
// lb1.setToolTipText("Course: " + courseName);
// clearAndReloadTable(courseName);
// }

// StringBuilder message = new StringBuilder();
// if (!addedCourses.isEmpty()) {
// message.append("Courses added successfully:\n")
// .append(String.join(", ", addedCourses))
// .append("\n");
// }
// if (!duplicateCourses.isEmpty()) {
// message.append("Duplicate course names ignored:\n")
// .append(String.join(", ", duplicateCourses));
// }
// JOptionPane.showMessageDialog(null, message.toString(), "Result",
// JOptionPane.INFORMATION_MESSAGE);
// } catch (Exception ex) {
// ex.printStackTrace();
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

// private void clearAndReloadTable(String courseName) {
// model.setRowCount(0);
// loadStudentsForCourse(courseName);
// }
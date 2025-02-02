package com.example.ScoreManagement;

import javax.swing.*;

import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.example.MainSystem;
import com.example.CourseManagement.CourseStudent;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
import java.util.HashMap;
import java.util.Map;

public class ScoreStudent extends JFrame {
    private static final ArrayList<RoundedButton> newButtons = new ArrayList<>();
    private Set<String> deletedCourses = new HashSet<>(); // Track deleted courses
    private int courseCounter = 1; // Track the course number
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
    private JPanel p4; // Declare p4 as a field
    private JButton B1; // Declare B1 as a field

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

            // Fill the button background
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);

            // Draw the button text
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

    public ScoreStudent() {
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
        p5.setBounds(8, 85, 1170, 530);
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

        RoundedButton b2 = new RoundedButton("Student Course", 20);
        b2.setBounds(30, 80, 250, 40);
        b2.setForeground(Color.black);
        b2.setFont(new Font("Arial", Font.PLAIN, 18));
        b2.setBackground(buttonColor);
        p1.add(b2);

        b2.addActionListener(e -> {
            new CourseStudent().setVisible(true);
            dispose();
        });

        RoundedButton b3 = new RoundedButton("Student Score", 20);
        b3.setBounds(300, 80, 250, 40);
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
        b5.setFont(new Font("Arial", Font.PLAIN, 18));
        b5.setBackground(Color.WHITE); // Button background color
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
        RoundedButton b11 = new RoundedButton("Log Out", 20);
        b11.setBounds(800, 45, 150, 30);
        b11.setForeground(Color.black);
        b11.setFont(new Font("Arial", Font.PLAIN, 18));
        b11.setBackground(buttonColor);
        p4.add(b11);

        RoundedButton b4 = new RoundedButton("Search", 20);
        b4.setBounds(450, 45, 150, 30);
        b4.setForeground(Color.BLACK);
        b4.setFont(new Font("Arial", Font.PLAIN, 18));
        b4.setBackground(buttonColor);
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
                        List<Document> academicRecords = student.getList("AcademicRecords", Document.class);

                        Set<String> courseSet = new LinkedHashSet<>();
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
                        columnNames.addAll(courseSet);
                        columnNames.add("Total Point");
                        columnNames.add("GPA");

                        DefaultTableModel tempModel = new DefaultTableModel(null, columnNames.toArray());

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

        JLabel year1 = new JLabel();
        JLabel year2 = new JLabel();
        JLabel year3 = new JLabel();
        JLabel year4 = new JLabel();
        JLabel year5 = new JLabel();

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
                currentSemester = "Year 1 - Semester 1";
                System.out.println("Selected Semester: " + currentSemester);
                p4.revalidate();
                p4.repaint();

                currentSemester = "Year 1 - Semester 1";
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
                currentSemester = "Year 1 - Semester 2";
                System.out.println("Selected Semester: " + currentSemester);
                p4.revalidate();
                p4.repaint();

                currentSemester = "Year 1 - Semester 2";
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
                currentSemester = "Year 2 - Semester 1";
                System.out.println("Selected Semester: " + currentSemester);
                p4.revalidate();
                p4.repaint();

                currentSemester = "Year 2 - Semester 1";
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
                currentSemester = "Year 2 - Semester 2";
                System.out.println("Selected Semester: " + currentSemester);
                p4.revalidate();
                p4.repaint();

                currentSemester = "Year 2 - Semester 2";
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
                currentSemester = "Year 3 - Semester 1";
                System.out.println("Selected Semester: " + currentSemester);
                p4.revalidate();
                p4.repaint();

                currentSemester = "Year 3 - Semester 1";
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
                currentSemester = "Year 3 - Semester 2";
                System.out.println("Selected Semester: " + currentSemester);
                p4.revalidate();
                p4.repaint();

                currentSemester = "Year 3 - Semester 2";
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
                currentSemester = "Year 4 - Semester 1";
                System.out.println("Selected Semester: " + currentSemester);
                p4.revalidate();
                p4.repaint();

                currentSemester = "Year 4 - Semester 1";
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
                currentSemester = "Year 4 - Semester 2";
                System.out.println("Selected Semester: " + currentSemester);
                p4.revalidate();
                p4.repaint();

                currentSemester = "Year 4 - Semester 2";
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
                currentSemester = "Year 5 - Semester 1";
                System.out.println("Selected Semester: " + currentSemester);
                p4.revalidate();
                p4.repaint();

                currentSemester = "Year 5 - Semester 1";
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
                currentSemester = "Year 5 - Semester 2";
                System.out.println("Selected Semester: " + currentSemester);
                p4.revalidate();
                p4.repaint();

                currentSemester = "Year 5 - Semester 2";
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

            System.out.println("📊 Extracted Columns: " + columnNames);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return columnNames;
    }

    private void loadDataAndInitializeTable(String year, String semester) {
        System.out.println("🔍 Loading data for: " + year + " | " + semester);

        p5.removeAll();

        List<String> columns = getDynamicColumns(year, semester);
        System.out.println("📊 Table Columns: " + columns);

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

                        System.out.println("📊 Added row: " + rowData);
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
        new ScoreStudent().setVisible(true);
    }
}
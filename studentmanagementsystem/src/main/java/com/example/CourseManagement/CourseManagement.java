package com.example.CourseManagement;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import com.example.MainSystem;
import com.example.HandleSystem.Course;
import com.example.HandleSystem.StuCourse;
import com.example.ScoreManagement.ScoreManagement;
import com.example.StudentManagement.StudentManagementSystem;
import com.mongodb.client.FindIterable;
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
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;

public class CourseManagement extends JFrame {
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

        public CourseManagement() {
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
                p4.setBounds(310, 5, 1185, 620);
                p4.setBackground(Color.white);
                p4.setBorder(new LineBorder(Color.black, 2));
                p4.setLayout(null);
                p2.add(p4);

                JPanel p5 = new JPanel();
                p5.setBounds(8, 85, 1170, 470);
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

                // Set bounds and add to the panel
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
                b3.addActionListener(e -> {
                        new ScoreManagement().setVisible(true);
                        dispose();
                });

                JLabel l2 = new JLabel("Search Student Course:");
                l2.setBounds(10, 45, 200, 30);
                l2.setFont(new Font("Arial", Font.PLAIN, 18));
                p4.add(l2);

                JPanel p6 = new JPanel();
                p6.setBounds(5, 5, 300, 90);
                p6.setBackground(Color.white);
                p6.setBorder(new LineBorder(Color.black, 2));
                p6.setLayout(null);
                p2.add(p6);

                p3 = new JPanel();
                p3.setLayout(new BoxLayout(p3, BoxLayout.Y_AXIS));
                p3.setBackground(Color.white);
                p3.setBorder(new LineBorder(Color.black, 2));

                // Wrap p3 in a JScrollPane
                JScrollPane scrollPane = new JScrollPane(p3, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                scrollPane.setBounds(20, 100, 275, 525);
                p2.add(scrollPane);

                // Add multiple course
                lb1 = new JLabel("Course: ");
                lb1.setBounds(10, 10, 300, 30);
                lb1.setFont(new Font("Arial", Font.PLAIN, 18));
                p4.add(lb1);

                RoundedButton b6 = new RoundedButton("New", 20);
                b6.setBounds(5, 45, 85, 40);
                b6.setPreferredSize(new Dimension(85, 40));
                b6.setForeground(Color.black);
                b6.setBackground(buttonColor);
                p6.add(b6);

                // Add course button
                b6.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                String courseNames = JOptionPane.showInputDialog(null,
                                                "Enter course names separated by commas:");

                                if (courseNames != null && !courseNames.trim().isEmpty()) {
                                        try (MongoClient mongoClient = MongoClients.create(
                                                        "mongodb+srv://Admin:admin12345@adminsystem.kedip.mongodb.net/?retryWrites=true&w=majority")) {

                                                MongoDatabase database = mongoClient.getDatabase("AdminSystem");
                                                MongoCollection<Document> collection = database
                                                                .getCollection("CourseManagement");

                                                String[] courseArray = courseNames.split("\\s*,\\s*");
                                                List<String> trimmedCourses = Arrays.stream(courseArray)
                                                                .map(String::trim)
                                                                .filter(name -> !name.isEmpty())
                                                                .distinct()
                                                                .toList();

                                                if (trimmedCourses.isEmpty()) {
                                                        JOptionPane.showMessageDialog(null,
                                                                        "No valid course names entered.",
                                                                        "Error", JOptionPane.ERROR_MESSAGE);
                                                        return;
                                                }

                                                List<Document> existingCourses = collection.find(
                                                                new Document("courseName",
                                                                                new Document("$in", trimmedCourses)))
                                                                .into(new ArrayList<>());

                                                Set<String> existingCourseNames = existingCourses.stream()
                                                                .map(doc -> doc.getString("courseName"))
                                                                .collect(Collectors.toSet());

                                                List<String> duplicateCourses = new ArrayList<>();
                                                List<String> addedCourses = new ArrayList<>();

                                                for (String name : trimmedCourses) {
                                                        if (existingCourseNames.contains(name)) {
                                                                duplicateCourses.add(name);
                                                                continue;
                                                        }

                                                        Course newCourse = new Course("");
                                                        newCourse.setCourseName(name);

                                                        Document courseDocument = new Document("courseName",
                                                                        newCourse.getCourseName())
                                                                        .append("students", new ArrayList<>());
                                                        collection.insertOne(courseDocument);
                                                        addedCourses.add(newCourse.getCourseName());

                                                        RoundedButton courseButton = new RoundedButton(
                                                                        newCourse.getCourseName(), 20);
                                                        courseButton.setPreferredSize(new Dimension(250, 40));
                                                        courseButton.setMaximumSize(new Dimension(250, 40));
                                                        courseButton.setForeground(Color.BLACK);
                                                        courseButton.setBackground(Color.decode("#8FB6FF"));

                                                        newButtons.add(courseButton);
                                                        p3.add(courseButton);
                                                        p3.revalidate();
                                                        p3.repaint();

                                                        courseButton.addActionListener(e2 -> {
                                                                selectedButton = courseButton;
                                                                lb1.setText("Course: " + courseButton.getText());
                                                                lb1.setToolTipText("Course: " + courseButton.getText());
                                                                clearAndReloadTable(courseButton.getText());
                                                        });

                                                        selectedButton = courseButton;
                                                        lb1.setText("Course: " + newCourse.getCourseName());
                                                        lb1.setToolTipText("Course: " + newCourse.getCourseName());
                                                        clearAndReloadTable(newCourse.getCourseName());
                                                }

                                                StringBuilder message = new StringBuilder();
                                                if (!addedCourses.isEmpty()) {
                                                        message.append("Courses added successfully:\n")
                                                                        .append(String.join(", ", addedCourses))
                                                                        .append("\n");
                                                }
                                                if (!duplicateCourses.isEmpty()) {
                                                        message.append("Duplicate course names ignored:\n")
                                                                        .append(String.join(", ", duplicateCourses));
                                                }

                                                JOptionPane.showMessageDialog(null, message.toString(),
                                                                "Result", JOptionPane.INFORMATION_MESSAGE);

                                        } catch (Exception ex) {
                                                ex.printStackTrace();
                                                JOptionPane.showMessageDialog(null,
                                                                "Failed to save to database: " + ex.getMessage(),
                                                                "Error", JOptionPane.ERROR_MESSAGE);
                                        }
                                } else {
                                        JOptionPane.showMessageDialog(null, "Course names cannot be empty!",
                                                        "Error", JOptionPane.ERROR_MESSAGE);
                                }
                        }
                });

                RoundedButton b7 = new RoundedButton("Delete", 20);
                b7.setBounds(108, 45, 85, 40);
                b7.setPreferredSize(new Dimension(85, 40));
                b7.setForeground(Color.black);
                b7.setBackground(buttonColor);
                p6.add(b7);

                // Delete course button
                b7.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                if (selectedButton != null) {
                                        try (MongoClient mongoClient = MongoClients.create(
                                                        "mongodb+srv://Admin:admin12345@adminsystem.kedip.mongodb.net/?retryWrites=true&w=majority")) {

                                                MongoDatabase database = mongoClient.getDatabase("AdminSystem");
                                                MongoCollection<Document> collection = database
                                                                .getCollection("CourseManagement");

                                                Course courseToDelete = new Course("");
                                                courseToDelete.setCourseName(selectedButton.getText());

                                                collection.deleteOne(new Document("courseName",
                                                                courseToDelete.getCourseName()));

                                                newButtons.remove(selectedButton);
                                                p3.remove(selectedButton);
                                                selectedButton = null;
                                                lb1.setText("Course: None");
                                                model.setRowCount(0);
                                                currentStudents.clear();

                                                p3.revalidate();
                                                p3.repaint();

                                                JOptionPane.showMessageDialog(null,
                                                                "Course '" + courseToDelete.getCourseName()
                                                                                + "' deleted successfully!");

                                        } catch (Exception ex) {
                                                JOptionPane.showMessageDialog(null,
                                                                "Failed to delete from database: " + ex.getMessage(),
                                                                "Error", JOptionPane.ERROR_MESSAGE);
                                        }
                                } else {
                                        JOptionPane.showMessageDialog(null,
                                                        "Please select a course to delete!",
                                                        "Error", JOptionPane.ERROR_MESSAGE);
                                }
                        }
                });

                RoundedButton bb7 = new RoundedButton("Update", 20);
                bb7.setBounds(210, 45, 85, 40);
                bb7.setPreferredSize(new Dimension(85, 40));
                bb7.setForeground(Color.black);
                bb7.setBackground(buttonColor);
                p6.add(bb7);

                // Update course button
                bb7.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                if (selectedButton != null) {
                                        String newName = JOptionPane.showInputDialog(null,
                                                        "Enter new name for the course:", selectedButton.getText());

                                        if (newName != null && !newName.trim().isEmpty()) {
                                                String oldName = selectedButton.getText();

                                                try (MongoClient mongoClient = MongoClients.create(
                                                                "mongodb+srv://Admin:admin12345@adminsystem.kedip.mongodb.net/?retryWrites=true&w=majority")) {

                                                        MongoDatabase database = mongoClient.getDatabase("AdminSystem");
                                                        MongoCollection<Document> collection = database
                                                                        .getCollection("CourseManagement");

                                                        Course courseToUpdate = new Course("");
                                                        courseToUpdate.setCourseName(newName.trim());

                                                        collection.updateOne(new Document("courseName", oldName),
                                                                        new Document("$set", new Document("courseName",
                                                                                        courseToUpdate.getCourseName())));

                                                        selectedButton.setText(courseToUpdate.getCourseName());
                                                        lb1.setText("Course: " + courseToUpdate.getCourseName());
                                                        p3.revalidate();
                                                        p3.repaint();

                                                        JOptionPane.showMessageDialog(null,
                                                                        "Course updated successfully!");

                                                } catch (Exception ex) {
                                                        JOptionPane.showMessageDialog(null,
                                                                        "Failed to update course: " + ex.getMessage(),
                                                                        "Error", JOptionPane.ERROR_MESSAGE);
                                                }
                                        } else {
                                                JOptionPane.showMessageDialog(null,
                                                                "Course name cannot be empty!",
                                                                "Error", JOptionPane.ERROR_MESSAGE);
                                        }
                                } else {
                                        JOptionPane.showMessageDialog(null,
                                                        "Please select a course to update first!",
                                                        "Error", JOptionPane.ERROR_MESSAGE);
                                }
                        }
                });

                RoundedButton B1 = new RoundedButton("Add Students", 20);
                B1.setBounds(1030, 10, 150, 40);
                B1.setForeground(Color.black);
                B1.setBackground(buttonColor);
                p4.add(B1);
                B1.addActionListener(e -> {
                        if (selectedButton == null) {
                                JOptionPane.showMessageDialog(null, "Please select a course first!", "Error",
                                                JOptionPane.ERROR_MESSAGE);
                                return;
                        }

                        String selectedCourseName = selectedButton.getText();
                        AddCourse addCourseWindow = new AddCourse(selectedCourseName);
                        addCourseWindow.setVisible(true);
                        addCourseWindow.addWindowListener(new java.awt.event.WindowAdapter() {
                                @Override
                                public void windowClosed(java.awt.event.WindowEvent e) {
                                        loadStudentsForCourse(selectedCourseName);
                                }
                        });
                });

                JLabel lb3 = new JLabel("Add Course");
                lb3.setBounds(90, 5, 300, 30);
                lb3.setFont(new Font(getName(), Font.BOLD, 20));
                lb3.setForeground(Color.black);
                p6.add(lb3);

                JTextField t1 = new JTextField();
                t1.setBounds(210, 45, 220, 30);
                t1.setBorder(new LineBorder(Color.BLACK, 2));
                t1.setFont(new Font("Arial", Font.PLAIN, 18));
                p4.add(t1);

                RoundedButton b4 = new RoundedButton("Search", 20);
                b4.setBounds(450, 45, 150, 30);
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
                                        if (student.getStudentName().contains(searchQuery)
                                                        || student.getStudentID().equalsIgnoreCase(searchQuery)) {
                                                tempModel.addRow(new Object[] {
                                                                student.getStudentID(),
                                                                student.getStudentName(),
                                                                student.getStudentGender(),
                                                                student.getStudentAge(),
                                                                student.getStudentYear(),
                                                                student.getStudentAcademy(),
                                                                student.getStudentMajor()
                                                });
                                                found = true;
                                        }
                                }

                                if (!found) {
                                        try (MongoClient mongoClient = MongoClients.create(
                                                        "mongodb+srv://Admin:admin12345@adminsystem.kedip.mongodb.net/?retryWrites=true&w=majority")) {
                                                MongoDatabase database = mongoClient.getDatabase("AdminSystem");
                                                MongoCollection<Document> studentCollection = database
                                                                .getCollection("StudentManagement");

                                                Document query = new Document("$or", List.of(
                                                                new Document("Name", new Document("$regex", searchQuery)
                                                                                .append("$options", "i")),
                                                                new Document("ID", searchQuery)));
                                                FindIterable<Document> students = studentCollection.find(query);

                                                for (Document doc : students) {
                                                        tempModel.addRow(new Object[] {
                                                                        doc.getString("ID"),
                                                                        doc.getString("Name"),
                                                                        doc.getString("Sex"),
                                                                        doc.getInteger("Age"),
                                                                        doc.getInteger("Year"),
                                                                        doc.getString("AcademyYear"),
                                                                        doc.getString("Major")
                                                        });
                                                        found = true;
                                                }
                                        } catch (Exception ex) {
                                                ex.printStackTrace();
                                                JOptionPane.showMessageDialog(this,
                                                                "Failed to search student in MongoDB: "
                                                                                + ex.getMessage(),
                                                                "Error",
                                                                JOptionPane.ERROR_MESSAGE);
                                        }
                                }

                                if (!found) {
                                        JOptionPane.showMessageDialog(this, "No matching students found!");
                                } else {
                                        jt.setModel(tempModel);
                                }
                        } else {
                                JOptionPane.showMessageDialog(this, "Search field is empty!");
                        }
                });

                RoundedButton b5 = new RoundedButton("Back", 20);
                b5.setBounds(630, 45, 150, 30);
                b5.setForeground(Color.black);
                b5.setFont(new Font("Arial", Font.PLAIN, 18));
                b5.setBackground(Color.WHITE);
                p4.add(b5);
                b5.addActionListener(e -> {
                        jt.setModel(originalModel);
                        t1.setText("");
                        JOptionPane.showMessageDialog(this, "Table restored to original state!");
                });

                RoundedButton b8 = new RoundedButton("Update", 20);
                b8.setBounds(80, 565, 150, 40);
                b8.setForeground(Color.black);
                b8.setFont(new Font("Arial", Font.PLAIN, 18));
                b8.setBackground(buttonColor);
                p4.add(b8);

                // Update student button
                b8.addActionListener(e -> {
                        int selectedRow = jt.getSelectedRow();
                        if (selectedRow != -1) {
                                String studentID = model.getValueAt(selectedRow, 0).toString();

                                StuCourse studentToUpdate = null;
                                for (StuCourse student : currentStudents) {
                                        if (student.getStudentID().equals(studentID)) {
                                                studentToUpdate = student;
                                                break;
                                        }
                                }

                                if (studentToUpdate != null) {
                                        JTextField SI = new JTextField(studentToUpdate.getStudentID());
                                        JTextField SN = new JTextField(studentToUpdate.getStudentName());
                                        JTextField G = new JTextField(studentToUpdate.getStudentGender());
                                        JTextField A = new JTextField(String.valueOf(studentToUpdate.getStudentAge()));
                                        JTextField Y = new JTextField(String.valueOf(studentToUpdate.getStudentYear()));
                                        JTextField M = new JTextField(studentToUpdate.getStudentMajor());
                                        JTextField AC = new JTextField(studentToUpdate.getStudentAcademy());
                                        JTextField E = new JTextField(studentToUpdate.getStudentEmail());
                                        JTextField AD = new JTextField(studentToUpdate.getStudentAddress());
                                        JTextField PN = new JTextField(studentToUpdate.getStudentPhoneNumber());

                                        JPanel panel = new JPanel(new GridLayout(10, 2));
                                        panel.add(new JLabel("Student ID:"));
                                        panel.add(SI);
                                        panel.add(new JLabel("Student Name:"));
                                        panel.add(SN);
                                        panel.add(new JLabel("Gender:"));
                                        panel.add(G);
                                        panel.add(new JLabel("Age:"));
                                        panel.add(A);
                                        panel.add(new JLabel("Year:"));
                                        panel.add(Y);
                                        panel.add(new JLabel("Major:"));
                                        panel.add(M);
                                        panel.add(new JLabel("Academy:"));
                                        panel.add(AC);
                                        panel.add(new JLabel("Email:"));
                                        panel.add(E);
                                        panel.add(new JLabel("Address:"));
                                        panel.add(AD);
                                        panel.add(new JLabel("Phone Number:"));
                                        panel.add(PN);

                                        int option = JOptionPane.showConfirmDialog(null, panel, "Update Student",
                                                        JOptionPane.OK_CANCEL_OPTION);
                                        if (option == JOptionPane.OK_OPTION) {
                                                studentToUpdate.setStudentName(SN.getText());
                                                studentToUpdate.setStudentID(SI.getText());
                                                studentToUpdate.setStudentGender(G.getText());
                                                studentToUpdate.setStudentAge(Integer.parseInt(A.getText()));
                                                studentToUpdate.setStudentYear(Integer.parseInt(Y.getText()));
                                                studentToUpdate.setStudentMajor(M.getText());
                                                studentToUpdate.setStudentAcademy(AC.getText());
                                                studentToUpdate.setStudentEmail(E.getText());
                                                studentToUpdate.setStudentAddress(AD.getText());
                                                studentToUpdate.setStudentPhoneNumber(PN.getText());

                                                try (MongoClient mongoClient = MongoClients.create(
                                                                "mongodb+srv://Admin:admin12345@adminsystem.kedip.mongodb.net/?retryWrites=true&w=majority")) {
                                                        MongoDatabase database = mongoClient.getDatabase("AdminSystem");
                                                        MongoCollection<Document> collection = database
                                                                        .getCollection("CourseManagement");

                                                        Document query = new Document("courseName",
                                                                        selectedButton.getText())
                                                                        .append("students.StudentID", studentID);

                                                        Document updatedStudent = new Document()
                                                                        .append("StudentID",
                                                                                        studentToUpdate.getStudentID())
                                                                        .append("StudentName", studentToUpdate
                                                                                        .getStudentName())
                                                                        .append("Gender", studentToUpdate
                                                                                        .getStudentGender())
                                                                        .append("Age", studentToUpdate.getStudentAge())
                                                                        .append("Year", studentToUpdate
                                                                                        .getStudentYear())
                                                                        .append("AcademyYear", studentToUpdate
                                                                                        .getStudentAcademy())
                                                                        .append("Major", studentToUpdate
                                                                                        .getStudentMajor())
                                                                        .append("Email", studentToUpdate
                                                                                        .getStudentEmail())
                                                                        .append("Address", studentToUpdate
                                                                                        .getStudentAddress())
                                                                        .append("PhoneNumber", studentToUpdate
                                                                                        .getStudentPhoneNumber());

                                                        collection.updateOne(query, new Document("$set",
                                                                        new Document("students.$", updatedStudent)));

                                                        model.setValueAt(studentToUpdate.getStudentID(), selectedRow,
                                                                        0);
                                                        model.setValueAt(studentToUpdate.getStudentName(), selectedRow,
                                                                        1);
                                                        model.setValueAt(studentToUpdate.getStudentGender(),
                                                                        selectedRow, 2);
                                                        model.setValueAt(studentToUpdate.getStudentAge(), selectedRow,
                                                                        3);
                                                        model.setValueAt(studentToUpdate.getStudentYear(), selectedRow,
                                                                        4);
                                                        model.setValueAt(studentToUpdate.getStudentMajor(), selectedRow,
                                                                        5);
                                                        model.setValueAt(studentToUpdate.getStudentAcademy(),
                                                                        selectedRow, 6);
                                                        model.setValueAt(studentToUpdate.getStudentEmail(), selectedRow,
                                                                        7);
                                                        model.setValueAt(studentToUpdate.getStudentAddress(),
                                                                        selectedRow, 8);
                                                        model.setValueAt(studentToUpdate.getStudentPhoneNumber(),
                                                                        selectedRow, 9);

                                                        JOptionPane.showMessageDialog(this,
                                                                        "Student updated successfully!");
                                                } catch (Exception ex) {
                                                        JOptionPane.showMessageDialog(this,
                                                                        "Failed to update student: " + ex.getMessage(),
                                                                        "Error",
                                                                        JOptionPane.ERROR_MESSAGE);
                                                }
                                        }
                                }
                        } else {
                                JOptionPane.showMessageDialog(this, "Please select a row to update!");
                        }
                });

                RoundedButton b9 = new RoundedButton("Delete", 20);
                b9.setBounds(397, 565, 150, 40);
                b9.setForeground(Color.black);
                b9.setFont(new Font("Arial", Font.PLAIN, 18));
                b9.setBackground(buttonColor);
                p4.add(b9);

                // Delete student button
                b9.addActionListener(e -> {
                        int selectedRow = jt.getSelectedRow();
                        if (selectedRow != -1) {
                                String studentIDToDelete = model.getValueAt(selectedRow, 0).toString();

                                StuCourse studentToDelete = null;
                                for (StuCourse student : currentStudents) {
                                        if (student.getStudentID().equals(studentIDToDelete)) {
                                                studentToDelete = student;
                                                break;
                                        }
                                }

                                if (studentToDelete != null) {
                                        try (MongoClient mongoClient = MongoClients.create(
                                                        "mongodb+srv://Admin:admin12345@adminsystem.kedip.mongodb.net/?retryWrites=true&w=majority")) {
                                                MongoDatabase database = mongoClient.getDatabase("AdminSystem");
                                                MongoCollection<Document> collection = database
                                                                .getCollection("CourseManagement");

                                                Document query = new Document("courseName", selectedButton.getText());
                                                Document update = new Document("$pull", new Document("students",
                                                                new Document("StudentID", studentIDToDelete)));

                                                collection.updateOne(query, update);

                                                currentStudents.remove(studentToDelete);
                                                model.removeRow(selectedRow);
                                                JOptionPane.showMessageDialog(this, "Student deleted successfully!");
                                        } catch (Exception ex) {
                                                JOptionPane.showMessageDialog(this,
                                                                "Failed to delete student: " + ex.getMessage(), "Error",
                                                                JOptionPane.ERROR_MESSAGE);
                                        }
                                }
                        } else {
                                JOptionPane.showMessageDialog(this, "Please select a row to delete!");
                        }
                });

                RoundedButton b10 = new RoundedButton("Clear Form", 20);
                b10.setBounds(714, 565, 150, 40);
                b10.setForeground(Color.black);
                b10.setFont(new Font("Arial", Font.PLAIN, 18));
                b10.setBackground(buttonColor);
                p4.add(b10);

                // Delete all student button
                b10.addActionListener(e -> {
                        if (selectedButton == null) {
                                JOptionPane.showMessageDialog(this, "Please select a course first!", "Error",
                                                JOptionPane.ERROR_MESSAGE);
                                return;
                        }

                        String selectedCourseName = selectedButton.getText();
                        int confirm = JOptionPane.showConfirmDialog(this,
                                        "Are you sure you want to delete all students in the course: "
                                                        + selectedCourseName + "?",
                                        "Confirm Deletion",
                                        JOptionPane.YES_NO_OPTION);

                        if (confirm == JOptionPane.YES_OPTION) {
                                try (MongoClient mongoClient = MongoClients.create(
                                                "mongodb+srv://Admin:admin12345@adminsystem.kedip.mongodb.net/?retryWrites=true&w=majority")) {
                                        MongoDatabase database = mongoClient.getDatabase("AdminSystem");
                                        MongoCollection<Document> collection = database
                                                        .getCollection("CourseManagement");

                                        Document query = new Document("courseName", selectedCourseName);
                                        Document update = new Document("$set",
                                                        new Document("students", new ArrayList<>()));
                                        collection.updateOne(query, update);

                                        model.setRowCount(0);
                                        currentStudents.clear();

                                        JOptionPane.showMessageDialog(this, "All students in the course '"
                                                        + selectedCourseName + "' have been deleted successfully!");
                                } catch (Exception ex) {
                                        JOptionPane.showMessageDialog(this,
                                                        "Failed to delete students: " + ex.getMessage(), "Error",
                                                        JOptionPane.ERROR_MESSAGE);
                                }
                        }
                });

                RoundedButton b11 = new RoundedButton("Log Out", 20);
                b11.setBounds(1021, 565, 150, 40);
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

        private void clearAndReloadTable(String courseName) {

                model.setRowCount(0);

                loadStudentsForCourse(courseName);
        }

        public static void main(String[] args) {
                new CourseManagement().setVisible(true);
        }
}
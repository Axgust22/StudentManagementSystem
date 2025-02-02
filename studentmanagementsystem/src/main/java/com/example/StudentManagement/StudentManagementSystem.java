package com.example.StudentManagement;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.example.MainSystem;
import com.example.CourseManagement.CourseManagement;
import com.example.HandleSystem.Student;
import com.example.ScoreManagement.ScoreManagement;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import java.awt.*;

public class StudentManagementSystem extends JFrame {
    private DefaultTableModel model;
    private DefaultTableModel originalModel;

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

    public StudentManagementSystem() {
        setSize(1510, 800);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel p1 = new JPanel();
        p1.setBounds(0, 0, 1510, 800);
        p1.setBackground(Color.white);
        p1.setLayout(null);
        add(p1);

        JLabel l1 = new JLabel("STUDENT INFORMATION");
        l1.setBounds(30, 20, 800, 40);
        l1.setFont(new Font("poppins", Font.BOLD, 36));
        p1.add(l1);

        JLabel lb2 = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Load the image
                ImageIcon icon = new ImageIcon("Image/ITC.jpg");
                Image img = icon.getImage();
                Graphics2D g2d = (Graphics2D) g;
                g2d.setClip(new java.awt.geom.Ellipse2D.Double(0, 0, getWidth(), getHeight()));
                g2d.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            }
        };

        // Set bounds and add to the panel
        lb2.setBounds(1350, 0, 120, 120);
        p1.add(lb2);

        Color buttonColor = Color.decode("#8FB6FF");

        RoundedButton b1 = new RoundedButton("Student Information", 20);
        b1.setBounds(30, 80, 250, 40);
        b1.setFont(new Font("Arial", Font.PLAIN, 18));
        b1.setForeground(Color.black);
        b1.setBackground(buttonColor);
        p1.add(b1);

        RoundedButton b2 = new RoundedButton("Student Course", 20);
        b2.setBounds(300, 80, 250, 40);
        b2.setForeground(Color.black);
        b2.setFont(new Font("Arial", Font.PLAIN, 18));
        b2.setBackground(buttonColor);
        p1.add(b2);
        b2.addActionListener(e -> {
            dispose();
            new CourseManagement().setVisible(true);
        });

        RoundedButton bb3 = new RoundedButton("Student Score", 20);
        bb3.setBounds(570, 80, 250, 40);
        bb3.setForeground(Color.black);
        bb3.setFont(new Font("Arial", Font.PLAIN, 18));
        bb3.setBackground(buttonColor);
        p1.add(bb3);
        bb3.addActionListener(e -> {
            dispose();
            new ScoreManagement().setVisible(true);
        });

        JPanel p2 = new JPanel();
        p2.setBounds(30, 120, 1450, 600);
        p2.setBackground(Color.WHITE);
        p2.setBorder(new LineBorder(Color.BLACK, 2));
        p2.setLayout(null);
        p1.add(p2);

        JLabel l3 = new JLabel("Search Student Information:");
        l3.setBounds(30, 20, 300, 30);
        l3.setFont(new Font("Arial", Font.PLAIN, 20));
        p2.add(l3);

        JTextField t1 = new JTextField();
        t1.setBounds(300, 20, 450, 30);
        t1.setBorder(new LineBorder(Color.BLACK, 2));
        t1.setFont(new Font("Arial", Font.PLAIN, 18));
        p2.add(t1);

        RoundedButton b3 = new RoundedButton("Search", 20);
        b3.setBounds(775, 15, 150, 40);
        b3.setFont(new Font("Arial", Font.PLAIN, 18));
        b3.setForeground(Color.black);
        b3.setBackground(buttonColor);
        p2.add(b3);

        RoundedButton b4 = new RoundedButton("Back", 20);
        b4.setBounds(945, 15, 150, 40);
        b4.setForeground(Color.BLACK);
        b4.setFont(new Font("Arial", Font.PLAIN, 18));
        b4.setBackground(Color.WHITE);
        p2.add(b4);

        JPanel p3 = new JPanel();
        p3.setBounds(15, 70, 1420, 450);
        p3.setBackground(Color.LIGHT_GRAY);
        p3.setBorder(new LineBorder(Color.BLACK, 2));
        p3.setLayout(new BorderLayout());
        p2.add(p3);

        String[] column = {
                "ID", "Name", "Age", "Sex", "Major", "Year", "Academy Year",
                "Email", "Address", "Phone Number"
        };
        model = new DefaultTableModel(null, column);
        originalModel = model;

        JTable jt = new JTable(model);
        jt.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);

        // Add the table to a scroll pane
        JScrollPane sp = new JScrollPane(jt);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Set layout and add scroll pane to panel p3
        p3.setLayout(new BorderLayout());
        p3.add(sp, BorderLayout.CENTER);

        RoundedButton b5 = new RoundedButton("Add", 20);
        b5.setBounds(85, 530, 150, 40);
        b5.setForeground(Color.black);
        b5.setFont(new Font("Arial", Font.PLAIN, 18));
        b5.setBackground(buttonColor);
        p2.add(b5);

        RoundedButton b6 = new RoundedButton("Update", 20);
        b6.setBounds(360, 530, 150, 40);
        b6.setForeground(Color.black);
        b6.setFont(new Font("Arial", Font.PLAIN, 18));
        b6.setBackground(buttonColor);
        p2.add(b6);

        RoundedButton b7 = new RoundedButton("Delete", 20);
        b7.setBounds(660, 530, 150, 40);
        b7.setForeground(Color.black);
        b7.setFont(new Font("Arial", Font.PLAIN, 18));
        b7.setBackground(buttonColor);
        p2.add(b7);

        RoundedButton b8 = new RoundedButton("Clear Form", 20);
        b8.setBounds(950, 530, 150, 40);
        b8.setForeground(Color.black);
        b8.setFont(new Font("Arial", Font.PLAIN, 18));
        b8.setBackground(buttonColor);
        p2.add(b8);

        RoundedButton b9 = new RoundedButton("Log Out", 20);
        b9.setBounds(1220, 530, 150, 40);
        b9.setForeground(Color.black);
        b9.setFont(new Font("Arial", Font.PLAIN, 18));
        b9.setBackground(buttonColor);
        p2.add(b9);
        b9.addActionListener(e -> {
            dispose();
            new MainSystem().setVisible(true);
        });

        // Search student button
        b3.addActionListener(e -> {
            String searchID = t1.getText().trim();
            if (searchID == null || searchID.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a valid Student ID!");
                return;
            }

            try (MongoClient mongoClient = MongoClients.create(
                    "mongodb+srv://Admin:admin12345@adminsystem.kedip.mongodb.net/?retryWrites=true&w=majority")) {
                MongoDatabase database = mongoClient.getDatabase("AdminSystem");
                MongoCollection<Document> collection = database.getCollection("StudentManagement");

                Document query = new Document("ID", searchID);
                Document result = collection.find(query).first();

                if (result != null) {
                    Student student = new Student(
                            result.getString("ID"),
                            result.getString("Name"),
                            parseInteger(result.get("Age")),
                            result.getString("Sex"),
                            null,
                            result.getString("Major"),
                            parseInteger(result.get("Year")),
                            result.getString("AcademyYear"),
                            result.getString("Email"),
                            result.getString("Address"),
                            result.getString("PhoneNumber"));

                    DefaultTableModel model = (DefaultTableModel) jt.getModel();
                    model.setRowCount(0);
                    model.addRow(new Object[] {
                            student.getStudentID(),
                            student.getStudentName(),
                            student.getStudentAge(),
                            student.getStudentSex(),
                            student.getStudentMajor(),
                            student.getStudentYear(),
                            student.getStudentAcademicYear(),
                            student.getStudentEmail(),
                            student.getStudentAddress(),
                            student.getStudentPhone()
                    });

                    JOptionPane.showMessageDialog(this, "Search completed!");
                } else {
                    JOptionPane.showMessageDialog(this,
                            "No student found for Student ID: " + searchID);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Database connection failed: " + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        // Update the Back button to restore the original model
        b4.addActionListener(e -> {
            try {
                // Reload all students into the table
                loadStudentsFromMongoDB();
                t1.setText("");
                JOptionPane.showMessageDialog(this, "Table restored to original data!");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this,
                        "Failed to reload original data: " + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        // Add student button
        b5.addActionListener(e -> {
            new AddStudent(model).setVisible(true);
        });

        // Update student button
        b6.addActionListener(e -> {
            int selectedRow = jt.getSelectedRow();
            if (selectedRow != -1) {
                String currentStuID = model.getValueAt(selectedRow, 0).toString();
                String name = model.getValueAt(selectedRow, 1).toString();
                int age = Integer.parseInt(model.getValueAt(selectedRow, 2).toString());
                String sex = model.getValueAt(selectedRow, 3).toString();
                String major = model.getValueAt(selectedRow, 4).toString();
                int year = Integer.parseInt(model.getValueAt(selectedRow, 5).toString());
                String academyYear = model.getValueAt(selectedRow, 6).toString();
                String email = model.getValueAt(selectedRow, 7).toString();
                String address = model.getValueAt(selectedRow, 8).toString();
                String phone = model.getValueAt(selectedRow, 9).toString();

                JTextField idField = new JTextField(currentStuID);
                JTextField nameField = new JTextField(name);
                JTextField ageField = new JTextField(String.valueOf(age));
                JTextField sexField = new JTextField(sex);
                JTextField majorField = new JTextField(major);
                JTextField yearField = new JTextField(String.valueOf(year));
                JTextField academyYearField = new JTextField(academyYear);
                JTextField emailField = new JTextField(email);
                JTextField phoneField = new JTextField(phone);
                JTextField addressField = new JTextField(address);

                JPanel panel = new JPanel(new GridLayout(10, 2, 10, 10));
                panel.add(new JLabel("Student ID"));
                panel.add(idField);
                panel.add(new JLabel("Name"));
                panel.add(nameField);
                panel.add(new JLabel("Age"));
                panel.add(ageField);
                panel.add(new JLabel("Sex"));
                panel.add(sexField);
                panel.add(new JLabel("Major"));
                panel.add(majorField);
                panel.add(new JLabel("Year"));
                panel.add(yearField);
                panel.add(new JLabel("Academy Year"));
                panel.add(academyYearField);
                panel.add(new JLabel("Email"));
                panel.add(emailField);
                panel.add(new JLabel("Address"));
                panel.add(addressField);
                panel.add(new JLabel("Phone Number"));
                panel.add(phoneField);

                int option = JOptionPane.showConfirmDialog(null, panel, "Update Student", JOptionPane.OK_CANCEL_OPTION);

                if (option == JOptionPane.OK_OPTION) {
                    try {
                        String newStuID = idField.getText();
                        String newName = nameField.getText();
                        int newAge = Integer.parseInt(ageField.getText());
                        String newSex = sexField.getText();
                        String newMajor = majorField.getText();
                        int newYear = Integer.parseInt(yearField.getText());
                        String newAcademyYear = academyYearField.getText();
                        String newEmail = emailField.getText();
                        String newPhone = phoneField.getText();
                        String newAddress = addressField.getText();

                        if (newStuID.isEmpty() || newName.isEmpty() || newSex.isEmpty() || newMajor.isEmpty() ||
                                newAcademyYear.isEmpty() || newEmail.isEmpty() || newAddress.isEmpty()
                                || newPhone.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Please fill in all the fields before submitting.");
                            return;
                        }

                        try (MongoClient mongoClient = MongoClients.create(
                                "mongodb+srv://Admin:admin12345@adminsystem.kedip.mongodb.net/?retryWrites=true&w=majority")) {
                            MongoDatabase database = mongoClient.getDatabase("AdminSystem");
                            MongoCollection<Document> collection = database.getCollection("StudentManagement");

                            if (!newStuID.equals(currentStuID)
                                    && collection.find(new Document("ID", newStuID)).iterator().hasNext()) {
                                JOptionPane.showMessageDialog(null,
                                        "This Student ID already exists. Please choose another ID.");
                                return;
                            }

                            Student updatedStudent = new Student(newStuID, newName, newAge, newSex, "N/A", newMajor,
                                    newYear, newAcademyYear, newEmail, newAddress, newPhone);

                            Document query = new Document("ID", currentStuID);
                            Document update = new Document("$set", new Document()
                                    .append("ID", updatedStudent.getStudentID())
                                    .append("Name", updatedStudent.getStudentName())
                                    .append("Age", updatedStudent.getStudentAge())
                                    .append("Sex", updatedStudent.getStudentSex())
                                    .append("Major", updatedStudent.getStudentMajor())
                                    .append("Year", updatedStudent.getStudentYear())
                                    .append("AcademyYear", updatedStudent.getStudentAcademicYear())
                                    .append("Email", updatedStudent.getStudentEmail())
                                    .append("Address", updatedStudent.getStudentAddress())
                                    .append("PhoneNumber", updatedStudent.getStudentPhone()));

                            collection.updateOne(query, update);
                            model.setValueAt(updatedStudent.getStudentID(), selectedRow, 0);
                            model.setValueAt(updatedStudent.getStudentName(), selectedRow, 1);
                            model.setValueAt(updatedStudent.getStudentAge(), selectedRow, 2);
                            model.setValueAt(updatedStudent.getStudentSex(), selectedRow, 3);
                            model.setValueAt(updatedStudent.getStudentMajor(), selectedRow, 4);
                            model.setValueAt(updatedStudent.getStudentYear(), selectedRow, 5);
                            model.setValueAt(updatedStudent.getStudentAcademicYear(), selectedRow, 6);
                            model.setValueAt(updatedStudent.getStudentEmail(), selectedRow, 7);
                            model.setValueAt(updatedStudent.getStudentAddress(), selectedRow, 8);
                            model.setValueAt(updatedStudent.getStudentPhone(), selectedRow, 9);

                            JOptionPane.showMessageDialog(null, "Student updated successfully!");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Please enter valid numeric values for Age and Year.");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Failed to update student: " + ex.getMessage(), "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please select a row to update!");
            }
        });

        // Delete student button
        b7.addActionListener(e -> {
            int selectedRow = jt.getSelectedRow();
            if (selectedRow != -1) {
                Student studentToDelete = new Student(
                        (String) model.getValueAt(selectedRow, 0),
                        (String) model.getValueAt(selectedRow, 1),
                        Integer.parseInt(model.getValueAt(selectedRow, 2).toString()),
                        (String) model.getValueAt(selectedRow, 3),
                        null,
                        (String) model.getValueAt(selectedRow, 4),
                        Integer.parseInt(model.getValueAt(selectedRow, 5).toString()),
                        (String) model.getValueAt(selectedRow, 6),
                        (String) model.getValueAt(selectedRow, 7),
                        (String) model.getValueAt(selectedRow, 8),
                        (String) model.getValueAt(selectedRow, 9));

                try (MongoClient mongoClient = MongoClients.create(
                        "mongodb+srv://Admin:admin12345@adminsystem.kedip.mongodb.net/?retryWrites=true&w=majority")) {
                    MongoDatabase database = mongoClient.getDatabase("AdminSystem");
                    MongoCollection<Document> collection = database.getCollection("StudentManagement");
                    Document query = new Document("ID", studentToDelete.getStudentID());
                    collection.deleteOne(query);

                    model.removeRow(selectedRow);

                    JOptionPane.showMessageDialog(this, "Student deleted successfully!");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this,
                            "Failed to delete student: " + ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a row to delete!");
            }
        });

        // Clear all student records button
        b8.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to clear all records from the database?",
                    "Confirm Clear",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                try (MongoClient mongoClient = MongoClients.create(
                        "mongodb+srv://Admin:admin12345@adminsystem.kedip.mongodb.net/?retryWrites=true&w=majority")) {
                    MongoDatabase database = mongoClient.getDatabase("AdminSystem");
                    MongoCollection<Document> collection = database.getCollection("StudentManagement");

                    model.setRowCount(0);
                    long deletedCount = collection.deleteMany(new Document()).getDeletedCount();
                    if (deletedCount > 0) {
                        JOptionPane.showMessageDialog(this, "All student records cleared successfully!");
                    } else {
                        JOptionPane.showMessageDialog(this, "No records found to clear!");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Failed to clear student records: " + ex.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
                t1.setText("");
            }
        });

        b9.addActionListener(e -> dispose());
        loadStudentsFromMongoDB();
    }

    private int parseInteger(Object value) {
        try {
            return value != null ? Integer.parseInt(value.toString()) : 0;
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

    private void loadStudentsFromMongoDB() {
        model.setRowCount(0);
        try (MongoClient mongoClient = MongoClients.create(
                "mongodb+srv://Admin:admin12345@adminsystem.kedip.mongodb.net/?retryWrites=true&w=majority")) {
            MongoDatabase database = mongoClient.getDatabase("AdminSystem");
            MongoCollection<Document> collection = database.getCollection("StudentManagement");

            // Fetch all student documents
            for (Document doc : collection.find()) {
                model.addRow(new Object[] {
                        doc.getString("ID"),
                        doc.getString("Name"),
                        doc.getInteger("Age"),
                        doc.getString("Sex"),
                        doc.getString("Major"),
                        doc.getInteger("Year"),
                        doc.getString("AcademyYear"),
                        doc.getString("Email"),
                        doc.getString("Address"),
                        doc.getString("PhoneNumber")
                });
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load student data: " + ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new StudentManagementSystem().setVisible(true);
    }
}
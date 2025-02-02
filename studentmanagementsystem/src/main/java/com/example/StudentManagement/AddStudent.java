package com.example.StudentManagement;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.example.HandleSystem.Student;
import com.mongodb.ErrorCategory;
import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;

import org.bson.Document;

public class AddStudent extends JFrame {
        public DefaultTableModel model;

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

        public AddStudent(DefaultTableModel tableModel) {
                this.model = tableModel;

                setSize(520, 600);
                setLayout(null);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                JPanel p1 = new JPanel();
                p1.setBounds(10, 8, 494, 551);
                p1.setBackground(Color.white);
                p1.setBorder(new LineBorder(Color.BLACK, 2));
                p1.setLayout(null);
                add(p1);

                JLabel l1 = new JLabel("ADD STUDENT");
                l1.setBounds(154, 20, 300, 30);
                l1.setFont(new Font("Poppins", Font.BOLD, 24));
                p1.add(l1);

                JLabel l2 = new JLabel("StudentID");
                l2.setBounds(30, 80, 200, 30);
                p1.add(l2);
                JTextField t1 = new JTextField();
                t1.setBounds(130, 85, 250, 25);
                t1.setBorder(new LineBorder(Color.black, 2));
                p1.add(t1);

                JLabel l3 = new JLabel("StudentName");
                l3.setBounds(30, 125, 200, 30);
                p1.add(l3);
                JTextField t2 = new JTextField();
                t2.setBounds(130, 130, 250, 25);
                t2.setBorder(new LineBorder(Color.black, 2));
                p1.add(t2);

                JLabel l4 = new JLabel("Age");
                l4.setBounds(30, 165, 200, 30);
                p1.add(l4);
                JTextField t3 = new JTextField();
                t3.setBounds(130, 170, 250, 25);
                t3.setBorder(new LineBorder(Color.black, 2));
                p1.add(t3);

                JLabel l5 = new JLabel("Gender");
                l5.setBounds(30, 205, 200, 30);
                p1.add(l5);
                JTextField t4 = new JTextField();
                t4.setBounds(130, 210, 250, 25);
                t4.setBorder(new LineBorder(Color.black, 2));
                p1.add(t4);

                JLabel l6 = new JLabel("Major");
                l6.setBounds(30, 245, 200, 30);
                p1.add(l6);
                JTextField t5 = new JTextField();
                t5.setBounds(130, 250, 250, 25);
                t5.setBorder(new LineBorder(Color.black, 2));
                p1.add(t5);

                JLabel l7 = new JLabel("Year");
                l7.setBounds(30, 285, 200, 30);
                p1.add(l7);
                JTextField t6 = new JTextField();
                t6.setBounds(130, 290, 250, 25);
                t6.setBorder(new LineBorder(Color.black, 2));
                p1.add(t6);

                JLabel l8 = new JLabel("Academy Year");
                l8.setBounds(30, 325, 200, 30);
                p1.add(l8);
                JTextField t7 = new JTextField();
                t7.setBounds(130, 330, 250, 25);
                t7.setBorder(new LineBorder(Color.black, 2));
                p1.add(t7);

                JLabel l9 = new JLabel("Email");
                l9.setBounds(30, 365, 200, 30);
                p1.add(l9);
                JTextField t8 = new JTextField();
                t8.setBounds(130, 370, 250, 25);
                t8.setBorder(new LineBorder(Color.black, 2));
                p1.add(t8);

                JLabel l10 = new JLabel("Address");
                l10.setBounds(30, 405, 200, 30);
                p1.add(l10);
                JTextField t9 = new JTextField();
                t9.setBounds(130, 410, 250, 25);
                t9.setBorder(new LineBorder(Color.black, 2));
                p1.add(t9);

                JLabel l11 = new JLabel("Phone Number");
                l11.setBounds(30, 445, 200, 30);
                p1.add(l11);
                JTextField t10 = new JTextField();
                t10.setBounds(130, 450, 250, 25);
                t10.setBorder(new LineBorder(Color.black, 2));
                p1.add(t10);

                RoundedButton b1 = new RoundedButton("Back", 20);
                b1.setBounds(30, 500, 100, 40);
                b1.setBackground(Color.WHITE);
                b1.setForeground(Color.black);
                p1.add(b1);
                b1.addActionListener(e -> dispose());

                RoundedButton b2 = new RoundedButton("Save", 20);
                b2.setBounds(370, 500, 100, 40);
                b2.setBackground(Color.decode("#8FB6FF"));
                b2.setForeground(Color.black);
                p1.add(b2);

                b2.addActionListener(e -> {
                        String ID = t1.getText().trim();
                        String Name = t2.getText().trim();
                        String Sex = t4.getText().trim();
                        String Major = t5.getText().trim();
                        String AcademyYear = t7.getText().trim();
                        String Email = t8.getText().trim();
                        String Address = t9.getText().trim();
                        String PhoneNumber = t10.getText().trim();
                        String AgeInput = t3.getText().trim();
                        String YearInput = t6.getText().trim();

                        if (ID.isEmpty() || Name.isEmpty() || Sex.isEmpty() || Major.isEmpty() || AcademyYear.isEmpty()
                                        ||
                                        Email.isEmpty() || Address.isEmpty() || PhoneNumber.isEmpty()
                                        || AgeInput.isEmpty() || YearInput.isEmpty()) {
                                JOptionPane.showMessageDialog(this, "Please fill in all required fields!", "Error",
                                                JOptionPane.ERROR_MESSAGE);
                                return;
                        }

                        int Age;
                        try {
                                Age = Integer.parseInt(AgeInput);
                                if (Age <= 0) {
                                        JOptionPane.showMessageDialog(this, "Age must be a positive number.", "Error",
                                                        JOptionPane.ERROR_MESSAGE);
                                        return;
                                }
                        } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(this, "Please enter a valid number for Age.", "Error",
                                                JOptionPane.ERROR_MESSAGE);
                                return;
                        }

                        int Year;
                        try {
                                Year = Integer.parseInt(YearInput);
                                if (Year <= 0) {
                                        JOptionPane.showMessageDialog(this, "Year must be a positive number.", "Error",
                                                        JOptionPane.ERROR_MESSAGE);
                                        return;
                                }
                        } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(this, "Please enter a valid number for Year.", "Error",
                                                JOptionPane.ERROR_MESSAGE);
                                return;
                        }

                        if (!Email.matches("^[a-zA-Z0-9._%+-]+@gmail\\.com$")) {
                                JOptionPane.showMessageDialog(this, "Please enter a valid @gmail.com email",
                                                "Error",
                                                JOptionPane.ERROR_MESSAGE);
                                return;
                        }

                        Student student = new Student(ID, Name, Age, Sex, "N/A", Major, Year, AcademyYear, Email,
                                        Address,
                                        PhoneNumber);

                        try (MongoClient mongoClient = MongoClients.create(
                                        "mongodb+srv://Admin:admin12345@adminsystem.kedip.mongodb.net/?retryWrites=true&w=majority")) {
                                MongoDatabase database = mongoClient.getDatabase("AdminSystem");
                                MongoCollection<Document> collection = database.getCollection("StudentManagement");
                                collection.createIndex(Indexes.ascending("ID"), new IndexOptions().unique(true));

                                Document studentDoc = new Document("ID", student.getStudentID())
                                                .append("Name", student.getStudentName())
                                                .append("Age", student.getStudentAge())
                                                .append("Sex", student.getStudentSex())
                                                .append("Major", student.getStudentMajor())
                                                .append("Year", student.getStudentYear())
                                                .append("AcademyYear", student.getStudentAcademicYear())
                                                .append("Email", student.getStudentEmail())
                                                .append("Address", student.getStudentAddress())
                                                .append("PhoneNumber", student.getStudentPhone());

                                collection.insertOne(studentDoc);

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

                                JOptionPane.showMessageDialog(this, "Student added successfully!");
                                dispose();
                        } catch (MongoWriteException ex) {
                                if (ex.getError().getCategory() == ErrorCategory.DUPLICATE_KEY) {
                                        JOptionPane.showMessageDialog(this, "A student with this ID already exists!",
                                                        "Error",
                                                        JOptionPane.ERROR_MESSAGE);
                                } else {
                                        ex.printStackTrace();
                                        JOptionPane.showMessageDialog(this,
                                                        "An error occurred while saving student data.", "Error",
                                                        JOptionPane.ERROR_MESSAGE);
                                }
                        } catch (Exception ex) {
                                ex.printStackTrace();
                                JOptionPane.showMessageDialog(this, "An error occurred while saving student data.",
                                                "Error",
                                                JOptionPane.ERROR_MESSAGE);
                        }
                });

        }

        public static void main(String[] args) {
                SwingUtilities.invokeLater(() -> {
                        DefaultTableModel model = new DefaultTableModel(
                                        new String[] { "ID", "Name", "Age", "Sex", "Major", "Year", "AcademyYear",
                                                        "Email", "Address",
                                                        "PhoneNumber" },
                                        0);
                        new AddStudent(model).setVisible(true);
                });
        }
}
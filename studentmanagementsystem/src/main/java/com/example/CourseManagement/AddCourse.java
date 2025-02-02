package com.example.CourseManagement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.example.HandleSystem.Course;
import com.example.HandleSystem.StuCourse;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AddCourse extends JFrame {

        private JTable studentTable;
        private DefaultTableModel tableModel;
        private String selectedCourseName;

        public AddCourse(String selectedCourseName) {
                this.selectedCourseName = selectedCourseName;

                setSize(900, 600);
                setLayout(new BorderLayout());
                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                JPanel topPanel = new JPanel();
                topPanel.setPreferredSize(new Dimension(900, 50));
                JLabel titleLabel = new JLabel("Add Students to Course: " + selectedCourseName);
                titleLabel.setFont(new Font("Poppins", Font.BOLD, 18));
                topPanel.add(titleLabel);
                add(topPanel, BorderLayout.NORTH);

                String[] columnNames = { "Select", "Student ID", "Student Name", "Age", "Sex", "Major", "Year",
                                "Academy Year", "Email", "Address", "Phone Number" };
                tableModel = new DefaultTableModel(null, columnNames) {
                        @Override
                        public Class<?> getColumnClass(int column) {
                                if (column == 0) {
                                        return Boolean.class;
                                }
                                return String.class;
                        }

                        @Override
                        public boolean isCellEditable(int row, int column) {
                                return column == 0;
                        }
                };
                studentTable = new JTable(tableModel);
                studentTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
                JScrollPane scrollPane = new JScrollPane(studentTable);
                add(scrollPane, BorderLayout.CENTER);

                studentTable.getColumnModel().getColumn(0).setPreferredWidth(50);
                studentTable.getColumnModel().getColumn(1).setPreferredWidth(80);
                studentTable.getColumnModel().getColumn(2).setPreferredWidth(150);
                studentTable.getColumnModel().getColumn(10).setPreferredWidth(120);

                JPanel bottomPanel = new JPanel();
                JButton loadButton = new JButton("Load Students");
                JButton selectAllButton = new JButton("Select All");
                JButton addButton = new JButton("Add to Course");

                bottomPanel.add(loadButton);
                bottomPanel.add(selectAllButton);
                bottomPanel.add(addButton);
                add(bottomPanel, BorderLayout.SOUTH);

                // Button Actions
                loadButton.addActionListener(e -> loadStudents());
                selectAllButton.addActionListener(e -> toggleSelectAll());
                addButton.addActionListener(e -> {
                        addSelectedStudentsToCourse();
                        dispose();
                });
        }

        private void toggleSelectAll() {
                boolean selectAll = !(Boolean) tableModel.getValueAt(0, 0);
                for (int i = 0; i < tableModel.getRowCount(); i++) {
                        tableModel.setValueAt(selectAll, i, 0);
                }
        }

        private List<StuCourse> loadedStudents = new ArrayList<>();

        private void loadStudents() {
                try (MongoClient mongoClient = MongoClients.create(
                                "mongodb+srv://Admin:admin12345@adminsystem.kedip.mongodb.net/?retryWrites=true&w=majority")) {

                        MongoDatabase database = mongoClient.getDatabase("AdminSystem");
                        MongoCollection<Document> studentCollection = database.getCollection("StudentManagement");

                        Course course = new Course(selectedCourseName);
                        course.loadStudentsFromDB(database);

                        List<String> enrolledStudentIDs = new ArrayList<>();
                        for (StuCourse student : course.getStudents()) {
                                enrolledStudentIDs.add(student.getStudentID());
                        }

                        tableModel.setRowCount(0);
                        loadedStudents.clear();

                        Document query = new Document("ID", new Document("$nin", enrolledStudentIDs));
                        FindIterable<Document> students = studentCollection.find(query);

                        if (!students.iterator().hasNext()) {
                                JOptionPane.showMessageDialog(this,
                                                "No students available to add. All students are already in the course or database is empty.",
                                                "Information", JOptionPane.INFORMATION_MESSAGE);
                                return;
                        }

                        for (Document studentDoc : students) {
                                StuCourse student = new StuCourse(
                                                studentDoc.getString("ID"),
                                                studentDoc.getString("Name"),
                                                studentDoc.getInteger("Age", 0),
                                                studentDoc.getString("Sex"),
                                                studentDoc.getString("Major"),
                                                studentDoc.getInteger("Year", 1),
                                                studentDoc.getString("AcademyYear"),
                                                studentDoc.getString("Email"),
                                                studentDoc.getString("Address"),
                                                studentDoc.getString("PhoneNumber"));

                                loadedStudents.add(student);

                                tableModel.addRow(new Object[] {
                                                false,
                                                student.getStudentID(),
                                                student.getStudentName(),
                                                student.getStudentAge(),
                                                student.getStudentGender() != null ? student.getStudentGender() : "N/A",
                                                student.getStudentMajor(),
                                                student.getStudentYear(),
                                                student.getStudentAcademy(),
                                                student.getStudentEmail() != null ? student.getStudentEmail() : "N/A",
                                                student.getStudentAddress() != null ? student.getStudentAddress()
                                                                : "N/A",
                                                student.getStudentPhoneNumber() != null
                                                                ? student.getStudentPhoneNumber()
                                                                : "N/A"
                                });
                        }

                } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(this, "Error loading students: " + ex.getMessage(), "Error",
                                        JOptionPane.ERROR_MESSAGE);
                }
        }

        private void addSelectedStudentsToCourse() {
                try (MongoClient mongoClient = MongoClients.create(
                                "mongodb+srv://Admin:admin12345@adminsystem.kedip.mongodb.net/?retryWrites=true&w=majority")) {

                        MongoDatabase database = mongoClient.getDatabase("AdminSystem");
                        Course course = new Course(selectedCourseName);

                        List<StuCourse> selectedStudents = new ArrayList<>();
                        List<Integer> rowsToRemove = new ArrayList<>();

                        for (int i = 0; i < tableModel.getRowCount(); i++) {
                                if ((Boolean) tableModel.getValueAt(i, 0)) {
                                        StuCourse student = loadedStudents.get(i);
                                        selectedStudents.add(student);
                                        rowsToRemove.add(i);
                                }
                        }

                        if (selectedStudents.isEmpty()) {
                                JOptionPane.showMessageDialog(this, "No students selected.", "Error",
                                                JOptionPane.ERROR_MESSAGE);
                                return;
                        }

                        for (StuCourse student : selectedStudents) {
                                course.addStudentToDB(student, database);
                        }

                        for (int i = rowsToRemove.size() - 1; i >= 0; i--) {
                                tableModel.removeRow(rowsToRemove.get(i));
                        }

                        JOptionPane.showMessageDialog(this, "Selected students added to the course successfully!");

                } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(this, "Error adding students: " + ex.getMessage(), "Error",
                                        JOptionPane.ERROR_MESSAGE);
                }
        }

        public static void main(String[] args) {
                SwingUtilities.invokeLater(() -> new AddCourse("ExampleCourse").setVisible(true));
        }
}
package com.example.Testing;

public class yaa {

    // private void refreshTableData(String year, String semester) {
    // if (model == null) {
    // JOptionPane.showMessageDialog(this, "Table model is not initialized!",
    // "Error", JOptionPane.ERROR_MESSAGE);
    // return;
    // }

    // try (MongoClient mongoClient = MongoClients.create(
    // "mongodb+srv://Admin:admin12345@adminsystem.kedip.mongodb.net/?retryWrites=true&w=majority"))
    // {

    // MongoDatabase database = mongoClient.getDatabase("AdminSystem");
    // MongoCollection<Document> collection =
    // database.getCollection("ScoreManagement");

    // // Clear the table model
    // model.setRowCount(0);

    // boolean dataFound = false; // Flag to track if any data was added

    // // Fetch all student documents
    // for (Document doc : collection.find()) {
    // String studentID = doc.getString("StudentID");
    // String studentName = doc.getString("StudentName");
    // Object recordsObj = doc.get("AcademicRecords");

    // // Validate fields
    // if (studentID == null || studentName == null || !(recordsObj instanceof
    // List)) {
    // System.out.println("Skipping document due to missing or invalid fields: " +
    // doc.toJson());
    // continue;
    // }

    // List<Document> records = (List<Document>) recordsObj;

    // for (Document record : records) {
    // // Check if the record matches the selected year and semester
    // if (year.equals(record.getString("Year")) &&
    // semester.equals(record.getString("Semester"))) {
    // Object coursesObj = record.get("Courses");

    // if (!(coursesObj instanceof List)) {
    // System.out.println("Invalid format for Courses: " + record.toJson());
    // continue;
    // }

    // List<Document> courses = (List<Document>) coursesObj;

    // if (courses != null) {
    // List<Object> rowData = new ArrayList<>();
    // rowData.add(studentID);
    // rowData.add(studentName);

    // double totalScore = 0;
    // for (Document course : courses) {
    // String courseName = course.getString("CourseName");
    // Double score = course.getDouble("Score");

    // if (courseName != null && score != null) {
    // rowData.add(courseName);
    // rowData.add(score);
    // totalScore += score;
    // } else {
    // System.out.println("Skipping invalid course data: " + course.toJson());
    // }
    // }

    // // Calculate GPA
    // double gpa = (courses.size() > 0) ? totalScore / courses.size() : 0;
    // rowData.add(totalScore);
    // rowData.add(gpa);

    // // Add the row to the table
    // model.addRow(rowData.toArray());
    // dataFound = true; // Set flag to true
    // }
    // }
    // }
    // }

    // // If no data was found, add a placeholder row
    // if (!dataFound) {
    // model.setRowCount(0); // Clear the table
    // model.addRow(new Object[] { "No data available for this semester." });
    // }
    // } catch (Exception ex) {
    // ex.printStackTrace(); // Debugging
    // JOptionPane.showMessageDialog(this, "Error fetching data from MongoDB: " +
    // ex.getMessage(), "Error",
    // JOptionPane.ERROR_MESSAGE);
    // }
    // }

    // private void initializeDynamicTable() {
    // // Clear previous table content if necessary
    // p5.removeAll(); // Remove old content from p5

    // // Define dynamic column names
    // List<String> columnNames = new ArrayList<>();
    // columnNames.add("StudentID"); // Always include StudentID and StudentName
    // columnNames.add("StudentName");

    // // Add course names dynamically from MongoDB
    // try (MongoClient mongoClient = MongoClients.create(
    // "mongodb+srv://Admin:admin12345@adminsystem.kedip.mongodb.net/?retryWrites=true&w=majority"))
    // {

    // MongoDatabase database = mongoClient.getDatabase("AdminSystem");
    // MongoCollection<Document> collection =
    // database.getCollection("ScoreManagement");

    // // Collect unique course names dynamically
    // Set<String> courseNames = new LinkedHashSet<>();
    // for (Document doc : collection.find()) {
    // List<Document> records = (List<Document>) doc.get("AcademicRecords");
    // if (records != null) {
    // for (Document record : records) {
    // List<Document> courses = (List<Document>) record.get("Courses");
    // if (courses != null) {
    // for (Document course : courses) {
    // courseNames.add(course.getString("CourseName"));
    // }
    // }
    // }
    // }
    // }

    // // Add course columns to the table
    // for (String courseName : courseNames) {
    // columnNames.add(courseName + " Score");
    // }
    // } catch (Exception ex) {
    // JOptionPane.showMessageDialog(this, "Error fetching course data: " +
    // ex.getMessage(), "Error",
    // JOptionPane.ERROR_MESSAGE);
    // }

    // // Add Total Point and GPA columns
    // columnNames.add("Total Point");
    // columnNames.add("GPA");

    // // Create a new DefaultTableModel with dynamic columns
    // model = new DefaultTableModel(null, columnNames.toArray());
    // originalModel = model; // Save the original model
    // JTable jt = new JTable(model);
    // JScrollPane sp = new JScrollPane(jt);

    // // Add the JTable and JScrollPane to the panel (p5)
    // p5.setLayout(new BorderLayout());
    // p5.add(sp, BorderLayout.CENTER);

    // // Refresh the panel to reflect changes
    // p5.revalidate();
    // p5.repaint();
    // }

    // b8.addActionListener(e -> {
    // int selectedRow = jt.getSelectedRow(); // Get the selected row index

    // if (selectedRow != -1) { // Check if a row is selected
    // // Create text fields dynamically, excluding Total Point and GPA
    // int editableColumns = model.getColumnCount() - 2; // Exclude last two columns
    // JTextField[] fields = new JTextField[editableColumns];
    // JPanel panel = new JPanel(new GridLayout(editableColumns, 2, 10, 10));

    // for (int i = 0; i < editableColumns; i++) {
    // String columnName = model.getColumnName(i);
    // String currentValue = model.getValueAt(selectedRow, i) != null
    // ? model.getValueAt(selectedRow, i).toString()
    // : "";

    // JLabel label = new JLabel(columnName + ":");
    // fields[i] = new JTextField(currentValue);
    // panel.add(label);
    // panel.add(fields[i]);
    // }

    // int option = JOptionPane.showConfirmDialog(
    // this,
    // panel,
    // "Update Student",
    // JOptionPane.OK_CANCEL_OPTION,
    // JOptionPane.PLAIN_MESSAGE);

    // if (option == JOptionPane.OK_OPTION) {
    // boolean isValid = true;

    // // Validate all fields
    // for (JTextField field : fields) {
    // if (field.getText().trim().isEmpty()) {
    // isValid = false;
    // break;
    // }
    // }

    // if (!isValid) {
    // JOptionPane.showMessageDialog(this, "All fields are required!", "Error",
    // JOptionPane.ERROR_MESSAGE);
    // return;
    // }

    // try {
    // // Update the row in the model
    // for (int i = 0; i < editableColumns; i++) {
    // model.setValueAt(fields[i].getText().trim(), selectedRow, i);
    // }

    // // Recalculate Total Point and GPA if scores are updated
    // double totalPoint = 0;
    // int scoreColumns = 5; // Number of score columns
    // int scoreStartIndex = 3; // Starting index of scores in the table model

    // for (int i = 0; i < scoreColumns; i++) {
    // double score = Double.parseDouble(fields[scoreStartIndex + (i *
    // 2)].getText().trim());
    // totalPoint += score;
    // }

    // double gpa = totalPoint / scoreColumns;

    // // Update Total Point and GPA in the table
    // model.setValueAt(String.format("%.2f", totalPoint), selectedRow,
    // model.getColumnCount() - 2); // Total
    // // Point
    // model.setValueAt(String.format("%.2f", gpa), selectedRow,
    // model.getColumnCount() - 1); // GPA

    // JOptionPane.showMessageDialog(this, "Student updated successfully!");
    // } catch (NumberFormatException ex) {
    // JOptionPane.showMessageDialog(this, "Please enter valid numeric scores!",
    // "Error",
    // JOptionPane.ERROR_MESSAGE);
    // }
    // }
    // } else {
    // JOptionPane.showMessageDialog(this, "Please select a row to update!");
    // }
    // });

    // b10.addActionListener(e -> {
    // // Clear the search text field
    // t1.setText("");

    // // Clear the table by removing all rows
    // model.setRowCount(0);

    // // Optionally, reset any JComboBoxes or other form fields if necessary
    // JOptionPane.showMessageDialog(this, "Form cleared successfully!");
    // });

    // b9.addActionListener(e -> {
    // int selectedRow = jt.getSelectedRow();
    // if (selectedRow != -1) {
    // model.removeRow(selectedRow);
    // } else {
    // JOptionPane.showMessageDialog(this, "Please select a row to delete!");
    // }
    // });

    // // Declare a shared JLabel for the year label
    // JLabel year1 = new JLabel();
    // JLabel year2 = new JLabel();
    // JLabel year3 = new JLabel();
    // JLabel year4 = new JLabel();
    // JLabel year5 = new JLabel();

    // BB1.addActionListener(new ActionListener() {
    // public void actionPerformed(ActionEvent e) {
    // // Clear previous label from p4
    // p4.remove(year1);
    // p4.remove(year2);
    // p4.remove(year3);
    // p4.remove(year4);
    // p4.remove(year5);

    // // Set "Year 1 - Semester 1" text to the shared label
    // year1.setText("Year 1 - Semester 1");
    // year1.setBounds(10, 10, 300, 30);
    // year1.setFont(new Font("Arial", Font.BOLD, 24));

    // // Add the updated label to p4
    // p4.add(year1);
    // B1.setVisible(true);
    // // Repaint and revalidate the panel
    // p4.revalidate();
    // p4.repaint();
    // }
    // });

    // BB2.addActionListener(new ActionListener() {
    // public void actionPerformed(ActionEvent e) {
    // // Clear previous label from p4
    // p4.remove(year1);
    // p4.remove(year2);
    // p4.remove(year3);
    // p4.remove(year4);
    // p4.remove(year5);

    // // Set "Year 1 - Semester 2" text to the shared label
    // year1.setText("Year 1 - Semester 2");
    // year1.setBounds(10, 10, 300, 30);
    // year1.setFont(new Font("Arial", Font.BOLD, 24));
    // B1.setVisible(true);
    // // Add the updated label to p4
    // p4.add(year1);

    // // Repaint and revalidate the panel
    // p4.revalidate();
    // p4.repaint();
    // }
    // });

    // BB4.addActionListener(new ActionListener() {
    // public void actionPerformed(ActionEvent e) {
    // // Clear previous label from p4
    // p4.remove(year1);
    // p4.remove(year2);
    // p4.remove(year3);
    // p4.remove(year4);
    // p4.remove(year5);

    // // Set "Year 1 - Semester 1" text to the shared label
    // year2.setText("Year 2 - Semester 1");
    // year2.setBounds(10, 10, 300, 30);
    // year2.setFont(new Font("Arial", Font.BOLD, 24));

    // // Add the updated label to p4
    // p4.add(year2);
    // B1.setVisible(true);
    // // Repaint and revalidate the panel
    // p4.revalidate();
    // p4.repaint();
    // }
    // });

    // BB5.addActionListener(new ActionListener() {
    // public void actionPerformed(ActionEvent e) {
    // // Clear previous label from p4
    // p4.remove(year1);
    // p4.remove(year2);
    // p4.remove(year3);
    // p4.remove(year4);
    // p4.remove(year5);

    // // Set "Year 1 - Semester 2" text to the shared label
    // year2.setText("Year 2 - Semester 2");
    // year2.setBounds(10, 10, 300, 30);
    // year2.setFont(new Font("Arial", Font.BOLD, 24));

    // // Add the updated label to p4
    // p4.add(year2);
    // B1.setVisible(true);
    // // Repaint and revalidate the panel
    // p4.revalidate();
    // p4.repaint();
    // }
    // });

    // BB6.addActionListener(new ActionListener() {
    // public void actionPerformed(ActionEvent e) {
    // // Clear previous label from p4
    // p4.remove(year1);
    // p4.remove(year2);
    // p4.remove(year3);
    // p4.remove(year4);
    // p4.remove(year5);

    // // Set "Year 1 - Semester 1" text to the shared label
    // year3.setText("Year 3 - Semester 1");
    // year3.setBounds(10, 10, 300, 30);
    // year3.setFont(new Font("Arial", Font.BOLD, 24));

    // // Add the updated label to p4
    // p4.add(year3);
    // B1.setVisible(true);
    // // Repaint and revalidate the panel
    // p4.revalidate();
    // p4.repaint();
    // }
    // });

    // BB7.addActionListener(new ActionListener() {
    // public void actionPerformed(ActionEvent e) {
    // // Clear previous label from p4
    // p4.remove(year1);
    // p4.remove(year2);
    // p4.remove(year3);
    // p4.remove(year4);
    // p4.remove(year5);

    // // Set "Year 1 - Semester 2" text to the shared label
    // year3.setText("Year 3 - Semester 2");
    // year3.setBounds(10, 10, 300, 30);
    // year3.setFont(new Font("Arial", Font.BOLD, 24));

    // // Add the updated label to p4
    // p4.add(year3);
    // B1.setVisible(true);
    // // Repaint and revalidate the panel
    // p4.revalidate();
    // p4.repaint();
    // }
    // });

    // BB8.addActionListener(new ActionListener() {
    // public void actionPerformed(ActionEvent e) {
    // // Clear previous label from p4
    // p4.remove(year1);
    // p4.remove(year2);
    // p4.remove(year3);
    // p4.remove(year4);
    // p4.remove(year5);

    // // Set "Year 1 - Semester 1" text to the shared label
    // year4.setText("Year 4 - Semester 1");
    // year4.setBounds(10, 10, 300, 30);
    // year4.setFont(new Font("Arial", Font.BOLD, 24));

    // // Add the updated label to p4
    // p4.add(year4);
    // B1.setVisible(true);
    // // Repaint and revalidate the panel
    // p4.revalidate();
    // p4.repaint();
    // }
    // });

    // BB9.addActionListener(new ActionListener() {
    // public void actionPerformed(ActionEvent e) {
    // // Clear previous label from p4
    // p4.remove(year1);
    // p4.remove(year2);
    // p4.remove(year3);
    // p4.remove(year4);
    // p4.remove(year5);

    // // Set "Year 1 - Semester 2" text to the shared label
    // year4.setText("Year 4 - Semester 2");
    // year4.setBounds(10, 10, 300, 30);
    // year4.setFont(new Font("Arial", Font.BOLD, 24));

    // // Add the updated label to p4
    // p4.add(year4);
    // B1.setVisible(true);
    // // Repaint and revalidate the panel
    // p4.revalidate();
    // p4.repaint();
    // }
    // });

    // BB10.addActionListener(new ActionListener() {
    // public void actionPerformed(ActionEvent e) {
    // // Clear previous label from p4
    // p4.remove(year1);
    // p4.remove(year2);
    // p4.remove(year3);
    // p4.remove(year4);
    // p4.remove(year5);

    // // Set "Year 1 - Semester 1" text to the shared label
    // year5.setText("Year 5 - Semester 1");
    // year5.setBounds(10, 10, 300, 30);
    // year5.setFont(new Font("Arial", Font.BOLD, 24));

    // // Add the updated label to p4
    // p4.add(year5);
    // B1.setVisible(true);
    // // Repaint and revalidate the panel
    // p4.revalidate();
    // p4.repaint();
    // }
    // });

    // BB11.addActionListener(new ActionListener() {
    // public void actionPerformed(ActionEvent e) {
    // // Clear previous label from p4
    // p4.remove(year1);
    // p4.remove(year2);
    // p4.remove(year3);
    // p4.remove(year4);
    // p4.remove(year5);

    // // Set "Year 1 - Semester 2" text to the shared label
    // year5.setText("Year 5 - Semester 2");
    // year5.setBounds(10, 10, 300, 30);
    // year5.setFont(new Font("Arial", Font.BOLD, 24));

    // // Add the updated label to p4
    // p4.add(year5);
    // B1.setVisible(true);
    // // Repaint and revalidate the panel
    // p4.revalidate();
    // p4.repaint();
    // }
    // });

    // String[] column = {
    // "StudentID", "StudentName", "Course 1", "Score 1", "Course 2", "Score 2",
    // "Course 3", "Score 3", "Course 4", "Score 4", "Course 5", "Score 5", "Total
    // Point", "GPA"
    // };
    // model = new DefaultTableModel(null, column);
    // originalModel = model; // Save the original model
    // JTable jt = new JTable(model);
    // JScrollPane sp = new JScrollPane(jt);
    // p5.add(sp);

    // B1.addActionListener(new ActionListener() {
    // public void actionPerformed(ActionEvent e) {
    // B1.setBackground(Color.yellow);
    // new AddScore(model).setVisible(true);
    // }
    // });

}

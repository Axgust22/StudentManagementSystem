// package com.example.HandleSystem;

// import java.util.*;

// public class StuScore {
// private Map<String, Score> students = new HashMap<>();

// // Add a student to the system
// public void addStudent(String studentID, String studentName) {
// if (!students.containsKey(studentID)) {
// students.put(studentID, new Score(studentID, studentName));
// } else {
// throw new IllegalArgumentException("Student with ID " + studentID + " already
// exists.");
// }
// }

// // Add or update a score for a specific student
// public void addOrUpdateScore(String studentID, int year, int semester, String
// courseName, double score) {
// if (students.containsKey(studentID)) {
// students.get(studentID).addOrUpdateScore(year, semester, courseName, score);
// } else {
// throw new IllegalArgumentException("Student with ID " + studentID + " does
// not exist.");
// }
// }

// // Retrieve all scores for a specific student
// public String getStudentScores(String studentID) {
// if (students.containsKey(studentID)) {
// return students.get(studentID).getAllScores();
// } else {
// throw new IllegalArgumentException("Student with ID " + studentID + " does
// not exist.");
// }
// }

// // Calculate the overall GPA for a specific student
// public double calculateStudentOverallGPA(String studentID) {
// if (students.containsKey(studentID)) {
// return students.get(studentID).calculateOverallGPA();
// } else {
// throw new IllegalArgumentException("Student with ID " + studentID + " does
// not exist.");
// }
// }

// // List all students in the system
// public List<String> listAllStudents() {
// List<String> studentList = new ArrayList<>();
// for (Score score : students.values()) {
// studentList.add(score.getStudentName() + " (" + score.getStudentID() + ")");
// }
// return studentList;
// }
// }

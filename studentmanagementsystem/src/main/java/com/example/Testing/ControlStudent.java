package com.example.Testing;

// import java.util.ArrayList;

class Admin {
    private String name;
    private String password;
    private String email;

    // Constructor
    public Admin(String name, String password, String email) {
        this.name = name;
        this.email = email;
        this.password = hashPassword(password); // Hash the password
    }

    // Password hashing (mock example)
    private String hashPassword(String password) {
        return Integer.toHexString(password.hashCode());
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public boolean verifyPassword(String password) {
        return hashPassword(password).equals(this.password);
    }

    // Display admin details (excluding the password for security)
    public void displayAdmin() {
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
    }
}

class Student extends Admin {
    private String studentID;
    private String studentName;
    private int studentAge;
    private String studentSex;
    private String dob;
    private String studentMajor;
    private int studentYear;
    private String studentAcademicYear;
    private String studentEmail;
    private int studentPhone;

    // Constructor
    public Student(String adminName, String adminPassword, String adminEmail,
            String studentID, String studentName, int studentAge, String studentSex, String dob,
            String studentMajor, int studentYear, String studentAcademicYear,
            String studentEmail, int studentPhone) {
        super(adminName, adminPassword, adminEmail); // Call to Admin constructor
        this.studentID = studentID;
        this.studentName = studentName;
        this.studentAge = studentAge;
        this.studentSex = studentSex;
        this.dob = dob;
        this.studentMajor = studentMajor;
        this.studentYear = studentYear;
        this.studentAcademicYear = studentAcademicYear;
        this.studentEmail = studentEmail;
        this.studentPhone = studentPhone;
    }

    // Getters and Setters
    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getStudentAge() {
        return studentAge;
    }

    public void setStudentAge(int studentAge) {
        this.studentAge = studentAge;
    }

    public String getStudentSex() {
        return studentSex;
    }

    public void setStudentSex(String studentSex) {
        this.studentSex = studentSex;
    }

    public void setStudentDob(String dob) {
        this.dob = dob;
    }

    public String getStudentDob() {
        return dob;
    }

    public String getStudentMajor() {
        return studentMajor;
    }

    public void setStudentMajor(String studentMajor) {
        this.studentMajor = studentMajor;
    }

    public int getStudentYear() {
        return studentYear;
    }

    public void setStudentYear(int studentYear) {
        this.studentYear = studentYear;
    }

    public String getStudentAcademicYear() {
        return studentAcademicYear;
    }

    public void setStudentAcademicYear(String studentAcademicYear) {
        this.studentAcademicYear = studentAcademicYear;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public int getStudentPhone() {
        return studentPhone;
    }

    public void setStudentPhone(int studentPhone) {
        this.studentPhone = studentPhone;
    }

    // Display Student details
    public void displayStudentDetails() {
        System.out.println("Student ID: " + studentID);
        System.out.println("Name: " + studentName);
        System.out.println("Age: " + studentAge);
        System.out.println("Sex: " + studentSex);
        System.out.println("Dob: " + dob);
        System.out.println("Major: " + studentMajor);
        System.out.println("Year: " + studentYear);
        System.out.println("Academic Year: " + studentAcademicYear);
        System.out.println("Email: " + studentEmail);
        System.out.println("Phone: " + studentPhone);
    }
}

class Course extends Admin {
    private String studentID;
    private String studentName;
    private String courseName;

    // Constructor
    public Course(String adminName, String adminPassword, String adminEmail,
            String studentID, String studentName, String courseName) {
        super(adminName, adminPassword, adminEmail); // Call to Admin constructor
        this.studentID = studentID;
        this.studentName = studentName;
        this.courseName = courseName;
    }

    // Getters and Setters
    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    // Display Course details
    public void displayCourseDetails() {
        System.out.println("Student ID: " + studentID);
        System.out.println("Student Name: " + studentName);
        System.out.println("Course Name: " + courseName);
    }
}

// class Teacher extends Admin{

// }

public class ControlStudent {
    public static void main(String[] args) {
    }
}

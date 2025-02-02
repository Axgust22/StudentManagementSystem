package com.example.HandleSystem;

public class Student {
    private String studentID;
    private String studentName;
    private int studentAge;
    private String studentSex;
    private String dob;
    private String studentMajor;
    private int studentYear;
    private String studentAcademicYear;
    private String studentEmail;
    private String studentPhone;
    private String studentAddress;

    public Student(String studentID, String studentName, int studentAge, String studentSex, String dob,
            String studentMajor, int studentYear, String studentAcademicYear,
            String studentEmail, String studentAddress, String studentPhone) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.studentAge = studentAge;
        this.studentSex = studentSex;
        this.dob = dob;
        this.studentMajor = studentMajor;
        this.studentYear = studentYear;
        this.studentAcademicYear = studentAcademicYear;
        this.studentEmail = studentEmail;
        this.studentAddress = studentAddress;
        this.studentPhone = studentPhone;
    }

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
        if (studentAge > 0) {
            this.studentAge = studentAge;
        } else {
            System.out.println("Invalid age!");
        }
    }

    public String getStudentSex() {
        return studentSex;
    }

    public void setStudentSex(String studentSex) {
        this.studentSex = studentSex;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
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
        if (studentEmail.contains("@")) {
            this.studentEmail = studentEmail;
        } else {
            System.out.println("Invalid email format!");
        }
    }

    public String getStudentAddress() {
        return studentAddress;
    }

    public void setStudentAddress(String studentAddress) {
        this.studentAddress = studentAddress;
    }

    public String getStudentPhone() {
        return studentPhone;
    }

    public void setStudentPhone(String studentPhone) {
        if (studentPhone.matches("\\d+")) {
            this.studentPhone = studentPhone;
        } else {
            System.out.println("Invalid phone number!");
        }
    }
}

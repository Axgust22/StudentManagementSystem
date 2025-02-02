package com.example.HandleSystem;

public class StuCourse {
    private String studentID;
    private String studentName;
    private int studentAge;
    private String studentGender;
    private String studentMajor;
    private int studentYear;
    private String studentAcademy;
    private String studentEmail;
    private String studentAddress;
    private String studentPhoneNumber;

    public StuCourse(String studentID, String studentName, int studentAge, String studentGender, String studentMajor,
            int studentYear, String studentAcademy, String studentEmail, String studentAddress,
            String studentPhoneNumber) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.studentAge = studentAge;
        this.studentGender = studentGender;
        this.studentMajor = studentMajor;
        this.studentYear = studentYear;
        this.studentAcademy = studentAcademy;
        this.studentEmail = studentEmail;
        this.studentAddress = studentAddress;
        this.studentPhoneNumber = studentPhoneNumber;
    }

    public StuCourse(String studentID2, String studentName2, Object object, int i, int j, Object object2,
            String string) {
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
        }
    }

    public String getStudentGender() {
        return studentGender;
    }

    public void setStudentGender(String studentGender) {
        this.studentGender = studentGender;
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
        if (studentYear > 0) {
            this.studentYear = studentYear;
        }
    }

    public String getStudentAcademy() {
        return studentAcademy;
    }

    public void setStudentAcademy(String studentAcademy) {
        this.studentAcademy = studentAcademy;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getStudentAddress() {
        return studentAddress;
    }

    public void setStudentAddress(String studentAddress) {
        this.studentAddress = studentAddress;
    }

    public String getStudentPhoneNumber() {
        return studentPhoneNumber;
    }

    public void setStudentPhoneNumber(String studentPhoneNumber) {
        this.studentPhoneNumber = studentPhoneNumber;
    }
}
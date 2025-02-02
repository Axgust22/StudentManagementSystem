package com.example.HandleSystem;

public class Stu implements StudentActions {
    private String studentID;
    private String studentPassword;

    public Stu(String studentID, String studentPassword) {
        this.studentID = studentID;
        this.studentPassword = studentPassword;
    }

    public String getID() {
        return studentID;
    }

    public String getPassword() {
        return studentPassword;
    }

    @Override
    public boolean login(String studentID, String studentPassword) {
        return this.studentID.equals(studentID) && this.studentPassword.equals(studentPassword);

    }
}
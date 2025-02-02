package com.example.HandleSystem;

interface UserActions {
    public abstract boolean login(String username, String password);

    public abstract boolean resetPassword(String username, String email, String newPassword);
}

interface StudentActions {
    public abstract boolean login(String studentID, String studentPassword);
}
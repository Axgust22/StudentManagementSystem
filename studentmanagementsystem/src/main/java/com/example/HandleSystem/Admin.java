package com.example.HandleSystem;

public class Admin implements UserActions {
    private String name;
    private String email;
    private String password;

    public Admin(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean login(String username, String password) {
        return this.name.equals(username) && this.password.equals(password);
    }

    @Override
    public boolean resetPassword(String username, String email, String newPassword) {
        if (this.email.equals(email)) {
            this.password = newPassword;
            return true;
        }
        return false;
    }

}
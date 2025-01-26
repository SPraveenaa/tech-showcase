package com.example.wedhall_reservationsystem.model;

public class User {
    private String username;
    private String password;
    private String contact_no;
    private String email_address;

    public User(String username, String password, String contact_no, String email_address) {
        this.username = username;
        this.password = password;
        this.contact_no = contact_no;
        this.email_address = email_address;
    }
}

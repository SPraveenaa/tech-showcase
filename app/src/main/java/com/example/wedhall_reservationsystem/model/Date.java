package com.example.wedhall_reservationsystem.model;

public class Date {

    private int bookId; // Assuming the book_id is an integer in your database
    private String date; // This will store the date as a String

    // Constructor
    public Date(int bookId, String date) {
        this.bookId = bookId;
        this.date = date;
    }

    // Getter and Setter for bookId
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    // Getter and Setter for date
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

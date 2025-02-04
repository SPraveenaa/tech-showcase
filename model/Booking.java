package com.example.wedhall_reservationsystem.model;

public class Booking {
    private String event;
    private String hall;
    private String priceHallPac;
    private String advanceHallPac;
    private String remainingHallPac;
    private String menu;
    private String plateCount;
    private String totalAmount;
    private String advanceAmount;
    private String remainingAmount;
    private String decorationTheme;
    private String totalPriceDeco;
    private String advancePaymentDeco;

    // Constructor
    public Booking(String event, String hall, String priceHallPac, String advanceHallPac, String remainingHallPac,
                   String menu, String plateCount, String totalAmount, String advanceAmount, String remainingAmount,
                   String decorationTheme, String totalPriceDeco, String advancePaymentDeco) {
        this.event = event;
        this.hall = hall;
        this.priceHallPac = priceHallPac;
        this.advanceHallPac = advanceHallPac;
        this.remainingHallPac = remainingHallPac;
        this.menu = menu;
        this.plateCount = plateCount;
        this.totalAmount = totalAmount;
        this.advanceAmount = advanceAmount;
        this.remainingAmount = remainingAmount;
        this.decorationTheme = decorationTheme;
        this.totalPriceDeco = totalPriceDeco;
        this.advancePaymentDeco = advancePaymentDeco;
    }

    // Getters and Setters
    public String getEvent() { return event; }
    public void setEvent(String event) { this.event = event; }

    public String getHall() { return hall; }
    public void setHall(String hall) { this.hall = hall; }

    public String getPriceHallPac() { return priceHallPac; }
    public void setPriceHallPac(String priceHallPac) { this.priceHallPac = priceHallPac; }

    public String getAdvanceHallPac() { return advanceHallPac; }
    public void setAdvanceHallPac(String advanceHallPac) { this.advanceHallPac = advanceHallPac; }

    public String getRemainingHallPac() { return remainingHallPac; }
    public void setRemainingHallPac(String remainingHallPac) { this.remainingHallPac = remainingHallPac; }

    public String getMenu() { return menu; }
    public void setMenu(String menu) { this.menu = menu; }

    public String getPlateCount() { return plateCount; }
    public void setPlateCount(String plateCount) { this.plateCount = plateCount; }

    public String getTotalAmount() { return totalAmount; }
    public void setTotalAmount(String totalAmount) { this.totalAmount = totalAmount; }

    public String getAdvanceAmount() { return advanceAmount; }
    public void setAdvanceAmount(String advanceAmount) { this.advanceAmount = advanceAmount; }

    public String getRemainingAmount() { return remainingAmount; }
    public void setRemainingAmount(String remainingAmount) { this.remainingAmount = remainingAmount; }

    public String getDecorationTheme() { return decorationTheme; }
    public void setDecorationTheme(String decorationTheme) { this.decorationTheme = decorationTheme; }

    public String getTotalPriceDeco() { return totalPriceDeco; }
    public void setTotalPriceDeco(String totalPriceDeco) { this.totalPriceDeco = totalPriceDeco; }

    public String getAdvancePaymentDeco() { return advancePaymentDeco; }
    public void setAdvancePaymentDeco(String advancePaymentDeco) { this.advancePaymentDeco = advancePaymentDeco; }
}

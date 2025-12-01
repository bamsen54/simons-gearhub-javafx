package com.simonsgearhubjavafx.item;

public abstract class Item {

    private int id;
    private String name;
    private String category;
    private double dailyRate;

    public Item() {
    }

    public Item(int id, String name, String category, double dailyRate) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.dailyRate = dailyRate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getDailyRate() {
        return dailyRate;
    }

    public void setDailyRate(double dailyRate) {
        this.dailyRate = dailyRate;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    abstract public String toString();
}

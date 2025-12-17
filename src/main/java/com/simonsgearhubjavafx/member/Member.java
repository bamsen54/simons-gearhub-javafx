package com.simonsgearhubjavafx.member;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.simonsgearhubjavafx.Level;
import com.simonsgearhubjavafx.rental.Rental;

public class Member {

    private int id;
    private String name;
    private Level level;
    private List<Rental> currentRentals = new ArrayList<>();
    private List<HistoryEntry> rentalHistory = new ArrayList<>();

    public Member() {
    }

    public Member(int id, String name, Level level) {
        this.id            = id;
        this.name          = name;
        this.level         = level;
    }

    public int getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public Level getLevel() {
        return this.level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    @JsonManagedReference
    public List<Rental> getCurrentRentals() {
        return currentRentals;
    }

    public List<HistoryEntry> getRentalHistory() {
        return rentalHistory;
    }

    public void addToCurrentRentals( Rental rental  ) {
        this.currentRentals.add( rental );
    }

    public String toString() {

        try {

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.id).append(" ").append(this.name).
                    append(" ").append(this.level.toString());

            return stringBuilder.toString();

        }

        catch (NullPointerException e) { }

        return "fel";
    }

    public void setId(int id) {
        this.id = id;
    }
}

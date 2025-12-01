package com.simonsgearhubjavafx.policy;


import com.simonsgearhubjavafx.rental.Rental;

public class StudentPolicy implements PricePolicy {

    private final double changeFactor = 0.85;

    public StudentPolicy() {
    }

    @Override
    public double getPrice(int days, Rental rental) {
        return changeFactor * days * rental.getItem().getDailyRate();
    }

    @Override
    public double getEntryFee() {
        return 0;
    }
}

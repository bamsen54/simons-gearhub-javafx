package com.simonsgearhubjavafx.policy;


import com.simonsgearhubjavafx.rental.Rental;

public class StandardPolicy implements PricePolicy {

    private final double changeFactor = 1.0;

    public StandardPolicy() {
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

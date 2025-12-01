package com.simonsgearhubjavafx.policy;

import com.simonsgearhubjavafx.rental.Rental;

public interface PricePolicy {

    double getPrice(int days, Rental rental);
    double getEntryFee();
}

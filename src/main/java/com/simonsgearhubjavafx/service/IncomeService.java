package com.simonsgearhubjavafx.service;

import com.simonsgearhubjavafx.Level;
import com.simonsgearhubjavafx.member.Member;
import com.simonsgearhubjavafx.policy.PremiumPolicy;
import com.simonsgearhubjavafx.policy.StandardPolicy;
import com.simonsgearhubjavafx.policy.StudentPolicy;
import com.simonsgearhubjavafx.rental.Rental;

import static java.lang.IO.println;

public class IncomeService {

    StudentPolicy studentPolicy   = new StudentPolicy();
    StandardPolicy standardPolicy = new StandardPolicy();
    PremiumPolicy premiumPolicy   = new PremiumPolicy();

    private double incomeEntryFees = 0;
    private double incomeRentalFees = 0;

    public IncomeService() {
    }

    public double getIncomeEntryFees() {
        return this.incomeEntryFees;
    }

    public void setIncomeEntryFees(double incomeEntryFees) {
        this.incomeEntryFees = incomeEntryFees;
    }

    public double getIncomeRentalFees() {
        return this.incomeRentalFees;
    }

    public void setIncomeRentalFees(double incomeRentalFees) {
        this.incomeRentalFees = incomeRentalFees;
    }

    public void addEntryFees(double incomeFees) {
        this.incomeEntryFees += incomeFees;
    }

    public void addRentaleFees(double incomeFees) {
        this.incomeRentalFees += incomeFees;
    }

    public double getTotalIncome() {
        return this.incomeEntryFees + this.incomeRentalFees;
    }

    public void handleEntryFeePaymen( Member member, Level previousLevel) {

        if( member.getLevel() == Level.PREMIUM && previousLevel != Level.PREMIUM )
            this.addEntryFees( premiumPolicy.getEntryFee() );
    }

    public void handleRentalFeePayment(Member member, int days, Rental rental) {

        switch ( member.getLevel() ) {
            case Level.STUDENT  -> this.addRentaleFees( studentPolicy.getPrice( days, rental ) );
            case Level.STANDARD -> this.addRentaleFees( standardPolicy.getPrice( days, rental ) );
            case Level.PREMIUM  -> this.addRentaleFees( premiumPolicy.getPrice( days, rental ) );
        }
    }

    public void printIncomeSummary() {

        println( "intäkter: " );
        println( "inträdesavgifter: " + this.incomeEntryFees );
        println( "hyrningsavgifter: " + this.incomeRentalFees );
        println( "total inkomst   : " + ( this.incomeEntryFees + this.incomeRentalFees ) );
        println("");
    }
}

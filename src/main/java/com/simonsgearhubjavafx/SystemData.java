package com.simonsgearhubjavafx;

import com.simonsgearhubjavafx.database.InventoryEntry;
import com.simonsgearhubjavafx.member.Member;
import com.simonsgearhubjavafx.rental.Rental;

import java.util.HashMap;
import java.util.List;

public class SystemData {

    private List<Member> members;
    private List<InventoryEntry> inventoryEntries;
    private double incomeEntryFees;
    private double incomeRentalFees;

    public SystemData() {

    }

    public SystemData(List<Member> members, List<InventoryEntry> inventoryEntries, double incomeEntryFees, double incomeRentalFees) {
        this.members = members;
        this.inventoryEntries = inventoryEntries;
        this.incomeEntryFees = incomeEntryFees;
        this.incomeRentalFees = incomeRentalFees;
    }

    public List<InventoryEntry> getInventoryEntries() {
        return this.inventoryEntries;
    }

    public void setInventory(List<InventoryEntry> inventoryEntries) {
        this.inventoryEntries = inventoryEntries;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public double getIncomeEntryFees() {
        return incomeEntryFees;
    }

    public void setIncomeEntryFees(double incomeEntryFees) {
        this.incomeEntryFees = incomeEntryFees;
    }

    public double getIncomeRentalFees() {
        return incomeRentalFees;
    }

    public void setIncomeRentalFees(double incomeRentalFees) {
        this.incomeRentalFees = incomeRentalFees;
    }
}

package com.simonsgearhubjavafx.service;

import java.time.LocalDateTime;

import com.simonsgearhubjavafx.database.InventoryEntry;
import com.simonsgearhubjavafx.member.HistoryEntry;
import com.simonsgearhubjavafx.member.Member;
import com.simonsgearhubjavafx.rental.Rental;

public class RentalService {

    public void handleRental(InventoryEntry inventoryEntry, Member member, Rental rental, IncomeService incomeService ) {
        member.addToCurrentRentals( rental );
        inventoryEntry.setQuantityInStore( inventoryEntry.getQuantityInStore() - 1 );
    }

    public void handleEndRental( InventoryEntry inventoryEntry, Member member, int index ) {
        inventoryEntry.setQuantityInStore( inventoryEntry.getQuantityInStore() + 1 );

        LocalDateTime timeOfRental = member.getCurrentRentals().get( index ).getTimeOfRental();
        LocalDateTime timeOfReturn = LocalDateTime.now();

        member.getRentalHistory().add( new HistoryEntry( inventoryEntry.getItem(), timeOfRental, timeOfReturn ) );
        member.getCurrentRentals().remove( index );
    }
}

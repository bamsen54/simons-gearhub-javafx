package com.simonsgearhubjavafx.member;

import java.time.LocalDateTime;

import com.simonsgearhubjavafx.item.Item;
import com.simonsgearhubjavafx.time.LocalDateTimeToString;

public class HistoryEntry {

    private Member member;
    private Item item;
    private LocalDateTime rentalDate;
    private LocalDateTime returnDate;

    public HistoryEntry() {
    }

    public HistoryEntry(Member member, Item item, LocalDateTime rentalDate, LocalDateTime returnDate) {
        this.member = member;
        this.item = item;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public LocalDateTime getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(LocalDateTime rentalDate) {
        this.rentalDate = rentalDate;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append( "medlem: " );
        stringBuilder.append( this.member );
        stringBuilder.append( " artikel: " );
        stringBuilder.append( this.item );
        //stringBuilder.append( " hyrning: " ).append( LocalDateTimeToString.toString( this.rentalDate ) ).append( " retur: " ).
                //append( LocalDateTimeToString.toString( this.returnDate ) );
        return stringBuilder.toString();
    }
}

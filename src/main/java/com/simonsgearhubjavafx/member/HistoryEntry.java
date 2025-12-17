package com.simonsgearhubjavafx.member;

import java.time.LocalDateTime;

import com.simonsgearhubjavafx.item.Item;
import com.simonsgearhubjavafx.time.LocalDateTimeToString;

public class HistoryEntry {

    private Member member;
    private Item item;
    private String rentalDate;
    private String returnDate;

    public HistoryEntry() {
    }

    public HistoryEntry(Member member, Item item, String rentalDate, String returnDate) {
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

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(String rentalDate) {
        this.rentalDate = rentalDate;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append( "medlem: " );
        stringBuilder.append( this.member );
        stringBuilder.append( " artikel: " );
        stringBuilder.append( this.item );
        stringBuilder.append( " hyrning: " ).append( this.rentalDate ).append( " retur: " ).
                append( this.rentalDate );
        return stringBuilder.toString();
    }
}

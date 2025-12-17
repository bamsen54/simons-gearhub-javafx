package com.simonsgearhubjavafx.rental;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.simonsgearhubjavafx.item.Item;
import com.simonsgearhubjavafx.member.Member;
import com.simonsgearhubjavafx.time.LocalDateTimeToString;

public class Rental {

    private Member member;
    private Item item;
    private String duration;
    private String rentalDate;

    public Rental() {
    }

    public Rental(Member member, Item item, String duration) {
        this.member = member;
        this.item = item;
        this.duration = duration;
        this.rentalDate = LocalDateTimeToString.toString( LocalDateTime.now() );
    }

    @JsonBackReference // <-- Jackson ignorerar denna vid serialisering
    public Member getMember() {
        return this.member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Item getItem() {
        return this.item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public String getDuration() {
        return this.duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(String rentalDate) {
        this.rentalDate = rentalDate;
    }

    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append( this.member.getId() ).append( " " );
        stringBuilder.append( this.member.getName() ).append( " " ).append( this.member.getLevel() ).append( " hyr " ).
                append( this.item ).append( " i " ).append( this.duration ).append( " dagar hyrning " ).append(  this.rentalDate );

        //stringBuilder.append( "tid: " ).append( LocalDateTimeToString.toString( this.timeOfRental ) );

        return stringBuilder.toString();
    }
}

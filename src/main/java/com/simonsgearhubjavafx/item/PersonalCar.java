package com.simonsgearhubjavafx.item;

public class PersonalCar extends Item {

    private int numberOfSeats;
    private String bodyStyle;

    public PersonalCar() {
    }

    public PersonalCar(int id, String name, String category, double dailyRate, int numberOfSeats, String bodyStyle ) {

        super( id, name, category, dailyRate );
        this.numberOfSeats = numberOfSeats;
        this.bodyStyle = bodyStyle;
    }


    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }


    public String getBodyStyle() {
        return bodyStyle;
    }

    public void setBodyStyle(String bodyStyle) {
        this.bodyStyle = bodyStyle;
    }



    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append( this.getId() );
        stringBuilder.append( " " );
        stringBuilder.append( this.getName() );
        stringBuilder.append( " " );
        stringBuilder.append( this.getCategory() );
        stringBuilder.append( " " );
        stringBuilder.append( this.numberOfSeats ).append( " säten " );
        stringBuilder.append( this.bodyStyle );
        return stringBuilder.toString();
    }
}

package com.simonsgearhubjavafx.item;

public class TravelTrailer extends Item {

    private String numberOfBeds;

    public TravelTrailer() {
        super();
    }

    public TravelTrailer(int id, String name, String category, double dailyRate, String numberOfBeds) {
        super( id, name, category, dailyRate );

        this.numberOfBeds = numberOfBeds;
    }

    public String getNumberOfBeds() {
        return this.numberOfBeds;
    }

    public void setNumberOfBeds(String numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append( "|" );
        stringBuilder.append( this.getId() );
        stringBuilder.append( "|" );
        stringBuilder.append( this.getName() );
        stringBuilder.append( "|" );
        stringBuilder.append( this.numberOfBeds );
        stringBuilder.append( "|" );
        stringBuilder.append( this.getDailyRate() );
        stringBuilder.append( "|" );

        return stringBuilder.toString();
    }
}

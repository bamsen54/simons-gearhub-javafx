package com.simonsgearhubjavafx.item;

public class Car extends Item {

    private String fuelType;
    private String cylinders;

    public Car() {
        super();
    }

    public Car(int id, String name, String category, double dailyRate) {

        super( id, name, category, dailyRate );
    }

    public Car(int id, String name, String category, double dailyRate, String fuelType, String cylinders) {
        super( id, name, category, dailyRate );

        this.fuelType = fuelType;
        this.cylinders = cylinders;
    }

    public String getCylinders() {
        return this.cylinders;
    }

    public void setCylinders(String cylinders) {
        this.cylinders = cylinders;
    }

    public String getFuelType() {
        return this.fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append( "|" );
        stringBuilder.append( this.getId() );
        stringBuilder.append( "|" );
        stringBuilder.append( this.getName() );
        stringBuilder.append( "|" );
        stringBuilder.append( this.fuelType );
        stringBuilder.append( "|" );
        stringBuilder.append( this.cylinders );
        stringBuilder.append( "|" );
        stringBuilder.append( this.getDailyRate() );
        stringBuilder.append( "|" );

        return stringBuilder.toString();
    }
}

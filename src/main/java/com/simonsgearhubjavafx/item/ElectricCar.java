package com.simonsgearhubjavafx.item;

public class ElectricCar extends Car {

    private String batteryCapacity;
    private boolean regenerativeBraking;

    public ElectricCar() {
        super();
    }

    public ElectricCar(int id, String name, String category, double dailyRate, String batteryCapacity, boolean regenerativeBraking) {
        super( id, name, category, dailyRate );
        this.batteryCapacity = batteryCapacity;
        this.regenerativeBraking = regenerativeBraking;
    }

    public String getBatteryCapacity() {
        return this.batteryCapacity;
    }

    public void setBatteryCapacity(String batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public boolean isRegenerativeBraking() {
        return regenerativeBraking;
    }

    public void setRegenerativeBraking(boolean regenerativeBraking) {
        this.regenerativeBraking = regenerativeBraking;
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append( "|" );
        stringBuilder.append( this.getId() );
        stringBuilder.append( "|" );
        stringBuilder.append( this.getName() );
        stringBuilder.append( "|" );
        stringBuilder.append( this.getBatteryCapacity() );
        stringBuilder.append( "|" );
        stringBuilder.append( this.isRegenerativeBraking() );
        stringBuilder.append( "|" );
        stringBuilder.append( this.getDailyRate() );
        stringBuilder.append( "|" );

        return stringBuilder.toString();
    }
}

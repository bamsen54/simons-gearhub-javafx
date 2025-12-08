package com.simonsgearhubjavafx.item;

public class RacingCar extends Item {


    private String racingDicipline;
    private  int horsePower;

    public RacingCar() {
    }

    public RacingCar(int id, String name, String category, double dailyRate, String racingDicipline, int horsePower) {
        super(id, name, category, dailyRate);

        this.racingDicipline = racingDicipline;
        this.horsePower = horsePower;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    public String getRacingDicipline() {
        return racingDicipline;
    }

    public void setRacingDicipline(String racingDicipline) {
        this.racingDicipline = racingDicipline;
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
        stringBuilder.append( this.racingDicipline );
        stringBuilder.append( " " );
        stringBuilder.append( this.horsePower ).append( "hp" );
        return stringBuilder.toString();
    }
}

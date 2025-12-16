package com.simonsgearhubjavafx.gui;

import com.simonsgearhubjavafx.Level;
import com.simonsgearhubjavafx.database.InventoryEntry;
import com.simonsgearhubjavafx.item.Item;
import com.simonsgearhubjavafx.item.PersonalCar;
import com.simonsgearhubjavafx.item.RacingCar;
import com.simonsgearhubjavafx.member.Member;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicReference;

public class EditRacingCarMenu {

    public static void display( InventoryEntry inventoryEntry ) {



//        AtomicReference<InventoryEntry> newInventoryEntry = new AtomicReference<>();
//
//        Stage stage = new Stage();
//
//        GridPane root = new GridPane();
//        root.getStylesheets().add( NewMemberMenu.class.getResource( "/new-inventoryentry-menu.css" ).toExternalForm() );
//
//        Label id = new Label( "Id" );
//        TextField idField = new TextField();
//
//        Label name = new Label( "Namn" );
//        TextField nameField = new TextField();
//
//        Label numberOfSeats = new Label( "Säten" );
//        TextField numberOfSeatsField = new TextField();
//
//        Label bodyStyle = new Label( "Bilkarosstyp" );
//        TextField bodyStyleField = new TextField();
//
//        Label dailyRate = new Label( "Dagspris" );
//        TextField dailyRateField = new TextField();
//
//        Label quantity = new Label( "Antal" );
//        TextField quantityField = new TextField();
//
//        idField.setText( String.valueOf( inventoryEntry.getItem().getId() ) );
//        nameField.setText( inventoryEntry.getItem().getName() );
//        PersonalCar personalCarEdit = (PersonalCar) inventoryEntry.getItem();
//        numberOfSeatsField.setText( personalCarEdit.getNumberOfSeats() + "" );
//        bodyStyleField.setText( personalCarEdit.getBodyStyle() );
//        dailyRateField.setText( personalCarEdit.getDailyRate() + "" );
//        quantityField.setText( inventoryEntry.getQuantityInStore() + "" );
//
//        Button savePersonalCarButton = new Button( "Spara personbil" );
//
//        savePersonalCarButton.setOnAction( e -> {
//
//            boolean anyEmpty = idField.getText().isEmpty() ||  nameField.getText().isEmpty() || numberOfSeats.getText().isEmpty()
//                    || numberOfSeatsField.getText().isEmpty() ||  bodyStyleField.getText().isEmpty()
//                    || bodyStyleField.getText().isEmpty() ||  quantityField.getText().isEmpty()
//                    ||  quantityField.getText().isEmpty() ||  dailyRateField.getText().isEmpty();
//            boolean numericalFieldsAreNumeric = numberOfSeatsField.getText().matches( "[0-9]+" )
//                    && quantityField.getText().matches( "[0-9]+" );
//
//            if( !anyEmpty && numericalFieldsAreNumeric) {
//
//                InventoryEntry newPersonalCarEntry = new InventoryEntry();
//                newPersonalCarEntry.setQuantityInStore(Integer.parseInt( quantityField.getText() ) );
//
//                final int ID = Integer.parseInt( idField.getText() );
//                final String NAME = nameField.getText();
//                final int NUMBER_OF_SEATS = Integer.parseInt( numberOfSeatsField.getText() );
//                final String BODY_TYPE = bodyStyleField.getText();
//                final double DAILY_RATE = Double.parseDouble( dailyRateField.getText() );
//
//                PersonalCar personalCar = new PersonalCar( ID, NAME, "personbil", DAILY_RATE, NUMBER_OF_SEATS, BODY_TYPE );
//
//                newPersonalCarEntry.setItem( personalCar );
//
//                newInventoryEntry.set( newPersonalCarEntry );
//
//                inventoryEntry.setItem( personalCar );
//
//                if( quantityField.getText().matches( "[0-9]+" ) )
//                    inventoryEntry.setQuantityInStore( Integer.parseInt( quantityField.getText() ) );
//
//                else
//                    return; // todo messege to user
//                IO.println( personalCar );
//                stage.close();
//            }
//        } );
//
//        root.add( id, 0, 0 );
//        root.add( idField, 1, 0 );
//
//        root.add( name, 0, 1 );
//        root.add( nameField, 1, 1 );
//
//        root.add( numberOfSeats, 0, 2 );
//        root.add( numberOfSeatsField, 1, 2 );
//
//        root.add( bodyStyle, 0, 3 );
//        root.add( bodyStyleField, 1, 3 );
//
//        root.add( dailyRate,  0, 4 );
//        root.add( dailyRateField, 1, 4 );
//
//        root.add( quantity, 0, 5 );
//        root.add( quantityField, 1, 5 );
//
//        root.add( savePersonalCarButton, 0, 6 );
//
//        Scene scene = new Scene( root, 400, 400 );
//        stage.setScene( scene );
//
//        stage.initModality(  Modality.APPLICATION_MODAL );
//        stage.showAndWait();

        //return newInventoryEntry.get();

        AtomicReference<InventoryEntry> newInventoryEntry = new AtomicReference<>();

        Stage stage = new Stage();

        GridPane root = new GridPane();
        root.getStylesheets().add( NewMemberMenu.class.getResource( "/new-inventoryentry-menu.css" ).toExternalForm() );

        Label id = new Label( "Id" );
        TextField idField = new TextField();

        Label name = new Label( "Namn" );
        TextField nameField = new TextField();

        Label racingDicipline          = new Label( "racingtyp" );
        TextField racingDiciplineField = new TextField();

        Label horsePower          = new Label( "Hästkrafter" );
        TextField horsePowerField = new TextField();

        Label dailyRate = new Label( "Dagspris" );
        TextField dailyRateField = new TextField();

        Label quantity = new Label( "Antal" );
        TextField quantityField = new TextField();

        idField.setText( String.valueOf( inventoryEntry.getItem().getId() ) );
        nameField.setText( inventoryEntry.getItem().getName() );
        RacingCar racingCarEdit = (RacingCar) inventoryEntry.getItem();

        racingDiciplineField.setText( racingCarEdit.getRacingDicipline() + "" );
        horsePowerField.setText( racingCarEdit.getHorsePower() + "" );
        dailyRateField.setText( racingCarEdit.getDailyRate() + "" );

        quantityField.setText( inventoryEntry.getQuantityInStore() + "" );

        Button savePersonalCarButton = new Button( "Spara personbil" );

        savePersonalCarButton.setOnAction( e -> {

            boolean anyEmpty = idField.getText().isEmpty() ||  nameField.getText().isEmpty() || racingDiciplineField.getText().isEmpty()
                    || horsePowerField.getText().isEmpty() || quantityField.getText().isEmpty()
                    ||  quantityField.getText().isEmpty() ||  dailyRateField.getText().isEmpty();
            boolean numericalFieldsAreNumeric = horsePowerField.getText().matches("[0-9]+" )
                    && quantityField.getText().matches( "[0-9]+" );

            if( !anyEmpty && numericalFieldsAreNumeric) {

                InventoryEntry newRacingCarEntry = new InventoryEntry();
                newRacingCarEntry.setQuantityInStore(Integer.parseInt( quantityField.getText() ) );

                try {
                    final int ID = Integer.parseInt(idField.getText());
                    final String RACING_DICIPLINE = racingDiciplineField.getText();
                    final double DAILY_RATE = Double.parseDouble(dailyRateField.getText());
                    final String NAME = nameField.getText();
                    final int HORSE_POWER = Integer.parseInt( horsePowerField.getText() );

                    RacingCar racingCar = new RacingCar( ID, NAME, "Racingbil", DAILY_RATE, RACING_DICIPLINE, HORSE_POWER );

                    newRacingCarEntry.setItem( racingCar );

                    newInventoryEntry.set( newRacingCarEntry );

                    inventoryEntry.setItem( racingCar );
                    inventoryEntry.setQuantityInStore( Integer.parseInt( quantityField.getText() ) );
                }

                catch ( NumberFormatException ex ) {
                    // todo message to user
                    return;
                }

                stage.close();
            }
        } );

        root.add( id, 0, 0 );
        root.add( idField, 1, 0 );

        root.add( name, 0, 1 );
        root.add( nameField, 1, 1 );

        root.add( racingDicipline, 0, 2 );
        root.add( racingDiciplineField, 1, 2 );

        root.add( horsePower, 0, 3 );
        root.add( horsePowerField, 1, 3 );

        root.add( dailyRate,  0, 4 );
        root.add( dailyRateField, 1, 4 );

        root.add( quantity, 0, 5 );
        root.add( quantityField, 1, 5 );

        root.add( savePersonalCarButton, 0, 6 );

        Scene scene = new Scene( root, 400, 400 );
        stage.setScene( scene );

        stage.initModality(  Modality.APPLICATION_MODAL );
        stage.showAndWait();
    }
}

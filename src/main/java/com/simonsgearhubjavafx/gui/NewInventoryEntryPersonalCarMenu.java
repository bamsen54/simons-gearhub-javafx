package com.simonsgearhubjavafx.gui;

import com.simonsgearhubjavafx.database.Inventory;
import com.simonsgearhubjavafx.database.InventoryEntry;
import com.simonsgearhubjavafx.item.PersonalCar;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicReference;

public class NewInventoryEntryPersonalCarMenu {

    public static InventoryEntry display( Inventory inventory  ) {

        AtomicReference<InventoryEntry> newInventoryEntry = new AtomicReference<>();

        Stage stage = new Stage();

        GridPane root = new GridPane();
        root.getStylesheets().add( NewMemberMenu.class.getResource( "/new-inventoryentry-menu.css" ).toExternalForm() );

        Label id = new Label( "Id" );
        TextField idField = new TextField();

        Label name = new Label( "Namn" );
        TextField nameField = new TextField();

        Label numberOfSeats = new Label( "Säten" );
        TextField numberOfSeatsField = new TextField();

        Label bodyStyle = new Label( "Bilkarosstyp" );
        TextField bodyStyleField = new TextField();

        Label dailyRate = new Label( "Dagspris" );
        TextField dailyRateField = new TextField();

        Label quantity = new Label( "Antal" );
        TextField quantityField = new TextField();

        Button savePersonalCarButton = new Button( "Spara personbil" );

        savePersonalCarButton.setOnAction( e -> {

            boolean anyEmpty = idField.getText().isEmpty() ||  nameField.getText().isEmpty() || numberOfSeats.getText().isEmpty()
                                                           || numberOfSeatsField.getText().isEmpty() ||  bodyStyleField.getText().isEmpty()
                                                           || bodyStyleField.getText().isEmpty() ||  quantityField.getText().isEmpty()
                                                           ||  quantityField.getText().isEmpty() ||  dailyRateField.getText().isEmpty();
            boolean numericalFieldsAreNumeric = numberOfSeatsField.getText().matches( "[0-9]+" )
                                              && quantityField.getText().matches( "[0-9]+" );

            if( !anyEmpty && numericalFieldsAreNumeric) {

                try {

                    InventoryEntry newPersonalCarEntry = new InventoryEntry();
                    newPersonalCarEntry.setQuantityInStore(Integer.parseInt(quantityField.getText()));

                    final int ID = Integer.parseInt( idField.getText() );
                    final String NAME = nameField.getText();
                    final int NUMBER_OF_SEATS = Integer.parseInt(numberOfSeatsField.getText());
                    final String BODY_TYPE = bodyStyleField.getText();
                    final double DAILY_RATE = Double.parseDouble(dailyRateField.getText());

                    PersonalCar personalCar = new PersonalCar(ID, NAME, "Personbil", DAILY_RATE, NUMBER_OF_SEATS, BODY_TYPE);

                    newPersonalCarEntry.setItem(personalCar);

                    if ( inventory.getInventory().containsKey( ID ) ) {
                        AlertBox.display("id", "artikel med det id:t finns redan");
                        stage.close();
                        return;
                    }

                    newInventoryEntry.set(newPersonalCarEntry);

                    stage.close();
                }

                catch ( NumberFormatException ex ) {

                }
            }

            else {
                AlertBox.display( "format", "se till att inga fält är tomma och att de som ska innehålla heltal gör det" );

                stage.close();
            }
        } );

        root.add( id, 0, 0 );
        root.add( idField, 1, 0 );

        root.add( name, 0, 1 );
        root.add( nameField, 1, 1 );

        root.add( numberOfSeats, 0, 2 );
        root.add( numberOfSeatsField, 1, 2 );

        root.add( bodyStyle, 0, 3 );
        root.add( bodyStyleField, 1, 3 );

        root.add( dailyRate,  0, 4 );
        root.add( dailyRateField, 1, 4 );

        root.add( quantity, 0, 5 );
        root.add( quantityField, 1, 5 );

        root.add( savePersonalCarButton, 0, 6 );

        Scene scene = new Scene( root, 400, 400 );
        stage.setScene( scene );

        stage.initModality(  Modality.APPLICATION_MODAL );
        stage.showAndWait();


        return newInventoryEntry.get();
    }
}

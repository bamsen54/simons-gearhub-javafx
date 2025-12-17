package com.simonsgearhubjavafx.gui;

import com.simonsgearhubjavafx.Level;
import com.simonsgearhubjavafx.database.Inventory;
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
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicReference;

public class NewInventoryEntryRacingCarMenu {

    public static InventoryEntry display( Inventory inventory ) {

        AtomicReference<InventoryEntry> newInventoryEntry = new AtomicReference<>();

        Stage stage = new Stage();
        stage.setTitle( "Ny Racingbil" );
        stage.setResizable( false );

        GridPane root = new GridPane();
        root.getStylesheets().add( NewMemberMenu.class.getResource( "/forms.css" ).toExternalForm() );

        Label id = new Label( "Id" );
        TextField idField = new TextField();

        Label name = new Label( "Namn" );
        TextField nameField = new TextField();

        Label racingDicipline     = new Label( "Racing typ" );
        TextField racingDiciplineField = new TextField();

        Label horsePower = new Label( "Hästkraft" );
        TextField horsePowerField = new TextField();

        Label dailyRate = new Label( "Dagspris" );
        TextField dailyRateField = new TextField();

        Label quantity = new Label( "Antal" );
        TextField quantityField = new TextField();

        Button saveRacingCarButton = new Button( "Spara racingbil" );

        saveRacingCarButton.setOnAction( e -> {

            boolean anyEmpty = idField.getText().isEmpty() ||  nameField.getText().isEmpty() || racingDiciplineField.getText().isEmpty()
                    || racingDiciplineField.getText().isEmpty() ||  horsePowerField.getText().isEmpty()
                    || horsePowerField.getText().isEmpty() ||  quantityField.getText().isEmpty()
                    ||  quantityField.getText().isEmpty() ||  dailyRateField.getText().isEmpty();
            boolean numericalFieldsAreNumeric = quantityField.getText().matches( "[0-9]+" ) && horsePowerField.getText().matches( "[0-9]+" );

            if( !anyEmpty && numericalFieldsAreNumeric) {

                try {
                    InventoryEntry newPersonalCarEntry = new InventoryEntry();
                    newPersonalCarEntry.setQuantityInStore(Integer.parseInt(quantityField.getText()));

                    final int ID = Integer.parseInt(idField.getText());
                    final String NAME = nameField.getText();
                    final String RACING_DICIPLINE = racingDiciplineField.getText();
                    final int HORSE_POWER = Integer.parseInt(horsePowerField.getText());
                    final double DAILY_RATE = Double.parseDouble(dailyRateField.getText());

                    RacingCar racingCar = new RacingCar(ID, NAME, "Racingbil", DAILY_RATE, RACING_DICIPLINE, HORSE_POWER);

                    boolean idAlreadyExists = false;

                    for( int ids: inventory.getInventory().keySet() ) {

                        InventoryEntry entry = inventory.getInventory().get( ids );

                        if( entry.getId() == ID )
                            idAlreadyExists = true;
                    }

                    if ( idAlreadyExists ) {
                        AlertBox.display("id", "artikel med det id:t finns redan");
                        stage.close();
                        return;
                    }

                    newInventoryEntry.set(newPersonalCarEntry);

                    stage.close();

                }

                catch ( NumberFormatException ex ) {}
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

        root.add( racingDicipline, 0, 2 );
        root.add( racingDiciplineField, 1, 2 );

        root.add( horsePower, 0, 3 );
        root.add( horsePowerField, 1, 3 );

        root.add( dailyRate,  0, 4 );
        root.add( dailyRateField, 1, 4 );

        root.add( quantity, 0, 5 );
        root.add( quantityField, 1, 5 );

        root.add( saveRacingCarButton, 0, 6 );

        Scene scene = new Scene( root, 500, 500 );
        stage.setScene( scene );

        stage.initModality(  Modality.APPLICATION_MODAL );
        stage.showAndWait();

        return newInventoryEntry.get();
    }
}

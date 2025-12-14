package com.simonsgearhubjavafx.gui;

import com.simonsgearhubjavafx.database.Inventory;
import com.simonsgearhubjavafx.database.InventoryEntry;
import com.simonsgearhubjavafx.member.Member;
import com.simonsgearhubjavafx.rental.Rental;
import com.simonsgearhubjavafx.service.IncomeService;
import com.simonsgearhubjavafx.service.MembershipService;
import com.simonsgearhubjavafx.service.RentalService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.function.Predicate;

public class RentForChosenMemberMenu {

    MembershipService memberShipService;
    RentalService rentalService;
    Inventory inventory;
    IncomeService incomeService;

    final ObservableList<InventoryEntry> inventoryEntryList = FXCollections.observableArrayList();
    FilteredList<InventoryEntry> filteredList = new FilteredList( inventoryEntryList );

    Predicate<InventoryEntry> predicate = null;

    RentForChosenMemberMenu(MembershipService memberShipService, RentalService rentalService, Inventory inventory, IncomeService incomeService) {

        this.memberShipService = memberShipService;
        this.rentalService     = rentalService;
        this.inventory         = inventory;
        this.incomeService     = incomeService;
    }

    public void display(Member member) {

        BorderPane root = new BorderPane();
        root.getStylesheets().add( MemberMenu.class.getResource("/rent-for-chosen-member-menu.css").toExternalForm() );

        ListView items = new ListView();
        items.setItems( inventoryEntryList );
        items.setPrefWidth( 500 );
        this.updateObservableList();

        root.setCenter( items );

        HBox topInfo = new HBox();

        Label memberInfo = new Label( member.toString() );
        topInfo.getChildren().addAll( memberInfo );
        topInfo.setAlignment( Pos.CENTER );
        root.setTop( topInfo );

        Label days = new Label( "dagar" );
        TextField daysField = new TextField();
        VBox buttonsAndDays = new VBox();

        HBox daysBox = new HBox();
        daysBox.setAlignment( Pos.CENTER );
        daysBox.setSpacing( 10 );
        daysBox.getChildren().addAll( days, daysField );

        Button rent = new  Button( "Hyr" );
        rent.setOnAction( e -> {

            try {
                int daysToRent = Integer.parseInt(daysField.getText() );

                InventoryEntry inventoryEntrySelected = (InventoryEntry) items.getSelectionModel().getSelectedItem();

                if( inventoryEntrySelected.getQuantityInStore() <= 0 )
                    return;

                Rental rental = new Rental(member, inventoryEntrySelected.getItem(), daysField.getText());

                member.addToCurrentRentals(rental);
                inventoryEntrySelected.setQuantityInStore(inventoryEntrySelected.getQuantityInStore() - 1);

                this.updateObservableList();

                incomeService.handleRentalFeePayment(member, daysToRent, rental);
            }

            catch ( NumberFormatException ex ) {
                // TODO
            }

            catch ( NullPointerException ex ) {
                // TODO
            }

        } );

        buttonsAndDays.setAlignment( Pos.CENTER );
        buttonsAndDays.getChildren().addAll( daysBox, rent );

        root.setBottom( buttonsAndDays );

        Scene scene = new Scene(root, 800, 600 );
        Stage stage = new Stage();
        stage.setScene( scene );
        stage.initModality( Modality.APPLICATION_MODAL );
        stage.showAndWait();
    }

    public void updateObservableList() {
        inventoryEntryList.clear();
        for( int key: inventory.getInventory().keySet() )
            inventoryEntryList.add( inventory.getInventory().get( key ) );
    }

}

package com.simonsgearhubjavafx.gui;

import com.simonsgearhubjavafx.database.Inventory;
import com.simonsgearhubjavafx.database.InventoryEntry;
import com.simonsgearhubjavafx.member.HistoryEntry;
import com.simonsgearhubjavafx.member.Member;
import com.simonsgearhubjavafx.rental.Rental;
import com.simonsgearhubjavafx.service.IncomeService;
import com.simonsgearhubjavafx.service.MembershipService;
import com.simonsgearhubjavafx.service.RentalService;
import com.simonsgearhubjavafx.time.LocalDateTimeToString;
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

import java.time.LocalDateTime;
import java.util.function.Predicate;

public class ReturnForChosenMemberMenu {

    MembershipService memberShipService;
    RentalService rentalService;
    Inventory inventory;
    IncomeService incomeService;

    final ObservableList<Rental> rentalList = FXCollections.observableArrayList();
    FilteredList<Rental> filteredList = new FilteredList( rentalList );

    Predicate<InventoryEntry> predicate = null;

    ReturnForChosenMemberMenu(MembershipService memberShipService, RentalService rentalService, Inventory inventory, IncomeService incomeService) {

        this.memberShipService = memberShipService;
        this.rentalService     = rentalService;
        this.inventory         = inventory;
        this.incomeService     = incomeService;
    }

    public void display(Member member) {

        BorderPane root = new BorderPane();
        root.getStylesheets().add( MemberMenu.class.getResource( "/rent-for-chosen-member-menu.css").toExternalForm() );

        ListView rentals = new ListView();
        rentals.setItems( rentalList );
        rentals.setPrefWidth( 500 );
        this.updateObservableList( member );

        root.setCenter( rentals );

        HBox topInfo = new HBox();

        Label memberInfo = new Label( member.toString() );
        topInfo.getChildren().addAll( memberInfo );
        topInfo.setAlignment( Pos.CENTER );
        root.setTop( topInfo );

        HBox buttons = new HBox();
        Button returnButton = new Button( "Återlämna" );

        returnButton.setOnAction( e -> {
            Rental rental = (Rental) rentals.getSelectionModel().getSelectedItem();

            member.getCurrentRentals().remove( rental );

            member.getRentalHistory().add( new HistoryEntry( member, rental.getItem(), null, null ) );

            final int id = rental.getItem().getId();

            if( inventory.getInventory().get( id ) != null ) {

                InventoryEntry inventoryEntryWithID = inventory.getInventory().get( id );
                inventoryEntryWithID.setQuantityInStore( inventoryEntryWithID.getQuantityInStore() + 1 );
            }

            else
                inventory.getInventory().put( id, new InventoryEntry( rental.getItem(), 1 ) );

            this.updateObservableList( member );
        } );

        buttons.getChildren().add( returnButton );
        buttons.setAlignment( Pos.CENTER );
        root.setBottom( buttons );

        Scene scene = new Scene( root, 800, 600 );
        Stage stage = new Stage();
        stage.setScene( scene );
        stage.initModality( Modality.APPLICATION_MODAL );
        stage.showAndWait();
    }

    public void updateObservableList(Member member) {
        rentalList.clear();
        for( Rental rental: member.getCurrentRentals() )
            rentalList.add( rental );
    }
}

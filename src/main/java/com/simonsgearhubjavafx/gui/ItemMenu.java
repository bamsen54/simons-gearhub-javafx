package com.simonsgearhubjavafx.gui;

import com.simonsgearhubjavafx.Level;
import com.simonsgearhubjavafx.database.Inventory;
import com.simonsgearhubjavafx.database.InventoryEntry;
import com.simonsgearhubjavafx.item.Item;
import com.simonsgearhubjavafx.item.PersonalCar;
import com.simonsgearhubjavafx.member.Member;
import com.simonsgearhubjavafx.regex.Regex;
import com.simonsgearhubjavafx.service.IncomeService;
import com.simonsgearhubjavafx.service.MembershipService;
import com.simonsgearhubjavafx.service.RentalService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.function.Predicate;
import java.util.regex.PatternSyntaxException;

public class ItemMenu {

    MembershipService memberShipService;
    RentalService rentalService;
    Inventory inventory;
    IncomeService incomeService;


    final ObservableList<InventoryEntry> inventoryEntryList = FXCollections.observableArrayList();
    FilteredList<InventoryEntry> filteredList = new FilteredList( inventoryEntryList );

    Predicate<Item> predicate = null;

    public ItemMenu(MembershipService memberShipService, RentalService rentalService, Inventory inventory, IncomeService incomeService) {

        this.memberShipService = memberShipService;
        this.rentalService     = rentalService;
        this.inventory         = inventory;
        this.incomeService     = incomeService;
    }

    public void display() {

        BorderPane root = new BorderPane();
        root.getStylesheets().add( MemberMenu.class.getResource("/item-menu.css").toExternalForm() );

        ListView items = new ListView();
        items.setItems( inventoryEntryList );
        this.updateObservableList();

        root.setLeft( items );

        HBox buttons = new HBox();
        Button addPersonalCarButton   = new Button( "Ny Personbil" );
        Button addRacingCarButton = new Button( "Ny Racingbil" );
        Button editItemButton   = new Button( "Ändra Artikel" );
        Button removeItemButton = new Button( "Ta Bort Artikel" );
        buttons.getChildren().addAll( addPersonalCarButton, addRacingCarButton, editItemButton, removeItemButton );

        addPersonalCarButton.setOnAction( e -> {
            InventoryEntry newInventoryEntry = NewInventoryEntryPersonalCarMenu.display();

            IO.println( newInventoryEntry );
            if( newInventoryEntry != null ) {
                inventory.getInventory().put( newInventoryEntry.getId(), newInventoryEntry );
                this.updateObservableList();
            }
        } );

        addRacingCarButton.setOnAction( e -> {
            InventoryEntry newInventoryEntry = NewInventoryEntryRacingCarMenu.display();

            IO.println( newInventoryEntry );
            if( newInventoryEntry != null ) {
                inventory.getInventory().put( newInventoryEntry.getId(), newInventoryEntry );
                this.updateObservableList();
            }
        } );

        editItemButton.setOnAction( e -> {
            InventoryEntry inventoryEntryToEdit = (InventoryEntry) items.getSelectionModel().getSelectedItem();
            IO.println( inventoryEntryToEdit );

            if( inventoryEntryToEdit.getItem() instanceof PersonalCar ) {
                //try {
                    EditPersonalCarMenu.display( inventoryEntryToEdit );
                    updateObservableList();
                //}

                //catch ( RuntimeException ex ) {
                  //  IO.println( "exception" );
                //}
            }
        } );

        buttons.setAlignment( Pos.CENTER );
        root.setBottom( buttons );

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
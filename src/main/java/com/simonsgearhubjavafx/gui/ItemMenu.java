package com.simonsgearhubjavafx.gui;

import com.simonsgearhubjavafx.database.Inventory;
import com.simonsgearhubjavafx.database.InventoryEntry;
import com.simonsgearhubjavafx.item.PersonalCar;
import com.simonsgearhubjavafx.item.RacingCar;
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

    Predicate<InventoryEntry> predicate = null;

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
        items.setPrefWidth( 500 );
        this.updateObservableList();

        root.setLeft( items );

        HBox buttons = new HBox();
        Button addPersonalCarButton   = new Button( "Ny Personbil" );
        Button addRacingCarButton = new Button( "Ny Racingbil" );
        Button editItemButton   = new Button( "Ändra Artikel" );
        Button removeItemButton = new Button( "Ta Bort Artikel" );
        buttons.getChildren().addAll( addPersonalCarButton, addRacingCarButton, editItemButton, removeItemButton );

        addPersonalCarButton.setOnAction( e -> {
            InventoryEntry newInventoryEntry = NewInventoryEntryPersonalCarMenu.display( inventory );

            IO.println( newInventoryEntry );
            if( newInventoryEntry != null ) {
                inventory.getInventory().put( newInventoryEntry.getId(), newInventoryEntry );
                this.updateObservableList();
            }
        } );

        addRacingCarButton.setOnAction( e -> {
            InventoryEntry newInventoryEntry = NewInventoryEntryRacingCarMenu.display( inventory );

            IO.println( newInventoryEntry );
            if ( newInventoryEntry != null ) {
                inventory.getInventory().put( newInventoryEntry.getId(), newInventoryEntry );
                this.updateObservableList();
            }
        } );

        editItemButton.setOnAction( e -> {
            InventoryEntry inventoryEntryToEdit = (InventoryEntry) items.getSelectionModel().getSelectedItem();
            IO.println( inventoryEntryToEdit );

            try {

                if ( inventoryEntryToEdit.getItem() instanceof PersonalCar ) {
                    EditPersonalCarMenu.display( inventoryEntryToEdit, inventory );
                    updateObservableList();
                } else if (inventoryEntryToEdit.getItem() instanceof RacingCar) {
                    EditRacingCarMenu.display( inventoryEntryToEdit, inventory );
                    updateObservableList();
                }
            }

            catch (NullPointerException ex) {}
        } );

        buttons.setAlignment( Pos.CENTER );
        root.setBottom( buttons );

        HBox search = new HBox();

        Label searchMethod = new Label( "Söksätt" );
        searchMethod.setId( "search-method-label" );

        ComboBox<String> searchBox = new ComboBox<>();
        searchBox.setValue( "id" );
        searchBox.getItems().addAll( "id", "find", "match", "kategori" );

        TextField searchField = new TextField();

        Button searchButton = new Button( "Sök" );

        searchButton.setOnAction( e -> {

            searchAndFilter( items,  searchBox, searchField );

        } );

        search.getChildren().addAll( searchMethod, searchBox, searchField, searchButton );
        search.setAlignment( Pos.CENTER );
        search.setSpacing( 5 );
        search.setPadding(new Insets(20, 20, 20, 20));

        root.setTop( search );

        removeItemButton.setOnAction( e -> {

            try {
                InventoryEntry inventoryEntryToRemove =  (InventoryEntry) items.getSelectionModel().getSelectedItem();
                inventory.getInventory().remove( inventoryEntryToRemove.getId() );
                inventoryEntryList.remove( inventoryEntryToRemove  );
            }



            catch ( NullPointerException ex ) {}
        } );

        Scene scene = new Scene(root, 800, 600 );
        Stage stage = new Stage();
        stage.setScene( scene );
        stage.setTitle( "Artiklar" );
        stage.setResizable( false );
        stage.initModality( Modality.APPLICATION_MODAL );
        stage.showAndWait();
    }

    public void searchAndFilter(ListView items, ComboBox searchBox, TextField searchField ) {


        if( searchBox.getValue().equals( "id" ) ) {

            predicate = inventoryEntry -> inventoryEntry.getId() == Integer.parseInt( searchField.getText() );

            if( searchField.getText().isEmpty() )
                predicate = null;

            if( !searchField.getText().matches(  "[0-9]+" ) )
                predicate = null;

            filteredList.setPredicate( predicate );

            items.setItems( filteredList );
        }


        else if( searchBox.getValue().equals( "find" ) ) {

            try {
                predicate = inventoryEntry -> Regex.isFound( inventoryEntry.getItem().getName(), searchField.getText() );
                filteredList.setPredicate( predicate );
                items.setItems( filteredList );
            }

            catch ( PatternSyntaxException ex ) {}
        }

        else if( searchBox.getValue().equals( "match" ) ) {

            try {
                predicate = inventoryEntry -> Regex.isMatch( inventoryEntry.getItem().getName(), searchField.getText() );
                filteredList.setPredicate( predicate );
                items.setItems( filteredList );
            }

            catch ( PatternSyntaxException ex ) {}
        }

        else if( searchBox.getValue().equals( "kategori" ) ) {

            if( searchField.getText().isEmpty() )
                return;

            try {
                predicate = inventoryEntry -> inventoryEntry.getItem().getCategory().equals( searchField.getText() );
                filteredList.setPredicate( predicate );
                items.setItems( filteredList );
            }

            catch ( PatternSyntaxException ex ) {}
        }
    }

    public void updateObservableList() {
        inventoryEntryList.clear();
        for( int key: inventory.getInventory().keySet() )
            inventoryEntryList.add( inventory.getInventory().get( key ) );
    }
}
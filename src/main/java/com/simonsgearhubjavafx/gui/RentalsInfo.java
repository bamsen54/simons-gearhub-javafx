package com.simonsgearhubjavafx.gui;

import com.simonsgearhubjavafx.database.Inventory;
import com.simonsgearhubjavafx.member.HistoryEntry;
import com.simonsgearhubjavafx.member.Member;
import com.simonsgearhubjavafx.rental.Rental;
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

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class RentalsInfo {

    MembershipService memberShipService;
    RentalService rentalService;
    Inventory inventory;
    IncomeService incomeService;

    ComboBox<String> historyOrActive;

    final ObservableList<Rental> activeRentals = FXCollections.observableArrayList();
    final ObservableList<HistoryEntry> historyEntries = FXCollections.observableArrayList();

    public RentalsInfo(MembershipService memberShipService, RentalService rentalService, Inventory inventory, IncomeService incomeService) {

        this.memberShipService = memberShipService;
        this.rentalService     = rentalService;
        this.inventory         = inventory;
        this.incomeService     = incomeService;
    }

    public void display() {

        BorderPane root = new BorderPane();
        root.getStylesheets().add( MemberMenu.class.getResource("/member-menu.css").toExternalForm() );

        ListView rentals = new ListView();
        rentals.setPrefWidth( 800 );
        rentals.setItems( activeRentals );

        root.setLeft( rentals );

        HBox combo = new HBox();
        historyOrActive =  new ComboBox();
        historyOrActive.getItems().addAll( "Aktiva", "Historia" );
        historyOrActive.setValue( "Aktiva" );
        combo.getChildren().addAll( historyOrActive );
        combo.setAlignment( Pos.CENTER );
        root.setTop(  combo );

        historyOrActive.setOnAction( e -> {

            if( historyOrActive.getValue().equals( "Aktiva" ) ) {
                rentals.setItems( activeRentals );
            }

            else
                rentals.setItems( historyEntries );

            updateObservableList();
        } );

        this.updateObservableList();

        Scene scene = new Scene( root, 800, 600  );
        Stage stage = new Stage();

        stage.initModality( Modality.APPLICATION_MODAL );
        stage.setTitle( "Hyrningar" );
        stage.setScene( scene );
        stage.showAndWait();
    }

    public void updateObservableList() {

        activeRentals.clear();
        historyEntries.clear();
        for( int key: memberShipService.getMemberRegistry().getMembers().keySet() ) {

            Member member = memberShipService.getMemberRegistry().getMember( key );

            activeRentals.addAll( member.getCurrentRentals() );
            historyEntries.addAll( member.getRentalHistory() );
        }
    }
}
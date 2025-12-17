package com.simonsgearhubjavafx.gui;

import com.simonsgearhubjavafx.Level;
import com.simonsgearhubjavafx.database.Inventory;
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

import java.sql.SQLException;
import java.util.function.Predicate;
import java.util.regex.PatternSyntaxException;

public class MemberMenu {

    MembershipService memberShipService;
    RentalService rentalService;
    Inventory inventory;
    IncomeService incomeService;


    final ObservableList<Member> membersList = FXCollections.observableArrayList();
    FilteredList<Member> filteredList = new FilteredList<>( membersList );

    Predicate<Member> predicate = null;

    public MemberMenu(MembershipService memberShipService, RentalService rentalService, Inventory inventory, IncomeService incomeService) {

        this.memberShipService = memberShipService;
        this.rentalService     = rentalService;
        this.inventory         = inventory;
        this.incomeService     = incomeService;
    }

    public void display() {

        BorderPane root = new BorderPane();
        root.getStylesheets().add( MemberMenu.class.getResource("/member-menu.css").toExternalForm() );

        ListView members = new ListView();
        members.setItems( membersList );
        this.updateObservableList();

        root.setLeft( members );

        HBox buttons = new HBox();
        Button addMemberButton    = new Button( "Ny Medlem" );
        Button editMemberButton   = new Button( "Ändra Medlem" );
        Button removeMemberButton = new Button( "Ta Bort Medlem" );

        addMemberButton.setOnAction( e -> {

            try {
                Member newMember = new Member();
                NewMemberMenu.display( newMember, memberShipService, incomeService );


                if ( newMember != null && memberShipService.getMemberWithID( newMember.getId() ) == null ) {
                    memberShipService.getMemberRegistry().addMember(newMember);
                    this.updateObservableList();
                }
            }

            catch ( NullPointerException ex ) { }
        } );

        editMemberButton.setOnAction( e -> {

            try {
                Member member = (Member) members.getSelectionModel().getSelectedItem();

                EditMemberMenu.display( member, memberShipService, incomeService );
                updateObservableList();

                for( int key: memberShipService.getMemberRegistry().getMembers().keySet() )
                    IO.println( memberShipService.getMemberRegistry().getMembers().get(key) );
            }

            catch ( IndexOutOfBoundsException ex ) {
                IO.println( "Du måste trycka på en medlem" );
                // todo alert box
            }

            catch ( NullPointerException ex ) {}
        } );

        removeMemberButton.setOnAction( e -> {
            try {
                Member memberToRemove = (Member) members.getSelectionModel().getSelectedItem();
                memberShipService.getMemberRegistry().removeMember( memberToRemove );
                membersList.remove( memberToRemove );
            }

            catch ( IndexOutOfBoundsException ex ) {
                IO.println( "Du måste trycka på en medlem" );
                // todo alert box
            }

            catch ( NullPointerException ex ) {}
        } );

        buttons.getChildren().addAll( addMemberButton,  editMemberButton, removeMemberButton );
        buttons.setAlignment( Pos.CENTER );

        root.setBottom( buttons );

        HBox search = new HBox();

        Label searchMethod = new Label( "Söksätt" );
        searchMethod.setId( "search-method-label" );

        ComboBox<String> searchBox = new ComboBox<>();
        searchBox.setValue( "id" );
        searchBox.getItems().addAll( "id", "find", "match", "medlemsnivå" );

        TextField searchField = new TextField();

        Button searchButton = new Button( "Sök" );

        searchButton.setOnAction( e -> {

            searchAndFilter( members,  searchBox, searchField );

        } );

        search.getChildren().addAll( searchMethod, searchBox, searchField, searchButton );
        search.setAlignment( Pos.CENTER );
        search.setSpacing( 5 );
        search.setPadding(new Insets(20, 20, 20, 20));

        root.setTop( search );

        Scene scene = new Scene( root, 800, 600  );
        Stage stage = new Stage();

        stage.initModality( Modality.APPLICATION_MODAL );
        stage.setTitle( "Medlemmar" );
        stage.setScene( scene );
        stage.showAndWait();
    }

    public void updateObservableList() {
        membersList.clear();
        for( int key: memberShipService.getMemberRegistry().getMembers().keySet() )
            membersList.add( memberShipService.getMemberRegistry().getMembers().get( key ) );
    }

    public void searchAndFilter( ListView members, ComboBox searchBox, TextField searchField  ) {

        if( searchBox.getValue().equals( "id" ) ) {

            predicate = member -> member.getId() == Integer.parseInt( searchField.getText() );

            if( searchField.getText().isEmpty() )
                predicate = null;

            if( !searchField.getText().matches(  "[0-9]+" ) )
                predicate = null;

            filteredList.setPredicate( predicate );

            members.setItems( filteredList );
        }

        else if( searchBox.getValue().equals( "find" ) ) {

            try {
                predicate = member -> Regex.isFound( member.getName(), searchField.getText() );
                filteredList.setPredicate( predicate );
                members.setItems( filteredList );
            }

            catch ( PatternSyntaxException ex ) {}
        }

        else if( searchBox.getValue().equals( "match" ) ) {

            try {
                predicate = member -> Regex.isMatch( member.getName(), searchField.getText() );
                filteredList.setPredicate( predicate );
                members.setItems( filteredList );
            }

            catch ( PatternSyntaxException ex ) {}
        }

        else if( searchBox.getValue().equals( "medlemsnivå" ) ) {

            try {

                predicate = member -> member.getLevel() == Level.valueOf( searchField.getText().toUpperCase() );

                if( searchField.getText().isEmpty() )
                    predicate = null;
                try {
                    filteredList.setPredicate(predicate);
                    members.setItems(filteredList);
                }

                catch ( IllegalArgumentException ex ) {}
            }

            catch ( PatternSyntaxException ex ) {}
        }
    }
}
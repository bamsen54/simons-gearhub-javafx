package com.simonsgearhubjavafx.gui;

import com.simonsgearhubjavafx.Level;
import com.simonsgearhubjavafx.database.Inventory;
import com.simonsgearhubjavafx.member.Member;
import com.simonsgearhubjavafx.service.IncomeService;
import com.simonsgearhubjavafx.service.MembershipService;
import com.simonsgearhubjavafx.service.RentalService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.DefaultStringConverter;

import java.util.Map;
import java.util.Random;

public class MemberMenu {

    MembershipService memberShipService;
    RentalService rentalService;
    Inventory inventory;
    IncomeService incomeService;


    ObservableList<Member> membersList = FXCollections.observableArrayList();


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
            Member newMember = NewMemberMenu.display();

            if( newMember != null ) {
                memberShipService.addNewMember(newMember, incomeService);
                this.updateObservableList();
            }
        } );

        editMemberButton.setOnAction( e -> {

            try {
                int index = members.getSelectionModel().getSelectedIndex();
                Member member = membersList.get(index);
                EditMemberMenu.display( member, incomeService );
                updateObservableList();
            }

            catch ( IndexOutOfBoundsException ex ) {
                IO.println( "Du måste trycka på en medlem" );
                // todo alert box
            }
        } );

        removeMemberButton.setOnAction( e -> {
            try {
                int index = members.getSelectionModel().getSelectedIndex();
                membersList.remove( index );
            }

            catch ( IndexOutOfBoundsException ex ) {
                IO.println( "Du måste trycka på en medlem" );
                // todo alert box
            }
        } );

        buttons.getChildren().addAll( addMemberButton,  editMemberButton, removeMemberButton );
        buttons.setAlignment( Pos.CENTER );

        root.setBottom( buttons );

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
}

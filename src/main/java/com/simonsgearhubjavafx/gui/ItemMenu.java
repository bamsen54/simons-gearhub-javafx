package com.simonsgearhubjavafx.gui;

import com.simonsgearhubjavafx.database.Inventory;
import com.simonsgearhubjavafx.item.Item;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ItemMenu {

    MembershipService memberShipService;
    RentalService rentalService;
    Inventory inventory;
    IncomeService incomeService;

    ObservableList<Item> articleList = FXCollections.observableArrayList();

    public ItemMenu(MembershipService memberShipService, RentalService rentalService, Inventory inventory, IncomeService incomeService) {

        this.memberShipService = memberShipService;
        this.rentalService     = rentalService;
        this.inventory         = inventory;
        this.incomeService     = incomeService;
    }

    public void display() {

        BorderPane root = new BorderPane();
        root.getStylesheets().add( MemberMenu.class.getResource("/member-menu.css").toExternalForm() );

        ListView members = new ListView();
        members.setItems( articleList );
        this.updateObservableList();

        root.setLeft( members );

        HBox buttons = new HBox();
        Button addMemberButton = new Button( "Ny Medlem" );

        addMemberButton.setOnAction( e -> {

            Member newMember = NewMemberMenu.display();
            memberShipService.addNewMember( newMember, incomeService );

            this.updateObservableList();
        } );

        buttons.getChildren().add( addMemberButton );
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
        articleList.clear();
        for( int key: inventory.getInventory().keySet() )
            articleList.add( inventory.getInventory().get( key ).getItem() );
    }
}

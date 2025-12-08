package com.simonsgearhubjavafx.gui;

import com.simonsgearhubjavafx.database.Inventory;
import com.simonsgearhubjavafx.member.Member;
import com.simonsgearhubjavafx.service.IncomeService;
import com.simonsgearhubjavafx.service.MembershipService;
import com.simonsgearhubjavafx.service.RentalService;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainMenu {

    MembershipService memberShipService;
    RentalService rentalService;
    Inventory inventory;
    IncomeService incomeService;

    MemberMenu memberMenu;
    ItemMenu itemMenu;

    MainMenu() {
    }

    public MainMenu(MembershipService memberShipService, RentalService rentalService, Inventory inventory, IncomeService incomeService) {

        this.memberShipService = memberShipService;
        this.rentalService     = rentalService;
        this.inventory         = inventory;
        this.incomeService     = incomeService;

        memberMenu = new MemberMenu( memberShipService, rentalService, inventory, incomeService );
        itemMenu   = new ItemMenu( memberShipService, rentalService, inventory, incomeService );
    }

    public void display( Stage primaryStage ) {

        BorderPane root = new BorderPane();
        root.getStylesheets().add( MainMenu.class.getResource("/main-menu.css").toExternalForm() );

        Button membersButton = new Button("Medlemmar");
        Button itemsButton = new Button( "Artiklar" );

        membersButton.setOnAction( e ->  memberMenu.display() );
        itemsButton.setOnAction( e -> itemMenu.display() );

        VBox buttons = new VBox();
        buttons.setAlignment( Pos.CENTER );

        buttons.getChildren().addAll( membersButton, itemsButton );

        root.setCenter( buttons );

        Scene scene = new Scene( root, 400, 400  );
        primaryStage.setScene( scene );
        primaryStage.show();

    }
}

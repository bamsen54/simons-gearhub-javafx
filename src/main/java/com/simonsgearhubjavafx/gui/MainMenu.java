package com.simonsgearhubjavafx.gui;

import com.simonsgearhubjavafx.database.Inventory;
import com.simonsgearhubjavafx.service.IncomeService;
import com.simonsgearhubjavafx.service.MembershipService;
import com.simonsgearhubjavafx.service.RentalService;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainMenu {

    MembershipService memberShipService;
    RentalService rentalService;
    Inventory inventory;
    IncomeService incomeService;

    MemberMenu memberMenu;
    ItemMenu itemMenu;
    RentalMenu rentalMenu;
    RentalsInfo rentalsInfo;

    MainMenu() {
    }

    public MainMenu(MembershipService memberShipService, RentalService rentalService, Inventory inventory, IncomeService incomeService) {

        this.memberShipService = memberShipService;
        this.rentalService     = rentalService;
        this.inventory         = inventory;
        this.incomeService     = incomeService;

        memberMenu  = new MemberMenu( memberShipService, rentalService, inventory, incomeService );
        itemMenu    = new ItemMenu( memberShipService, rentalService, inventory, incomeService );
        rentalMenu  = new RentalMenu( memberShipService, rentalService, inventory, incomeService );
        rentalsInfo = new RentalsInfo( memberShipService, rentalService, inventory, incomeService );
    }

    public void display( Stage primaryStage ) {

        BorderPane root = new BorderPane();
        root.getStylesheets().add( MainMenu.class.getResource("/main-menu.css").toExternalForm() );

        Button membersButton     = new Button("Medlemmar");
        Button itemsButton       = new Button( "Artiklar" );
        Button rentalButton      = new Button( "Hyr och återlämning" );
        Button rentalsInfoButton = new Button( "Hyrningar" );

        membersButton.setOnAction( e ->  memberMenu.display() );
        itemsButton.setOnAction( e -> itemMenu.display() );
        rentalButton.setOnAction( e -> rentalMenu.display( ) );
        rentalsInfoButton.setOnAction( e -> rentalsInfo.display() );

        VBox buttons = new VBox();
        buttons.setAlignment( Pos.CENTER );
        buttons.setSpacing( 5 );

        buttons.getChildren().addAll( membersButton, itemsButton, rentalButton, rentalsInfoButton );

        root.setCenter( buttons );

        Scene scene = new Scene( root, 400, 400  );
        primaryStage.setScene( scene );
        primaryStage.show();

    }
}

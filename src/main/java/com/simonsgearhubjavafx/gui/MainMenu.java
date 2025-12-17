package com.simonsgearhubjavafx.gui;

import com.simonsgearhubjavafx.SystemData;
import com.simonsgearhubjavafx.database.Inventory;
import com.simonsgearhubjavafx.database.InventoryEntry;
import com.simonsgearhubjavafx.json.SaveAndLoadFromJSON;
import com.simonsgearhubjavafx.member.Member;
import com.simonsgearhubjavafx.service.IncomeService;
import com.simonsgearhubjavafx.service.MembershipService;
import com.simonsgearhubjavafx.service.RentalService;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainMenu {

    MembershipService memberShipService;
    RentalService rentalService;
    Inventory inventory;
    IncomeService incomeService;

    MemberMenu memberMenu;
    ItemMenu itemMenu;
    RentalMenu rentalMenu;
    RentalsInfo rentalsInfo;
    Income income;

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
        income      = new Income();
    }

    public void display( Stage primaryStage ) {

        BorderPane root = new BorderPane();
        root.getStylesheets().add( MainMenu.class.getResource("/main-menu.css").toExternalForm() );

        Button membersButton     = new Button("Medlemmar");
        Button itemsButton       = new Button( "Artiklar" );
        Button rentalButton      = new Button( "Hyr och återlämning" );
        Button rentalsInfoButton = new Button( "Hyrningar" );
        Button incomeButton      = new Button( "Intäkter" );
        Button saveButton        = new Button( "Spara" );
        Button loadButton        = new Button( "Ladda" );

        membersButton.setOnAction( e ->  memberMenu.display() );
        itemsButton.setOnAction( e -> itemMenu.display() );
        rentalButton.setOnAction( e -> rentalMenu.display( ) );
        rentalsInfoButton.setOnAction( e -> rentalsInfo.display() );
        incomeButton.setOnAction( e ->  income.display( incomeService ) );
        saveButton.setOnAction( e -> {

            FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter( "JSON files (*.json)", "*.json" );

            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add( extensionFilter );
            File file = fileChooser.showSaveDialog( primaryStage );

            if( file == null )
                return;

            List<Member> memberList = new ArrayList<>();
            for( int key: memberShipService.getMemberRegistry().getMembers().keySet() )
                memberList.add( memberShipService.getMemberWithID( key ) );

            List<InventoryEntry> inventoryEntries = new ArrayList<>();
            for( int key: inventory.getInventory().keySet() )
                inventoryEntries.add( inventory.getInventory().get( key ) );

            double incomeEntryFees = incomeService.getIncomeEntryFees();
            double incomeRentalFees = incomeService.getIncomeRentalFees();

            SystemData systemData = new SystemData( memberList,  inventoryEntries, incomeRentalFees, incomeEntryFees );

            try {
                SaveAndLoadFromJSON.saveSystemDataToFile( file.getAbsolutePath(), systemData );
            }
            catch (IOException ex) {

                AlertBox.display( "spara", "sparandet gick ej" );
            }

            catch ( NullPointerException ex ) {}
        } );

        loadButton.setOnAction( e -> {

            try {

                FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter( "JSON files (*.json)", "*.json" );

                FileChooser fileChooser = new FileChooser();
                fileChooser.getExtensionFilters().add( extensionFilter );
                File file = fileChooser.showOpenDialog( primaryStage );

                if( file == null )
                    return;

                SaveAndLoadFromJSON.loadSystemDataFromFile( file.getAbsolutePath(), memberShipService, inventory, incomeService );
            }

            catch (IOException ex) {

                AlertBox.display( "ladda", "filen du försöka ladda är korrupt" );
            }
        } );

        VBox buttons = new VBox();
        buttons.setAlignment( Pos.CENTER );
        buttons.setSpacing( 5 );

        buttons.getChildren().addAll( membersButton, itemsButton, rentalButton, rentalsInfoButton, incomeButton, saveButton, loadButton );

        root.setCenter( buttons );

        Scene scene = new Scene( root, 400, 400  );
        primaryStage.setScene( scene );
        primaryStage.show();
    }
}

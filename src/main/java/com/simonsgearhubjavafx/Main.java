package com.simonsgearhubjavafx;

// Simon Toivola SY25 Objektorienterad Programmering Uppgift 1

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.simonsgearhubjavafx.database.Inventory;
import com.simonsgearhubjavafx.database.InventoryEntry;
import com.simonsgearhubjavafx.database.MemberRegistry;
import com.simonsgearhubjavafx.gui.MainMenu;
import com.simonsgearhubjavafx.json.SaveAndLoadFromJSON;
import com.simonsgearhubjavafx.member.Member;
import com.simonsgearhubjavafx.service.IncomeService;
import com.simonsgearhubjavafx.service.MembershipService;
import com.simonsgearhubjavafx.service.RentalService;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {

    private static final Inventory inventory           = new Inventory();
    private static MembershipService memberShipService = new MembershipService( inventory );
    private static final RentalService rentalService   = new RentalService();
    private static final IncomeService incomeService   = new IncomeService();


    @Override
    public void start(Stage stage) throws Exception {

        SaveAndLoadFromJSON.loadSystemDataFromFile( "systemdata.json", memberShipService, inventory, incomeService );

        MainMenu mainMenu = new MainMenu( memberShipService, rentalService, inventory, incomeService );

        mainMenu.display( stage );

    }
}

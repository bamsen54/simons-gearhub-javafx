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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    private static final Inventory inventory                 = new Inventory();
    private static final MembershipService memberShipService = new MembershipService( inventory );
    private static final RentalService rentalService         = new RentalService();
    private static final IncomeService incomeService         = new IncomeService();

    public static volatile boolean autoSaveOn = true;
    public static volatile int autoSavePeriod = 60;   // seconds
    public static volatile String autoSavePath = "autosave.json";

    @Override
    public void start(Stage stage) throws Exception {

        SaveAndLoadFromJSON.loadSystemDataFromFile( "systemdata.json", memberShipService, inventory, incomeService );

        Thread autosave = new Thread( () -> {

            while(true) {

                try {
                    Thread.sleep( autoSavePeriod * 1L );

                    List<Member> members = new ArrayList<>();
                    List<InventoryEntry> inventoryEntries = new ArrayList<>();

                    for (int key : memberShipService.getMemberRegistry().getMembers().keySet()) {

                        Member member = memberShipService.getMemberRegistry().getMembers().get(key);
                        members.add(member);
                    }

                    for (int key : inventory.getInventory().keySet()) {

                        InventoryEntry inventoryEntry = inventory.getInventory().get(key);
                        inventoryEntries.add(inventoryEntry);
                    }

                    SystemData systemData = new SystemData(members, inventoryEntries, incomeService.getIncomeEntryFees(), incomeService.getIncomeRentalFees() );

                    if( autoSaveOn )
                        SaveAndLoadFromJSON.saveSystemDataToFile( autoSavePath, systemData );

                    else
                        Thread.sleep( 1000 );
                }

                catch ( IOException | InterruptedException e ) {

                }
            }

        } );

        autosave.setDaemon( true );
        autosave.start();

        MainMenu mainMenu = new MainMenu( memberShipService, rentalService, inventory, incomeService );

        mainMenu.display( stage );

    }
}

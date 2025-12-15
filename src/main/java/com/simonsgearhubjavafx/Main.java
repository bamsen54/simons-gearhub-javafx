package com.simonsgearhubjavafx;

// Simon Toivola SY25 Objektorienterad Programmering Uppgift 1

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.simonsgearhubjavafx.database.Inventory;
import com.simonsgearhubjavafx.database.InventoryEntry;
import com.simonsgearhubjavafx.database.MemberRegistry;
import com.simonsgearhubjavafx.gui.MainMenu;
import com.simonsgearhubjavafx.item.PersonalCar;
import com.simonsgearhubjavafx.item.RacingCar;
import com.simonsgearhubjavafx.member.Member;
import com.simonsgearhubjavafx.service.IncomeService;
import com.simonsgearhubjavafx.service.MembershipService;
import com.simonsgearhubjavafx.service.RentalService;
import javafx.application.Application;
import javafx.stage.Stage;

import com.simonsgearhubjavafx.gui.MainMenu;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main extends Application {

    private static final Inventory inventory           = new Inventory();
    private static MembershipService memberShipService = new MembershipService( inventory );
    private static final RentalService rentalService   = new RentalService();
    private static final IncomeService incomeService   = new IncomeService();


    @Override
    public void start(Stage stage) throws Exception {



        ObjectMapper mapper = new ObjectMapper();
        mapper.enable( SerializationFeature.INDENT_OUTPUT );

        SystemData fromFile = mapper.readValue( new File( "systemdata.json" ), SystemData.class );

        MemberRegistry memberRegistry = new MemberRegistry();

        for( Member member : fromFile.getMembers() ) {
            memberRegistry.addMember( member );
        }

        memberShipService.setMemberRegistry( memberRegistry );

        for( InventoryEntry e: fromFile.getInventoryEntries() ) {
            inventory.getInventory().put( e.getItem().getId(), e );
        }

        MainMenu mainMenu = new MainMenu( memberShipService, rentalService, inventory, incomeService );

        mainMenu.display( stage );

    }
}

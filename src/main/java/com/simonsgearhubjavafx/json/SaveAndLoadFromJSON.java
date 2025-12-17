package com.simonsgearhubjavafx.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.simonsgearhubjavafx.Main;
import com.simonsgearhubjavafx.SystemData;
import com.simonsgearhubjavafx.database.Inventory;
import com.simonsgearhubjavafx.database.InventoryEntry;
import com.simonsgearhubjavafx.database.MemberRegistry;
import com.simonsgearhubjavafx.member.Member;
import com.simonsgearhubjavafx.service.IncomeService;
import com.simonsgearhubjavafx.service.MembershipService;

import java.io.File;
import java.io.IOException;

public class SaveAndLoadFromJSON {

    public static void saveSystemDataToFile(String path, SystemData systemData) throws IOException {

        if( systemData == null ) {
            throw new IllegalArgumentException( "systemData får inte vara null vid sparning" );
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        mapper.writeValue(new File(path), systemData);
    }

    public static  void  loadSystemDataFromFile(String path, MembershipService memberShipService, Inventory inventory, IncomeService incomeService) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable( SerializationFeature.INDENT_OUTPUT );

        SystemData systemData = mapper.readValue( new File(path), SystemData.class );

        if( systemData == null )
            throw new IOException( "Filens format matchar inte" );

        if( systemData.getMembers() == null || systemData.getInventoryEntries() == null )
            throw new IOException( "Filens format matchar inte" );

        if( systemData.getIncomeEntryFees() < 0 || systemData.getIncomeRentalFees() < 0 )
            throw new IOException( "Filens format matchar inte" );

        MemberRegistry memberRegistry = new MemberRegistry();
        for( Member member : systemData.getMembers() )
            memberRegistry.addMember( member );

        memberShipService.setMemberRegistry( memberRegistry );

        inventory.getInventory().clear();
        for( InventoryEntry e: systemData.getInventoryEntries() )
            inventory.getInventory().put( e.getItem().getId(), e );


        incomeService.setIncomeEntryFees(  systemData.getIncomeEntryFees() );
        incomeService.setIncomeRentalFees( systemData.getIncomeRentalFees() );

        Main.autoSaveOn = systemData.isAutoSaveOn();
        Main.autoSavePeriod = systemData.getAutoSavePeriod();
        Main.autoSavePath = systemData.getAutoSavePath();
    }
}

package com.simonsgearhubjavafx.database;

import java.util.HashMap;

import com.simonsgearhubjavafx.item.Item;
import com.simonsgearhubjavafx.item.PersonalCar;
import com.simonsgearhubjavafx.item.RacingCar;

public class Inventory {

    private HashMap<Integer, InventoryEntry> inventory = new HashMap<>();

    public Inventory() {
    }

    public void loadFromDatabase( ) {

        Item volvov70 = new PersonalCar( 0, "Volvo V70", "Personbil", 500, 5, "wagon" );
        InventoryEntry volvov70Article = new InventoryEntry( volvov70, 2 );

        inventory.put( volvov70.getId(), volvov70Article );

        Item f2004    = new RacingCar( 5, "Ferrari F2004", "Racingbil", 2000, "Formula 1", 800 );
        InventoryEntry f2004Article = new InventoryEntry( f2004, 2 );
        inventory.put( f2004.getId(), f2004Article );

        IO.println( volvov70 );
        IO.println( f2004 );
    }

    public HashMap<Integer, InventoryEntry> getInventory() {
        return this.inventory;
    }

    public void setInventory(HashMap<Integer, InventoryEntry> inventory) {
        this.inventory = inventory;
    }
}

package com.simonsgearhubjavafx.database;

import java.util.HashMap;

import com.simonsgearhubjavafx.item.Car;
import com.simonsgearhubjavafx.item.ElectricCar;
import com.simonsgearhubjavafx.item.Item;
import com.simonsgearhubjavafx.item.TravelTrailer;

public class Inventory {

    private HashMap<Integer, InventoryEntry> inventory = new HashMap<>();

    public Inventory() {
    }

    public void loadFromDatabase( ) {

        Item minicooper      = new Car( 0, "Mini Cooper", "bil", 700, "bensin", "3" );
        Item volvov70        = new Car( 1, "Volvo v70", "bil", 800, "bensin", "4" );
        Item volkswagengolf  = new Car( 2, "Volkswagen Golf", "bil", 950, "diesel", "4" );
        Item porsche911turbo = new Car( 3, "Porsche 911 Turbo", "bil", 1200, "bensin", "8" );
        inventory.put( minicooper.getId(), new InventoryEntry( minicooper ,  4 ) );
        inventory.put( volvov70.getId() , new InventoryEntry( volvov70 ,  2 ) );
        inventory.put( volkswagengolf.getId() , new InventoryEntry( volkswagengolf ,  3 ) );
        inventory.put( porsche911turbo.getId() , new InventoryEntry( porsche911turbo ,  1 ) );

        Item teslamodely   = new ElectricCar( 4, "Tesla Model Y", "elbil", 900, "75", true );
        Item volkswagenid4 = new ElectricCar( 5, "Volkswagen ID.4", "elbil", 750, "52",  true );
        Item volvoex40     = new ElectricCar( 6, "Volvo EX40", "elbil", 1100, "6 ", true );
        inventory.put( teslamodely.getId() , new InventoryEntry( teslamodely ,  2 ) );
        inventory.put( volkswagenid4.getId() , new InventoryEntry( volkswagenid4 ,  3 ) );
        inventory.put( volvoex40.getId() , new InventoryEntry( volvoex40 ,  1 ) );

        Item Polar590 = new TravelTrailer( 7, "Polar 590", "husvagn", 1400, "5" );
        Item kabesmaragd520xl = new TravelTrailer( 8, "Kabe Smaragd 520 XL", "husvagn", 2000, "4" );
        inventory.put( Polar590.getId() , new InventoryEntry( Polar590 ,  1 ) );
        inventory.put( kabesmaragd520xl.getId(), new InventoryEntry( kabesmaragd520xl, 2) );
    }

    public HashMap<Integer, InventoryEntry> getInventory() {
        return this.inventory;
    }
}

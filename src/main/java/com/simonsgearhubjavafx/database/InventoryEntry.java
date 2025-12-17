package com.simonsgearhubjavafx.database;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.simonsgearhubjavafx.item.Item;
import com.simonsgearhubjavafx.item.PersonalCar;

@JsonIgnoreProperties(ignoreUnknown = true) // <-- FIXA DETTA
public class InventoryEntry {

    private Item item;
    private int quantityInStore;

    public InventoryEntry() {
    }

    public InventoryEntry(Item item, int quantityInStore) {
        this.item = item;
        this.quantityInStore = quantityInStore;
    }

    public InventoryEntry(PersonalCar personalCar, String text) {
    }

    public Item getItem() {
        return this.item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getQuantityInStore() {
        return this.quantityInStore;
    }

    public void setQuantityInStore(int quantityInStore) {
        this.quantityInStore = quantityInStore;
    }

    @JsonIgnore
    public int getId() {
        return this.item.getId();
    }
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append( this.item.toString() );
        stringBuilder.append( " antal i lager: " ).append( this.quantityInStore );

        return stringBuilder.toString();
    }
}

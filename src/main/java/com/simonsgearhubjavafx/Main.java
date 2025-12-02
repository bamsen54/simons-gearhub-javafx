package com.simonsgearhubjavafx;

// Simon Toivola SY25 Objektorienterad Programmering Uppgift 1

import com.simonsgearhubjavafx.database.Inventory;
import com.simonsgearhubjavafx.gui.MainMenu;
import com.simonsgearhubjavafx.service.IncomeService;
import com.simonsgearhubjavafx.service.MembershipService;
import com.simonsgearhubjavafx.service.RentalService;
import javafx.application.Application;
import javafx.stage.Stage;

import com.simonsgearhubjavafx.gui.MainMenu;

public class Main extends Application {

    private static final Inventory inventory           = new Inventory();
    private static MembershipService memberShipService = new MembershipService( inventory );
    private static final RentalService rentalService   = new RentalService();
    private static final IncomeService incomeService   = new IncomeService();


    @Override
    public void start(Stage stage) throws Exception {

        MainMenu mainMenu = new MainMenu( memberShipService, rentalService, inventory, incomeService );

        mainMenu.display( stage );
    }
}

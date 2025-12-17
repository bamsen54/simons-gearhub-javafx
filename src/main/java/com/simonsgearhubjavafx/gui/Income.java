package com.simonsgearhubjavafx.gui;

import com.simonsgearhubjavafx.service.IncomeService;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Income {

    public void display(IncomeService incomeService)  {

        VBox income = new VBox();
        income.setStyle( "-fx-background-color: #2B2B2B;" );
        income.getStylesheets().add( NewMemberMenu.class.getResource( "/forms.css" ).toExternalForm() );
        income.setAlignment( Pos.CENTER );
        income.setSpacing(10);


        Label entryFeeIncome = new Label( "inkomst från inträde: " + incomeService.getIncomeEntryFees() + "" );
        Label rentalFeeIncome = new Label( "inkomst från uthyrningar: " + incomeService.getIncomeRentalFees() + "" );
        Label total = new Label( "totalt: " + ( incomeService.getIncomeEntryFees() + incomeService.getIncomeRentalFees() ) );

        income.getChildren().addAll( entryFeeIncome,rentalFeeIncome, total );

        Scene scene = new Scene( income, 400, 300 );

        Stage stage = new Stage();
        stage.initModality( Modality.APPLICATION_MODAL );
        stage.setScene( scene );
        stage.setResizable( false );
        stage.setTitle( "Inkomst" );
        stage.show();
    }
}

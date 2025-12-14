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
        income.setAlignment( Pos.CENTER );
        income.setSpacing(10);

        Label entryFeeIncome = new Label( incomeService.getIncomeEntryFees() + "" );
        Label rentalFeeIncome = new Label( incomeService.getIncomeRentalFees() + "" );

        income.getChildren().addAll( entryFeeIncome,rentalFeeIncome );

        Scene scene = new Scene( income, 400, 300 );

        Stage stage = new Stage();
        stage.initModality( Modality.APPLICATION_MODAL );
        stage.setScene( scene );
        stage.show();
    }
}

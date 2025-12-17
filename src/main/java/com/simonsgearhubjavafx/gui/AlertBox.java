package com.simonsgearhubjavafx.gui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {

    public static void display(String title, String message) {
//
//        Stage stage = new Stage();
//        VBox root = new VBox();
//
//        root.setAlignment( Pos.CENTER );
//        Label messageLabel = new Label( message );
//        messageLabel.setStyle( "-fx-font-size: 18px; " );
//        root.getChildren().addAll( messageLabel );
//        messageLabel.setAlignment(Pos.CENTER );
//        Scene scene = new Scene( root , 400, 300 );
//        stage.setTitle( title );
//        stage.setScene( scene );
//        stage.initModality( Modality.APPLICATION_MODAL );
//        stage.setResizable( false );
//        stage.show();

        Stage stage = new Stage();


        BorderPane root = new BorderPane();

        VBox messageBox = new VBox();
        Label messageLabel = new Label( message );
        messageLabel.setStyle( "-fx-font-size: 18px;" );
        messageBox.getChildren().add( messageLabel );
        messageBox.setAlignment( Pos.CENTER );

        root.setCenter(  messageBox );

        HBox buttonBox = new HBox();
        Button okayButton = new Button( "OK" );
        buttonBox.getChildren().add( okayButton );
        buttonBox.setAlignment( Pos.CENTER );

        okayButton.setOnAction( e -> stage.close() );

        root.setBottom( buttonBox );

        Scene scene = new Scene( root, 600, 600 );
        stage.setScene( scene );
        stage.initModality( Modality.APPLICATION_MODAL );
        stage.setTitle( title );
        stage.setResizable( false );
        stage.show();
    }
}

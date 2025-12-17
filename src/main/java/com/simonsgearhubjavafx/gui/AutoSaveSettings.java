package com.simonsgearhubjavafx.gui;

import com.simonsgearhubjavafx.Main;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AutoSaveSettings {

    public static void display() {

        BorderPane root = new BorderPane();
        root.getStylesheets().add( AutoSaveSettings.class.getResource("/autosave-settings.css").toExternalForm() );

        Scene scene = new Scene( root, 400, 400 );

        ToggleButton autoSaveToggle = new ToggleButton( "Auto Save" );
        autoSaveToggle.setSelected( Main.autoSaveOn );

        ComboBox<Integer> autoSaveTimes = new ComboBox<>();

        Label saveTimes = new Label( "Auto Save Period (sekunder)" );
        autoSaveTimes.getItems().addAll( 5, 10, 20, 30, 60 );
        autoSaveTimes.setValue( Main.autoSavePeriod );

        HBox saveTimeBox = new HBox();
        saveTimeBox.setAlignment( Pos.CENTER );
        saveTimeBox.setSpacing( 10 );
        saveTimeBox.getChildren().addAll( saveTimes, autoSaveTimes  );

        HBox pathBox = new HBox();
        pathBox.setAlignment( Pos.CENTER );
        pathBox.setSpacing( 10 );

        Label pathLabel = new Label( "path" );
        TextField pathField = new TextField();
        pathField.setPrefWidth( 250 );
        pathField.setText( Main.autoSavePath );

        Button savePathButton = new Button( "Spara Path" );

        pathBox.getChildren().addAll( pathLabel, pathField,  savePathButton );

        VBox items = new VBox(10);
        items.setAlignment( Pos.CENTER );
        items.getChildren().addAll( autoSaveToggle, saveTimeBox, pathBox );


        autoSaveToggle.setOnAction( e -> {
             Main.autoSaveOn =  autoSaveToggle.isSelected();
        } );

        autoSaveTimes.setOnAction( e -> {
            Main.autoSavePeriod = autoSaveTimes.getValue();
        } );

        savePathButton.setOnAction( e -> {
            Main.autoSavePath = pathField.getText();
        } );

        root.setCenter( items );

        Stage stage = new Stage();
        stage.setScene( scene );
        stage.initModality( Modality.APPLICATION_MODAL );
        stage.setResizable( false );
        stage.setTitle( "Autosave Inställningar" );
        stage.showAndWait();
    }
}

package com.simonsgearhubjavafx.gui;

import com.simonsgearhubjavafx.Level;
import com.simonsgearhubjavafx.member.Member;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicReference;

public class NewMemberMenu {

    public static Member display() {

        AtomicReference<Member> newMember = new AtomicReference<>();

        Stage stage = new Stage();

        GridPane root = new GridPane();
        root.getStylesheets().add( NewMemberMenu.class.getResource( "/new-member-menu.css" ).toExternalForm() );

        root.setHgap( 25 );
        root.setVgap( 15 );

        Label idLabel = new Label( "Id" );
        TextField idTextField = new TextField();

        Label nameLabel = new Label( "Name" );
        TextField nameTextField = new TextField();

        Label pricePolicyLabel = new Label( "Medlemsnivå" );
        ComboBox<Level> pricePolicyLevel = new ComboBox<>();
        pricePolicyLevel.getItems().addAll( Level.values() );
        pricePolicyLevel.setValue( Level.STANDARD );

        Button addNewMemberButton = new Button( "Lägg Till Medlem" );
        addNewMemberButton.setOnAction( e -> {

            final String id   = idTextField.getText();
            final String name =  nameTextField.getText();

            if( !id.isEmpty() && id.matches("[0-9]+") && !name.isEmpty()  )
                newMember.set(new Member( Integer.parseInt( id ), name, pricePolicyLevel.getValue()));

            stage.close();
        } );



        root.add( idLabel, 0, 0 );
        root.add( idTextField, 1, 0 );
        root.add( nameLabel, 0, 1 );
        root.add( nameTextField, 1, 1 );
        root.add(  pricePolicyLabel, 0, 2 );
        root.add( pricePolicyLevel, 1, 2 );
        root.add( addNewMemberButton, 0, 3 );

        GridPane.setMargin(addNewMemberButton, new Insets(0, 0, 0, 10) );

        Scene scene = new Scene( root, 400, 300 );
        stage.setScene( scene );
        stage.initModality( Modality.APPLICATION_MODAL );
        stage.showAndWait();

        return newMember.get();
    }
}

package com.simonsgearhubjavafx.gui;

import com.simonsgearhubjavafx.Level;
import com.simonsgearhubjavafx.member.Member;
import com.simonsgearhubjavafx.service.IncomeService;
import com.simonsgearhubjavafx.service.MembershipService;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicReference;

public class NewMemberMenu {

    public static void display(Member member, MembershipService membershipService, IncomeService incomeService) {

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

            try {
                final String id = idTextField.getText();
                final String name = nameTextField.getText();
                final Level level = Level.valueOf(String.valueOf( pricePolicyLevel.getValue() ) );

                boolean idAlreadyExists = membershipService.getMemberWithID( Integer.parseInt( id ) ) != null;

                if ( !id.isEmpty() && id.matches("[0-9]+") && !name.isEmpty() && !idAlreadyExists ) {
                    member.setId( Integer.parseInt(id) );
                    member.setName( nameTextField.getText() );
                    member.setLevel( level );


                    incomeService.handleEntryFeePaymen( member, null );

                    stage.close();

                } else {
                    AlertBox.display("id", "medlem med det id:t finns redan");
                    stage.close();
                }
            }

            catch ( NumberFormatException ex ) {
                AlertBox.display( "id", "id:t måste vara ett icke-negativt heltal" );
            }
            catch ( NullPointerException ex ) { IO.println( "Null" );}

        } );

        root.add( idLabel, 0, 0 );
        root.add( idTextField, 1, 0 );
        root.add( nameLabel, 0, 1 );
        root.add( nameTextField, 1, 1 );
        root.add(  pricePolicyLabel, 0, 2 );
        root.add( pricePolicyLevel, 1, 2 );
        root.add( addNewMemberButton, 0, 3 );

        GridPane.setMargin( addNewMemberButton, new Insets(0, 0, 0, 10 ) );

        Scene scene = new Scene( root, 400, 300 );
        stage.setScene( scene );
        stage.initModality( Modality.APPLICATION_MODAL );
        stage.showAndWait();

    }
}

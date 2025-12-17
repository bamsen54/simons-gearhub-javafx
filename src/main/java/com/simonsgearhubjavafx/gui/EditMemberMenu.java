package com.simonsgearhubjavafx.gui;

import com.simonsgearhubjavafx.Level;
import com.simonsgearhubjavafx.member.Member;
import com.simonsgearhubjavafx.service.IncomeService;
import com.simonsgearhubjavafx.service.MembershipService;
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

public class EditMemberMenu {

    public static void display(Member member, MembershipService membershipService , IncomeService incomeService ) {

        AtomicReference<Member> newMember = new AtomicReference<>();
        newMember.set( member );

        Stage stage = new Stage();
        stage.setTitle( "Ändra Medlem" );
        stage.setResizable( false );

        GridPane root = new GridPane();
        root.setStyle("-fx-background-color: #2b2b2b;"); // En snygg mörkgrå "Dark Mode"-färg
        root.getStylesheets().add( NewMemberMenu.class.getResource("/forms.css").toExternalForm() );

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

        String previousMemberLevel = member.getLevel().toString();

        idTextField.setText( String.valueOf( newMember.get().getId() ) );
        nameTextField.setText( newMember.get().getName() );
        pricePolicyLevel.setValue( newMember.get().getLevel() );

        Button saveButton = new Button( "Spara Ny Info" );
        saveButton.setOnAction( e -> {

            int newID = - 1;
            try {
                newID = Integer.parseInt( idTextField.getText() );
            }

            catch ( NumberFormatException ex ) {
                AlertBox.display( "id", "id:t måste vara ett icke-negativt heltal" );
                stage.close();
                return;
            }

            boolean idAlreadyExists = false;
            int counter = 0;

            for( int id: membershipService.getMemberRegistry().getMembers().keySet() ) {

                Member memberAtID = membershipService.getMemberRegistry().getMember( id );

                if( memberAtID.getId() == newID )
                    counter++;
            }

            if( counter > 0 )
                idAlreadyExists = true;

            IO.println( "counter: " + counter );



            if( idAlreadyExists ) {
                AlertBox.display( "id", "medlem med det id:t finns redan" );
                stage.close();
                return;
            }

            try {
                member.setId( Integer.parseInt( idTextField.getText() ) );
                member.setName(  nameTextField.getText() );
                member.setLevel( pricePolicyLevel.getValue() );
            }

            catch ( NumberFormatException ex ) {
                // todo
                IO.println( "id:t måste vara ett icke-negativt heltal" );
            }

            incomeService.handleEntryFeePaymen( member, Level.valueOf( previousMemberLevel ) );
            IO.println( member.getLevel() + " " + previousMemberLevel );


            stage.close();
        } );

        root.add( idLabel, 0, 0 );
        root.add( idTextField, 1, 0 );
        root.add( nameLabel, 0, 1 );
        root.add( nameTextField, 1, 1 );
        root.add(  pricePolicyLabel, 0, 2 );
        root.add( pricePolicyLevel, 1, 2 );
        root.add( saveButton, 0, 3 );

        GridPane.setMargin( saveButton, new Insets(0, 0, 0, 10) );

        Scene scene = new Scene( root, 400, 300 );
        stage.setScene( scene );
        stage.initModality( Modality.APPLICATION_MODAL );
        stage.showAndWait();

    }
}
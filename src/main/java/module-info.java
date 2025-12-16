module com.simonsgearhubjavafx {

    requires javafx.controls;
    requires javafx.fxml;

    requires javafx.graphics;
    requires javafx.base;
    requires com.fasterxml.jackson.databind;

    opens com.simonsgearhubjavafx to com.fasterxml.jackson.databind;        // Tillåter Jackson komma åt SystemData (om den ligger i rot)
    opens com.simonsgearhubjavafx.member to com.fasterxml.jackson.databind;   // Tillåter Jackson komma åt Member (den du fixade)
    opens com.simonsgearhubjavafx.database to com.fasterxml.jackson.databind; // Databas/I/O

    opens com.simonsgearhubjavafx.item to com.fasterxml.jackson.databind;     // För Item, PersonalCar, RaceCar, etc.
    opens com.simonsgearhubjavafx.rental to com.fasterxml.jackson.databind;   // För Rental-klassen
    opens com.simonsgearhubjavafx.policy to com.fasterxml.jackson.databind;   // Om du har dataklasser här
    opens com.simonsgearhubjavafx.service to com.fasterxml.jackson.databind;  // Om du har fält som ska sparas här
    opens com.simonsgearhubjavafx.json to com.fasterxml.jackson.databind;

    exports com.simonsgearhubjavafx.database;
    exports com.simonsgearhubjavafx.member;
    exports com.simonsgearhubjavafx.item;
    exports com.simonsgearhubjavafx.policy;
    exports com.simonsgearhubjavafx.rental;
    exports com.simonsgearhubjavafx.service;
    exports com.simonsgearhubjavafx.json;


    exports com.simonsgearhubjavafx;
}

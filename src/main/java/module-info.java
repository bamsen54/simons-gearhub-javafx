module com.simonsgearhubjavafx {

    requires javafx.controls;
    requires javafx.fxml;

    requires javafx.graphics;
    requires javafx.base;
    requires com.fasterxml.jackson.databind;
    requires java.sql;

    opens com.simonsgearhubjavafx to com.fasterxml.jackson.databind;
    opens com.simonsgearhubjavafx.member to com.fasterxml.jackson.databind;
    opens com.simonsgearhubjavafx.database to com.fasterxml.jackson.databind;

    opens com.simonsgearhubjavafx.item to com.fasterxml.jackson.databind;
    opens com.simonsgearhubjavafx.rental to com.fasterxml.jackson.databind;
    opens com.simonsgearhubjavafx.policy to com.fasterxml.jackson.databind;
    opens com.simonsgearhubjavafx.service to com.fasterxml.jackson.databind;
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

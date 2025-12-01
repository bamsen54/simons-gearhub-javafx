module com.simonsgearhubjavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.simonsgearhubjavafx to javafx.fxml;
    exports com.simonsgearhubjavafx;
}
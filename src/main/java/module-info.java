module com.simonsgearhubjavafx {
    
    requires javafx.controls;
    requires javafx.fxml;

    requires javafx.graphics;
    requires javafx.base;

    opens com.simonsgearhubjavafx to javafx.fxml;
    
    exports com.simonsgearhubjavafx;
}

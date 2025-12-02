module com.simonsgearhubjavafx {
    // Dessa krävs för JavaFX:s kärnfunktionalitet
    requires javafx.controls;
    requires javafx.fxml;

    // Denna rad har tagits bort: requires com.simonsgearhubjavafx;

    // Öppnar modulen för FXML-laddaren
    opens com.simonsgearhubjavafx to javafx.fxml;

    // Exporterar paketet så att andra moduler kan använda det
    exports com.simonsgearhubjavafx;
}
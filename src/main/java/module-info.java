module com.simonsgearhubjavafx {
    // Dessa krävs för JavaFX:s kärnfunktionalitet
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics; // <--- Flyttad upp, men är ett standardkrav

    // Om du använder andra moduler i ditt projekt (t.ex. din service-modul)
    // skulle de läggas till HÄR. Men du behöver INTE kräva dig själv.

    // Öppnar paketet så att FXML-laddaren kan komma åt dina controllers
    opens com.simonsgearhubjavafx to javafx.fxml;

    // Exporterar paketet så att andra (externa) moduler kan använda din kod
    exports com.simonsgearhubjavafx;
}
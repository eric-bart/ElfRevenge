module com.rtai.elfsrevenge.elfsrevenge {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.rtai.elfsrevenge.elfsrevenge to javafx.fxml;
    exports com.rtai.elfsrevenge.elfsrevenge;
}
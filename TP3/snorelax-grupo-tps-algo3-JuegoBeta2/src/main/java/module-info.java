module tp1beta {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens org.tp1.controller to javafx.fxml;
    exports org.tp1.controller;
    exports org.tp1;
}
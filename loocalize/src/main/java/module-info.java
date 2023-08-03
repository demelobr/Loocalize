module com.example.loocalize {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens gui to javafx.fxml;
    exports gui;

    opens models to javafx.fxml;
    exports models;
}
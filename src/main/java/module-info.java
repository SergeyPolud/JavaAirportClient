module com.example.client {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires javafx.graphics;
    requires java.sql;
    requires qrgen;
    requires com.google.zxing;

    opens com.example.client to javafx.fxml;
    exports com.example.client;
    exports users;
    opens users to javafx.fxml;
    exports Entities;
    opens Entities to javafx.fxml;
}
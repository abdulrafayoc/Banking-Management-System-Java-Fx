module org.bms.bms {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.sql;

    exports org.bms.presentation.controllers;
    opens org.bms.presentation.controllers to javafx.fxml;
    exports org.bms.presentation.viewmodels;
    opens org.bms.presentation.viewmodels to javafx.fxml;
    exports org.bms.presentation;
    opens org.bms.presentation to javafx.fxml;
}
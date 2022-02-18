module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires com.fasterxml.jackson.databind;

    opens org.example.control to javafx.fxml;
    opens org.example to javafx.fxml;
    opens  org.example.bll;
    exports org.example;
    exports org.example.model;
    exports org.example.control;
    exports org.example.bll to javafx.base;
}
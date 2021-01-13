/**
 *
 */
module TreeOfSkills {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires mysql.connector.java;

    opens sample;
    opens view;
    opens controller;
}
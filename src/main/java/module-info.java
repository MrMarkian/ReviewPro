/**
 *
 */
module com.revise.reviewpro {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    opens com.revise.reviewpro to javafx.fxml, javafx.graphics;
    opens com.revise.reviewpro.View to javafx.fxml;
    exports com.revise.reviewpro.View;
    exports com.revise.reviewpro.Data;
}
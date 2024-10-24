package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import single.ItemManagementForm;

public class EmployeeDashboardFormController {

    @FXML
    void btnItemManagementOnAction(ActionEvent event) {
        ItemManagementForm itemManagementForm = ItemManagementForm.getInstance();
        itemManagementForm.show();

    }

    @FXML
    void btnOrderMnagementOnAction(ActionEvent event) {

    }

    @FXML
    void btnReportGenerateOnAction(ActionEvent event) {

    }

    @FXML
    void btnSuppliersManagemntOnAction(ActionEvent event) {

    }

}

package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import single.ItemManagementForm;
import single.OrderManagementForm;
import single.SupplierManagementForm;

public class EmployeeDashboardFormController {

    @FXML
    void btnItemManagementOnAction(ActionEvent event) {
        ItemManagementForm itemManagementForm = ItemManagementForm.getInstance();
        itemManagementForm.show();

    }

    @FXML
    void btnOrderManagementOnAction(ActionEvent event) {
        OrderManagementForm orderManagementForm = OrderManagementForm.getInstance();
        orderManagementForm.show();

    }

    @FXML
    void btnReportGenerateOnAction(ActionEvent event) {

    }

    @FXML
    void btnSupplierManagementOnAction(ActionEvent event) {
        SupplierManagementForm supplierManagementForm = SupplierManagementForm.getInstance();
        supplierManagementForm.show();

    }

}

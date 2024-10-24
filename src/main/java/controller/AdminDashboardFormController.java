package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import single.EmployeeManagementForm;
import single.ItemManagementForm;
import single.SupplierManagementForm;

public class AdminDashboardFormController {

    @FXML
    void btnEmployeeManagementOnAction(ActionEvent event) {
        EmployeeManagementForm employeeManagementForm = EmployeeManagementForm.getInstance();
        employeeManagementForm.show();

    }

    @FXML
    void btnOrderManagementOnAction(ActionEvent event) {

    }

    @FXML
    void btnReportGenerateOnAction(ActionEvent event) {

    }

    @FXML
    void btnSuppliersManagementOnAction(ActionEvent event) {
        SupplierManagementForm supplierManagementForm = SupplierManagementForm.getInstance();
        supplierManagementForm.show();
    }
    @FXML
    void btnItemManagementOnAction(ActionEvent event) {
        ItemManagementForm itemManagementForm = ItemManagementForm.getInstance();
        itemManagementForm.show();
    }

}

package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import single.AdminLoginForm;
import single.EmployeeLoginForm;

public class MainFormController {

    @FXML
    void btnAdminOnAction(ActionEvent event) {
        AdminLoginForm adminLoginForm = AdminLoginForm.getInstance();
        adminLoginForm.show();

    }

    @FXML
    void btnCashierOnAction(ActionEvent event) {
        EmployeeLoginForm employeeLoginForm = EmployeeLoginForm.getInstance();
        employeeLoginForm.show();

    }

}

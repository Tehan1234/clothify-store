package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import single.AdminDashboardForm;
import single.EmployeeDashboardForm;

public class EmployeeLoginFormController {

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtPassword;

    @FXML
    void btnLoginOnAction(ActionEvent event) {
        String email = txtEmail.getText();
        String password = txtPassword.getText();
        if (email.equals("Employee@gmail.com") && password.equals("Employee")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Employee Login Alert");
            alert.setHeaderText("successfully Employee Logged!!");
            alert.setContentText(" Employee Logging successfully !!!!  .");

            String css = getClass().getResource("/css/Alert.css").toExternalForm();
            alert.getDialogPane().getStylesheets().add(css);

            alert.showAndWait();

            EmployeeDashboardForm employeeDashboardForm = EmployeeDashboardForm.getInstance();
            employeeDashboardForm.show();

            clearField();
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Employee Logging Failed");
            alert.setContentText("Employee Not Logged!!!.");

            String css = getClass().getResource("/css/Alert.css").toExternalForm();
            alert.getDialogPane().getStylesheets().add(css);
            alert.getDialogPane().setStyle("-fx-background-color: #d4edda; -fx-border-color: #c3e6cb; -fx-border-width: 2px;");

            alert.showAndWait();
            clearField();
        }
    }
    public void clearField(){
        txtEmail.setText(null);
        txtPassword.setText(null);

    }

}

package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import single.AdminDashboardForm;


public class AdminLoginFormController {

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtPassword;

    @FXML
    void btnLoginOnAction(ActionEvent event) {
        String email = txtEmail.getText();
        String password = txtPassword.getText();
        if (email.equals("Admin@gmail.com") && password.equals("Admin")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Admin Login Alert");
            alert.setHeaderText("successfully Admin Logged!!");
            alert.setContentText(" Admin Logging successfully !!!!  .");

            String css = getClass().getResource("/css/Alert.css").toExternalForm();
            alert.getDialogPane().getStylesheets().add(css);

            alert.showAndWait();

            AdminDashboardForm adminDashboardForm = AdminDashboardForm.getInstance();
            adminDashboardForm.show();

            clearField();
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Admin Logging Failed");
            alert.setContentText("Admin Not Logged!!!.");

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

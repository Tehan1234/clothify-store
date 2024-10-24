package single;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class EmployeeManagementForm {
    private static EmployeeManagementForm instance;
    private Stage stage;

    private EmployeeManagementForm() {
        stage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/employee_management_form.fxml"));
            Parent root = loader.load();
            stage.setScene(new Scene(root));
            stage.setTitle("Employee Login Form ");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static EmployeeManagementForm getInstance() {
        if (instance == null) {
            instance = new EmployeeManagementForm();
        }
        return instance;
    }

    public void show() {
        stage.toFront();
        stage.show();
    }

    public void close() {
        stage.close();
        instance = null; // Allow a new instance to be created next time
    }
}

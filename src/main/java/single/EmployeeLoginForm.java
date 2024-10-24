package single;

import controller.EmployeeLoginFormController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class EmployeeLoginForm {
    private static EmployeeLoginForm instance;
    private Stage stage;

    private EmployeeLoginForm() {
        stage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/employee_login_form.fxml"));
            Parent root = loader.load();
            stage.setScene(new Scene(root));
            stage.setTitle("Employee Login Form ");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static EmployeeLoginForm getInstance() {
        if (instance == null) {
            instance = new EmployeeLoginForm();
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

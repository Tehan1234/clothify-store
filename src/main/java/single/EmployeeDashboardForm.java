package single;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class EmployeeDashboardForm {
    private static EmployeeDashboardForm instance;
    private Stage stage;

    private EmployeeDashboardForm() {
        stage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/employee_dashboard_form.fxml"));
            Parent root = loader.load();
            stage.setScene(new Scene(root));
            stage.setTitle("Employee Dashboard Form ");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static EmployeeDashboardForm getInstance() {
        if (instance == null) {
            instance = new EmployeeDashboardForm();
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

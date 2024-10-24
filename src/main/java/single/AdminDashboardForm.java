package single;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminDashboardForm {
    private static AdminDashboardForm instance;
    private Stage stage;

    private AdminDashboardForm() {
        stage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/admin_dashboard_form.fxml"));
            Parent root = loader.load();
            stage.setScene(new Scene(root));
            stage.setTitle("Admin Dashboard Form ");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static AdminDashboardForm getInstance() {
        if (instance == null) {
            instance = new AdminDashboardForm();
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

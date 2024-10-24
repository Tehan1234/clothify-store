package single;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SupplierManagementForm {
    private static SupplierManagementForm instance;
    private Stage stage;

    private SupplierManagementForm() {
        stage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/supplier_Management_form.fxml"));
            Parent root = loader.load();
            stage.setScene(new Scene(root));
            stage.setTitle("Employee Login Form ");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static SupplierManagementForm getInstance() {
        if (instance == null) {
            instance = new SupplierManagementForm();
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

package single;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class OrderManagementForm {
    private static OrderManagementForm instance;
    private Stage stage;

    private OrderManagementForm() {
        stage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/order_management_form.fxml"));
            Parent root = loader.load();
            stage.setScene(new Scene(root));
            stage.setTitle("Order Management DashBoard ");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static OrderManagementForm getInstance() {
        if (instance == null) {
            instance = new OrderManagementForm();
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

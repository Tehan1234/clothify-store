package single;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PlaceOrderForm {

    private static PlaceOrderForm instance;
    private Stage stage;

    private PlaceOrderForm() {
        stage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/place_order_form.fxml"));
            Parent root = loader.load();
            stage.setScene(new Scene(root));
            stage.setTitle("Place Order Form ");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static PlaceOrderForm getInstance() {
        if (instance == null) {
            instance = new PlaceOrderForm();
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

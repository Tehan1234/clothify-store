package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import single.PlaceOrderForm;

public class OrderManagementFormController {

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnHomeOnAction(ActionEvent event) {

    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {
        PlaceOrderForm placeOrderForm = PlaceOrderForm.getInstance();
        placeOrderForm.show();


    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    @FXML
    void btnViewOrderOnAction(ActionEvent event) {

    }

}

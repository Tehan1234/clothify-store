package controller;

import dto.Item;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import service.ServiceFactory;
import service.custom.ItemService;
import service.custom.OrderService;
import util.ServiceType;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class PlaceOrderFormController implements Initializable {

    OrderService orderService= ServiceFactory.getInstance().getServiceType(ServiceType.ORDER);

    @FXML
    private ComboBox<?> cmbCustomerIds;

    @FXML
    private ComboBox<String> cmbItemCodes;

    @FXML
    private ComboBox<String> cmbSize;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colItemName;

    @FXML
    private TableColumn<?, ?> colQuantity;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;

    @FXML
    private Label lblTotal;

    @FXML
    private TableView<?> tblCart;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtOrderId;

    @FXML
    private TextField txtQuantity;

    @FXML
    private TextField txtStock;

    @FXML
    private TextField txtUnitPrice;

    @FXML
    void btnAddNewCustomer(ActionEvent event) {

    }

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {

    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDateAndTime();

        ObservableList<String> ItemCodeList = orderService.getAllItemCodes();
        cmbItemCodes.setItems(ItemCodeList);


        cmbItemCodes.getSelectionModel().selectedItemProperty().addListener((observableValue, s, newVal) -> {
            if (newVal!=null){
                SearchItem( newVal);
            }
        });

    }

    private void SearchItem(String newVal) {

        Item item = orderService.searchItem(newVal);
        txtDescription.setText(item.getItemName());
        txtStock.setText(String.valueOf(item.getItemQty()));
        txtUnitPrice.setText(String.valueOf(item.getItemUnitPrice()));
        cmbSize.setValue(item.getItemSize());
    }

    private void loadDateAndTime() {
        String dateNow = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        lblDate.setText(dateNow);


        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime now = LocalTime.now();
            String timeNow = String.format("%02d : %02d : %02d",
                    now.getHour(), now.getMinute(), now.getSecond());
            lblTime.setText(timeNow);
        }), new KeyFrame(Duration.seconds(1)));

        timeline.setCycleCount(Animation.INDEFINITE);  // Keep the timeline running indefinitely
        timeline.play();
    }



}

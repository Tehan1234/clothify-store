package controller;

import com.jfoenix.controls.JFXTextField;
import dto.Employee;
import dto.Item;
import entity.ItemEntity;
import entity.SupplierEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import service.ServiceFactory;
import service.custom.ItemService;
import util.ServiceType;

import java.net.URL;
import java.util.ResourceBundle;

public class ItemManagementFormController implements Initializable {

    @FXML
    private ComboBox<String> cmbCategory;

    @FXML
    private ComboBox<String> cmbSize;

    @FXML
    private ComboBox<String> cmbSupplierID;

    @FXML
    private TableColumn<?, ?> colCategory;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colItemName;

    @FXML
    private TableColumn<?, ?> colItemSize;

    @FXML
    private TableColumn<?, ?> colQtyOnHand;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private TableView<ItemEntity> tblItem;

    @FXML
    private JFXTextField txtItemCode;

    @FXML
    private JFXTextField txtItemName;

    @FXML
    private JFXTextField txtQtyOnHand;

    @FXML
    private JFXTextField txtUnitPrice;

    ItemService itemService  = ServiceFactory.getInstance().getServiceType(ServiceType.ITEM);

    @FXML
    void btnAddOnAction(ActionEvent event) {
        String itemCode = txtItemCode.getText();
        String itemName = txtItemName.getText();
        String category = cmbCategory.getValue();
        String size = cmbSize.getValue();
        double price = Double.parseDouble(txtUnitPrice.getText());
        int  itemQty = Integer.parseInt(txtQtyOnHand.getText());
        String supplierId = String.valueOf(cmbSupplierID.getValue());

        Item item =  new Item(itemCode,itemName,category,size,price,itemQty,supplierId);
        if (itemCode.isEmpty() || itemName.isEmpty() || category == null || size == null || supplierId.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Fill  the fields");
            alert.setTitle("Warning");
            alert.setHeaderText(null); // No header text for a simple warning
            alert.showAndWait();
        }
        if (itemService.addItem(item)){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Employee Alert");
            alert.setHeaderText("Successfully Added!");
            alert.setContentText("Item added Successfully");


            String css = getClass().getResource("/css/Alert.css").toExternalForm();
            alert.getDialogPane().getStylesheets().add(css);
            alert.getDialogPane().setStyle("-fx-background-color: #d4edda; -fx-border-color: #c3e6cb; -fx-border-width: 2px;");


            alert.showAndWait();


        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Operation Failed");
            alert.setContentText("Something went wrong.");

            // Customize the alert dialog
            String css = getClass().getResource("/css/Alert.css").toExternalForm();
            alert.getDialogPane().getStylesheets().add(css);
            alert.getDialogPane().setStyle("-fx-background-color: #d4edda; -fx-border-color: #c3e6cb; -fx-border-width: 2px;");

            alert.showAndWait();
        }


    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String itemid = txtItemCode.getText();
        boolean isDeleted = itemService.deleteItem(itemid);
        if(isDeleted){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Item Alert");
            alert.setHeaderText("Successfully Deleted!");
            alert.setContentText("Item Deleted Successfully");


            String css = getClass().getResource("/css/Alert.css").toExternalForm();
            alert.getDialogPane().getStylesheets().add(css);
            alert.getDialogPane().setStyle("-fx-background-color: #d4edda; -fx-border-color: #c3e6cb; -fx-border-width: 2px;");


            alert.showAndWait();
            clearForm();


        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Operation Failed");
            alert.setContentText("Something went wrong.");

            // Customize the alert dialog
            String css = getClass().getResource("/css/Alert.css").toExternalForm();
            alert.getDialogPane().getStylesheets().add(css);
            alert.getDialogPane().setStyle("-fx-background-color: #d4edda; -fx-border-color: #c3e6cb; -fx-border-width: 2px;");

            alert.showAndWait();
        }


    }

    @FXML
    void btnReloadOnAction(ActionEvent event) {
        loadTable();

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        String itemCodeText = txtItemCode.getText();
        if (itemCodeText.isEmpty()){
            new Alert(Alert.AlertType.WARNING,"search a Employee !!").showAndWait();
        }else{
            Item item = itemService.searchItem(itemCodeText);
            if(item==null){
                new Alert(Alert.AlertType.ERROR," Employee Not Found !!").showAndWait();

            }else{
                txtItemCode.setEditable(false);
                txtItemName.setText(item.getItemName());
                cmbCategory.setValue(item.getItemCategory());
                cmbSize.setValue(item.getItemSize());
                txtUnitPrice.setText(String.valueOf(item.getItemUnitPrice()));
                txtQtyOnHand.setText(String.valueOf(item.getItemQty()));
                cmbSupplierID.setValue(item.getSupplierId());

                new Alert(Alert.AlertType.INFORMATION," Employee searched Successfully !!").showAndWait();

            }
        }

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

        String itemCode = txtItemCode.getText();
        String itemName = txtItemName.getText();
        String category = cmbCategory.getValue();
        String size = cmbSize.getValue();
        double priceText = Double.parseDouble(txtUnitPrice.getText());
        int qtyText = Integer.parseInt(txtQtyOnHand.getText());
        String supplierId = String.valueOf(cmbSupplierID.getValue());

        Item item = new Item(itemCode,itemName,category,size,priceText,qtyText,supplierId);

        itemService.updateItem(item);









    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("itemCategory"));
        colItemSize.setCellValueFactory(new PropertyValueFactory<>("itemSize"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("itemUnitPrice"));

        tblItem.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {

            setData(newValue);
        }));



        ObservableList<String> category = FXCollections.observableArrayList();
        category.add("Gens");
        category.add("Ladies");
        category.add("Kids");
        cmbCategory.setItems(category);

        ObservableList<String> sizes = FXCollections.observableArrayList();
        sizes.add("XS");
        sizes.add("S");
        sizes.add("M");
        sizes.add("L");
        sizes.add("XL");
        sizes.add("XXL");
        cmbSize.setItems(sizes);

        ObservableList<String> allSuppliers = itemService.getAllSuppliers();
        cmbSupplierID.setItems(allSuppliers);


        generateId();
        loadTable();


    }

    private void setData(ItemEntity newValue) {
        txtItemCode.setText(newValue.getItemCode());
        txtItemName.setText(newValue.getItemName());
        cmbCategory.setValue(newValue.getItemCategory());
        cmbSize.setValue(newValue.getItemSize());
        txtUnitPrice.setText(String.valueOf(newValue.getItemUnitPrice()));

    }

    private void generateId() {
        // Retrieve the next employee ID from the database (e.g., "E001").
        String nextItem = itemService.getNextItem();

        // Extract the numeric part of the ID (e.g., from "E001" to 1).
        int idNumber = Integer.parseInt(nextItem.substring(1));

        // Increment the numeric part by 1.
        idNumber++;

        // Format the new ID back to the desired format (e.g., "E002").
        String newEmployeeId = String.format("E%03d", idNumber);

        // Set the new ID to the text field (txtEmployeeId).
        txtItemCode.setText(newEmployeeId);
    }

    private void clearForm(){
        txtItemCode.setText(null);
        txtItemName.setText(null);
        cmbCategory.setValue(null);
        cmbSize.setValue(null);
        txtUnitPrice.setText(null);
        txtQtyOnHand.setText(null);
        cmbSupplierID.setValue(null);
    }

    public void loadTable(){
        ObservableList<ItemEntity> itemList = itemService.getAllItem();
        tblItem.setItems(itemList);

    }











}

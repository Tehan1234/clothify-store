package controller;

import com.jfoenix.controls.JFXTextField;
import dto.Employee;
import dto.Supplier;
import entity.EmployeeEntity;
import entity.SupplierEntity;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import service.ServiceFactory;
import service.custom.EmployeeService;
import service.custom.SupplierService;
import util.ServiceType;

import java.net.URL;
import java.util.ResourceBundle;

import static com.itextpdf.io.codec.brotli.dec.Dictionary.setData;

public class SupplierManagementFormController implements Initializable {

    @FXML
    private TableColumn<?, ?> colCompanyName;

    @FXML
    private TableColumn<?, ?> colSupplierAddress;

    @FXML
    private TableColumn<?, ?> colSupplierId;

    @FXML
    private TableColumn<?, ?> colSupplierName;

    @FXML
    private TableView<SupplierEntity> tblSupplier;

    @FXML
    private JFXTextField txtCompanyName;

    @FXML
    private JFXTextField txtSupplierAddress;

    @FXML
    private JFXTextField txtSupplierId;

    @FXML
    private JFXTextField txtSupplierName;

    SupplierService supplierService = ServiceFactory.getInstance().getServiceType(ServiceType.SUPPLIER);


    @FXML
    void btnAddOnAction(ActionEvent event) {
        String supplierId = txtSupplierId.getText();
        String supplierName = txtSupplierName.getText();
        String companyName = txtCompanyName.getText();
        String supplierAddress = txtSupplierAddress.getText();

        Supplier supplier = new Supplier( supplierId,supplierName,companyName,supplierAddress);

            if (supplierId.isEmpty() || supplierName.isEmpty() || companyName.isEmpty()|| supplierAddress.isEmpty()){
                Alert alert = new Alert(Alert.AlertType.WARNING, "Fill All the fields");
                alert.setTitle("Warning");
                alert.setHeaderText(null); // No header text for a simple warning
                alert.showAndWait();
            }
            if (supplierService.addSupplier(supplier)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Supplier Alert");
                alert.setHeaderText("successfully Added");
                alert.setContentText(" Supplier Added successfully !!!!  .");
                String css = getClass().getResource("/css/Alert.css").toExternalForm();
                alert.getDialogPane().getStylesheets().add(css);

                alert.showAndWait();

                clearForm();

            }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Operation Failed");
            alert.setContentText("Supplier Not Added!!!.");


            String css = getClass().getResource("/css/Alert.css").toExternalForm();
            alert.getDialogPane().getStylesheets().add(css);
            alert.getDialogPane().setStyle("-fx-background-color: #d4edda; -fx-border-color: #c3e6cb; -fx-border-width: 2px;");

            alert.showAndWait();
            clearForm();
        }




    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String supplierId = txtSupplierId.getText();
        boolean isDeleted = supplierService.deleteSupplier(supplierId);
        if(isDeleted){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Supplier Alert");
            alert.setHeaderText("Successfully Deleted!");
            alert.setContentText("Supplier deleted Successfully");


            String css = getClass().getResource("/css/Alert.css").toExternalForm();
            alert.getDialogPane().getStylesheets().add(css);
            alert.getDialogPane().setStyle("-fx-background-color: #d4edda; -fx-border-color: #c3e6cb; -fx-border-width: 2px;");


            alert.showAndWait();
            Loadable();


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
        Loadable();

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        String supplierId = txtSupplierId.getText();
        if (supplierId.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Input Supplier Id !!").showAndWait();
        }
        Supplier supplier = supplierService.searchSupplier(supplierId);
        if (supplier ==  null){
            new Alert(Alert.AlertType.ERROR," Supplier Not Found !!").showAndWait();
        }else{
            txtSupplierId.setText(supplier.getSupplierId());
            txtCompanyName.setText(supplier.getSupplierName());
            txtCompanyName.setText(supplier.getCompanyName());
            txtSupplierAddress.setText(supplier.getSupplierAddress());

        }


    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

        String supplierId = txtSupplierId.getText();
        String supplierName = txtSupplierName.getText();
        String companyName = txtCompanyName.getText();
        String supplierAddress = txtSupplierAddress.getText();

        boolean isUpdated = supplierService.updateSupplier(new Supplier(supplierId, supplierName, companyName, supplierAddress));
        if (isUpdated){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Supplier Alert");
            alert.setHeaderText("successfully Updated");
            alert.setContentText(" Supplier Updated successfully !!!!  .");

            String css = getClass().getResource("/css/Alert.css").toExternalForm();
            alert.getDialogPane().getStylesheets().add(css);

            // Show the alert and wait for a response
            alert.showAndWait();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Operation Failed");
            alert.setContentText("Supplier Not Updated!!!.");

            // Customize the alert dialog
            String css = getClass().getResource("/css/Alert.css").toExternalForm();
            alert.getDialogPane().getStylesheets().add(css);
            alert.getDialogPane().setStyle("-fx-background-color: #d4edda; -fx-border-color: #c3e6cb; -fx-border-width: 2px;");

            alert.showAndWait();
        }


    }

    private void clearForm(){
        txtSupplierId.setText(null);
        txtSupplierName.setText(null);
        txtCompanyName.setText(null);
        txtSupplierAddress.setText(null);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        colSupplierName.setCellValueFactory(new PropertyValueFactory<>("supplierName"));
        colCompanyName.setCellValueFactory(new PropertyValueFactory<>("companyName"));
        colSupplierAddress.setCellValueFactory(new PropertyValueFactory<>("supplierAddress"));

        tblSupplier.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {

            setData(newValue);
        }));
        generateId();
    }

    private void setData(SupplierEntity newValue) {
        txtSupplierId.setText(newValue.getSupplierId());
        txtSupplierName.setText(newValue.getSupplierName());
        txtCompanyName.setText(newValue.getCompanyName());
        txtSupplierAddress.setText(newValue.getSupplierAddress());


    }
    ObservableList<SupplierEntity> allSuppliers = supplierService.getAllSuppliers();

    private void Loadable(){

        tblSupplier.setItems(allSuppliers);
    }

    private void generateId() {

        String nextSupplierId = supplierService.getNextSupplierId();

        int idNumber = Integer.parseInt(nextSupplierId.substring(1));
        idNumber++;
        String newEmployeeId = String.format("S%03d", idNumber);


        txtSupplierId.setText(newEmployeeId);
    }





}

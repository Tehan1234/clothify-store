package controller;

import com.jfoenix.controls.JFXTextField;
import dto.Employee;
import entity.EmployeeEntity;
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
import util.ServiceType;

import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeManagementFormController  implements Initializable {

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colEmployeeId;

    @FXML
    private TableColumn<?, ?> colEmployeeName;

    @FXML
    private TableView<EmployeeEntity> tblEmployee;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtEmployeeId;

    @FXML
    private JFXTextField txtEmployeeName;

    @FXML
    private JFXTextField txtPassword;

    EmployeeService employeeService = ServiceFactory.getInstance().getServiceType(ServiceType.EMPLOYEE);


    @FXML
    void btnAddOnAction(ActionEvent event){

        String employeeId= txtEmployeeId.getText();
        String employeeName = txtEmployeeName.getText();
        String txtEmails = txtEmail.getText();
        String password = txtPassword.getText();

        Employee employee = new Employee(employeeId, employeeName, txtEmails, password);

        if (employeeId.isEmpty() || employeeName.isEmpty() || txtEmails.isEmpty() || password.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Fill All the fields");
            alert.setTitle("Warning");
            alert.setHeaderText(null); // No header text for a simple warning
            alert.showAndWait();
        }
        if (employeeService.isPasswordExists(password)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText(" Password Not Valid");
            alert.setContentText("Password Cannot Be Accepted");

            String css = getClass().getResource("/css/Alert.css").toExternalForm();
            alert.getDialogPane().getStylesheets().add(css);


            alert.showAndWait();

           txtPassword.setText(null);

        }
        if (employeeService.addEmployee(employee)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Employee Alert");
            alert.setHeaderText("Successfully Added");
            alert.setContentText("Employee Added successfully !!!!.");

            // Customize the alert dialog
            String css = getClass().getResource("/css/Alert.css").toExternalForm();
            alert.getDialogPane().getStylesheets().add(css);

            // Show the alert and wait for a response
            alert.showAndWait();
        } else {
            // Show error alert if the addition failed
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Operation Failed");
            alert.setContentText("Customer Not Added!!!.");

            // Customize the alert dialog
            String css = getClass().getResource("/css/Alert.css").toExternalForm();
            alert.getDialogPane().getStylesheets().add(css);
            alert.getDialogPane().setStyle("-fx-background-color: #d4edda; -fx-border-color: #c3e6cb; -fx-border-width: 2px;");

            // Show the alert and wait for a response
            alert.showAndWait();
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

        String employeeId = txtEmployeeId.getText();
        boolean isDeleted = employeeService.deleteEmployee(employeeId);
        if(isDeleted){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Employee Alert");
            alert.setHeaderText("Successfully Added!");
            alert.setContentText("Employee added Successfully");

          
            String css = getClass().getResource("/css/Alert.css").toExternalForm();
            alert.getDialogPane().getStylesheets().add(css);
            alert.getDialogPane().setStyle("-fx-background-color: #d4edda; -fx-border-color: #c3e6cb; -fx-border-width: 2px;");

          
            alert.showAndWait();
            LoadTable();

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
        LoadTable();

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

        String employeeIdText = txtEmployeeId.getText();
        if (employeeIdText.isEmpty()){
            new Alert(Alert.AlertType.WARNING,"search a Employee !!").showAndWait();
        }else{
            Employee employee = employeeService.searchEmployee(employeeIdText);
            if(employee==null){
                new Alert(Alert.AlertType.ERROR," Employee Not Found !!").showAndWait();

            }else{
                txtEmployeeId.setEditable(false);
                txtEmployeeName.setText(employee.getEmployeeName());
                txtEmail.setText(employee.getEmail());
                new Alert(Alert.AlertType.INFORMATION," Employee searched Successfully !!").showAndWait();

            }
        }


    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String employeeId = txtEmployeeId.getText();
        String employeeName = txtEmployeeName.getText();
        String email = txtEmail.getText();
        String password = txtPassword.getText();

        boolean isUpdated = employeeService.updateEmployee(new Employee(employeeId, employeeName, email, password));
        if (isUpdated){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Employee Alert");
            alert.setHeaderText("successfully Updated");
            alert.setContentText(" Employee Updated successfully !!!!  .");

            LoadTable();
            clearForm();

            // Customize the alert dialog
            String css = getClass().getResource("/css/Alert.css").toExternalForm();
            alert.getDialogPane().getStylesheets().add(css);

            // Show the alert and wait for a response
            alert.showAndWait();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Operation Failed");
            alert.setContentText("Employee Not Updated!!!.");

            // Customize the alert dialog
            String css = getClass().getResource("/css/Alert.css").toExternalForm();
            alert.getDialogPane().getStylesheets().add(css);
            alert.getDialogPane().setStyle("-fx-background-color: #d4edda; -fx-border-color: #c3e6cb; -fx-border-width: 2px;");

            alert.showAndWait();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colEmployeeName.setCellValueFactory(new PropertyValueFactory<>("employeeName"));

        tblEmployee.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {

            setData(newValue);
        }));
        generateId();

    }



    private void setData(EmployeeEntity newValue) {
        if (newValue != null) {
            txtEmployeeId.setText(newValue.getEmployeeId());
            txtEmployeeName.setText(newValue.getEmployeeName());
            txtEmail.setText(newValue.getEmail());

        }
    }

    private void LoadTable(){
        ObservableList<EmployeeEntity> allEmployees = employeeService.getAllEmployees();
        tblEmployee.setItems(allEmployees);


    }

    private void generateId() {
        // Retrieve the next employee ID from the database (e.g., "E001").
        String nextEmployeeId = employeeService.getNextEmployeeId();

        // Extract the numeric part of the ID (e.g., from "E001" to 1).
        int idNumber = Integer.parseInt(nextEmployeeId.substring(1));

        // Increment the numeric part by 1.
        idNumber++;

        // Format the new ID back to the desired format (e.g., "E002").
        String newEmployeeId = String.format("E%03d", idNumber);

        // Set the new ID to the text field (txtEmployeeId).
        txtEmployeeId.setText(newEmployeeId);
    }

    private void clearForm(){
        txtEmployeeId.setText(null);
        txtEmployeeName.setText(null);
        txtEmail.setText(null);
        txtPassword.setText(null);
    }

}

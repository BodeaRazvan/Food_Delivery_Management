package org.example.control;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.example.App;
import org.example.bll.AdminBLL;
import org.example.bll.ClientBLL;
import org.example.bll.EmployeeBLL;
import org.example.hashing.Hashing;
import org.example.model.Admin;
import org.example.model.Client;
import org.example.model.Employee;

import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class LoginController {
    Controller controller = new Controller();

//normal user
    @FXML
    private TextField loginUsername;
    @FXML
    private PasswordField loginPassword;
    @FXML
    private TextField loginErrorLog;

    @FXML
    private void goToAdminLoginPage() throws IOException {
        App.setRoot("AdminLoginPage");
    }
    @FXML
    private void goToEmployeeLoginPage() throws IOException{
        App.setRoot("EmployeeLoginPage");
    }
    @FXML
    private void goToRegisterPage() throws IOException{
        App.setRoot("RegisterPage");
    }
    @FXML
    private void goBackToLogin() throws IOException{
        App.setRoot("LoginPage");
    }

    /**
     * This method takes the username and password entered in the visual interface and checks them
     * to see if they match the data in the database
     * The password is hashed and the hash code is compared with the one in the database
     * @return  - Int - used for stopping the method
     * @throws NoSuchAlgorithmException,IOException - errors
     */
    @FXML
    private int logIn() throws NoSuchAlgorithmException, IOException {
        ClientBLL clientBll = new ClientBLL();
        String username = loginUsername.getText();
        String password = loginPassword.getText();

        List<Client> clients = new ArrayList<>(clientBll.getClients());
        for(Client client:clients){
            if(username.equals(client.getUsername())){
                if(Hashing.hashPassword(password).equals(client.getPassword())){
                    FileWriter myWriter = new FileWriter("CurrentClient.txt");
                    myWriter.write(String.valueOf(client.getId()));
                    myWriter.close();
                    App.setRoot("ClientPage");
                    return 0;
                }
            }
        }
        loginErrorLog.setText("Incorrect username or password");
        loginUsername.clear();
        loginPassword.clear();
        return 0;
    }

//employee
    @FXML
    private TextField loginEmployeeUsername;
    @FXML
    private PasswordField loginEmployeePassword;
    @FXML
    private TextField loginEmployeeErrorLog;
    /**
     * This method takes the username and password entered in the visual interface and checks them
     * to see if they match the data in the database
     * The password is hashed and the hash code is compared with the one in the database
     * @return  - Int - used for stopping the method
     * @throws NoSuchAlgorithmException,IOException - errors
     */
    @FXML
    private int loginEmployee() throws NoSuchAlgorithmException, IOException {
        EmployeeBLL employeeBLL = new EmployeeBLL();
        String username = loginEmployeeUsername.getText();
        String password = loginEmployeePassword.getText();

        List<Employee> employees = employeeBLL.getEmployees();

        for (Employee employee : employees) {
            if (employee.getUsername().equals(username)) {
                if (employee.getPassword().equals(Hashing.hashPassword(password))) {
                    App.setRoot("EmployeePage");
                    return 0;
                }
            }
        }
        loginEmployeeErrorLog.setText("Invalid username or password");
        loginEmployeePassword.clear();
        loginEmployeeUsername.clear();
        return 0;
    }

//admin
    @FXML
    private TextField adminUsername;
    @FXML
    private PasswordField adminPassword;
    @FXML
    private TextField adminErrorLog;

    /**
     * This method takes the username and password entered in the visual interface and checks them
     * to see if they match the data in the database
     * The password is hashed and the hash code is compared with the one in the database
     * @return  - Int - used for stopping the method
     * @throws NoSuchAlgorithmException,IOException - errors
     */
    @FXML
    private int loginAdmin() throws NoSuchAlgorithmException, IOException {
        AdminBLL adminBLL = new AdminBLL();
        String username = adminUsername.getText();
        String password = adminPassword.getText();

        List<Admin> admins = adminBLL.getAdmins();

        for(Admin admin:admins){
            if(admin.getUsername().equals(username)){
                if(admin.getPassword().equals(Hashing.hashPassword(password))){
                    App.setRoot("AdminPage");
                    return 0;
                }
            }
        }
        adminUsername.clear();
        adminPassword.clear();
        adminErrorLog.setText("Invalid username or password");
        return 0;
    }
}

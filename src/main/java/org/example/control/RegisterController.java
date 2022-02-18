package org.example.control;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.example.App;
import org.example.bll.ClientBLL;
import org.example.hashing.Hashing;
import org.example.model.Client;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLIntegrityConstraintViolationException;

public class RegisterController {
    Controller controller=new Controller();
    @FXML
    private TextField registerUsername;
    @FXML
    private PasswordField registerPassword1;
    @FXML
    private PasswordField registerPassword2;
    @FXML
    private TextField registerAddress;
    @FXML
    private TextField registerEmail;
    @FXML
    private TextField errorLogRegister;

    @FXML
    private void goToLoginPage() throws IOException {
        App.setRoot("LoginPage");
    }
    @FXML
    private void clearFields(){
        registerUsername.clear();
        registerPassword1.clear();
        registerPassword2.clear();
        registerAddress.clear();
        registerEmail.clear();
    }
    /**
     * This method checks if the information entered by the user are valid and stores the account in the database
     * @return  - Int - the return is used for stopping the method if any problem is found
     * @throws IllegalAccessException,SQLIntegrityConstraintViolationException,NoSuchAlgorithmException - errors caused by calling the hash or trying to work with the database
     */
    @FXML
    private int registerUser() throws IllegalAccessException, SQLIntegrityConstraintViolationException, NoSuchAlgorithmException {
        errorLogRegister.clear();
        ClientBLL clientBll = new ClientBLL();
        if(registerEmail.getText().equals("") || registerAddress.getText().equals("") || registerUsername.getText().equals("") || registerPassword1.getText().equals("") || registerPassword2.getText().equals("")){
            errorLogRegister.setText("All fields must be completed");
            clearFields();
            return 0;
        }
        if(!registerPassword1.getText().equals(registerPassword2.getText())){
            errorLogRegister.setText("Passwords do not match");
            registerPassword2.clear();
            registerPassword1.clear();
            return 0;
        }
        if(!(registerEmail.getText().contains("@") && registerEmail.getText().contains("."))){
            errorLogRegister.setText("Email is not correct");
            registerEmail.clear();
            return 0;
        }
        Client client=new Client(registerUsername.getText(),Hashing.hashPassword(registerPassword1.getText()),registerEmail.getText(), registerAddress.getText());

        if(clientBll.insertClient(client)==1){
            errorLogRegister.setText("Username already exists");
            registerUsername.clear();
            return 0;
        }
        errorLogRegister.setText("Registered Successfully");
        return 0;
    }
}
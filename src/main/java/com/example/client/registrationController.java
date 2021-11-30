package com.example.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import users.ClientUser;

import java.io.IOException;

public class registrationController
{
    @FXML
    public PasswordField pwdFirst;
    @FXML
    public TextField userName;
    @FXML
    public PasswordField pwdFirst1;
    @FXML
    public Button regbtn;

    public void onSubmitClick(ActionEvent actionEvent) throws IOException
    {
        ClientUser user = new ClientUser(userName.getText(), pwdFirst1.getText());
        NetworkController.outputStream.writeObject("REGISTER");
        NetworkController.outputStream.writeObject(user);
        Stage stage = (Stage) regbtn.getScene().getWindow();
        stage.close();
    }
}

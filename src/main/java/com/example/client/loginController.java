package com.example.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import users.AdminUser;
import users.ClientUser;

import java.io.IOException;

public class loginController
{
    @FXML
    public Button loginbtn;
    public TextField usr;
    public PasswordField pwd;

    @FXML
    public void onLoginBtnClick(ActionEvent actionEvent) throws IOException, ClassNotFoundException
    {
        var user = new ClientUser(usr.getText(), pwd.getText());
        NetworkController.outputStream.writeObject("LOGIN");
        NetworkController.outputStream.writeObject("CLIENT");
        NetworkController.outputStream.writeObject(user);
        var isAuthorized =(Integer) NetworkController.inputStream.readObject();
        if(isAuthorized == 1)
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ClientView.fxml"));
            Parent root1 = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1, 800,600));
            stage.show();
            Stage stage1 = (Stage) loginbtn.getScene().getWindow();
            stage1.close();
        }

        else
        {


            FXMLLoader loader = new FXMLLoader(getClass().getResource("IncorrectPassword.fxml"));
            Parent root1 = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1, 300,300));
            stage.show();
            Stage stage1 = (Stage) loginbtn.getScene().getWindow();
            stage1.close();

        }

    }
}

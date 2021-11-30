package com.example.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import users.AdminUser;

import java.io.IOException;

public class AdminLoginController
{

    public TextField adminPwd;

    public void AdminEnter(ActionEvent actionEvent) throws IOException, ClassNotFoundException
    {
        var admin = new AdminUser(adminPwd.getText());
        NetworkController.outputStream.writeObject("LOGIN");
        NetworkController.outputStream.writeObject("ADMIN");
        NetworkController.outputStream.writeObject(admin);
        var isAuthorized =(Integer) NetworkController.inputStream.readObject();
        if(isAuthorized == 1)
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminView.fxml"));
            Parent root1 = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1, 800,600));
            stage.show();
            Stage stage1 = (Stage) adminPwd.getScene().getWindow();
            stage1.close();
        }
        else
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("IncorrectPassword.fxml"));
            Parent root1 = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1, 300,300));
            stage.show();
            Stage stage1 = (Stage) adminPwd.getScene().getWindow();
            stage1.close();
        }
    }
}

package com.example.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController
{


    @FXML
    public void onRegisterClick(ActionEvent actionEvent)
    {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("registrationForm.fxml"));
            Parent root1 = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1, 600,400));
            stage.showAndWait();


        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    @FXML
    public void onLoginClick(ActionEvent actionEvent)
    {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("loginForm.fxml"));
            Parent root1 = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1, 600,400));
            stage.showAndWait();
            stage.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void onAdminLogin(ActionEvent actionEvent) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminLogin.fxml"));
        Parent root1 = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1, 600,400));
        stage.showAndWait();
        stage.close();
    }
}
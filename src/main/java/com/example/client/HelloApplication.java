package com.example.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class HelloApplication extends Application
{
    @Override
    public void start(Stage stage) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.sizeToScene();
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws IOException
    {
        try
        {
            NetworkController.connection = new Socket(InetAddress.getLoopbackAddress(), 42069);
            NetworkController.inputStream = new ObjectInputStream(NetworkController.connection.getInputStream());
            NetworkController.outputStream = new ObjectOutputStream(NetworkController.connection.getOutputStream());
        }catch(UnknownHostException ex)
        {
            ex.printStackTrace();
        }
        launch();
    }
}
package com.example.client;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class IncorrectController
{

    public Button closer;

    public void onClose(ActionEvent actionEvent)
    {
        Stage stage = (Stage) closer.getScene().getWindow();
        stage.close();
    }
}

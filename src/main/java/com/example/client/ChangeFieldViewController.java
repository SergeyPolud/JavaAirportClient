package com.example.client;

import Entities.Passengers;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ChangeFieldViewController implements Initializable
{
    @FXML
    public ChoiceBox<String> choiceBox;
    public TextField inputText;
    public Passengers currentPas;

    public void onSubmitClicked(ActionEvent actionEvent)
    {

        try
        {
            switch (choiceBox.getValue())
        {
            case "Имя" -> currentPas.setName(inputText.getText());
            case "Фамилия" -> currentPas.setSurname(inputText.getText());
            case "Номер Паспорта" -> currentPas.setPassportNumber(Integer.parseInt(inputText.getText()));
        }
        NetworkController.outputStream.writeObject("UPDATE_PAS");
        NetworkController.outputStream.writeObject(currentPas);
        Stage stage = (Stage) inputText.getScene().getWindow();
        stage.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        choiceBox.setItems(FXCollections.observableArrayList("Имя", "Фамилия", "Номер Паспорта"));
    }

}

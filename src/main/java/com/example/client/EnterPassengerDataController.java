package com.example.client;

import Entities.Passengers;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Timestamp;

public class EnterPassengerDataController
{

    public TextField name;
    public TextField surname;
    public TextField nation;
    public TextField passport;

    public void onSubmit(ActionEvent actionEvent) throws IOException, ClassNotFoundException
    {
        var newPassenger = new Passengers();
        newPassenger.setName(name.getText());
        newPassenger.setSurname(surname.getText());
        newPassenger.setPassportNumber(Integer.parseInt(passport.getText()));
        newPassenger.setBirthDate(Timestamp.valueOf("1999-01-09 20:11:11.21"));
        newPassenger.setNationality(nation.getText());
        NetworkController.outputStream.writeObject("INSERT_PASSENGER");
        NetworkController.outputStream.writeObject(newPassenger);
        Stage stage1 = (Stage) name.getScene().getWindow();
        stage1.close();
    }
}

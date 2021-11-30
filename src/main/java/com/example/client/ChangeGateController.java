package com.example.client;

import Entities.Departures;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ChangeGateController
{
    public Departures departure;
    public Label flight;
    public TextField gatecodefield;

    public void setDeparture(Departures d)
    {
        this.departure = d;
        flight.setText("Новый выход для рейса " + departure.getFlightId());
    }

    public void onChange(ActionEvent actionEvent) throws IOException, ClassNotFoundException
    {
        var newcode = gatecodefield.getText();
        departure.setGateCode(newcode);
        NetworkController.outputStream.writeObject("CHANGE_DEP_STATUS");
        NetworkController.outputStream.writeObject(departure);
        if((Integer)NetworkController.inputStream.readObject() == 1)
        {
            Stage stage1 = (Stage) gatecodefield.getScene().getWindow();
            stage1.close();
        }
    }
}
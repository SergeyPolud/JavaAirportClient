package com.example.client;

import Entities.Arrivals;
import Entities.Departures;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ChangeBaggageController
{
    public Arrivals arrival;
    public Label flight;
    public TextField baggageCodefield;

    public void setArrival(Arrivals d)
    {
        this.arrival = d;
        flight.setText("Новая стойка для рейса " + arrival.getFlightId());
    }

    public void onChange(ActionEvent actionEvent) throws IOException, ClassNotFoundException
    {
        var newBgPoint = baggageCodefield.getText();
        arrival.setBaggagePoint(newBgPoint);
        NetworkController.outputStream.writeObject("CHANGE_ARR_STATUS");
        NetworkController.outputStream.writeObject(arrival);
        if((Integer)NetworkController.inputStream.readObject() == 1)
        {
            Stage stage1 = (Stage) baggageCodefield.getScene().getWindow();
            stage1.close();
        }
    }
}

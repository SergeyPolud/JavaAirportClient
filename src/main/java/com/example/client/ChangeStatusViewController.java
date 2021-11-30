package com.example.client;

import Entities.Departures;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ChangeStatusViewController
{
    public Departures arrival;
    public TextField statusfield;
    public Label flight;

    public void setArrival(Departures d)
    {
        this.arrival = d;
        flight.setText("Новый статус рейса " + arrival.getFlightId());
    }

    public void onChange(ActionEvent actionEvent) throws IOException, ClassNotFoundException
    {
        var newStatus = statusfield.getText();
        arrival.setStatus(newStatus);
        NetworkController.outputStream.writeObject("CHANGE_ARR_STATUS");
        NetworkController.outputStream.writeObject(arrival);
        if((Integer)NetworkController.inputStream.readObject() == 1)
        {
            Stage stage1 = (Stage) statusfield.getScene().getWindow();
            stage1.close();
        }
    }
}

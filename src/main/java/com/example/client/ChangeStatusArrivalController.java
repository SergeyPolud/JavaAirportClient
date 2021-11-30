package com.example.client;

import Entities.Arrivals;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ChangeStatusArrivalController
{
    public Arrivals departure;
    public TextField statusfield;
    public Label flight;

    public void setArrival(Arrivals d)
    {
        this.departure = d;
        flight.setText("Новый статус рейса " + departure.getFlightId());
    }

    public void onChange(ActionEvent actionEvent) throws IOException, ClassNotFoundException
    {
        var newStatus = statusfield.getText();
        departure.setStatus(newStatus);
        NetworkController.outputStream.writeObject("CHANGE_ARR_STATUS");
        NetworkController.outputStream.writeObject(departure);
        if((Integer)NetworkController.inputStream.readObject() == 1)
        {
            Stage stage1 = (Stage) statusfield.getScene().getWindow();
            stage1.close();
        }
    }
}

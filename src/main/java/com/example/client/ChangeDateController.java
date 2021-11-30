package com.example.client;

import Entities.Flights;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Timestamp;

public class ChangeDateController
{
    public TextField newDepDate;
    public TextField newArrDate;
    public Flights flight;

    public void onChangeDate(ActionEvent actionEvent) throws IOException, ClassNotFoundException
    {
        flight.setScheduledDeparture(Timestamp.valueOf(newDepDate.getText()));
        flight.setScheduledArrival(Timestamp.valueOf(newArrDate.getText()));
        NetworkController.outputStream.writeObject("CHANGE_FLIGHT");
        NetworkController.outputStream.writeObject(flight);
        if((Integer)NetworkController.inputStream.readObject() == 1)
        {
            Stage s = (Stage) newArrDate.getScene().getWindow();
            s.close();
        }
    }

    public void setFlight(Flights f)
    {
        this.flight = f;
    }
}

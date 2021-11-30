package com.example.client;

import Entities.Booking;
import Entities.Flights;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class ChangePriceController
{
    public TextField changePriceField;
    private Booking flight;

    public void setFlight(Booking flight)
    {
        this.flight = flight;
    }

    public void onSubmit(ActionEvent actionEvent) throws IOException, ClassNotFoundException
    {
        flight.setPrice(BigDecimal.valueOf(Double.parseDouble(changePriceField.getText())));
        NetworkController.outputStream.writeObject("CHANGE_BOOKING");
        NetworkController.outputStream.writeObject(flight);
        if((Integer)NetworkController.inputStream.readObject() == 1)
        {
            Stage s = (Stage) changePriceField.getScene().getWindow();
            s.close();
        }
    }
}

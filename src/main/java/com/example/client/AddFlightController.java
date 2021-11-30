package com.example.client;

import Entities.Booking;
import Entities.Flights;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class AddFlightController
{

    public TextField flightId;
    public TextField from;
    public TextField to;
    public TextField price;
    public TextField dep;
    public TextField arr;

    public void onAdd(ActionEvent actionEvent) throws IOException, ClassNotFoundException
    {
        var flight = new Flights();
        flight.setFlightId(flightId.getText());
        flight.setDepartureAirportIata(from.getText());
        flight.setArrivalAirportIata(to.getText());
        flight.setScheduledDeparture(Timestamp.valueOf(dep.getText()));
        flight.setScheduledArrival(Timestamp.valueOf(arr.getText()));
        var booking = new Booking();
        booking.setFlightId(flight.getFlightId());
        booking.setPrice(BigDecimal.valueOf(Double.parseDouble(price.getText())));
        NetworkController.outputStream.writeObject("ADD_FLIGHT");
        NetworkController.outputStream.writeObject(flight);
        NetworkController.outputStream.writeObject(booking);
        if((Integer)NetworkController.inputStream.readObject() == 1)
        {
            Stage s = (Stage) arr.getScene().getWindow();
            s.close();
        }
    }
}

package com.example.client;

import Entities.Departures;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Locale;
import java.util.Random;

public class AddDepartureController
{

    public TextField flightID;
    public TextField depTime;
    public TextField status;

    public void onAddNew(ActionEvent actionEvent) throws IOException, ClassNotFoundException
    {
        var departure = new Departures();
        departure.setFlightId(flightID.getText());
        departure.setActualDeparture(Timestamp.valueOf(depTime.getText()));
        departure.setStatus(status.getText());
        var rd = new Random();
        var rnd = rd.nextInt(2);
        StringBuilder gatecode = new StringBuilder();
        departure.setRunwayId(rnd);
        var code = rd.nextInt(20);
        var c = (char) (rd.nextInt(6) + 'a');
        gatecode.append(code).append(c);
        departure.setGateCode(gatecode.toString().toUpperCase());
        NetworkController.outputStream.writeObject("ADD_DEPARTURE");
        NetworkController.outputStream.writeObject(departure);
        if((Integer)NetworkController.inputStream.readObject() != 0)
        {
        Stage stage1 = (Stage) status.getScene().getWindow();
        stage1.close();
        }
    }
}

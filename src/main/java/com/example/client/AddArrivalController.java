package com.example.client;

import Entities.Arrivals;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Random;

public class AddArrivalController
{
    public TextField flightID;
    public TextField depTime;
    public TextField status;

    public void onAddNew(ActionEvent actionEvent) throws IOException, ClassNotFoundException
    {
        var arrival = new Arrivals();
        arrival.setFlightId(flightID.getText());
        arrival.setActualArrival(Timestamp.valueOf(depTime.getText()));
        arrival.setStatus(status.getText());
        var rd = new Random();
        StringBuilder gatecode = new StringBuilder();
        var code = rd.nextInt(20);
        var c = (char) (rd.nextInt(6) + 'a');
        gatecode.append(code).append(" ").append(c);
        arrival.setBaggagePoint(gatecode.toString().toUpperCase());
        NetworkController.outputStream.writeObject("ADD_ARRIVAL");
        NetworkController.outputStream.writeObject(arrival);
        if((Integer)NetworkController.inputStream.readObject() != 0)
        {
            Stage stage1 = (Stage) status.getScene().getWindow();
            stage1.close();
        }
    }
}

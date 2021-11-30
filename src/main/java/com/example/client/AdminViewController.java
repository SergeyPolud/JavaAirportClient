package com.example.client;

import Entities.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdminViewController implements Initializable
{
    public TableView<Departures> departuretable;
    public TableColumn<Departures, String> flight;
    public TableColumn<Departures, Timestamp> deptime;
    public TableColumn<Departures, String> gatecode;
    public TableColumn<Departures, String> status;

    public TableView<Arrivals> arrTable;
    public TableColumn<Arrivals, String> flightArr;
    public TableColumn<Arrivals, String> baggage;
    public TableColumn<Arrivals, Timestamp> arrTime;
    public TableColumn<Arrivals, String> statusArr;

    public TableView<WrapperBooking> availableFlights;
    public TableColumn<WrapperBooking, String> flightNum;
    public TableColumn<WrapperBooking, String> from;
    public TableColumn<WrapperBooking, String> to;
    public TableColumn<WrapperBooking, BigDecimal> price;
    public TableColumn<WrapperBooking, Timestamp> depart;
    public TableColumn<WrapperBooking, Timestamp> arrive;

    public List<Booking> books;
    public List<Flights> flights;


    public void onDeparturesSelected(Event event) throws IOException
    {
        departuretable.getItems().clear();
        NetworkController.outputStream.writeObject("GET_DEPARTURES");
        List<Departures> departures = null;
        try
        {
            departures = (List<Departures>) NetworkController.inputStream.readObject(); //deserialize
        } catch (ClassNotFoundException | IOException | ClassCastException ex){
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        assert departures != null;
        ObservableList<Departures> deps = FXCollections.observableList(departures);
        for(var d : deps) departuretable.getItems().add(d);
    }

    public void onArrivalsSelected(Event event) throws IOException
    {
        arrTable.getItems().clear();
        NetworkController.outputStream.writeObject("GET_ARRIVALS");
        List<Arrivals> arrivals = null;
        try
        {
            arrivals = (List<Arrivals>) NetworkController.inputStream.readObject(); //deserialize
        } catch (ClassNotFoundException | IOException | ClassCastException ex){
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        assert arrivals != null;
        ObservableList<Arrivals> arrs = FXCollections.observableList(arrivals);
        for(var a : arrs) arrTable.getItems().add(a);

    }

    public void onAddDeparture(ActionEvent actionEvent) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddDepartureView.fxml"));
        Parent root1 = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1, 400,400));
        stage.showAndWait();
        onDeparturesSelected(actionEvent);
    }

    public void onChangeGCode(ActionEvent actionEvent) throws IOException
    {
        var dep = departuretable.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ChangeGateView.fxml"));
        Parent root1 = loader.load();
        ChangeGateController c = loader.getController();
        c.setDeparture(dep);
        Stage stage = new Stage();
        stage.setScene(new Scene(root1, 292,182));
        stage.showAndWait();
        onDeparturesSelected(actionEvent);
    }

    public void onDeleteFlight(ActionEvent actionEvent) throws IOException, ClassNotFoundException
    {
        var flight = departuretable.getSelectionModel().getSelectedItem();
        NetworkController.outputStream.writeObject("DELETE_DEPARTURE");
        NetworkController.outputStream.writeObject(flight);
        if((Integer)NetworkController.inputStream.readObject() == 1) onDeparturesSelected(actionEvent);
    }

    public void onSetStatus(ActionEvent actionEvent) throws IOException
    {
        var flight = departuretable.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ChangeStatusView.fxml"));
        Parent root1 = loader.load();
        ChangeStatusViewController c = loader.getController();
        c.setArrival(flight);
        Stage stage = new Stage();
        stage.setScene(new Scene(root1, 292,182));
        stage.showAndWait();
        onDeparturesSelected(actionEvent);
    }

    public void onAddNewArrival(ActionEvent actionEvent) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddArrivalView.fxml"));
        Parent root1 = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1, 400,400));
        stage.showAndWait();
        onArrivalsSelected(actionEvent);
    }

    public void onChangeBaggage(ActionEvent actionEvent) throws IOException
    {
        var flight = arrTable.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ChangeBaggageView.fxml"));
        Parent root1 = loader.load();
        ChangeBaggageController c = loader.getController();
        c.setArrival(flight);
        Stage stage = new Stage();
        stage.setScene(new Scene(root1, 292,182));
        stage.showAndWait();
        onArrivalsSelected(actionEvent);
    }

    public void onDeleteAriival(ActionEvent actionEvent) throws IOException, ClassNotFoundException
    {
        var flight = arrTable.getSelectionModel().getSelectedItem();
        NetworkController.outputStream.writeObject("DELETE_ARRIVAL");
        NetworkController.outputStream.writeObject(flight);
        if((Integer)NetworkController.inputStream.readObject() == 1) onArrivalsSelected(actionEvent);
    }

    public void onSetArrStatus(ActionEvent actionEvent) throws IOException
        {
        var flight = arrTable.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ChangeStatusArrivalView.fxml"));
        Parent root1 = loader.load();
        ChangeStatusArrivalController c = loader.getController();
        c.setArrival(flight);
        Stage stage = new Stage();
        stage.setScene(new Scene(root1, 292,182));
        stage.showAndWait();
        onArrivalsSelected(actionEvent);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        flight.setCellValueFactory(new PropertyValueFactory<>("flightId"));
        deptime.setCellValueFactory(new PropertyValueFactory<>("actualDeparture"));
        gatecode.setCellValueFactory(new PropertyValueFactory<>("gateCode"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        flightArr.setCellValueFactory(new PropertyValueFactory<>("flightId"));
        baggage.setCellValueFactory(new PropertyValueFactory<>("baggagePoint"));
        arrTime.setCellValueFactory(new  PropertyValueFactory<>("actualArrival"));
        statusArr.setCellValueFactory(new PropertyValueFactory<>("status"));
        flightNum.setCellValueFactory(new PropertyValueFactory<>("flightnum"));
        from.setCellValueFactory(new PropertyValueFactory<>("from"));
        to.setCellValueFactory(new PropertyValueFactory<>("to"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        depart.setCellValueFactory(new  PropertyValueFactory<>("dep"));
        arrive.setCellValueFactory(new PropertyValueFactory<>("arr"));
    }

    public void onPlanesTabSelected(Event event)
    {
        //TODO:Implement Planes CRUD
    }

    public void onPilotsTabSelected(Event event)
    {
        //TODO:Implement Pilots CRUD
    }

    public void onRWYsTabSelected(Event event)
    {
        //TODO:Implement Runways Management
    }

    public void onFlightsSelected(Event event) throws IOException
    {
        availableFlights.getItems().clear();
        NetworkController.outputStream.writeObject("GET_ALL_FLIGHTS");

        List<WrapperBooking> wrapper = null;
        try
        {
            flights = (List<Flights>) NetworkController.inputStream.readObject();
            books = (List<Booking>) NetworkController.inputStream.readObject();
            wrapper = new ArrayList<>();
            for (var f : flights)
            {
                var b = new WrapperBooking();
                b.setArr(f.getScheduledArrival());
                b.setDep(f.getScheduledDeparture());
                b.setFrom(f.getDepartureAirportIata());
                b.setTo(f.getArrivalAirportIata());
                b.setFlightnum(f.getFlightId());
                wrapper.add(b);
            }
            for (int i = 0; i < wrapper.size(); i++)
            {
                var el = wrapper.get(i);
                el.setPrice(books.get(i).getPrice());
                wrapper.set(i, el);
            }
        } catch (ClassNotFoundException | IOException | ClassCastException ex)
        {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        assert wrapper != null;
        ObservableList<WrapperBooking> flIds = FXCollections.observableList(wrapper);
        for (WrapperBooking f : flIds) availableFlights.getItems().add(f);
    }


    public void onAddFlight(ActionEvent actionEvent) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddFlightView.fxml"));
        Parent root1 = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1, 400,400));
        stage.showAndWait();
        onFlightsSelected(actionEvent);
    }

    public void onChangePrice(ActionEvent actionEvent) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ChangePriceView.fxml"));
        Parent root1 = loader.load();
        ChangePriceController c = loader.getController();
        var selected = availableFlights.getSelectionModel().getSelectedItem();
        var booking = books
                .stream()
                .filter(fid -> fid.getFlightId().equals(selected.flightnum))
                .findFirst()
                .get();
        c.setFlight(booking);
        Stage stage = new Stage();
        stage.setScene(new Scene(root1, 288,148));
        stage.showAndWait();
        onFlightsSelected(actionEvent);
    }

    public void onDeleteF(ActionEvent actionEvent) throws IOException
    {
        var selected = availableFlights.getSelectionModel().getSelectedItem();
        var booking = books
                .stream()
                .filter(fid -> fid.getFlightId().equals(selected.flightnum))
                .findFirst()
                .get();
        var flight = flights
                .stream()
                .filter(fid -> fid.getFlightId().equals(selected.flightnum))
                .findFirst()
                .get();
        NetworkController.outputStream.writeObject("DELETE_FLIGHT");
        NetworkController.outputStream.writeObject(flight);
        NetworkController.outputStream.writeObject(booking);
        onFlightsSelected(actionEvent);
    }

    public void onChangeDate(ActionEvent actionEvent) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ChangeDateView.fxml"));
        Parent root1 = loader.load();
        ChangeDateController c = loader.getController();
        var selected = availableFlights.getSelectionModel().getSelectedItem();
        var flight = flights
                .stream()
                .filter(fid -> fid.getFlightId().equals(selected.flightnum))
                .findFirst()
                .get();
        c.setFlight(flight);
        Stage stage = new Stage();
        stage.setScene(new Scene(root1, 400,400));
        stage.showAndWait();
        onFlightsSelected(actionEvent);
    }
}

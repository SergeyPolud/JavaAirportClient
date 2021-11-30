package com.example.client;

import Entities.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

public class ClientViewController implements Initializable
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
    public Passengers currentPas;

    public TableView<WrapperBooking> availableFlights;
    public TableColumn<WrapperBooking, String> flightNum;
    public TableColumn<WrapperBooking, String> from;
    public TableColumn<WrapperBooking, String> to;
    public TableColumn<WrapperBooking, BigDecimal> price;
    public TableColumn<WrapperBooking, Timestamp> depart;
    public TableColumn<WrapperBooking, Timestamp> arrive;

    public Label ClockLabel;
    public Label surname;
    public Label name;
    public Label nation;
    public Label passport;
    public Label Status;
    public List<Booking> books;
    public Booking bought;
    public ImageView logo;
    public TextField fId;
    public TextField PassportNumber;
    public CheckBox acceptRules;
    public ImageView qrBoardingPass;
    public Label qrcode_success;
    public Label statusLabel;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        flight.setCellValueFactory(new PropertyValueFactory<>("flightId"));
        deptime.setCellValueFactory(new PropertyValueFactory<>("actualDeparture"));
        gatecode.setCellValueFactory(new PropertyValueFactory<>("gateCode"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        flightArr.setCellValueFactory(new PropertyValueFactory<>("flightId"));
        baggage.setCellValueFactory(new PropertyValueFactory<>("baggagePoint"));
        arrTime.setCellValueFactory(new PropertyValueFactory<>("actualArrival"));
        statusArr.setCellValueFactory(new PropertyValueFactory<>("status"));
        flightNum.setCellValueFactory(new PropertyValueFactory<>("flightnum"));
        from.setCellValueFactory(new PropertyValueFactory<>("from"));
        to.setCellValueFactory(new PropertyValueFactory<>("to"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        depart.setCellValueFactory(new PropertyValueFactory<>("dep"));
        arrive.setCellValueFactory(new PropertyValueFactory<>("arr"));
    }

    public void onDeparturesSelected(Event event) throws IOException
    {
        departuretable.getItems().clear();
        NetworkController.outputStream.writeObject("GET_DEPARTURES");
        List<Departures> departures = null;
        try
        {
            departures = (List<Departures>) NetworkController.inputStream.readObject(); //deserialize
        } catch (ClassNotFoundException | IOException | ClassCastException ex)
        {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        assert departures != null;
        ObservableList<Departures> deps = FXCollections.observableList(departures);
        for (var d : deps) departuretable.getItems().add(d);
    }

    public void onArrivalsSelected(Event event) throws IOException
    {
        arrTable.getItems().clear();
        NetworkController.outputStream.writeObject("GET_ARRIVALS");
        List<Arrivals> arrivals = null;
        try
        {
            arrivals = (List<Arrivals>) NetworkController.inputStream.readObject(); //deserialize
        } catch (ClassNotFoundException | IOException | ClassCastException ex)
        {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        assert arrivals != null;
        ObservableList<Arrivals> arrs = FXCollections.observableList(arrivals);
        for (var a : arrs) arrTable.getItems().add(a);

    }

    public void onChangePData(ActionEvent actionEvent) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("EnterPData.fxml"));
        Parent root1 = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1, 400, 400));
        stage.showAndWait();
        UpdatePData();
    }

    public void UpdatePData()
    {

        try
        {
            currentPas = (Passengers) NetworkController.inputStream.readObject();
        } catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        assert currentPas != null;
        name.setText(currentPas.getName());
        surname.setText(currentPas.getSurname());
        nation.setText(currentPas.getNationality());
        passport.setText(currentPas.getPassportNumber().toString());
    }

    public void onChangePDataByField(ActionEvent actionEvent) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ChangeFieldView.fxml"));
        Parent root1 = loader.load();
        ChangeFieldViewController c = loader.getController();
        c.currentPas = currentPas;
        Stage stage = new Stage();
        stage.setScene(new Scene(root1, 400, 148));
        stage.showAndWait();
        UpdatePData();
    }

    public void onClientTabSelected(Event event)
    {
        try
        {
            Image image = new Image("file:" + "C:\\Users\\serge\\IdeaProjects\\client\\src\\airport.png");
            logo.setImage(image);
            logo.setCache(true);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        Thread timerThread = new Thread(() ->
        {
            while (true)
            {
                try
                {
                    Thread.sleep(1000);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                final String time = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
                Platform.runLater(() -> ClockLabel.setText(time));
            }
        });
        timerThread.start();
    }

    public void onBuySelected(Event event) throws IOException
    {

        availableFlights.getItems().clear();
        NetworkController.outputStream.writeObject("GET_ALL_FLIGHTS");
        List<Flights> flights = null;
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

    public void onBuySubmitted(ActionEvent actionEvent) throws IOException
    {
        var flightID = fId.getText();
        var availableIDS = availableFlights.getItems();
        boolean found = false;
        for (var i : availableIDS)
        {
            if (Objects.equals(i.getFlightnum(), flightID))
            {
                StringBuilder gatecode = new StringBuilder();
                Random rd = new Random();
                var num = rd.nextInt(20);
                var c = (char) (rd.nextInt(6) + 'a');
                gatecode.append(num).append(c);
                found = true;
                Status.setText("ПОКУПКА УСПЕШНА! МЕСТО " + gatecode.toString().toUpperCase());
                Booking buy = null;
                for (var b : books) if (b.getFlightId().equals(i.getFlightnum())) buy = b;
                assert buy != null;
                buy.setSeatNumber(gatecode.toString().toUpperCase());
                NetworkController.outputStream.writeObject("BUY_SEAT");
                NetworkController.outputStream.writeObject(buy);
                bought = buy;
                break;
            }
        }
        if (!found)
        {
            Status.setTextFill(Color.RED);
            Status.setText("ПОКУПКА НЕ УДАЛАСЬ, ОШИБКА");
        }
    }

    public void onRegisterSubmitted(ActionEvent actionEvent)
    {
        var passport = Integer.parseInt(PassportNumber.getText());
        if (acceptRules.isSelected())
        {
            try
            {
                NetworkController.outputStream.writeObject("REGISTER_PASSENGER");
                NetworkController.outputStream.writeObject(passport);
                NetworkController.outputStream.writeObject(currentPas);
                NetworkController.outputStream.writeObject(bought);
                var boardingPass = (Boardingpasses) NetworkController.inputStream.readObject();
                printQr(boardingPass);
            } catch (ClassNotFoundException | IOException e)
            {
                statusLabel.setTextFill(Color.RED);
                statusLabel.setText("Ошибка! Проверьте введенные данные");
                e.printStackTrace();
            }

        } else
        {
            statusLabel.setTextFill(Color.RED);
            statusLabel.setText("Вы должны подтвердить согласие");
        }
    }

    private void printQr(Boardingpasses p)
    {
        var infoString = p.toString() + " passport = " + currentPas.getPassportNumber() + " } ";
        var output = QRCode.from(infoString).to(ImageType.PNG).withSize(200, 200).stream();
        var input = new ByteArrayInputStream(output.toByteArray());
        qrBoardingPass.setImage(new Image(input));
        qrBoardingPass.setStyle("-fx-stroke-width: 2; -fx-stroke: blue");
        qrcode_success.setTextFill(Color.GREEN);
        qrcode_success.setText("QR-код посадочного талона");
    }
}



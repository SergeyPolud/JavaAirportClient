package Entities;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;


public class Flights implements Serializable
{
    @Serial
    private static final long serialVersionUID = 1L;
    private String flightId;
    private Timestamp scheduledDeparture;
    private Timestamp scheduledArrival;
    private String departureAirportIata;
    private String arrivalAirportIata;
    private String status;


    public String getFlightId()
    {
        return flightId;
    }

    public void setFlightId(String flightId)
    {
        this.flightId = flightId;
    }


    public Timestamp getScheduledDeparture()
    {
        return scheduledDeparture;
    }

    public void setScheduledDeparture(Timestamp scheduledDeparture)
    {
        this.scheduledDeparture = scheduledDeparture;
    }


    public Timestamp getScheduledArrival()
    {
        return scheduledArrival;
    }

    public void setScheduledArrival(Timestamp scheduledArrival)
    {
        this.scheduledArrival = scheduledArrival;
    }


    public String getDepartureAirportIata()
    {
        return departureAirportIata;
    }

    public void setDepartureAirportIata(String departureAirportIata)
    {
        this.departureAirportIata = departureAirportIata;
    }

    public String getArrivalAirportIata()
    {
        return arrivalAirportIata;
    }

    public void setArrivalAirportIata(String arrivalAirportIata)
    {
        this.arrivalAirportIata = arrivalAirportIata;
    }


    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flights flights = (Flights) o;
        return Objects.equals(flightId, flights.flightId) && Objects.equals(scheduledDeparture, flights.scheduledDeparture) && Objects.equals(scheduledArrival, flights.scheduledArrival) && Objects.equals(departureAirportIata, flights.departureAirportIata) && Objects.equals(arrivalAirportIata, flights.arrivalAirportIata) && Objects.equals(status, flights.status);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(flightId, scheduledDeparture, scheduledArrival, departureAirportIata, arrivalAirportIata, status);
    }
}

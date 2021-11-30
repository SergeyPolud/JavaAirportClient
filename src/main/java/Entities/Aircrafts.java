package Entities;

import java.util.Objects;


public class Aircrafts
{
    private Integer aircraftId;
    private String type;
    private String regNumber;
    private String flightId;


    public Integer getAircraftId()
    {
        return aircraftId;
    }

    public void setAircraftId(Integer aircraftId)
    {
        this.aircraftId = aircraftId;
    }


    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }


    public String getRegNumber()
    {
        return regNumber;
    }

    public void setRegNumber(String regNumber)
    {
        this.regNumber = regNumber;
    }


    public String getFlightId()
    {
        return flightId;
    }

    public void setFlightId(String flightId)
    {
        this.flightId = flightId;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aircrafts aircrafts = (Aircrafts) o;
        return Objects.equals(aircraftId, aircrafts.aircraftId) && Objects.equals(type, aircrafts.type) && Objects.equals(regNumber, aircrafts.regNumber) && Objects.equals(flightId, aircrafts.flightId);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(aircraftId, type, regNumber, flightId);
    }
}

package Entities;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;


public class Departures implements Serializable
{
    @Serial
    private static final long serialVersionUID = 2L;
    private Integer departureId;
    private String gateCode;
    private Timestamp actualDeparture;
    private String status;
    private String flightId;
    private Integer runwayId;


    public Integer getDepartureId()
    {
        return departureId;
    }

    public void setDepartureId(Integer departureId)
    {
        this.departureId = departureId;
    }


    public String getGateCode()
    {
        return gateCode;
    }

    public void setGateCode(String gateCode)
    {
        this.gateCode = gateCode;
    }

    public Timestamp getActualDeparture()
    {
        return actualDeparture;
    }

    public void setActualDeparture(Timestamp actualDeparture)
    {
        this.actualDeparture = actualDeparture;
    }


    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }


    public String getFlightId()
    {
        return flightId;
    }

    public void setFlightId(String flightId)
    {
        this.flightId = flightId;
    }


    public Integer getRunwayId()
    {
        return runwayId;
    }

    public void setRunwayId(Integer runwayId)
    {
        this.runwayId = runwayId;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Departures that = (Departures) o;
        return Objects.equals(departureId, that.departureId) && Objects.equals(gateCode, that.gateCode) && Objects.equals(actualDeparture, that.actualDeparture) && Objects.equals(status, that.status) && Objects.equals(flightId, that.flightId) && Objects.equals(runwayId, that.runwayId);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(departureId, gateCode, actualDeparture, status, flightId, runwayId);
    }
}

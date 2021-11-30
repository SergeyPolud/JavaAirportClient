package Entities;

import java.util.Objects;

public class Flightpilots
{
    private Integer pilotId;
    private String flightId;


    public Integer getPilotId()
    {
        return pilotId;
    }

    public void setPilotId(Integer pilotId)
    {
        this.pilotId = pilotId;
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
        Flightpilots that = (Flightpilots) o;
        return Objects.equals(pilotId, that.pilotId) && Objects.equals(flightId, that.flightId);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(pilotId, flightId);
    }
}

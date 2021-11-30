package Entities;

import java.util.Objects;


public class Pilots
{
    private Integer pilotId;
    private String name;
    private String surname;
    private String hoursFlied;
    private Integer pilotscol;


    public Integer getPilotId()
    {
        return pilotId;
    }

    public void setPilotId(Integer pilotId)
    {
        this.pilotId = pilotId;
    }


    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }


    public String getSurname()
    {
        return surname;
    }

    public void setSurname(String surname)
    {
        this.surname = surname;
    }


    public String getHoursFlied()
    {
        return hoursFlied;
    }

    public void setHoursFlied(String hoursFlied)
    {
        this.hoursFlied = hoursFlied;
    }


    public Integer getPilotscol()
    {
        return pilotscol;
    }

    public void setPilotscol(Integer pilotscol)
    {
        this.pilotscol = pilotscol;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pilots pilots = (Pilots) o;
        return Objects.equals(pilotId, pilots.pilotId) && Objects.equals(name, pilots.name) && Objects.equals(surname, pilots.surname) && Objects.equals(hoursFlied, pilots.hoursFlied) && Objects.equals(pilotscol, pilots.pilotscol);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(pilotId, name, surname, hoursFlied, pilotscol);
    }
}

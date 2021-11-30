package Entities;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;


public class Passengers implements Serializable
{
    @Serial
    private static final long serialVersionUID = 5L;
    private Integer passengerId;
    private String name;
    private String surname;
    private Timestamp birthDate;
    private String nationality;
    private Integer passportNumber;


    public Integer getPassengerId()
    {
        return passengerId;
    }

    public void setPassengerId(Integer passengerId)
    {
        this.passengerId = passengerId;
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


    public Timestamp getBirthDate()
    {
        return birthDate;
    }

    public void setBirthDate(Timestamp birthDate)
    {
        this.birthDate = birthDate;
    }


    public String getNationality()
    {
        return nationality;
    }

    public void setNationality(String nationality)
    {
        this.nationality = nationality;
    }


    public Integer getPassportNumber()
    {
        return passportNumber;
    }

    public void setPassportNumber(Integer passportNumber)
    {
        this.passportNumber = passportNumber;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Passengers that = (Passengers) o;
        return Objects.equals(passengerId, that.passengerId) && Objects.equals(name, that.name) && Objects.equals(surname, that.surname) && Objects.equals(birthDate, that.birthDate) && Objects.equals(nationality, that.nationality) && Objects.equals(passportNumber, that.passportNumber);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(passengerId, name, surname, birthDate, nationality, passportNumber);
    }
}

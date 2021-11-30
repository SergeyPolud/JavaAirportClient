package com.example.client;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class WrapperBooking
{
    public String flightnum;
    public String from;
    public String to;
    public BigDecimal price;
    public Timestamp arr;
    public Timestamp dep;



    public String getFlightnum()
    {
        return flightnum;
    }

    public void setFlightnum(String flightnum)
    {
        this.flightnum = flightnum;
    }

    public String getFrom()
    {
        return from;
    }

    public void setFrom(String from)
    {
        this.from = from;
    }

    public String getTo()
    {
        return to;
    }

    public void setTo(String to)
    {
        this.to = to;
    }

    public BigDecimal getPrice()
    {
        return price;
    }

    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }

    public Timestamp getArr()
    {
        return arr;
    }

    public void setArr(Timestamp arr)
    {
        this.arr = arr;
    }

    public Timestamp getDep()
    {
        return dep;
    }

    public void setDep(Timestamp dep)
    {
        this.dep = dep;
    }
}

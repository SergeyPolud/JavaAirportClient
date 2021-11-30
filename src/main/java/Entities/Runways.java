package Entities;

import java.util.Objects;


public class Runways
{
    private Integer runwayId;
    private String runwayCategory;


    public Integer getRunwayId()
    {
        return runwayId;
    }

    public void setRunwayId(Integer runwayId)
    {
        this.runwayId = runwayId;
    }


    public String getRunwayCategory()
    {
        return runwayCategory;
    }

    public void setRunwayCategory(String runwayCategory)
    {
        this.runwayCategory = runwayCategory;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Runways runways = (Runways) o;
        return Objects.equals(runwayId, runways.runwayId) && Objects.equals(runwayCategory, runways.runwayCategory);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(runwayId, runwayCategory);
    }
}

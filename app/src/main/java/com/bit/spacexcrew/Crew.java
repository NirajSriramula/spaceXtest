package com.bit.spacexcrew;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "crew_table")
public class Crew {

    public int getId() {
        return id;
    }


    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

    private String agency;
    private String imageURL;
    private String wikipedia;
    private String status;

    public Crew(String name, String agency, String imageURL, String wikipedia, String status) {
        this.name = name;
        this.agency = agency;
        this.imageURL = imageURL;
        this.wikipedia = wikipedia;
        this.status = status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getAgency() {
        return agency;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getWikipedia() {
        return wikipedia;
    }

    public String getStatus() {
        return status;
    }
}

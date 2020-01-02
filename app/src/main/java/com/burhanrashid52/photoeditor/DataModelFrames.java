package com.burhanrashid52.photoeditor;


import com.google.gson.annotations.SerializedName;

public class DataModelFrames {

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("propellant")
    private String propellant;
    @SerializedName("imageurl")
    private String FrameImageURL;
    @SerializedName("technologyexists")
    private int technologyExists;

    public DataModelFrames(int id, String name, String propellant, String imageURL, int technologyExists) {
        this.id = id;
        this.name = name;
        this.propellant = propellant;
        this.FrameImageURL = imageURL;
        this.technologyExists = technologyExists;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPropellant() {
        return propellant;
    }

    public void setPropellant(String propellant) {
        this.propellant = propellant;
    }

    public String getImageURL() {
        return FrameImageURL;
    }

    public void setImageURL(String imageURL) {
        this.FrameImageURL = imageURL;
    }

    public int getTechnologyExists() {
        return technologyExists;
    }

    public void setTechnologyExists(int technologyExists) {
        this.technologyExists = technologyExists;
    }
}
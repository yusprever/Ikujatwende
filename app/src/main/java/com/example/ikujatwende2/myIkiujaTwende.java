package com.example.ikujatwende2;

import java.io.Serializable;

public class myIkiujaTwende implements Serializable {

    private String activty, location, date, time, reporter;

    public String getActivty() {
        return activty;
    }

    public void setActivty(String activty) {
        this.activty = activty;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getReporter() {
        return reporter;
    }

    public void setReporter(String reporter) {
        this.reporter = reporter;
    }

    public myIkiujaTwende(String activty, String location, String date, String time, String reporter) {
        this.activty = activty;
        this.location = location;
        this.date = date;
        this.time = time;
        this.reporter = reporter;
    }

    @Override
    public String toString() {
        return "myIkiujaTwende{" +
                "activty='" + activty + '\'' +
                ", location='" + location + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", reporter='" + reporter + '\'' +
                '}';
    }
}

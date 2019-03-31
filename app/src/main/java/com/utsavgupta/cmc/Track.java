package com.utsavgupta.cmc;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Belal on 2/26/2017.
 */
@IgnoreExtraProperties
public class Track {
    private String id;
    private String trackName;
    private String status;
    private  int i;

    public Track() {

    }

    public Track(String id, String trackName, String status, int i) {
        this.trackName = trackName;
        this.status = status;
        this.id = id;
        this.i=i;
    }

    public String getTrackName() {
        return trackName;
    }

    public String getRating() {
        return status;
    }

    public String getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public int getI() {
        return i;
    }
}

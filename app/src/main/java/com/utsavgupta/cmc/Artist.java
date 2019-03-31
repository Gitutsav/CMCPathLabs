package com.utsavgupta.cmc;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Belal on 2/26/2017.
 */
@IgnoreExtraProperties
public class Artist {
    private String artistId;
    private String artistName;
    private String amob, agend, aadd, refx, testx, agex, datex;

    public Artist() {
        //this constructor is required
    }

    public Artist(String artistId, String artistName, String mob, String gend, String add, String refx, String testx, String agex, String datewt) {
        this.artistId = artistId;
        this.artistName = artistName;
        this.amob = mob;
        this.agend = gend;
        this.agex = agex;
        this.aadd = add;
        this.refx = refx;
        this.testx = testx;
        this.datex = datewt;
    }

    public String getArtistId() {
        return artistId;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getAmob() {        return amob;
    }

    public String getAgend() {        return agend;
    }

    public String getAadd() {        return aadd;
    }

    public String getRefx() {        return refx;
    }

    public String getTestx() {        return testx;
    }

    public String getAgex() {        return agex;
    }

    public String getDatex() {        return datex;
    }
}

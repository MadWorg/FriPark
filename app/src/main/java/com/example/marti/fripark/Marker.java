package com.example.marti.fripark;


import android.arch.persistence.room.Entity;

import java.util.Date;


//for database - unfinished
@Entity(tableName="mark_table")
public class Marker {

    private String id;
    private Date exp_d;
    private Marker m;

    public Marker(String id, Date exp_d, Marker m) {
        this.id = id;
        this.exp_d = exp_d;
        this.m = m;

    }

    public Marker getM() {
        return m;
    }

    public String getId() {
        return id;
    }

    public Date getD() {
        return exp_d;
    }
}

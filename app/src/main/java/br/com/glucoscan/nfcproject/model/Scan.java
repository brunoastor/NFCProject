package br.com.glucoscan.nfcproject.model;

import java.util.Date;

public class Scan {
    private int i;
    private String TAG;
    private Date date;


    public String getTAG() {
        return TAG;
    }

    public Date getDate() {
        return date;
    }

    public void setTAG(String TAG) {
        this.TAG = TAG;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

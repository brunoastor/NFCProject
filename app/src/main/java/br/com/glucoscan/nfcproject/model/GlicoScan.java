package br.com.glucoscan.nfcproject.model;

import java.util.Date;

public class GlicoScan {
    private int index;
    private String tag;
    private Date date;
    private NanoDate nanoDate;

    public GlicoScan(int index, String tag, Date date, NanoDate nanoDate) {
        this.index = index;
        this.tag = tag;
        this.date = date;
        this.nanoDate = new NanoDate();
    }
}

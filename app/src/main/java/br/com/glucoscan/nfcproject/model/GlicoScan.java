package br.com.glucoscan.nfcproject.model;

public class GlicoScan {

    private String tag;
    private String date;
    private long nanoDate;

    public GlicoScan(String tag, String date, long nanoDate) {
        this.tag = tag;
        this.date = date;
        this.nanoDate = nanoDate;
    }

    public String getGlicoScan(){
        return this.tag+ "\n" + this.date +"\n" + this.nanoDate;
    }
}
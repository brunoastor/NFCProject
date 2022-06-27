package br.com.glucoscan.nfcproject.model;

import static br.com.glucoscan.nfcproject.model.DateNow.getDate;
import static br.com.glucoscan.nfcproject.model.DateNow.getNanoDate;

import java.util.ArrayList;

public class GlicoScan {

    private String tag;
    private String date;
    private long nanoDate;

    private static int index = 0;
    private static ArrayList<GlicoScan> glicoScans = new ArrayList<GlicoScan>();

    public GlicoScan(String tag, String date, long nanoDate) {
        this.tag = tag;
        this.date = date;
        this.nanoDate = nanoDate;
    }

    public static void insertScan(String tag) {
        glicoScans.add(index++, new GlicoScan(tag, getDate(), getNanoDate()));
    }

    public static int getScanSize() {
        return glicoScans.size() - 1;
    }

    public static String getScanIndex(int i) {
        return glicoScans.get(i).getScan();
    }

    public String getScan() {
        return glicoScans.indexOf(this) + "\n" + this.tag + "\n" + this.date + "\n" + this.nanoDate;
    }


}
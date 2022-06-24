package br.com.glucoscan.nfcproject.storage;

import static br.com.glucoscan.nfcproject.model.DateNow.getDate;
import static br.com.glucoscan.nfcproject.model.DateNow.getNanoDate;

import java.util.ArrayList;

import br.com.glucoscan.nfcproject.model.GlicoScan;

public class Storage {
    static int i = 0;

    private static ArrayList<GlicoScan> glicoScans = new ArrayList<GlicoScan>();

    public static String insertScan(String messages) {

        glicoScans.add( i, new GlicoScan(messages, getDate(), getNanoDate()));
        i++;
        return messages;
    }

    public static String getScans(int i){
        return glicoScans.get(i).getGlicoScan();
    }

    public static int getScanSize(){
        return glicoScans.size();
    }
}

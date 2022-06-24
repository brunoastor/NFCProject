package br.com.glucoscan.nfcproject.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateNow {

    public final static String getDate(){
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy' 'HH:mm:ss");
        return df.format(new Date());
    }

    public final static long getNanoDate(){
        long nanoTime = System.nanoTime();
        return nanoTime;
    }
}

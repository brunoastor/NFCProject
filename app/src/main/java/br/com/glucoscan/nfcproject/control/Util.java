package br.com.glucoscan.nfcproject.control;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

    public final static String getNow(){
        //TimeZone tz = TimeZone.getTimeZone("UTC-3");
        //df.setTimeZone(tz);
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy' 'HH:mm:ss");
        return df.format(new Date());
    }
}

package br.com.glucoscan.nfcproject.control;

import android.content.Context;
import android.nfc.NfcAdapter;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

    public static int INDEX = 0;

    public final static String getDate(){

        //TODO

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy' 'HH:mm:ss");
        return df.format(new Date());
    }

    public static void checkNFC(NfcAdapter nfcAdapter, Context context) {
        if (nfcAdapter != null && nfcAdapter.isEnabled()) {
            Toast.makeText(context, "NFC AVAILABLE", Toast.LENGTH_LONG).show();
        } else {
            if (nfcAdapter == null) {
                Toast.makeText(context, "NFC NOT SUPPORTED", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(context, "NFC DISABLED", Toast.LENGTH_LONG).show();
            }
        }
    }
}

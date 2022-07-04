package br.com.glucoscan.nfcproject.control;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.os.Parcelable;
import android.widget.Toast;

public class AdapterUtil {

    public static NfcAdapter nfcAdapter;
    public static PendingIntent pendingIntent = null;

    public static void setPendingIntent(Context context) {
        nfcAdapter = NfcAdapter.getDefaultAdapter(context);
        pendingIntent = PendingIntent.getActivity(
                context, 0, new Intent(context, context.getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
    }

    public static String readTag(Intent intent) {
        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(intent.getAction())) {
            Parcelable[] rawMessages = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
            if (rawMessages != null) {
                NdefMessage[] tag = new NdefMessage[rawMessages.length];
                for (int i = 0; i < rawMessages.length; i++) {
                    tag[i] = (NdefMessage) rawMessages[i];
                    return new String(tag[i].getRecords()[i].getPayload());
                }
            }
        }
        return "SCAN_ERROR";
    }

    public static void checkDeviceNFC(Context context) {
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

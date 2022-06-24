package br.com.glucoscan.nfcproject.view;

import static br.com.glucoscan.nfcproject.control.Util.INDEX;
import static br.com.glucoscan.nfcproject.control.Util.getDate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;


import br.com.glucoscan.nfcproject.R;
import br.com.glucoscan.nfcproject.control.Util;

public class MainActivity extends AppCompatActivity {

    static private NfcAdapter nfcAdapter;
    private PendingIntent pendingIntent = null;
    private TextView textView;
    private ConstraintLayout layout;
    private ArrayList<String> misc = new ArrayList<String>();
    private float touchDownX, touchUpX;
    static private int currentTagIndex = -1;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        InitializeComponents();
        Util.checkNFC(nfcAdapter, this);


        layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final float swipeThreshold = 150;

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        touchDownX = event.getX();
                        break;

                    case MotionEvent.ACTION_UP:
                        touchUpX = event.getX();
                        final float deltaX = touchUpX - touchDownX;

                        if (deltaX > swipeThreshold) {
                            showPreviousTag();
                        } else if (deltaX < -swipeThreshold) {
                            showNextTag();
                        }

                        break;
                }

                return false;
            }
        });
    }

    private void showNextTag() {
        if (++currentTagIndex >= misc.size()) currentTagIndex = 0;
        textView.setText(misc.get(currentTagIndex));
    }

    private void showPreviousTag() {
        if (--currentTagIndex < 0) currentTagIndex = misc.size() - 1;
        textView.setText(misc.get(currentTagIndex));
    }

    @Override
    public void onPause() {
        super.onPause();
        nfcAdapter.disableForegroundDispatch(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        assert nfcAdapter != null;
        nfcAdapter.enableForegroundDispatch(this, pendingIntent, null, null);
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(intent.getAction())) {
            Parcelable[] rawMessages = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
            if (rawMessages != null) {
                NdefMessage[] messages = new NdefMessage[rawMessages.length];
                for (int i = 0; i < rawMessages.length; i++) {
                    messages[i] = (NdefMessage) rawMessages[i];
                    insertTag(new String(messages[i].getRecords()[i].getPayload()));
                }
            }
        }
    }

    private String insertTag(String messages) {

        misc.add(INDEX, INDEX + "\n" + messages + "\n" + getDate());
        textView.setText(misc.get(INDEX));
        currentTagIndex = INDEX;
        INDEX++;
        return messages;
    }

    private void InitializeComponents() {

        layout = findViewById(R.id.layout);
        textView = findViewById(R.id.textView);

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        pendingIntent = PendingIntent.getActivity(
                this, 0, new Intent(this, this.getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);

    }
}
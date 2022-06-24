package br.com.glucoscan.nfcproject.view;

import static br.com.glucoscan.nfcproject.control.AdapterUtil.checkNFC;
import static br.com.glucoscan.nfcproject.control.AdapterUtil.nfcAdapter;
import static br.com.glucoscan.nfcproject.control.AdapterUtil.pendingIntent;
import static br.com.glucoscan.nfcproject.control.AdapterUtil.setPendingIntent;
import static br.com.glucoscan.nfcproject.control.AdapterUtil.readTag;
import static br.com.glucoscan.nfcproject.storage.Storage.getScanSize;
import static br.com.glucoscan.nfcproject.storage.Storage.getScans;
import static br.com.glucoscan.nfcproject.storage.Storage.insertScan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import br.com.glucoscan.nfcproject.R;

public class MainActivity extends AppCompatActivity {


    private TextView textView;
    private ConstraintLayout layout;

    private float touchDownX, touchUpX;
    public static int currentTagIndex = -1;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        setPendingIntent(this);
        InitializeComponents();
        checkNFC(this);


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
        if (++currentTagIndex >= getScanSize()) currentTagIndex = 0;
        //TODO
        //textView.setText(getScans(currentTagIndex));
        Log.i("TAG", "NEXT TAG");
    }

    private void showPreviousTag() {
        if (--currentTagIndex < 0) currentTagIndex = getScanSize() - 1;
        //TODO
        //textView.setText(getScans(currentTagIndex));
        Log.i("TAG", "BACK TAG");
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
        insertScan(readTag(intent));
        textView.setText(getScans(getScanSize()-1));
    }

    private void InitializeComponents() {

        layout = findViewById(R.id.layout);
        textView = findViewById(R.id.textView);

    }
}
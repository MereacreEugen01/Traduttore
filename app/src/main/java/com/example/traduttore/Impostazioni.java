package com.example.traduttore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import java.net.MalformedURLException;

public class Impostazioni extends AppCompatActivity {
    private long mLastClickTime = 0;
    private Spinner sceltaLinguaPartenza;
    private Spinner sceltaLinguaArrivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_impostazioni);
        this.sceltaLinguaPartenza = (Spinner) findViewById((R.id.linguaPartenza));
        this.sceltaLinguaArrivo = (Spinner) findViewById((R.id.linguaArrivo));

        SharedPreferences s = getSharedPreferences("settings", MODE_PRIVATE);
        int a = s.getInt("source", 0);
        int b = s.getInt("target", 1);
        String c = s.getString("key", "d17b9f49-8ee1-06a3-161a-8cb2a513ae10:fx");
        ((Spinner)findViewById(R.id.linguaPartenza)).setSelection(a);
        ((Spinner)findViewById(R.id.linguaArrivo)).setSelection(b);
        ((EditText)findViewById(R.id.key)).setText(c);
        //((EditText)findViewById(R.id.key)).setHint(c);



    }
    public void onClickSave(View arg0) {
        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();
        int source = ((Spinner)findViewById(R.id.linguaPartenza)).getSelectedItemPosition();
        int target = ((Spinner)findViewById(R.id.linguaArrivo)).getSelectedItemPosition();
        String key = ((EditText)findViewById(R.id.key)).getText().toString();
        SharedPreferences.Editor e = getSharedPreferences("settings", MODE_PRIVATE).edit();
        e.putInt("source", source);
        e.putInt("target", target);
        e.putString("key", key);
        e.commit();
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
    public void onClickScambia(View arg0)
    {
        int sceltaUno = sceltaLinguaPartenza.getSelectedItemPosition();
        int sceltaDue = sceltaLinguaArrivo.getSelectedItemPosition();
        sceltaLinguaPartenza.setSelection(sceltaDue+1);
        sceltaLinguaArrivo.setSelection(sceltaUno-1);
    }


}
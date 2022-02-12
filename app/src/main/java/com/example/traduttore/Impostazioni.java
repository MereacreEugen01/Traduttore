package com.example.traduttore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import java.net.MalformedURLException;

public class Impostazioni extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_impostazioni);

        SharedPreferences s = getSharedPreferences("settings", MODE_PRIVATE);
        int a = s.getInt("source", 0);
        int b = s.getInt("target", 1);
        String c = s.getString("key", null);

        ((Spinner)findViewById(R.id.linguaPartenza)).setSelection(a);
        ((Spinner)findViewById(R.id.linguaArrivo)).setSelection(b);
        ((EditText)findViewById(R.id.key)).setText(c);



    }
    public void onClickSave(View arg0) {
        int source = ((Spinner)findViewById(R.id.linguaPartenza)).getSelectedItemPosition();
        int target = ((Spinner)findViewById(R.id.linguaArrivo)).getSelectedItemPosition();
        String key = ((EditText)findViewById(R.id.key)).getText().toString();
        SharedPreferences.Editor e = getSharedPreferences("settings", MODE_PRIVATE).edit();
        e.putInt("source", source);
        e.putInt("target", target);
        e.putString("key", key);
        e.commit();
    }
}
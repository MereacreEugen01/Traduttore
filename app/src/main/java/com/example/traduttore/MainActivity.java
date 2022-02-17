package com.example.traduttore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Spinner;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.io.UnsupportedEncodingException;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity
{

    private TextInputEditText testoDaTradurre;
    private TextInputEditText testoTradotto;
    private Spinner sceltaLinguaPartenza;
    private Spinner sceltaLinguaArrivo;
    private ListaLingue convertitore = new ListaLingue();
    private long mLastClickTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.testoDaTradurre = findViewById(R.id.testoDaTradurre);
        this.testoTradotto =  findViewById(R.id.testoTradotto);
        this.sceltaLinguaPartenza = findViewById((R.id.linguaPartenza));
        this.sceltaLinguaArrivo = findViewById((R.id.linguaArrivo));

        SharedPreferences s = getSharedPreferences("settings", MODE_PRIVATE);
        int a = s.getInt("source", 0);
        int b = s.getInt("target", 1);

        ((Spinner)findViewById(R.id.linguaPartenza)).setSelection(a);
        ((Spinner)findViewById(R.id.linguaArrivo)).setSelection(b);


    }
    public void onClickTraduci(View arg0) {

        SharedPreferences s = getSharedPreferences("settings", MODE_PRIVATE);
        String c = s.getString("key", "d17b9f49-8ee1-06a3-161a-8cb2a513ae10:fx");

        String prova = testoDaTradurre.getText().toString();

        String encodedQuery = encodeValue(prova);
        System.out.println(encodedQuery);

        inviaRichiesta(encodeValue(prova), convertitore.getLinguaScelta(sceltaLinguaArrivo.getSelectedItem().toString()), convertitore.getLinguaScelta(sceltaLinguaPartenza.getSelectedItem().toString()), c);

    }

    private void inviaRichiesta(String testoDaTradurre, String target_lang, String source_lang, String auth_key)
    {
        String e = "&";
        String testo = "text="+testoDaTradurre;
        String target = "target_lang="+target_lang;
        String sorgente = "";
        String chiave = "auth_key="+auth_key;
        if(source_lang == null) { }
        else
        {
            sorgente = "source_lang="+source_lang;
        }
        String url = "https://api-free.deepl.com/v2/translate?"+chiave+e+testo+e+target+e+sorgente;
        Log.d("richista", url);
        //definisco la coda delle richieste
        RequestQueue queue = Volley.newRequestQueue(this);
        System.out.println(url);

        //definisco la richiesta

        StringRequest richiesta = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        try {
                            JSONObject risposaJSON = new JSONObject(response);
                            JSONArray a = risposaJSON.getJSONArray("translations");
                            JSONObject testoJSON = a.getJSONObject(0);
                            String testoTradorro = (decodeValue(testoJSON.getString("text")));
                            testoTradotto.setText(testoTradorro);

                        } catch (JSONException jsonException) {
                            jsonException.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        testoTradotto.setText("Si è verificato un errore, si prega di riprovare!");
                        Log.e("API",error.getLocalizedMessage());
                    }
                }
        );
        queue.add(richiesta);
    }

    public void onClickImposta(View arg0)
    {
        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();
        Intent i = new Intent(this, Impostazioni.class);
        startActivity(i);
    }
    public void onClickScambia(View arg0)
    {
        int sceltaUno = sceltaLinguaPartenza.getSelectedItemPosition();
        int sceltaDue = sceltaLinguaArrivo.getSelectedItemPosition();
        sceltaLinguaPartenza.setSelection(sceltaDue+1);
        sceltaLinguaArrivo.setSelection(sceltaUno-1);
    }

    private static String encodeValue(String value)
    {
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex.getCause());
        }
    }
    public static String decodeValue(String value)
    {
        try {
            return new String(value.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            {
                throw new RuntimeException(ex.getCause());
            }
        }
    }
}
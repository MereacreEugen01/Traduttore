package com.example.traduttore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.SyncStateContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.net.URLDecoder;
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

import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity
{

    private TextInputEditText testoDaTradurre;
    private TextInputEditText testoTradotto;
    private Spinner sceltaLinguaPartenza;
    private Spinner sceltaLinguaArrivo;
    private ListaLingue convertitore = new ListaLingue();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.testoDaTradurre = (TextInputEditText) findViewById(R.id.testoDaTradurre);
        this.testoTradotto = (TextInputEditText) findViewById(R.id.testoTradotto);
        this.sceltaLinguaPartenza = (Spinner) findViewById((R.id.linguaPartenza));
        this.sceltaLinguaArrivo = (Spinner) findViewById((R.id.linguaArrivo));






    }
    public void onClickTraduci(View arg0) throws MalformedURLException {
        //testoTradotto.setText(testoDaTradurre.getText());
        //testoDaTradurre.setHint("Testo Da tradurre:");
        // ;

        Log.d("decode", encodeValue("Ciao come stai"));
        Log.d("config","Lingua inserita: "+ sceltaLinguaPartenza.getSelectedItem().toString() + ": "+
                convertitore.getLinguaScelta(sceltaLinguaPartenza.getSelectedItem().toString()));
        Log.d("config", "Lingua desiderata:" + sceltaLinguaArrivo.getSelectedItem().toString() + ": "+
                convertitore.getLinguaScelta(sceltaLinguaArrivo.getSelectedItem().toString()));


        String prova = testoDaTradurre.getText().toString();
        /*
        URL traduttore = new URL(prova);
        System.out.println(traduttore.toString());
        */
        String encodedQuery = encodeValue(prova);
        System.out.println(encodedQuery);

        inviaRichiesta(encodeValue(prova), convertitore.getLinguaScelta(sceltaLinguaArrivo.getSelectedItem().toString()), convertitore.getLinguaScelta(sceltaLinguaPartenza.getSelectedItem().toString()));

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

    private void inviaRichiesta(String testoDaTradurre, String target_lang, String source_lang)
    {
        String e = "&";
        String testo = "text="+testoDaTradurre;
        String target = "target_lang="+target_lang;
        String sorgente = "";
        if(source_lang == null) { }
        else
        {
            sorgente = "source_lang="+source_lang;
        }
        String url = "https://api-free.deepl.com/v2/translate?auth_key=d17b9f49-8ee1-06a3-161a-8cb2a513ae10:fx"+e+testo+e+target+e+sorgente;

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
                        Log.d("API", response);
                        String traduzione = response.substring(response.indexOf("\"text\":\"")+8, response.indexOf("\"}]}"));
                        //String t = null;
                        testoTradotto.setText(decodeValue(traduzione));
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        //System.out.println("c'è stato un errore");
                        Log.e("API",error.getLocalizedMessage());
                    }
                }
        );
        queue.add(richiesta);
    }
}
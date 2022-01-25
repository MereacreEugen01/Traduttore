package com.example.traduttore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
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
        System.out.println("Lingua inserita:");
        System.out.println(sceltaLinguaPartenza.getSelectedItem().toString());
        System.out.println(convertitore.getLinguaScelta(sceltaLinguaPartenza.getSelectedItem().toString()));
        System.out.println("Lingua desiderata:");
        System.out.println(sceltaLinguaArrivo.getSelectedItem().toString());
        System.out.println(convertitore.getLinguaScelta(sceltaLinguaArrivo.getSelectedItem().toString()));

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
                        // Display the first 500 characters of the response string.
                       // textView.setText("Response is: "+ response.substring(0,500));
                        Log.d("API", response);
                        String traduzione = response.substring(response.indexOf("\"text\":\"")+8, response.indexOf("\"}]}"));
                        testoTradotto.setText(traduzione);

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
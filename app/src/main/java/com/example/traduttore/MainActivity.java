package com.example.traduttore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView testoDaTradurre;
    private TextView testoTradotto;
    //private Button traduci = (Button) this.findViewById(R.id.bottoneTraduzione);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.testoDaTradurre = (TextView) findViewById(R.id.testoDaTradurre);
        this.testoTradotto = (TextView) findViewById(R.id.testoTradotto);



    }
    public void onClickTraduci(View arg0)
    {
        testoTradotto.setText(testoDaTradurre.getText());
    }
}
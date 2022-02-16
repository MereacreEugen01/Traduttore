package com.example.traduttore;

import java.util.HashMap;
import java.util.Set;

public class ListaLingue
{

    private HashMap<String, String> listaLinguePrima ;

    public ListaLingue()
    {
        listaLinguePrima = new HashMap<String, String>();

        listaLinguePrima.put("Rileva Lingue", " ");
        listaLinguePrima.put("Inglese", "EN");
        listaLinguePrima.put("Italiano", "IT");
        listaLinguePrima.put("Francese", "FR");
        listaLinguePrima.put("Spagnolo", "ES");
        listaLinguePrima.put("Tedesco", "DE");
        listaLinguePrima.put("Cinese", "ZH");
        listaLinguePrima.put("Giapponese", "JA");
        listaLinguePrima.put("Bulgaro", "BG");
        listaLinguePrima.put("Ceco", "CS");
        listaLinguePrima.put("Danese", "DA");
        listaLinguePrima.put("Greco", "EL");
        listaLinguePrima.put("Estone", "ET");
        listaLinguePrima.put("Fillandese", "FI");
        listaLinguePrima.put("Ungerese", "HU");
        listaLinguePrima.put("Lituano", "LT");
        listaLinguePrima.put("Lettone", "LV");
        listaLinguePrima.put("Olandese", "NL");
        listaLinguePrima.put("Polacco", "PL");
        listaLinguePrima.put("Portoghese", "PT");
        listaLinguePrima.put("Rumeno", "RO");
        listaLinguePrima.put("Russo", "RU");
        listaLinguePrima.put("Slovacco", "SK");
        listaLinguePrima.put("Sloveno", "SL");
        listaLinguePrima.put("Svedese", "SV");
    }

    public String getLinguaScelta(String scelta)
    {
        return listaLinguePrima.get(scelta);
    }
}

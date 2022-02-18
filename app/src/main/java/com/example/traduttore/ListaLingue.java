package com.example.traduttore;

import java.util.HashMap;
import java.util.Set;

public class ListaLingue
{
    private HashMap<String, String> listaLingue ;

    public ListaLingue()
    {
        listaLingue = new HashMap<String, String>();

        listaLingue.put("Rileva Lingue", " ");
        listaLingue.put("Inglese", "EN");
        listaLingue.put("Italiano", "IT");
        listaLingue.put("Francese", "FR");
        listaLingue.put("Spagnolo", "ES");
        listaLingue.put("Tedesco", "DE");
        listaLingue.put("Cinese", "ZH");
        listaLingue.put("Giapponese", "JA");
        listaLingue.put("Bulgaro", "BG");
        listaLingue.put("Ceco", "CS");
        listaLingue.put("Danese", "DA");
        listaLingue.put("Greco", "EL");
        listaLingue.put("Estone", "ET");
        listaLingue.put("Fillandese", "FI");
        listaLingue.put("Ungerese", "HU");
        listaLingue.put("Lituano", "LT");
        listaLingue.put("Lettone", "LV");
        listaLingue.put("Olandese", "NL");
        listaLingue.put("Polacco", "PL");
        listaLingue.put("Portoghese", "PT");
        listaLingue.put("Rumeno", "RO");
        listaLingue.put("Russo", "RU");
        listaLingue.put("Slovacco", "SK");
        listaLingue.put("Sloveno", "SL");
        listaLingue.put("Svedese", "SV");
    }

    public String getLinguaScelta(String scelta)
    {
        return listaLingue.get(scelta);
    }
}

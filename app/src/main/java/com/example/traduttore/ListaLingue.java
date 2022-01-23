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

    }

    public String getLinguaScelta(String scelta)
    {
        return listaLinguePrima.get(scelta);
    }



}

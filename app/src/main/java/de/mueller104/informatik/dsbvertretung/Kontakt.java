package de.mueller104.informatik.dsbvertretung;

import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.HandlesEventDispatching;
import com.google.appinventor.components.runtime.Label;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

public class Kontakt extends Form implements HandlesEventDispatching {
    private Label Csv;
    private Reader br;
    private String k;

    protected void $define(){
        Csv = new Label(this);
        try{
        InputStream raw = this.getAssets().open("lehrer.csv");
        k = convertStreamToString(raw);
        }
        catch(IOException e){
            System.err.println("Lehrer.csv konnte nicht gelesen werden");
            e.printStackTrace();
        }
        Csv.Text(k);

    }

    public static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}

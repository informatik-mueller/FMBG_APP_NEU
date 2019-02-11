package de.mueller104.informatik.dsbvertretung;

import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.HandlesEventDispatching;
import com.google.appinventor.components.runtime.Label;
import com.google.appinventor.components.runtime.ListPicker;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

//TODO: Download aus dem Internet

public class LulListe extends Form implements HandlesEventDispatching {
    private ListPicker List;
    private Label Csv;
    private Reader br;
    private String k;

    protected void $define(){
        Csv = new Label(this);
        List = new ListPicker(this);
        try{
        InputStream raw = this.getAssets().open("lehrer.csv");
        k = convertStreamToString(raw);
        }
        catch(IOException e){
            System.err.println("lehrer.csv konnte nicht gelesen werden");
            e.printStackTrace();
        }
        //Csv.Text(k);
        List.ElementsFromString(k);

    }

    public static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}

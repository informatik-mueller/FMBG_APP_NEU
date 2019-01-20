package de.mueller104.informatik.dsbvertretung;

import android.content.Intent;
import android.os.StrictMode;

import com.google.appinventor.components.runtime.Button;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.EventDispatcher;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.HandlesEventDispatching;
import com.google.appinventor.components.runtime.Label;

import java.util.ArrayList;

import de.sematre.api.dsbmobile.*;

//TODO: schöner designen

public class Datumswahl extends Form implements HandlesEventDispatching {
    private Button Heute;
    private Button Gestern;
    private ArrayList<TimeTable> timeTables;
    private Label Space2;
    private Label Überschrift;
    private Label Hinweis;
    private final int CENTER_HORIZONTAL = 3;


    protected void $define(){
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        this.AlignHorizontal(CENTER_HORIZONTAL);
        this.BackgroundColor(0xFFF89432);
        Überschrift = new Label(this);
        Überschrift.Text("Vertretungsplan");
        Überschrift.HeightPercent(40);
        Überschrift.FontSize(30.0f);
        Überschrift.FontBold(true);
        Überschrift.TextColor(0xFF444444);
        Heute = new Button(this);
        Gestern = new Button(this);
        Space2 = new Label(this);
        Space2.HeightPercent(60);
        Hinweis = new Label(this);
        Hinweis.TextColor(Label.COLOR_RED);

        Hinweis.Text("");

        DSBMobile dsbMobile = new DSBMobile("168442", "schule");
        timeTables = dsbMobile.getTimeTables();
        String dateinameGestern = timeTables.get(0).getTitle();
        String dateinameHeute = timeTables.get(1).getTitle();

        String datumGestern = dateinameGestern.substring(0, dateinameGestern.length()-4);
        String datumHeute = dateinameHeute.substring(0, dateinameHeute.length()-4);

        Gestern.Text(datumGestern);
        Heute.Text(datumHeute);
        EventDispatcher.registerEventForDelegation(this, "qc", "Click");
    }

    @Override
    public boolean dispatchEvent(Component component, String componentName, String eventName, Object[] args) {
        if(eventName.equals("Click")){
            TimeTable timeTable;

            if(component.equals(Gestern))
                timeTable = timeTables.get(0);

            else if(component.equals(Heute))
                timeTable = timeTables.get(1);
            else return true;

            String dlLink = timeTable.getUrl();
            String titel = "Vertretungsplan für " + timeTable.getTitle().substring(0, timeTable.getTitle().length()-4);
            Intent intent = new Intent(this, VertretungViewer.class);
            intent.putExtra("url", dlLink);
            intent.putExtra("titel", titel);
            startActivity(intent);
            return true;
        }

        return false;
    }
}

package de.mueller104.informatik.dsbvertretung;

import android.content.Intent;
import java.text.SimpleDateFormat;
import android.os.StrictMode;

import com.google.appinventor.components.runtime.Button;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.EventDispatcher;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.HandlesEventDispatching;
import com.google.appinventor.components.runtime.Label;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import de.sematre.api.dsbmobile.*;


public class Datumswahl extends Form implements HandlesEventDispatching {
    private Button Heute;
    private Button Gestern;
    private ArrayList<TimeTable> timeTables;
    private Label Space2;
    private Label Überschrift;
    private Label ÜberschriftSpace;
    private Label KeineLabel;
    private final int CENTER_HORIZONTAL = 3;
    private String DateinameGestern;
    private String DateinameHeute;
    private String DatumGestern;
    private String DatumHeute;
    private Label ButtonSpace;
    private static float FONT_SIZE = 25.0f;


    protected void $define(){
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        this.AlignHorizontal(CENTER_HORIZONTAL);
        this.ScreenOrientation("portrait");
        this.BackgroundColor(0xFFF89432);
        ÜberschriftSpace = new Label(this);
        ÜberschriftSpace.HeightPercent(2);
        Überschrift = new Label(this);
        Überschrift.Text("Vertretungsplan");
        Überschrift.HeightPercent(35);
        Überschrift.FontSize(30.0f);
        Überschrift.FontBold(true);
        Überschrift.TextColor(0xFF444444);
        Heute = new Button(this);
        Heute.FontSize(FONT_SIZE);
        ButtonSpace = new Label(this);
        ButtonSpace.HeightPercent(5);
        Gestern = new Button(this);
        Gestern.FontSize(FONT_SIZE);
        Space2 = new Label(this);
        Space2.HeightPercent(60);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, - 1);
        String DatumEchtGestern = new SimpleDateFormat("dd.MM.yyyy").format(cal.getTime());
        String DatumEchtHeute = new SimpleDateFormat("dd.MM.yyyy").format(new Date());

        try{
        DSBMobile dsbMobile = new DSBMobile("168442", "schule");
        timeTables = dsbMobile.getTimeTables();
        DateinameGestern = timeTables.get(0).getTitle();
        DateinameHeute = timeTables.get(1).getTitle();
        }
        catch (Exception e){
            System.err.println("Fehler beim Laden der Daten");
            e.printStackTrace();
        }

        try{
            if(timeTables.size() > 0){
                 DatumGestern = DateinameGestern.substring(0, DateinameGestern.length()-4);
                if(DatumGestern.equals(DatumEchtGestern))
                    DatumGestern = "Gestern: " + DatumHeute;
                if(DatumGestern.equals(DatumEchtHeute))
                    DatumGestern = "Heute: " + DatumHeute;
                Gestern.Text(DatumGestern);
            }
            else{
                Gestern.Visible(false);
                KeineLabel = new Label(this);
                KeineLabel.Text("Es gibt zurzeit keine Vertretungspläne");
            }

            if(timeTables.size() > 1){
                DatumHeute = DateinameHeute.substring(0, DateinameHeute.length()-4);
                if(DatumHeute.equals(DatumEchtGestern))
                    DatumHeute = "Gestern: " + DatumHeute;
                if(DatumHeute.equals(DatumEchtHeute))
                    DatumHeute = "Heute: " + DatumHeute;
                Heute.Text(DatumHeute);
            }
            else{
                Heute.Visible(false);
            }
        }
        catch (Exception e){
            System.err.println("Fehler beim Setzen der Daten");
            e.printStackTrace();
        }

        EventDispatcher.registerEventForDelegation(this, "qc", "Click");
    }

    @Override
    public boolean dispatchEvent(Component component, String componentName, String eventName, Object[] args) {
        if(eventName.equals("Click")){
            TimeTable timeTable;

            if(component.equals(Gestern)){
                timeTable = timeTables.get(0);
            }

            else if(component.equals(Heute)){
                timeTable = timeTables.get(1);
            }
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

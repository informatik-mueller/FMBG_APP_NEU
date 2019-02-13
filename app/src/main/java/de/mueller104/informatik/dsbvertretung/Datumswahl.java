package de.mueller104.informatik.dsbvertretung;

import android.content.Intent;
import java.text.SimpleDateFormat;

import android.os.StrictMode;

import com.google.appinventor.components.runtime.Button;
import com.google.appinventor.components.runtime.Clock;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.EventDispatcher;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.HandlesEventDispatching;
import com.google.appinventor.components.runtime.Label;

import org.apache.commons.lang3.StringUtils;

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
    private static final float FONT_SIZE = 25.0f;
    private  String DatumEchtGestern;
    private  String DatumEchtHeute;
    private  String DatumEchtMorgen;
    private String DatumEchtÜbermorgen;
    private String Jahr;
    private Clock Autostart;


    protected void $define(){
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        this.AlignHorizontal(CENTER_HORIZONTAL);
        this.ScreenOrientation("portrait");
        this.BackgroundColor(0xFFF89432);

        Autostart = new Clock(this);
        Autostart.TimerInterval(40);
        Autostart.TimerEnabled(true);

        ÜberschriftSpace = new Label(this);
        ÜberschriftSpace.HeightPercent(2);
        Überschrift = new Label(this);
        Überschrift.Text("Vertretungsplan");
        Überschrift.HeightPercent(35);
        Überschrift.FontSize(30.0f);
        Überschrift.FontBold(true);
        KeineLabel = new Label(this);
        KeineLabel.FontSize(18.0f);
        KeineLabel.Visible(false);
        Überschrift.TextColor(0xFF444444);
        Heute = new Button(this);
        Heute.FontSize(FONT_SIZE);
        ButtonSpace = new Label(this);
        ButtonSpace.HeightPercent(5);
        Gestern = new Button(this);
        Gestern.FontSize(FONT_SIZE);
        Space2 = new Label(this);
        Space2.HeightPercent(60);

        Jahr = new SimpleDateFormat("yyyy").format(new Date());

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, - 1);

        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        DatumEchtGestern = format.format(cal.getTime());
        DatumEchtHeute = format.format(new Date());
        cal.add(Calendar.DATE, 2);
        DatumEchtMorgen = format.format(cal.getTime());
        cal.add(Calendar.DATE, 1);
        DatumEchtÜbermorgen = format.format(cal.getTime());
        Überschrift.Text(DatumEchtÜbermorgen);
        EventDispatcher.registerEventForDelegation(this, "qc", "Click");
        EventDispatcher.registerEventForDelegation(this, "id", "Timer");
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

        if(component.equals(Autostart) && eventName.equals("Timer")){
            try{
                DSBMobile dsbMobile = new DSBMobile("168442", "schule");
                timeTables = dsbMobile.getTimeTables();
                DateinameGestern = timeTables.get(0).getTitle();
                DateinameHeute = timeTables.get(1).getTitle();

                try{
                    if(timeTables.size() > 0){
                        DatumGestern = TemporaleBestimmung(DateinameGestern.substring(0, DateinameGestern.length()-4));
                        Gestern.Text(DatumGestern);
                    }
                    else {
                        Gestern.Visible(false);
                        KeineLabel.Visible(true);
                        KeineLabel.Text("Es gibt zurzeit keine Vertretungspläne");
                    }

                    if(timeTables.size() > 1){
                        DatumHeute = TemporaleBestimmung(DateinameHeute.substring(0, DateinameHeute.length()-4));
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
            }
            catch (Exception e){
                System.err.println("Fehler beim Laden der Daten");
                e.printStackTrace();
            }
            Autostart.TimerEnabled(false);
        }

        return false;
    }

    public String TemporaleBestimmung(String in){
      char[] inArr = in.toCharArray();

        char[] ErrorArr = in.toCharArray();
        ErrorArr[3] = ErrorArr[3] == '0' ? '1' : '0';
        String ErrorStr = new String(ErrorArr);

        if(DatumEchtGestern.equals(ErrorStr)){
            DatumGestern = "Gestern: " + ErrorStr;
        }

        if(DatumEchtHeute.equals(ErrorStr)){
            DatumGestern = "Heute: " + ErrorStr;
        }

        String res = in;
        String[] divided = res.split("\\."); //Punkt muss excaped werden

        if(!(divided[1].equals("12") && divided[2].equals(Jahr))){
            divided[2] = Jahr;
            res = StringUtils.join(divided, ".");
        }

        if(res.equals(DatumEchtGestern))
            return "Gestern: " + res;
        if(res.equals(DatumEchtHeute))
            return "Heute: " + res;
        if(res.equals(DatumEchtMorgen))
            return  "Morgen: "  + res;

        if(res.equals(DatumEchtÜbermorgen))
            return "Übermorgen: " + res;

        return res;
    }
}

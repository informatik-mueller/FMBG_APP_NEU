package de.mueller104.informatik.dsbvertretung;
import org.apache.commons.lang3.StringUtils;

import com.google.appinventor.components.runtime.Button;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.HandlesEventDispatching;
import com.google.appinventor.components.runtime.HorizontalArrangement;
import com.google.appinventor.components.runtime.Label;
import com.google.appinventor.components.runtime.VerticalArrangement;

import org.apache.commons.text.StringEscapeUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.sematre.api.dsbmobile.*;

public class NewsActivity extends Form implements HandlesEventDispatching {

    private ArrayList<News> Nachrichten;
    private Label Überschrift; //Java unterstützt Umlaute
    private final static float FONT_SIZE = 15.0f;

    protected void $define(){
        this.Scrollable(true);
        //this.BackgroundColor(0xFFF89432);
        this.AlignHorizontal(3);

        DSBMobile dsbMobile = new DSBMobile("168442", "schule");
        Nachrichten = dsbMobile.getNews();
        Nachrichten.add(Nachrichten.get(0));

        Überschrift = new Label(this);
        Überschrift.FontSize(30.0f);
        Überschrift.FontBold(true);
        Überschrift.Text("News");
        Überschrift.TextColor(0xFF444444);

        for(int i = 1; i < Nachrichten.size(); i++){
            if(Nachrichten.get(i-1).getHeadLine() == Nachrichten.get(i-1).getHeadLine() ||
               Nachrichten.get(i-1).getWholeMessage() == Nachrichten.get(i-1).getWholeMessage()){
                Nachrichten.remove(i);
            }
        }

        obsoleteZeilenFiltern();

        for(int i = 0; i < Nachrichten.size(); i++){
            VerticalArrangement v = new VerticalArrangement(this);
            Label Space = new Label(v);
            Space.HeightPercent(3);
            HorizontalArrangement h1 = new HorizontalArrangement(v);
            HorizontalArrangement h2 = new HorizontalArrangement(v);
            HorizontalArrangement h3 = new HorizontalArrangement(v);
            News nachricht = Nachrichten.get(i);
            Label Titel = new  Label(h1);
            Titel.Text("Titel: ");
            Titel.FontBold(true);
            Titel.TextColor(0xFF444444);
            Label TitelWert = new Label(h1);
            TitelWert.Text(nachricht.getHeadLine());
            TitelWert.TextColor(0xFF444444);
            Label Datum = new Label(h2);
            Datum.Text("Datum: ");
            Datum.FontBold(true);
            Datum.TextColor(0xFF444444);
            Label DatumWert = new Label(h2);
            DatumWert.Text(nachricht.getDate());
            DatumWert.TextColor(0xFF444444);
            Label Mitteilung = new Label(h3);
            Mitteilung.Text("Mitteilung: ");
            Mitteilung.FontBold(true);
            Mitteilung.TextColor(0xFF444444);
            Label MitteilungWert = new Label(h3);
            MitteilungWert.TextColor(0xFF444444);
            String MitteilungHTML = nachricht.getWholeMessage();
            MitteilungWert.Text(StringEscapeUtils.unescapeHtml4(MitteilungHTML));
            if(i<Nachrichten.size()-1){
                Button line = new Button(v);
                line.BackgroundColor(0xFF444444);
                line.Width(Component.LENGTH_FILL_PARENT);
                line.HeightPercent(1);
                line.Enabled(false);
            }


        }


    }

    private void obsoleteZeilenFiltern() {
        List <String> wörter = new ArrayList<>();
        wörter.add("false");
        wörter.add("X-NONE");

        for(int i = 0; i< Nachrichten.size(); i++){
            String msg = Nachrichten.get(i).getWholeMessage();
            List<String> zeilen = Arrays.asList(msg.split("[\\r\\n]+")); //regex
            for (String wort : wörter){
                for(int j = 0; i<zeilen.size(); i++){
                    if(zeilen.get(i).indexOf(wort) >= 0){
                        try{
                        zeilen.remove(i);
                            }
                        catch(Exception e){
                            System.err.println("Filterung hat nicht geklappt");
                            e.printStackTrace();
                        }
                        }
                }
            }
           String gefilterteMsg = StringUtils.join(zeilen);
            //TODO: Absturz dieser Funktionalität verhindern
            //Nachrichten.get(i).setWholeMessage(gefilterteMsg);
        }
    }
}

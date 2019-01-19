package de.mueller104.informatik.dsbvertretung;

import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.HandlesEventDispatching;
import com.google.appinventor.components.runtime.HorizontalArrangement;
import com.google.appinventor.components.runtime.Label;
import com.google.appinventor.components.runtime.VerticalArrangement;

import org.apache.commons.text.StringEscapeUtils;

import java.util.ArrayList;

import de.sematre.api.dsbmobile.*;

public class NewsActivity extends Form implements HandlesEventDispatching {

    private ArrayList<News> Nachrichten;
    private Label Überschrift; //Java unterstützt Umlaute

    protected void $define(){
        this.Scrollable(true);
        this.AlignHorizontal(3);
        DSBMobile dsbMobile = new DSBMobile("168442", "schule");
        Nachrichten = dsbMobile.getNews();

        Überschrift = new Label(this);
        Überschrift.FontSize(30.0f);
        Überschrift.FontBold(true);
        Überschrift.Text("News");

        for(int i = 0; i < Nachrichten.size(); i++){
            VerticalArrangement v = new VerticalArrangement(this);
            HorizontalArrangement h1 = new HorizontalArrangement(v);
            HorizontalArrangement h2 = new HorizontalArrangement(v);
            HorizontalArrangement h3 = new HorizontalArrangement(v);
            News nachricht = Nachrichten.get(i);
            Label Titel = new  Label(h1);
            Titel.Text("Titel: ");
            Titel.FontBold(true);
            Label TitelWert = new Label(h1);
            TitelWert.Text(nachricht.getHeadLine());
            Label Datum = new Label(h2);
            Datum.Text("Datum: ");
            Datum.FontBold(true);
            Label DatumWert = new Label(h2);
            DatumWert.Text(nachricht.getDate());
            Label Mitteilung = new Label(h3);
            Mitteilung.Text("Mitteilung: ");
            Mitteilung.FontBold(true);
            Label MitteilungWert = new Label(h3);
            String MitteilungHTML = nachricht.getWholeMessage();
            MitteilungWert.Text(StringEscapeUtils.unescapeHtml4(MitteilungHTML));

        }


    }
}

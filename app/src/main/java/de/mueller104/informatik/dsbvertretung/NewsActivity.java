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
    private final static float FONT_SIZE = 14.0f;
    private List<String> AlternativeNews = new ArrayList<>();

    protected void $define() {
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

        for (int i = 1; i < Nachrichten.size(); i++) {
            if (Nachrichten.get(i - 1).getHeadLine() == Nachrichten.get(i - 1).getHeadLine() ||
                    Nachrichten.get(i - 1).getWholeMessage() == Nachrichten.get(i - 1).getWholeMessage()) {
                Nachrichten.remove(i);
            }
        }

        for (int i = 0; i < Nachrichten.size(); i++) {
            VerticalArrangement v = new VerticalArrangement(this);
            v.WidthPercent(95);
            Label Space = new Label(v);
            Space.HeightPercent(3);
            HorizontalArrangement h1 = new HorizontalArrangement(v);
            HorizontalArrangement h2 = new HorizontalArrangement(v);
            HorizontalArrangement h3 = new HorizontalArrangement(v);
            News nachricht = Nachrichten.get(i);
            Label Titel = new Label(h1);
            Titel.Text("Titel: ");
            Titel.FontSize(FONT_SIZE);
            Titel.FontBold(true);
            Titel.TextColor(0xFF444444);
            Label TitelWert = new Label(h1);
            TitelWert.Text(nachricht.getHeadLine());
            TitelWert.FontSize(FONT_SIZE);
            TitelWert.TextColor(0xFF444444);
            Label Datum = new Label(h2);
            Datum.Text("Datum: ");
            Datum.FontSize(FONT_SIZE);
            Datum.FontBold(true);
            Datum.TextColor(0xFF444444);
            Label DatumWert = new Label(h2);
            DatumWert.Text(nachricht.getDate());
            DatumWert.FontSize(FONT_SIZE);
            DatumWert.TextColor(0xFF444444);
            Label Mitteilung = new Label(h3);
            Mitteilung.Text("Mitteilung: ");
            Mitteilung.FontSize(FONT_SIZE);
            Mitteilung.FontBold(true);
            Mitteilung.TextColor(0xFF444444);
            Label MitteilungWert = new Label(h3);
            MitteilungWert.FontSize(FONT_SIZE);
            MitteilungWert.TextColor(0xFF444444);
            String MitteilungHTML = nachricht.getWholeMessage();
            MitteilungWert.Text(ZeilenLöschen(StringEscapeUtils.unescapeHtml4(MitteilungHTML), 1));

            if (i < Nachrichten.size() - 1) {
                Button line = new Button(v);
                line.BackgroundColor(0xFF444444);
                line.Width(Component.LENGTH_FILL_PARENT);
                line.HeightPercent(1);
                line.Enabled(false);
            }


        }


    }

    private static String ZeilenLöschen(String msg, int anzahl) {
        String zeilen[] = msg.split("[\\r\\n]+"); //regex

        try {
            zeilen = Arrays.copyOf(zeilen, zeilen.length - anzahl);
        } catch (Exception e) {
            System.err.println("Konnte Array-Kopie nicht modifizieren");
            e.printStackTrace();
        }

        return StringUtils.join(zeilen, "\n");
    }

}


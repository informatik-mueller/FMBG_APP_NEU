package de.mueller104.informatik.dsbvertretung;


import android.content.Context; //eine Art Interface für App-weite Daten
import android.content.Intent; //eine Operation, welche geplant ist durchzuführen 
import android.content.SharedPreferences; //Dateisystem für Einstellungen (z.B.AutoLogin)
import android.net.ConnectivityManager; //Ein Klasse, um Operationen mit dem WLan-Interface durchzuführen
import android.net.NetworkInfo; //Eine Klasse, um Netzwerkinformationen zu erhalten
import android.widget.Toast; //Android-Toasts erstellen

import com.google.appinventor.components.runtime.EventDispatcher; // Events wie Click oder Timer auslösen
import com.google.appinventor.components.runtime.Form; // Die Basisklasse für alle AppInventor-Screens
import com.google.appinventor.components.runtime.HandlesEventDispatching; // Interface für das Auslösen von Events
import com.google.appinventor.components.runtime.Component; // Die Klasse für AppInventor-Komponenten
import com.google.appinventor.components.runtime.Notifier; //Benachrichtgungen, besonders der Exit-Dialog
import com.google.appinventor.components.runtime.TableArrangement; //Komponenten in einer Tabelle anordnen - für Kachellayout wichtig
import com.google.appinventor.components.runtime.HorizontalArrangement; // Komponenten nebeneinander anordnen
import com.google.appinventor.components.runtime.Button; //Schaltflächen importieren
import com.google.appinventor.components.runtime.VerticalArrangement; // Komponenten untereinander anordnen

import static android.widget.Toast.LENGTH_SHORT; //Konstante für kurze Toast-Länge importieren

/**
Hauptmenü - hier landet der Benutzer standardmäßig, wenn er eingeloggt ist
AppInventor: ja
*/

public class MainMenu extends Form implements HandlesEventDispatching {
        private TableArrangement TableArrangement1;
        //private VerticalArrangement VerticalArrangement3;
        private HorizontalArrangement HorizontalArrangement1;
        private Button RadioButton;
        private Button SchulessenButton;
        private HorizontalArrangement HorizontalArrangement2;
        private VerticalArrangement VerticalArrangement1;
        private Button NewsButton;
        private Button DokumenteButton;
        private VerticalArrangement VerticalArrangement2;
        private Button VertretungButton;
        private Button KontaktButton;
        private Button EinstellungenButton;
        private Notifier PopUp;
        private int EasterEggCounter = 0;
        private boolean isEasterEgg = false;
        private boolean FirstTimeSolitaer = false;

        
        /**
        Wird ausgelöst, wenn der Nutzer die Zurück-Taste drückt, ein Exit-Dailog wird dann erzeugt
        Zukünftig soll ein geheimes Solitärfeature eingebaut werden
        */
        @Override
        public void onBackPressed(){
            if(!FirstTimeSolitaer && !isEasterEgg)
            PopUp.ShowChooseDialog("Bitte wählen Sie eine Option aus", "FMBGo verlassen?", "Ja", "Nein", true);
            else if(FirstTimeSolitaer) PopUp.ShowChooseDialog("Bitte wählen Sie eine Option aus", "FMBGo verlassen?", "Ja", "Solitär", true);
        }

        /**
        Diese Methode wird beim Initialisieren des AppInventor-Screens aufgerufen
        */
        
        protected void $define() {

            this.AppName("FMBG_App_Entwurf");
            this.BackgroundColor(0xFF444444);
            this.Title("Felix-Mendelssohn_Bartholdy_Gymnasium");
            this.ScreenOrientation("portrait");

            checkEasterEgg();

            PopUp = new Notifier(this);
            this.AlignHorizontal(3);

            TableArrangement1 = new TableArrangement(this);
            TableArrangement1.Columns(8);
            TableArrangement1.HeightPercent(100);
            TableArrangement1.WidthPercent(100);
            TableArrangement1.Rows(8);
            HorizontalArrangement1 = new HorizontalArrangement(TableArrangement1);
            HorizontalArrangement1.BackgroundColor(0x00FFFFFF);
            HorizontalArrangement1.Column(0);
            HorizontalArrangement1.HeightPercent(37);
            HorizontalArrangement1.Width(Component.LENGTH_FILL_PARENT);
            HorizontalArrangement1.Row(0);
            RadioButton = new Button(HorizontalArrangement1);
            RadioButton.BackgroundColor(0xFFCC6600);
            RadioButton.FontSize(20);
            RadioButton.Height(LENGTH_FILL_PARENT);
            RadioButton.Width(-1062);
            RadioButton.Shape(2);
            RadioButton.Text("Schulradio");
            RadioButton.TextColor(0xFF444444);
            SchulessenButton = new Button(HorizontalArrangement1);
            SchulessenButton.BackgroundColor(0xFFF89432);
            SchulessenButton.FontSize(20);
            SchulessenButton.Height(LENGTH_FILL_PARENT);
            SchulessenButton.Width(LENGTH_FILL_PARENT);
            SchulessenButton.Shape(2);
            SchulessenButton.Text("Kantine");
            SchulessenButton.TextColor(0xFF444444);
            HorizontalArrangement2 = new HorizontalArrangement(TableArrangement1);
            HorizontalArrangement2.BackgroundColor(0x00FFFFFF);
            HorizontalArrangement2.Column(0);
            HorizontalArrangement2.Height(-1062);
            HorizontalArrangement2.Width(LENGTH_FILL_PARENT);
            HorizontalArrangement2.Row(1);
            VerticalArrangement1 = new VerticalArrangement(HorizontalArrangement2);
            VerticalArrangement1.BackgroundColor(0x00FFFFFF);
            VerticalArrangement1.Height(-1062);
            VerticalArrangement1.Width(-1050);
            NewsButton = new Button(VerticalArrangement1);
            NewsButton.BackgroundColor(0xFFF89432);
            NewsButton.FontSize(20);
            NewsButton.Height(-1020);
            NewsButton.Width(LENGTH_FILL_PARENT);
            NewsButton.Shape(2);
            NewsButton.Text("News");
            NewsButton.TextColor(0xFF444444);
            DokumenteButton = new Button(VerticalArrangement1);
            DokumenteButton.BackgroundColor(0xFFCC6600);
            DokumenteButton.FontSize(20);
            DokumenteButton.Height(-1025);
            DokumenteButton.Width(LENGTH_FILL_PARENT);
            DokumenteButton.Shape(2);
            DokumenteButton.Text("Dokumente");
            DokumenteButton.TextColor(0xFF444444);
            EinstellungenButton = new Button(VerticalArrangement1);
            EinstellungenButton.BackgroundColor(0xFFEA7D12);
            EinstellungenButton.FontSize(20);
            EinstellungenButton.Height(-1018);
            EinstellungenButton.Width(LENGTH_FILL_PARENT);
            EinstellungenButton.Shape(2);
            EinstellungenButton.Text("Einstellungen");
            EinstellungenButton.TextColor(0xFF444444);
            VerticalArrangement2 = new VerticalArrangement(HorizontalArrangement2);
            VerticalArrangement2.BackgroundColor(0x00FFFFFF);
            VerticalArrangement2.Height(-1062);
            VerticalArrangement2.Width(-1050);
            VertretungButton = new Button(VerticalArrangement2);
            VertretungButton.BackgroundColor(0xFFEA7D12);
            VertretungButton.FontSize(20);
            VertretungButton.Height(-1030);
            VertretungButton.Width(LENGTH_FILL_PARENT);
            VertretungButton.Shape(2);
            VertretungButton.Text("Vertretung");
            VertretungButton.TextColor(0xFF444444);
            KontaktButton = new Button(VerticalArrangement2);
            KontaktButton.BackgroundColor(0xFFF89432);
            KontaktButton.FontSize(20);
            KontaktButton.Height(LENGTH_FILL_PARENT);
            KontaktButton.Width(LENGTH_FILL_PARENT);
            KontaktButton.Shape(2);
            KontaktButton.Text("Kontakt");
            KontaktButton.TextColor(0xFF444444);
            EventDispatcher.registerEventForDelegation(this, "1", "Click");
            EventDispatcher.registerEventForDelegation(this, "2", "AfterChoosing");
        }

    private void checkEasterEgg() {
        SharedPreferences sharedPreferences = this.getSharedPreferences("easteregg", Context.MODE_PRIVATE);
        boolean easteregg = sharedPreferences.getBoolean("enable", false);
        if(easteregg){
            isEasterEgg = true;
        }
    }

    public boolean dispatchEvent(Component component, String componentName, String eventName, Object[] params){
            if(component.equals(VertretungButton) && eventName.equals("Click")){

                if(netzwerkVerfuegbar()) {
                Intent intent = new Intent(this, Datumswahl.class);
                startActivity(intent);
                }

                else {
                    Toast.makeText(this, "Kein Internet", LENGTH_SHORT).show();
                }
                return true;
            }

            if(component.equals(RadioButton) && eventName.equals("Click")){
                if(netzwerkVerfuegbar()){
                    Intent intent = new Intent(this, Radio2.class);
                    startActivity(intent);
                }

                else {
                     Toast.makeText(this, "Kein Internet", LENGTH_SHORT).show();
                }
                return true;
            }

            if(component.equals(NewsButton) && eventName.equals("Click")){
                if(netzwerkVerfuegbar()){
                    Intent intent = new Intent(this, NewsActivity.class);
                    startActivity(intent);
                }

                else {
                    Toast.makeText(this, "Kein Internet", LENGTH_SHORT).show();
                }
                return true;
            }

            if(component.equals(KontaktButton) && eventName.equals("Click")){
                if(netzwerkVerfuegbar()) {
                    Intent intent = new Intent(this, Kontakt.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(this, "Kein Internet", LENGTH_SHORT).show();
                }
                return true;
            }

            if(component.equals(EinstellungenButton) && eventName.equals("Click")){
                Toast.makeText(this, "Wird zurzeit nicht unterstützt", LENGTH_SHORT).show();
                return true;
            }

        if(component.equals(DokumenteButton) && eventName.equals("Click")){
            /*if(netzwerkVerfuegbar()){
                Intent intent = new Intent(this, Dokumente.class);
                startActivity(intent);
            }

            else {
                Toast.makeText(this, "Kein Internet", LENGTH_SHORT).show();
            }
            return true;*/
            Toast.makeText(this, "Wird zurzeit nicht unterstützt", LENGTH_SHORT).show();
        }

            if(component.equals(SchulessenButton) && eventName.equals("Click")){
                if(netzwerkVerfuegbar()){
                Intent intent = new Intent(this, Haftbefehl.class);
                startActivity(intent);
                }
                else{
                    Toast.makeText(this, "Kein Internet", LENGTH_SHORT).show();
                }
            }

            if(component.equals(PopUp) && eventName.equals("AfterChoosing")){
                String choice = (String)params[0];
                if(choice.equals("Ja"))
                    exit();
                else if(choice.equals("Nein"))
                    EasterEggCounter++;

                if(choice.equals("Solitär")){
                    solitaer();
                }


                else if(EasterEggCounter == 5 && !choice.equals("Solitär"))
                    enableEasterEgg();
                return true;
            }

            return false;
        }


        private boolean netzwerkVerfuegbar() {
            ConnectivityManager connectivityManager =
                    (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

        private void exit(){
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

        private void enableEasterEgg(){
            SharedPreferences sharedPreferences = this.getSharedPreferences("easteregg", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("enable", true);
            editor.commit();
            Toast.makeText(this, "Solitär wurde aktiviert", Toast.LENGTH_SHORT).show();
            checkEasterEgg();
            FirstTimeSolitaer = true;
        }

        private void solitaer(){
            Intent intent = new Intent(this, Solitär.class);
            intent.putExtra("first_time", FirstTimeSolitaer);
            startActivity(intent);
        }

    }

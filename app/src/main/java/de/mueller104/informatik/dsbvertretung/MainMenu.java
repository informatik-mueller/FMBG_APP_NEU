package de.mueller104.informatik.dsbvertretung;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.google.appinventor.components.runtime.EventDispatcher;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.HandlesEventDispatching;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.Notifier;
import com.google.appinventor.components.runtime.TableArrangement;
import com.google.appinventor.components.runtime.HorizontalArrangement;
import com.google.appinventor.components.runtime.Button;
import com.google.appinventor.components.runtime.VerticalArrangement;

import static android.widget.Toast.LENGTH_SHORT;

//TODO: 'kein Internet'-Toast für betroffene Screens implemntieren
//TODO: Progressbar für lange Wartezeiten

/*TODO: es müssen noch implentiert werden:
 * Dokumente
 * LulListe
 * Einstellungen
 *
 * TODO: angefangen wurden:
 * Schulradio
 * Kantine
 * News
 * Datumswahl
 * Vertretung
*/

public class MainMenu extends Form implements HandlesEventDispatching {
        private TableArrangement TableArrangement1;
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
        private boolean FirstTimeSolitär = false;

        @Override
        public void onBackPressed(){
            if(!FirstTimeSolitär && !isEasterEgg)
            PopUp.ShowChooseDialog("Bitte wählen Sie eine Option aus", "FMBGo verlassen?", "Ja", "Nein", true);
            else if(FirstTimeSolitär) PopUp.ShowChooseDialog("Bitte wählen Sie eine Option aus", "FMBGo verlassen?", "Ja", "Solitär", true);
        }

        protected void $define() {

            this.AppName("FMBG_App_Entwurf");
            this.BackgroundColor(0xFF444444);
            this.Title("Felix-Mendelssohn_Bartholdy_Gymnasium");
            this.ScreenOrientation("portrait");

            checkEasterEgg();

            PopUp = new Notifier(this);
            TableArrangement1 = new TableArrangement(this);
            TableArrangement1.Columns(8);
            TableArrangement1.Height(LENGTH_FILL_PARENT);
            TableArrangement1.Width(-1100);
            TableArrangement1.Rows(8);
            HorizontalArrangement1 = new HorizontalArrangement(TableArrangement1);
            HorizontalArrangement1.BackgroundColor(0x00FFFFFF);
            HorizontalArrangement1.Column(0);
            HorizontalArrangement1.Height(-1037);
            HorizontalArrangement1.Width(-1100);
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

                if(netzwerkVerfügbar()) {
                Intent intent = new Intent(this, Datumswahl.class);
                startActivity(intent);
                }

                else {
                    Toast.makeText(this, "Kein Internet", LENGTH_SHORT).show();
                }
                return true;
            }

            if(component.equals(RadioButton) && eventName.equals("Click")){
                if(netzwerkVerfügbar()){
                    Intent intent = new Intent(this, SimpleRadio.class);
                    startActivity(intent);
                }

                else {
                     Toast.makeText(this, "Kein Internet", LENGTH_SHORT).show();
                }
                return true;
            }

            if(component.equals(NewsButton) && eventName.equals("Click")){
                if(netzwerkVerfügbar()){
                    Intent intent = new Intent(this, NewsActivity.class);
                    startActivity(intent);
                }

                else {
                    Toast.makeText(this, "Kein Internet", LENGTH_SHORT).show();
                }
                return true;
            }

            if(component.equals(KontaktButton) && eventName.equals("Click")){
                Intent intent = new Intent(this, LulListe.class);
                startActivity(intent);
            }

        if(component.equals(DokumenteButton) && eventName.equals("Click")){
            if(netzwerkVerfügbar()){
                Intent intent = new Intent(this, Dokumente.class);
                startActivity(intent);
            }

            else {
                Toast.makeText(this, "Kein Internet", LENGTH_SHORT).show();
            }
            return true;
        }

            if(component.equals(SchulessenButton) && eventName.equals("Click")){
                Intent intent = new Intent(this, Schulessen.class);
                intent.putExtra("url", "https://natuerlich-kunde.com/");
                startActivity(intent);
            }

            if(component.equals(PopUp) && eventName.equals("AfterChoosing")){
                String choice = (String)params[0];
                if(choice.equals("Ja"))
                    exit();
                else if(choice.equals("Nein"))
                    EasterEggCounter++;

                if(choice.equals("Solitär")){
                    solitär();
                }


                else if(EasterEggCounter == 5 && !choice.equals("Solitär"))
                    enableEasterEgg();
                return true;
            }

            return false;
        }


        private boolean netzwerkVerfügbar() {
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
            FirstTimeSolitär = true;
        }

        private void solitär(){
            Intent intent = new Intent(this, Solitär.class);
            intent.putExtra("first_time", FirstTimeSolitär);
            startActivity(intent);
        }

    }

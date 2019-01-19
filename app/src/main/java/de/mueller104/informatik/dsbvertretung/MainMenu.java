package de.mueller104.informatik.dsbvertretung;

import android.content.Context;
import android.content.Intent;
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


public class MainMenu extends Form implements HandlesEventDispatching {
        private TableArrangement TableArrangement1;
        private HorizontalArrangement HorizontalArrangement1;
        private Button RadioButton;
        private Button Button2;
        private HorizontalArrangement HorizontalArrangement2;
        private VerticalArrangement VerticalArrangement1;
        private Button Button3;
        private Button Button4;
        private VerticalArrangement VerticalArrangement2;
        private Button VertretungButton;
        private Button Button6;
        private Notifier PopUp;

        @Override
        public void onBackPressed(){
            PopUp.ShowChooseDialog("Bitte wählen Sie eine Option aus", "FMBGo verlassen?", "Ja", "Nein", true);
        }

        protected void $define() {

            this.AppName("FMBG_App_Entwurf");
            this.BackgroundColor(0xFF444444);
            this.Title("Felix-Mendelssohn_Bartholdy_Gymnasium");

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
            Button2 = new Button(HorizontalArrangement1);
            Button2.BackgroundColor(0xFFF89432);
            Button2.FontSize(20);
            Button2.Height(LENGTH_FILL_PARENT);
            Button2.Width(LENGTH_FILL_PARENT);
            Button2.Shape(2);
            Button2.Text("Kantine");
            Button2.TextColor(0xFF444444);
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
            Button3 = new Button(VerticalArrangement1);
            Button3.BackgroundColor(0xFFF89432);
            Button3.FontSize(20);
            Button3.Height(-1020);
            Button3.Width(LENGTH_FILL_PARENT);
            Button3.Shape(2);
            Button3.Text("News");
            Button3.TextColor(0xFF444444);
            Button4 = new Button(VerticalArrangement1);
            Button4.BackgroundColor(0xFFCC6600);
            Button4.FontSize(20);
            Button4.Height(LENGTH_FILL_PARENT);
            Button4.Width(LENGTH_FILL_PARENT);
            Button4.Shape(2);
            Button4.Text("Dokumente");
            Button4.TextColor(0xFF444444);
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
            Button6 = new Button(VerticalArrangement2);
            Button6.BackgroundColor(0xFFF89432);
            Button6.FontSize(20);
            Button6.Height(LENGTH_FILL_PARENT);
            Button6.Width(LENGTH_FILL_PARENT);
            Button6.Shape(2);
            Button6.Text("Kontakt");
            Button6.TextColor(0xFF444444);
            EventDispatcher.registerEventForDelegation(this, "1", "Click");
            EventDispatcher.registerEventForDelegation(this, "2", "AfterChoosing");
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

            if(component.equals(PopUp) && eventName.equals("AfterChoosing")){
                String choice = (String)params[0];
                if(choice.equals("Ja"))
                    exit();
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

    }

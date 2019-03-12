package de.mueller104.informatik.dsbvertretung;

import android.content.Intent;
import android.os.StrictMode;

import com.google.appinventor.components.runtime.EventDispatcher;
import com.google.appinventor.components.runtime.Form;

import com.google.appinventor.components.runtime.HandlesEventDispatching;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.HorizontalArrangement;
import com.google.appinventor.components.runtime.Button;
import com.google.appinventor.components.runtime.Label;
import com.google.appinventor.components.runtime.TableArrangement;
import com.google.appinventor.components.runtime.VerticalArrangement;
import com.google.appinventor.components.runtime.TextBox;


public class Kontakt extends Form implements HandlesEventDispatching {
    private VerticalArrangement VerticalArrangement3;
    private Button Button3;
    private TableArrangement TableArrangement1;
    private HorizontalArrangement HorizontalArrangement4;
    private Button Button1;
    private VerticalArrangement VerticalArrangement1;
    private VerticalArrangement VerticalArrangement2;
    private Button Button2;
    private Label Space1;
    private TableArrangement TableArrangement2;
    private TextBox TextBox6;
    private TextBox TextBox4;
    private TextBox TextBox5;
    private TextBox TextBox7;
    protected void $define() {

        getWindow().setSoftInputMode(2);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        this.AppName("FMBG_App_Entwurf");
        this.BackgroundColor(0xFF444444);
        this.Title("Kontakt");
        VerticalArrangement1 = new VerticalArrangement(this);
        VerticalArrangement1.WidthPercent(95);
        VerticalArrangement3 = new VerticalArrangement(VerticalArrangement1);
        VerticalArrangement3.AlignHorizontal(3);
        VerticalArrangement3.BackgroundColor(0x00FFFFFF);
        VerticalArrangement3.Height(-1010);
        VerticalArrangement3.Width(-1100);
        Button3 = new Button(VerticalArrangement3);
        Button3.BackgroundColor(0xFF525252);
        Button3.FontSize(20);
        Button3.Height(-1010);
        Button3.Width(-1050);
        Button3.Text("Menü");
        Button3.TextColor(0xFFFFFFFF);
        Space1 = new Label(VerticalArrangement3);
        Space1.HeightPercent(100);
        TableArrangement1 = new TableArrangement(this);
        TableArrangement1.Height(-1100);
        TableArrangement1.Width(-1100);
        TableArrangement1.Rows(4);
        HorizontalArrangement4 = new HorizontalArrangement(TableArrangement1);
        HorizontalArrangement4.AlignHorizontal(3);
        HorizontalArrangement4.BackgroundColor(0xFF444444);
        HorizontalArrangement4.Column(0);
        HorizontalArrangement4.Height(-1010);
        HorizontalArrangement4.Row(3);
        Button1 = new Button(HorizontalArrangement4);
        Button1.BackgroundColor(0x00FFFFFF);
        Button1.FontSize(20);
        Button1.Text("LuL Liste (Kürzel)");
        Button1.TextColor(0xFFFFFFFF);
        VerticalArrangement2 = new VerticalArrangement(TableArrangement1);
        VerticalArrangement2.AlignHorizontal(3);
        VerticalArrangement2.AlignVertical(2);
        VerticalArrangement2.BackgroundColor(0xFF444444);
        VerticalArrangement2.Column(0);
        VerticalArrangement2.Height(-1025);
        VerticalArrangement2.Row(2);
        Button2 = new Button(VerticalArrangement2);
        Button2.BackgroundColor(0xFF444444);
        Button2.FontSize(20);
        Button2.Text("Kontaktformular");
        Button2.TextColor(0xFFFFFFFF);
        TableArrangement2 = new TableArrangement(TableArrangement1);
        TableArrangement2.Column(0);
        TableArrangement2.Columns(3);
        TableArrangement2.Height(-1040);
        TableArrangement2.Width(-1100);
        TableArrangement2.Row(1);
        TextBox6 = new TextBox(TableArrangement2);
        TextBox6.BackgroundColor(0xFF444444);
        TextBox6.Column(0);
        TextBox6.FontSize(15);
        TextBox6.Width(-1050);
        TextBox6.Row(0);
        TextBox6.Text("Sekretariat");
        TextBox6.TextColor(0xFFFFFFFF);
        TextBox4 = new TextBox(TableArrangement2);
        TextBox4.BackgroundColor(0xFF444444);
        TextBox4.Column(1);
        TextBox4.FontSize(15);
        TextBox4.Width(-1050);
        TextBox4.Row(0);
        TextBox4.Text("Schulleitung");
        TextBox4.TextColor(0xFFFFFFFF);
        TextBox5 = new TextBox(TableArrangement2);
        TextBox5.BackgroundColor(0xFF444444);
        TextBox5.Column(1);
        TextBox5.Height(-1020);
        TextBox5.Width(-1050);
        TextBox5.MultiLine(true);
        TextBox5.Row(1);
        TextBox5.Text("Schulleiter: Herr Dr. P. Stock  Stellvertretende Schulleiterin: Frau M. Leddin  Pädagogische Koordinatorin der Sekundarstufe I: Frau L. Viellechner - Wegener (kommissarisch)  Pädagogische Koordinatorin der Sekundarstufe II: Frau P. Seeger");
        TextBox5.TextColor(0xFFFFFFFF);
        TextBox7 = new TextBox(TableArrangement2);
        TextBox7.BackgroundColor(0xFF444444);
        TextBox7.Column(0);
        TextBox7.Height(-1020);
        TextBox7.Width(-1050);
        TextBox7.MultiLine(true);
        TextBox7.Row(1);
        TextBox7.Text("Sekretärin: Frau Wurtzel Tel.: 030 - 42 43 85 12 Fax: 030 - 42 43 85 31 Mail: Sekretariat (B. Wurtzel)");
        TextBox7.TextColor(0xFFFFFFFF);
        EventDispatcher.registerEventForDelegation(this, "nt", "Click");
    }
    public boolean dispatchEvent(Component component, String componentName, String eventName, Object[] params){
        if(component.equals(Button1) && (eventName.equals("Click"))){
            Intent intent = new Intent(this, LulListe.class);
            startActivity(intent);
            return true;
        }
        return false;
    }
}
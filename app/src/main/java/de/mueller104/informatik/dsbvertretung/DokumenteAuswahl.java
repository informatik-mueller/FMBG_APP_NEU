package de.mueller104.informatik.dsbvertretung;


import com.google.appinventor.components.runtime.HandlesEventDispatching;

import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.VerticalArrangement;
import com.google.appinventor.components.runtime.HorizontalArrangement;
import com.google.appinventor.components.runtime.Button;

public class DokumenteAuswahl extends Form implements HandlesEventDispatching {
    private VerticalArrangement VerticalArrangement1;
    private HorizontalArrangement HorizontalArrangement6;
    private Button Button2;
    private HorizontalArrangement HorizontalArrangement1;
    private Button Button3;
    private HorizontalArrangement HorizontalArrangement2;
    private Button Button4;
    private HorizontalArrangement HorizontalArrangement4;
    private Button Button5;
    private HorizontalArrangement HorizontalArrangement5;
    protected void $define() {
        this.AppName("FMBG_App_Entwurf");
        this.BackgroundColor(0xFF444444);
        this.Scrollable(true);
        this.Title("Dokumente");
        VerticalArrangement1 = new VerticalArrangement(this);
        VerticalArrangement1.AlignHorizontal(3);
        VerticalArrangement1.BackgroundColor(0x00FFFFFF);
        VerticalArrangement1.Height(-1100);
        VerticalArrangement1.Width(-1100);
        HorizontalArrangement6 = new HorizontalArrangement(VerticalArrangement1);
        HorizontalArrangement6.BackgroundColor(0x00FFFFFF);
        HorizontalArrangement6.Height(-1010);
        Button2 = new Button(VerticalArrangement1);
        Button2.BackgroundColor(0xFFCC6600);
        Button2.FontSize(20);
        Button2.Height(-1015);
        Button2.Width(-1100);
        Button2.Text("Allgemein");
        Button2.TextColor(0xFF000000);
        HorizontalArrangement1 = new HorizontalArrangement(VerticalArrangement1);
        HorizontalArrangement1.BackgroundColor(0x00FFFFFF);
        HorizontalArrangement1.Height(-1005);
        Button3 = new Button(VerticalArrangement1);
        Button3.BackgroundColor(0xFFF89432);
        Button3.FontSize(20);
        Button3.Height(-1015);
        Button3.Width(-1100);
        Button3.Text("Lehrer");
        Button3.TextColor(0xFF000000);
        HorizontalArrangement2 = new HorizontalArrangement(VerticalArrangement1);
        HorizontalArrangement2.BackgroundColor(0x00FFFFFF);
        HorizontalArrangement2.Height(-1005);
        Button4 = new Button(VerticalArrangement1);
        Button4.BackgroundColor(0xFFCC6600);
        Button4.FontSize(20);
        Button4.Height(-1015);
        Button4.Width(-1100);
        Button4.Text("Schüler");
        Button4.TextColor(0xFF000000);
        HorizontalArrangement4 = new HorizontalArrangement(VerticalArrangement1);
        HorizontalArrangement4.BackgroundColor(0x00FFFFFF);
        HorizontalArrangement4.Height(-1005);
        Button5 = new Button(VerticalArrangement1);
        Button5.BackgroundColor(0xFFF89432);
        Button5.FontSize(20);
        Button5.Height(-1015);
        Button5.Width(-1100);
        Button5.Text("Eltern");
        Button5.TextColor(0xFF000000);
        HorizontalArrangement5 = new HorizontalArrangement(VerticalArrangement1);
        HorizontalArrangement5.BackgroundColor(0x00FFFFFF);
        HorizontalArrangement5.Height(-1005);
    }
    public boolean dispatchEvent(Component component, String componentName, String eventName, Object[] params){
        return false;
    }
}

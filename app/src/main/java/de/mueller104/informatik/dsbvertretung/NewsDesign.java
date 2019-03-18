package de.mueller104.informatik.dsbvertretung;

import com.google.appinventor.components.runtime.HandlesEventDispatching;
import com.google.appinventor.components.runtime.EventDispatcher;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.VerticalArrangement;
import com.google.appinventor.components.runtime.Label;

class NewsDesign extends Form implements HandlesEventDispatching {
    private VerticalArrangement VerticalArrangement1;
    private VerticalArrangement VerticalArrangement2;
    private Label Label1;
    private Label Label2;
    private Label Label3;
    private VerticalArrangement VerticalArrangement3;
    private VerticalArrangement VerticalArrangement4;
    protected void $define() {
        this.AppName("FMBG_App_Entwurf");
        this.BackgroundColor(0xFF444444);
        this.Title("News");
        VerticalArrangement1 = new VerticalArrangement(this);
        VerticalArrangement1.BackgroundColor(0x00FFFFFF);
        VerticalArrangement1.Height(-1100);
        VerticalArrangement1.Width(-1100);
        VerticalArrangement2 = new VerticalArrangement(VerticalArrangement1);
        VerticalArrangement2.BackgroundColor(0xFFF89432);
        VerticalArrangement2.Height(-1033);
        VerticalArrangement2.Width(-1100);
        Label1 = new Label(VerticalArrangement2);
        Label1.FontSize(20);
        Label1.Height(-1005);
        Label1.Width(-1015);
        Label1.Text("Titel:");
        Label2 = new Label(VerticalArrangement2);
        Label2.FontSize(20);
        Label2.Height(-1005);
        Label2.Width(-1020);
        Label2.Text("Datum:");
        Label3 = new Label(VerticalArrangement2);
        Label3.FontSize(20);
        Label3.Height(-1005);
        Label3.Width(-1026);
        Label3.Text("Mitteilung:");
        VerticalArrangement3 = new VerticalArrangement(VerticalArrangement1);
        VerticalArrangement3.BackgroundColor(0xFFEA7D12);
        VerticalArrangement3.Height(-1034);
        VerticalArrangement3.Width(-1100);
        VerticalArrangement4 = new VerticalArrangement(VerticalArrangement1);
        VerticalArrangement4.BackgroundColor(0xFFCC6600);
        VerticalArrangement4.Height(-1033);
        VerticalArrangement4.Width(-1100);
    }

    public boolean dispatchEvent(Component component, String componentName, String eventName, Object[] params){
        return false;
    }
}
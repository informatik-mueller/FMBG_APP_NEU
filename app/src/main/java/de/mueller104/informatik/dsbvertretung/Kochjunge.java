package de.mueller104.informatik.dsbvertretung;

import android.content.Intent;

import com.google.appinventor.components.runtime.HandlesEventDispatching;
import com.google.appinventor.components.runtime.EventDispatcher;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.Button;
import com.google.appinventor.components.runtime.VerticalArrangement;

public class Kochjunge extends Form implements HandlesEventDispatching {

    private Button Button1;
    private VerticalArrangement V1;


    protected void $define() {
        V1 = new VerticalArrangement(this);
        V1.WidthPercent(100);
        V1.WidthPercent(100);;
        Button1 = new Button(V1);
        Button1.Column(0);
        Button1.Height(Component.LENGTH_FILL_PARENT);
        Button1.Width(Component.LENGTH_FILL_PARENT);
        Button1.Image("haftbefehl.jpg");
        Button1.Row(0);
        EventDispatcher.registerEventForDelegation(this, "ok", "Click");
    }
    public boolean dispatchEvent(Component component, String componentName, String eventName, Object[] params){
        if(component.equals(Button1) && eventName.equals("Click")){
            Intent intent = new Intent(this, Schulessen.class);
            intent.putExtra("url", "https://natuerlich-kunde.com/");
            startActivity(intent);
            return true;
        }
        return false;
    }
}

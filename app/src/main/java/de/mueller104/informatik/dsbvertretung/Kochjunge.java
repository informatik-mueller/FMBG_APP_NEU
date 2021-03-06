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
        Button1 = new Button(this);
        Button1.Height(Component.LENGTH_FILL_PARENT);
        Button1.Width(Component.LENGTH_FILL_PARENT);
        Button1.Image("haftbefehl.jpg");
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

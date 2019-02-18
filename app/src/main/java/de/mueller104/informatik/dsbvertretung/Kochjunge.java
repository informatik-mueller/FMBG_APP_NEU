package de.mueller104.informatik.dsbvertretung;

import android.content.Intent;

import com.google.appinventor.components.runtime.HandlesEventDispatching;
import com.google.appinventor.components.runtime.EventDispatcher;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.TableArrangement;
import com.google.appinventor.components.runtime.Button;

public class Kochjunge extends Form implements HandlesEventDispatching {
    private TableArrangement TableArrangement1;
    private Button Button1;
    protected void $define() {
        TableArrangement1 = new TableArrangement(this);
        TableArrangement1.Height(-1100);
        TableArrangement1.Width(-1100);
        Button1 = new Button(TableArrangement1);
        Button1.Column(0);
        Button1.Height(-1100);
        Button1.Width(-1100);
        Button1.Image("kochjunge.jpg");
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

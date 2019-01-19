package de.mueller104.informatik.dsbvertretung;

import com.google.appinventor.components.runtime.Button;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.EventDispatcher;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.HandlesEventDispatching;
import com.google.appinventor.components.runtime.Player;

public class SimpleRadio extends Form implements HandlesEventDispatching {
    Button btn1;
    Player player1;

    public void $define(){
        player1 = new Player(this);
        player1.Source("http://stream.radiosaw.de/stream.mp3");
        btn1 = new Button(this);
        btn1.Text("Radio abspielen");
        EventDispatcher.registerEventForDelegation( this, "1", "Click" );
    }

    @Override
    public boolean dispatchEvent(Component component, String componentName, String eventName, Object[] args) {
        if(component.equals(btn1) && eventName.equals("Click")){
            player1.Start();
            return true;
        }
        return false;
    }
}
package de.mueller104.informatik.dsbvertretung;

import com.google.appinventor.components.runtime.Button;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.EventDispatcher;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.HandlesEventDispatching;

public class Dokumente extends Form implements HandlesEventDispatching {

    private Button downloadButton;
    private static final String Url = "";

    protected void $define(){
        downloadButton = new Button(this);

        EventDispatcher.registerEventForDelegation(this, "moritzistserioes", "Click");
    }

    @Override
    public boolean dispatchEvent(Component component, String componentName, String eventName, Object[] args) {
        if(component.equals(downloadButton) && eventName.equals("Click")){
            //Hier die Aktion ausführen, die passieren soll, wenn man auf den Button klickt
            return true;
        }
        return false;
    }


}

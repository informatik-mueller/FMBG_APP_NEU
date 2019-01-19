package de.mueller104.informatik.dsbvertretung;

import com.google.appinventor.components.runtime.Button;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.EventDispatcher;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.HandlesEventDispatching;
import com.google.appinventor.components.runtime.Label;
import com.google.appinventor.components.runtime.Player;

//TODO: Absturz nach Rückkher zum Hauptmenü verhindern

public class SimpleRadio extends Form implements HandlesEventDispatching {
    private Button Btn1;
    private Label Space1;
    private Player Player1;

    public void $define(){
        this.AlignHorizontal(3);
        Player1 = new Player(this);
        Player1.Source("http://stream.radiosaw.de/stream.mp3");

        Space1 = new Label(this);
        Space1.HeightPercent(40);
        Btn1 = new Button(this);
        Btn1.Text("Abspielen");
        Btn1.FontSize(30);
        EventDispatcher.registerEventForDelegation( this, "1", "Click" );
    }

    @Override
    public boolean dispatchEvent(Component component, String componentName, String eventName, Object[] args) {
        if(component.equals(Btn1) && eventName.equals("Click")){
            if (Player1.playerState == Player.State.PLAYING){
                Btn1.Text("Abspielen");
                Player1.Pause();
            }
            else{
                Btn1.Text("Pausieren");
                Player1.Start();
            }
            return true;
        }
        return false;
    }
}
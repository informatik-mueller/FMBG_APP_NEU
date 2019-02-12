package de.mueller104.informatik.dsbvertretung;

import com.google.appinventor.components.runtime.Button;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.EventDispatcher;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.HandlesEventDispatching;
import com.google.appinventor.components.runtime.HorizontalArrangement;
import com.google.appinventor.components.runtime.Image;
import com.google.appinventor.components.runtime.Label;
import com.google.appinventor.components.runtime.Player;
import com.google.appinventor.components.runtime.VerticalArrangement;


//(TODO: Benachrichtigung mit Player, um Radio zu togglen, wenn es minimiert ist)
//TODO: Archiv implementieren

public class SimpleRadio extends Form implements HandlesEventDispatching {
    private Label Space1;
    private Player Player1;
    private Label Überschrift;
    private Label ÜberschriftSpace;
    private Button Archiv;
    private Button Abspielen;
    private VerticalArrangement VerticalArrangement1;
    private HorizontalArrangement HorizontalArrangement1;
    private HorizontalArrangement HorizontalArrangement2;
    private Image Logo;
    private HorizontalArrangement HorizontalArrangement3;
    private static final String RadioUrl = "http://stream.radiosaw.de/stream.mp3";

    public void $define(){
        this.AlignHorizontal(3);
        this.BackgroundColor(0xFFF89432);
        this.Title("Schulradio");

        Player1 = new Player(this);
        Player1.Source(RadioUrl);

        VerticalArrangement1 = new VerticalArrangement(this);
        VerticalArrangement1.AlignHorizontal(3);
        //VerticalArrangement1.BackgroundColor(0xFF444444);
        VerticalArrangement1.BackgroundColor(0xFFF89432);
        VerticalArrangement1.Height(-1100);
        VerticalArrangement1.Width(-1100);
        HorizontalArrangement1 = new HorizontalArrangement(VerticalArrangement1);
        HorizontalArrangement1.AlignHorizontal(3);
        HorizontalArrangement1.BackgroundColor(0x00FFFFFF);
        HorizontalArrangement1.Height(-1020);
        HorizontalArrangement1.Width(-1100);
        ÜberschriftSpace = new Label(HorizontalArrangement1);
        ÜberschriftSpace.HeightPercent(80);
        Überschrift = new Label(HorizontalArrangement1);
        Überschrift.Text("Schulradio");
        Überschrift.HeightPercent(35);
        Überschrift.FontSize(30.0f);
        Überschrift.FontBold(true);
        Überschrift.TextColor(0xFF444444);
        HorizontalArrangement2 = new HorizontalArrangement(VerticalArrangement1);
        HorizontalArrangement2.AlignHorizontal(3);
        HorizontalArrangement2.AlignVertical(2);
        //HorizontalArrangement2.BackgroundColor(0xFF888888);
        HorizontalArrangement2.Height(-1050);
        HorizontalArrangement2.Width(-1100);
        Logo = new Image(HorizontalArrangement2);
        Logo.Picture("fmbg-logo-3.png");
        Logo.ScalePictureToFit(true);
        HorizontalArrangement3 = new HorizontalArrangement(VerticalArrangement1);
        Space1 = new Label(HorizontalArrangement3);
        Space1.HeightPercent(3);
        Abspielen = new Button(HorizontalArrangement3);
        Abspielen.Text("Abspielen");
        Abspielen.FontSize(30);
        Archiv = new Button(HorizontalArrangement3);
        Archiv.Text("Archiv");
        Archiv.FontSize(30);
        EventDispatcher.registerEventForDelegation( this, "1", "Click" );
    }

    @Override
    public boolean dispatchEvent(Component component, String componentName, String eventName, Object[] args) {
        if(component.equals(Logo) && eventName.equals("Click")){
            if (Player1.playerState == Player.State.PLAYING){
                Abspielen.Text("Abspielen");
                Player1.Pause();
            }
            else{
                Abspielen.Text("Pausieren");
                Player1.Start();
            }
            return true;
        }
        return false;
    }
}
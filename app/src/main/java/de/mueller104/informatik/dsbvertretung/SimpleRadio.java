package de.mueller104.informatik.dsbvertretung;

import android.widget.ProgressBar;

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
    private Image Logo;
    private HorizontalArrangement HorizontalArrangement2;
    private ProgressBar progressBar;
    private boolean isPlaying = false;
    private static final String RadioUrl = "http://stream.radiosaw.de/stream.mp3";

    public void $define(){
        this.AlignHorizontal(3);
        this.BackgroundColor(0xFFF89432);
        this.Title("Schulradio");

        Player1 = new Player(this);

        VerticalArrangement1 = new VerticalArrangement(this);
        VerticalArrangement1.AlignHorizontal(3);
        //VerticalArrangement1.BackgroundColor(0xFF444444);
        progressBar = new ProgressBar(this);
        VerticalArrangement1.BackgroundColor(0xFFF89432);
        VerticalArrangement1.Height(-1100);
        VerticalArrangement1.Width(-1100);
        ÜberschriftSpace = new Label(VerticalArrangement1);
        ÜberschriftSpace.HeightPercent(5);
        Überschrift = new Label(VerticalArrangement1);
        Überschrift.Text("Schulradio");
        Überschrift.HeightPercent(15);
        Überschrift.FontSize(30.0f);
        Überschrift.FontBold(true);
        Überschrift.TextColor(0xFF444444);
        HorizontalArrangement1 = new HorizontalArrangement(VerticalArrangement1);
        HorizontalArrangement1.AlignHorizontal(3);
        HorizontalArrangement1.AlignVertical(2);
        //HorizontalArrangement1.BackgroundColor(0xFF888888);
        HorizontalArrangement1.Height(-1050);
        HorizontalArrangement1.Width(-1100);
        Logo = new Image(HorizontalArrangement1);
        Logo.Picture("fmbg-logo-3.png");
        Logo.ScalePictureToFit(true);
        HorizontalArrangement2 = new HorizontalArrangement(VerticalArrangement1);
        Space1 = new Label(HorizontalArrangement2);
        Space1.HeightPercent(3);
        Abspielen = new Button(HorizontalArrangement2);
        Abspielen.Text("Abspielen");
        Abspielen.FontSize(30);
        Archiv = new Button(HorizontalArrangement2);
        Archiv.Text("Archiv");
        Archiv.FontSize(30);
        EventDispatcher.registerEventForDelegation( this, "1", "Click" );
    }

    @Override
    public boolean dispatchEvent(Component component, String componentName, String eventName, Object[] args) {
        if(component.equals(Abspielen) && eventName.equals("Click")){

            if (isPlaying){
                isPlaying = false;
                Abspielen.Text("Abspielen");
                Player1.Pause();
                return true;
            }
                Player1.Source(RadioUrl);
                isPlaying = true;
                Abspielen.Text("Pausieren");
                Player1.Start();
                return true;


        }
        return false;
    }
}
package de.mueller104.informatik.dsbvertretung;

import android.graphics.Point;
import android.view.Display;
import android.widget.Toast;

import com.google.appinventor.components.runtime.Canvas;
import com.google.appinventor.components.runtime.EventDispatcher;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.HandlesEventDispatching;

import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.ImageSprite;
import com.google.appinventor.components.runtime.Label;
import com.google.appinventor.components.runtime.Player;
import com.google.appinventor.components.runtime.VerticalArrangement;
import com.google.appinventor.components.runtime.HorizontalArrangement;
import com.google.appinventor.components.runtime.Button;
import com.google.appinventor.components.runtime.VerticalScrollArrangement;
import com.google.appinventor.components.runtime.Clock;

import java.util.ArrayList;
import java.util.List;

public class Radio2 extends Form implements HandlesEventDispatching {


    private VerticalArrangement VerticalArrangement1;
    private VerticalArrangement VerticalHeader;
    private ImageSprite PausePlayImage;
    private Canvas container;
    private VerticalArrangement VerticalArrangement2;
    private Label LetzteAudios;
    private VerticalScrollArrangement VerticalScroll1;
    private Player Player1;
    private List<String> availableUrls = new ArrayList<>();
    private List<String> availableNames = new ArrayList<>();
    private List<Button> buttonList = new ArrayList<>();
    private boolean isPlaying = false;
    private Clock timer;
    private boolean loaded = false;
    //TODO: wahre Url in Erfahrung bringen
    private String RadioUrl;
    private int selectionIndex = 0;
    private Label ÜberschriftSpace;
    private Label Überschrift;
    private Label Space2;

    protected void $define() {
        this.Title("Radio");
        this.AlignHorizontal(3);
        this.BackgroundColor(0xFF444444);
        this.ScreenOrientation("portrait");
        Player1 = new Player(this);
        availableUrls.add("http://stream.radiosaw.de/stream.mp3");
        availableNames.add("Radio Sachsen-Anhalt-Welle");
        RadioUrl = availableUrls.get(0);
        timer = new Clock(this);
        timer.TimerInterval(500);
        timer.TimerEnabled(true);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        VerticalArrangement1 = new VerticalArrangement(this);
        VerticalArrangement1.BackgroundColor(0xFF444444);
        VerticalArrangement1.Height(-1100);
        VerticalArrangement1.Width(-1100);
        VerticalArrangement1.AlignHorizontal(3);
        VerticalHeader = new VerticalArrangement(VerticalArrangement1);
        VerticalHeader.AlignHorizontal(3);
        VerticalHeader.AlignVertical(2);
        VerticalHeader.BackgroundColor(0x00FFFFFF);
        VerticalHeader.Height(-1010);
        VerticalHeader.Width(-1100);
        ÜberschriftSpace = new Label(VerticalHeader);
        ÜberschriftSpace.HeightPercent(1);
        Überschrift = new Label(VerticalHeader);
        Überschrift.FontSize(30.0f);
        Überschrift.TextColor(Label.COLOR_WHITE);
        Überschrift.Text("Schulradio");
        Überschrift.FontBold(true);
        Überschrift.HeightPercent(10);
        container = new Canvas(VerticalArrangement1);
        container.Height(-1050);
        container.Width(-1100);
        container.BackgroundColor(0xFFEA7D12);
        PausePlayImage = new ImageSprite(container);
        PausePlayImage.Picture("play_small.png");;
        PausePlayImage.X(size.x/8.5f);
        PausePlayImage.Y(size.y/20.0f);
        VerticalArrangement2 = new VerticalArrangement(VerticalArrangement1);
        VerticalArrangement2.BackgroundColor(0xFF444444);
        VerticalArrangement2.Height(-1040);
        VerticalArrangement2.Width(-1095);
        VerticalArrangement2.AlignHorizontal(3);
        Space2 = new Label(VerticalArrangement2);
        Space2.HeightPercent(2);
        LetzteAudios = new Label(VerticalArrangement2);
        LetzteAudios.BackgroundColor(0xFF444444);
        LetzteAudios.Height(-1005);
        LetzteAudios.Width(Component.LENGTH_FILL_PARENT);
        LetzteAudios.TextAlignment(Label.ALIGNMENT_CENTER);
        LetzteAudios.Text("Sendung verpasst?");
        LetzteAudios.TextColor(0xFFFFFFFF);
        LetzteAudios.FontSize(15.0f);
        VerticalScroll1 = new VerticalScrollArrangement(VerticalArrangement2);
        for(int i = 0; i<availableNames.size(); i++){
            Button temp = new Button(VerticalScroll1);
            temp.WidthPercent(100);
            temp.Text(availableNames.get(i));
            buttonList.add(temp);
        }
        EventDispatcher.registerEventForDelegation(this, "yeah", "Click");
        EventDispatcher.registerEventForDelegation(this, "yeah2", "Touched");
        EventDispatcher.registerEventForDelegation(this, "yeah3", "Timer");
        EventDispatcher.registerEventForDelegation(this, "yeah3", "Flung");
    }
    public boolean dispatchEvent(Component component, String componentName, String eventName, Object[] params){
        if(component.equals(PausePlayImage) && eventName.equals("Touched")){

            if(!loaded){
                loaded = true;
                Toast.makeText(this, "Das Schulradio ist live! \nViel Spaß beim Hören", Toast.LENGTH_LONG).show();
            }


            if (isPlaying){
                isPlaying = false;
                PausePlayImage.Picture("play_small.png");
                Player1.Pause();
                return true;
            }

                isPlaying = true;
                PausePlayImage.Picture("pause_small.png");
                Player1.Start();
                return true;
        }

        if(component.equals(PausePlayImage) && eventName.equals("Flung")){
            PausePlayImage.Speed((float)params[2]*3);
            //PausePlayImage.
        }



        int i = 0; for(Button button : buttonList){
            if(component.equals(button) && eventName.equals("Click")){
                RadioUrl = availableUrls.get(i);
                Player1.Source(RadioUrl);
            }
            i++;
        }

        if(component.equals(timer) && eventName.equals("Timer")){
            Player1.Source(RadioUrl);
            timer.TimerEnabled(false);
            return true;
        }

            return false;
    }



    }
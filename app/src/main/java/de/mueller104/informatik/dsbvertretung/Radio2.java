package de.mueller104.informatik.dsbvertretung;

import android.widget.Toast;

import com.google.appinventor.components.runtime.EventDispatcher;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.HandlesEventDispatching;

import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.Label;
import com.google.appinventor.components.runtime.Player;
import com.google.appinventor.components.runtime.VerticalArrangement;
import com.google.appinventor.components.runtime.HorizontalArrangement;
import com.google.appinventor.components.runtime.Button;
import com.google.appinventor.components.runtime.VerticalScrollArrangement;
import com.google.appinventor.components.runtime.Image;

import org.apache.commons.text.StringEscapeUtils;

import java.util.ArrayList;
import java.util.List;

public class Radio2 extends Form implements HandlesEventDispatching {


    private VerticalArrangement VerticalArrangement1;
    private HorizontalArrangement HorizontalArrangement1;
    private Button Button2;
    private HorizontalArrangement HorizontalArrangement2;
    private Image Button1;
    private VerticalArrangement VerticalArrangement2;
    private Label LetzteAudios;
    private HorizontalArrangement HorizontalArrangement3;
    private VerticalScrollArrangement VerticalScroll1;
    private Player Player1;
    private List<String> availableUrls = new ArrayList<>();
    private List<String> availableNames = new ArrayList<>();
    private List<Button> buttonList = new ArrayList<>();
    private boolean isPlaying = false;
    private boolean loaded = false;
    //TODO: wahre Url in Erfahrung bringen
    private String RadioUrl;
    private int selectionIndex = 0;

    protected void $define() {
        this.AppName("FMBG_App_Entwurf");
        this.Title("Radio");
        this.BackgroundColor(0xFF444444);
        Player1 = new Player(this);
        VerticalArrangement1 = new VerticalArrangement(this);
        VerticalArrangement1.BackgroundColor(0xFF444444);
        VerticalArrangement1.Height(-1100);
        VerticalArrangement1.Width(-1100);
        HorizontalArrangement1 = new HorizontalArrangement(VerticalArrangement1);
        HorizontalArrangement1.AlignHorizontal(3);
        HorizontalArrangement1.AlignVertical(2);
        HorizontalArrangement1.BackgroundColor(0x00FFFFFF);
        HorizontalArrangement1.Height(-1010);
        HorizontalArrangement1.Width(-1100);
        HorizontalArrangement2 = new HorizontalArrangement(VerticalArrangement1);
        HorizontalArrangement2.AlignHorizontal(3);
        HorizontalArrangement2.AlignVertical(2);
        HorizontalArrangement2.BackgroundColor(0xFFEA7D12);
        HorizontalArrangement2.Height(-1050);
        HorizontalArrangement2.Width(-1100);
        Button1 = new Image(HorizontalArrangement2);
        //Button1.BackgroundColor(0x00FFFFFF);
        Button1.Height(-1038);
        Button1.Width(-1050);
        Button1.Picture("play.png");;
        VerticalArrangement2 = new VerticalArrangement(VerticalArrangement1);
        VerticalArrangement2.BackgroundColor(0xFF444444);
        VerticalArrangement2.Height(-1040);
        VerticalArrangement2.Width(-1100);
        LetzteAudios = new Label(VerticalArrangement2);
        LetzteAudios.BackgroundColor(0xFF444444);
        LetzteAudios.Height(-1005);
        LetzteAudios.Width(-1100);
        LetzteAudios.Text("Letzte Audiodateien");
        LetzteAudios.TextColor(0xFFFFFFFF);
        LetzteAudios.FontSize(15.0f);
        VerticalScroll1 = new VerticalScrollArrangement(VerticalArrangement2);
        //RadioUrl = availableUrls.get(0);

        availableUrls.add("http://stream.radiosaw.de/stream.mp3");
        availableNames.add("Radio Sachsen-Anhalt-Welle");

        for(int i = 0; i<availableNames.size(); i++){
            Button temp = new Button(VerticalScroll1);
            temp.WidthPercent(100);
            temp.Text(availableNames.get(i));
            buttonList.add(temp);
        }
        EventDispatcher.registerEventForDelegation(this, "yeah", "Click");
    }
    public boolean dispatchEvent(Component component, String componentName, String eventName, Object[] params){
        if(component.equals(Button1) && eventName.equals("Click")){

            if(!loaded){
                Player1.Source(RadioUrl);
                loaded = true;
                Toast.makeText(this, "Livestream gefunden! \nPlay drücken, um Radio zu hören", Toast.LENGTH_LONG).show();
                return true;
            }


            if (isPlaying){
                isPlaying = false;
                Button1.Picture("play.png");
                Player1.Pause();
                return true;
            }

                isPlaying = true;
                Button1.Picture("pause.png");
                Player1.Start();
                return true;
        }



        int i = 0; for(Button button : buttonList){
            if(component.equals(button) && eventName.equals("Click")){
                RadioUrl = availableUrls.get(i);
            }
            i++;
        }

            return false;
        }



    }
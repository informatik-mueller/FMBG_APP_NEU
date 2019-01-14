package de.mueller104.informatik.dsbvertretung;
import com.google.appinventor.components.runtime.HandlesEventDispatching;
import com.google.appinventor.components.runtime.EventDispatcher;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.TextBox;
import com.google.appinventor.components.runtime.Button;
import com.google.appinventor.components.runtime.Label;
import com.google.appinventor.components.runtime.Clock;
import com.google.appinventor.components.runtime.File;
import android.content.Intent;
import android.os.StrictMode;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

import static android.widget.Toast.LENGTH_SHORT;

public class Login extends Form implements HandlesEventDispatching {
    private TextBox Benutzername;
    private TextBox Passwort;
    private Button LoginButton;
    private Button savedataButton;
    private Button getdataButton;
    private TextBox TextBox1;
    private Label Label2;
    private File File1;
    private String In = "";

    protected void $define() {
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        this.AppName("login");
        this.Title("Screen1");
        Benutzername = new TextBox(this);
        Benutzername.Hint("Benutzername");
        Passwort = new TextBox(this);
        Passwort.Hint("Passwort");
        LoginButton = new Button(this);
        LoginButton.Text("login");
        savedataButton = new Button(this);
        savedataButton.Text("save data");
        getdataButton = new Button(this);
        getdataButton.Text("get data");
        TextBox1 = new TextBox(this);
        TextBox1.Hint("Hint for TextBox1");
        Label2 = new Label(this);
        Label2.Text("Text for Label2");
        File1 = new File(this);

        try{
            File1.ReadFrom("file:///storage/emulated/0/beispiel.txt");
            TimeUnit.MILLISECONDS.sleep(500);
        }
        catch(Exception e){
        }
        if(!In.equals("")){
            startActivity(new Intent().setClass(this, MainMenu.class));
        }

        EventDispatcher.registerEventForDelegation(this, "ClickEvent", "Click" );
        EventDispatcher.registerEventForDelegation(this, "TimerEvent", "Timer" );
        EventDispatcher.registerEventForDelegation(this, "GotTextEvent", "GotText" );
    }
    public boolean dispatchEvent(Component component, String componentName, String eventName, Object[] params){
        if( component.equals(LoginButton) && eventName.equals("Click") ){
            LoginButtonClick();
            return true;
        }

        if(component.equals(File1) && eventName.equals("GotText")){
            In = (String)params[0];
        }

        return false;
    }
    public void LoginButtonClick(){
        if((String.valueOf(Passwort.Text()).compareTo(String.valueOf("schule1")) == 0) && (String.valueOf(Benutzername.Text()).compareTo(String.valueOf("Benutzername")) == 0)){
            File1.AppendToFile("USER_ANGEMELDET", "/fmbgo-config.txt");
            startActivity(new Intent().setClass(this, MainMenu.class));
        }
        else {
            Toast.makeText(this, "Falsche Anmeldedaten", LENGTH_SHORT).show();
        }
    } //end LoginButtonClick

}
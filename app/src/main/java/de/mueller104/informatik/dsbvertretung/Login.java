package de.mueller104.informatik.dsbvertretung;
import com.google.appinventor.components.runtime.HandlesEventDispatching;
import com.google.appinventor.components.runtime.EventDispatcher;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.HorizontalArrangement;
import com.google.appinventor.components.runtime.Image;
import com.google.appinventor.components.runtime.Label;
import com.google.appinventor.components.runtime.Notifier;
import com.google.appinventor.components.runtime.PasswordTextBox;
import com.google.appinventor.components.runtime.TableArrangement;
import com.google.appinventor.components.runtime.TextBox;
import com.google.appinventor.components.runtime.Button;
import com.google.appinventor.components.runtime.VerticalArrangement;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static android.widget.Toast.LENGTH_SHORT;

public class Login extends Form implements HandlesEventDispatching {
    private Notifier Alert;
    private boolean Gefragt = false;

    private VerticalArrangement VerticalArrangement1;
    private TableArrangement TableArrangement1;
    private HorizontalArrangement HorizontalArrangement6;
    private HorizontalArrangement HorizontalArrangement7;
    private HorizontalArrangement HorizontalArrangement8;
    private VerticalArrangement VerticalArrangement8;
    private Image Image1;
    private Label Erstmalige;
    private HorizontalArrangement HorizontalArrangement1;
    private VerticalArrangement VerticalArrangement2;
    private VerticalArrangement VerticalArrangement3;
    private VerticalArrangement VerticalArrangement5;
    private VerticalArrangement VerticalArrangement6;
    private HorizontalArrangement HorizontalArrangement4;
    private TextBox BenutzernameBox;
    private PasswordTextBox PasswortBox;
    private HorizontalArrangement HorizontalArrangement3;
    private Button LoginButton;
    private HorizontalArrangement HorizontalArrangement5;
    private VerticalArrangement VerticalArrangement7;
    private VerticalArrangement VerticalArrangement4;

    protected void $define() {
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        getWindow().setSoftInputMode(2); //Autofokus ausschalten

        this.AlignHorizontal(3);
        this.BackgroundColor(0xFF444444);


        Alert = new Notifier(this);

        VerticalArrangement1 = new VerticalArrangement(this);
        VerticalArrangement1.BackgroundColor(0x00FFFFFF);
        VerticalArrangement1.Height(-1100);
        VerticalArrangement1.Width(-1100);
        TableArrangement1 = new TableArrangement(VerticalArrangement1);
        TableArrangement1.Columns(3);
        TableArrangement1.Height(-1030);
        TableArrangement1.Width(-1100);
        HorizontalArrangement6 = new HorizontalArrangement(TableArrangement1);
        HorizontalArrangement6.BackgroundColor(0x00FFFFFF);
        HorizontalArrangement6.Column(0);
        HorizontalArrangement6.Height(-1025);
        HorizontalArrangement6.Width(-1010);
        HorizontalArrangement6.Row(1);
        HorizontalArrangement7 = new HorizontalArrangement(TableArrangement1);
        HorizontalArrangement7.BackgroundColor(0x00FFFFFF);
        HorizontalArrangement7.Column(2);
        HorizontalArrangement7.Height(-1025);
        HorizontalArrangement7.Width(-1010);
        HorizontalArrangement7.Row(1);
        HorizontalArrangement8 = new HorizontalArrangement(TableArrangement1);
        HorizontalArrangement8.BackgroundColor(0x00FFFFFF);
        HorizontalArrangement8.Column(1);
        HorizontalArrangement8.Height(-1005);
        HorizontalArrangement8.Width(-1080);
        HorizontalArrangement8.Row(0);
        VerticalArrangement8 = new VerticalArrangement(TableArrangement1);
        VerticalArrangement8.AlignHorizontal(3);
        VerticalArrangement8.AlignVertical(2);
        VerticalArrangement8.BackgroundColor(0xFFCC6600);
        VerticalArrangement8.Column(1);
        VerticalArrangement8.Height(-1025);
        VerticalArrangement8.Width(-1080);
        VerticalArrangement8.Row(1);
        Image1 = new Image(VerticalArrangement8);
        Image1.Height(-1015);
        Image1.Width(-1040);
        Image1.Picture("fmbg-logo-3.png");
        Erstmalige = new Label(VerticalArrangement8);
        Erstmalige.BackgroundColor(0xFFEA7D12);
        Erstmalige.FontItalic(true);
        Erstmalige.FontSize(11);
        Erstmalige.Height(-1008);
        Erstmalige.Width(-1070);
        Erstmalige.Text("Eine Anmeldung erfolgt nur bei erstmaliger Nutzung.");
        HorizontalArrangement1 = new HorizontalArrangement(VerticalArrangement1);
        HorizontalArrangement1.BackgroundColor(0x00FFFFFF);
        HorizontalArrangement1.Height(-1070);
        HorizontalArrangement1.Width(-1100);
        VerticalArrangement2 = new VerticalArrangement(HorizontalArrangement1);
        VerticalArrangement2.BackgroundColor(0x00FFFFFF);
        VerticalArrangement2.Height(-1070);
        VerticalArrangement2.Width(-1020);
        VerticalArrangement3 = new VerticalArrangement(HorizontalArrangement1);
        VerticalArrangement3.BackgroundColor(0x00FFFFFF);
        VerticalArrangement3.Height(-1070);
        VerticalArrangement3.Width(-1060);
        VerticalArrangement5 = new VerticalArrangement(VerticalArrangement3);
        VerticalArrangement5.BackgroundColor(0x00FFFFFF);
        VerticalArrangement5.Height(-1010);
        VerticalArrangement5.Width(-1060);
        VerticalArrangement6 = new VerticalArrangement(VerticalArrangement3);
        VerticalArrangement6.AlignHorizontal(3);
        VerticalArrangement6.BackgroundColor(0xFFCC6600);
        VerticalArrangement6.Height(-1045);
        VerticalArrangement6.Width(-1060);
        HorizontalArrangement4 = new HorizontalArrangement(VerticalArrangement6);
        HorizontalArrangement4.BackgroundColor(0x00FFFFFF);
        HorizontalArrangement4.Height(-1005);
        HorizontalArrangement4.Width(-1060);
        BenutzernameBox = new TextBox(VerticalArrangement6);
        BenutzernameBox.Height(-1010);
        BenutzernameBox.Width(-1050);
        BenutzernameBox.Hint("Benutzername");
        PasswortBox = new PasswordTextBox(VerticalArrangement6);
        PasswortBox.Height(-1010);
        PasswortBox.Width(-1050);
        PasswortBox.Hint("Passwort");
        HorizontalArrangement3 = new HorizontalArrangement(VerticalArrangement6);
        HorizontalArrangement3.BackgroundColor(0x00FFFFFF);
        HorizontalArrangement3.Height(-1005);
        HorizontalArrangement3.Width(-1060);
        LoginButton = new Button(VerticalArrangement6);
        LoginButton.BackgroundColor(0xFFFFFFFF);
        LoginButton.FontSize(16);
        LoginButton.FontTypeface(1);
        LoginButton.Height(-1010);
        LoginButton.Width(-1050);
        LoginButton.Text("Anmelden");
        HorizontalArrangement5 = new HorizontalArrangement(VerticalArrangement6);
        HorizontalArrangement5.BackgroundColor(0x00FFFFFF);
        HorizontalArrangement5.Height(-1005);
        HorizontalArrangement5.Width(-1060);
        VerticalArrangement7 = new VerticalArrangement(VerticalArrangement3);
        VerticalArrangement7.BackgroundColor(0x00FFFFFF);
        VerticalArrangement7.Height(-1015);
        VerticalArrangement7.Width(-1050);
        VerticalArrangement4 = new VerticalArrangement(HorizontalArrangement1);
        VerticalArrangement4.BackgroundColor(0x00FFFFFF);
        VerticalArrangement4.Height(-1070);
        VerticalArrangement4.Width(-1020);
;
        PermissionsAbfragen();
        //dataDir.mkdirs();
        readAutoLogin();

        EventDispatcher.registerEventForDelegation(this, "ClickEvent", "Click" );
    }

    private void PermissionsAbfragen() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE) && !Gefragt) {
                Alert.ShowMessageDialog("Es wird Zugriff auf das Dateisystem benötigt, um die Einstellungen für diese App speichern zu können", "Berechtigung", "Schließen");
                Gefragt = true;
            }
                else{ ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        1);
            }
        }
    }


    private void readAutoLogin() {
        SharedPreferences sharedPreferences = this.getSharedPreferences("login", Context.MODE_PRIVATE);
        boolean autoLogin = sharedPreferences.getBoolean("AutoLogin", false);
            if(autoLogin){
                startActivity(new Intent(this, MainMenu.class));
            }
    }

    private void enableAutoLogin() {
        SharedPreferences sharedPreferences = this.getSharedPreferences("login", Context.MODE_PRIVATE);
        Konfiguration config = new Konfiguration();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("AutoLogin", config.getAutoLogin());
        editor.commit();
        }

    public String md5(String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            for (int i=0; i<messageDigest.length; i++)
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));

            return hexString.toString();
        }catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public boolean dispatchEvent(Component component, String componentName, String eventName, Object[] params){
        if( component.equals(LoginButton) && eventName.equals("Click") ){
            LoginButtonClick();
            return true;
        }

        return false;
    }
    public void LoginButtonClick(){
        PermissionsAbfragen();
        if(BenutzernameBox.Text().equals("fmbg") && md5(PasswortBox.Text()).equals("bf2d9f31612873ca4cc918d8d6e467cf")){
            enableAutoLogin();
            Toast.makeText(this, "Autologin wurde aktiviert", LENGTH_SHORT).show();
            readAutoLogin();
        }
        else {
            Toast.makeText(this, "Falsche Anmeldedaten", LENGTH_SHORT).show();
        }
    } //end LoginButtonClick

}
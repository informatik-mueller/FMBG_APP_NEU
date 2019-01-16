package de.mueller104.informatik.dsbvertretung;

public class Konfiguration {
    private boolean AutoLogin;
    private String SonstigeEinstellung;

    public Konfiguration(){
        AutoLogin = true;
        SonstigeEinstellung = "nicht vorhanden";
    }

    public Konfiguration(boolean autologin){
        AutoLogin = autologin;
        SonstigeEinstellung = "nicht vorhanden";
    }

    public Konfiguration(boolean autologin, String sonstige){
        AutoLogin = autologin;
        SonstigeEinstellung = sonstige;
    }

    public boolean getAutoLogin(){
        return AutoLogin;
    }

    public String getSonstigeEinstellung(){
        return SonstigeEinstellung;
    }

    public void setAutoLogin(boolean autologin){
        AutoLogin = autologin;
    }

    public void setSonstigeEinstellung(String sonstige){
        SonstigeEinstellung = sonstige;
    }

}

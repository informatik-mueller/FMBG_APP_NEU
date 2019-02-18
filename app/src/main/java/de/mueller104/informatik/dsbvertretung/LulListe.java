package de.mueller104.informatik.dsbvertretung;

import com.google.appinventor.components.runtime.Clock;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.EventDispatcher;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.HandlesEventDispatching;
import com.google.appinventor.components.runtime.Label;
import com.google.appinventor.components.runtime.TableArrangement;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LulListe extends Form implements HandlesEventDispatching {
    private TableArrangement List;
    private Clock Timer1;
    private Label Csv;
    private Label Csv2;
    private FTPClient client;
    private List<String> fileNames;
    private String CsvTable;

    private static final String FtpServerUrl = "leobraguinski.bplaced.net";
    private static final String FtpBenutzer = "leonardobraguinski";
    private static final String FtpPasswort = "blabla";
    private static final String DateiName = "lehrer.csv";
    private static final String DateiPfad = "/lulListe";

    protected void $define(){
        Timer1 = new Clock(this);
        Timer1.TimerInterval(50);
        Timer1.TimerEnabled(true);
        List = new TableArrangement(this);
        List.WidthPercent(100);
        List.HeightPercent(100);
        List.Columns(2);
        List.Rows(20);
        Csv = new Label(List);
        Csv2 = new Label(List);
        Csv.Row(0);
        Csv.Column(0);
        Csv.TextAlignment(Label.ALIGNMENT_CENTER);
        Csv2.Row(0);
        Csv2.Column(1);
        EventDispatcher.registerEventForDelegation(this, "ok", "Timer");
    }

    public boolean dispatchEvent(Component component, String componentName, String eventName, Object[] params){
        if(component.equals(Timer1) && eventName.equals("Timer")){
            CsvTable = retrieveFTP();
            Timer1.TimerEnabled(false);
            Csv2.Text(CsvTable);
            return true;
        }
        return false;
    }

    protected String retrieveFTP(){
        String InStr = "";

        try{
            client = new FTPClient();
            client.connect(FtpServerUrl);
            if(!FTPReply.isPositiveCompletion(client.getReplyCode())){
                System.err.println("Etwas ist schief gelaufen..");
            }
            boolean success = client.login(FtpBenutzer, FtpPasswort);
            if(success) {
                FTPFile[] files = client.listFiles();
                fileNames = new ArrayList<>();
                for (FTPFile file : files) {
                    String name = file.getName();
                    if (!name.equals("...") && !name.equals("..") && !name.equals("")) {
                        fileNames.add(file.getName());
                    }
                }
                if(fileNames.contains(DateiName)){
                /*int i = 0;
                for (String name : fileNames){
                    if (name.equals(DateiName)){
                        break;
                    }
                    i++;
                }*/

                InputStream raw = client.retrieveFileStream(DateiName);
                Scanner scanner = new java.util.Scanner(raw).useDelimiter("\\A");
                if(scanner.hasNext()){
                    InStr = scanner.next();
                }

                }
                else InStr = "";
            }
            return InStr;
        }

        catch(IOException e){
            System.err.println(DateiName + " konnte nicht gelesen werden");
            e.printStackTrace();
            return null;
        }
    }
}

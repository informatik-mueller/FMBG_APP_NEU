package de.mueller104.informatik.dsbvertretung;

import com.google.appinventor.components.runtime.Clock;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.EventDispatcher;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.HandlesEventDispatching;
import com.google.appinventor.components.runtime.Label;
import com.google.appinventor.components.runtime.TableArrangement;
import com.google.appinventor.components.runtime.VerticalArrangement;
import com.google.appinventor.components.runtime.VerticalScrollArrangement;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LulListe extends Form implements HandlesEventDispatching {
    private VerticalArrangement MainParent;
    private VerticalScrollArrangement Scroll;
    private TableArrangement Table;
    private Clock Timer1;
    private Label Csv2;
    private FTPClient client;
    private List<String> fileNames;
    private Label ÜberschriftSpace;
    private Label Überschrift;
    private VerticalArrangement Heading1Layout;
    private VerticalArrangement Heading2Layout;
    private Label TableHeading1;
    private Label TableHeading1Space;
    private Label TableHeading2;
    private String CsvTable;

    private static final String FtpServerUrl = "leobraguinski.bplaced.net";
    private static final String FtpBenutzer = "leobraguinski";
    private static final String FtpPasswort = "blabla";
    private static final String DateiName = "lehrer.csv";
    private static final String DateiPfad = "/lulListe";
    private static final int AnzahlElemente = 50;

    public void $define(){
        this.ScreenOrientation("portrait");
        this.AlignHorizontal(3);

        Timer1 = new Clock(this);
        Timer1.TimerInterval(100);
        Timer1.TimerEnabled(true);

        MainParent = new VerticalArrangement(this);
        MainParent.AlignHorizontal(3);
        MainParent.WidthPercent(95);

        ÜberschriftSpace = new Label(MainParent);
        ÜberschriftSpace.HeightPercent(1);
        Überschrift = new Label(MainParent);
        Überschrift.FontSize(30.0f);
        Überschrift.Text("LuL-Liste");
        Überschrift.FontBold(true);
        Überschrift.HeightPercent(10);

        Scroll = new VerticalScrollArrangement(MainParent);
        Table = new TableArrangement(Scroll);
        Table.WidthPercent(100);
        Table.HeightPercent(100);
        Table.Columns(2);
        Table.Rows(AnzahlElemente);

        Heading1Layout = new VerticalArrangement(Table);
        Heading1Layout.WidthPercent(50);
        Heading1Layout.AlignHorizontal(3);
        Heading1Layout.Column(0);
        Heading1Layout.Row(0);

        TableHeading1 = new Label(Heading1Layout);
        TableHeading1.Text("Lehrer");
        TableHeading1.FontBold(true);
        TableHeading1.FontSize(20.0f);
        //TableHeading1Space = new Label(Heading1Layout);


        Heading2Layout = new VerticalArrangement(Table);
        Heading2Layout.WidthPercent(50);
        Heading2Layout.AlignHorizontal(3);
        Heading2Layout.Column(1);
        Heading2Layout.Row(0);

        TableHeading2 = new Label(Heading2Layout);
        TableHeading2.Text("Kürzel");
        TableHeading2.FontBold(true);
        TableHeading2.FontSize(20.0f);



        EventDispatcher.registerEventForDelegation(this, "ok", "Timer");
    }

    public boolean dispatchEvent(Component component, String componentName, String eventName, Object[] params){
        if(component.equals(Timer1) && eventName.equals("Timer")){
            CsvTable = retrieveFTP();
            Timer1.TimerEnabled(false);
            for(int i = 1; i<20+1; i++){
                VerticalArrangement vTemp1 = new VerticalArrangement(Table);
                vTemp1.AlignHorizontal(3);
                vTemp1.WidthPercent(50);
                vTemp1.Column(0);
                vTemp1.Row(i);

                Label temp = new Label(vTemp1);
                temp.Text("Lehrer");

                VerticalArrangement vTemp2 = new VerticalArrangement(Table);
                vTemp2.AlignHorizontal(3);
                vTemp2.WidthPercent(50);
                vTemp2.Column(1);
                vTemp2.Row(i);
                Label temp2 = new Label(vTemp2);
                temp2.Text("Kürzel");
            }
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
                return "";
            }

            boolean success = client.login(FtpBenutzer, FtpPasswort);
            if(success) {
                //client.changeWorkingDirectory(DateiPfad);
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

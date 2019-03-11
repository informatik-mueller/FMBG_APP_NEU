package de.mueller104.informatik.dsbvertretung;

import android.widget.Toast;

import com.google.appinventor.components.runtime.Clock;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.EventDispatcher;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.HandlesEventDispatching;
import com.google.appinventor.components.runtime.Label;
import com.google.appinventor.components.runtime.TableArrangement;
import com.google.appinventor.components.runtime.VerticalArrangement;
import com.google.appinventor.components.runtime.VerticalScrollArrangement;

import org.apache.commons.lang3.StringUtils;
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

    private static final String FtpServerUrl = "fmbg-berlin.de";
    private static final String FtpBenutzer = "web27549934f2";
    private static final String FtpPasswort = "OgXgPAAcT8ez0JaVsOU0NzfgSoplqlUy";
    private static final String DateiName = "lehrer.csv";
    private static final String DateiPfad = "/var/www/web27549934/html/appdata";
    //private static final String DateiPfad = "/appdata";
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
            String[] SplitTable = CsvTable.split(";|\\n|\\r");
            /*VerticalArrangement vTemp1 = new VerticalArrangement(Table);
            vTemp1.AlignHorizontal(3);
            vTemp1.WidthPercent(50);
            vTemp1.Column(0);
            vTemp1.Row(1);

            //Label temp = new Label(vTemp1);
            //temp.Text(CsvTable);

            VerticalArrangement vTemp2 = new VerticalArrangement(Table);
            vTemp1.AlignHorizontal(3);
            vTemp1.WidthPercent(50);
            vTemp1.Column(1);
            vTemp1.Row(1);*/


          for(int i = 1; i<SplitTable.length; i+=2){
                VerticalArrangement vTemp1 = new VerticalArrangement(Table);
                vTemp1.AlignHorizontal(3);
                vTemp1.WidthPercent(50);
                vTemp1.Column(0);
                vTemp1.Row(i);

                Label temp = new Label(vTemp1);
                temp.FontSize(16);
                temp.Text(SplitTable[i-1]);
                //temp.HeightPercent(2);

                VerticalArrangement vTemp2 = new VerticalArrangement(Table);
                vTemp2.AlignHorizontal(3);
                vTemp2.WidthPercent(50);
                vTemp2.Column(1);
                vTemp2.Row(i);
                Label temp2 = new Label(vTemp2);
                temp2.FontSize(16);
                temp2.Text(SplitTable[i]);
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
                //Toast.makeText(this, "Verbindung mit FTP-Server fehlgeschlagen", Toast.LENGTH_LONG).show();
                return "";
            }

            boolean success = client.login(FtpBenutzer, FtpPasswort);
            if(success) {
                client.changeWorkingDirectory(DateiPfad);
                //Toast.makeText(this, "FTP-Login erfolgreich", Toast.LENGTH_LONG).show();
                FTPFile[] files = client.listFiles();
                fileNames = new ArrayList<>();
                for (FTPFile file : files) {
                    String name = file.getName();
                    if (!name.equals("...") && !name.equals("..") && !name.equals("")) {
                        fileNames.add(file.getName());
                    }

                    if(fileNames.size() > 0) ;
                      //Toast.makeText(this, "Letzte gelistete Datei" + String.valueOf(fileNames.get(fileNames.size()-1)), Toast.LENGTH_SHORT).show();
                     //else Toast.makeText(this, "In dem Pfad gibt es keine Dateien", Toast.LENGTH_SHORT).show();
                }
                if(files.length == 0){
                    //Toast.makeText(this, "Es jibt keine Dateien", Toast.LENGTH_SHORT).show();
                }

                if(fileNames.contains(DateiName)){
                /*int i = 0;
                for (String name : fileNames){
                    if (name.equals(DateiName)){
                        break;
                    }
                    i++;
                }*/
               // Toast.makeText(this, "Lese lehrer.csv", Toast.LENGTH_LONG).show();
                InputStream raw = client.retrieveFileStream(DateiName);
                Scanner scanner = new java.util.Scanner(raw).useDelimiter("\\A");
                if(scanner.hasNext()){
                    InStr = scanner.next();
                }

                }
                else InStr = "";
            }
            else{
                Toast.makeText(this, "FTP-Login fehlgeschlagen", Toast.LENGTH_LONG).show();
            }
            return InStr;
        }

        catch(IOException e){
            Toast.makeText(this, "IO-Exception...", Toast.LENGTH_LONG).show();
            System.err.println(DateiName + " konnte nicht gelesen werden");
            e.printStackTrace();
            return null;
        }
    }
}

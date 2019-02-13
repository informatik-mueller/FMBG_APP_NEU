package de.mueller104.informatik.dsbvertretung;

import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.HandlesEventDispatching;
import com.google.appinventor.components.runtime.Label;
import com.google.appinventor.components.runtime.TableArrangement;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Scanner;

//TODO: Download aus dem Internet

public class LulListe extends Form implements HandlesEventDispatching {
    private TableArrangement List;
    private Label Csv;
    private Label Csv2;
    private Reader br;
    private String k;
    private static final String FtpServerUrl = "leobraguinski.bplaced.net";
    private FTPClient client;
    private FTPFile[] files;
    private static final String lulUrl = "/lulUrl";

    protected void $define(){
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


        try{
            client = new FTPClient();
            client.connect(FtpServerUrl);
                 if(!FTPReply.isPositiveCompletion(client.getReplyCode())){
                     System.err.println("Etwas ist schief gelaufen..");
                 }
                 boolean success = client.login("leobraguinski", "blabla");
                 if(success){
                     Csv.Text(client.listFiles("/")[2].getName());
                 }

            InputStream raw = this.getAssets().open("lehrer.csv");
            Scanner scanner = new java.util.Scanner(raw).useDelimiter("\\A");
            if(scanner.hasNext())
                k = scanner.next();
            else k = "";
        }
        catch(IOException e){
            System.err.println("lehrer.csv konnte nicht gelesen werden");
            e.printStackTrace();
        }
        //Csv.Text(k);
        Csv2.Text(k);

    }
}

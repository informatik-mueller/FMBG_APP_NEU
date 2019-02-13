package de.mueller104.informatik.dsbvertretung;

import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.HandlesEventDispatching;
import com.google.appinventor.components.runtime.Label;
import com.google.appinventor.components.runtime.TableArrangement;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;

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
    private static final String FtpServerUrl = "test.rebex.net";
    private FTPClient client;

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
            Csv.Text(client.getReplyString());
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

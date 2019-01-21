package de.mueller104.informatik.dsbvertretung;

import android.app.DownloadManager;
import android.net.Uri;
import android.os.Environment;

import com.google.appinventor.components.runtime.Button;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.EventDispatcher;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.HandlesEventDispatching;

public class Dokumente extends Form implements HandlesEventDispatching {

    private Button downloadButton;
    private Uri Url = Uri.parse("");

    protected void $define(){
        downloadButton = new Button(this);

        EventDispatcher.registerEventForDelegation(this, "moritzistserioes", "Click");
    }

    @Override
    public boolean dispatchEvent(Component component, String componentName, String eventName, Object[] args) {
        if(component.equals(downloadButton) && eventName.equals("Click")){
            //Hier die Aktion ausführen, die passieren soll, wenn man auf den Button klickt
            DownloadManager.Request r = new DownloadManager.Request(Url);

// This put the download in the same Download dir the browser uses
            r.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "fileName");

// When downloading music and videos they will be listed in the player
// (Seems to be available since Honeycomb only)
            r.allowScanningByMediaScanner();

// Notify user when download is completed
// (Seems to be available since Honeycomb only)
            r.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

// Start download
            DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
            dm.enqueue(r);
            return true;
        }
        return false;
    }


}

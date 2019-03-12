package de.mueller104.informatik.dsbvertretung;

import android.os.StrictMode;

import com.google.appinventor.components.runtime.Canvas;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.EventDispatcher;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.HandlesEventDispatching;
import com.google.appinventor.components.runtime.ImageSprite;

public class Haftbefehl extends Form implements HandlesEventDispatching {

    private Canvas Canvas1;
    private ImageSprite Haftbefehl;

    protected void $define(){
        getWindow().setSoftInputMode(2);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        this.AlignHorizontal(3);
        Canvas1 = new Canvas(this);
        Canvas1.HeightPercent(100);
        Canvas1.WidthPercent(100);
        Haftbefehl = new ImageSprite(Canvas1);
        Haftbefehl.Picture("haftbefehl.jpg");
        Haftbefehl.WidthPercent(100);
        Haftbefehl.HeightPercent(100);
        EventDispatcher.registerEventForDelegation(this, "ok", "Click");
    }
}

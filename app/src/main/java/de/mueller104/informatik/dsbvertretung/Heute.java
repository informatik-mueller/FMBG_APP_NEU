package de.mueller104.informatik.dsbvertretung;


import android.os.StrictMode;

import com.google.appinventor.components.runtime.ActivityStarter;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.HandlesEventDispatching;
import com.google.appinventor.components.runtime.Label;
import com.google.appinventor.components.runtime.Sharing;

public class Heute extends Form implements HandlesEventDispatching {
        private ActivityStarter hi;
        private Sharing sh;

        public void $define(){
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build()); //comment

            hi = new ActivityStarter(this);
            hi.Action("android.intent.action.VIEW");
            hi.DataType("application/pdf");
            hi.DataUri("file:///storage/emulated/0/Download/1986459.pdf");
            hi.StartActivity();
        }

}

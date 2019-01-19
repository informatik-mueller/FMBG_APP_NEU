package de.mueller104.informatik.dsbvertretung;

public class Papierkorb {

    //Permissions abfragen

    /*private void PermissionsAbfragen() {
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
    }*/
}

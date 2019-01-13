package de.mueller104.informatik.dsbvertretung;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class VertretungViewer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebView  webview = findViewById(R.id.wrap);
        //webview.getSettings().setJavaScriptEnabled(true);
        String vertretungUrl = getIntent().getStringExtra("url");
        String activityTitle = getIntent().getStringExtra("titel");
        setTitle(activityTitle);
        webview.loadUrl(vertretungUrl);
    }
}

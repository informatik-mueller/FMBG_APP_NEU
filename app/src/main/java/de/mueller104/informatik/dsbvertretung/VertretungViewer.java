package de.mueller104.informatik.dsbvertretung;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.SearchView;


public class VertretungViewer extends AppCompatActivity {

    private WebView webview;

    private SearchView.OnQueryTextListener Handler = new SearchView.OnQueryTextListener(){

        @Override
        public boolean onQueryTextSubmit(String query){
            return false;
        }

        @Override
        public boolean onQueryTextChange(String text){
            search(text);
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.vertretung);
        LinearLayout bg = findViewById(R.id.linearLayout);
        bg.setBackgroundColor(Color.WHITE);
        webview = findViewById(R.id.web);
        webview.setInitialScale(1);

        WebSettings webSettings = webview.getSettings();
        webSettings.setUseWideViewPort(true);
        webSettings.setJavaScriptEnabled(false);
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);

        SearchView searchview = findViewById(R.id.search);
        searchview.setOnQueryTextListener(Handler);
        setTitleColor(0xFF444444);

        String vertretungUrl = getIntent().getStringExtra("url");
        String activityTitle = getIntent().getStringExtra("titel");
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#585858'>" + activityTitle + "</font>"));
        webview.loadUrl(vertretungUrl);
    }

    public void search(String text){
        //setTitle(text);
        webview.findAllAsync(text);
    }
}

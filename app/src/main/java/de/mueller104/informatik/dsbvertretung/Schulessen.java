package de.mueller104.informatik.dsbvertretung;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.SearchView;

public class Schulessen extends AppCompatActivity {

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

        protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.vertretung);
            LinearLayout bg = findViewById(R.id.linearLayout);
            bg.setBackgroundColor(Color.WHITE);
            webview = findViewById(R.id.web);
            WebSettings webSettings = webview.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webSettings.setSupportZoom(true);
            webSettings.setBuiltInZoomControls(true);
            webSettings.setDisplayZoomControls(false);

            SearchView searchview = findViewById(R.id.search);
            searchview.setOnQueryTextListener(Handler);

            String schulessenUrl = getIntent().getStringExtra("url");
            setTitle("Schulessen - Internet Bestellsystem");
            webview.loadUrl(schulessenUrl);
        }

    public void search(String text){
        //setTitle(text);
        webview.findAllAsync(text);
    }

}

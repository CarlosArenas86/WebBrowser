package co.arenascarlos.www.webbrowser;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    ProgressBar superProgressBar;
    ImageView superImageView;
    WebView superWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        superProgressBar = findViewById(R.id.myProgressBar);
        superImageView = findViewById(R.id.myImageView);
        superWebView = findViewById(R.id.myWebView);

        //set the max percentage.
        superProgressBar.setMax(100);

        // set up the webview with google.com
        superWebView.loadUrl("https://www.google.com");

        // add js to the webView
        superWebView.getSettings().setJavaScriptEnabled(true);

        //permition to open that url
        superWebView.setWebViewClient(new WebViewClient());

        //set up chrome client
        superWebView.setWebChromeClient(new WebChromeClient(){

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                superProgressBar.setProgress(newProgress);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                getSupportActionBar().setTitle(title);
            }

            @Override
            public void onReceivedIcon(WebView view, Bitmap icon) {
                super.onReceivedIcon(view, icon);
                superImageView.setImageBitmap(icon);
            }
        });

    }

   //method to go back and don't close the app
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        if (superWebView.canGoBack()){
            superWebView.goBack();
        }
        else {
            finish();
        }
    }
}























package co.arenascarlos.www.webbrowser;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ProgressBar superProgressBar;
    ImageView superImageView;
    WebView superWebView;

    LinearLayout superLinearLayout;
    String myURL;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        superLinearLayout = findViewById(R.id.LinearLayout);
        superProgressBar = findViewById(R.id.ProgressBar);
        superImageView = findViewById(R.id.ImageView);
        superWebView = findViewById(R.id.WebView);


        //set the max percentage.
        superProgressBar.setMax(100);

        // set up the webview with google.com
        superWebView.loadUrl("https://www.google.com");

        // add js to the webView
         superWebView.getSettings().setJavaScriptEnabled(true);

        //permission to open that url
        superWebView.setWebViewClient(new WebViewClient() {


            //method to received error.
//            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
//
//                EditText t = (EditText) findViewById(R.id.url);
//                t.setText("Errror " + failingUrl);
//                superWebView.loadUrl("https://www.google.co.nz/search?q=youtube&rlz=1C5CHFA_enNZ814NZ814&oq="
//                        + failingUrl + "&aqs=chrome..69i57j69i60l5.2511j0j4&sourceid=chrome&ie=UTF-8");
//
//            }
//
//            public void onPageFinished(WebView view, String url) {
//                // do your stuff here
//
//            }
        });

        //set up chrome client
        superWebView.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                superProgressBar.setProgress(newProgress);

            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                getSupportActionBar();
            }

            @Override
            public void onReceivedIcon(WebView view, Bitmap icon) {
                super.onReceivedIcon(view, icon);
                superImageView.setImageBitmap(icon);
            }


        });

        superWebView =  findViewById(R.id.WebView);
        superWebView.setWebViewClient(new WebViewClient());
       // superWebView.getSettings().setJavaScriptEnabled(true);
        superWebView.loadUrl(myURL);


        Button btn =  findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText t =  findViewById(R.id.myURL);

                String add = adjustURl(t.getText().toString());


               superWebView.loadUrl(add);
            }
        });

    }

    public String adjustURl(String myCurrentUrl) {
        return myCurrentUrl;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.super_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.menu_back:
                onBackPressed();
                break;

            case R.id.menu_forward:
                onForwarsPressed();
                break;

            case R.id.menu_refresh:
                superWebView.reload();
                break;

            case R.id.menu_share:
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, myURL);
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Copied URL");
                startActivity(Intent.createChooser(shareIntent, "Share URL with Friends"));

        }

        return super.onOptionsItemSelected(item);
    }

    private void onForwarsPressed() {
        if (superWebView.canGoForward()) {
            superWebView.goForward();
        } else {
            Toast.makeText(this, "can't go forward!", Toast.LENGTH_SHORT).show();
        }

    }

    //method to go back and don't close the app
    @Override
    public void onBackPressed() {

        if (superWebView.canGoBack()) {
            superWebView.goBack();
        } else {
            finish();
        }
    }
}






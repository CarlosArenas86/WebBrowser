package co.arenascarlos.www.webbrowser;

/*
* 1. al escribir la direccion en el edittext y darle click al boton, salta a la pagina correctamente
*pero al ingresar a youtube y darle click a algun video, no puedo ver la reproduccion del video,
*  y el cuadro para ingresar otra direccion URL se me va se pierde.
*
* 2. como puedo hacerla compatible con otros dispositivos moviles? cree en la carpeta res, tres nuevos directorios
* con nombres "layout-small, layout-large y layout-xlarge" mi pero no las puedo ver, el sistema no me las muestra,
* mi idea es copiar los layout en esas carpetas y hacerlas compatibles con dispositivos small, large y xtralarge.
*
* 3. los botones de arriba de la app funcionan bien pero cree un boton de bookmark que no lo hago funcionar aun porque
* no se como guardar listas del historial de link navegados, mi idea es que el usuario haga click en ese boton de bookmark
* y que la pagina que esta en el edittext de URL se guarde como lista y se pueda ver..
*
*
* */

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
    String myCurrentUrl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        superLinearLayout = findViewById(R.id.myLinearLayout);
        superProgressBar = findViewById(R.id.myProgressBar);
        superImageView = findViewById(R.id.myImageView);
        superWebView = findViewById(R.id.myWebView);


        //set the max percentage.
        superProgressBar.setMax(100);

        // set up the webview with google.com
        superWebView.loadUrl("https://www.google.com");

        // add js to the webView
      //  superWebView.getSettings().setJavaScriptEnabled(true);

        //permition to open that url
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
                getSupportActionBar().setTitle(title);
            }

            @Override
            public void onReceivedIcon(WebView view, Bitmap icon) {
                super.onReceivedIcon(view, icon);
                superImageView.setImageBitmap(icon);
            }


        });

        superWebView = (WebView) findViewById(R.id.myWebView);
        superWebView.setWebViewClient(new WebViewClient());
        superWebView.getSettings().setJavaScriptEnabled(true);
        superWebView.loadUrl(myCurrentUrl);


        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText t = (EditText) findViewById(R.id.myURL);


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
                shareIntent.putExtra(Intent.EXTRA_TEXT, myCurrentUrl);
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























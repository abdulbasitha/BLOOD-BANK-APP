package myl.bloodbank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class AdminWeb extends AppCompatActivity {

    WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_web);

        web=(WebView) findViewById(R.id.web);
        web.getSettings().setJavaScriptEnabled(true);
        web.getSettings().setDomStorageEnabled(true);
        web.getSettings().getLoadsImagesAutomatically();
        web.setWebChromeClient(new WebChromeClient());
        // admin web url like http://localhost/AppDept/login.php
        web.loadUrl("");


        web.setWebViewClient(new WebViewClient(){
            public boolean shouldOverrideUrlLoading(WebView view){
                // admin web url like http://localhost/AppDept/login.php
                view.loadUrl("");
                return false;

            }
        });

    }


    @Override
    public void onBackPressed() {
        if (web.copyBackForwardList().getCurrentIndex() > 0) {
            web.goBack();
        }
        else {
            // Your exit alert code, or alternatively line below to finish
            //super.onBackPressed(); // finishes activity
            Intent back= new Intent(getApplicationContext(),MainActivity.class);
            startActivity(back);
            finish();
        }
    }


}

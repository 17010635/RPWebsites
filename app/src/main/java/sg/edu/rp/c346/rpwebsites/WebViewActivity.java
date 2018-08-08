package sg.edu.rp.c346.rpwebsites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewActivity extends AppCompatActivity {


    WebView wvMyPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        wvMyPage=findViewById(R.id.wvItem);

        wvMyPage.setWebViewClient(new WebViewClient());

        Intent intentReceived = getIntent();
        String url = intentReceived.getStringExtra("url");

        wvMyPage.loadUrl(url);

    }
}

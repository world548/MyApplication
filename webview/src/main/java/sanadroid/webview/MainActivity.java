package sanadroid.webview;




import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {
    EditText edtUr1;
    Button btnGo,btnBack;
    WebView web;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtUr1 = (EditText)findViewById(R.id.edtUr1);
        btnGo = (Button)findViewById(R.id.btnGo);
        btnBack = (Button)findViewById(R.id.btnBack);
        web = (WebView)findViewById(R.id.webView1);

        web.setWebViewClient(new CookWebViewClient());

        WebSettings webSet = web.getSettings();
        webSet.setBuiltInZoomControls(true);


        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtUr1.getText().toString() != "http://*")
                    web.loadUrl("http://" + edtUr1.getText().toString());
                else if (edtUr1.getText().toString() == "http://*")
                    web.loadUrl(edtUr1.getText().toString());
                //edtUr1.setText(v.getRootView().toString());
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                web.goBack();
            }
        });
        web.setWebViewClient(new CookWebViewClient());
    }

    class CookWebViewClient extends WebViewClient{

        public void onPageFinished(WebView view,String url){
            super.onPageFinished(view,url);
            view.getRootView();
            edtUr1.setText(url);
        }

        public boolean shouldOverrideUrlLoading(WebView view,String url) {
            view.loadUrl(url);
            return false;
        }


    }
}

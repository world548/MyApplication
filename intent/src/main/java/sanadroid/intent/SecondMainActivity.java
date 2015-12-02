package sanadroid.intent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SecondMainActivity extends AppCompatActivity {

    RadioGroup Rgroup;
    RadioButton radioP;
    int hapValue;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondmain);
        setTitle("Second 액티비티");
        int check=0;
        Intent inIntent = getIntent();
        check = inIntent.getIntExtra("Check",0);
        if(check==1)
            hapValue = inIntent.getIntExtra("Num1",0) + inIntent.getIntExtra("Num2",0);
        else if(check==2)
            hapValue = inIntent.getIntExtra("Num1",0) - inIntent.getIntExtra("Num2",0);
        else if(check==3)
            hapValue = inIntent.getIntExtra("Num1",0) * inIntent.getIntExtra("Num2",0);
        else if(check==4)
            hapValue = inIntent.getIntExtra("Num1",0) / inIntent.getIntExtra("Num2",0);
       // hapValue = inIntent.getIntExtra("Num1",0) + inIntent.getIntExtra("Num2",0);

        Button btnReturn = (Button) findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent outIntent = new Intent(getApplicationContext(),MainActivity.class);
                outIntent.putExtra("Hap", hapValue);
                setResult(RESULT_OK, outIntent);
                finish();
            }
        });
    }
}

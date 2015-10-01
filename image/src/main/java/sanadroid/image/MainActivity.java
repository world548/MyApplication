package sanadroid.image;

import android.app.Activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity{
    TextView text1,text2;
    Switch chkAgree;
    RadioGroup rGroup1;
    RadioButton rButton1,rButton2,rButton3;
    Button ExitButton,StartButton;
    ImageView imgIC;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("안드로이드 사진보기");

        text1 = (TextView)findViewById(R.id.Text1);
        chkAgree = (Switch) findViewById(R.id.ChkAgree);

        text2= (TextView) findViewById(R.id.Text2);
        rGroup1=(RadioGroup) findViewById(R.id.rGroup1);
        rButton1=(RadioButton) findViewById(R.id.rButton1);
        rButton2=(RadioButton) findViewById(R.id.rButton2);
        rButton3=(RadioButton) findViewById(R.id.rButton3);

        ExitButton=(Button) findViewById(R.id.ExitButton);
        StartButton=(Button) findViewById(R.id.StartButton);
        imgIC = (ImageView) findViewById(R.id.ImgIC);

        chkAgree.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                if (chkAgree.isChecked() == true) {
                    text2.setVisibility(View.VISIBLE);
                    rGroup1.setVisibility(View.VISIBLE);
                    ExitButton.setVisibility(View.VISIBLE);
                    StartButton.setVisibility(View.VISIBLE);
                    imgIC.setVisibility(View.VISIBLE);
                } else {
                    text2.setVisibility(View.INVISIBLE);
                    rGroup1.setVisibility(View.INVISIBLE);
                    ExitButton.setVisibility(View.INVISIBLE);
                    StartButton.setVisibility(View.INVISIBLE);
                    imgIC.setVisibility(View.INVISIBLE);
                }
            }
        });



        ExitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        StartButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                text2.setVisibility(View.INVISIBLE);
                rGroup1.setVisibility(View.INVISIBLE);
                ExitButton.setVisibility(View.INVISIBLE);
                StartButton.setVisibility(View.INVISIBLE);
                imgIC.setVisibility(View.INVISIBLE);
                chkAgree.setChecked(false);
                rGroup1.clearCheck();
            }
        });



    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClick(View v){
        switch(rGroup1.getCheckedRadioButtonId()){
            case R.id.rButton1:
                imgIC.setImageResource(R.drawable.jelleybean);
                break;
            case R.id.rButton2:
                imgIC.setImageResource(R.drawable.kit);
                break;
            case R.id.rButton3:
                imgIC.setImageResource(R.drawable.pop);
                break;
            default:
                Toast.makeText(getApplicationContext(),"버전을 선택하세요",Toast.LENGTH_SHORT).show();

        }
    }

}

package sanadroid.intent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RadioGroup Rgroup;
    int check;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("메인 엑티비티");
        Rgroup = (RadioGroup) findViewById(R.id.Rgroup);
        Button btnMewActivity = (Button) findViewById(R.id.btnNewActivity);
        btnMewActivity.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                switch(Rgroup.getCheckedRadioButtonId()){
                    case R.id.radioPlus:
                        check=1;
                        break;
                    case R.id.raiodSub:
                        check=2;
                        break;
                    case R.id.radioMul:
                        check=3;
                        break;
                    case R.id.radioDiv:
                        check=4;
                        break;
                }
                EditText edtNum1 = (EditText) findViewById(R.id.edtNum1);
                EditText edtNum2 = (EditText) findViewById(R.id.edtNum2);
                Intent intent = new Intent(getApplicationContext(), SecondMainActivity.class);
                intent.putExtra("Num1",Integer.parseInt(edtNum1.getText().toString()));
                intent.putExtra("Num2",Integer.parseInt(edtNum2.getText().toString()));
                intent.putExtra("Check",check);
                startActivityForResult(intent,0);

            }
        });

    }
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        if(resultCode == RESULT_OK){
            int hap = data.getIntExtra("Hap",0);
            Toast.makeText(getApplicationContext(), "결과 값 :" + hap, Toast.LENGTH_SHORT).show();
        }
    }

}

package sanadroid.calc;



import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {
    EditText edit1,edit2;
    Button btnAdd,btnSub,btnMul,btnDiv,btnRev;
    TextView textResult;
    String num1,num2;
    Double result;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("초간단 계산기(수정)");

        edit1=(EditText) findViewById(R.id.Edit1);
        edit2=(EditText) findViewById(R.id.Edit2);
        btnAdd=(Button) findViewById(R.id.btnAdd);
        btnSub=(Button) findViewById(R.id.BtnSub);
        btnDiv=(Button) findViewById(R.id.BtnDiv);
        btnMul=(Button) findViewById(R.id.BtnMul);
        btnRev=(Button) findViewById(R.id.BtnReV);
        textResult=(TextView) findViewById(R.id.TextResult);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num1=edit1.getText().toString();
                num2=edit2.getText().toString();
                if(num1.equals("") || num2.equals(""))
                    Toast.makeText(getApplicationContext(), "num값을 입력하세요",Toast.LENGTH_SHORT).show();
                else {
                    result = Double.parseDouble(num1) + Double.parseDouble(num2);
                    textResult.setText("계산 결과 : " + result.toString());
                }
            }
        });
        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num1=edit1.getText().toString();
                num2=edit2.getText().toString();
                if(num1.equals("") || num2.equals(""))
                    Toast.makeText(getApplicationContext(), "num값을 입력하세요",Toast.LENGTH_SHORT).show();
                else {
                    result = Double.parseDouble(num1) - Double.parseDouble(num2);
                    textResult.setText("계산 결과 : " + result.toString());
                }
            }
        });
        btnMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num1=edit1.getText().toString();
                num2=edit2.getText().toString();
                if(num1.equals("") || num2.equals(""))
                    Toast.makeText(getApplicationContext(), "num값을 입력하세요",Toast.LENGTH_SHORT).show();
                else {
                    result = Double.parseDouble(num1) * Double.parseDouble(num2);
                    textResult.setText("계산 결과 : " + result.toString());
                }
            }
        });
        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num1=edit1.getText().toString();
                num2=edit2.getText().toString();
                if(num1.equals("") || num2.equals(""))
                    Toast.makeText(getApplicationContext(), "num값을 입력하세요",Toast.LENGTH_SHORT).show();
                else if (Double.parseDouble(num1)==0 || Double.parseDouble(num2)==0)
                {
                    Toast.makeText(getApplicationContext(), "0으로 나눌수가 없습니다.",Toast.LENGTH_SHORT).show();
                }
                else {
                    result = Double.parseDouble(num1) / Double.parseDouble(num2);
                    textResult.setText("계산 결과 : " + result.toString());
                }
            }
        });
        btnRev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num1=edit1.getText().toString();
                num2=edit2.getText().toString();
                if(num1.equals("") || num2.equals(""))
                    Toast.makeText(getApplicationContext(), "num값을 입력하세요",Toast.LENGTH_SHORT).show();
                else if (Double.parseDouble(num1)==0 || Double.parseDouble(num2)==0)
                {
                    Toast.makeText(getApplicationContext(), "0으로 나눌수가 없습니다.",Toast.LENGTH_SHORT).show();
                }
                else {
                    result = Double.parseDouble(num1) % Double.parseDouble(num2);
                    textResult.setText("계산 결과 : " + result.toString());
                }
            }
        });
    }


}
package sanadroid.sqlite;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
    myDBHelper myHelper;
    EditText edtName,edtNumber,edtNameResult,edtNumberResult;
    Button btnInit,btnInput,btnView,btnUpdate,btnDelete;
    SQLiteDatabase sqlDB;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("가수 그룹 관리 DB");

        edtName = (EditText) findViewById(R.id.edtName);
        edtNumber =(EditText)findViewById(R.id.edtmum);
        edtNameResult = (EditText) findViewById(R.id.edtNameResult);
        edtNumberResult = (EditText) findViewById(R.id.edtNumResult);
        btnInit =(Button) findViewById(R.id.btnInit);
        btnInput =(Button) findViewById(R.id.btnInput);
        btnView =(Button)findViewById(R.id.btnView);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnUpdate =(Button) findViewById(R.id.btnUpdate);


        myHelper = new myDBHelper(this);
        btnInit.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                sqlDB = myHelper.getWritableDatabase();
                myHelper.onUpgrade(sqlDB,1,2);
                sqlDB.close();
            }
        });

        btnInput.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                sqlDB = myHelper.getWritableDatabase();
                sqlDB.execSQL("INSERT INTO groupTBL VALUES('" + edtName.getText().toString() + "' , '" + edtNumber.getText().toString() + "');");
                sqlDB.close();
                Toast.makeText(getApplicationContext(), "입력됨", Toast.LENGTH_SHORT).show();
            }
        });

        btnView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                sqlDB = myHelper.getReadableDatabase();
                Cursor cursor;
                cursor = sqlDB.rawQuery("SELECT * FROM groupTBL;",null);

                String strNames = "그룹 이름" + "\r\n"+"________"+"\r\n";
                String strNumbers = "인원" + "\r\n"+"________"+"\r\n";

                while(cursor.moveToNext()){
                    strNames += cursor.getString(0)+"\r\n";
                    strNumbers += cursor.getString(1) +"\r\n";
                }

                edtNameResult.setText(strNames);
                edtNumberResult.setText(strNumbers);

                cursor.close();
                sqlDB.close();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                sqlDB = myHelper.getWritableDatabase();
                sqlDB.execSQL("UPDATE groupTBL SET gNumber="
                        + edtNumber.getText().toString() + " WHERE gName=\""
                        + edtName.getText().toString() + "\";");
                sqlDB.close();
                Toast.makeText(getApplicationContext(), "수정 완료", Toast.LENGTH_SHORT).show();
            }
        });
       btnDelete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                sqlDB = myHelper.getWritableDatabase();
                sqlDB.execSQL("DELETE FROM groupTBL WHERE gName=\""
                        + edtName.getText().toString() + "\";");
                sqlDB.close();
                Toast.makeText(getApplicationContext(), "삭제 완료", Toast.LENGTH_SHORT).show();
            }
        });



    }
    public class myDBHelper extends SQLiteOpenHelper{
        public myDBHelper(Context context){
            super(context,"groupDB",null,1);
        }
        public void onCreate(SQLiteDatabase db){
            db.execSQL("CREATE TABLE groupTBL ( gName CHAR(20) PRIMARY KEY,gNumber INTEGER);");
        }
        public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
            db.execSQL("DROP TABLE IF EXISTS groupTBL");
            onCreate(db);
        }
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
}

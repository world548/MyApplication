package sanadroid.diaryproject;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.content.DialogInterface;
import android.widget.Toast;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    TextView datetext;
    String fileName;
    Button btnwtn;
    EditText edtDiary;
    DatePickerDialog dp;
    File datefile;
    String strSDpath;
    String setdatetext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("다이어리"); // 제목

       Calendar cal = Calendar.getInstance(); //calender에서 년,월,일을 받아옴
        final int cYear = cal.get(Calendar.YEAR);
        final int cMonth = cal.get(Calendar.MONTH);
        final int cDay = cal.get(Calendar.DAY_OF_MONTH);
        dp =new DatePickerDialog(this,this,cYear,cMonth,cDay); //DatePickerDialog

        datetext= (TextView)findViewById(R.id.datetext); //날짜텍스트
        btnwtn = (Button)findViewById(R.id.btnwtn);      // 저장버튼
        edtDiary =(EditText)findViewById(R.id.editDiary); //다이어리내용 editText
        //초기화면에 현재 날짜 출력
        datetext.setText(String.format("%d년 " + "%d월 " + "%d일", cYear, cMonth + 1, cDay));

        //날짜 텍스트 터치시 DatePickDialog가 보여짐
        datetext.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View arg0, MotionEvent arg1) {
                dp.show();
                edtDiary.setText("");//저장후 공백
                return false;
            }
        });

        strSDpath = Environment.getExternalStorageDirectory().getAbsolutePath(); //저장경로
        final File file = new File(strSDpath+"/mydiary");   //저장경로+mydiary디렉토리 경로
        //저장 버튼 클릭하면 파일 생성
        btnwtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                // 디렉토리가 존재하지 않으면 디렉토리 생성
                if ( !file.exists() ) {
                    file.mkdir();
                    Toast.makeText(getApplicationContext(),"디렉토리생성",Toast.LENGTH_SHORT).show();
                }
                    try {
                        datefile = new File(strSDpath + "/mydiary/" + fileName); //년_월_일.txt로 된 파일이 디렉토리 경로에 생성
                        FileOutputStream outFs = new FileOutputStream(datefile); //파일입력스트림
                        String str = edtDiary.getText().toString();  //다이어리 내용에 있는 문자열을 저장
                        outFs.write(str.getBytes()); // 파일에 다이어리내용을 저장
                        outFs.close();
                        Toast.makeText(getApplicationContext(), datetext.getText() + " 일기가 저장 되었습니다.", Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        Toast.makeText(getApplicationContext(), "오류 발생", Toast.LENGTH_SHORT).show();
                    } catch (NullPointerException e) { //null인 경우
                        Toast.makeText(getApplicationContext(), "NULL", Toast.LENGTH_SHORT).show();
                    }
                }
        });
    }
    //DatePickerDialog
    public void onDateSet(DatePicker view,int year,int monthOfYear,int datOfMonth){
        datetext.setText(String.format("%d년 " + "%d월 " + "%d일", year, monthOfYear + 1, datOfMonth));  //DatePickerDialog에 선택된 날짜로 날짜 텍스트를 변경
        fileName =Integer.toString(year)+"년_"+Integer.toString(monthOfYear+1)+"월_"+Integer.toString(datOfMonth)+"일.txt"; //DatePickerDialog에 선택된 날짜로 파일형식 설정 년_월_일.txt
    }
    @Override
    //메뉴
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1, menu);
        return true;
    }

   @Override
    public boolean onOptionsItemSelected(MenuItem item)  {
       String diaryStr= null;
       int id = item.getItemId();
       //메인에서 id 값을 받아서 메뉴 화면 출력
       //다시 읽기인 경우
       if (id == R.id.reread) {
           try{
                 FileInputStream inFs = new FileInputStream(strSDpath+"/mydiary/"+fileName); //해당날짜에 저장된 파일이 있는 경우 기존에 저장된 파일을 불러옴: 파일출력스트림
                 byte[] txt = new byte[200];
                 inFs.read(txt);              //파일 내용을 문자에 저장
                  diaryStr=(new String(txt)).trim();
                  edtDiary.setText(diaryStr);   //다이어리내용에 저장된 파일 내용을 보여줌
                  inFs.close();
            }catch(IOException e){ //해당날짜에 저장된 파일이 없는 경우
               Toast.makeText(getApplicationContext(),datetext.getText() + " 일기가 없습니다",Toast.LENGTH_SHORT).show();
          }
        }
       //제거인 경우
       else if(id==R.id.remove){
            //Dialog출력
            final AlertDialog.Builder removedialog = new AlertDialog.Builder(this);
            setdatetext = datetext.getText().toString(); //파일명과 날짜텍스트 비교를 위한 날짜텍스트 문자열 변환
            setdatetext=setdatetext.replace(" ","_")+".txt";
            removedialog.setMessage(datetext.getText() + " 일기를 삭제하시겠습니까?");
            removedialog.setPositiveButton("제거", new DialogInterface.OnClickListener() { //제거를 누른경우
                public void onClick(DialogInterface dialog, int item) {
                   if(datefile.getName().equals(setdatetext)) { //제거를 위해 파일명과 날짜텍스트를 비교
                        datefile.delete(); //해당날짜에 파일이 있는 경우 제거
                       Toast.makeText(getApplicationContext(), datetext.getText() + " 일기가 제거 되었습니다.", Toast.LENGTH_SHORT).show();
                   }
                    else //해당날짜에 파일이 없는 경우
                       Toast.makeText(getApplicationContext(),  datetext.getText() + " 일기가 없습니다.", Toast.LENGTH_SHORT).show();
                }
            });
           //취소버튼
            removedialog.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int item) {
                }
            });
           removedialog.show();
        }
       //글씨 크기
       else if(id==R.id.fontbig) {
           edtDiary.setTextSize(30); //글씨 크기 크게
           Toast.makeText(getApplicationContext(), "글씨 30", Toast.LENGTH_SHORT).show();
       }
       else if(id==R.id.fontmiddle) {
           edtDiary.setTextSize(20); //글씨 크기 중간
           Toast.makeText(getApplicationContext(), "글씨 20", Toast.LENGTH_SHORT).show();
       }
       else if(id==R.id.fontsmall) {
           edtDiary.setTextSize(10); //글씨 크기 작게
           Toast.makeText(getApplicationContext(), "글씨 10", Toast.LENGTH_SHORT).show();
       }
       return false;
    }
}

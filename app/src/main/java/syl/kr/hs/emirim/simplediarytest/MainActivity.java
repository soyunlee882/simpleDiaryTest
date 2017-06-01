package syl.kr.hs.emirim.simplediarytest;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    DatePicker date;
    EditText edit;
    Button but;
    String fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        date = (DatePicker)findViewById(R.id.date_pick);
        edit = (EditText) findViewById(R.id.edit);
        but = (Button) findViewById(R.id.but);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FileOutputStream fOut = openFileOutput(fileName, Context.MODE_PRIVATE);
                    String str=edit.getText().toString();
                    fOut.write(str.getBytes());
                    fOut.close();
                    Toast.makeText(MainActivity.this,"정삭적으로"+fileName+"파일이 저장되었습니다.",Toast.LENGTH_LONG).show();

                }catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        //캔린더 설정시 java용으로 설정
        Calendar cal=Calendar.getInstance();
        int year=cal.get(Calendar.YEAR);
        int month=cal.get(Calendar.MONTH);
        int day=cal.get(Calendar.DATE);

        //날짜 변경시 day 뒤 ㅇnull자리에 옴
        date.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int year, int month, int day) {
            fileName=year+"_"+(month+1)+"_"+day+".txt";
            String readData= readDiary(fileName);
                edit.setText(readData);
                but.setEnabled(true);
                
            }
        });



    }
    public String readDiary(String fileName){
    String diaryStr=null;
        FileInputStream fIn=null;
        try {
           fIn = openFileInput(fileName);
           byte[] buf=new byte[500];
            fIn.read(buf);
            diaryStr=new String(buf).trim();
           //trim() :: 앞뒤 공백 넣어 주기;
            but.setText("수정 하기");
           //fIn.close();
        }catch(FileNotFoundException e){
         //   e.printStackTrace();
            edit.setText("일기가 존재하지 않습니다.");
            but.setText("새로 저장");

        } catch (IOException e) {
            e.printStackTrace();
        }
        try{
            fIn.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }


        return diaryStr;
    }
}





package syl.kr.hs.emirim.simplediarytest;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

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
    public String readDiary(){
    return null;
    }
}




/*
--#1MainActivity의 레이아웃 수정

-[]activity_main.xml수정   git "#1 updateinit

--#2View 객체의 참조값

-[]MainActivity 설정된 레이아웃에 사용된 View 객체의 참조값을 참조변수에 대  git "#2Assign View Reference

--#3현재날자를 DatePicker 설정과 날짜 변경 핸틀러

-[] calender 클래스로 현재날짜 구한다.
-[] DatePicker에 현재날짜를 설정한다.
-[] 날짜가 변경되었을 때 처리하는 Handler 작성한다.


*/
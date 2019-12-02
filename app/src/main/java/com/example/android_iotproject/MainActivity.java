package com.example.android_iotproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import static com.example.android_iotproject.R.id.settingBtn;

public class MainActivity extends AppCompatActivity {
    Button stopButton;
    Button scanButton;
    ImageButton settingButton;

    boolean btnDefault = true;
    boolean btnState = false;

    //TimePickerDialog(시간설정)
    int hour = 0, minute = 0;
    TextView timeText;

    //scan에서 선택한 버스 가져오기
    TextView busText;

    //esp32에서 가져온 온습도 값
    TextView dhtText;
    /**
     * btnState는 연결된 LED에서 가져온 값
     *  하차벨이 눌러져있으면 true, 안눌러져 있으면 false
     *
     * btnDefault는 안드로이드 내에서 button의 색상을 결정한다
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stopButton = (Button) findViewById(R.id.stopBtn);
        scanButton = (Button) findViewById(R.id.scanBtn);
        settingButton = (ImageButton)findViewById(R.id.settingBtn);

        timeText = (TextView)findViewById(R.id.timeText);

        busText = (TextView)findViewById(R.id.busText);
        //busText.setText("750A"); 다음을 통해 버스번호 변경가능

        dhtText = (TextView)findViewById(R.id.dhtText);
        //dhtText.setText(temp+ "℃ " + humi + "%"); 다음을 통해 온습도 변경가능

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public  void onClick(View v){
                if(btnDefault){
                    btnDefault = false;

                    Toast.makeText(getApplicationContext(), "하차 버튼을 눌렀습니다", Toast.LENGTH_SHORT).show();

                    stopButton.setBackgroundDrawable(getDrawable(R.drawable.rounded_red));
                    stopButton.setTextColor(getResources().getColorStateList(R.color.colorWhite));
                }else{
                    btnDefault = true;

                    Toast.makeText(getApplicationContext(), "하차 버튼 누른걸 취소합니다", Toast.LENGTH_SHORT).show();

                    stopButton.setBackgroundDrawable(getDrawable(R.drawable.rounded_base));
                    stopButton.setTextColor(getResources().getColorStateList(R.color.colorBlack));
                }

                /**
                 LED(하차벨)연결 후 사용될 코드

                 btnState = 연결된 곳에서 현재 상태를 읽어 온다.

                 if(btnDefault && !btnState){
                 btnDefault = false;
                 btnState = true;

                 stopButton.setBackgroundDrawable(getDrawable(R.drawable.rounded_red));
                 stopButton.setTextColor(getResources().getColorStateList(R.color.colorWhite));
                 }else if(btnDefault && btnState){
                 btnDefault = false;

                 stopButton.setBackgroundDrawable(getDrawable(R.drawable.rounded_red));
                 stopButton.setTextColor(getResources().getColorStateList(R.color.colorWhite));
                 }else if(!btnDefault && !btnState){
                 btnDefault = true;
                 btnState = false;

                 stopButton.setBackgroundDrawable(getDrawable(R.drawable.rounded_base));
                 stopButton.setTextColor(getResources().getColorStateList(R.color.colorBlack));
                 }else if(!btnDefault && btnState){
                 btnDefault = true;

                 stopButton.setBackgroundDrawable(getDrawable(R.drawable.rounded_base));
                 stopButton.setTextColor(getResources().getColorStateList(R.color.colorBlack));

                 }
                 */
            }
        });

        //커스텀 리스트뷰
        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public  void onClick(View v){
                //버스 리스트 목록으로 이동한다
                Intent intent = new Intent(getApplicationContext(), BusListActivity.class);
                startActivity(intent);
            }
        });

        // TimePickerDialog (시간설정)
        settingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public  void onClick(View v){
                //버스 리스트 목록으로 이동한다
                showTimeDialog();
            }
        });
    }

    void showTimeDialog() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int h, int m) {
                hour = h;
                minute = m;

                timeText.setText(hour+"시 "+minute+ "분");
            }
        }, 12, 00, true);

        timePickerDialog.setMessage("시간설정");
        timePickerDialog.show();
    }
}

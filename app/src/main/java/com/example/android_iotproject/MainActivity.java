package com.example.android_iotproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button stopButton;
    Button scanButton;
    boolean btnDefault = true;
    boolean btnState = false;

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

        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public  void onClick(View v){
                //버스 리스트 목록으로 이동한다
                Intent intent = new Intent(getApplicationContext(), BusListActivity.class);
                startActivity(intent);
            }
        });
    }
}

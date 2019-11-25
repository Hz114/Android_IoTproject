package com.example.android_iotproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class BusListActivity extends AppCompatActivity {
    //Button backButton;
    private ListView listView;
    private BusListViewAdapter adapter;

    private Drawable bus_icon = null;
    private String busNum = null;
    private String busTime = null;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_list);

        mContext = getApplicationContext();

        listView = (ListView) findViewById(R.id.listView_bus);
        adapter = new BusListViewAdapter();
        listView.setAdapter(adapter);


        /* List에 item을 추가하는 방법 */
        bus_icon = (Drawable) ContextCompat.getDrawable(mContext, R.drawable.ic_bus);
        busNum = "750A";
        busTime = "10분";
        adapter.addItem(bus_icon, busNum, busTime);

        bus_icon = (Drawable) ContextCompat.getDrawable(mContext, R.drawable.ic_bus);
        busNum = "470";
        busTime = "3분";
        adapter.addItem(bus_icon, busNum, busTime);

        /* 바뀐 값으로 list를 업데이트 한다 */
        adapter.notifyDataSetChanged();


        /* list항목을 길게 클릭할 경우 */
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView parent, View v, int position, long id) {
                /* 사용자가 클릭한 item의 정보를 받아온다 */
                BusListViewItem item = (BusListViewItem) parent.getItemAtPosition(position);

                String getBusNum = item.getTitle();
                String getBusTime = item.getDesc();

                Toast.makeText(getApplicationContext(), getBusNum+" 버스를 클릭하셨습니다",Toast.LENGTH_LONG).show();
                return false;
            }
        });
    }
}
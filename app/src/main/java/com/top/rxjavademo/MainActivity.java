package com.top.rxjavademo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：李阳
 * 时间：2018/4/20
 * 描述：
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rlv)
    ListView rlv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        String[] mDatas=new String[]{
                "RxJavaDemo",
                "RetrofitDemo"
        };

        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,mDatas);

        //rlv.setLayoutManager(new LinearLayoutManager(this));

        rlv.setAdapter(adapter);


        rlv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 switch (position){
                    case 0:

                        startActivity(new Intent(MainActivity.this,RxJavaDemo.class));
                        break;

                    case 1:
                        startActivity(new Intent(MainActivity.this,RetrofitDemo.class));

                        break;
                }
            }
        });


    }
}

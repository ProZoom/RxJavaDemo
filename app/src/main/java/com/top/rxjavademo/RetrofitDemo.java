package com.top.rxjavademo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.top.rxjavademo.bean.ProvinceBean;
import com.top.rxjavademo.bean.ProvinceService;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


/**
 * 作者：李阳
 * 时间：2018/4/20
 * 描述：
 */
public class RetrofitDemo extends AppCompatActivity {

    private static final String TAG = "RetrofitDemo";
    @BindView(R.id.btn_retrofit_get)
    Button btnRetrofitGet;
    @BindView(R.id.btn_retrofit_post)
    Button btnRetrofitPost;
    @BindView(R.id.tv_result)
    TextView tvResult;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        ButterKnife.bind(this);

        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setDisplayHomeAsUpEnabled(true);

    }

    @OnClick({R.id.btn_retrofit_get, R.id.btn_retrofit_post, R.id.tv_result})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_retrofit_get:

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://raw.githubusercontent.com/ProZoom/Code/master/Other/TestRes/json/")
                        .build();

                ProvinceService service = retrofit.create(ProvinceService.class);

                Call<ProvinceBean> call = service.getCity();

                call.enqueue(new Callback<ProvinceBean>() {
                    @Override
                    public void onResponse(Call<ProvinceBean> call, Response<ProvinceBean> response) {
                        Log.i(TAG,response.body().getName());
                    }

                    @Override
                    public void onFailure(Call<ProvinceBean> call, Throwable t) {

                    }
                });

                break;
            case R.id.btn_retrofit_post:
                break;
        }
    }


    @Override
    public boolean onSupportNavigateUp() {
        finish();

        return super.onSupportNavigateUp();
    }

}

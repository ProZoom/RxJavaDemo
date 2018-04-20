package com.top.rxjavademo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * 作者：李阳
 * 时间：2018/4/19
 * 描述：
 */
public class RxJavaDemo extends AppCompatActivity {


    private static final String TAG = "RxJavaDemo";
    @BindView(R.id.btn_click)
    Button btnClick;
    @BindView(R.id.btn_click2)
    Button btnClick2;
    @BindView(R.id.btn_click3)
    Button btnClick3;
    @BindView(R.id.btn_click4)
    Button btnClick4;


    StringBuilder sb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_rxjava);
        ButterKnife.bind(this);

        ActionBar supportActionBar = getSupportActionBar();

        supportActionBar.setDisplayHomeAsUpEnabled(true);

        sb=new StringBuilder();


    }


    @OnClick({R.id.btn_click, R.id.btn_click2, R.id.btn_click3, R.id.btn_click4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_click:
                observable.subscribe(observer);

                break;
            case R.id.btn_click2:
                observable2.subscribe(observerstr);
                break;
            case R.id.btn_click3:
                observable3.subscribe(observerstr);

                break;
            case R.id.btn_click4:

                break;
        }
    }


//步骤1：创建被观察者 （Observable ）& 生产事件
    //1.1.创建被观察者对象

    Observable observable = Observable.create(new ObservableOnSubscribe() {

        //create()是RxJava最基本的创造事件序列的方法

        //1.2在复写的subscribe()里定义需要发送的事件
        @Override
        public void subscribe(ObservableEmitter emitter) throws Exception {

            Log.i(TAG, "步骤1：创建被观察者 （Observable ）& 生产事件----observable-------->subscribe\n");
            sb.append("步骤1：创建被观察者 （Observable ）& 生产事件----observable-------->subscribe");
            //通过ObserableEmitter类对象产生事件并通知观察者
            //ObserableEmitter类介绍
            //定义：时间发送器
            //作用：定义需要发送的事件，像观察者发送事件

            emitter.onNext(1);

            emitter.onNext(2);

            emitter.onNext(3);
            emitter.onComplete();
        }
    });


    //扩展：RxJavaDemo 提供了其他方法用于 创建被观察者对象Observable -->
    // 方法1：just(T...)：直接将传入的参数依次发送出来
    Observable observable2 = Observable.just("A", "B", "C");
    // 将会依次调用：
    // onNext("A");
    // onNext("B");
    // onNext("C");
    // onCompleted();

    // 方法2：from(T[]) / from(Iterable<? extends T>) : 将传入的数组 / Iterable 拆分成具体对象后，依次发送出来
    String[] words = {"E", "F", "G"};
    Observable observable3 = Observable.fromArray(words);
    // 将会依次调用：
    // onNext("A");
    // onNext("B");
    // onNext("C");
    // onCompleted();


    //步骤2：创建观察者 （Observer ）并 定义响应事件的行为

    //2.1采用Observer接口
    Observer<Integer> observer = new Observer<Integer>() {
        @Override
        public void onSubscribe(Disposable d) {
            Log.i(TAG, "步骤2.1：创建观察者Observer并定义响应事件行为-----onSubscribe---开始使用Subscribe连接池" + d.toString());
            sb.append("步骤2.1：创建观察者Observer并定义响应事件行为-----onSubscribe---开始使用Subscribe连接池" + d.toString()+"\n");
        }

        @Override
        public void onNext(Integer integer) {
            Log.i(TAG, "步骤2.1：创建观察者Observer并定义响应事件行为-----onNext---对next事件作出响应" + integer);
            sb.append("步骤2.1：创建观察者Observer并定义响应事件行为-----onNext---对next事件作出响应" + integer+"\n");
        }

        @Override
        public void onError(Throwable e) {
            Log.i(TAG, "步骤2.1：创建观察者Observer并定义响应事件行为-----onError----对Error事件作出响应" + e.toString());

        }

        @Override
        public void onComplete() {
            Log.i(TAG, "步骤2.1：创建观察者Observer并定义响应事件行为-----onComplete---对complete事件作出响应");

        }
    };


    //2.2采用Subscriber抽象类

    Subscriber<String> subscriber = new Subscriber<String>() {
        @Override
        public void onSubscribe(Subscription s) {
            Log.i(TAG, "步骤2.2：创建观察者Observer并定义响应事件行为-----onSubscribe---开始使用Subscribe连接池" + s.toString());

        }

        @Override
        public void onNext(String s) {
            Log.i(TAG, "步骤2.2：创建观察者Observer并定义响应事件行为-----onNext---对next事件作出响应" + s);

        }

        @Override
        public void onError(Throwable t) {
            Log.i(TAG, "步骤2.2：创建观察者Observer并定义响应事件行为-----onError----对Error事件作出响应" + t.toString());

        }

        @Override
        public void onComplete() {
            Log.i(TAG, "步骤2.2：创建观察者Observer并定义响应事件行为-----onComplete---对complete事件作出响应");

        }
    };


        //2.3采用Observer接口
    Observer<String> observerstr = new Observer<String>() {
        @Override
        public void onSubscribe(Disposable d) {
            Log.i(TAG, "步骤2.3：创建观察者Observer并定义响应事件行为-----onSubscribe---开始使用Subscribe连接池" + d.toString());
        }

        @Override
        public void onNext(String integer) {
            Log.i(TAG, "步骤2.3：创建观察者Observer并定义响应事件行为-----onNext---对next事件作出响应" + integer);

        }

        @Override
        public void onError(Throwable e) {
            Log.i(TAG, "步骤2.3：创建观察者Observer并定义响应事件行为-----onError----对Error事件作出响应" + e.toString());

        }

        @Override
        public void onComplete() {
            Log.i(TAG, "步骤2.3：创建观察者Observer并定义响应事件行为-----onComplete---对complete事件作出响应");

        }
    };


    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}

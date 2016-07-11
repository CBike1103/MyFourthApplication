package com.example.crossfire.myfourthapplication;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewpager;
    private List<View> viewList;
    private View view1;

    private static final int NOTIFYID_1 = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewpager = (ViewPager)findViewById(R.id.viewpager1);
        viewList = new ArrayList<>();
        LayoutInflater li = getLayoutInflater();

        view1 = li.inflate(R.layout.view1,null,false);
        Button v1btn1 = (Button) view1.findViewById(R.id.view1button1);
        v1btn1.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                //定义一个PendingIntent点击Notification后启动一个Activity
                Intent it = new Intent(MainActivity.this, checkContact.class);
                PendingIntent pit = PendingIntent.getActivity(MainActivity.this, 0, it, 0);

                //设置图片,通知标题,发送时间,提示方式等属性
                Notification.Builder mBuilder = new Notification.Builder(MainActivity.this);
                mBuilder.setContentTitle(getString(R.string.ylc))                        //标题
                        .setContentText(getString(R.string.kklxr))      //内容
                        .setTicker(getString(R.string.msgfromylc))             //收到信息后状态栏显示的文字信息
                        .setWhen(System.currentTimeMillis())           //设置通知时间
                        .setSmallIcon(R.mipmap.blue_cloudy)            //设置小图标
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.rabbit))             //设置大图标
                        .setDefaults(Notification.DEFAULT_ALL)    //设置默认的三色灯与振动器
                     //   .setSound(Uri.parse("android.resource://" + getPackageName() + "/" + R.))  //设置自定义的提示音
                        .setAutoCancel(true)                           //设置点击后取消Notification
                        .setContentIntent(pit);                        //设置PendingIntent
                Notification notify1 = mBuilder.build();
                NotificationManager mnManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                mnManager.notify(NOTIFYID_1, notify1);
            }
        });

        viewList.add(view1);
        viewList.add(li.inflate(R.layout.view2,null,false));
        viewList.add(li.inflate(R.layout.view3,null,false));

        viewpager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return viewList.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view==object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(viewList.get(position));
                return viewList.get(position);
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(viewList.get(position));
            }
        });

        viewpager.setCurrentItem(1);
    }
}

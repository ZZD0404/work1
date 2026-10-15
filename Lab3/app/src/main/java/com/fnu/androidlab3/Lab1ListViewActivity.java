package com.fnu.androidlab3;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 实验1：用 SimpleAdapter 把 动物名 + 动物图 显示在 ListView 中。
 * 点击列表项：Toast 显示名称，同时发一条系统通知。
 */
public class Lab1ListViewActivity extends AppCompatActivity {

    private static final String CHANNEL_ID = "animal_channel";
    private static final String KEY_NAME = "name";
    private static final String KEY_ICON = "icon";

    /** 动物名 -> 对应 drawable 图片 */
    private final String[] names = {"Lion", "Tiger", "Monkey", "Dog", "Cat", "Elephant"};
    private final int[] icons = {
            R.drawable.lion, R.drawable.tiger, R.drawable.monkey,
            R.drawable.dog, R.drawable.cat, R.drawable.elephant
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab1);
        setTitle("实验1 ListView");

        createNotificationChannel();

        // 组装数据：List<Map<String, Object>>
        List<Map<String, Object>> data = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            Map<String, Object> item = new HashMap<>();
            item.put(KEY_NAME, names[i]);
            item.put(KEY_ICON, icons[i]);
            data.add(item);
        }

        // SimpleAdapter：把 map 里的数据灌进 item_animal.xml 的控件
        SimpleAdapter adapter = new SimpleAdapter(
                this,
                data,
                R.layout.item_animal,
                new String[]{KEY_NAME, KEY_ICON},
                new int[]{R.id.tvName, R.id.ivIcon}
        );

        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            String name = names[position];
            // 1) Toast 显示选中项
            Toast.makeText(this, "你选中了：" + name, Toast.LENGTH_SHORT).show();
            // 2) 发一条通知：Title = 列表项内容
            sendNotification(name);
        });
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID, "动物列表通知", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            if (nm != null) nm.createNotificationChannel(channel);
        }
    }

    private void sendNotification(String title) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_menu_info_details) // 通知小图标
                .setContentTitle(title)                               // Title = 列表项内容
                .setContentText("这是来自实验1的通知：你点了 " + title + " 这一项。")
                .setAutoCancel(true);

        NotificationManagerCompat.from(this).notify((int) System.currentTimeMillis(), builder.build());
    }
}

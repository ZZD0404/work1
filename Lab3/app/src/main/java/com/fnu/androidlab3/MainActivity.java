package com.fnu.androidlab3;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

/**
 * 实验3入口：列出5个实验，分别跳转到对应 Activity。
 */
public class MainActivity extends AppCompatActivity {

    private static final int REQ_NOTIFICATION = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Android 13+ 发送通知需要运行时权限
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU
                && ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.POST_NOTIFICATIONS}, REQ_NOTIFICATION);
        }

        bind(R.id.btnLab1, Lab1ListViewActivity.class);
        bind(R.id.btnLab2, Lab2DialogActivity.class);
        bind(R.id.btnLab3, Lab3MenuActivity.class);
        bind(R.id.btnLab4, Lab4ActionModeActivity.class);
        bind(R.id.btnLab5, Lab5RecyclerActivity.class);
    }

    private void bind(int btnId, Class<?> target) {
        Button b = findViewById(btnId);
        if (b != null) b.setOnClickListener(v -> startActivity(new Intent(this, target)));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}

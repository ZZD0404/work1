package com.fnu.androidlab3;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

/**
 * 实验3：用 XML 定义选项菜单。
 *  - 字体大小：小/中/大 -> 10 / 16 / 20 sp
 *  - 普通菜单项：弹 Toast
 *  - 字体颜色：红 / 黑
 */
public class Lab3MenuActivity extends AppCompatActivity {

    private TextView tvTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab3);
        setTitle("MenuTest");
        tvTest = findViewById(R.id.tvTest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_test, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_size_small) {
            tvTest.setTextSize(10);
            return true;
        } else if (id == R.id.menu_size_medium) {
            tvTest.setTextSize(16);
            return true;
        } else if (id == R.id.menu_size_large) {
            tvTest.setTextSize(20);
            return true;
        } else if (id == R.id.menu_normal) {
            Toast.makeText(this, "你点了普通菜单项", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.color_red) {
            tvTest.setTextColor(0xFFFF0000);
            return true;
        } else if (id == R.id.color_black) {
            tvTest.setTextColor(0xFF000000);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

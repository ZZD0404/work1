package com.fnu.androidlab3;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 实验5：RecyclerView 展示动物列表（图片 + 名称）。
 * 单击：Toast 显示名称，同时在末尾新增一条记录并立即刷新。
 */
public class Lab5RecyclerActivity extends AppCompatActivity {

    private final List<Animal> data = new ArrayList<>();
    private AnimalAdapter adapter;
    private int addCount = 0; // 用来给新增记录编号

    // 可循环复用的图片
    private final int[] poolIcons = {
            R.drawable.lion, R.drawable.tiger, R.drawable.monkey,
            R.drawable.dog, R.drawable.cat, R.drawable.elephant
    };
    private final String[] poolNames = {"Lion", "Tiger", "Monkey", "Dog", "Cat", "Elephant"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab5);
        setTitle("实验5 RecyclerView（点击任意项新增一条）");

        // 初始 6 条
        for (int i = 0; i < poolNames.length; i++) {
            data.add(new Animal(poolNames[i], poolIcons[i]));
        }

        RecyclerView rv = findViewById(R.id.recyclerView);
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AnimalAdapter(data, position -> {
            String name = data.get(position).name;
            // 1) 单击显示名称
            Toast.makeText(this, "你点了：" + name, Toast.LENGTH_SHORT).show();
            // 2) 新增一条记录到末尾
            addCount++;
            int icon = poolIcons[addCount % poolIcons.length];
            data.add(new Animal("New Animal " + addCount, icon));
            // 3) 立即更新，并平滑滚动到底部
            int newPos = data.size() - 1;
            adapter.notifyItemInserted(newPos);
            rv.smoothScrollToPosition(newPos);
        });
        rv.setAdapter(adapter);
    }
}

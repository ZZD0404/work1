package com.fnu.androidlab3;

import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 实验4：ListView 长按进入 ActionMode 多选模式，顶栏显示 "n selected"，
 * 点垃圾桶删除选中项。
 */
public class Lab4ActionModeActivity extends AppCompatActivity {

    private final List<String> data = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab4);
        setTitle("实验4 ActionMode（长按列表项）");

        data.add("One");
        data.add("Two");
        data.add("Three");
        data.add("Four");
        data.add("Five");

        ListView listView = findViewById(R.id.listView);
        adapter = new ArrayAdapter<>(this, R.layout.item_lab4, R.id.tvName, data);
        listView.setAdapter(adapter);

        // 关键：开启多选模式
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position,
                                                  long id, boolean checked) {
                // 选中数量变化时刷新标题
                mode.setTitle(listView.getCheckedItemCount() + " selected");
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                mode.getMenuInflater().inflate(R.menu.menu_action_mode, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                if (item.getItemId() == R.id.action_delete) {
                    // 收集所有被勾选的位置，倒序删除避免下标错位
                    android.util.SparseBooleanArray checked = listView.getCheckedItemPositions();
                    List<Integer> toRemove = new ArrayList<>();
                    for (int i = 0; i < checked.size(); i++) {
                        if (checked.valueAt(i)) toRemove.add(checked.keyAt(i));
                    }
                    java.util.Collections.sort(toRemove, java.util.Collections.reverseOrder());
                    for (int pos : toRemove) {
                        data.remove(pos);
                    }
                    adapter.notifyDataSetChanged();
                    mode.finish();
                    return true;
                }
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
            }
        });
    }
}

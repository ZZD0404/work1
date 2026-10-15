package com.fnu.androidlab3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

/**
 * 实验2：用 AlertDialog.Builder.setView() 把自定义布局塞进去。
 */
public class Lab2DialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab2);
        setTitle("实验2 自定义对话框");

        findViewById(R.id.btnShowDialog).setOnClickListener(v -> showLoginDialog());
    }

    private void showLoginDialog() {
        // 1) 加载自定义布局
        View view = getLayoutInflater().inflate(R.layout.dialog_login, null);
        EditText etUser = view.findViewById(R.id.etUsername);
        EditText etPwd = view.findViewById(R.id.etPassword);
        Button btnCancel = view.findViewById(R.id.btnCancel);
        Button btnSignIn = view.findViewById(R.id.btnSignIn);

        // 2) 用 AlertDialog.Builder 并 setView
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setView(view)
                .create();

        // 3) 布局里两个按钮自己处理
        btnCancel.setOnClickListener(v -> dialog.dismiss());

        btnSignIn.setOnClickListener(v -> {
            String user = etUser.getText().toString().trim();
            if (user.isEmpty()) user = "(未输入用户名)";
            Toast.makeText(this, "欢迎你，" + user + "！", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        });

        dialog.show();
    }
}

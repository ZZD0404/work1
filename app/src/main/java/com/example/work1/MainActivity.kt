package com.example.work1

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.work1.ui.theme.Work1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Work1Theme {
                EntryScreen()
            }
        }
    }
}

@Composable
fun EntryScreen() {
    val context = LocalContext.current
    val items = listOf(
        "1. 线性布局 LinearLayout" to LinearLayoutActivity::class.java,
        "2. 表格布局 TableLayout" to TableLayoutActivity::class.java,
        "3. 约束布局-计算器" to CalculatorActivity::class.java,
        "4. 约束布局-太空旅行" to SpaceActivity::class.java,
        "5. Compose-课程学习任务" to TaskListActivity::class.java
    )

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Android 布局实验",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(24.dp))
            items.forEach { (label, activity) ->
                Button(
                    onClick = { context.startActivity(Intent(context, activity)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                ) {
                    Text(label)
                }
            }
        }
    }
}

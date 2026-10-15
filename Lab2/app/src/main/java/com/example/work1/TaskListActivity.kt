package com.example.work1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.work1.ui.theme.Work1Theme

data class Task(val id: Int, val name: String, val done: Boolean = false)

class TaskListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Work1Theme {
                TaskListScreen()
            }
        }
    }
}

@Composable
fun TaskListScreen() {
    // 状态：任务列表 + 输入框内容（UI = f(state)）
    var tasks by remember {
        mutableStateOf(
            listOf(
                Task(1, "学习 Column 和 Row"),
                Task(2, "学习状态管理"),
                Task(3, "完成 Compose 实验")
            )
        )
    }
    var input by remember { mutableStateOf("") }
    var nextId by remember { mutableIntStateOf(4) }

    val doneCount = tasks.count { it.done }
    val red = Color(0xFFE53935)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // 标题
        Text(
            text = "课程学习任务",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = red
        )

        Spacer(Modifier.height(16.dp))

        // 输入行：输入框 + 添加按钮
        Row(verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                value = input,
                onValueChange = { input = it },
                modifier = Modifier.weight(1f),
                placeholder = { Text("请输入学习任务") },
                singleLine = true
            )
            Spacer(Modifier.width(8.dp))
            Button(
                onClick = {
                    if (input.isNotBlank()) {
                        tasks = tasks + Task(nextId, input.trim())
                        nextId++
                        input = ""
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = red)
            ) {
                Text("添加", color = Color.White)
            }
        }

        Spacer(Modifier.height(16.dp))

        // 进度
        Text("已完成：$doneCount/${tasks.size}", color = red, fontSize = 16.sp)

        Spacer(Modifier.height(8.dp))

        // 任务列表：按需渲染
        LazyColumn(Modifier.fillMaxSize()) {
            items(tasks, key = { it.id }) { task ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = task.done,
                        onCheckedChange = { checked ->
                            // 状态更新：勾选后完成数和删除线同步变化
                            tasks = tasks.map {
                                if (it.id == task.id) it.copy(done = checked) else it
                            }
                        }
                    )
                    Text(
                        text = task.name,
                        modifier = Modifier.weight(1f),
                        fontSize = 16.sp,
                        textDecoration = if (task.done) TextDecoration.LineThrough else null,
                        color = if (task.done) Color.Gray else Color.Black
                    )
                    TextButton(onClick = { tasks = tasks.filter { it.id != task.id } }) {
                        Text("删除", color = red)
                    }
                }
            }
        }
    }
}

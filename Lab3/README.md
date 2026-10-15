# 实验3 Android 界面组件 —— 完整工程

本工程把实验指导书里的 **5 个任务** 全部做进了一个 App，启动后从主界面点按钮分别进入。

## 一、怎么导入运行

1. 打开 **Android Studio**（建议 Hedgehog 2023.1 或更新版本）。
2. `File → Open`，选择本文件夹 `AndroidLab3`（注意是包含 `settings.gradle` 的那一层）。
3. 第一次打开会自动下载 Gradle 8.2 / 依赖，等底部进度条跑完（需要联网）。
4. 选一个模拟器（推荐 API 26~34）或真机，点绿色 ▶ Run。
5. 主界面 5 个按钮对应 5 个实验。

> 通知权限：Android 13 及以上首次启动会弹“是否允许发送通知”，点**允许**，否则实验1点列表项看不到通知。

## 二、5 个实验对应位置

| 实验 | Activity | 关键知识点 |
|---|---|---|
| 1. ListView + SimpleAdapter | `Lab1ListViewActivity.java` | SimpleAdapter 绑数据；点击 `Toast`；`NotificationCompat` 发通知 |
| 2. 自定义 AlertDialog 登录框 | `Lab2DialogActivity.java` + `dialog_login.xml` | `AlertDialog.Builder.setView()` 加载自定义布局 |
| 3. XML 菜单 | `Lab3MenuActivity.java` + `res/menu/menu_test.xml` | `onCreateOptionsMenu` 加载 XML；子菜单；改 textSize / textColor |
| 4. ActionMode 上下文菜单 | `Lab4ActionModeActivity.java` | `CHOICE_MODE_MULTIPLE_MODAL` + `MultiChoiceModeListener`，长按多选删除 |
| 5. RecyclerView | `Lab5RecyclerActivity.java` + `AnimalAdapter.java` | Adapter + ViewHolder；`notifyItemInserted` 局部刷新 |

## 三、实验要点对照

### 实验1
- 数据是 `List<Map<String,Object>>`，key 为 `name` / `icon`。
- `SimpleAdapter` 把 map 灌进 `item_animal.xml` 的 `tvName`、`ivIcon`。
- 点击：`Toast` 显示选中名；同时建通知渠道（API 26+ 必须），`setSmallIcon`、`setContentTitle(列表项)`、`setContentText(自拟)`。

### 实验2
- `dialog_login.xml`：橙色横幅 `ANDROID APP` + 两个下划线输入框 + 底部 Cancel / Sign in。
- Java 里 `inflate(R.layout.dialog_login, null)` 后 `setView(view)`，按钮事件自己在布局里 `findViewById` 处理。

### 实验3
- `res/menu/menu_test.xml` 里：“字体大小”“字体颜色”用嵌套 `<menu>` 做子菜单；“普通菜单项”是普通 item。
- 字号：`setTextSize(10/16/20)`；颜色：`setTextColor(Color.RED / Color.BLACK)`。

### 实验4
- `listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL)`。
- `setMultiChoiceModeListener`：选中时 `mode.setTitle(n + " selected")`；点垃圾桶时用 `getCheckedItemPositions()` 收集勾选项，倒序删除后 `notifyDataSetChanged()`。
- item 根布局加了 `?android:attr/activatedBackgroundIndicator`，选中项会自动高亮。

### 实验5
- `AnimalAdapter` 继承 `RecyclerView.Adapter<VH>`，`onCreateViewHolder` inflate item，`onBindViewHolder` 绑数据。
- 点击：`Toast` 显示名称 → `data.add(...)` → `notifyItemInserted(newPos)` → `smoothScrollToPosition`，不重排其它 item、不错位。

## 四、目录结构

```
AndroidLab3/
├── settings.gradle / build.gradle / gradle.properties
└── app/
    ├── build.gradle
    └── src/main/
        ├── AndroidManifest.xml
        ├── java/com/fnu/androidlab3/
        │   ├── MainActivity.java          （入口）
        │   ├── Lab1ListViewActivity.java
        │   ├── Lab2DialogActivity.java
        │   ├── Lab3MenuActivity.java
        │   ├── Lab4ActionModeActivity.java
        │   ├── Lab5RecyclerActivity.java
        │   ├── Animal.java
        │   └── AnimalAdapter.java
        └── res/
            ├── layout/  (activity_*.xml, item_*.xml, dialog_login.xml)
            ├── menu/    (menu_test.xml, menu_action_mode.xml)
            ├── drawable/(lion/tiger/monkey/dog/cat/elephant, ic_android_robot, edittext_underline)
            └── values/  (strings.xml)
```

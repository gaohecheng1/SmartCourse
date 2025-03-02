package com.smartcourse.ui.schedule

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.smartcourse.data.model.Course
import com.smartcourse.data.repository.CourseRepository
import kotlinx.coroutines.launch

// 星期几常量
val weekdays = listOf("周一", "周二", "周三", "周四", "周五", "周六", "周日")

// 时间段常量
val timeSlots = listOf(
    TimeSlot(1, "08:00", "09:40"),
    TimeSlot(2, "10:00", "11:40"),
    TimeSlot(3, "14:00", "15:40"),
    TimeSlot(4, "16:00", "17:40"),
    TimeSlot(5, "19:00", "20:40")
)

@Composable
fun ScheduleScreen(navController: NavController, courseRepository: CourseRepository) {
    // 状态变量
    var courses by remember { mutableStateOf<List<Course>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var isRefreshing by remember { mutableStateOf(false) }
    var currentWeek by remember { mutableStateOf(1) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    
    val coroutineScope = rememberCoroutineScope()
    
    // 获取课程数据
    fun fetchCourses() {
        coroutineScope.launch {
            isLoading = true
            errorMessage = null
            
            try {
                // 这里应该是实际的网络请求
                // 暂时使用模拟数据
                val result = courseRepository.getCourses()
                courses = result
            } catch (e: Exception) {
                errorMessage = "获取课程失败，请检查网络连接"
                // 使用模拟数据
                courses = listOf(
                    Course(1, "高等数学", "张教授", "教学楼A-101", 1, "08:00", "09:40", 1, 16),
                    Course(2, "大学英语", "李教授", "教学楼B-202", 2, "10:00", "11:40", 1, 16),
                    Course(3, "数据结构", "王教授", "教学楼C-303", 3, "14:00", "15:40", 1, 16),
                    Course(4, "计算机网络", "刘教授", "教学楼D-404", 4, "16:00", "17:40", 1, 16),
                    Course(5, "操作系统", "陈教授", "教学楼E-505", 5, "19:00", "20:40", 1, 16)
                )
            } finally {
                isLoading = false
                isRefreshing = false
            }
        }
    }
    
    // 初始加载数据
    LaunchedEffect(key1 = true) {
        fetchCourses()
    }
    
    // 获取指定时间和日期的课程
    fun getCoursesByTimeAndDay(weekday: Int, timeSlot: TimeSlot): List<Course> {
        return courses.filter { course ->
            course.weekday == weekday &&
            course.startTime == timeSlot.start &&
            course.endTime == timeSlot.end &&
            currentWeek >= course.startWeek &&
            currentWeek <= course.endWeek
        }
    }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("课表") },
                backgroundColor = MaterialTheme.colors.primary
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // 周次选择器
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(Color.White),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {
                        if (currentWeek > 1) {
                            currentWeek--
                        }
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary)
                ) {
                    Text("上一周", color = Color.White)
                }
                
                Text(
                    text = "第 $currentWeek 周",
                    style = MaterialTheme.typography.h6
                )
                
                Button(
                    onClick = {
                        if (currentWeek < 20) { // 假设一学期最多20周
                            currentWeek++
                        }
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary)
                ) {
                    Text("下一周", color = Color.White)
                }
            }
            
            // 错误信息
            errorMessage?.let { error ->
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    color = Color(0xFFFFEBEE),
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Text(
                        text = error,
                        color = Color(0xFFC62828),
                        modifier = Modifier.padding(16.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }
            
            if (isLoading && !isRefreshing) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        CircularProgressIndicator()
                        Spacer(modifier = Modifier.height(16.dp))
                        Text("加载课表中...", color = Color.Gray)
                    }
                }
            } else {
                // 课表内容
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    // 时间列
                    Column {
                        // 时间/日期标题单元格
                        Box(
                            modifier = Modifier
                                .size(width = 80.dp, height = 40.dp)
                                .background(Color(0xFFE3F2FD))
                                .border(0.5.dp, Color.Gray.copy(alpha = 0.3f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "时间/日期",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        
                        // 时间单元格
                        timeSlots.forEach { slot ->
                            Box(
                                modifier = Modifier
                                    .size(width = 80.dp, height = 100.dp)
                                    .background(Color(0xFFE8EAF6))
                                    .border(0.5.dp, Color.Gray.copy(alpha = 0.3f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text(text = slot.start, fontSize = 12.sp)
                                    Text(text = "|", fontSize = 12.sp)
                                    Text(text = slot.end, fontSize = 12.sp)
                                }
                            }
                        }
                    }
                    
                    // 课程表格
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .horizontalScroll(rememberScrollState())
                    ) {
                        Column {
                            // 星期行
                            Row {
                                weekdays.forEachIndexed { index, day ->
                                    Box(
                                        modifier = Modifier
                                            .size(width = 100.dp, height = 40.dp)
                                            .background(Color(0xFFE3F2FD))
                                            .border(0.5.dp, Color.Gray.copy(alpha = 0.3f)),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = day,
                                            fontSize = 14.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }
                            }
                            
                            // 课程单元格
                            timeSlots.forEach { timeSlot ->
                                Row {
                                    weekdays.forEachIndexed { dayIndex, _ ->
                                        Box(
                                            modifier = Modifier
                                                .size(width = 100.dp, height = 100.dp)
                                                .border(0.5.dp, Color.Gray.copy(alpha = 0.3f))
                                        ) {
                                            val coursesForCell = getCoursesByTimeAndDay(dayIndex + 1, timeSlot)
                                            
                                            if (coursesForCell.isNotEmpty()) {
                                                Column(
                                                    modifier = Modifier
                                                        .fillMaxSize()
                                                        .background(Color(0xFFBBDEFB))
                                                        .padding(5.dp)
                                                        .clickable {
                                                            // 导航到课程详情页面
                                                            navController.navigate("course_detail/${coursesForCell[0].id}")
                                                        },
                                                    horizontalAlignment = Alignment.CenterHorizontally,
                                                    verticalArrangement = Arrangement.Center
                                                ) {
                                                    Text(
                                                        text = coursesForCell[0].name,
                                                        fontSize = 12.sp,
                                                        fontWeight = FontWeight.Bold,
                                                        textAlign = TextAlign.Center
                                                    )
                                                    Text(
                                                        text = coursesForCell[0].teacher,
                                                        fontSize = 10.sp,
                                                        color = Color.DarkGray,
                                                        textAlign = TextAlign.Center
                                                    )
                                                    Text(
                                                        text = coursesForCell[0].location,
                                                        fontSize = 10.sp,
                                                        color = Color.DarkGray,
                                                        textAlign = TextAlign.Center
                                                    )
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

// 时间段数据类
data class TimeSlot(val id: Int, val start: String, val end: String)
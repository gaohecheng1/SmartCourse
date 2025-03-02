import SwiftUI

struct ScheduleView: View {
    // 状态变量
    @State private var courses: [Course] = []
    @State private var isLoading = true
    @State private var isRefreshing = false
    @State private var currentWeek = 1
    @State private var errorMessage: String? = nil
    
    // 常量
    private let weekdays = ["周一", "周二", "周三", "周四", "周五", "周六", "周日"]
    private let timeSlots = [
        TimeSlot(id: 1, start: "08:00", end: "09:40"),
        TimeSlot(id: 2, start: "10:00", end: "11:40"),
        TimeSlot(id: 3, start: "14:00", end: "15:40"),
        TimeSlot(id: 4, start: "16:00", end: "17:40"),
        TimeSlot(id: 5, start: "19:00", end: "20:40")
    ]
    
    var body: some View {
        NavigationView {
            VStack(spacing: 0) {
                // 周次选择器
                HStack {
                    Button(action: {
                        if currentWeek > 1 {
                            currentWeek -= 1
                        }
                    }) {
                        Text("上一周")
                            .foregroundColor(.white)
                            .padding(.horizontal, 12)
                            .padding(.vertical, 8)
                            .background(Color.blue)
                            .cornerRadius(5)
                    }
                    
                    Spacer()
                    
                    Text("第 \(currentWeek) 周")
                        .font(.headline)
                    
                    Spacer()
                    
                    Button(action: {
                        if currentWeek < 20 { // 假设一学期最多20周
                            currentWeek += 1
                        }
                    }) {
                        Text("下一周")
                            .foregroundColor(.white)
                            .padding(.horizontal, 12)
                            .padding(.vertical, 8)
                            .background(Color.blue)
                            .cornerRadius(5)
                    }
                }
                .padding()
                .background(Color.white)
                
                // 错误信息
                if let error = errorMessage {
                    Text(error)
                        .foregroundColor(.red)
                        .padding()
                        .background(Color(UIColor.systemRed).opacity(0.1))
                        .cornerRadius(5)
                        .padding(.horizontal)
                }
                
                if isLoading && !isRefreshing {
                    Spacer()
                    ProgressView("加载课表中...")
                    Spacer()
                } else {
                    // 课表内容
                    ScrollView {
                        HStack(alignment: .top, spacing: 0) {
                            // 时间列
                            VStack(spacing: 0) {
                                // 时间/日期标题单元格
                                ZStack {
                                    Rectangle()
                                        .fill(Color(UIColor.systemBlue).opacity(0.1))
                                        .frame(width: 80, height: 40)
                                        .border(Color.gray.opacity(0.3), width: 0.5)
                                    
                                    Text("时间/日期")
                                        .font(.caption)
                                        .fontWeight(.bold)
                                }
                                
                                // 时间单元格
                                ForEach(timeSlots, id: \.id) { slot in
                                    ZStack {
                                        Rectangle()
                                            .fill(Color(UIColor.systemIndigo).opacity(0.1))
                                            .frame(width: 80, height: 100)
                                            .border(Color.gray.opacity(0.3), width: 0.5)
                                        
                                        VStack(spacing: 4) {
                                            Text(slot.start)
                                                .font(.caption)
                                            Text("|")
                                                .font(.caption)
                                            Text(slot.end)
                                                .font(.caption)
                                        }
                                    }
                                }
                            }
                            
                            // 课程表格
                            ScrollView(.horizontal, showsIndicators: false) {
                                VStack(spacing: 0) {
                                    // 星期行
                                    HStack(spacing: 0) {
                                        ForEach(weekdays.indices, id: \.self) { index in
                                            ZStack {
                                                Rectangle()
                                                    .fill(Color(UIColor.systemBlue).opacity(0.1))
                                                    .frame(width: 100, height: 40)
                                                    .border(Color.gray.opacity(0.3), width: 0.5)
                                                
                                                Text(weekdays[index])
                                                    .font(.subheadline)
                                                    .fontWeight(.bold)
                                            }
                                        }
                                    }
                                    
                                    // 课程单元格
                                    ForEach(timeSlots, id: \.id) { timeSlot in
                                        HStack(spacing: 0) {
                                            ForEach(weekdays.indices, id: \.self) { dayIndex in
                                                ZStack {
                                                    Rectangle()
                                                        .fill(Color.white)
                                                        .frame(width: 100, height: 100)
                                                        .border(Color.gray.opacity(0.3), width: 0.5)
                                                    
                                                    coursesForCell(weekday: dayIndex + 1, timeSlot: timeSlot)
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    .refreshable {
                        await fetchCourses()
                    }
                }
            }
            .navigationTitle("课表")
            .onAppear {
                Task {
                    await fetchCourses()
                }
            }
            .onChange(of: currentWeek) { _ in
                // 周次变化时可以执行一些操作
            }
        }
        #if os(iOS)
        .navigationViewStyle(StackNavigationViewStyle())
        #endif
    }
    
    // 获取指定时间和日期的课程
    private func coursesForCell(weekday: Int, timeSlot: TimeSlot) -> some View {
        let filteredCourses = courses.filter { course in
            course.weekday == weekday &&
            course.startTime == timeSlot.start &&
            course.endTime == timeSlot.end &&
            currentWeek >= course.startWeek &&
            currentWeek <= course.endWeek
        }
        
        if filteredCourses.isEmpty {
            return AnyView(EmptyView())
        } else {
            return AnyView(
                ForEach(filteredCourses) { course in
                    NavigationLink(destination: CourseDetailView(course: course)) {
                        VStack {
                            Text(course.name)
                                .font(.caption)
                                .fontWeight(.bold)
                                .multilineTextAlignment(.center)
                            
                            Text(course.teacher)
                                .font(.caption2)
                                .foregroundColor(.secondary)
                            
                            Text(course.location)
                                .font(.caption2)
                                .foregroundColor(.secondary)
                        }
                        .frame(maxWidth: .infinity, maxHeight: .infinity)
                        .padding(5)
                        .background(Color.blue.opacity(0.2))
                    }
                    .buttonStyle(PlainButtonStyle())
                }
            )
        }
    }
    
    // 获取课程数据
    private func fetchCourses() async {
        isLoading = true
        errorMessage = nil
        
        do {
            // 这里应该是实际的网络请求
            // 模拟网络延迟
            try await Task.sleep(nanoseconds: 1_000_000_000)
            
            // 模拟数据
            courses = [
                Course(id: 1, name: "高等数学", teacher: "张教授", location: "教学楼A-101", weekday: 1, startTime: "08:00", endTime: "09:40", startWeek: 1, endWeek: 16),
                Course(id: 2, name: "大学英语", teacher: "李教授", location: "教学楼B-202", weekday: 2, startTime: "10:00", endTime: "11:40", startWeek: 1, endWeek: 16),
                Course(id: 3, name: "数据结构", teacher: "王教授", location: "教学楼C-303", weekday: 3, startTime: "14:00", endTime: "15:40", startWeek: 1, endWeek: 16),
                Course(id: 4, name: "计算机网络", teacher: "刘教授", location: "教学楼D-404", weekday: 4, startTime: "16:00", endTime: "17:40", startWeek: 1, endWeek: 16),
                Course(id: 5, name: "操作系统", teacher: "陈教授", location: "教学楼E-505", weekday: 5, startTime: "19:00", endTime: "20:40", startWeek: 1, endWeek: 16)
            ]
        } catch {
            errorMessage = "获取课程失败，请检查网络连接"
        }
        
        isLoading = false
        isRefreshing = false
    }
}

// 课程模型
struct Course: Identifiable {
    let id: Int
    let name: String
    let teacher: String
    let location: String
    let weekday: Int
    let startTime: String
    let endTime: String
    let startWeek: Int
    let endWeek: Int
    var courseType: String? = nil
    var credit: Float? = nil
    var description: String? = nil
}

// 时间段模型
struct TimeSlot: Identifiable {
    let id: Int
    let start: String
    let end: String
}

#Preview {
    ScheduleView()
}
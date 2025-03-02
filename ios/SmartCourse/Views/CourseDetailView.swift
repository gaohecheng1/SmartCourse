import SwiftUI

struct CourseDetailView: View {
    let course: Course
    
    // 将数字星期几转换为文字
    private func getWeekdayText(weekday: Int) -> String {
        let weekdays = ["周一", "周二", "周三", "周四", "周五", "周六", "周日"]
        return weekdays[weekday - 1]
    }
    
    var body: some View {
        ScrollView {
            VStack(alignment: .leading, spacing: 0) {
                // 课程标题区域
                ZStack(alignment: .trailing) {
                    VStack(alignment: .leading) {
                        Text(course.name)
                            .font(.title)
                            .fontWeight(.bold)
                            .foregroundColor(.white)
                            .padding(.trailing, 60)
                    }
                    .frame(maxWidth: .infinity, alignment: .leading)
                    .padding(20)
                    
                    Text(course.courseType ?? "必修课")
                        .font(.caption)
                        .fontWeight(.bold)
                        .foregroundColor(Color.blue)
                        .padding(.horizontal, 10)
                        .padding(.vertical, 5)
                        .background(Color.white)
                        .cornerRadius(15)
                        .padding(.trailing, 20)
                }
                .background(Color.blue)
                
                // 课程信息区域
                VStack(spacing: 0) {
                    InfoRow(label: "授课教师", value: course.teacher)
                    InfoRow(label: "上课地点", value: course.location)
                    InfoRow(label: "上课时间", value: "\(getWeekdayText(weekday: course.weekday)) \(course.startTime)-\(course.endTime)")
                    InfoRow(label: "课程周期", value: "第\(course.startWeek)-\(course.endWeek)周")
                    
                    if let credit = course.credit {
                        InfoRow(label: "学分", value: "\(credit)")
                    }
                }
                .background(Color.white)
                .cornerRadius(8)
                .shadow(color: Color.black.opacity(0.1), radius: 5, x: 0, y: 2)
                .padding()
                
                // 课程简介区域
                VStack(alignment: .leading, spacing: 10) {
                    Text("课程简介")
                        .font(.headline)
                        .padding(.bottom, 5)
                    
                    Text(course.description ?? "暂无课程简介信息。")
                        .font(.body)
                        .foregroundColor(.secondary)
                        .lineSpacing(4)
                }
                .padding()
                .background(Color.white)
                .cornerRadius(8)
                .shadow(color: Color.black.opacity(0.1), radius: 5, x: 0, y: 2)
                .padding(.horizontal)
                
                Spacer()
            }
        }
        .navigationTitle("课程详情")
        .navigationBarTitleDisplayMode(.inline)
        .background(Color(UIColor.systemGroupedBackground))
        .edgesIgnoringSafeArea(.bottom)
    }
}

// 信息行组件
struct InfoRow: View {
    let label: String
    let value: String
    
    var body: some View {
        HStack {
            Text(label)
                .font(.body)
                .foregroundColor(.secondary)
            
            Spacer()
            
            Text(value)
                .font(.body)
                .fontWeight(.medium)
        }
        .padding(.vertical, 12)
        .padding(.horizontal, 16)
        .background(Color.white)
        .overlay(
            Divider()
                .background(Color(UIColor.systemGray5))
                .frame(height: 0.5)
                .offset(y: 16),
            alignment: .bottom
        )
    }
}

#Preview {
    NavigationView {
        CourseDetailView(
            course: Course(
                id: 1,
                name: "高等数学",
                teacher: "张教授",
                location: "教学楼A-101",
                weekday: 1,
                startTime: "08:00",
                endTime: "09:40",
                startWeek: 1,
                endWeek: 16,
                courseType: "必修课",
                credit: 4.0,
                description: "本课程主要讲授微积分、线性代数等高等数学知识，培养学生的数学思维和解决问题的能力。"
            )
        )
    }
}
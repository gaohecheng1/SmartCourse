import SwiftUI

struct ContentView: View {
    @State private var selectedTab = 0
    
    var body: some View {
        TabView(selection: $selectedTab) {
            // 课表标签
            ScheduleView()
                .tabItem {
                    Label("课表", systemImage: "calendar")
                }
                .tag(0)
            
            // 通知标签
            NotificationsView()
                .tabItem {
                    Label("通知", systemImage: "bell")
                }
                .tag(1)
            
            // 个人资料标签
            ProfileView()
                .tabItem {
                    Label("我的", systemImage: "person")
                }
                .tag(2)
        }
        #if os(iOS)
        .accentColor(.blue)
        #endif
    }
}

// 通知视图（占位，后续实现）
struct NotificationsView: View {
    var body: some View {
        NavigationView {
            VStack(spacing: 20) {
                Image(systemName: "bell.circle")
                    .font(.system(size: 60))
                    .foregroundColor(.blue)
                
                Text("通知功能即将上线")
                    .font(.title2)
                    .fontWeight(.medium)
                
                Text("您将在这里收到课程变更、作业提醒等重要通知")
                    .font(.body)
                    .foregroundColor(.secondary)
                    .multilineTextAlignment(.center)
                    .padding(.horizontal)
            }
            .frame(maxWidth: .infinity, maxHeight: .infinity)
            .background(Color(UIColor.systemGroupedBackground))
            .navigationTitle("通知")
        }
        #if os(iOS)
        .navigationViewStyle(StackNavigationViewStyle())
        #endif
    }
}

// 个人资料视图（占位，后续实现）
struct ProfileView: View {
    var body: some View {
        NavigationView {
            List {
                Section {
                    HStack(spacing: 15) {
                        Image(systemName: "person.circle.fill")
                            .font(.system(size: 60))
                            .foregroundColor(.blue)
                        
                        VStack(alignment: .leading, spacing: 5) {
                            Text("未登录")
                                .font(.title3)
                                .fontWeight(.medium)
                            
                            Text("点击登录账号")
                                .font(.subheadline)
                                .foregroundColor(.secondary)
                        }
                        
                        Spacer()
                        
                        Image(systemName: "chevron.right")
                            .foregroundColor(.secondary)
                    }
                    .padding(.vertical, 8)
                }
                
                Section(header: Text("设置")) {
                    NavigationLink(destination: Text("账号设置")) {
                        Label("账号设置", systemImage: "person.crop.circle")
                    }
                    
                    NavigationLink(destination: Text("通知设置")) {
                        Label("通知设置", systemImage: "bell")
                    }
                    
                    NavigationLink(destination: Text("隐私设置")) {
                        Label("隐私设置", systemImage: "hand.raised")
                    }
                }
                
                Section(header: Text("关于")) {
                    NavigationLink(destination: Text("关于智课")) {
                        Label("关于智课", systemImage: "info.circle")
                    }
                    
                    NavigationLink(destination: Text("帮助中心")) {
                        Label("帮助中心", systemImage: "questionmark.circle")
                    }
                    
                    NavigationLink(destination: Text("意见反馈")) {
                        Label("意见反馈", systemImage: "envelope")
                    }
                }
            }
            .listStyle(InsetGroupedListStyle())
            .navigationTitle("我的")
        }
        #if os(iOS)
        .navigationViewStyle(StackNavigationViewStyle())
        #endif
    }
}

#Preview {
    ContentView()
}
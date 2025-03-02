import SwiftUI

@main
struct SmartCourseApp: App {
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
        #if os(macOS)
        // macOS特有的窗口设置
        .windowStyle(HiddenTitleBarWindowStyle())
        .commands {
            // 添加macOS菜单命令
            CommandGroup(replacing: .appInfo) {
                Button("关于智课") {
                    // 显示关于对话框
                }
            }
            CommandGroup(replacing: .newItem) {
                Button("刷新课表") {
                    // 刷新课表操作
                }
            }
        }
        #endif
    }
}
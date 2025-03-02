# SmartCourse（智课）

## 项目简介

智课（SmartCourse）是一款专为高校导员和学生设计的跨端课程管理应用。它旨在帮助导员高效管理各个班级的课表，同时方便学生在任何设备上查看个人课表和接收导员发布的重要信息。

## 主要功能

### 导员端功能

- **课表管理**：导员可以方便地添加、编辑和删除班级课表信息，支持批量导入和导出功能
- **信息发布**：支持导员向学生发布通知、作业、活动安排等信息，可设置重要程度和提醒方式
- **课程查询**：支持按照课程名称、教师、时间段、教室等多维度快速查询和筛选课程信息
- **数据分析**：提供课表使用情况和学生查看率的数据分析，帮助导员优化信息发布策略
- **数据同步**：确保课表和信息在所有设备上实时同步，提高工作效率

### 学生端功能

- **课表查看**：学生可以随时随地查看个人课表，包括上课时间、地点和教师信息
- **课程搜索**：通过关键词、时间段、教师名称等条件快速查找所需课程信息
- **信息接收**：实时接收导员发布的各类通知和信息，不错过任何重要内容
- **日程提醒**：根据课表和重要通知自动设置提醒，支持自定义提醒时间
- **跨端同步**：支持手机、平板、电脑和网页等多种设备，信息无缝同步

## 技术栈

### 核心技术

- **前端移动端**：React Native（支持iOS和Android）
- **前端桌面端**：Electron（支持Windows、macOS）
- **前端网页端**：React.js + TypeScript
- **后端**：Java + Spring Boot（提供RESTful API）
- **数据库**：MySQL（主数据存储）+ Redis（缓存和会话管理）
- **实时通信**：Socket.IO（实现多端实时信息推送）
- **云服务**：AWS/阿里云（弹性部署，确保高可用性）

### 优化技术

- **状态管理**：Redux/MobX（统一管理应用状态）
- **离线存储**：IndexedDB/AsyncStorage（支持离线使用）
- **UI框架**：Material-UI/Ant Design（统一的视觉体验）
- **搜索引擎**：Elasticsearch/Algolia（提供高效的课程搜索功能）
- **CI/CD**：GitHub Actions（自动化测试和部署）
- **监控系统**：Sentry（错误跟踪和性能监控）

## 项目结构

```
smartcourse/
├── backend/                # 后端代码
│   ├── src/                # 源代码目录
│   │   ├── main/           # 主要代码
│   │   │   ├── java/       # Java源代码
│   │   │   └── resources/  # 资源文件
│   │   └── test/           # 测试代码
│   ├── pom.xml             # Maven配置文件
│   └── README.md           # 后端说明文档
├── mobile/                 # 移动端代码（React Native）
│   ├── components/         # 公共组件
│   ├── screens/            # 页面组件
│   ├── navigation/         # 导航配置
│   ├── store/              # 状态管理
│   ├── styles/             # 样式文件
│   ├── App.js              # 移动端入口文件
│   └── __tests__/          # 测试文件
├── desktop/                # 桌面端代码（Electron）
│   ├── main/               # 主进程
│   ├── renderer/           # 渲染进程
│   ├── assets/             # 静态资源
│   └── package.json        # 桌面端依赖
├── web/                    # 网页端代码（React.js）
│   ├── components/         # 公共组件
│   ├── pages/              # 页面组件
│   ├── hooks/              # 自定义Hooks
│   ├── services/           # API服务
│   ├── styles/             # 样式文件
│   └── App.tsx             # 网页端入口文件
├── shared/                 # 共享代码
│   ├── api/                # API客户端
│   ├── constants/          # 常量定义
│   ├── types/              # 类型定义
│   └── utils/              # 通用工具函数
├── scripts/                # 构建和部署脚本
├── docs/                   # 项目文档
├── package.json            # 项目依赖管理
├── README.md               # 项目说明文档
└── docker-compose.yml      # 容器化配置
```

## 运行指南

### 后端

```bash
# 进入后端目录
cd backend

# 编译项目
mvn clean package

# 开发环境启动
mvn spring-boot:run

# 生产环境启动
java -jar target/smartcourse-backend-1.0.0.jar
```

### 移动端（iOS/Android）

```bash
# 安装依赖
cd mobile && npm install

# 启动开发服务器
npx react-native start

# 运行iOS应用
npx react-native run-ios

# 运行Android应用
npx react-native run-android
```

### 桌面端（Windows/macOS）

```bash
# 安装依赖
cd desktop && npm install

# 开发模式运行
npm run dev

# 打包应用
npm run build
```

### 网页端

```bash
# 安装依赖
cd web && npm install

# 开发模式运行
npm run dev

# 构建生产版本
npm run build
```

## 部署方案

### 容器化部署

使用Docker和Docker Compose实现一键部署：

```bash
# 构建并启动所有服务
docker-compose up -d
```

### 云服务部署

- 后端API：部署在AWS Elastic Beanstalk或阿里云ECS
- 数据库：MongoDB Atlas或阿里云数据库服务
- 静态资源：AWS S3或阿里云OSS
- CDN加速：CloudFront或阿里云CDN

## 注意事项

- 确保开发环境中已安装JDK 11+、Maven、MySQL、React Native CLI和Electron等工具
- 移动端开发需要安装相应平台的开发工具（Xcode for iOS, Android Studio for Android）
- 在实际部署时，请根据生产环境需求进行配置和优化
- 跨端开发中需注意不同平台的兼容性问题，及时测试和调整
- 定期备份数据库，确保数据安全

## 路线图

- [ ] v1.0：基础课表管理和信息发布功能
- [ ] v1.2：实现基础课程查询功能
- [ ] v1.5：添加数据分析和统计功能
- [ ] v1.8：优化搜索体验，支持模糊搜索和高级筛选
- [ ] v2.0：支持离线模式和智能提醒
- [ ] v2.5：集成AI助手，提供智能课表建议和课程推荐
- [ ] v3.0：开放API，支持第三方应用集成

## 联系我们

如有任何问题或建议，请通过以下方式联系我们：

- **邮箱**：3596626541@qq.com
- **GitHub Issues**：[提交问题](https://github.com/gaohecheng1/smartcourse/issues)

我们期待与您一起打造更高效、便捷的课程管理体验！

## 许可证

本项目采用 [GNU General Public License v3.0](LICENSE) 许可证。

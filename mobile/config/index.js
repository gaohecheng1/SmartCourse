// 移动端配置文件

// API基础URL
export const API_URL = __DEV__ 
  ? 'http://localhost:3000' // 开发环境
  : 'https://api.smartcourse.com'; // 生产环境

// 应用版本
export const APP_VERSION = '1.0.0';

// 课程时间配置
export const COURSE_TIMES = [
  { id: 1, name: '第一节', start: '08:00', end: '08:45' },
  { id: 2, name: '第二节', start: '08:55', end: '09:40' },
  { id: 3, name: '第三节', start: '10:00', end: '10:45' },
  { id: 4, name: '第四节', start: '10:55', end: '11:40' },
  { id: 5, name: '第五节', start: '14:00', end: '14:45' },
  { id: 6, name: '第六节', start: '14:55', end: '15:40' },
  { id: 7, name: '第七节', start: '16:00', end: '16:45' },
  { id: 8, name: '第八节', start: '16:55', end: '17:40' },
  { id: 9, name: '第九节', start: '19:00', end: '19:45' },
  { id: 10, name: '第十节', start: '19:55', end: '20:40' },
];

// 周几配置
export const WEEKDAYS = [
  { id: 1, name: '周一' },
  { id: 2, name: '周二' },
  { id: 3, name: '周三' },
  { id: 4, name: '周四' },
  { id: 5, name: '周五' },
  { id: 6, name: '周六' },
  { id: 7, name: '周日' },
];

// 通知重要程度配置
export const IMPORTANCE_LEVELS = [
  { id: 'low', name: '普通', color: '#52c41a' },
  { id: 'medium', name: '重要', color: '#1890ff' },
  { id: 'high', name: '非常重要', color: '#fa8c16' },
  { id: 'urgent', name: '紧急', color: '#f5222d' },
];

// 通知类型配置
export const NOTIFICATION_TYPES = [
  { id: 'notice', name: '通知', icon: 'notification' },
  { id: 'homework', name: '作业', icon: 'book' },
  { id: 'activity', name: '活动', icon: 'calendar' },
  { id: 'other', name: '其他', icon: 'more' },
];
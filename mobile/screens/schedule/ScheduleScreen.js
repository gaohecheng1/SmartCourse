import React, { useState, useEffect } from 'react';
import { View, Text, StyleSheet, ScrollView, TouchableOpacity, ActivityIndicator, RefreshControl } from 'react-native';
import { SafeAreaView } from 'react-native-safe-area-context';
import axios from 'axios';
import { API_URL } from '../../config';

const weekdays = ['周一', '周二', '周三', '周四', '周五', '周六', '周日'];
const timeSlots = [
  { id: 1, start: '08:00', end: '09:40' },
  { id: 2, start: '10:00', end: '11:40' },
  { id: 3, start: '14:00', end: '15:40' },
  { id: 4, start: '16:00', end: '17:40' },
  { id: 5, start: '19:00', end: '20:40' },
];

const ScheduleScreen = ({ navigation }) => {
  const [courses, setCourses] = useState([]);
  const [loading, setLoading] = useState(true);
  const [refreshing, setRefreshing] = useState(false);
  const [currentWeek, setCurrentWeek] = useState(1);
  const [error, setError] = useState(null);

  const fetchCourses = async () => {
    try {
      setLoading(true);
      setError(null);
      // 这里应该根据用户ID或班级ID获取课程
      // 目前使用模拟数据
      const response = await axios.get(`${API_URL}/api/courses`);
      setCourses(response.data);
    } catch (err) {
      console.error('获取课程失败:', err);
      setError('获取课程失败，请检查网络连接');
      // 使用模拟数据
      setCourses([
        { id: 1, name: '高等数学', teacher: '张教授', location: '教学楼A-101', weekday: 1, startTime: '08:00', endTime: '09:40', startWeek: 1, endWeek: 16 },
        { id: 2, name: '大学英语', teacher: '李教授', location: '教学楼B-202', weekday: 2, startTime: '10:00', endTime: '11:40', startWeek: 1, endWeek: 16 },
        { id: 3, name: '数据结构', teacher: '王教授', location: '教学楼C-303', weekday: 3, startTime: '14:00', endTime: '15:40', startWeek: 1, endWeek: 16 },
        { id: 4, name: '计算机网络', teacher: '刘教授', location: '教学楼D-404', weekday: 4, startTime: '16:00', endTime: '17:40', startWeek: 1, endWeek: 16 },
        { id: 5, name: '操作系统', teacher: '陈教授', location: '教学楼E-505', weekday: 5, startTime: '19:00', endTime: '20:40', startWeek: 1, endWeek: 16 },
      ]);
    } finally {
      setLoading(false);
      setRefreshing(false);
    }
  };

  useEffect(() => {
    fetchCourses();
  }, []);

  const onRefresh = () => {
    setRefreshing(true);
    fetchCourses();
  };

  const handleWeekChange = (increment) => {
    const newWeek = currentWeek + increment;
    if (newWeek >= 1 && newWeek <= 20) { // 假设一学期最多20周
      setCurrentWeek(newWeek);
    }
  };

  const getCoursesByTimeAndDay = (weekday, timeSlot) => {
    return courses.filter(course => 
      course.weekday === weekday && 
      course.startTime === timeSlot.start &&
      course.endTime === timeSlot.end &&
      currentWeek >= course.startWeek &&
      currentWeek <= course.endWeek
    );
  };

  const renderCourseCell = (weekday, timeSlot) => {
    const coursesForCell = getCoursesByTimeAndDay(weekday, timeSlot);
    
    if (coursesForCell.length === 0) {
      return <View style={styles.emptyCourseCell} />;
    }

    return coursesForCell.map(course => (
      <TouchableOpacity 
        key={course.id}
        style={styles.courseCell}
        onPress={() => navigation.navigate('CourseDetail', { course })}
      >
        <Text style={styles.courseName}>{course.name}</Text>
        <Text style={styles.courseInfo}>{course.teacher}</Text>
        <Text style={styles.courseInfo}>{course.location}</Text>
      </TouchableOpacity>
    ));
  };

  if (loading && !refreshing) {
    return (
      <View style={styles.loadingContainer}>
        <ActivityIndicator size="large" color="#0066cc" />
        <Text style={styles.loadingText}>加载课表中...</Text>
      </View>
    );
  }

  return (
    <SafeAreaView style={styles.container}>
      <View style={styles.header}>
        <TouchableOpacity onPress={() => handleWeekChange(-1)} style={styles.weekButton}>
          <Text style={styles.weekButtonText}>上一周</Text>
        </TouchableOpacity>
        <Text style={styles.weekText}>第 {currentWeek} 周</Text>
        <TouchableOpacity onPress={() => handleWeekChange(1)} style={styles.weekButton}>
          <Text style={styles.weekButtonText}>下一周</Text>
        </TouchableOpacity>
      </View>

      {error && (
        <View style={styles.errorContainer}>
          <Text style={styles.errorText}>{error}</Text>
        </View>
      )}

      <ScrollView 
        refreshControl={
          <RefreshControl refreshing={refreshing} onRefresh={onRefresh} />
        }
      >
        <View style={styles.scheduleContainer}>
          {/* 时间列 */}
          <View style={styles.timeColumn}>
            <View style={styles.dayHeaderCell}>
              <Text style={styles.timeHeaderText}>时间/日期</Text>
            </View>
            {timeSlots.map(slot => (
              <View key={slot.id} style={styles.timeCell}>
                <Text style={styles.timeText}>{slot.start}</Text>
                <Text style={styles.timeText}>|</Text>
                <Text style={styles.timeText}>{slot.end}</Text>
              </View>
            ))}
          </View>

          {/* 课程表格 */}
          <ScrollView horizontal showsHorizontalScrollIndicator={false}>
            <View style={styles.scheduleGrid}>
              {/* 星期行 */}
              <View style={styles.dayRow}>
                {weekdays.map((day, index) => (
                  <View key={index} style={styles.dayHeaderCell}>
                    <Text style={styles.dayText}>{day}</Text>
                  </View>
                ))}
              </View>

              {/* 课程单元格 */}
              {timeSlots.map(timeSlot => (
                <View key={timeSlot.id} style={styles.courseRow}>
                  {weekdays.map((_, dayIndex) => (
                    <View key={dayIndex} style={styles.courseCellContainer}>
                      {renderCourseCell(dayIndex + 1, timeSlot)}
                    </View>
                  ))}
                </View>
              ))}
            </View>
          </ScrollView>
        </View>
      </ScrollView>
    </SafeAreaView>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#f5f5f5',
  },
  loadingContainer: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
  loadingText: {
    marginTop: 10,
    fontSize: 16,
    color: '#666',
  },
  header: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
    padding: 15,
    backgroundColor: '#fff',
    borderBottomWidth: 1,
    borderBottomColor: '#e0e0e0',
  },
  weekText: {
    fontSize: 18,
    fontWeight: 'bold',
  },
  weekButton: {
    padding: 8,
    backgroundColor: '#0066cc',
    borderRadius: 5,
  },
  weekButtonText: {
    color: '#fff',
    fontWeight: 'bold',
  },
  errorContainer: {
    padding: 10,
    backgroundColor: '#ffebee',
    borderRadius: 5,
    margin: 10,
  },
  errorText: {
    color: '#c62828',
    textAlign: 'center',
  },
  scheduleContainer: {
    flexDirection: 'row',
    flex: 1,
  },
  timeColumn: {
    width: 80,
  },
  scheduleGrid: {
    flex: 1,
  },
  dayRow: {
    flexDirection: 'row',
  },
  courseRow: {
    flexDirection: 'row',
  },
  dayHeaderCell: {
    width: 100,
    height: 40,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#e3f2fd',
    borderWidth: 0.5,
    borderColor: '#bdbdbd',
  },
  timeHeaderText: {
    fontSize: 12,
    fontWeight: 'bold',
  },
  dayText: {
    fontSize: 14,
    fontWeight: 'bold',
  },
  timeCell: {
    height: 100,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#e8eaf6',
    borderWidth: 0.5,
    borderColor: '#bdbdbd',
  },
  timeText: {
    fontSize: 12,
  },
  courseCellContainer: {
    width: 100,
    height: 100,
    borderWidth: 0.5,
    borderColor: '#bdbdbd',
  },
  courseCell: {
    flex: 1,
    padding: 5,
    backgroundColor: '#bbdefb',
    justifyContent: 'center',
    alignItems: 'center',
  },
  emptyCourseCell: {
    flex: 1,
    backgroundColor: '#fff',
  },
  courseName: {
    fontSize: 12,
    fontWeight: 'bold',
    textAlign: 'center',
  },
  courseInfo: {
    fontSize: 10,
    textAlign: 'center',
    color: '#555',
  },
});

export default ScheduleScreen;
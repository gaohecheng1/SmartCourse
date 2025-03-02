import React from 'react';
import { View, Text, StyleSheet, ScrollView, TouchableOpacity } from 'react-native';
import { SafeAreaView } from 'react-native-safe-area-context';

const CourseDetailScreen = ({ route, navigation }) => {
  const { course } = route.params;
  
  // 将数字星期几转换为文字
  const getWeekdayText = (weekday) => {
    const weekdays = ['周一', '周二', '周三', '周四', '周五', '周六', '周日'];
    return weekdays[weekday - 1];
  };

  return (
    <SafeAreaView style={styles.container}>
      <ScrollView>
        <View style={styles.header}>
          <Text style={styles.courseName}>{course.name}</Text>
          <View style={styles.badge}>
            <Text style={styles.badgeText}>{course.courseType || '必修课'}</Text>
          </View>
        </View>

        <View style={styles.infoSection}>
          <View style={styles.infoItem}>
            <Text style={styles.infoLabel}>授课教师</Text>
            <Text style={styles.infoValue}>{course.teacher}</Text>
          </View>
          
          <View style={styles.infoItem}>
            <Text style={styles.infoLabel}>上课地点</Text>
            <Text style={styles.infoValue}>{course.location}</Text>
          </View>
          
          <View style={styles.infoItem}>
            <Text style={styles.infoLabel}>上课时间</Text>
            <Text style={styles.infoValue}>
              {getWeekdayText(course.weekday)} {course.startTime}-{course.endTime}
            </Text>
          </View>
          
          <View style={styles.infoItem}>
            <Text style={styles.infoLabel}>课程周期</Text>
            <Text style={styles.infoValue}>第{course.startWeek}-{course.endWeek}周</Text>
          </View>
          
          {course.credit && (
            <View style={styles.infoItem}>
              <Text style={styles.infoLabel}>学分</Text>
              <Text style={styles.infoValue}>{course.credit}</Text>
            </View>
          )}
        </View>

        <View style={styles.descriptionSection}>
          <Text style={styles.sectionTitle}>课程简介</Text>
          <Text style={styles.descriptionText}>
            {course.description || '暂无课程简介信息。'}
          </Text>
        </View>

        <View style={styles.buttonContainer}>
          <TouchableOpacity style={styles.button} onPress={() => navigation.goBack()}>
            <Text style={styles.buttonText}>返回课表</Text>
          </TouchableOpacity>
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
  header: {
    padding: 20,
    backgroundColor: '#0066cc',
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
  },
  courseName: {
    fontSize: 22,
    fontWeight: 'bold',
    color: '#fff',
    flex: 1,
  },
  badge: {
    backgroundColor: '#fff',
    paddingHorizontal: 10,
    paddingVertical: 5,
    borderRadius: 15,
    marginLeft: 10,
  },
  badgeText: {
    color: '#0066cc',
    fontWeight: 'bold',
    fontSize: 12,
  },
  infoSection: {
    backgroundColor: '#fff',
    padding: 15,
    marginTop: 10,
    borderRadius: 5,
    marginHorizontal: 10,
  },
  infoItem: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    paddingVertical: 10,
    borderBottomWidth: 1,
    borderBottomColor: '#f0f0f0',
  },
  infoLabel: {
    fontSize: 16,
    color: '#666',
    fontWeight: '500',
  },
  infoValue: {
    fontSize: 16,
    color: '#333',
    fontWeight: 'bold',
  },
  descriptionSection: {
    backgroundColor: '#fff',
    padding: 15,
    marginTop: 10,
    borderRadius: 5,
    marginHorizontal: 10,
  },
  sectionTitle: {
    fontSize: 18,
    fontWeight: 'bold',
    marginBottom: 10,
    color: '#333',
  },
  descriptionText: {
    fontSize: 16,
    lineHeight: 24,
    color: '#555',
  },
  buttonContainer: {
    margin: 20,
  },
  button: {
    backgroundColor: '#0066cc',
    padding: 15,
    borderRadius: 5,
    alignItems: 'center',
  },
  buttonText: {
    color: '#fff',
    fontSize: 16,
    fontWeight: 'bold',
  },
});

export default CourseDetailScreen;
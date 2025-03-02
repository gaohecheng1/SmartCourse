import React from 'react';
import { SafeAreaView, StatusBar, StyleSheet, Text, View } from 'react-native';
import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import { Provider } from 'react-redux';
import Icon from 'react-native-vector-icons/MaterialIcons';

// 导入屏幕组件（后续会实现）
import LoginScreen from './screens/auth/LoginScreen';
import RegisterScreen from './screens/auth/RegisterScreen';
import CourseListScreen from './screens/course/CourseListScreen';
import CourseDetailScreen from './screens/course/CourseDetailScreen';
import NotificationListScreen from './screens/notification/NotificationListScreen';
import NotificationDetailScreen from './screens/notification/NotificationDetailScreen';
import ProfileScreen from './screens/profile/ProfileScreen';

// 导入状态管理（后续会实现）
import store from './store';

// 创建导航器
const Stack = createStackNavigator();
const Tab = createBottomTabNavigator();

// 主标签导航
const MainTabNavigator = () => {
  return (
    <Tab.Navigator
      screenOptions={({ route }) => ({
        tabBarIcon: ({ focused, color, size }) => {
          let iconName;

          if (route.name === '课表') {
            iconName = 'event-note';
          } else if (route.name === '通知') {
            iconName = 'notifications';
          } else if (route.name === '我的') {
            iconName = 'person';
          }

          return <Icon name={iconName} size={size} color={color} />;
        },
      })}
      tabBarOptions={{
        activeTintColor: '#1890ff',
        inactiveTintColor: 'gray',
      }}
    >
      <Tab.Screen name="课表" component={CourseListScreen} />
      <Tab.Screen name="通知" component={NotificationListScreen} />
      <Tab.Screen name="我的" component={ProfileScreen} />
    </Tab.Navigator>
  );
};

// 主应用组件
const App = () => {
  return (
    <Provider store={store}>
      <NavigationContainer>
        <StatusBar barStyle="dark-content" />
        <Stack.Navigator initialRouteName="Login">
          <Stack.Screen 
            name="Login" 
            component={LoginScreen} 
            options={{ headerShown: false }}
          />
          <Stack.Screen 
            name="Register" 
            component={RegisterScreen} 
            options={{ title: '注册' }}
          />
          <Stack.Screen 
            name="Main" 
            component={MainTabNavigator} 
            options={{ headerShown: false }}
          />
          <Stack.Screen 
            name="CourseDetail" 
            component={CourseDetailScreen} 
            options={{ title: '课程详情' }}
          />
          <Stack.Screen 
            name="NotificationDetail" 
            component={NotificationDetailScreen} 
            options={{ title: '通知详情' }}
          />
        </Stack.Navigator>
      </NavigationContainer>
    </Provider>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
  },
});

export default App;
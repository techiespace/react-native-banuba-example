import 'react-native-gesture-handler';
import React from 'react';
import {
  StyleSheet,
  View,
} from 'react-native';
import VideoView from './src/VideoView';

export default function App() {
  return (
    <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}>
      <VideoView
        style={{ flex: 1, width: '100%'}}
      />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    // paddingTop: Platform.OS === 'android' ? StatusBar.currentHeight : 0, //add for expo start
    paddingTop: 0,
  },
});

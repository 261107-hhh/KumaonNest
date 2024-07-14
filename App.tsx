// import 'react-native-gesture-handler';
// import React from 'react';
// import { NavigationContainer } from '@react-navigation/native';
// import { createStackNavigator } from '@react-navigation/stack';
// import Loginscreen from './src/components/screens/Loginscreen';
// import Homescreen from './src/components/screens/Homescreen';

// const Stack = createStackNavigator();

// const App = () => {
//   return (
//     <NavigationContainer>
//       <Stack.Navigator initialRouteName="Login">
//         <Stack.Screen name="Loginscreen" component={Loginscreen} options={{ headerShown: false }}/>
//         <Stack.Screen name="Homescreen" component={Homescreen} options={{ headerShown: false }} />
//       </Stack.Navigator>
//     </NavigationContainer>
//   );
// };

// export default App;



import { View, Text, StyleSheet } from 'react-native';
import React from 'react';
import AppNavigator from './src/components/screens/AppNavigator';

const App = () => {
  return (
    <View style={styles.container}>
      <AppNavigator />
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: 'black', // Set background color to black
  },
});

export default App;











import React, { useEffect, useRef, useState } from 'react';
import { Animated, StyleSheet, Dimensions, Platform } from 'react-native';
import Lottie from 'lottie-react-native';
import LottieView from 'lottie-react-native';
import { useNavigation } from '@react-navigation/native';

import 'react-native-gesture-handler';
import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';
import Loginscreen from './Loginscreen';
import Homescreen from './Homescreen';
import Forgetpassword from './Forgetpassword';
import Resetpassword from './Resetpassword';
import Createaccount from './Createaccount';

const height = Dimensions.get('screen').height;
const width = Dimensions.get('screen').width;

export const USE_NATIVE_DRIVER = Platform.select({
  ios: true,
  android: Platform.constants?.Release !== '12',
  default: true,
});

const AnimatedLottieView = Animated.createAnimatedComponent(LottieView);

export function WithSplashScreen({ children, isAppReady }) {
  return (
    <>
      {isAppReady && children}
      <Splash isAppReady={isAppReady} />
    </>
  );
}

const LOADING_IMAGE = 'Loading image';
const FADE_IN_IMAGE = 'Fade in image';
const WAIT_FOR_APP_TO_BE_READY = 'Wait for app to be ready';
const FADE_OUT = 'Fade out';
const HIDDEN = 'Hidden';

export const Splash = ({ isAppReady, navigation }) => {
  const animationProgress = useRef(new Animated.Value(0));
  const containerOpacity = useRef(new Animated.Value(1.5)).current;
  const imageOpacity = useRef(new Animated.Value(0)).current;
  const [state, setState] = useState(LOADING_IMAGE);

  useEffect(() => {
    Animated.timing(animationProgress.current, {
      toValue: 1,
      duration: 5000,
      useNativeDriver: USE_NATIVE_DRIVER,
    }).start(() => {
      setState(FADE_IN_IMAGE);
    });
  }, []);

  useEffect(() => {
    if (state === FADE_IN_IMAGE) {
      Animated.timing(imageOpacity, {
        toValue: 1,
        duration: 1000,
        useNativeDriver: USE_NATIVE_DRIVER,
      }).start(() => {
        setState(WAIT_FOR_APP_TO_BE_READY);
      });
    }
  }, [state]);

  useEffect(() => {
    if (state === WAIT_FOR_APP_TO_BE_READY && isAppReady) {
      setState(FADE_OUT);
    }
  }, [isAppReady, state]);

  useEffect(() => {
    if (state === FADE_OUT) {
      Animated.timing(containerOpacity, {
        toValue: 0,
        duration: 1000,
        delay: 1000,
        useNativeDriver: USE_NATIVE_DRIVER,
      }).start(() => {
        setState(HIDDEN);
        if (navigation) {
          navigation.navigate('Loginscreen');
        }
      });
    }
  }, [state, navigation]);


  return (
    <AnimatedLottieView
      progress={animationProgress.current}
      style={[styles.container, {transform: [{scale: 1.0}]}]} 
      source={require('../../utils/assets/animation/splash4.json')}
    />
  );
};

const styles = StyleSheet.create({
  container: {
    ...StyleSheet.absoluteFillObject,
    backgroundColor: '#ffffff',
    width: width,
    height: height,
  },
  image: {
    width: width,
    height: height,
  },
});

const ControllingAnimationProgress = () => {
  const navigation = useNavigation();
  return (
    <WithSplashScreen isAppReady={true}>
      <Splash isAppReady={true} navigation={navigation} />
    </WithSplashScreen>
  );
};

const Stack = createStackNavigator();

const AppNavigator = () => {
  return (
    <NavigationContainer>
      <Stack.Navigator initialRouteName="Splash">
        <Stack.Screen name="Splash" component={ControllingAnimationProgress} options={{ headerShown: false }} />
        <Stack.Screen name="Loginscreen" component={Loginscreen} options={{ headerShown: false }} />
        <Stack.Screen name="Homescreen" component={Homescreen} options={{ headerShown: false }} />
        <Stack.Screen name="Forgetpassword" component={Forgetpassword} options={{ headerShown: false }} />
        <Stack.Screen name="Resetpassword" component={Resetpassword} options={{ headerShown: false }} />
        <Stack.Screen name="Createaccount" component={Createaccount} options={{ headerShown: false }} />
      </Stack.Navigator>
    </NavigationContainer>
  );
};

export default AppNavigator;
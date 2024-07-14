/**
 * @format
 *
 */


import {name as appName} from './app.json';
import {AppRegistry} from 'react-native';
import App from './App';
import 'react-native-gesture-handler';
import { LogBox } from 'react-native';
console.disableYellowBox = true;

AppRegistry.registerComponent(appName, () => App);
LogBox.ignoreLogs(['Warning: ...']); // Ignore log notification by message
LogBox.ignoreAllLogs();//Ignore all log notifications
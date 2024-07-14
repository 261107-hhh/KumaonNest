import React, { useState, useRef, useEffect } from 'react';
import { View, Text, TextInput, TouchableOpacity, StyleSheet, SafeAreaView, Keyboard, TouchableWithoutFeedback, ActivityIndicator } from 'react-native';
import { useFocusEffect, useNavigation } from '@react-navigation/native';
import Icon from 'react-native-vector-icons/FontAwesome';

const Resetpassword = () => {
  const navigation = useNavigation();
  const [showPassword1, setShowPassword1] = useState(false);
  const [showPassword2, setShowPassword2] = useState(false);
  const [otp, setOtp] = useState(['', '', '', '']);
  const [password1, setPassword1] = useState('');
  const [password2, setPassword2] = useState('');
  const [isLoading, setIsLoading] = useState(false);
  const inputRefs = [useRef(null), useRef(null), useRef(null), useRef(null)];

  const togglePasswordVisibility1 = () => {
    setShowPassword1(!showPassword1);
  };

  const togglePasswordVisibility2 = () => {
    setShowPassword2(!showPassword2);
  };

  useFocusEffect(
    React.useCallback(() => {
      setShowPassword1('');
      setShowPassword2('');
      setOtp(['', '', '', '']);
      setPassword1('');
      setPassword2('');
    }, [])
  );

  const navigateToLogin = () => {
    navigation.navigate('Loginscreen');
  };

  const handleOtpChange = (text, index) => {
    const newOtp = [...otp];
    newOtp[index] = text;
    setOtp(newOtp);

    if (text && index < 3) {
      inputRefs[index + 1].current.focus();
    }
  };

  const handleKeyPress = (event, index) => {
    if (event.nativeEvent.key === 'Backspace' && !otp[index] && index > 0) {
      inputRefs[index - 1].current.focus();
    }
  };

  const isFormValid = () => {
    return otp.every(val => val !== '') && password1 !== '' && password2 !== '';
  };

  const handleResetPassword = () => {
    if (isFormValid()) {
      setIsLoading(true);
      setTimeout(() => {
        setIsLoading(false);
        navigateToLogin();
      }, 3000);
    }
  };

  return (
    <SafeAreaView style={styles.container}>
      <View style={styles.header}>
        <TouchableOpacity onPress={() => navigation.goBack()}>
          <Text style={styles.backText}>{'<'}</Text>
        </TouchableOpacity>
        <Text style={styles.headerText}>RESET PASSWORD</Text>
      </View>

      <View style={styles.content}>
        <Text style={styles.enterOtpText}>Enter OTP</Text>
        <TouchableWithoutFeedback onPress={Keyboard.dismiss} accessible={false}>
          <View style={styles.otpContainer}>
            {otp.map((value, index) => (
              <TextInput
                key={index}
                style={styles.otpBox}
                ref={inputRefs[index]}
                keyboardType="numeric"
                value={value}
                onChangeText={text => handleOtpChange(text, index)}
                onKeyPress={event => handleKeyPress(event, index)}
                maxLength={1}
              />
            ))}
          </View>
        </TouchableWithoutFeedback>

        <View style={styles.passwordInput}>
          <TextInput
            style={styles.input}
            placeholder="Enter password"
            secureTextEntry={!showPassword1}
            value={password1}
            onChangeText={setPassword1}
          />
          <TouchableOpacity style={styles.eyeIcon} onPress={togglePasswordVisibility1}>
            <Icon
              name={showPassword1 ? 'eye' : 'eye-slash'}
              size={20}
              style={{ marginRight: 10, alignSelf: 'center', alignItems: 'center', marginTop: 5 }}
            />
          </TouchableOpacity>
        </View>

        <View style={styles.passwordInput}>
          <TextInput
            style={styles.input}
            placeholder="Repeat password"
            secureTextEntry={!showPassword2}
            value={password2}
            onChangeText={setPassword2}
          />
          <TouchableOpacity style={styles.eyeIcon} onPress={togglePasswordVisibility2}>
            <Icon
              name={showPassword2 ? 'eye' : 'eye-slash'}
              size={20}
              style={{ marginRight: 10, alignSelf: 'center', alignItems: 'center', marginTop: 5 }}
            />
          </TouchableOpacity>
        </View>

        <TouchableOpacity
          style={[styles.resetButton, { backgroundColor: isFormValid() ? '#3F7E44' : '#ccc' }]}
          onPress={handleResetPassword}
          disabled={!isFormValid()}
        >
          {isLoading ? (
            <ActivityIndicator size="small" color="#fff" />
          ) : (
            <Text style={styles.resetButtonText}>Reset Password</Text>
          )}
        </TouchableOpacity>
      </View>

      <TouchableOpacity style={styles.returnButton} onPress={navigateToLogin}>
        <Text style={styles.returnButtonText}>Return to Login Page</Text>
      </TouchableOpacity>
    </SafeAreaView>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
  },
  header: {
    flexDirection: 'row',
    alignItems: 'center',
    backgroundColor: '#83683C',
    padding: 20,
  },
  backText: {
    color: '#fff',
    fontSize: 18,
  },
  headerText: {
    color: '#fff',
    fontSize: 20,
    marginLeft: 60,
    alignSelf: 'center',
    alignContent: 'center',
    fontWeight: 'bold',
  },
  content: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    padding: 20,
  },
  enterOtpText: {
    color: '#D9534F',
    fontSize: 18,
    fontWeight: 'bold',
    marginBottom: 20,
  },
  otpContainer: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    width: '60%',
    marginBottom: 30,
    marginLeft: -20,
  },
  otpBox: {
    width: 50,
    height: 50,
    backgroundColor: '#D2BFA0',
    borderRadius: 10,
    textAlign: 'center',
    fontSize: 18,
    fontWeight: 'bold',
    marginHorizontal: 5,
    color: '#fff',
  },
  passwordInput: {
    position: 'relative',
    width: '100%',
    marginBottom: 20,
  },
  input: {
    width: '100%',
    height: 40,
    borderColor: '#ccc',
    borderWidth: 1,
    borderRadius: 20,
    paddingLeft: 20,
    backgroundColor: '#D1D1D3',
  },
  eyeIcon: {
    position: 'absolute',
    top: 5,
    right: 10,
  },
  resetButton: {
    width: '100%',
    height: 40,
    justifyContent: 'center',
    alignItems: 'center',
    borderRadius: 20,
    marginBottom: 10,
  },
  resetButtonText: {
    color: '#fff',
    fontSize: 16,
    fontWeight: 'bold',
  },
  returnButton: {
    padding: 20,
  },
  returnButtonText: {
    color: '#3F7E44',
    fontSize: 16,
    fontWeight: 'bold',
    textAlign: 'center',
  },
});

export default Resetpassword;

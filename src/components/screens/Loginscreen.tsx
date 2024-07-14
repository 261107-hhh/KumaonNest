import { View, Text, Image, StyleSheet, TextInput, TouchableOpacity, ActivityIndicator } from 'react-native';
import React, { useState } from 'react';
import { useFocusEffect, useNavigation } from '@react-navigation/native';
import Icon from 'react-native-vector-icons/FontAwesome';

const Loginscreen = () => {
  const navigation = useNavigation();
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [showPassword2, setShowPassword2] = useState(false);
  const [isLoading, setIsLoading] = useState(false);



  useFocusEffect(
    React.useCallback(() => {
      // Clear input fields when the screen is focused
      setEmail('');
      setPassword('');
    }, [])
  );

  const gotoCreate = () => {
    navigation.navigate('Createaccount');
  };

  const navigateToPassword = () => {
    navigation.navigate('Forgetpassword');
  };

  const togglePasswordVisibility2 = () => {
    setShowPassword2(!showPassword2);
  };

  const handleLogin = () => {
    setIsLoading(true);

    setTimeout(() => {
      setIsLoading(false);
      // Handle navigation after login here
      navigation.navigate('Homescreen')
    }, 2000);
  };

  const isButtonEnabled = email.length > 0 && password.length > 0;

  return (
    <View style={{ backgroundColor: '#83683C', flex: 1 }}>
      <View style={{ height: '70%', borderBottomLeftRadius: 25, borderBottomRightRadius: 25, backgroundColor: '#D1D1D3' }}>
        <Image
          source={require('../../utils/assets/images/newss.png')}
          style={{ width: 300, height: 200, alignSelf: 'center', marginTop: 60, backgroundColor: '#ff' }}
          resizeMode="contain"
        />

        <View style={{ alignSelf: 'center', marginTop: 10 }}>
          <Text style={{ color: '#83683C', fontSize: 25, fontWeight: 'bold', flexWrap: 'wrap-reverse', letterSpacing: 3, // Increase this value to stretch the text
    lineHeight: 30, }}>
            KUMAUN NESssssssT
          </Text>
          <Text style={{ color: '#83683C', fontStyle: 'italic', fontSize: 15, fontWeight: '700', paddingTop: 5 }}>
            The abode of Himalayan Products
          </Text>
        </View>
      </View>

      <View style={{ height: '45%', marginHorizontal: 20, backgroundColor: '#D2BFA0', marginTop: -200, borderRadius: 10 }}>
        <TextInput
          style={styles.phoneInput}
          placeholder="Email"
    
         
          onChangeText={setEmail}
          value={email}
        />
        <View style={styles.passwordInput}>
          <TextInput
            style={{ color: '#000000', borderRadius: 15, padding: -10, backgroundColor: '#FFFFFF', fontWeight: 'bold' }}
            placeholder="Password"
            secureTextEntry={!showPassword2}
            onChangeText={setPassword}
            value={password}
          />
          <TouchableOpacity style={styles.eyeIcon} onPress={togglePasswordVisibility2}>
            <Icon name={showPassword2 ? 'eye' : 'eye-slash'} size={20} color='green' />
          </TouchableOpacity>
        </View>
        <TouchableOpacity onPress={navigateToPassword}>
          <Text style={{ alignSelf: 'flex-end', fontSize: 10, marginRight: 21, fontWeight: '600', color: '#2E7DEA' }}>
            Forget Password
          </Text>
        </TouchableOpacity>

        <TouchableOpacity onPress={handleLogin} disabled={!isButtonEnabled}>
          <View style={{ ...styles.button, backgroundColor: isButtonEnabled ? 'green' : 'white' }}>
            {isLoading ? (
              <ActivityIndicator size="small" color="#FFFFFF" />
            ) : (
              <Text style={[styles.buttonText, (isButtonEnabled || isLoading) && { color: '#FFFFFF' }]}>LOG IN</Text>
            )}
          </View>
        </TouchableOpacity>
        <View style={{ flexDirection: 'row', marginTop: 30, marginHorizontal: 30 }}>
          <Text style={{ color: '#ffffff', fontSize: 11 }}>Don't have an account?</Text>
          <TouchableOpacity onPress={gotoCreate}>
            <Text style={{ alignSelf: 'flex-end', fontSize: 12, marginRight: 21, fontWeight: '600', color: '#83683C', paddingLeft: 5 }}>
              Create an account
            </Text>
          </TouchableOpacity>
        </View>
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
  phoneInput: {
    borderRadius: 15,
    padding: 10,
    color: '#000000',
    backgroundColor: '#FFFFFF',
    fontWeight: 'bold',
    marginBottom: 10,
    marginTop: 50,
    marginLeft: 20,
    marginRight: 20,
    height: 50,
  },
  eyeIcon: {
    position: 'absolute',
    top: 5,
    right: 10,
    paddingTop: 8,
  },
  button: {
    display: 'flex',
    flexDirection: 'row',
    justifyContent: 'space-evenly',
    alignItems: 'center',
    height: 40,
    marginTop: 20,
    borderRadius: 20,
    marginHorizontal: 20,
  },
  buttonText: {
    color: '#000000',
    fontWeight: 'bold',
    fontSize: 14,
  },
  passwordInput: {
    borderRadius: 15,
    padding: 10,
    color: '#000000',
    height: 50,
    backgroundColor: '#FFFFFF',
    fontWeight: 'bold',
    marginBottom: 10,
    marginTop: 10,
    marginLeft: 20,
    marginRight: 20,
  },
});

export default Loginscreen;

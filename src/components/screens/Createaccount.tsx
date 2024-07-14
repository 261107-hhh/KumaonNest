import React, { useState } from 'react';
import { View, Text, TextInput, TouchableOpacity, StyleSheet, SafeAreaView, KeyboardAvoidingView, Platform, ScrollView, Keyboard, ActivityIndicator } from 'react-native'; // Added Keyboard import
import { useNavigation } from '@react-navigation/native';
import Icon from 'react-native-vector-icons/FontAwesome';

const Createaccount = () => {
  const navigation = useNavigation();
  const [showPassword1, setShowPassword1] = useState(false); // State for password field 1
  const [showPassword2, setShowPassword2] = useState(false); // State for password field 2
  const [email, setEmail] = useState('');
  const [fullName, setFullName] = useState('');
  const [mobile, setMobile] = useState('');
  const [password, setPassword] = useState('');
  const [repeatPassword, setRepeatPassword] = useState('');
  const [isLoading, setIsLoading] = useState(false);

  const togglePasswordVisibility1 = () => {
    setShowPassword1(!showPassword1);
  };



  const handleCreateAccount = () => {
    setIsLoading(true);

    setTimeout(() => {
      setIsLoading(false);
      // Handle navigation after account creation here
    }, 2000);
  };

  const togglePasswordVisibility2 = () => {
    setShowPassword2(!showPassword2);
  };

  const navigateToLogin = () => {
    navigation.goBack();
  };

  const isButtonEnabled = email.length > 0 && fullName.length > 0 && mobile.length > 0 && password.length > 0 && repeatPassword.length > 0;
  return (
    <SafeAreaView style={styles.container}>
      <KeyboardAvoidingView
        behavior={Platform.OS === 'ios' ? 'padding' : null} // Adjusted behavior for iOS to only apply padding
        style={styles.avoidingView}
        keyboardVerticalOffset={Platform.OS === 'ios' ? 0 : 0} // Adjusted keyboardVerticalOffset if necessary
      >
        <ScrollView
          contentContainerStyle={{ flexGrow: 1 }} // Ensure content fills the available space
          keyboardShouldPersistTaps="handled" // Ensure taps outside TextInput dismiss keyboard
        >
          <View style={styles.header}>
            <TouchableOpacity onPress={() => navigation.goBack()}>
              <Text style={styles.backText}>{'<'}</Text>
            </TouchableOpacity>
            <Text style={styles.headerText}>CREATE YOUR ACCOUNT</Text>
          </View>

          <View style={styles.content}>

            <TextInput style={styles.input1} placeholder="Email" keyboardType="email-address" onChangeText={setEmail} value={email} />
            <TextInput style={styles.input} placeholder="Full Name" onChangeText={setFullName} value={fullName} />
            <TextInput style={styles.input1} placeholder="Mobile No." keyboardType="phone-pad" onChangeText={setMobile} value={mobile} />





            <View style={styles.passwordInput}>
            <TextInput
                style={styles.input}
                placeholder="Password"
                secureTextEntry={!showPassword1}
                onChangeText={setPassword}
                value={password}
              />
              <TouchableOpacity style={styles.eyeIcon} onPress={togglePasswordVisibility1}>
                <Icon
                  name={showPassword1 ? 'eye' : 'eye-slash'}
                  size={20}
                  color='#6D5835'
                />
              </TouchableOpacity>
            </View>

            <View style={styles.passwordInput}>
            <TextInput
                style={styles.input1}
                placeholder="Repeat Password"
                secureTextEntry={!showPassword2}
                onChangeText={setRepeatPassword}
                value={repeatPassword}
              />
              <TouchableOpacity style={styles.eyeIcon} onPress={togglePasswordVisibility2}>
                <Icon
                  name={showPassword2 ? 'eye' : 'eye-slash'}
                  size={20}
                  color='#6D5835'
                />
              </TouchableOpacity>
            </View>


           
            <TouchableOpacity 
              onPress={handleCreateAccount} 
              disabled={!isButtonEnabled}
              style={[
                styles.createButton, 
                { backgroundColor: isButtonEnabled ? 'green' : '#3F7E44' }
              ]}>
            






{isLoading ? (
              <ActivityIndicator size="small" color="#FFFFFF" />
            ) : (
              <Text style={[styles.buttonText, (isButtonEnabled || isLoading) && { color: '#FFFFFF' }]}>Create Account</Text>
            )}
            
            </TouchableOpacity>






            
          </View>





          <TouchableOpacity style={styles.returnButton} onPress={navigateToLogin}>
            <Text style={styles.returnButtonText}>Return to Login Page</Text>
          </TouchableOpacity>
        </ScrollView>
      </KeyboardAvoidingView>
    </SafeAreaView>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
  },
  avoidingView: {
    flex: 1,
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
  buttonText: {
    color: '#000000',
    fontWeight: 'bold',
    fontSize: 14,
  },
  headerText: {
    color: '#fff',
    fontSize: 20,
    fontWeight: 'bold',
    marginLeft: 40,
  },
  content: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    padding: 20,
  },
  input1: {
    width: '100%',
    height: 50,
    backgroundColor: '#D2BFA0',
    borderRadius: 20,
    paddingLeft: 40,
    marginBottom: 20,
    color: '#fff',
  },


  input: {
    width: '100%',
    height: 50,
    backgroundColor: '#B29D7D',
    borderRadius: 20,
    paddingLeft: 40,
    marginBottom: 20,
    color: '#fff',
  },
  passwordInput: {
    position: 'relative',
    width: '100%',
    marginBottom: 20,
  },
  eyeIcon: {
    position: 'absolute',
    top: 11,
    right: 20,
  },
  createButton: {
    width: '100%',
    height: 40,
    backgroundColor: '#3F7E44',
    justifyContent: 'center',
    alignItems: 'center',
    borderRadius: 20,
  },
  createButtonText: {
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

export default Createaccount;

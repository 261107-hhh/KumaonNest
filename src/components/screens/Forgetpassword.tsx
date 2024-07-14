
import { useFocusEffect, useNavigation } from '@react-navigation/native';
import React, { useState } from 'react';
import { View, Text, TextInput, TouchableOpacity, StyleSheet, SafeAreaView, ActivityIndicator } from 'react-native';

const Forgotpassword = () => {
    const navigation = useNavigation()
    const [email, setEmail] = useState('');
    const [isLoading, setIsLoading] = useState(false);
  

    useFocusEffect(
      React.useCallback(() => {
       
        setEmail('');
       setIsLoading('');
      }, [])
    );
  
    
 const gotoReset = () => {
  setIsLoading(true);
  setTimeout(() => {
    setIsLoading(false);
    navigation.navigate('Resetpassword');
  }, 2000);
}
        const isButtonEnabled = email.length > 0 ;


  return (
    <SafeAreaView style={styles.container}>
      <View style={styles.header}>
      <TouchableOpacity onPress={() => navigation.goBack()}>
          <Text style={styles.backText}>{'<'}</Text>
        </TouchableOpacity>
        <Text style={styles.headerText}>FORGOT PASSWORD</Text>
      </View>

      <View style={styles.content}>
        <Text style={styles.troubleText}>Trouble Logging in?</Text>
        <Text style={styles.descriptionText}>Enter your email and we'll send you an OTP to reset your password.</Text>
        <TextInput style={styles.input} placeholder="Email" onChangeText={setEmail} value={email}   keyboardType="email-address" />


        <TouchableOpacity onPress ={gotoReset} disabled={!isButtonEnabled}
        style={[
          styles.resetButton, 
          { backgroundColor: isButtonEnabled ? 'green' : 'red' }
        ]}>
                
{isLoading ? (
              <ActivityIndicator size="small" color="#FFFFFF" />
            ) : (
              <Text style={[styles.resetButtonText, (isButtonEnabled || isLoading) && { color: '#FFFFFF' }]}>RESET PASSWORD</Text>
            )}
             </TouchableOpacity>
     

      </View>

      <TouchableOpacity style={styles.returnButton} onPress={()=> navigation.goBack()}>
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
    fontWeight: 'bold',
    marginLeft: 60,
    alignSelf:'center',
    alignContent:'center'
  },
  content: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    padding: 20,
  },
  troubleText: {
    color: 'red',
    fontSize: 22,
    fontWeight: 'bold',
    marginBottom: 10,
  },
  descriptionText: {
    color: 'red',
    fontSize: 16,
    textAlign: 'center',
    marginBottom: 30,
  },
  input: {
    width: '100%',
    height: 40,
     borderWidth: 1,
    borderRadius: 20,
    paddingLeft:20,
    marginBottom: 20,
    borderColor: '#ccc',
    backgroundColor:'#ffffff'
  },
  resetButton: {
    width: '100%',
    height: 40,
    backgroundColor: '#3F7E44',
    justifyContent: 'center',
    alignItems: 'center',
    borderRadius: 20,
    
  },
  resetButtonText: {
    color: '#000000',
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

export default Forgotpassword;

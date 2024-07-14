import React, { useState } from 'react';
import { View, Text, StyleSheet, Image, TouchableOpacity, ScrollView, TextInput } from 'react-native';
import Icon from 'react-native-vector-icons/FontAwesome';
const Categories = () => {
  const [selectedCategory, setSelectedCategory] = useState('Pahadi namak');
  const [searchText, setSearchText] = useState('');

  const handleCategoryPress = (category: string) => {
    setSelectedCategory(category);
  };

  const categories = [
    {
      name: 'Pahadi namak',
      price: 150,
    
    },
    {
      name: 'Aipan utensil set',
      price: 350,
   
    },
    {
      name: 'Kafal (mountain berry)',
      price: 200,
 
    },
    {
      name: 'Pahadi pichodi',
      price: 1300,

    },
    {
      name: 'Pahadi pichodi',
      price: 1300,

    },
  ];

  return (
 
    <ScrollView
        style={{  flex: 1}}
        showsHorizontalScrollIndicator={false}
        showsVerticalScrollIndicator={true}
        automaticallyAdjustKeyboardInsets={true}>
    <View style={styles.container}>

<View style ={styles.searchBox}>
  <View style={{flexDirection:'row'}}>
  

  <View style={{flexDirection:'row'}}>
<TextInput
              style={styles.searchInput}
              placeholder="Search..."
              placeholderTextColor={'#000000'}
              value={searchText}
              onChangeText={setSearchText}
              >     
           
              </TextInput>
           
              <Icon name="search" size={30} color="#fff" style={{ marginTop:40 }}/>
             </View>
            <Icon name="shopping-cart" size={30} color="#fff" style={{ marginTop:40 }}/>
            </View>

<View style={{flexDirection:'row'}}>
          <Image
    source={require('../../utils/assets/images/ic-home.png')}
    style={styles.image2}
  />  
<Text style={styles.overlayText1}>
    Get Your{"\n"}
   Needs!
  </Text>

 
  </View>
  <Image
    source={require('../../utils/assets/images/ic-home4.png')}
    style={styles.image3}
  />  
</View>

      <View style ={styles.firstcontainer}>
  <Image
    source={require('../../utils/assets/images/ic-home1.png')}
    style={styles.image}
  />
  <Text style={styles.overlayText}>
    Most{"\n"}
    loved{"\n"}
    Collection
  </Text>


  <Image
    source={require('../../utils/assets/images/ic-home2.png')}
    style={styles.image1}
  />




      </View>   


<View style={{flexDirection:'column', marginTop:20,  backgroundColor:'#D2BFA0',width:'50%',height:'50%' ,}}>

<Text>
  Categories
</Text>
</View>




      <View style={styles.categoriesContainer}>
        {categories.map((category) => (
          <TouchableOpacity
            key={category.name}
            style={[
              styles.categoryItem,
              selectedCategory === category.name && styles.selectedCategory,
            ]}
            onPress={() => handleCategoryPress(category.name)}>
            <Image source={category.image} style={styles.categoryImage} />
            <Text style={styles.categoryText}>{category.name}</Text>
            <Text style={styles.categoryPrice}>₹{category.price}</Text>
          </TouchableOpacity>
        ))}
      </View>
      {/* Add more content here for the selected category */}
    </View>
    </ScrollView>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#f5f5f5',
   
  },
  searchBox:{
    color:'red',
    backgroundColor:'#6D5835',
    borderBottomLeftRadius:20,
    borderBottomRightRadius:20,
  height:'25%'

  },
  firstcontainer:{
  flexDirection:'row',
  marginHorizontal:10,
    borderWidth:11,
    marginTop:10,
    borderEndStartRadius:10,
    borderColor:'#D2BFA0',
    borderCurve:'continuous',
    borderRadius:20,
    height:'20%',  position: 'relative',


  },
  container2: {
    position: 'relative',
    width: 105,
    height: 127,
  },
  image: {
    width: '50%',
    height: '96%',
    marginTop:5,
    marginLeft:40
  },
  image3: {
    width: '45%',
    height: '60%',
    marginTop:-10,
    alignSelf:'center',
    marginLeft:210
  },
  image2: {
    width: '50%',
    height: '916%',
    marginTop:5,
    color:'#ffffff',

  },
  image1: {
    width: '45%',
    height: '60%',
    marginTop:5,
    alignSelf:'center',
    marginLeft:-20
  },
  overlayText1: {
    position: 'absolute',
    top: -10, // Adjust as needed to center vertically
    left:80, // Adjust as needed to center horizontally
    textAlign: 'center',
    alignSelf:'center',
    alignContent:'center',
    alignItems:'center',
    color: '#ffffff',
    marginTop:40,
    fontSize:30,fontWeight:'700'
  },
  searchInput: {
 color:'#000000',
 paddingLeft:10,
   backgroundColor:'#ffffff',
    marginHorizontal:20,
    width:'70%',
    paddingHorizontal: 10,
    paddingVertical: 5,
    marginBottom: 10,
    borderRadius:20,
    marginTop:40,
  },
  overlayText: {
    position: 'absolute',
    top: 40, // Adjust as needed to center vertically
    left: 10, // Adjust as needed to center horizontally
    textAlign: 'center',
    color: '#ffffff',
    textShadowColor:'red',
    shadowColor:'red',
    fontSize: 18,
    fontWeight: 'bold',
    lineHeight: 24,
    textShadowColor: '#83683C', // Shadow color
    textShadowOffset: { width: 1, height: 1 }, // Offset of the shadow
    textShadowRadius: 3, // Radius of the shadow blur
  },
  title: {
    fontSize: 24,
    fontWeight: 'bold',
    marginBottom: 20,
    textAlign: 'left',
    paddingLeft:10
  },
  categoriesContainer: {
    flexDirection: 'row',
    flexWrap: 'wrap',
    marginTop:20,
    justifyContent: 'space-between',
  },
  categoryItem: {
    width: '48%',
    marginBottom: 20,
    backgroundColor: '#fff',
    borderRadius: 10,
    padding: 15,
    shadowColor: '#000',
    shadowOffset: {
      width: 0,
      height: 2,
    },
    shadowOpacity: 0.25,
    shadowRadius: 3.84,
    elevation: 5,
  },
  selectedCategory: {
    backgroundColor: '#f0f0f0',
  },
  categoryImage: {
    width: '100%',
    height: 150,
    borderRadius: 10,
    marginBottom: 10,
  },
  categoryText: {
    fontSize: 16,
    fontWeight: 'bold',
    marginBottom: 5,
  },
  categoryPrice: {
    fontSize: 14,
    color: '#888',
  },
});

export default Categories;
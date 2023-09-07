package com.example.register.Config;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.management.RuntimeErrorException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.register.Entity.User;
import com.example.register.Exception.BadUserDetailsException;
import com.example.register.Exception.ResourceNotFoundException;
import com.example.register.Repo.UserRepo;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// Load user From database
		System.out.println("loading user from database");
		User findByEmail = this.userRepo.findByEmail(username).orElseThrow(() -> new BadUserDetailsException("User Not Found"));
		return findByEmail;
	}
	
	

//	@Autowired
//	public UserRepo userRepo;
////
//	@Autowired
//	public ModelMapper mapper;
//
//	@Override
//	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//
//		System.out.println("loading user from database");
//		System.out.println(email + " Email to search");
//		try {
//			Optional<User> user = userRepo.findByEmail(email);
//			if (user != null) {
//				System.out.println(user.get().getEmail() + " Got");
//			} else {
//				System.out.println("User Not Present " + user);
//			}
////			System.out.println(userRepo.findByEmail(email).get().getEmail());
//		} catch (UsernameNotFoundException e) {
//			// TODO: handle exception
//			System.out.println("Exception Handel by try UsernameNotFoundException: " + e);
//		}
//		catch (Exception e) {
//			// TODO: handle exception
//			System.out.println(" Thrown by load by name ");
//			throw new RuntimeException("THrown Exception By load By userName");
//		}
////		} catch (NullPointerException e) {
////			// TODO: handle exception
////			System.out.println("Exception Handel by try Null Pointer: " + e);
////		User user = userRepo.findByEmail(email).orElseThrow(() ->  new UsernameNotFoundException("User not found with username: " + email));
////		return user;
////		if (user != null) {
////			return new CustomUserDetails(user);
////		}
////		return org.springframework.security.core.userdetails.User.builder()
////				.username(user.getEmail())
////				.password(user.getPassword())
////				.roles(user.getRole())
////				.build();
//
////		 List<SimpleGrantedAuthority> grantedAuthorities = user.getAuthorities().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList()); // (1)
////	     return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities); // (2)
////		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), grantedAuthorities);
//
////		 List<SimpleGrantedAuthority> collect = this.roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
//
////		throw new UsernameNotFoundException(email + " User not present");
//
////		System.out.println( userRepo.existsByEmail(email)+ ": Exist or Not");
//		Optional<User> us = this.userRepo.findById(1);
//		return mapper.map(us, User.class);
//	}

}

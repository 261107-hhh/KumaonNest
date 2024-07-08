package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.entity.ERole;
import com.example.demo.entity.Role;
import com.example.demo.repository.RoleRepository;


@SpringBootApplication
public class JwtTestApplication 
//implements CommandLineRunner
{
	
	@Autowired
	RoleRepository roleRepository;
	public static void main(String[] args) {
		SpringApplication.run(JwtTestApplication.class, args);
	}
//	@Override
//	public void run(String... args) throws Exception {
//		// TODO Auto-generated method stub
//		
//	}

//	public void run(String... args) throws Exception {
//		try {
//			if (roleRepository.findByName(ERole.ROLE_ADMIN).isEmpty()) {
//				roleRepository.save(new Role(ERole.ROLE_ADMIN));
//			}				
//			if (roleRepository.findByName(ERole.ROLE_MODERATOR).isEmpty()) {
//				roleRepository.save(new Role(ERole.ROLE_MODERATOR));
//			}
//			if (roleRepository.findByName(ERole.ROLE_STAFF).isEmpty()) {
//				roleRepository.save(new Role(ERole.ROLE_STAFF));
//			}
//			if (roleRepository.findByName(ERole.ROLE_USER).isEmpty()) {
//				roleRepository.save(new Role(ERole.ROLE_USER));
//			}
//			
//		}catch(Exception e) {
//			
//			System.out.println("Role already exist");
//			e.printStackTrace();
//			
//		}
//	}
}
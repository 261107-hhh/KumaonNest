package com.example.register.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.register.Dto.UserDto;
import com.example.register.Entity.User;
import com.example.register.Payload.UserResponse;
import com.example.register.Payload.UserUpdateRequest;

public interface UserService {

	UserDto createUser(UserDto userDto);

	boolean checkEmail(String email);

	UserResponse removeUser(int id);

	boolean sendMail(String to, String subject, String body);

	void setOtp(String otp, String mail);

	void updatePassword(String mail, String password);

	UserResponse getAllUsers();

	UserDto getUsers(int id);

	String updateUser(Long id, UserUpdateRequest user);

}

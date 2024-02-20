package com.example.register.Service;

import java.util.List;

import com.example.register.Dto.UserDto;
import com.example.register.Entity.User;
import com.example.register.Payload.UserResponse;

public interface UserService {

	UserDto createUser(UserDto userDto);

	boolean checkEmail(String email);

	void removeUser(UserDto user);

	boolean sendMail(String to, String subject, String body);

	void setOtp(String otp, String mail);

	void updatePassword(String mail, String password);

	UserResponse getAllUsers();

	UserDto getUsers(int id);
}

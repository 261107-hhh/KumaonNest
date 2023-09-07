package com.example.register.Service;

import org.springframework.web.multipart.MultipartFile;

import com.example.register.Dto.UserDto;

public interface UserService {

	UserDto createUser(UserDto userDto);

	boolean checkEmail(String email);

	void removeUser(UserDto user);

	boolean sendMail(String to, String subject, String body);

	void setOtp(String otp, String mail);
}

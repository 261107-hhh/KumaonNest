package com.example.register.Dto;

import java.util.HashSet;
import java.util.Set;

public class UserDto {

	private int id;
	private String name;
	private String email;
	private String password;
	private String phone;
	private String address;
	private Set<RoleDto> role = new HashSet<>();
	private boolean active;
	private boolean verify;
//	private String otpVerify;

	public UserDto() {
		super();
	}

	public UserDto(int id, String name, String email, String password, String phone, String address,
			Set<com.example.register.Dto.RoleDto> role, boolean active, boolean verify) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.address = address;
		this.role = role;
		this.active = active;
		this.verify = verify;
//		this.otpVerify = otpVerify;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Set<RoleDto> getRole() {
		return role;
	}

	public void setRole(Set<RoleDto> role) {
		this.role = role;
	}

	public boolean isVerify() {
		return verify;
	}

	public boolean getverify() {
		return verify;
	}

	public void setVerify(boolean verify) {
		this.verify = verify;
	}

//	public String getOtpVerify() {
//		return otpVerify;
//	}
//
//	public void setOtpVerify(String otpVerify) {
//		this.otpVerify = otpVerify;
//	}

	
}
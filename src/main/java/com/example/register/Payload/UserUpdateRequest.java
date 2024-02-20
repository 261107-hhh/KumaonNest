package com.example.register.Payload;

public class UserUpdateRequest {

	private String name;
	private String email;
	private String password;
	private String phone;
	private String address;
	private boolean active;
	private boolean verify;
	private String role;

	public UserUpdateRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserUpdateRequest(String name, String email, String password, String phone, String address, boolean active,
			boolean verify, String role) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.address = address;
		this.active = active;
		this.verify = verify;
		this.role = role;
	}


	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
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

	public boolean isVerify() {
		return verify;
	}

	public void setVerify(boolean verify) {
		this.verify = verify;
	}

}

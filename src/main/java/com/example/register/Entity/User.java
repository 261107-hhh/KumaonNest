package com.example.register.Entity;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class User implements UserDetails {

	@Id
//	@Column(name = "userId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

//	@Column(name = "userName")
	private String name;

	@Column(unique = true)
	@NotNull
	@Email(message = "Can not be duplicate")
	private String email;

	@Size(min = 8)
	@NotNull
	@Column
	private String password;

	@Size(min = 10, max = 10, message = "Can not be duplicate")
	@NotNull
	@Column(unique = true)
	private String phone;

	@Column
	private String address;

	@NotNull
	@Column(columnDefinition = "boolean default false")
	private boolean active;

	@Column(columnDefinition = "boolean default false")
	private boolean verify;
	
	@Column
	private String otpverify;
	

//	@Column
//	private String role;
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Role> role = new HashSet<>();

//	private Set<GrantedAuthority> authorities;


//	private String verificationCode;

	public User() {
		super();
	}

	public User(int id, String name, @NotNull @Email(message = "Can not be duplicate") String email,
		@Size(min = 8) @NotNull String password,
		@Size(min = 10, max = 10, message = "Can not be duplicate") @NotNull String phone, String address,
		Set<Role> role, @NotNull boolean active, boolean verify, String otpverify) {
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
	this.otpverify = otpverify;
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

	public Set<Role> getRole() {
		return role;
	}



	public void setRole(Set<Role> role) {
		this.role = role;
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
	
	public String getOtpverify() {
		return otpverify;
	}

	public void setOtpverify(String otpverify) {
		this.otpverify = otpverify;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
//		List<SimpleGrantedAuthority> collect = this.roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());

//		return collect;
//		SimpleGrantedAuthority auth = new SimpleGrantedAuthority(this.getRole());
//		return Arrays.asList(auth);
		
		List<SimpleGrantedAuthority> auth = role.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
		return auth;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}

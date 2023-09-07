package com.example.register.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.example.register.Dto.UserDto;
//import com.example.register.Entity.RegisterUser;
import com.example.register.Entity.User;

//@EnableJpaRepositories
@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

	public Optional<User> getByEmail(String email);

	public  Optional<User> findByEmail(String email);

	public boolean existsByEmail(String email);

	public boolean existsByPhone(String phone);
	
	public User getByotpverify(String otp);
	
	public boolean existsByotpverify(String otp);

//	public UserDto save(UserDto userDto);


//	@Query("select u from User u")
//	@Query("UPDATE User u SET otpverify = s WHERE (email = mail)")
//	public void otpverify(String s, String mail);
//	void save(RegisterUser userReg);

}

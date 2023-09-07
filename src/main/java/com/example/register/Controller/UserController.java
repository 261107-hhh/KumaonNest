package com.example.register.Controller;

import java.security.Principal;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.register.Config.UserDetailsServiceImpl;
import com.example.register.Dto.UserDto;
import com.example.register.Entity.User;
import com.example.register.Exception.BadUserDetailsException;
import com.example.register.Exception.BadUserLoginDetailsException;
import com.example.register.Exception.ResourceNotValidException;
import com.example.register.Payload.JwtRequest;
import com.example.register.Payload.Response;
import com.example.register.Repo.UserRepo;
import com.example.register.Security.JwtHelper;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private AuthenticationManager manager;

	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;

	@Autowired
	private JwtHelper jwtHelper;

	@Autowired
	private ModelMapper mapper;
//
	@Autowired
	private UserRepo userRepo;

//	 Authenticate
	private void authenticate(JwtRequest req) throws Exception {

		System.out.println("This is authenticating");
		try {
//				auth = manager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			manager.authenticate(new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword()));
//				System.out.println("Authenticate: "+auth);
		} catch (BadCredentialsException e) {
			throw new BadUserLoginDetailsException("Invaild Username or Password");
		} catch (DisabledException e) {
			throw new  BadUserDetailsException("User is disable");
		} catch (NoSuchElementException e) {
			throw new  BadUserDetailsException("Email is not registered");
		} catch (Exception e) {
			throw new BadUserDetailsException("Invaild Username or Password");
		}

	}

	@PostMapping("/login")
	public ResponseEntity<Response> getDetail(@RequestBody JwtRequest request) throws Exception {

//		Authentication 
		authenticate(request);
		UserDetails userDetail = this.userDetailsServiceImpl.loadUserByUsername(request.getEmail());

		Optional<User> user = userRepo.findByEmail(request.getEmail());

		if (!user.get().isActive()) {
			System.out.println("User is Not Active");
			throw new ResourceNotValidException("User is Not Active");
		} else if (!user.get().isVerify()) {
			System.out.println("User is Not Verified");
			throw new ResourceNotValidException("User is Not Verified");

		} else {

			String token = jwtHelper.generateToken(userDetail);

			Response resp = new Response();
			resp.setToken(token);
			resp.setUser(mapper.map(userDetail, UserDto.class));
			return new ResponseEntity<Response>(resp, HttpStatus.OK);

		}
	}

//	@PreAuthorize("User")
	@GetMapping("/current")
	private String currentUser(Principal p) {
		return p.getName();
//		return "This is current";
	}

//	@PreAuthorize("STAFF")
	@PreAuthorize("hasRole('STAFF') or hasRole('ADMIN') or hasRole('GUEST')")
	@GetMapping("/logout")
	private String logout() {
		return "LOgout called";
//	private String logout(Principal p) {
//		return p.getName();
//		return "This is current";
	}

}

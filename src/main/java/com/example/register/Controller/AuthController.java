package com.example.register.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.register.Entity.ERole;
import com.example.register.Entity.Role;
import com.example.register.Entity.User;
import com.example.register.Payload.request.LoginRequest;
import com.example.register.Payload.request.SignupRequest;
import com.example.register.Payload.response.JwtResponse;
import com.example.register.Payload.response.MessageResponse;
import com.example.register.Repo.RoleRepository;
import com.example.register.Repo.UserRepository;
import com.example.register.Security.JwtUtils;
import com.example.register.Service.Impl.UserDetailsImpl;

import jakarta.validation.Valid;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Value("${kumaonnest.app.admin}")
	private String admin;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
		System.out.println("Sign");
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
		System.out.println("In: " + authentication.getAuthorities().iterator().next().getAuthority());
		String authority = authentication.getAuthorities().iterator().next().getAuthority();

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt;
		if (authority == "ROLE_ADMIN") {
			jwt = jwtUtils.generateJwtTokenAdmin(authentication);
		} else if (authority == "ROLE_MODERATOR") {
			jwt = jwtUtils.generateJwtTokenModerator(authentication);
		} else if (authority == "ROLE_STAFF") {
			jwt = jwtUtils.generateJwtTokenStaff(authentication);
		} else {
			jwt = jwtUtils.generateJwtToken(authentication);
		}
		System.out.println("Hello Authenticated");
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), roles));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		String email = signUpRequest.getEmail();
		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()));

		Set<Role> roles = new HashSet<>();
		logger.info("WOrking Sign Up");

		System.out.println(admin + ": THis is amdin: " + email);
		if (admin.equals(email)) {
			System.out.println(admin + ": THis is amdin111: " + email);

			Role userRole = roleRepository.findByName(ERole.ROLE_ADMIN)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found. 4"));
			roles.add(userRole);
		} else if (email.matches("himanshunainwal0@gmail.com")) {
			Role userRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found. 3"));
			roles.add(userRole);
		} else if (email.matches("@kumaonnest.com") || email.matches("pankukweera5@gmail.com")
				|| email.matches("dhruvkweera77@gmail.com")) {
			Role userRole = roleRepository.findByName(ERole.ROLE_STAFF)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found. 2"));
			roles.add(userRole);
		} else {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found. 1"));
			roles.add(userRole);
		}

		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
}
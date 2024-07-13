//package com.example.register.Controller;
//
//import java.security.Principal;
//import java.util.Iterator;
//import java.util.List;
//import java.util.NoSuchElementException;
//import java.util.Optional;
//import java.util.Set;
//
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.DisabledException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PatchMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.example.register.Config.UserDetailsServiceImpl;
//import com.example.register.Dto.UserDto;
//import com.example.register.Entity.Role;
//import com.example.register.Entity.User;
//import com.example.register.Exception.BadUserDetailsException;
//import com.example.register.Exception.BadUserLoginDetailsException;
//import com.example.register.Exception.ResourceNotValidException;
//import com.example.register.Payload.JwtRequest;
//import com.example.register.Payload.Response;
//import com.example.register.Payload.UserResponse;
//import com.example.register.Payload.UserUpdateRequest;
//import com.example.register.Repo.RoleRepository;
//import com.example.register.Repo.UserRepository;
//import com.example.register.Security.JwtHelper;
//import com.example.register.Service.UserService;
//
//import jakarta.websocket.server.PathParam;
//
//@CrossOrigin
//@RestController
//@RequestMapping("/user")
//public class UserController {
//
//	@Autowired
//	private AuthenticationManager manager;
//
//	@Autowired
//	private UserDetailsServiceImpl userDetailsServiceImpl;
//
//	@Autowired
//	private UserService userService;
//
//	@Autowired
//	private JwtHelper jwtHelper;
//
//	@Autowired
//	private ModelMapper mapper;
////
//	@Autowired
//	private UserRepository userRepo;
//	@Autowired
//	private RoleRepository roleRepo;
//
////	 Authenticate
//	private void authenticate(JwtRequest req) throws Exception {
//
//		System.out.println("This is authenticating");
//		try {
////				auth = manager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//			manager.authenticate(new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword()));
////				System.out.println("Authenticate: "+auth);
//		} catch (BadCredentialsException e) {
//			throw new BadUserLoginDetailsException("Invaild Username or Password");
//		} catch (DisabledException e) {
//			throw new BadUserDetailsException("User is disable");
//		} catch (NoSuchElementException e) {
//			throw new BadUserDetailsException("Email is not registered");
//		} catch (Exception e) {
//			throw new BadUserDetailsException("Invaild Username or Password");
//		}
//
//	}
//
//	@PostMapping("/login")
//	public ResponseEntity<Response> getDetail(@RequestBody JwtRequest request) throws Exception {
//
////		Authentication 
//		authenticate(request);
//		UserDetails userDetail = this.userDetailsServiceImpl.loadUserByUsername(request.getEmail());
//
//		Optional<User> user = userRepo.findByEmail(request.getEmail());
//
//		if (!user.get().isActive()) {
//			System.out.println("User is Not Active");
//			throw new ResourceNotValidException("User is Not Active");
//		} else if (!user.get().isVerify()) {
//			System.out.println("User is Not Verified");
//			throw new ResourceNotValidException("Request for a new Password then try again ");
//		} else {
//
//			String token = jwtHelper.generateToken(userDetail);
//
//			Response resp = new Response();
//			resp.setToken(token);
//			resp.setUser(mapper.map(userDetail, UserDto.class));
//			return new ResponseEntity<Response>(resp, HttpStatus.OK);
//
//		}
//	}
//
//	@GetMapping("/current")
//	private String currentUser(Principal p) {
//		System.out.println(p.getName() + " this is current user");
//		return p.getName();
//
////		return "This is current";
//	}
//
//	@GetMapping("/getAll")
//	private UserResponse getAllUser() {
//		System.out.println(" this is all user");
//		UserResponse u1 = userService.getAllUsers();
//		System.out.println(u1);
//		return u1;
//	}
//
//	@GetMapping("/get/{id}")
//	private UserDto getUser(@PathVariable int id) {
//		System.out.println(" this is get user");
//		UserDto u1 = userService.getUsers(id);
//		System.out.println(u1);
//		return u1;
//	}
//
//	@PutMapping("/update/{id}")
//	private String updateUser(@PathVariable int id, @RequestBody UserUpdateRequest userUpdate) {
//		System.out.println(" this is update user: " + userUpdate.getName() + ":" + userUpdate.getRole() + ":"
//				+ userUpdate.getAddress() + ":" + userUpdate.getEmail() + ":" + userUpdate.getPhone() + ":"
//				+ userUpdate.isActive() + ":" + userUpdate.isVerify());
//
//		String res = userService.updateUser(id, userUpdate);
//		return res;
//
//	}
//
//	@DeleteMapping("/delete/{id}")
//	private UserResponse deleteUser(@PathVariable int id) {
//		return userService.removeUser(id);
//	}
//
//	@GetMapping("/logout")
//	private String logout() {
//		return "LOgout called";
////	private String logout(Principal p) {
////		return p.getName();
////		return "This is current";
//	}
//
//}


package com.example.register.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class  UserController {

	@GetMapping("/home")
	public void Display() {
		System.out.print("THis is User Home");
		
	}
	
}


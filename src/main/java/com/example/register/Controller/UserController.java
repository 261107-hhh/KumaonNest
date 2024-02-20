package com.example.register.Controller;

import java.security.Principal;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.register.Config.UserDetailsServiceImpl;
import com.example.register.Dto.UserDto;
import com.example.register.Entity.Role;
import com.example.register.Entity.User;
import com.example.register.Exception.BadUserDetailsException;
import com.example.register.Exception.BadUserLoginDetailsException;
import com.example.register.Exception.ResourceNotValidException;
import com.example.register.Payload.JwtRequest;
import com.example.register.Payload.Response;
import com.example.register.Payload.UserResponse;
import com.example.register.Payload.UserUpdateRequest;
import com.example.register.Repo.RoleRepository;
import com.example.register.Repo.UserRepo;
import com.example.register.Security.JwtHelper;
import com.example.register.Service.UserService;

import jakarta.websocket.server.PathParam;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private AuthenticationManager manager;

	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;

	@Autowired
	private UserService userService;

	@Autowired
	private JwtHelper jwtHelper;

	@Autowired
	private ModelMapper mapper;
//
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private RoleRepository roleRepo;

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
			throw new BadUserDetailsException("User is disable");
		} catch (NoSuchElementException e) {
			throw new BadUserDetailsException("Email is not registered");
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
			throw new ResourceNotValidException("Request for a new Password then try again ");
		} else {

			String token = jwtHelper.generateToken(userDetail);

			Response resp = new Response();
			resp.setToken(token);
			resp.setUser(mapper.map(userDetail, UserDto.class));
			return new ResponseEntity<Response>(resp, HttpStatus.OK);

		}
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/current")
	private String currentUser(Principal p) {
		System.out.println(p.getName() + " this is current user");
		return p.getName();

//		return "This is current";
	}

//	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/getAll")
	private UserResponse getAllUser() {
		System.out.println(" this is all user");
		UserResponse u1 = userService.getAllUsers();
		System.out.println(u1);
		return u1;
	}

	@GetMapping("/get/{id}")
	private UserDto getUser(@PathVariable int id) {
		System.out.println(" this is get user");
		UserDto u1 = userService.getUsers(id);
		System.out.println(u1);
		return u1;
	}

	@PutMapping("/update/{id}")
	private String updateUser(@PathVariable int id, @RequestBody UserUpdateRequest userUpdate) {
		System.out.println(" this is update user: " + userUpdate.getName() + ":" + userUpdate.getRole());
		UserDto ud = new UserDto();
//		System.out.println(roleRepo.getReferenceById(id)+" role");
//		System.out.println(userRepo.getById(id).get().getRole()+" role");
//		ud.setRole(userUpdate.getRole());
		try {
			Set<Role> s = userRepo.getById(id).get().getRole();
			Iterator<Role> it = s.iterator();

			while (it.hasNext()) {
//				it.next().setId(2222);
				it.next().setId(2222);
				System.out.println("Update Role");
//				System.out.println(it.next().getName()+" Name");
//				System.out.println(it.next().getId() + " rrr");
//				it.next().setName("ROLE_STAFF");
//				it.next().setName("ASDD");

			}
			
		} 
		catch (Exception e) {

			// TODO: handle exception
			System.out.println("Thrown by update: " + e);
		}
		try {
			Set<Role> s1 = userRepo.getById(id).get().getRole();
			Iterator<Role> it1 = s1.iterator();

			while (it1.hasNext()) {
//				it.next().setId(2222);
//				it1.next().setId(2222);
//				System.out.println("Update Role");
//				System.out.println(it1.next().getName()+" Name");
				System.out.println(it1.next().getId() + " rrr");
//				it.next().setName("ROLE_STAFF");
//				System.out.println(it.next().getId() + " rrr");
//				it.next().setName("ASDD");

			}
			
		} 
		catch (Exception e) {

			// TODO: handle exception
			System.out.println("Thrown by update: " + e);
		}
//		Integer.parseInt(userUpdate.getRole())
//		Set<Role> s1 = userRepo.getById(id).get().getRole();
//		Iterator<Role> it1 = s1.iterator();
//		while(it.hasNext()) {
//			System.out.println(it1.next().getId()+" rrr");
////			System.out.println(it1.next().getId()+" rrr");
//		}

//		UserDto u1 = userService.getUsers(id);
//		System.out.println(u1);
		return "Updateddd";
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

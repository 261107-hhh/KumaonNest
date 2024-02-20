package com.example.register.Controller;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.register.Dto.UserDto;
import com.example.register.Entity.User;
import com.example.register.Payload.PasswordChangeRequest;
import com.example.register.Repo.UserRepo;
import com.example.register.Service.UserService;

import ch.qos.logback.core.model.Model;

@CrossOrigin
@RestController
@RequestMapping("/register")
public class HomeController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepo repo;

	@Autowired
	private ModelMapper mapper;

	@PostMapping("/createUser")
	public ResponseEntity<String> registerUserTemp(@RequestBody UserDto userDto) {

		userDto.setEmail(userDto.getEmail().trim());
		userDto.setPassword(userDto.getPassword().trim());
		userDto.setName(userDto.getName().trim());
		userDto.setPhone(userDto.getPhone().trim());

		System.out.println("Register user: " + userDto);

		// Validate the input data
		if (userDto.getName() == null || userDto.getEmail() == null || userDto.getPassword() == null
				|| userDto.getPhone() == null || userDto.getPhone().equals("0000000000")) {
			System.out.println("Not Valid Data");
			return ResponseEntity.badRequest().body("Invalid input data");

		}

		// Check if the email already exists
		else if (repo.existsByEmail(userDto.getEmail())) {
			System.out.println("Email ALready Exist");
			return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists by similar Email!");
		}

		// Check if the phone already exists
		else if (repo.existsByPhone(userDto.getPhone())) {
			System.out.println("Phone ALready Exist");
			return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists by similar Phone!");
		} else {
			UserDto user = userService.createUser(userDto);
			if (user != null) {
				boolean verify = sendMail(user.getEmail());
				if (verify) {
					System.out.println("Registered Success");
					return ResponseEntity.status(HttpStatus.CREATED)
							.body("User registered Suscessfully check Mail to verify");
				} else {
					System.out.println("Removing Registered Data");
					userService.removeUser(user);
					return ResponseEntity.status(HttpStatus.CONFLICT).body("User registered UnSuscessfully");

				}
			}

			else {
				System.out.println("Server not working");
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body("User registered UnSuscessfully try again later.");

			}

		}
	}

	boolean sendMail(String mail) {
		System.out.println("Hi there");
		String otp = getAlphaNumericString(29);
		String to = mail;
		String subject = "Mail Verification";
		String body = "T oversify the email register is verified personal only Otp paste it on" + "browser  " + otp;
		boolean verify = userService.sendMail(to, subject, body);
		userService.setOtp(otp, mail);
		return verify;
	}

	static String getAlphaNumericString(int n) {

		// choose a Character random from this String
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";

		// create StringBuffer size of AlphaNumericString
		StringBuilder sb = new StringBuilder(n);

		for (int i = 0; i < n; i++) {

			// generate a random number between
			// 0 to AlphaNumericString variable length
			int index = (int) (AlphaNumericString.length() * Math.random());

			// add Character one by one in end of sb
			sb.append(AlphaNumericString.charAt(index));
		}

		return sb.toString();
	}

	@GetMapping("/verify/{otp}")
	public ResponseEntity<String> saveUserAfterOtpVerification(@PathVariable("otp") String otp) {
		System.out.println(otp + " OTP IS");
		if (repo.existsByotpverify(otp)) {
			User user = repo.getByotpverify(otp);
			user.setVerify(true);
			user.setActive(true);
			user.setOtpverify("verified");
			repo.save(user);
			System.out.println("Verified user");
			return ResponseEntity.status(HttpStatus.CREATED)
					.body("User Verified");
		}
		return ResponseEntity.status(HttpStatus.CONFLICT)
				.body("Wrong OTP");
	}

	@GetMapping("/request/newotp/{mail}")
	public ResponseEntity<String> newOtp(@PathVariable("mail") String mail) {
		System.out.println(mail + " This is mail");
		if (repo.existsByEmail(mail)) {
			sendMail(mail);
			return ResponseEntity.status(HttpStatus.CREATED)
					.body("Check registered mail");
		} else {
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body("No user found with mail id");
		}
	}
	
	@PostMapping("/request/newpassword")
	public ResponseEntity<String> newOtp(@RequestBody PasswordChangeRequest req ) {
		String mail = req.getEmail();
		System.out.println(req.getEmail() + " This is mail");
		if (repo.existsByEmail(mail)) {
			Optional<User> user = repo.getByEmail(mail);
			if(user.get().getOtpverify().equals(req.getOtp())) {
				userService.updatePassword(mail, req.getPassword());
				return ResponseEntity.status(HttpStatus.CREATED)
						.body("New password has been set");
			}
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body("Otp does not match");
		} else {
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body("No user found with mail id");
		}
	}
	

}
package com.example.register.Service.Impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.register.Exception.ResourceNotFoundException;
import com.example.register.Dto.UserDto;
import com.example.register.Entity.Role;
import com.example.register.Entity.User;
import com.example.register.Repo.RoleRepository;
import com.example.register.Repo.UserRepo;
import com.example.register.Service.UserService;

import jakarta.mail.internet.MimeMessage;

import com.example.register.Exception.*;

@Service
public class UserServiceImpl implements UserService {

	@Value("${spring.mail.username}")
	private String fromEmail;

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	public BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public UserDto createUser(UserDto userDto) {
		// TODO Auto-generated method stub
		User user = mapper.map(userDto, User.class);
		String mail = user.getEmail();
		int roleID = 3333;
		if (mail.matches("admin@gmail.com") && userRepo.count() == 0) {
			roleID = 1111;
		}
		Role role = this.roleRepository.findById(roleID).get();
		user.getRole().add(role);

//		user.setRole(role);
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return mapper.map(userRepo.save(user), UserDto.class);

	}

	@Override
	public boolean checkEmail(String email) {
		// TODO Auto-generated method stub
		return userRepo.existsByEmail(email);
	}

	@Override
	public void removeUser(UserDto user) {
		// TODO Auto-generated method stub
		if (!user.getverify()) {
			userRepo.delete(mapper.map(user, User.class));
				
		}
		System.out.println(user.getEmail());
	}

	@Override
	public void setOtp(String otp, String mail) {
//		Update user with otp
		int userId = userRepo.getByEmail(mail).get().getId();
		User u = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found."));
		u.setOtpverify(otp);
		this.userRepo.save(u);
	}
	
	@Override
	public boolean sendMail(String to, String subject, String body) {
		try {
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();

			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

			mimeMessageHelper.setFrom(fromEmail);
			mimeMessageHelper.setTo(to);
//			mimeMessageHelper.setCc(cc);
			mimeMessageHelper.setSubject(subject);
			mimeMessageHelper.setText(body);

//			for (int i = 0; i < file.length; i++) {
//				mimeMessageHelper.addAttachment(file[i].getOriginalFilename(),
//						new ByteArrayResource(file[i].getBytes()));
//			}
			int flag = 0;
			try {
				javaMailSender.send(mimeMessage);

			} catch (MailSendException e) {
				flag++;
				System.out.println("Error while sending the mail");
			} catch (Exception e) {
				flag++;
				System.out.println("Error while sending the mail or in Authentication");
				
			}
			if(flag == 0 ) {
				return true;
				
			}
			else {
				return false;
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

//	@Override
//	public String addUser(UserDto userDto) {
//		// TODO Auto-generated method stub
//		
////		User u = repo.getByEmail(userDto.getEmail())
////				()->new ResourceNotFoundException("User not found by this Email"));
//		
//		User user = repo.getByEmail(userDto.getEmail());
////		.orElseThrow(()->new ResourceNotFoundException("User not found by this id"));
////		if(user) {
////			return "Already Present";
////		}
//		User user1 = mapper.map(userDto, User.class);
//		repo.save(user1);
//		return user.getName();
//	}
//	
//	@Override
//	public void validateOtp(int otp) {
//		// TODO Auto-generated method stub
//		
////		User u = repo.getByEmail(userDto.getEmail())
////				()->new ResourceNotFoundException("User not found by this Email"));
//		
////		User user = repo.getByEmail(userDto.getEmail());
////		.orElseThrow(()->new ResourceNotFoundException("User not found by this id"));
////		if(user) {
////			return "Already Present";
////		}
////		User user1 = mapper.map(userDto, User.class);
////		repo.save(user1);
////		return user.getName();
//	}
//	
//	
//
////	public void sendMail(String to, int otp) {
////		GEmailsender mailsender = new GEmailsender();
////		
////		String msg = "This is to verify mail " + otp + " is your OTP";
////		String subject = "OTP verification";
////		String from = "kumaunretailstore@gmail.com";
////		boolean ds = mailsender.send(to, from, msg, subject);
////		if (ds) {
////			System.out.println("Email sent Successfully");
////		} else {
////			System.out.println("There is a problem in sending Email");
////
////		}
////	}
//
////	private boolean send(String to, String from, String msg, String subject) {
////		// TODO Auto-generated method stub
////		boolean flag = false;
////		String host = "smtp.gmail.com";
////
////		Properties prop = new Properties();
////		System.out.println("properties " + prop);
////
////		prop.put("mail.smtp.auth", true);
////		prop.put("mail.smtp.starttls.enable", true);
////		prop.put("mail.smtp.host", host);
////		prop.put("mail.smtp.port", "587");
////
////		String username = "kumaunretailstore";
////		String pwd = "asdfghjkl";
////
////		Session session = Session.getInstance(prop, new Authenticator() {
//////			@Override
////			protected PasswordAuthentication getPasswordAuthentication() {
////				return new PasswordAuthentication(username, pwd);
////			}
////
////		});
////
////		try {
////			Message message = new MimeMessage(session);
////			message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
////			message.setFrom(new InternetAddress(from));
////			message.setSubject(subject);
////			message.setText(msg);
////			Transport.send(message);
////			flag = true;
////
////		} catch (Exception e) {
////			// TODO: handle exception
////			e.printStackTrace();
////		}
////		return flag;
////
////	}
//
//	public int generateOTP() {
//		int min = 1000;
//		int max = 10000;
//		int otp = (int) (Math.random() * (max - min + 1) + min);
//		return otp;
//	}
//
////	public boolean verifyOtp(String userEmail, int userInputOtp) {
////		// Get the previously stored OTP for the user
////		Integer storedOtp = otpStorage.get(userEmail);
////
////		if (storedOtp != null && storedOtp == userInputOtp) {
////			// OTP is valid, remove it from storage to prevent reuse
////			otpStorage.remove(userEmail);
////			return true;
////		} else {
////			// Invalid OTP
////			return false;
////		}
////	}

}

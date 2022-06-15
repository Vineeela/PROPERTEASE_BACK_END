package com.arshaa.controller;

import java.io.IOException;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.arshaa.common.EmployeeLogin;
import com.arshaa.common.UserModel;
import com.arshaa.common.Users;
import com.arshaa.entity.PaymentRemainder;
import com.arshaa.service.EmailSender;

@RestController
@RequestMapping("/mail")
public class EmailSenderController {

	@Autowired
	EmailSender emailSender;
	@Autowired
	@Lazy
	private RestTemplate template;

	@PostMapping(value = "/sendmail")
	public void send(@RequestBody UserModel model) throws AddressException, MessagingException, IOException {
		emailSender.sendEmail(model.getName(), model.getUserName(), model.getEmail(), model.getPassword(),
				model.getEmployeeId());
		
	}
//	@PostMapping(value = "/sendmails")
//	public void sent(@RequestBody UserModel model) throws AddressException, MessagingException, IOException {
//		emailSender.sentEmail(model.getName(), model.getUserName(), model.getEmail(), model.getPassword(),
//				model.getEmployeeId());
//		String loginURL="http://loginservice/login/addUsers";
//		String userURL="http://urpService/user/addUser";
//
//		Random rand = new Random();
//		Integer intRandom = rand.nextInt(9999);
//		String userId = model.getName() + intRandom;
//		int n = model.getName().length();
//		char first = model.getName().charAt(0);
//		char last = model.getName().charAt(n - 1);
//		String password = "user" + first + last + intRandom;
//		//System.out.println("Username :" + userId + "Password" + password);
//
//		//Posting data to UserMaster table
//		Users users = new Users();
//		users.setEmployeeId(model.getEmployeeId());
//		users.setUserName(userId);
//		template.postForObject(userURL, users, Users.class);
//		
//		//Posting data to Employee login table
//		EmployeeLogin login = new EmployeeLogin();
//		login.setEmail(model.getEmail());
//		login.setUserName(userId);
//		login.setPassword(password);
//		login.setEmployeeId(model.getEmployeeId());
//		login.setUserType("employee");
//		template.postForObject(loginURL, login, EmployeeLogin.class);
//		
//	}
	//String loginURL="http://loginservice/login/addUsers";

	@PostMapping(value = "/postmail")

	public String send() throws AddressException, MessagingException, IOException {
		emailSender.postMail();

		return "Email Sent Successfully";
	}
	
	@PostMapping("/sendPaymentRemainder")
	public ResponseEntity sendPaymentRemainder(@RequestBody PaymentRemainder sendPayRem)throws AddressException, MessagingException, IOException
	{
		 return emailSender.sendRemainder(sendPayRem.getEmail(),sendPayRem.getName(), sendPayRem.getDueAmount());
	}

}
package com.mybank.bankapp.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ContactController {

	@Autowired
	private JavaMailSender mailSender;
	
	@GetMapping("/contact")
	public String contactForm() {
		return "contact_form";
	}
	@PostMapping(value = "/contact")
	public String submitContactForm(HttpServletRequest request) {
		String fullName=request.getParameter("fullname");
		String Email=request.getParameter("email");
		String Subject=request.getParameter("subjet");
		String Content=request.getParameter("content");
		
		SimpleMailMessage message= new SimpleMailMessage();
		
		message.setFrom("javaspring619@gmail.com");
		message.setTo("benhassenkarim199@gmail.com");
		
		String mailSubject = fullName+" has sent a message";
		String mailContent = "Sender Name: "+fullName+"\n";
		mailContent+="Sender E-mail: "+Email+"\n";
		mailContent+="Subject: "+ Subject +"\n";
		mailContent+="Content: "+ Content +"\n";
		
		message.setSubject(mailSubject);
		message.setText(mailContent);
		mailSender.send(message);
		return"contact_form";
	}
}

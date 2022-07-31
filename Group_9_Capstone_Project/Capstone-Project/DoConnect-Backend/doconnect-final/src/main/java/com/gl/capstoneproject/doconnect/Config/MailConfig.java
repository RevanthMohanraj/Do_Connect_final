package com.gl.capstoneproject.doconnect.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MailConfig {
	
	@Autowired
	private JavaMailSender mail;
	
	@Value("${spring.mail.username}")
	private String fromMail;
	
	public void sendMail(String toEmail, String subject, String body) {
		
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(fromMail);
		message.setTo(toEmail);
		message.setText(body);
		message.setSubject(subject);
		mail.send(message);
	}
}
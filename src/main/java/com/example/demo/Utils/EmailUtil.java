package com.example.demo.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.MimeMessage;

@Component
public class EmailUtil {
	@Autowired
private JavaMailSender mailSender;
	public boolean SendMail(String to,String subject, String body) {
		boolean isSent=false;
		MimeMessage mime=mailSender.createMimeMessage();
		MimeMessageHelper helper=new MimeMessageHelper(mime);
		try {
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(body,true);
		isSent=true;
		mailSender.send(mime);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return isSent;
	}
}

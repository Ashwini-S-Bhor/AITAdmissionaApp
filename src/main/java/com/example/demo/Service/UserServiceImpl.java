package com.example.demo.Service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.User;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Utils.EmailUtil;
import com.example.demo.Utils.PwdUtil;
import com.example.demo.binding.LoginForm;
import com.example.demo.binding.SignUpForm;
import com.example.demo.binding.UnlockForm;

import jakarta.servlet.http.HttpSession;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
 private UserRepository repo;
	@Autowired
	EmailUtil emailUtil;
	@Autowired
	private HttpSession session;
	
@Override
	public boolean SignUp(SignUpForm form) {
	if (repo.existsByUserEmail(form.getUserEmail())) {
	    return false;
	}
	
	User user=new User();
	BeanUtils.copyProperties(form, user);
	
	String tempPassword=PwdUtil.pwdGenerator();
	user.setPassword(tempPassword);
	
	user.setAcc_Status("LOCKED");
	repo.save(user);
	String to=form.getUserEmail();
	String subject="Unlock your account | AIT";
	StringBuffer body=new StringBuffer();
		body.append("<h1>Use below link to unlock your account</h1>");
		body.append("Temprory password:"+tempPassword+"<br>");
		body.append("<a href=\"http://localhost:8081/unlock?email="+to+"\">Click here to unlock your account</a>");
		boolean emailStatus=emailUtil.SendMail(to, subject, body.toString());
	return emailStatus;
	}
@Override
	public boolean unlockAccount(UnlockForm form) {
		Optional<User> user=repo.findByUserEmail(form.getUserEmail());
		if(user.isEmpty()) {
			return false;
		}
		
		User user1=user.get();
		if(user1.getAcc_Status().contentEquals("ACTIVE")) {
			return false;
		}
		
			if(!user1.getPassword().equals(form.getPassword())) {
				return false;
			}
			if(!form.getNewPassword().equals(form.getConfirmPwd())){
				return false;
			}
          user1.setPassword(form.getNewPassword());
          user1.setAcc_Status("ACTIVE");
		
	repo.save(user1);
	
		return true;
	}
	@Override
	public boolean login(LoginForm form) {
		Optional<User>u1=repo.findByUserEmail(form.getUserEmail());
		 if (u1.isEmpty()) {
		        return false;
		    }
	User user=u1.get();
	  System.out.println("DB Email: " + user.getUserEmail());
	  if( user.getAcc_Status().equals("ACTIVE") && user.getPassword().equals(form.getPassword())){
		  
		  session.setAttribute("userId", user.getUserId());
		  
		  return true;
	  }
	    
	return false;
		
	}

	@Override
	public boolean forgotPwd(String email) {
		Optional<User> user=repo.findByUserEmail(email);
		if(user.isEmpty()) {
			return false;
		}
		User u1=user.get();
		String Subject="RecoverPassword";
		String body="Your Password:: "+u1.getPassword();
		emailUtil.SendMail(email, Subject,body);
		
		return true;
}}

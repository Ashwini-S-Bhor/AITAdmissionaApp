package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Service.UserService;
import com.example.demo.binding.LoginForm;
import com.example.demo.binding.SignUpForm;
import com.example.demo.binding.UnlockForm;


@Controller
public class UserController {
	@Autowired
	UserService service;
	
	@PostMapping("/signup")
	public String handleSignUp(@ModelAttribute("user") SignUpForm form,Model model) {
		boolean status=service.SignUp(form);
		if(status)
		{
			model.addAttribute("successMsg","Check Your mail");
		}else
		{
			model.addAttribute("errorMsg","Problem Occured...!!");
		}
		return "signup";
	}
	@PostMapping("/unlock")
	public String unlockForm(@ModelAttribute UnlockForm form) {
		boolean status=service.unlockAccount(form);
		if(status) {
			return "login";
		}
		else {
			return "unlock";
		}
	}
	@GetMapping("/unlock")
	public String UnlockForm(@RequestParam String email,Model model )
	{
		model.addAttribute("userEmail",email);
		return "unlock";
		
	}
	@PostMapping("/login")
	public String loginForm(@ModelAttribute("loginform") LoginForm form) {
		boolean status=service.login(form);
		if(status) {
			
			return "redirect:/dashboard";
		}else
			return "login";
	}
	@GetMapping("/login")
	public String loginForm()
	{
		return "login";
		
	}
	@GetMapping("/signup")
	public String signupForm(Model model) {
		model.addAttribute("user", new SignUpForm());
		return"signup";
	}
	@PostMapping("/forgot")
	public String forgotForm(@RequestParam("email") String email,Model model) {
		boolean status=service.forgotPwd(email);
		if(status) {
			model.addAttribute("succMsg","Password sent to your mail");
		}else {
			model.addAttribute("errMsg","Invalid email");
		}
		return"forgot";
	}

	@GetMapping("/forgot")
	public String forgotForm() {
		return"forgot";
	}

}

package com.example.demo.Service;

import com.example.demo.binding.LoginForm;
import com.example.demo.binding.SignUpForm;
import com.example.demo.binding.UnlockForm;

public interface UserService {
public boolean login(LoginForm form);
public boolean SignUp(SignUpForm form);
public boolean unlockAccount(UnlockForm form);
public boolean forgotPwd(String email);
}

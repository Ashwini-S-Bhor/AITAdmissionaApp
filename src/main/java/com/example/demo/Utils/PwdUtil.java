package com.example.demo.Utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

@Component
public class PwdUtil {
public static String  pwdGenerator() {
	
	String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	String pwd = RandomStringUtils.random( 6, characters );
	System.out.println( pwd );
return pwd;
}
}

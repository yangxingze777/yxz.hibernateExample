package com.yxz.hibernateExample.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Encryptor {
	
	public static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(4);
	
	public static String encrypt(String text) {
		return encoder.encode(text);
	}
	
	public static boolean isMatch(String rawText, String encyptedText) {
		return encoder.matches(rawText,encyptedText);
	}
}

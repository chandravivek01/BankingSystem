package com.vcs.service;

public class OtpGenerator {

	public int otpGenerator() {
		int random = (int) ((Math.random()*9000) + 1000);
		return random;
	}
}

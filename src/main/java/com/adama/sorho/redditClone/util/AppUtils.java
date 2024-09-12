package com.adama.sorho.redditClone.util;

public class AppUtils {
	public static final String EMAIL_SENDER = "redditsorho@email.com";
	public static final String ACTIVATION_EMAIL_SENT = "Activation email sent!!";
	public static final String ACTIVATION_EMAIL_SUBJECT = "Please Activate your Account";
	public static final String USER_REGISTERED_SUCCESSFULLY = "User Registered Successfully";
	public static final String INVALID_TOKEN = "Token is invalid";
	public static final String ACCOUNT_ACTIVATED_SUCCESS = "Account Activated Successfully";
	public static final String USER_NOT_FOUND = "User doesn't exist";
	public static final int REFRESH_TOKEN_EXPIRE_ON = 600000;
	public static final int ACCESS_TOKEN_EXPIRE_IN = 10000000 * 60;
	public static final String ROLE_USER = "USER";

	public static String emailSendException(String email) {
		return "Exception occurred when sending mail to " + email;
	}

	public static String activationEmailBody(String token) {
		return "Thank you for signing up to Reddit Clone, please " +
				"click on the url to activate your account: http://localhost:8080/api/v1/auth/accountVerification/" + token;
	}

	public static String userNotFound(String username) {
		return "User not found with username: " + username;
	}

	public static String notFindException(String resource, String id) {
		return resource + " not found. Id= " + id;
	}

	public static String newCommentBody(String username, String postUrl) {
		return username + " posted a comment on your post. " + postUrl;
	}

	public static String newCommentSubject(String username) {
		return username + " commented on your post";
	}
}

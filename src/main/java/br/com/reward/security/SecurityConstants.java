package br.com.reward.security;

public class SecurityConstants {

    public static final int 	EXPIRATION_TIME = 1; // in hours
    public static final String 	SECRET = "SecretKeyToGenJWTs";
    public static final String 	TOKEN_PREFIX = "Bearer ";
    public static final String 	HEADER_STRING = "Authorization";

    public static final String  API_VERSION = "/api/v1";
    public static final String 	SIGN_UP_URL = API_VERSION + "/users/sign-up";
    public static final String 	LOGIN_URL = API_VERSION + "/login";
    public static final String 	POSTAL_CODES_URL = API_VERSION + "/postal-codes";
    public static final String 	REGISTER_URL = API_VERSION + "/clients";
    
}

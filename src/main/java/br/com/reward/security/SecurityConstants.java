package br.com.reward.security;

public class SecurityConstants {

    public static final String 	TOKEN_PREFIX = "Bearer ";
    public static final String 	HEADER_STRING = "Authorization";

    public static final String  API_URL = "/api/v1";
    public static final String 	LOGIN_URL = API_URL + "/login";

    // Open URLs
    public static final String 	SIGN_UP_URL = API_URL + "/users/sign-up";
    public static final String 	POSTAL_CODES_URL = API_URL + "/postal-codes";
    public static final String 	REGISTER_URL = API_URL + "/clients";
    public static final String  H2_URL = "/h2-console/*";
    public static final String  IDENTIFICATIONS_URL = API_URL + "/identifications";

}

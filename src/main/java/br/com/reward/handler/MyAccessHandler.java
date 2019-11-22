package br.com.reward.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

public class MyAccessHandler implements AccessDeniedHandler, AuthenticationEntryPoint {

	// private final Logger LOG = LoggerFactory.getLogger(MyAccessHandler.class);

	private ObjectMapper objectMapper = new ObjectMapper();
 
    @Override
    public void handle(
      HttpServletRequest request,
      HttpServletResponse response,
      AccessDeniedException exception) 
      throws IOException, ServletException {
  
		ApiError error = new ApiError(
			HttpStatus.FORBIDDEN,
			"Access denied",
			new ArrayList<>());

		response.setStatus(HttpStatus.FORBIDDEN.value());
		response.setContentType("application/json");
 
        response.getOutputStream()
          .println(objectMapper.writeValueAsString(error));
	}
	
	@Override
    public void commence(
			HttpServletRequest request, 
			HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
		ApiError error = new ApiError(
			HttpStatus.FORBIDDEN,
			"Access denied",
			new ArrayList<>());

		response.setStatus(HttpStatus.FORBIDDEN.value());
		response.setContentType("application/json");
		
		response.getOutputStream()
			.println(objectMapper.writeValueAsString(error));
	}
	
	class ApiError {

		private HttpStatus status;
		private String message;
		private List<String> errors;

		public ApiError(HttpStatus status, String message, List<String> errors) {
			super();
			this.status = status;
			this.message = message;
			this.errors = errors;
		}

		public ApiError(HttpStatus status, String message, String error) {
			super();
			this.status = status;
			this.message = message;
			errors = Arrays.asList(error);
		}

		public HttpStatus getStatus() {
			return this.status;
		}

		public String getMessage() {
			return this.message;
		}

		public List<String> getErrors() {
			return this.errors;
		}
	}

}

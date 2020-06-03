package br.com.reward.handler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import org.hibernate.TransactionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.reward.exception.AuthorizationException;
import br.com.reward.exception.NotFoundException;

@ControllerAdvice
public class MyExceptionHandler extends ResponseEntityExceptionHandler {

	private final Logger LOG = LoggerFactory.getLogger(MyExceptionHandler.class);

	private static final String ERROR_MESSAGE = "Application Error";

	// Necessary to access fields if validation error occurs before dispatching request to the controler
	// @InitBinder
    // private void activateDirectFieldAccess(DataBinder dataBinder) {
    //     dataBinder.initDirectFieldAccess();
	// }
	
	/**
	 * This exception is thrown when argument annotated with @Valid failed
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
		MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		LOG.error(ERROR_MESSAGE, ex);
		List<String> errors = new ArrayList<>();

		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			errors.add(error.getField() + ": " + error.getDefaultMessage());
		}
		for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
			errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
		}	

		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "Invalid parameters", errors);
		return handleExceptionInternal(ex, apiError, headers, apiError.getStatus(), request);
	}

	/**
	 * This exception is thrown when request missing parameter
	 */
	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(
		MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		LOG.error(ERROR_MESSAGE, ex);
		String error = ex.getParameterName() + " parameter is missing";
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, error, error);
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	/**
	 * This exception is thrown when constraint validation
	 */
	@ExceptionHandler(value = { DataIntegrityViolationException.class })
	protected ResponseEntity<Object> handleConstraint(RuntimeException ex, WebRequest request) {
		LOG.error(ERROR_MESSAGE, ex);
		List<String> errors = new ArrayList<String>();
		ApiError apiError = new ApiError(HttpStatus.CONFLICT, "Constraint violation", errors);
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	/**
	 * This exception is thrown when transaction timeout
	 */
	@ExceptionHandler(value = { TransactionException.class })
	protected ResponseEntity<Object> handleTransactions(RuntimeException ex, WebRequest request) {
		LOG.error(ERROR_MESSAGE, ex);
		List<String> errors = new ArrayList<String>();
		ApiError apiError = new ApiError(HttpStatus.BAD_GATEWAY, "Transaction timeout", errors);
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	/**
	 * This exception is thrown when http method not supported
	 */
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
		HttpRequestMethodNotSupportedException ex,
		HttpHeaders headers, HttpStatus status, WebRequest request) {
		LOG.error(ERROR_MESSAGE, ex);
		StringBuilder builder = new StringBuilder();
		builder.append(ex.getMethod());
		builder.append(" method is not supported for this request. Supported methods are ");
		ex.getSupportedHttpMethods().forEach(t -> builder.append(t + " "));
		ApiError apiError = new ApiError(HttpStatus.METHOD_NOT_ALLOWED, "HTTP method not supported", builder.toString());
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	/**
	 * This exception is thrown when data not found or not permited operations
	 */
	@ExceptionHandler({ 
		NotFoundException.class, 
		AuthorizationException.class,
		AccessDeniedException.class
	 })
	public ResponseEntity<Object> handleNotFound(Exception ex, WebRequest request) {
		LOG.error(ERROR_MESSAGE, ex);

		ApiError apiError = null;
		if (ex instanceof AuthorizationException || ex instanceof AccessDeniedException) {
			apiError = new ApiError(HttpStatus.FORBIDDEN, ex.getMessage(), "error occurred");
		} else {
			apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage(), "error occurred");
		}
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	/**
	 * This exception is thrown when parameters not found
	 */
	@ExceptionHandler({ 
		MethodArgumentTypeMismatchException.class, 
	 })
	public ResponseEntity<Object> handleInvalidArgument(RuntimeException ex, WebRequest request) {
		LOG.error(ERROR_MESSAGE, ex);
		List<String> errors = new ArrayList<String>();
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "Invalid argument", errors);
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	/**
	 * This exception is thrown when http method not readable 
	 */
	@Override
	public ResponseEntity<Object> handleHttpMessageNotReadable(
		HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		LOG.error(ERROR_MESSAGE, ex);
		List<String> errors = new ArrayList<String>();
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "Invalid Parameters", errors);
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	/**
	 * This exception is thrown when unexpected exceptions
	 */
	@ExceptionHandler({ Exception.class })
	public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
		LOG.error(ERROR_MESSAGE, ex);
		ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error", "error occurred");
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	// @ExceptionHandler({ ConstraintViolationException.class })
	// public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
	// 	List<String> errors = new ArrayList<String>();
	// 	for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
	// 		errors.add(violation.getRootBeanClass().getName() + " " + violation.getPropertyPath() + ": "
	// 				+ violation.getMessage());
	// 	}

	// 	ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
	// 	return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	// }

	// @ExceptionHandler(value = { DataIntegrityViolationException.class })
	// protected ResponseEntity<Object> handleConstraint(RuntimeException ex, WebRequest request) {
	// 	LOG.error(ERROR_MESSAGE, ex);
	// 	String bodyOfResponse = "Constraint exception";
	// 	return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
		
	// }

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

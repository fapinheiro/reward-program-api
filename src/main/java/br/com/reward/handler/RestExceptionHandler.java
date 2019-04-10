// package mt.com.vodafone.handler;

// import org.hibernate.TransactionException;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.dao.DataIntegrityViolationException;
// import org.springframework.http.HttpHeaders;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.ControllerAdvice;
// import org.springframework.web.bind.annotation.ExceptionHandler;
// import org.springframework.web.context.request.WebRequest;
// import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

// @ControllerAdvice
// public class RestExceptionHandler extends ResponseEntityExceptionHandler {

// 	private final Logger LOG = LoggerFactory.getLogger(RestExceptionHandler.class);
	
// 	@ExceptionHandler(value = { DataIntegrityViolationException.class })
// 	protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
// 		LOG.error("Error" , ex);
// 		String bodyOfResponse = "Constraint exception";
// 		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
// 	}
	
// 	@ExceptionHandler(value = { TransactionException.class })
// 	protected ResponseEntity<Object> handleTransactions(RuntimeException ex, WebRequest request) {
// 		LOG.error("Error" , ex);
// 		String bodyOfResponse = "Timeout exception";
// 		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_GATEWAY, request);
// 	}
// }

package com.mindtree.mystayapp.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.mindtree.mystayapp.dto.ErrorResponse;

@ControllerAdvice
public class RestAPIExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(SearchHotelException.class)
	protected ResponseEntity<ErrorResponse> handleEntityNotFound(SearchHotelException ex, HttpServletRequest request) {
		ErrorResponse response = new ErrorResponse();
		response.setErrorCode(ex.getErrorCode());
		response.setMessage(ex.getMessage());
		response.setRequestURI(request.getRequestURI());
		return new ResponseEntity<ErrorResponse>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(BookingException.class)
	public ResponseEntity<ErrorResponse> bookingException(BookingException ex, HttpServletRequest request) {
		ErrorResponse response = new ErrorResponse();
		response.setErrorCode(ex.getErrorCode());
		response.setMessage(ex.getMessage());
		response.setRequestURI(request.getRequestURI());
		return new ResponseEntity<ErrorResponse>(response, HttpStatus.NOT_IMPLEMENTED);
	}

	@ExceptionHandler(ApplicationException.class)
	public ResponseEntity<ErrorResponse> applicationException(ApplicationException ex, HttpServletRequest request) {
		ErrorResponse response = new ErrorResponse();
		response.setErrorCode(ex.getErrorCode());
		response.setMessage(ex.getMessage());
		response.setRequestURI(request.getRequestURI());
		return new ResponseEntity<ErrorResponse>(response, HttpStatus.OK);
	}
	
	@ExceptionHandler(PaymentException.class)
	protected ResponseEntity<ErrorResponse> payemntException(PaymentException ex, HttpServletRequest request) {
		ErrorResponse response = new ErrorResponse();
		response.setErrorCode(ex.getErrorCode());
		response.setMessage(ex.getMessage());
		response.setRequestURI(request.getRequestURI());
		return new ResponseEntity<ErrorResponse>(response, HttpStatus.NOT_IMPLEMENTED);
	}
	@ExceptionHandler(Exception.class) 
	public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex, HttpServletRequest request) {
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setMessage("Please contact your administrator");
		error.setRequestURI(request.getRequestURI());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.INTERNAL_SERVER_ERROR);

	}
}

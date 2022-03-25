package com.amdocs.exception;

import java.util.Date;
import java.util.NoSuchElementException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

@RestControllerAdvice
public class MyControllerAdvice {

	@ExceptionHandler(EmptyInputException.class)
	public ResponseEntity<ErrorDetails> handleEmptyInput(EmptyInputException emptyInputException) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), "Input fileds are empty",
				emptyInputException.getLocalizedMessage());
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<ErrorDetails> handleNoSuchElementException(NoSuchElementException noSuchElementException) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(),
				"No Such element is present ! Please check your Request", noSuchElementException.getMessage());
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	protected ResponseEntity<ErrorDetails> handleHttpRequestMethodNotSupported(
			HttpRequestMethodNotSupportedException ex) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), "Method not Supported", ex.getLocalizedMessage());
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<ErrorDetails> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), "Validation error",
				ex.getBindingResult().getFieldError().getDefaultMessage());
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<ErrorDetails> handleEmptyResultDataAccessException(
			EmptyResultDataAccessException emptyResultDataAccessException) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), "No Such element is present ! Please Change",
				emptyResultDataAccessException.getLocalizedMessage());
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<ErrorDetails> handleNullPointerException(NullPointerException nullPointerException) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), "No Such element  is present!",
				nullPointerException.getLocalizedMessage());
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(InvalidFormatException.class)
	protected ResponseEntity<ErrorDetails> handleInvalidFormatException(InvalidFormatException ex) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), "Validation error", ex.getOriginalMessage());
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.BAD_REQUEST);
	}

}

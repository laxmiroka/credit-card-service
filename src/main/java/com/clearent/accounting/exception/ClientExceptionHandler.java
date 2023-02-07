package com.clearent.accounting.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ClientExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<ClientErrorResponse> handleException(ClientNotFoundException exec) {
		ClientErrorResponse error = new ClientErrorResponse();

		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(exec.getMessage());
		error.setTimeStamp(System.currentTimeMillis());

		return new ResponseEntity<ClientErrorResponse>(error, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler
	public ResponseEntity<ClientErrorResponse> handleException(Exception exec) {
		ClientErrorResponse error = new ClientErrorResponse();

		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage(exec.getMessage());
		error.setTimeStamp(System.currentTimeMillis());

		return new ResponseEntity<ClientErrorResponse>(error, HttpStatus.BAD_REQUEST);

	}

}

package com.flabedu.peoplemeet.web.common;

import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.flabedu.peoplemeet.exception.ApplicationException;

import lombok.Getter;

@RestControllerAdvice
public class GlobalController {

	@ExceptionHandler(ApplicationException.class)
	public ResponseEntity<Error> applicationExceptionHandler(ApplicationException exception) {
		return ResponseEntity.badRequest().body(Error.from(exception));
	}

	@Getter
	static class Error {

		private String message;

		public static Error from(ApplicationException exception) {
			Error error = new Error();

			error.message = exception.getMessage();

			return error;
		}
	}

}

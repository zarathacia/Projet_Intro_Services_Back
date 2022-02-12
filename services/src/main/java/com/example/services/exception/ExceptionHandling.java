package com.example.services.exception;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.example.services.exception.domain.EmailExistException;
import com.example.services.exception.domain.EmailNotFoundException;
import com.example.services.exception.domain.UserNotFoundException;
import com.example.services.exception.domain.UsernameExistException;
import com.example.services.model.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.persistence.NoResultException;
import java.io.IOException;
import java.util.Objects;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class ExceptionHandling implements ErrorController {
	private Logger LOGGER = LoggerFactory.getLogger(getClass());
	private static final String ACCOUNT_LOCKED = "Your account has been locked. Please contact administration";
	private static final String METHOD_IS_NOT_ALLOWED = "This request method is not allowed on this endpoint. Please send a '%s' request";
	private static final String INTERNAL_SERVER_ERROR_MSG = "An error occurred while processing the request";
	private static final String INCORRECT_CREDENTIALS = "Username / password incorrect. Please try again";
	private static final String ACCOUNT_DISABLED = "Your account has been disabled. If this is an error, please contact administration";
	private static final String ERROR_PROCESSING_FILE = "Error occurred while processing file";
	private static final String NOT_ENOUGH_PERMISSION = "You do not have enough permission";
	private static final String ERROR_PATH = "/error";

	@ExceptionHandler(DisabledException.class)
	public ResponseEntity<Response> accountDisabledException() {
		return createResponse(BAD_REQUEST, ACCOUNT_DISABLED);
	}

	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<Response> badCredentialsException() {
		return createResponse(BAD_REQUEST, INCORRECT_CREDENTIALS);
	}

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<Response> accessDeniedException() {
		return createResponse(FORBIDDEN, NOT_ENOUGH_PERMISSION);
	}

	@ExceptionHandler(LockedException.class)
	public ResponseEntity<Response> lockedException() {
		return createResponse(UNAUTHORIZED, ACCOUNT_LOCKED);
	}

	@ExceptionHandler(TokenExpiredException.class)
	public ResponseEntity<Response> tjokenExpiredException(TokenExpiredException exception) {
		return createResponse(UNAUTHORIZED, exception.getMessage().toUpperCase());
	}

	@ExceptionHandler(EmailExistException.class)
	public ResponseEntity<Response> emailExistException(EmailExistException exception) {
		return createResponse(BAD_REQUEST, exception.getMessage());
	}

	@ExceptionHandler(UsernameExistException.class)
	public ResponseEntity<Response> usernameExistException(UsernameExistException exception) {
		return createResponse(BAD_REQUEST, exception.getMessage());
	}

	@ExceptionHandler(EmailNotFoundException.class)
	public ResponseEntity<Response> emailNotFoundException(EmailNotFoundException exception) {
		return createResponse(BAD_REQUEST, exception.getMessage());
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Response> userNotFoundException(UserNotFoundException exception) {
		return createResponse(BAD_REQUEST, exception.getMessage());
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<Response> noHandlerFoundException(NoHandlerFoundException exception) {
		return createResponse(BAD_REQUEST, "There is no page for a " + exception.getHttpMethod()+ " request on path " + exception.getRequestURL());
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<Response> methodNotSupportedException(HttpRequestMethodNotSupportedException exception) {
		HttpMethod supportedMethod = Objects.requireNonNull(exception.getSupportedHttpMethods()).iterator().next();
		return createResponse(METHOD_NOT_ALLOWED, String.format(METHOD_IS_NOT_ALLOWED, supportedMethod));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Response> internalServerErrorException(Exception exception) {
		LOGGER.error(exception.getMessage());
		return createResponse(INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR_MSG);
	}

	@ExceptionHandler(NoResultException.class)
	public ResponseEntity<Response> notFoundException(NoResultException exception) {
		LOGGER.error(exception.getMessage());
		return createResponse(NOT_FOUND, exception.getMessage());
	}

	@ExceptionHandler(IOException.class)
	public ResponseEntity<Response> iOException(IOException exception) {
		LOGGER.error(exception.getMessage());
		return createResponse(INTERNAL_SERVER_ERROR, ERROR_PROCESSING_FILE);
	}

	private ResponseEntity<Response> createResponse(HttpStatus httpStatus, String message) {
		return new ResponseEntity<>(new Response(httpStatus.value(), httpStatus,
				httpStatus.getReasonPhrase().toUpperCase(), message.toUpperCase()), httpStatus);
	}

	@RequestMapping(ERROR_PATH)
	public ResponseEntity<Response> pageNotFound() {
		return createResponse(NOT_FOUND, "This page doesn't exist");
	}

	@Override
	public String getErrorPath() {
		return ERROR_PATH;
	}
}

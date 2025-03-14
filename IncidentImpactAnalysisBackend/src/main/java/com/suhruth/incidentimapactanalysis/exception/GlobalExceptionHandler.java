package com.suhruth.incidentimapactanalysis.exception;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<Map<String, Object>> handleNotFound(NoHandlerFoundException ex, HttpServletRequest request) {

		Map<String, Object> errorDetails = new LinkedHashMap<>();
		errorDetails.put("timestamp", LocalDateTime.now());
		errorDetails.put("status", HttpStatus.NOT_FOUND.value());
		errorDetails.put("error", "Not Found");
		errorDetails.put("message", "The requested URL was not found on this server.");
		errorDetails.put("path", request.getRequestURL().toString().replace(request.getRequestURI(), "")
				+ "/incident-impact-analysis?page=1");

		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(PageLimitExceededException.class)
	public ResponseEntity<Map<String, Object>> handlePageLimitExceeded(PageLimitExceededException ex,
			HttpServletRequest request) {
		Map<String, Object> errorDetails = new LinkedHashMap<>();
		errorDetails.put("timestamp", LocalDateTime.now());
		errorDetails.put("status", HttpStatus.NOT_FOUND.value());
		errorDetails.put("error", "Page Limit Exceeded");
		errorDetails.put("message", ex.getMessage());
		errorDetails.put("path", request.getRequestURL().toString().replace(request.getRequestURI(), "")
				+ "/incident-impact-analysis?page=1");

		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, Object>> handleGenericException(Exception ex, HttpServletRequest request) {
		Map<String, Object> errorDetails = new LinkedHashMap<>();
		errorDetails.put("timestamp", LocalDateTime.now());
		errorDetails.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorDetails.put("error", "Internal Server Error");
		errorDetails.put("message", ex.getMessage());
		errorDetails.put("path", request.getRequestURL().toString().replace(request.getRequestURI(), "")
				+ "/incident-impact-analysis?page=1");

		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

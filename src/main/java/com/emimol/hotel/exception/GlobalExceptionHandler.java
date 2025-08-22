package com.emimol.hotel.exception;

import com.emimol.hotel.constants.ErrorCode;
import com.emimol.hotel.dto.ErrorRS;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<ErrorRS> handleMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
		ErrorRS ErrorRS = new ErrorRS(HttpStatus.METHOD_NOT_ALLOWED.value(), ErrorCode.METHOD_NOT_ALLOWED,
				ex.getMessage());

		return new ResponseEntity<>(ErrorRS, HttpStatus.METHOD_NOT_ALLOWED);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ErrorRS> handleIllegalArgumentException(IllegalArgumentException ex) {
		ErrorRS ErrorRS = new ErrorRS(HttpStatus.BAD_REQUEST.value(), ErrorCode.BAD_REQUEST,
				ex.getMessage());

		return new ResponseEntity<>(ErrorRS, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorRS> handleValidationExceptions(MethodArgumentNotValidException ex) {
		StringBuilder details = new StringBuilder();
		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			details.append(error.getDefaultMessage()).append("; ");
		}

		ErrorRS ErrorRS = new ErrorRS(HttpStatus.BAD_REQUEST.value(), ErrorCode.VALIDATION_FAILED,
				details.toString().trim());

		return new ResponseEntity<>(ErrorRS, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ErrorRS> handleHttpMessageNotReadableExceptions(HttpMessageNotReadableException ex) {
		Throwable cause = ex.getCause();

		if (cause instanceof InvalidFormatException invalidFormatException) {
			Class<?> targetType = invalidFormatException.getTargetType();

			if (targetType.isEnum()) {
				Object[] enumConstants = targetType.getEnumConstants();
				String allowedValues = Arrays.stream(enumConstants).map(Object::toString)
						.collect(Collectors.joining(", "));

				String fieldName = invalidFormatException.getPath().stream()
						.map(JsonMappingException.Reference::getFieldName).collect(Collectors.joining("."));

				String errorMessage = "Invalid value for field '" + fieldName + "'. Allowed values are: "
						+ allowedValues;

				ErrorRS ErrorRS = new ErrorRS(HttpStatus.BAD_REQUEST.value(),
						ErrorCode.VALIDATION_FAILED, errorMessage);

				return new ResponseEntity<>(ErrorRS, HttpStatus.BAD_REQUEST);
			}
		}

		log.error("Exception: ", ex);
		ErrorRS ErrorRS = new ErrorRS(HttpStatus.BAD_REQUEST.value(), ErrorCode.VALIDATION_FAILED,
				"An unexpected error occurred");

		return new ResponseEntity<>(ErrorRS, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(DateTimeParseException.class)
	public ResponseEntity<ErrorRS> handleDateTimeParseException(DateTimeParseException ex) {
		ErrorRS ErrorRS = new ErrorRS(HttpStatus.BAD_REQUEST.value(), ErrorCode.VALIDATION_FAILED,
				"Invalid date format. Please use yyyy-MM-dd");

		return new ResponseEntity<>(ErrorRS, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorRS> handleGenericException(Exception ex) {
		ErrorRS ErrorRS = new ErrorRS(HttpStatus.INTERNAL_SERVER_ERROR.value(),
				 ErrorCode.INTERNAL_ERROR, "An unexpected error occurred");

		log.error("Exception: ", ex);

		return new ResponseEntity<>(ErrorRS, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

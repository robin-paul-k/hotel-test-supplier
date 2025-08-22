package com.emimol.hotel.dto;

import com.emimol.hotel.constants.ErrorCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class ErrorRS {
	private boolean success = false;
	private int status;
	private ErrorCode error;
	private String message;
	private String timestamp;

	public ErrorRS(int status, ErrorCode error, String message) {
		this.status = status;
		this.error = error;
		this.message = message;
		this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
	}
}

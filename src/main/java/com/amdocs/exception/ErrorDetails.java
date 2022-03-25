package com.amdocs.exception;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
public class ErrorDetails  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date errorTime;
	private String errorCode;
	private String errorMessage;
	
	public ErrorDetails() {
		
	}

	public ErrorDetails(Date errorTime, String errorCode, String errorMessage) {
		super();
		this.errorTime = errorTime;
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	
}

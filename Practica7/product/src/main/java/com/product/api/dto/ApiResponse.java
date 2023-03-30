package com.product.api.dto;

public class ApiResponse {

    private String message;

    public ApiResponse(String message) {
        this.message = message;
    }

    public ApiResponse() {
        
    }

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}

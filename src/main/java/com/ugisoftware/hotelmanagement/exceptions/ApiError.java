package com.ugisoftware.hotelmanagement.exceptions;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ApiError {
    private HttpStatus httpStatus;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime createdAt;
    private List<String> message;
   

    public ApiError(Builder builder) {
        this.httpStatus = builder.httpStatus;
        this.createdAt = builder.createdAt;
        this.message = builder.message;
    }

    static class Builder {
        private HttpStatus httpStatus;
        private  List<String> message;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
        private LocalDateTime createdAt;
       

        public Builder(){}

        public Builder withHttpStatus(HttpStatus httpStatus) {
            this.httpStatus = httpStatus;
            return this;
        }
        public Builder withCreatedAt() {
            this.createdAt = LocalDateTime.now();
            return this;
        }
        public Builder withMessage( List<String> message) {
            this.message = message;
            return this;
        }
     
        public ApiError build(){
            return new ApiError(this);
        }

    }

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public  List<String> getMessage() {
		return message;
	}

	public void setMessage( List<String> message) {
		this.message = message;
	}

	

}
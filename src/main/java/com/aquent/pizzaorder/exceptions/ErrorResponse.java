package com.aquent.pizzaorder.exceptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ErrorResponse {
	private String errorMessage;
	
	private List<String> validationMessages = new ArrayList<>();
	
	private String errorCode;
	
	private long timestamp = (new Date()).getTime();
	
	private String type;
	
	private String id;

	private Map<String,Object> detailMap;
	
    private HttpStatus status;
	
    private HttpHeaders headers = new HttpHeaders();
	
    private Throwable cause;
	
	private List<String> errors;

	public ErrorResponse(List<String> errors) {
		this();
		this.errors = errors;
	}

	public ErrorResponse(String... errors) {
		this(Arrays.asList(errors));
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	public ErrorResponse() { 
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public ErrorResponse(HttpStatus status) {
        this.status = status;
    }

    public ErrorResponse(String message) {
        this(Collections.singletonList(message));
        this.setErrorMessage(message);
    }

    public ErrorResponse(String message, HttpStatus status) {
        this.setErrorMessage(message);
        this.setStatus(status);
    }
	
    public ErrorResponse(Throwable thrown) {
        this();
		this.setErrorMessage(thrown.getMessage());
		this.setType(thrown.getClass().getName());
        //this.parseException(thrown);
        this.cause = thrown;
	}

    public List<String> getValidationMessages() {
        return validationMessages;
    }

    public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setDetails(Map<String,Object> details) {
		this.detailMap = details;
	}
	public Map<String,Object> getDetails() {
		return this.detailMap;
	}

    /**
     * @param status The status code to use in the HTTP response to the client. This will not be included in the body. 
     */
    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    /**
     * @return
     */
    public HttpStatus getStatus() {
        return this.status;
    }
    
    public void addValidationMessage(String message) {
        if(StringUtils.isNotBlank(message)) {
            this.validationMessages.add(message);
        }
    }

    public void addValidationMessages(String message, String...messages) {
        this.addValidationMessage(message);
        if(messages!=null) {
            for(String msg : messages) {
                this.addValidationMessage(msg);
            }
        }
    }

    public void addValidationMessages(List<String> msgList) {
        if(msgList!=null) {
            for(String msg : msgList) {
                this.addValidationMessage(msg);
            }
        }
    }

    /**
     * 
     * @param messages
     */
    public void setValidationMessages(String message, String...messages) {
        List<String> newList = new ArrayList<>();
        newList.add(message);
        if(messages!=null && messages.length>0) {
            Collections.addAll(newList, messages);
        }
        this.validationMessages = newList;
    }

    /**
     * 
     * @param messageList
     */
    public void setValidationMessages(List<String> messageList) {
        if(messageList!=null) {
            this.validationMessages = new ArrayList<>(messageList);
        }
    }

    /**
     * @return Headers that should be included in the HTTP response.
     */
    @JsonIgnore
    public HttpHeaders getHeaders() {
        return this.headers;
    }

    /**
     * Throws out current headers and replaces them with the argumented headers.
     * @param headers Headers that will be added to the HTTP response. These will not be included in the response body.
     */
    @JsonIgnore
    public void setHeaders(HttpHeaders headers) {
        this.headers = headers;
    }

    /**
     * Adds the argumented headers to the current headers.
     * @param headers Headers that will be added to the HTTP response. These will not be included in the response body.
     */
    @JsonIgnore
    public void addHeaders(HttpHeaders headers) {
        if(headers!=null) {
            this.headers.putAll(headers);
        }
    }

    @Override
    public String toString() {
        String ls = System.getProperty("line.separator");
        StringBuilder str = new StringBuilder();
        str.append("message="+this.getErrorMessage()+ls);
        if(StringUtils.isNotBlank(this.errorCode)) {
            str.append(ls+"code="+this.errorCode);
        }
        if(StringUtils.isNotBlank(this.type)) {
            str.append(ls+"type="+this.type);
        }
        if(StringUtils.isNotBlank(this.id)) {
            str.append(ls+"id="+this.id);
        }
        if(this.cause!=null) {
            str.append(ls+"stacktrace="+ ExceptionUtils.getStackTrace(this.cause));
        }
        if(this.validationMessages!=null && !this.validationMessages.isEmpty()) {
            str.append(ls+"validationMessages=["+String.join(ls+"    ",this.validationMessages)+ls+"]");
        }
        if(this.detailMap!=null && !this.detailMap.isEmpty()) {
            str.append(ls+"detailMap="+this.detailMap.toString());
        }
        return str.toString();
    }
}

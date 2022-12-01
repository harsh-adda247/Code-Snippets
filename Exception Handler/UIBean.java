package com.studyIQ.api.cms.liveclass.ui.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/*
    Response bean for live classes.
 */
public class UIBean<T> implements Serializable{

	@JsonProperty("data")
	private T data;

    private boolean success;

    private String message;

    private String response;

    public T getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}
}


package entity;

import java.io.Serializable;

public class MessageResult implements Serializable{
	
	private String message;
	
	private boolean success;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public MessageResult() {
		super();
	}
	
}

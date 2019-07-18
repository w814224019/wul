package com.select.wuliu.controller.exeception;

public class UserAlreadyExistsException extends RequestException {

	/**
	 * 用户已经存在异常
	 */
	private static final long serialVersionUID = 1L;

	public UserAlreadyExistsException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserAlreadyExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public UserAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public UserAlreadyExistsException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public UserAlreadyExistsException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}

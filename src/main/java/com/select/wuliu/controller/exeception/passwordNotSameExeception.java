package com.select.wuliu.controller.exeception;

public class passwordNotSameExeception extends RequestException{

	/**
	 * 输入密码前后不一致异常
	 */
	private static final long serialVersionUID = 1L;

	public passwordNotSameExeception() {
		super();
		// TODO Auto-generated constructor stub
	}

	public passwordNotSameExeception(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public passwordNotSameExeception(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public passwordNotSameExeception(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public passwordNotSameExeception(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	
	
	
	
}

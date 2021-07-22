package com.nt.rookie.post.exception;

public class SomethingNotFoundException extends BusinessException {
	
	public SomethingNotFoundException() {}
	
	public SomethingNotFoundException(String msg) {super(msg);}
	
	public SomethingNotFoundException(String msg, Throwable e) { super(msg, e);}
	
	public SomethingNotFoundException(String errorCode, String msg) {
		super(errorCode,msg);
	}
	
	public SomethingNotFoundException(String errorCode, String msg, Throwable e) {
		super(errorCode,msg, e);
	}

}

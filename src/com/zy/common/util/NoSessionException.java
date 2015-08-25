package com.zy.common.util;

/**
 * NoSessionException
 * @author Pingan
 *
 * @since  2015年6月6日
 */

public class NoSessionException extends RuntimeException {

	private static final long serialVersionUID = 8989462372749355980L;

	public NoSessionException() {
		super();
	}

	public NoSessionException(String s) {
		super(s);
	}

	public NoSessionException(String s, Throwable throwable) {
		super(s, throwable);
	}

	public NoSessionException(Throwable throwable) {
		super(throwable);
	}
}

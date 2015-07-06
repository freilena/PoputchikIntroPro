package com.intropro.academy.kkalashnykova.poputchik;

public class PoputchikAlreadyExistsException extends Exception {
	public PoputchikAlreadyExistsException() {

	}

	public PoputchikAlreadyExistsException(String message) {
		super(message);
	}

	public PoputchikAlreadyExistsException(Throwable cause) {
		super(cause);
	}

	public PoputchikAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
	}

}

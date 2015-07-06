package com.intropro.academy.kkalashnykova.poputchik;

public class PoputchikDoesNotExistException extends Exception {
	public PoputchikDoesNotExistException() {

	}

	public PoputchikDoesNotExistException(String message) {
		super(message);
	}

	public PoputchikDoesNotExistException(Throwable cause) {
		super(cause);
	}

	public PoputchikDoesNotExistException(String message, Throwable cause) {
		super(message, cause);
	}
}

package org.riot;

public class ResponseException extends Exception {
	/**
	 * If the server returns an error response code
	 */
	private static final long serialVersionUID = -6303501740276544817L;
	public ResponseException() {}
	public ResponseException(String message) { super(message); }
}

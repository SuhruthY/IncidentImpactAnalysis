package com.suhruth.incidentimapactanalysis.exception;

public class PageLimitExceededException extends RuntimeException {

	private static final long serialVersionUID = 5786515488014188350L;

	public PageLimitExceededException(String message) {
		super(message);
	}
}

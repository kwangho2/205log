package com._205log.api.exception;

/**
 * status -> 404
 */
public class PostNotFound extends _205logException {

    private static final String MESSAGE = "존재하지 않는 글입니다.";

    public PostNotFound() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 404;
    }
}

package com._205log.api.exception;

import lombok.Getter;

/**
 * status -> 401
 */
@Getter
public class Unauthorized extends _205logException {

    private static final String MESSAGE = "인증이 필요합니다.";

    public Unauthorized() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 401;
    }
}

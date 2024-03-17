package com._205log.api.exception;

public class AlreadyExistsEmailException extends _205logException{

    public static final String MESSAGE = "이미 가입된 이메일입니다.";

    public AlreadyExistsEmailException() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 400;
    }
}

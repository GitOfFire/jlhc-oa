package com.jlhc.common.exception;

public class ApplicationStartupVaildException extends Exception {
    public ApplicationStartupVaildException() {
    }

    public ApplicationStartupVaildException(String message) {
        super(message);
    }

    public ApplicationStartupVaildException(Throwable cause) {
        super(cause);
    }

    public ApplicationStartupVaildException(String message, Throwable cause) {
        super(message, cause);
    }
}

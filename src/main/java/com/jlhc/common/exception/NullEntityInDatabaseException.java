package com.jlhc.common.exception;

public class NullEntityInDatabaseException extends Exception{
    public NullEntityInDatabaseException(){

    }

    public NullEntityInDatabaseException(String message) {
        super(message);
    }

    public NullEntityInDatabaseException(Throwable cause) {
        super(cause);
    }

    public NullEntityInDatabaseException(String message, Throwable cause) {
        super(message, cause);
    }
}

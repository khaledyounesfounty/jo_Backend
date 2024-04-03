package com.jo.paris2024.exception;

import org.springframework.dao.DataAccessException;

import java.sql.SQLException;

public class LinkedToAnotherResourceException extends DataAccessException {
    public LinkedToAnotherResourceException(String message) {
        super(message);
    }

    public LinkedToAnotherResourceException(String message, Throwable cause) {
        super(message, cause);
    }

}

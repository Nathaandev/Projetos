package com.example.cadastropet.Response;

import java.util.Date;

public class ExceptionMessage {
    private Date CurrentDate;
    private String ErrorMessage;
    private String Exception;

    public ExceptionMessage() {
    }

    public ExceptionMessage(Date currentDate, String errorMessage, String exception) {
        CurrentDate = currentDate;
        ErrorMessage = errorMessage;
        Exception = exception;
    }

    public Date getCurrentDate() {
        return CurrentDate;
    }

    public void setCurrentDate(Date currentDate) {
        CurrentDate = currentDate;
    }

    public String getErrorMessage() {
        return ErrorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        ErrorMessage = errorMessage;
    }

    public String getException() {
        return Exception;
    }

    public void setException(String exception) {
        Exception = exception;
    }
}

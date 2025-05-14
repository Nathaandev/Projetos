package com.example.cadastropet.Response;

import java.util.Date;

public class ExceptionMessage {
    private Date CurrentDate;
    private String ErrorMessage;

    public ExceptionMessage() {
    }

    public ExceptionMessage(Date currentDate, String errorMessage) {
        CurrentDate = currentDate;
        ErrorMessage = errorMessage;
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
}

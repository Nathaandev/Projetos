package com.example.cadastropet.Exceptions;

public class AgeHigherThan19Exception extends RuntimeException {
    public AgeHigherThan19Exception(String message) {
        super(message);
    }
}

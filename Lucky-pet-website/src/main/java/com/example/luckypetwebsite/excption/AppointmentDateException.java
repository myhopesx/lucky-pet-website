package com.example.luckypetwebsite.excption;

public class AppointmentDateException extends RuntimeException{
    public AppointmentDateException(String message) {
        super(message);
    }
}

package dev.pprotsiv.travel.exception;

public class IllegalDateException extends IllegalArgumentException{
    public IllegalDateException() {
    }

    public IllegalDateException(String s) {
        super(s);
    }
}

package dev.pprotsiv.travel.exception;

public class BookedRoomsExceptions extends RuntimeException{
    public BookedRoomsExceptions() {
    }

    public BookedRoomsExceptions(String message) {
        super(message);
    }
}

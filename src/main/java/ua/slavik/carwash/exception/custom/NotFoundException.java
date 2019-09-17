package ua.slavik.carwash.exception.custom;

public class NotFoundException extends RuntimeException {
    public NotFoundException() {
        super("Object with provided id not found");
    }
}

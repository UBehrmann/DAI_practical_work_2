package ch.heigvd.dai;

public enum ErrorCodes {
    MISSING_ARGUMENTS("ERROR 1 -Missing arguments"),

    USER_NOT_FOUND("ERROR 2 -User name doesn't exist"),
    USER_ALREADY_EXISTS("ERROR 3 -User name already taken"),
    USER_NOT_CONNECTED_TO_SERVER("ERROR 4 -User not connected to server"),
    USER_NOT_CONNECTED_TO_ROOM("ERROR 4 -User not connected to room"),


    ROOM_NOT_FOUND("ERROR 5 -Room name dosen't existe"),
    ROOM_ALREADY_EXISTS("ERROR 6 -Room name already taken"),

    PASSWORD_WRONG("ERROR 7 -Wrong password"),

    STORAGE_FAILED("ERROR 7 -Failed to store, please try again");

    private final String message;

    ErrorCodes(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}


package java_hw2;

class InvalidDataFormatException extends Exception {
    public InvalidDataFormatException(String message) {
        super(message);
    }
}

class DataFormatException extends Exception {
    public DataFormatException(String message) {
        super(message);
    }
}

class FileWriteException extends Exception {
    public FileWriteException(String message, Throwable cause) {
        super(message, cause);
    }
}
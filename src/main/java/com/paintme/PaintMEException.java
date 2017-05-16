package com.paintme;

public class PaintMEException extends Exception
{
    public PaintMEException() {}

    public PaintMEException(String message)
    {
        super(message);
    }

    public PaintMEException (Throwable cause) {
        super (cause);
    }

    public PaintMEException (String message, Throwable cause) {
        super (message, cause);
    }
}

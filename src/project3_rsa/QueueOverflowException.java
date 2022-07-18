package project3_rsa;

public class QueueOverflowException extends RuntimeException
{
    public QueueOverflowException()
    {
        //Display the message from the runtime exception class to the user
        super();
    }
    
    public QueueOverflowException(String message)
    {
        //Display the over written message from the runtime exception class to the user
        super(message);
    }
    
}

package project3_rsa;

public class QueueUnderflowException extends RuntimeException
{
    public QueueUnderflowException()
    {
        //Display the message from the runtime exception class to the user
        super();
    }
    
    public QueueUnderflowException(String message)
    {
        //Display the over written message from the runtime exception class to the user
        super(message);
    }
}

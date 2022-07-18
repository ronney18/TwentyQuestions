
package project3_rsa;

//The stackoverflow exception is a subclass from the runtime exception
public class StackOverflowException extends RuntimeException
{
    public StackOverflowException()
    {
        //Display the message from the runtime exception class to the user
        super();
    }
    
    public StackOverflowException(String message)
    {
        //Display the over written message from the runtime exception class to the user
        super(message);
    }
}

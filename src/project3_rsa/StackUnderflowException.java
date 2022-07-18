
package project3_rsa;

//The stack underflow exception is a subclass from the runtime exception
public class StackUnderflowException extends RuntimeException 
{
    public StackUnderflowException()
    {
        //Display the message from the runtime exception class to the user
        super();
    }
    
    public StackUnderflowException(String message)
    {
        //Display the over written message from the runtime exception class to the user
        super(message);
    }
}

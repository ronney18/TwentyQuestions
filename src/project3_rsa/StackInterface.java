
package project3_rsa;

//These are all the abstract methods in order to be a stack
public interface StackInterface<T>
{
    //Include the method to push an object to the stack
    void push(T element) throws StackOverflowException;
    //Include the method to pop an object from the stack
    void pop() throws StackUnderflowException;
   //Include the method to take a peek of the last object from the stack
    T top() throws StackUnderflowException;
    //Include the method to determine if the stake is full
    boolean isFull();
    //Include the method to determine if the stake is empty
    boolean isEmpty();
}

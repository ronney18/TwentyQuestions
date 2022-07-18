
package project3_rsa;

//Creating a Linked List
public class LinkedStack<T> implements StackInterface<T> 
{
    //Declare a link variable for the top
    private LLNode<T> top;
    
    //Creating a Linked List constructor
    public LinkedStack()
    {
        //Assign null to the top link
        top = null;
    }
    
    //Pushing a node to the linked stack
    @Override
    public void push(T element)
    {
        //Created a linked node object
        LLNode<T> newNode = new LLNode<>(element);
        //Set the new node to the top link
        newNode.setLink(top);
        //Assign the new node to the top link
        top = newNode;
    }

    //Popping a node to the linked stack
    @Override
    public void pop() throws StackUnderflowException 
    {
        //Throw an exception if the array is empty
        if(isEmpty())
        {
            //Throw an error to the user
            throw new StackUnderflowException("Pop attempted on empty stack.");
        }
        
        else
        {
            //Assign the next link to the top link
            top = top.getLink();
        }
    }

    //Taking a peek at the node
    @Override
    public T top() throws StackUnderflowException 
    {
        //Throw an exception if the array is empty
        if(isEmpty())
        {
            //Throw an error to the user
            throw new StackUnderflowException("Top attempted on empty stack.");
        }
        
        else
        {
            //Return the node's information to the user
            return top.getInfo();
        }
    }

    //Checking if the linked list is full
    @Override
    public boolean isFull() 
    {
        //Always return false, the linked list never gets full
        return false;
    }

    //Checking if the linked list is empty
    @Override
    public boolean isEmpty() 
    {
        //Return true if the top link is equal to null otherwise return false
        return (top == null);
    }
}

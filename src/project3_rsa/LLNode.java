
package project3_rsa;

//Creating a node for Linked lists
public class LLNode<T>
{
    //Declare an information object
    protected T info;
    //Declare a link handle to the node
    protected LLNode<T> link;
    
    //Creating a node constructor
    public LLNode(T info)
    {
        //Assign the user's information to the information object
        this.info = info;
        //Assign null to the link handle
        link = null;
    }

    //Getting the object's information
    public T getInfo() 
    {
        //Return the object's information to the user
        return info;
    }

    //Setting the object's information
    public void setInfo(T info) 
    {
        //Set the user's information to the object's information
        this.info = info;
    }

    //Getting the link node
    public LLNode<T> getLink() 
    {
        //Return the link handle to the user
        return link;
    }

    //Setting the link node
    public void setLink(LLNode<T> link) 
    {
        //Set the user's link to the link handle
        this.link = link;
    }
}

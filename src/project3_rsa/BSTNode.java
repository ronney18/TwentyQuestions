
package project3_rsa;

public class BSTNode<T> 
{
    //The node info
    private T info;
    //A link to the left child node
    private BSTNode<T> left;
    //A link to the right child node
    private BSTNode<T> right;

    public BSTNode(T info) 
    {
        //Assign the info to the variable
        this.info = info;
        //Assign null to the left node
        left = null;
        //Assign null to the right node
        right = null;
    }

    public T getInfo() 
    {
        //Return the info to the user
        return info;
    }

    public void setInfo(T info) 
    {
        //Assign the info to the variable
        this.info = info;
    }

    public BSTNode<T> getLeft() 
    {
        //Return the left node to the user
        return left;
    }

    public void setLeft(BSTNode<T> left) 
    {
        //Assign the left node to the variable
        this.left = left;
    }

    public BSTNode<T> getRight() 
    {
        //Return the right node to the user
        return right;
    }

    public void setRight(BSTNode<T> right) 
    {
        //Assign the right node to the variable
        this.right = right;
    }
}

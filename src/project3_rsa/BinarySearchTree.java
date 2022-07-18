//---------------------------------------------------------------------------
// BinarySearchTree.java          by Dale/Joyce/Weems               Chapter 7
//
// Defines all constructs for a reference-based BST.
// Supports three traversal orders Preorder, Postorder & Inorder ("natural")
//---------------------------------------------------------------------------
package project3_rsa;

import java.util.*;
public class BinarySearchTree<T> implements BSTInterface<T> 
{

    protected BSTNode<T> root;      // reference to the root of this BST
    protected Comparator<T> comp;   // used for all comparisons
    protected boolean found;   // used by remove

    public BinarySearchTree() // Precondition: T implements Comparable
    // Creates an empty BST object - uses the natural order of elements.
    {
        root = null;
        comp = new Comparator<T>() 
        {
            @Override
            public int compare(T element1, T element2) 
            {
                return ((Comparable) element1).compareTo(element2);
            }
        };
    }

    public BinarySearchTree(Comparator<T> comp) // Creates an empty BST object - uses Comparator comp for order
    // of elements.
    {
        root = null;
        this.comp = comp;
    }

    @Override
    public boolean isFull() // Returns false; this link-based BST is never full.
    {
        return false;
    }

    @Override
    public boolean isEmpty() // Returns true if this BST is empty; otherwise, returns false.
    {
        return (root == null);
    }

    @Override
    public T min() // If this BST is empty, returns null;
    // otherwise returns the smallest element of the tree.
    {
        if(isEmpty()) 
        {
            return null;
        } 
        else 
        {
            BSTNode<T> node = root;
            while (node.getLeft() != null) 
            {
                node = node.getLeft();
            }
            return node.getInfo();
        }
    }

    @Override
    public T max() // If this BST is empty, returns null;
    // otherwise returns the largest element of the tree.
    {
        if(isEmpty()) 
        {
            return null;
        } 
        else 
        {
            BSTNode<T> node = root;
            while (node.getRight() != null) 
            {
                node = node.getRight();
            }
            return node.getInfo();
        }
    }

    private int recSize(BSTNode<T> node) // Returns the number of elements in subtree rooted at node.
    {
        if(node == null) 
        {
            return 0;
        } 
        else 
        {
            return 1 + recSize(node.getLeft()) + recSize(node.getRight());
        }
    }

    @Override
    public int size() // Returns the number of elements in this BST.
    {
        return recSize(root);
    }

    public int size2() // Returns the number of elements in this BST.
    {
        int count = 0;
        if(root != null) 
        {
            LinkedStack<BSTNode<T>> nodeStack = new LinkedStack<>();
            BSTNode<T> currNode;
            nodeStack.push(root);
            while(!nodeStack.isEmpty()) 
            {
                currNode = nodeStack.top();
                nodeStack.pop();
                count++;
                if(currNode.getLeft() != null) 
                {
                    nodeStack.push(currNode.getLeft());
                }
                if(currNode.getRight() != null) 
                {
                    nodeStack.push(currNode.getRight());
                }
            }
        }
        return count;
    }

    private boolean recContains(T target, BSTNode<T> node) // Returns true if the subtree rooted at node contains info i such that 
    // comp.compare(target, i) == 0; otherwise, returns false.
    {
        if(node == null) 
        {
            return false;       // target is not found
        } 
        else if(comp.compare(target, node.getInfo()) < 0) 
        {
            return recContains(target, node.getLeft());   // Search left subtree
        } 
        else if(comp.compare(target, node.getInfo()) > 0) 
        {
            return recContains(target, node.getRight());  // Search right subtree
        } 
        else 
        {
            return true;        // target is found
        }
    }

    @Override
    public boolean contains(T target) // Returns true if this BST contains a node with info i such that 
    // comp.compare(target, i) == 0; otherwise, returns false.
    {
        return recContains(target, root);
    }

    private T recGet(T target, BSTNode<T> node) // Returns info i from the subtree rooted at node such that 
    // comp.compare(target, i) == 0; if no such info exists, returns null.
    {
        if(node == null) 
        {
            return null;             // target is not found
        } 
        else if(comp.compare(target, node.getInfo()) < 0) 
        {
            return recGet(target, node.getLeft());         // get from left subtree
        } 
        else 
        {
            if(comp.compare(target, node.getInfo()) > 0) 
            {
                return recGet(target, node.getRight());        // get from right subtree
            } 
            else 
            {
                return node.getInfo();  // target is found
            }
        }
    }

    @Override
    public T get(T target) // Returns info i from node of this BST where comp.compare(target, i) == 0;
    // if no such node exists, returns null.
    {
        return recGet(target, root);
    }

    private BSTNode<T> recAdd(T element, BSTNode<T> node) // Adds element to tree rooted at node; tree retains its BST property.
    {
        if(node == null) // Addition place found
        {
            node = new BSTNode<>(element);
        } 
        else if(comp.compare(element, node.getInfo()) <= 0) 
        {
            node.setLeft(recAdd(element, node.getLeft()));    // Add in left subtree
        } 
        else 
        {
            node.setRight(recAdd(element, node.getRight()));   // Add in right subtree
        }
        return node;
    }

    @Override
    public boolean add(T element) // Adds element to this BST. The tree retains its BST property.
    {
        root = recAdd(element, root);
        return true;
    }

    /*
  public boolean add (T element)
  // Adds element to this BST. The tree retains its BST property.
  {
    BSTNode<T> newNode = new BSTNode<T>(element);
    BSTNode<T> prev = null, curr = null;
    
    if (root == null)
      root = newNode;
    else
    {
      curr = root;
      while (curr != null)
      {
        if (comp.compare(element, curr.getInfo()) <= 0)
        {
          prev = curr;
          curr = curr.getLeft();
        }
        else
        {
          prev = curr;
          curr = curr.getRight();
        }
      }
      if (comp.compare(element, prev.getInfo()) <= 0)
        prev.setLeft(newNode);
      else
        prev.setLeft(newNode);
    }
    return true;
  }
     */
    private T getPredecessor(BSTNode<T> subtree) // Returns the information held in the rightmost node of subtree
    {
        BSTNode<T> temp = subtree;
        while(temp.getRight() != null) 
        {
            temp = temp.getRight();
        }
        return temp.getInfo();
    }

    private BSTNode<T> removeNode(BSTNode<T> node) // Removes the information at node from the tree. 
    {
        T data;
        if(node.getLeft() == null) 
        {
            return node.getRight();
        } 
        else if(node.getRight() == null) 
        {
            return node.getLeft();
        } 
        else 
        {
            data = getPredecessor(node.getLeft());
            node.setInfo(data);
            node.setLeft(recRemove(data, node.getLeft()));
            return node;
        }
    }

    private BSTNode<T> recRemove(T target, BSTNode<T> node) // Removes element with info i from tree rooted at node such that
    // comp.compare(target, i) == 0 and returns true; 
    // if no such node exists, returns false. 
    {
        if(node == null) 
        {
            found = false;
        } 
        else if(comp.compare(target, node.getInfo()) < 0) 
        {
            node.setLeft(recRemove(target, node.getLeft()));
        } 
        else if(comp.compare(target, node.getInfo()) > 0) 
        {
            node.setRight(recRemove(target, node.getRight()));
        } 
        else 
        {
            node = removeNode(node);
            found = true;
        }
        return node;
    }

    @Override
    public boolean remove(T target) // Removes a node with info i from tree such that comp.compare(target,i) == 0
    // and returns true; if no such node exists, returns false.
    {
        root = recRemove(target, root);
        return found;
    }
    
    @Override
    public void balance()
    {
        //Create an array list with T elements
        ArrayList<T> array = new ArrayList<>(0);
        //Assign the inorder traversal to the iterator
        Iterator iter = this.getIterator(Traversal.Inorder);
        //Initialize the index to 0
        int index = 0;
        
        while(iter.hasNext())
        {
            //Add the current iterator traversal to the array list
            array.add(index, (T) iter.next());
            //Increment the index by 1
            index++;
        }
        //Assign null to the root
        root = null;
        //Insert the current element to the tree
        insertTree(array, 0, index - 1);
    }
    
    private void insertTree(ArrayList<T> array, int low, int high)
    {
        //Add the low bound of the array to the tree if loww is equal to high
        if(low == high)
        {
            //Add the low bound of the array to the tree
            this.add(array.get(low));
        }
        
        //If the low bound is one less than the high bound
        else if((low + 1) == high)
        {
            //Add the low bound of the array to the tree
            this.add(array.get(low));
            //Add the high bound of the array to the tree
            this.add(array.get(high));
        }
        
        else
        {
            //Mid bound is the sum of the low and high bound over 2
            int mid = (low + high) / 2;
            //Add the mid bound of the array to the tree
            this.add(array.get(mid));
            //Insert the low and one less of the mid bound of the array to the tree
            insertTree(array, low, mid - 1);
            //Insert one more of the mid and high bound of the array to the tree
            insertTree(array, mid + 1, high);
        }
    }
    
    @Override
    public Iterator<T> getIterator(BSTInterface.Traversal orderType) // Creates and returns an Iterator providing a traversal of a "snapshot" 
    // of the current tree in the order indicated by the argument.
    // Supports Preorder, Postorder, and Inorder traversal.
    {
        final LinkedQueue<T> infoQueue = new LinkedQueue<>();
        if(orderType == BSTInterface.Traversal.Preorder) 
        {
            preOrder(root, infoQueue);
        } 
        else 
        {
            if(orderType == BSTInterface.Traversal.Inorder) 
            {
                inOrder(root, infoQueue);
            } 
            else
            {
                if(orderType == BSTInterface.Traversal.Postorder) 
                {
                    postOrder(root, infoQueue);
                }
            }
        }

        return new Iterator<T>() 
        {
            @Override
            public boolean hasNext() // Returns true if the iteration has more elements; otherwise returns false.
            {
                return !infoQueue.isEmpty();
            }

            @Override
            public T next() // Returns the next element in the iteration.
            // Throws NoSuchElementException - if the iteration has no more elements
            {
                if (!hasNext()) 
                {
                    throw new IndexOutOfBoundsException("illegal invocation of next "
                            + " in BinarySearchTree iterator.\n");
                }
                return infoQueue.dequeue();
            }

            @Override
            public void remove() // Throws UnsupportedOperationException.
            // Not supported. Removal from snapshot iteration is meaningless.
            {
                throw new UnsupportedOperationException("Unsupported remove attempted on "
                        + "BinarySearchTree iterator.\n");
            }
        };
    }

    private void preOrder(BSTNode<T> node, LinkedQueue<T> q) // Enqueues the elements from the subtree rooted at node into q in preOrder.
    {
        if(node != null) 
        {
            q.enqueue(node.getInfo());
            preOrder(node.getLeft(), q);
            preOrder(node.getRight(), q);
        }
    }

    private void inOrder(BSTNode<T> node, LinkedQueue<T> q) // Enqueues the elements from the subtree rooted at node into q in inOrder.  
    {
        if(node != null) 
        {
            inOrder(node.getLeft(), q);
            q.enqueue(node.getInfo());
            inOrder(node.getRight(), q);
        }
    }

    private void postOrder(BSTNode<T> node, LinkedQueue<T> q) // Enqueues the elements from the subtree rooted at node into q in postOrder.  
    {
        if(node != null) 
        {
            postOrder(node.getLeft(), q);
            postOrder(node.getRight(), q);
            q.enqueue(node.getInfo());
        }
    }

    @Override
    public Iterator<T> iterator() // InOrder is the default, "natural" order.
    {
        return getIterator(BSTInterface.Traversal.Inorder);
    }
}

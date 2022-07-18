
package project3_rsa;

//Include all the files from the file input/output library
import java.io.*;
//Include all the files from the util library
import java.util.*;

public class TreeBuilder<T>
{
    //Store the root of the tree to a variable
    private BSTNode<T> root;
    //Store the array list to a variable
    private ArrayList<T> array;
    //Store the number of guesses to a variable
    private int guesses;
    
    public TreeBuilder()
    {
        //Assign null to the root of the tree
        root = null;
        //Create a new array list
        array = new ArrayList();
        //Initialize the number of guesses to 0
        guesses = 20;
    }
    
    public void printTree(String target)
    {
        //Search the tree for duplicate questions in a different path
        array = searchTree(root, target);
        
        //If the array is not empty
        if(array != null)
        {
            System.out.println("There is a node with the same content in a different path.");
            //Loop through every elements of the array from finish to start
            for(int i = array.size() - 1; i >= 0 ; i--)
            {
                //Display the current element to the screen
                System.out.print(array.get(i));
                
                //Display an arrow if the current element is not the last element
                if(i != 0)
                {
                    //Display an arrow
                    System.out.print(" >> ");
                }
            }
            //Display a new line
            System.out.println("\n");
        }
    }
    
    private ArrayList<T> searchTree(BSTNode<T> node, String target)
    {
        //Create a new array
        ArrayList<T> myArray = new ArrayList();
        //Return null if the node is null
        if(node == null)
        {
            return null;
        }
        
        else
        {
            //Add the context of the node to the array if the info is equal to the target
            if(node.getInfo().equals(target))
            {
                //Add the context of the node to the array
                myArray.add(node.getInfo());
            }
            
            else
            {
                //Search again to the left side of the subtree with the target
                myArray = searchTree(node.getLeft(), target);
                
                //Search again to the right side of the sub tree if the array is null
                if(myArray == null)
                {
                    //Search again to the right side of the sub tree
                    myArray = searchTree(node.getRight(), target);
                }
                
                else
                {
                    //Add the context of the node to the array
                    myArray.add(node.getInfo());
                }
                
            }
            //Return the array to the user
            return myArray;
        }
    }
    
    public void readFile(String filename)
    {
        try
        {
            //Create a new file object
            File myFile = new File(filename);
            //Create a scanner to read from the file
            Scanner info = new Scanner(myFile);
            //Store the context from the file to the root
            root = recReadFile(info);
        }
        //Display an error if the file is not found
        catch(FileNotFoundException exeption)
        {
            System.out.println("Sorry! The file " + filename + " is not found.");
        }
    }
    
    private BSTNode<T> recReadFile(Scanner info)
    {
        //Assign null to the new node
        BSTNode<T> newNode = null;
        //Use the first line in the file as the delimiter
        String delimiter = info.nextLine();
        //Use the next line as the context
        String context = info.nextLine();
        
        if(delimiter.equals("Guess?"))
        {
            //Assign a node with its context to the new node
            newNode = new BSTNode(context);
            //Read the next lines from the file to set the right link of the new node
            newNode.setRight(recReadFile(info));
            //Read the next lines from the file to set the left link of the new node
            newNode.setLeft(recReadFile(info));
        }
        else
        {
            //Assign a node with its context to the new node
            newNode = new BSTNode(context);
        }
        //Return the new node to the user
        return newNode;
    }
    public BSTNode<T> getPrevious(BSTNode<T> node)
    {
        //Return the previous node from the tree to the user
        return recGetPrevious(root, node);
    }
    
    private BSTNode<T> recGetPrevious(BSTNode<T> current, BSTNode<T> node) 
    {
        //Assign null to the search node
        BSTNode<T> searchNode = null;
        
        //Return null if the current node is null
        if(current == null)
        {
            return null;
        }
        else
        {
            //Return the current node if either the left or right link is equal to the node
            if((current.getLeft() == node) || (current.getRight() == node))
            {
                //Return the current node to the user
                return current;
            }
            else
            {
                //Continue to search for the node using the left link of the current node
                searchNode = recGetPrevious(current.getLeft(), node);
                
                //Search for the node using the left link of the current node if the search node is null 
                if(searchNode == null)
                {
                    //Search for the node using the left link of the current node 
                    searchNode = recGetPrevious(current.getRight(), node);
                }
                //Return the search node to the user
                return searchNode;   
            }   
        }
    }
    
    public void playGame()
    {
        //Read the file from the file extension
        readFile("20 Question Tree.txt");
        //Declare an input
        String input = " ";
        //Create a scanner to insert inputs to the console
        Scanner scan = new Scanner(System.in);
        //Assign the root to the current node
        BSTNode<T> current = root;
        
        //The game continues until the user decides to type "EXIT"
        while(!(input.equals("EXIT")))
        {
            //Making guesses until an answer is guessed
            if(((current.getLeft() != null) || (current.getRight() != null)) && (guesses > 0))
            {
                //Display the number of guesses left
                System.out.println("Number of guesses left: " + guesses);
                //Display the info of the current node
                System.out.println(current.getInfo());
                do
                {
                    //Prompt the user for a yes or no answer
                    System.out.println("Press 'Y' for Yes or 'N' for No");
                    //Store the input to the variable
                    input = scan.nextLine();
                    
                    //Display an error if the input is not a yes nor a no
                    if((!(input.equals("Y"))) && (!(input.equals("y"))) && 
                        (!(input.equals("N"))) && (!(input.equals("n"))))
                    {
                        //Display the error
                        System.out.println("This is a yes or no question!");
                    }
                //Validate the user's input
                }while((!(input.equals("Y"))) && (!(input.equals("y"))) && 
                        (!(input.equals("N"))) && (!(input.equals("n"))));
                
                //Decrement the number of guesses by 1
                guesses--;
                        
                if((input.equals("N")) || (input.equals("n")))
                {
                    //Assign the left link to the current node if the user answered "No"
                    current = current.getLeft();
                }
                else
                {
                    //Assign the left link to the current node if the user answered "Yes"
                    current = current.getRight();
                }
            }
            else
            {
                if(guesses > 0)
                {
                    //Display the guessed answer to the screen
                    System.out.println(current.getInfo());
                    System.out.println("Did I made the right guess?");
                    do
                    {
                        //Prompt the user for a yes or no answer
                        System.out.println("Press 'Y' for Yes or 'N' for No");
                        //Store the input to the variable
                        input = scan.nextLine();
                    
                        //Display an error if the input is not a yes nor a no
                        if((!(input.equals("Y"))) && (!(input.equals("y"))) && 
                            (!(input.equals("N"))) && (!(input.equals("n"))))
                        {
                            //Display the error
                            System.out.println("This is a yes or no question!");
                        }
                    //Validate the user's input
                    }while((!(input.equals("Y"))) && (!(input.equals("y"))) && 
                            (!(input.equals("N"))) && (!(input.equals("n"))));
                
                    if((input.equals("Y")) || (input.equals("y")))
                    {
                        //The player wins if they guessed correctly
                        System.out.println("Awesome!");
                    }
                }
                
                if((input.equals("N")) || (input.equals("n")) || (guesses <= 0))
                {
                    String secretAnswer = " ";
                    String question = " ";
                    //Prompt the user of what answer they were thinking of
                    System.out.println("What were you thinking of that I didn't thought?");
                    //Store the answer to the secret answer
                    secretAnswer = scan.nextLine();
                    //Prompt the user for a question to lead to their answer
                    System.out.println("\nCan you give me a question that would help me lead to your answer?");
                    //Store the question to a variable
                    question = scan.nextLine();
                    //Print the tree of the secret answer if the same answer exist in a different path
                    printTree(secretAnswer);
                    
                    //Create a new node to hold the new question
                    BSTNode<T> newNode = new BSTNode(question);
                    //Set the new node right link to the secret answer node
                    newNode.setRight(new BSTNode(secretAnswer));
                    //Set the new node's left link to the current node
                    newNode.setLeft(current);
                    //Assign the current's previous node to the previous node
                    BSTNode<T> previous = getPrevious(current);
                    
                    //Set the previous node's left link to the new node if the left link is equal to the current node
                    if(previous.getLeft() == current)
                    {
                        //Set the previous node's left link to the new node
                        previous.setLeft(newNode);
                    }
                    
                    else
                    {
                        //Set the previous node's right link to the new node
                        previous.setRight(newNode);
                    }
                    //Save the modified tree to the current file
                    fileWrite("20 Question Tree.txt");
                }
                
                System.out.println("\nWould like to continue playing?");
                
                do
                {
                    //Prompt the user for a yes or no answer
                    System.out.println("Press 'Y' for Yes or 'N' for No");
                    //Store the input to the variable
                    input = scan.nextLine();
                    
                    //Display an error if the input is not a yes nor a no
                    if((!(input.equals("Y"))) && (!(input.equals("y"))) && 
                        (!(input.equals("N"))) && (!(input.equals("n"))))
                    {
                        //Display the error
                        System.out.println("This is a yes or no question!");
                    }
                //Validate the user's input
                }while((!(input.equals("Y"))) && (!(input.equals("y"))) && 
                        (!(input.equals("N"))) && (!(input.equals("n"))));
                
                if(input.equals("Y")|| input.equals("y"))
                {
                    //Reassign the root to the current node if the user wants to play again
                    current = root;
                    //Reset the number of guesses to 20
                    guesses = 20;
                }
                else
                {
                    //Set the input to "EXIT" to exit the game
                    input = "EXIT";
                }
            }      
        }
    }
    
    public void fileWrite(String filename)
    {
        try
        {
            //Create a file to read and write
            File myFile = new File(filename);
            myFile.createNewFile();
            //Create a new file writer with the file passed on
            FileWriter outputFile = new FileWriter(myFile);
            //Create a buffer writer to add more context to the existing file
            BufferedWriter buffer = new BufferedWriter(outputFile);
            recFileWrite(buffer, root);
            //Close the buffer writer
            buffer.close();
        }
        //Display an error if the file crashes
        catch(IOException exception)
        {
            System.out.println("There is an error in creating the file.");
        }
    }
  
    private void recFileWrite(BufferedWriter buffer, BSTNode<T> node)
    {
        try
        {
            if(node != null)
            {
                if((node.getLeft() != null) || (node.getRight() != null))
                {
                    //Write the delimiter to the file
                    buffer.write("Guess?");
                    //Display a new line to the file
                    buffer.newLine();
                    //Write the new question to the file
                    buffer.write(node.getInfo().toString());
                    //Display a new line to the file
                    buffer.newLine();
                }
                
                else if((node.getLeft() == null) && (node.getRight() == null))
                {
                    buffer.write("Answer:");
                    //Display a new line to the file
                    buffer.newLine();
                    //Write the new answer to the file
                    buffer.write(node.getInfo().toString());
                    //Display a new line to the file
                    buffer.newLine();
                }
                //Continue to search through the right link of the current node
                recFileWrite(buffer, node.getRight());
                //Continue to search through the left link of the current node
                recFileWrite(buffer, node.getLeft());            
            }
        }
        //Display an error if the file writing crashes
        catch(IOException exception)
        {
            System.out.println("There is an error in creating the file.");
        }
    }
}

/*
    Name: Ronney Sanchez
    Date: 4/27/17
    Course: CIS252 Computer Science 2
    Program: Project 3 (20 Questions)
    Description: This program uses a binary search tree to make a guess of what the
    user is thinking about. The computer makes an educated guess and the user decides
    if it is a yes or a no. The number of answers gets cut in half each time.
    Reference: I worked with Juan Mejia and Alex Bochman to create this project.
*/
package project3_rsa;

public class Project3_RSA 
{
    public static void main(String[] args) 
    {
        //The game begins introducing the 20 question game.
        System.out.println("Welcome to 20 Questions Game!!");
        System.out.println("Let's Start");
        //Create the tree builder object to star the game
        TreeBuilder myTree = new TreeBuilder();
        //Play the game
        myTree.playGame();
    }
}

/*
    Completion: This project was the most difficult one out of the three projects
    covered throughout the semester. At first, the task of doing this project is
    very complex therefore I worked with two other students, Juan and Alex, 
    with this project. Once I wrapped my head around with the structure of the 
    tree I began coding. Finally, my game works and updates the tree every time
    wrong guesses are made.
*/
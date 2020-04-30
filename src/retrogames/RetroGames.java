
package retrogames;

import javax.swing.*; //importing the packages that relevant to the RetroGames project
import java.awt.*;
import java.awt.event.*;

public class RetroGames extends JFrame
{
    //Setting the size of the game infrastructure.
    private final int OFFSET = 50;

    public RetroGames() 
    {
        Map(); // defining the map for the sokoban game..
    }
    
    public void Map() 
    {    //Creating the object for the floor class.
        Floor board = new Floor();
        add(board);//adding the floor object 
        //set the color for the entrance frame to sokoban game
        board.setBackground(Color.ORANGE);
        //defining the exit with one click on the Jframe
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setting the floor alignment to keep it correct.
        setSize(board.getBoardWidth() + OFFSET, board.getBoardHeight() + 2*OFFSET);
        setLocationRelativeTo(null);
        setVisible(true);//The game should be visible when its running..
        //giving the title for the game..as sokoban game
        setTitle("Sokoban Game");
    }
    
    public static void main(String[] args)
    {//creating the class for the retro games 
        new RetroGames();
    }
    
}


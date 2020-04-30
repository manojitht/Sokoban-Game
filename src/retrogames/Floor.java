
package retrogames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
//importing the array package.
import java.util.ArrayList;// for implementing the array datastructure 


public class Floor extends JPanel implements ActionListener 
{
    private final int OFFSET = 1;
    private final int SPACE = 60;
    private final int LEFT_COLLISION = 1;
    private final int RIGHT_COLLISION = 2;
    private final int TOP_COLLISION = 3;
    private final int BOTTOM_COLLISION = 4;
    //creating the array data structure for the actors
    private ArrayList<wall> Wall = new ArrayList(); // defining the wall
    private ArrayList<Pushcrates> pushcrates = new ArrayList();// defining the crates
    private ArrayList<diamonds> Diamonds = new ArrayList();// defining the diamonds
    
    private WarehouseKeeper player;// declaring the player as warehousekeeper
    private int w = 0;
    private int h = 0;
    private boolean completed = false;
    private boolean inGame = false;
    //creation of the player name before the play...
    private String playerName = "Sokoban 1";
    private boolean hasPlayerName = false;
    // setting the time while the game starts
    Timer timer;
    int time = 0;
    //designing the game structure and level1
    private String level1 =   "                    \n"
                            + "####################\n"
                            + "####################\n"
                            + "####################\n"
                            + "###.##. ############\n"
                            + "### $  $  .###.#####\n"
                            + "###  $    $      ###\n"
                            + "#####.## $  $ @ ####\n"
                            + "########  ##.#######\n"
                            + "####################\n"
                            + "####################\n";
                            
    //designing the game structure and level2
    private String level2 =   "                    \n"
                            + "####################\n"
                            + "######   ###########\n"
                            + "######$  ###########\n"
                            + "######  $ ##########\n"
                            + "####  $   ##########\n"
                            + "#### # ##  #########\n"
                            + "##   # ##  ####  ..#\n"
                            + "## $   $ @     $ ..#\n"
                            + "###### ### # ##  ..#\n"
                            + "######     #########\n"
                            + "####################\n";
    
    //designing the game structure and level3
    private String level3 =   "                    \n"
                            + "####################\n"
                            + "######.  ###########\n"
                            + "######$  ###########\n"
                            + "######  $.##########\n"
                            + "####. $   ##########\n"
                            + "#### # ##  #########\n"
                            + "##   # ##  ####  ###\n"
                            + "##.$   $ @     $ ###\n"
                            + "###### ### # ##  ..#\n"
                            + "######     #########\n"
                            + "####################\n";
                            
    //designing the game structure and level4
    private String level4 =   "                        \n"
                            + "########################\n"
                            + "########################\n"
                            + "########################\n"
                            + "###.##  ################\n"
                            + "### $  $  .###.#########\n"
                            + "###  $    $      #######\n"
                            + "#####.## $  $ @ ########\n"
                            + "######## .##.###########\n"
                            + "########################\n";
    //designing the game structure and level5
    private String level5 =   "                    \n"
                            + "####################\n"
                            + "#.    $            #\n"
                            + "#         $  #   $ #\n"
                            + "#  $   $           #\n"
                            + "#           .#    .#\n"
                            + "###  # ##$ #########\n"
                            + "#                  #\n"
                            + "#    #  @    #     #\n"
                            + "#    #             #\n"
                            + "#  .     .   # .   #\n"
                            + "####################\n";
    
    String[] levels = {level1,level2, level3,level4,level5};
    int currentLevel = 0;
    String level = levels[currentLevel];
    
    public Floor() 
    {
        addKeyListener(new TAdapter());
        
        timer = new Timer(1000, (ActionListener) this);
        timer.start();
        
        setFocusable(true);
        initMap();
    }
    
    public int getBoardWidth() 
    {
        return this.w;
    }
    
    public int getBoardHeight()
    {
        return this.h;
    }
    
    public void GetPlayerInfo() 
    {
        playerName = JOptionPane.showInputDialog("Player's Name : ", "");
        repaint();
    }
    
    public void ShowLevelScreen(Graphics2D g2d) 
    {        
    	repaint();
    	if (!hasPlayerName)
        {
            hasPlayerName = true;
            GetPlayerInfo();
    	}

    	g2d.setBackground(Color.ORANGE);

    	Font large = new Font("Times New Roman", Font.BOLD, 36);
    	Font medium = new Font("Times New Roman", Font.BOLD, 32);
    	Font small = new Font("Times New Roman", Font.BOLD, 24);
    	
    	FontMetrics metr1 = this.getFontMetrics(large);
    	FontMetrics metr2 = this.getFontMetrics(medium);
        FontMetrics metr3 = this.getFontMetrics(small);
        
        String start = playerName + ", press [space] to start and [Q] to quit";
    	
    	if (currentLevel == 0)
        {
            String welcome = "Welcome to the Retro Games Pvt Ltd.";
	    String sokoban = "SOKOBAN";
            String sLevel = "Level " + (currentLevel + 1);
            g2d.setFont(large);
	    g2d.drawString(welcome, (getBoardWidth() - metr1.stringWidth(welcome)) / 2, 100);
	    g2d.setFont(large);
	    g2d.drawString(sokoban, (getBoardWidth() - metr1.stringWidth(sokoban)) / 2, 160);
            g2d.setFont(large);
	    g2d.drawString(sLevel, (getBoardWidth() - metr1.stringWidth(sLevel)) / 2, 200);
	    g2d.setFont(small);
	    g2d.drawString(start, (getBoardWidth() - metr3.stringWidth(start)) / 2, getBoardHeight() / 2 + 140 );
    	} 
        else
        {
            String fLevel = "Level: " + currentLevel;
//            String score = "Your Score: " + player.gameScore;
            String  me = "Well done! "+ playerName+" if you want to move to next level...";
            g2d.setFont(large);
            g2d.drawString(fLevel, (getBoardWidth() - metr1.stringWidth(fLevel)) / 2, 160);
            g2d.setFont(medium);
//            g2d.drawString(score, (getBoardWidth() - metr1.stringWidth(score)) / 2, 200);
//            g2d.setFont(medium);
            g2d.drawString(me, (getBoardWidth() - metr1.stringWidth(me)) / 2, 250);
            g2d.setFont(small);
	    g2d.drawString(start, (getBoardWidth() - metr3.stringWidth(start)) / 2, getBoardHeight() / 2 + 140 );
    	}
    }
    
    public final void initMap()
    {
        int x = OFFSET;
        int y = OFFSET;
        
        wall Walle;
        Pushcrates b;
        diamonds a;
        
        time = 0;
        
        for(int i = 0; i < level.length(); i++) 
        {
            char item = level.charAt(i);
            
            if(item == '\n')
            {
                y += SPACE;
                if(this.w < x) 
                {
                    this.w = x;
                }
                x = OFFSET;
            } 
            else if(item == '#')
            {
                Walle = new wall(x, y);
                Wall.add(Walle);
                x += SPACE;
            } 
            else if(item == '$')
            {
                b = new Pushcrates(x, y);
                pushcrates.add(b);
                x += SPACE;
            } 
            else if (item == '.') 
            {
                a = new diamonds(x, y);
                Diamonds.add(a);
                x += SPACE;
            }
            else if(item == '@') {
                player = new WarehouseKeeper(x, y);
                x += SPACE;
            } 
            else if(item == ' ')
            {
                x += SPACE;
            }
            
            h = y;
        }
    }
    
    public void buildMap(Graphics g) 
    {   
        g.setColor(new Color(0, 150, 15));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        
        ArrayList<Actions> map = new ArrayList();
        map.addAll(Wall);
        map.addAll(Diamonds);
        map.addAll(pushcrates);
        map.add(player);
        
        player.name = playerName;
        int levelDisplay = currentLevel + 1;
        
        for(int i = 0; i < map.size(); i++) 
        {
            Actions item = map.get(i);
            g.drawImage(item.getImage(), item.x(), item.y(), this);
            
            Font small = new Font("Times New Roman", Font.BOLD, 23);
            g.setFont(small);
            g.setColor(new Color(0, 0, 0));;
            
            g.drawString("Player Name: "+player.name, 20, 24);
            
            g.drawString("Tot Of Moves: " + player.moves, 20, 48);
            
            g.drawString("Time: " + time, 1000, 24);
            
            g.drawString("Level  " + levelDisplay, 580, 24);
            
//            g.drawString("Score: " + player.gameScore, 340, 48);
            
            
        }
    }

    public void paint(Graphics g) 
    {
    	Graphics2D g2d = (Graphics2D) g;
    	super.paint(g2d);
        
        if(inGame) 
        {
            buildMap(g2d);
        }
        else 
        {
            ShowLevelScreen(g2d);
            restartLevel();
        }      
    }
    
    class TAdapter extends KeyAdapter 
    {
        public void keyPressed(KeyEvent e)
        {
            if (completed) 
            {
                return;
            }
            
            int key = e.getKeyCode();
            
            if (inGame) 
            {
            	if(key == KeyEvent.VK_LEFT) 
                {
                    if (checkWallCollision(player, LEFT_COLLISION))
                    {
                        return;
                    }
                    if (checkBoxCollision(LEFT_COLLISION))
                    {
                        return;
                    }
                    player.move(-SPACE, 0);
                } 
                else if(key == KeyEvent.VK_RIGHT)
                {
                    if (checkWallCollision(player, RIGHT_COLLISION))
                    {
                        return;
                    }
                    if (checkBoxCollision(RIGHT_COLLISION))
                    {
                        return;
                    }
                    player.move(SPACE, 0);
                } 
                else if(key == KeyEvent.VK_UP) 
                {
                    if (checkWallCollision(player, TOP_COLLISION)) 
                    {
                        return;
                    }
                    if (checkBoxCollision(TOP_COLLISION))
                    {
                        return;
                    }
                    player.move(0, -SPACE);
                } 
                else if (key == KeyEvent.VK_DOWN)
                {
                    if (checkWallCollision(player, BOTTOM_COLLISION))
                    {
                        return;
                    }
                    if (checkBoxCollision(BOTTOM_COLLISION)) 
                    {
                        return;
                    }
                    player.move(0, SPACE);
                } 
                else if (key == KeyEvent.VK_R)
                {
                    restartLevel();
                }
            }
            
            if (key == KeyEvent.VK_SPACE)
            {
                
                if(currentLevel == 5){
                    inGame = false;
                }
                else{
                    
                
                inGame = true;
                }
            }
            else if (key == KeyEvent.VK_Q) 
            {
            	System.exit(0);
            }
            repaint();
        }
    }
    
    private boolean checkWallCollision(Actions actor, int type)
    {
        if(type == LEFT_COLLISION)
        {
            for(int i = 0; i < Wall.size(); i++) 
            {
                 wall Walle = Wall.get(i);
                if(actor.isLeftCollision(Walle)) 
                {
                    return true;
                }
            }
            return false;
        } 
        else if(type == RIGHT_COLLISION)
        {
            for (int i = 0; i < Wall.size(); i++)
            {
                 wall Walle = Wall.get(i);
                if (actor.isRightCollision(Walle))
                {
                    return true;
                }
            }
            return false;
        } 
        else if(type == TOP_COLLISION)
        {
            for (int i = 0; i < Wall.size(); i++) 
            {
                wall Walle = Wall.get(i);
                if (actor.isTopCollision(Walle))
                {
                    return true;
                }
            }
            return false;
        } 
        else if(type == BOTTOM_COLLISION)
        {
            for(int i = 0; i < Wall.size(); i++)
            {
                wall Walle = Wall.get(i);
                if (actor.isBottomCollision(Walle))
                {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    private boolean checkBoxCollision(int type)
    {
        if(type == LEFT_COLLISION)
        {
            for(int i = 0; i < pushcrates.size(); i++) 
            {
                Pushcrates b = pushcrates.get(i);
                if(player.isLeftCollision(b))
                {
                    for(int j=0; j < pushcrates.size(); j++) 
                    {
                        Pushcrates item = pushcrates.get(j);
                        if(!b.equals(item)) 
                        {
                            if(b.isLeftCollision(item))
                            {
                                return true;
                            }
                        }
                        if(checkWallCollision(b, LEFT_COLLISION)) 
                        {
                            return true;
                        }
                    }
                    b.move(-SPACE, 0);
                    isCompleted();
                }
            }
            return false;
        } 
        else if(type == RIGHT_COLLISION)
        {
            for(int i = 0; i < pushcrates.size(); i++) 
            {
                Pushcrates b = pushcrates.get(i);
                if(player.isRightCollision(b)) 
                {
                    for(int j=0; j < pushcrates.size(); j++)
                    {
                        Pushcrates item = pushcrates.get(j);
                        if(!b.equals(item))
                        {
                            if (b.isRightCollision(item))
                            {
                                return true;
                            }
                        }
                        if(checkWallCollision(b, RIGHT_COLLISION)) 
                        {
                            return true;
                        }
                    }
                    b.move(SPACE, 0);
                    isCompleted();                   
                }
            }
            return false;
        }
        else if(type == TOP_COLLISION)
        {
            for(int i = 0; i < pushcrates.size(); i++) 
            {
                Pushcrates b = pushcrates.get(i);
                if(player.isTopCollision(b))
                {
                    for(int j = 0; j < pushcrates.size(); j++)
                    {
                        Pushcrates item = pushcrates.get(j);
                        if(!b.equals(item)) 
                        {
                            if(b.isTopCollision(item)) 
                            {
                                return true;
                            }
                        }
                        if(checkWallCollision(b, TOP_COLLISION))
                        {
                            return true;
                        }
                    }
                    b.move(0, -SPACE);
                    isCompleted();
                }
            }
            return false;
        } 
        else if(type == BOTTOM_COLLISION)
        {
            for(int i = 0; i < pushcrates.size(); i++) 
            {
                Pushcrates b = pushcrates.get(i);
                if(player.isBottomCollision(b)) 
                {
                    for(int j = 0; j < pushcrates.size(); j++) 
                    {
                        Pushcrates item = pushcrates.get(j);
                        if(!b.equals(item)) 
                        {
                            if (b.isBottomCollision(item)) 
                            {
                                return true;
                            }
                        }
                        if(checkWallCollision(b, BOTTOM_COLLISION))
                        {
                            return true;
                        }
                    }
                    b.move(0, SPACE);
                    isCompleted();
                }
            }
        }
        return false;
    }
    
    public void isCompleted() 
    {
        int num = pushcrates.size();
        int PushcratesCompleted = 0;
        
        for(int i = 0; i < num; i++)
        {
            Pushcrates b = pushcrates.get(i);
            for(int j = 0; j < num; j++)
            {
               diamonds diamond = Diamonds.get(j);
                if(b.x() == diamond.x() && b.y() == diamond.y()) 
                {
                    PushcratesCompleted += 1;
                }
            }
        }
        
        
        if(PushcratesCompleted == num)
        {
            completed = true;
            repaint();
            
            EventQueue.invokeLater(() ->
            {
                    try{
                        Thread.sleep(500);
                    }
                    catch(InterruptedException ex){
                        
                    }
                    
                    restartLevel();
            });
            
            try
            {

            if(currentLevel < 5)
            {
            	currentLevel++;
                level = levels[currentLevel];	
            }
            }
            catch(Exception ex){
                
                ImageIcon pic = new ImageIcon ("congratulations.jpg");
                JOptionPane.showMessageDialog(null,"Mr. "+ playerName +"  you have finished all the levels successfully ! "," Execellent, Very Nice !",JOptionPane.INFORMATION_MESSAGE,pic);
                
            }
        }
    }
    
    public void restartLevel() 
    {
        repaint();
        
        Diamonds.clear();
        pushcrates.clear();
        Wall.clear();
        
        initMap();
        
        inGame = false;
        
        if(completed) 
        {
            completed = false;
        }
    }
    
    public void actionPerformed(ActionEvent e)
    {
        if(inGame)
        {
            time++;
	}
        
        repaint();
    }
}


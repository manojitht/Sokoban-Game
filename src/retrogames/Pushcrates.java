
package retrogames;

import javax.swing.*;
import java.awt.*;
import java.net.*;

public class Pushcrates extends Actions
{
    public Pushcrates(int x, int y) 
    {
        super(x, y);
        
        URL loc = this.getClass().getResource("/res/baggage.png");
        ImageIcon imgIcon = new ImageIcon(loc);
        Image image = imgIcon.getImage();
        this.setImage(image);
    }

    public void move(int x, int y) 
    {
        int nx = this.x() + x;
        int ny = this.y() + y;
        this.setX(nx);
        this.setY(ny);
    }
}

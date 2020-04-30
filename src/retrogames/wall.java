
package retrogames;

import javax.swing.*;
import java.awt.*;
import java.net.*;

public class wall extends Actions
{
    private Image image;

    public wall(int x, int y) 
    {
        super(x, y);

        URL loc = this.getClass().getResource("/res/wall.png");
        ImageIcon imgIcon = new ImageIcon(loc);
        image = imgIcon.getImage();
        this.setImage(image);
    }
}

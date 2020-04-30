
package retrogames;

import javax.swing.*;
import java.awt.*;
import java.net.*;

public class diamonds extends Actions
{
    public diamonds(int x, int y)
    {
        super(x, y);
        
        URL loc = this.getClass().getResource("/res/area.png");
        ImageIcon imgIcon = new ImageIcon(loc);
        Image image = imgIcon.getImage();
        this.setImage(image);
    }
}

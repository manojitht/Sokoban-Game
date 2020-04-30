
package retrogames;

import java.awt.*;
import java.util.ArrayList;

public class Actions 
{
    private final int SPACE = 60;

    private int x;
    private int y;
    private Image image;

    public Actions(int x, int y) 
    {
        this.x = x;
        this.y = y;
    }

    public Image getImage()
    {
        return this.image;
    }

    public void setImage(Image img) 
    {
        image = img;
    }

    public int x() 
    {
        return this.x;
    }

    public int y()
    {
        return this.y;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public boolean isLeftCollision(Actions actor) 
    {
        if (((this.x() - SPACE) == actor.x()) && (this.y() == actor.y())) 
        {
            return true;
        } 
        else 
        {
            return false;
        }
    }

    public boolean isRightCollision(Actions actor)
    {
        if (((this.x() + SPACE) == actor.x()) && (this.y() == actor.y()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean isTopCollision(Actions actor) 
    {
        if (((this.y() - SPACE) == actor.y()) && (this.x() == actor.x())) 
        {
            return true;
        } 
        else
        {
            return false;
        }
    }

    public boolean isBottomCollision(Actions actor)
    {
        if (((this.y() + SPACE) == actor.y()) && (this.x() == actor.x())) 
        {
            return true;
        } else
        {
            return false;
        }
    }

    
}

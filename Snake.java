import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.*;
import javax.swing.*;
import javax.swing.ImageIcon;
/**
 * Write a description of class Snake here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Snake extends JPanel
{
    private String snake = "head.png";

    private int dx = -1;
    private int dy = 0;
    private int x;
    private int y;
    private Image image;
    
    public Snake() {
        ImageIcon ii = new ImageIcon("images/"+this.getClass().getResource(snake));
        image = ii.getImage();
        x = 300;
        y = 500;
    }
    
    public void move() {
        x += dx;
        y += dy;
        
        
    }

    
    public int getDx() {
		return dx;
	}

	public void setDx(int dx) {
		this.dx = dx;
	}

	public int getDy() {
		return dy;
	}

	public void setDy(int dy) {
		this.dy = dy;
	}

	public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImage() {
        return image;
    }

 public Image bodyCobra(){
    	 ImageIcon ii = new ImageIcon(this.getClass().getResource("images/body.png"));
       
       
         return  ii.getImage();
    }
    
    
}

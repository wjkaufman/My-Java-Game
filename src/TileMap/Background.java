package TileMap;

import java.awt.image.*;

import javax.imageio.*;

import java.awt.Graphics2D;
import java.io.File;
import java.io.FileInputStream;

import Main.GamePanel;

public class Background
{
	private BufferedImage image;
	private double x, y, dx, dy, moveScale;
	
	public Background(String s, double ms)
	{
		try
		{
			File myFile = new File(s); // something is going wrong here
				System.out.println("File \"" + s + "\" exists: " + myFile.exists());
				System.out.println("File \"" + s + "\" is readable: " + myFile.canRead());
			FileInputStream fis = new FileInputStream(myFile);
			image = ImageIO.read(myFile);  // image = ImageIO.read(getClass().getResourceAsStream(s));
			moveScale = ms;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void setPosition(double x, double y)
	{
		this.x = (x * moveScale) % GamePanel.WIDTH;
		this.y = (y * moveScale) % GamePanel.HEIGHT;
		
	}
	
	public void setVector(double dx, double dy)
	{
		this.dx = dx;
		this.dy = dy;
	}
	
	public void update()
	{
		this.x += this.dx;
		this.y += this.dy;
		
	}
	
	public void draw(Graphics2D g)
	{
		g.drawImage(image, (int)x, (int)y, null);
		
		if (x < 0)
		{
			g.drawImage(image, (int)x + GamePanel.WIDTH, (int)y, null);
			
		}
		
		else if (x > 0)
		{
			g.drawImage(image, (int)x - GamePanel.WIDTH, (int)y, null);
		}
	}

}

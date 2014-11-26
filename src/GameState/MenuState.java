package GameState;

import java.awt.*;
import java.awt.event.KeyEvent;

import Main.GamePanel;
import TileMap.Background;

public class MenuState extends GameState
{
	
	private Background bg;
	
	private int currentChoice = 0;
	private String[] options = {"start", "help", "quit"};
	
	private Color titleColor;
	private Font titleFont;
	
	private Font regularFont;
	
	public MenuState(GameStateManager gsm)
	{
		this.gsm = gsm;
		
		try
		{
			bg = new Background("Resources/Backgrounds/menubg.png", 1);
			bg.setVector(-10, 0);
			
			titleColor = Color.BLACK;
			titleFont = new Font("Times New Roman", Font.PLAIN, 20);
			regularFont = new Font("Times New Roman", Font.ITALIC, 15);
			 
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void init()
	{
		
	}
	public void update()
	{
		bg.update();
	}
	
	public void draw(Graphics2D g)
	{
		// draw bg
		bg.draw(g);
		
		// draw title
		g.setColor(titleColor);
		g.setFont(titleFont);
		g.drawString("My Game", GamePanel.WIDTH / 2 - 60, GamePanel.HEIGHT / 4);
		
		// draw menu options
		g.setFont(regularFont);
		for (int i = 0; i < options.length; i++)
		{
			if (i == currentChoice)
			{
				g.setColor(Color.BLUE);
				
			}
			else
			{
				g.setColor(Color.BLACK);
			}
			
			g.drawString(options[i], GamePanel.WIDTH / 2 - 40, GamePanel.HEIGHT / 2 + i * 15);
		}
		
	}
	
	private void select()
	{
		 if (currentChoice == 0)
		 {
			 // start
		 }
		 else if (currentChoice == 1)
		 {
			 // help
		 }
		 else if (currentChoice == 2)
		 {
			 System.exit(0);
		 }
		 
	}
	
	public void keyPressed(int k)
	{
		if (k == KeyEvent.VK_ENTER)
		{
			select();
		}
		else if (k == KeyEvent.VK_UP)
		{
			currentChoice--;
			if (currentChoice == -1)
			{
				currentChoice = options.length - 1;
			}
		}
		else if (k == KeyEvent.VK_DOWN)
		{
			currentChoice++;
			if (currentChoice == options.length)
			{
				currentChoice = 0;
			}
		}
	}
	
	public void keyReleased(int k)
	{
		
	}
	
	
	
	
}

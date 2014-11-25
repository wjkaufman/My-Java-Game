package Main;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import GameState.GameStateManager;

public class GamePanel extends JPanel implements Runnable, KeyListener
{
	// dimensions
	public static final int xRatio = 320, yRatio = 240, SCALE = 2;
	public static final int WIDTH = xRatio * SCALE, HEIGHT = yRatio * SCALE;
	
	// game thread
	private Thread thread; // what does this mean?
	private boolean running;
	private int FPS = 30;
	private long targetTime = 1000 / FPS; // ?
	
	// image
	private BufferedImage image;
	private Graphics2D g;
	
	// game state manager
	private GameStateManager gsm;
	
	
	// constructors
	public GamePanel()
	{
		// setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		this.setSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		requestFocus();
		
	}
	
	// methods
	public void addNotify()
	{
		super.addNotify();
		if (thread == null)
		{
			thread = new Thread(this);
			addKeyListener(this);
			thread.start();
		}
	}
	
	private void init()
	{
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		
		running = true;
		
		gsm = new GameStateManager();
		
	}
	
	public void run()
	{
		init();
		
		long start, elapsed, wait;
		
		while (running)
		{
			start = System.nanoTime();
			
			update();
			draw();
			drawToScreen();
			
			elapsed = System.nanoTime() - start;
			wait = targetTime - elapsed / 1000000;
			if (wait < 0) wait = 5;
			
			try
			{
				Thread.sleep(wait);
			}
			catch (Exception e)
			{
				e.printStackTrace();
				
			}
		}
	}
	
	private void update()
	{
		gsm.update();
	}
	
	private void draw()
	{
		gsm.draw(g);
	}
	
	private void drawToScreen()
	{
		Graphics g2 = getGraphics();
		g2.drawImage(image, 0, 0, null);
		g2.dispose();
		
	}
	
	
	
	public void keyTyped(KeyEvent e)
	{
		
	}
	
	public void keyPressed(KeyEvent e)
	{
		gsm.keyPressed(e.getKeyCode());
	}
	
	public void keyReleased(KeyEvent e)
	{
		gsm.keyReleased(e.getKeyCode());
	}
	
	
	
}

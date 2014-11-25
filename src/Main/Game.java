package Main;

import javax.swing.JFrame;
import java.awt.Dimension;

public class Game
{
	public static void main(String[] args)
	{
		JFrame window = new JFrame("Game");
		window.setContentPane(new GamePanel());
		window.setSize(new Dimension(GamePanel.WIDTH, GamePanel.HEIGHT));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		window.setVisible(true);
		
	}
}

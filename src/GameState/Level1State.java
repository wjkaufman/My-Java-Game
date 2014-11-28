package GameState;

import java.awt.Color;
import java.awt.Graphics2D;

import Main.GamePanel;
import TileMap.TileMap;

public class Level1State extends GameState
{
	
	private TileMap tileMap;
	
	public Level1State(GameStateManager gsm)
	{
		this.gsm = gsm;
		init();
	}

	@Override
	public void init()
	{
		tileMap = new TileMap(30);
		tileMap.loadTiles("Resources/tileset.png");
		tileMap.loadMap("Resources/Maps/lvl1_map.txt");
		tileMap.setPosition(0, 0);
		

	}

	@Override
	public void update()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(Graphics2D g)
	{
		// clear screen
		g.setColor(Color.gray);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		
		// draw tilemap
		tileMap.draw(g);

	}

	@Override
	public void keyPressed(int k)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(int k)
	{
		// TODO Auto-generated method stub

	}

}

package TileMap;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.Arrays;

import javax.imageio.*;

import Main.GamePanel;

public class TileMap {
	
	// position
	private double x, y;
	
	// bounds
	private int xmin, ymin, xmax, ymax;
	
	// map
	private int[][] map;
	private int tileSize;
	private int numRows, numCols;
	private int width, height;
	
	// tileset
	private BufferedImage tileset;
	private int numTilesAcross;
	private Tile[][] tiles;
	
	// drawing
	private int rowOffset, colOffset, numRowsToDraw, numColsToDraw;
	
	public TileMap(int tileSize)
	{
		this.tileSize = tileSize;
		numRowsToDraw = GamePanel.HEIGHT / tileSize + 2;
		numColsToDraw = GamePanel.WIDTH / tileSize + 2;
	}
	
	public void loadTiles(String s)
	{
		try
		{
			File file = new File(s);
			tileset = ImageIO.read(file);
			
			numTilesAcross = tileset.getWidth() / tileSize;
			tiles = new Tile[2][numTilesAcross];
			
			BufferedImage subimage;
			for (int col = 0; col < numTilesAcross; col++)
			{
				subimage = tileset.getSubimage(col * tileSize, 0, tileSize, tileSize);
				tiles[0][col] =  new Tile(subimage, Tile.NORMAL);
				subimage = tileset.getSubimage(col * tileSize, tileSize, tileSize, tileSize);
				tiles[1][col] = new Tile(subimage, Tile.BLOCKED);
				
			}
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void loadMap(String s)
	{
		try
		{

			FileInputStream in = new FileInputStream( new File(s));
			
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			
			numRows = Integer.parseInt(br.readLine());
			numCols = Integer.parseInt(br.readLine());
			map = new int[numRows][numCols];
			width = numCols * tileSize;
			height = numRows * tileSize;
			
			String delims = "\\s+";
			
			for (int row = 0; row < numRows; row++)
			{
				String line = br.readLine();
				String[] tokens = line.split(delims);
				for (int col = 0; col < numCols; col++)
				{
					map[row][col] = Integer.parseInt(tokens[col]);
				}
			}
			
			br.close();
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public int getTileSize() { return tileSize; }
	public int getX() { return (int)x; }
	public int getY() { return (int)y; }
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	
	public int getType(int row, int col)
	{
		int rc = map[row][col];
		int r = rc / numTilesAcross;
		int c = rc % numTilesAcross;
		
		return tiles [r][c].getType();
	}
	
	public void setPosition(double x, double y)
	{
		this.x = x;
		this.y = y;
		
		fixBounds();
		
		colOffset = (int)-this.x / tileSize;
		rowOffset = (int)-this.y / tileSize;
	}
	
	public void fixBounds()
	{
		if (x < xmin) x = xmin;
		if (y < ymin) y = ymin;
		if (x > xmax) x = xmax;
		if (x > ymax) y = ymax;
		
	}
	
	public void draw(Graphics2D g)
	{
		for (int row = rowOffset; row < rowOffset + numRowsToDraw; row++)
		{
			if (row > numRows) break;
			 for (int col = colOffset; col < colOffset + numColsToDraw; col++)
			 {
				 if (col > numCols) break;
				 if (map[row][col] == 0) continue;

				 
				 
				 int rc = map[row][col];
				 int r = rc / numTilesAcross;
				 int c = rc % numTilesAcross;
				 
				 g.drawImage(tiles[r][c].getImage(), (int)x + col * tileSize, (int)y + row * tileSize, null);
			 }
		}
	}
	
	

}

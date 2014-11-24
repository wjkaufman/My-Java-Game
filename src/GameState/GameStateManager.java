package GameState;

import java.util.ArrayList;

public class GameStateManager {
	
	private ArrayList<GameState> gameStates;
	private int currentState;
	
	public GameStateManager()
	{
		gameStates = new ArrayList<GameState>();
		
		currentState = 0;
	}

}

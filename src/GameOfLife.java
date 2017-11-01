
public class GameOfLife {
	
	private LifeGrid gameGrid;
	
	int numberOfGenerations = 8;
	
	public GameOfLife(String path) 
	{
		System.out.println("Game Of Life started.");
		
		setUpGame(path);
	}
	
	public void setUpGame(String path) 
	{
		gameGrid = new LifeGrid(path);
	}
	
	public void start() 
	{
		// take first Generation and evolve, repeat
		for (int gen = 0; gen < numberOfGenerations; gen++) 
		{
			System.out.println("Generation: " + (gen + 1));
			gameGrid.printCurrentGrid();
			
			System.out.println();
			gameGrid.evolve();	
		}

	}
	
	public int getLivingNeighbours(int x, int y) 
	{
		return gameGrid.getLivingNeighbours(x,y);
	}
	
}

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class LifeGrid {

	public int LIVING_CELL = 1;
	public int DEAD_CELL = 0;
	
	public int width, height;
	private int[][] currentGeneration;
	private int[][] nextGeneration;
	
	public LifeGrid(String path)
	{
		// read values from file
		try {
			
			initialiseStartingState(path);
			
		} catch (IOException e) {

			e.printStackTrace();
		}
		
		//[debug]printCurrentGrid(); 
		
	}
	
	public LifeGrid(int width, int height) 
	{
		currentGeneration = new int[width][height];
	}
	
	public void evolve() 
	{
		// new set of generated cells
		nextGeneration = new int[width][height];
		
		// apply the rules to the current generation and create the next and set
		for(int y = 0; y < width; y++) 
		{
			for (int x = 0; x < height; x++) 
			{
				
				// for each cell
				if (aliveAndHasLessThanTwoNeighbours(x,y))
				{
					nextGeneration[x][y] = DEAD_CELL;
					
				} else if (aliveAndHasTwoOrThreeLiveNeighbours(x,y)) 
				{
					nextGeneration[x][y] = LIVING_CELL;
					
				} else if (aliveAndHasMoreThanThreeNeighbours(x,y))
				{
					nextGeneration[x][y] = DEAD_CELL;
					
				} else if (deadCellWithThreeLiveNeighbours(x,y))
				{
					nextGeneration[x][y] = LIVING_CELL;
				}
				
			}
		} // end of loop
		
		currentGeneration = nextGeneration;
		
	}
	
	// first rule - Any live cell with fewer than two live neighbours dies, as if caused by under-population.
	boolean aliveAndHasLessThanTwoNeighbours(int x, int y)
	{
		if (getLivingNeighbours(x,y) < 2 && isLivingCell(x,y))
		{
			return true;
			
		} else {
			
			return false;
		}
	}
	
	//second rule - Any live cell with two or three live neighbours lives on to the next generation.
	boolean aliveAndHasTwoOrThreeLiveNeighbours(int x, int y) 
	{
		if (isLivingCell(x,y) && (getLivingNeighbours(x,y) == 2 || getLivingNeighbours(x,y) == 3))
		{
			return true;
			
		} else
		{
			return false;
		}
		
	}
	
	// third rule - Any live cell with more than three live neighbours dies, as if by overcrowding.
	boolean aliveAndHasMoreThanThreeNeighbours(int x, int y) 
	{
		if (isLivingCell(x,y) && getLivingNeighbours(x,y) > 3) 
		{
			return true;
			
		} else 
		{
			return false;
		}
		
	}
	
	// fourth rule - Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
	boolean deadCellWithThreeLiveNeighbours(int x, int y)
	{
		if (isDeadCell(x,y) && getLivingNeighbours(x,y) == 3)
		{
			return true;
			
		} else 
		{
			return false;
		}
	}
	
	int getLivingNeighbours(int x, int y)
	{
		
		// Template for getting the cells around x,y (8 cells)
		int[][] neighbours = 
			{
				{x, y - 1},		// top
				{x + 1, y - 1}, // top right
				{x + 1, y}, 	// right
				{x + 1, y + 1}, // bottom right
				{x, y + 1},		// bottom
				{x - 1, y + 1},	// bottom left
				{x - 1, y},		// left
				{x - 1, y - 1}  // top left
			};
		
		
		// Go through each template and count
		int neighborCount = 0;
		
		for (int i = 0; i < 8; i++) 
		{
			
			int xRow = neighbours[i][0];
			int yCol = neighbours[i][1];
			
			if (isInGrid(xRow, yCol) && isLivingCell(xRow, yCol)) 
			{
				neighborCount++;
			}
			
		}
		
		//System.out.println(" neighbours " + neighborCount);
		return neighborCount;
	}
	
	boolean isInGrid(int x, int y) 
	{
		// if there's any negative indices, it's off grid - return false
		if (x < 0 || y < 0 || x > width - 1 || y > height - 1) {	
			return false;
		} else 
		{	
			return true;		
		}
		
	}
	
	public void setLivingCell(int x, int y) 
	{
		currentGeneration[x][y] = LIVING_CELL;
	}
	
	public void setDeadCell(int x, int y) 
	{
		currentGeneration[x][y] = DEAD_CELL;
	}
	
	public boolean isLivingCell(int x, int y) 
	{
		if (currentGeneration[x][y] == LIVING_CELL) 
		{
			 return true;
		} else 
		{
			return false;
		}
	}
	
	public boolean isDeadCell(int x, int y) 
	{
		if (currentGeneration[x][y] == DEAD_CELL) 
		{
			 return true;
		} else 
		{
			return false;
		}
	}
	
	public void initialiseStartingState(String path) throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader("bin/" + path));
        int xCount = 0, yCount = 0;
        String everything = "";
        
		try {
	    
			StringBuilder sb = new StringBuilder();
	        String line = br.readLine();
	        
	        xCount = line.length();
	        
	        while (line != null) {
	        
	        	sb.append(line);
	            //sb.append(System.lineSeparator());
	            System.out.println("file: " + line);	       
	            
	            line = br.readLine();
	            yCount++;
	        }
	        
	        System.out.println("Dimensions : " + xCount + " , " + yCount);
	        this.width = xCount;
	        this.height = yCount;
	        
	        		
	        everything = sb.toString();
	        
	    } catch (IOException e) {

	    	e.printStackTrace();
	    	
		} finally {
			
	        br.close();
	        
	    }
		
		currentGeneration = new int[xCount][yCount];
		
		
		// put everything in the gridarray array
		//[debug]System.out.println(" everything: " + everything);
		int i = 0;
		for (int x = 0; x < xCount; x++) 
		{
			for (int y = 0; y < yCount; y++)
			{
				//System.out.println(" order " + x + " , " + y);
				if (everything.charAt(i) == '-')
				{
					currentGeneration[y][x] = DEAD_CELL;
					
				} else if(everything.charAt(i) == 'x')
				{
					currentGeneration[y][x] = LIVING_CELL;
				}
				i++;
			}
			
		}
		
		//[debug]test grid
		//for (int x = 0; x < xCount; x++) 
		//{
		//	for (int y = 0; y < yCount; y++)
		//	{
		//		System.out.println(" result : " + currentGeneration[x][y]);
		//	}
		//}
	
	}
	
	public void printCurrentGrid() 
	{
		for(int y = 0; y < this.height; y++)
		{
			for (int x = 0; x < this.width; x++)
			{
				if (currentGeneration[x][y] == DEAD_CELL)
				{
					System.out.print(" -");
				} else 
				{
					System.out.print(" x");
				}
			}
			System.out.println();
		}
	}			
}
	
	


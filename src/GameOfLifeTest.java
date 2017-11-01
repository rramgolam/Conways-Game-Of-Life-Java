import static org.junit.Assert.*;

import org.junit.Test;


public class GameOfLifeTest {

	@Test
	public void testGameOfLife() {
		
		GameOfLife gol = new GameOfLife("test2.txt");
		gol.start();
		
		gol = new GameOfLife("test3.txt");
		gol.start();
		
		gol = new GameOfLife("test4.txt");
		gol.start();
	}
	
	@Test
	public void testNumberOfNeighbors() {
		
		GameOfLife gol = new GameOfLife("test1.txt");
		
		assertEquals(4, gol.getLivingNeighbours(1, 1));
		assertEquals(4, gol.getLivingNeighbours(2, 1));
		assertEquals(2, gol.getLivingNeighbours(3, 1));
		assertEquals(2, gol.getLivingNeighbours(0, 2));
		assertEquals(4, gol.getLivingNeighbours(1, 2));
		assertEquals(4, gol.getLivingNeighbours(2, 2));
		
	}

}

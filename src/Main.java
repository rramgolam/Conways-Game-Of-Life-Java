import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		// Game of Life
		System.out.println("Please choose which test to simulate:");
		System.out.println("		- test1.txt");
		System.out.println("		- test2.txt");
		System.out.println("		- test3.txt");
		System.out.println("		- test4.txt");
		
		int choice = 1;
		
		Scanner input = new Scanner(System.in);
		choice = input.nextInt();
		
		String fileName = "test1.txt";

		switch(choice)
		{
			case 1:
				fileName = "test1.txt";
				break;
			case 2:
				fileName = "test2.txt";
				break;
			case 3:
				fileName = "test3.txt";
				break;
			case 4:
				fileName = "test4.txt";
				break;
		
			default:
				System.out.println("Incorrect choice, running first test");
				break;
		}
		
		
		GameOfLife game = new GameOfLife(fileName);
		game.start();	
		
		System.out.println("Simulation has Ended.");
		
	}

}

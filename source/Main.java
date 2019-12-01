import java.util.Arrays;
//import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		Scanner keyboard = new  Scanner(System.in);

		//set up players 
		Player[] players = new Player[5]; //5 people including human player
		Player human = new Player(0,0,0,1.0,new Hand ("Agent"),0,1000,new int[4]);
		players[0] = human;// put human player in players array
		
		//generate random parameter for AI player
		// randomly choose one AI to be enemy agent
		int[] roleArray = new int[4];
		int agentIdx = (int)(Math.random()*3 +0);// (int)(Math.random()*3 +0) will return a random int between 0 and 3
		
		System.out.println("Agent index: " + agentIdx);

		roleArray[agentIdx] = 1;
		  // randomly assign AITendency
		int[] tendencyArray = new int[4];
		for (int i=0; i <= 3; i++) {tendencyArray[i]=(int)(Math.random()*4 +0);}//assume AITendency range from 0 to 4
		  // randomly generate clues;
		int[][] clueArray = new int[4][7];
		List<Integer> clue_row = Arrays.asList(1, 2, 3, 4); // create list 1,2,3,4
		for (int i=0; i < 7; i++) {
			
			Collections.shuffle(clue_row); // shuffle list
			Integer[] array_row = new Integer[4];
			array_row = clue_row.toArray(array_row);// turn list into Integer array
			for (int j=0; j < 4; j++) {
				clueArray[j][i] = array_row[j].intValue();}// turn Integer into int and fill clue array by column,
			                                               // then each column of clueArray is shuffled 1,2,3,4 feature index
		}

		//complete players array
		for (int i=0; i < 4; i++) {
			int role0 = roleArray[i];
			int tendency0 = tendencyArray[i];
			int[] clue0 = clueArray[i];
			String playerNumber = Integer.toString(i+1);
			players[i+1] = new Player(0,role0,tendency0,0,new Hand ("Player " + playerNumber),0,1000,clue0);
		}

		int guess_count = 0;
		
		int game_end = 0;
		int round_count = 0;

		Dialogue d = new Dialogue(agentIdx);
		d.setFeaturetable();
		System.out.println(d.toString());

		d.setClues();

		while (game_end == 0) {
			round_count++;
			System.out.println("\nRound "+ round_count);
			
			// blackjack game
			Blackjack blackjack = new Blackjack(players[4], players[3], players[2], players[1], players[0]);
			
			blackjack.RoundOfBlackjack();
			
			if (players[0].getchips() <= 0)
			{
				System.out.println("\n\nYou are out of money and have lost the game :(");
				game_end = 1;
				break;
			}


			
			if (round_count >= 5) {
				System.out.println("You have "+ human.getchips()+ " chips");
				System.out.println("Enter 0 if you want to do nothing");
				System.out.println("Enter player index(1-4) if you know who is enemy agent, you will lose if wrong");
				System.out.println("Enter 5 if you want buy clue with chips(20)");
				int humaninput = keyboard.nextInt();
				if (1<= humaninput &&  humaninput <= 4) {
					if (humaninput-1 == agentIdx){System.out.println("You win, game end"); game_end =1;}
					else {System.out.println("You lose, game end"); game_end =1;}
				}
				else if (humaninput == 5 && guess_count < 10) {
					human.setchips( human.getchips()-20 );
					System.out.println(d.getClues());
					d.reorganizeClues();
					guess_count++;
				}
				if (guess_count == 10 && game_end !=1){
					System.out.println("You have enough information on enemy agent's idenity");
				}
				
			}
			
		}
		
		keyboard.close();
	}
}
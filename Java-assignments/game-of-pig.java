//Alex Beaumont-Stidwill
package cmpe_212;

import java.util.Random;
import java.util.Scanner;

public class game-of-pig{

	public static int rollDice() {
		Random dice = new Random(); // this is the random function used in java,
									// which will give a random integer random
		int roll = dice.nextInt(6) + 1; // this will make it so that the random
										// number given is between 1-6
		return roll; // returns the roll value
	}

	public static String numberConvert(int i) { // this function converts the
												// integer to a string
		if (i == 1) {
			return "one";
		} else if (i == 2) {
			return "two";
		} else if (i == 3) {
			return "three";
		} else if (i == 4) {
			return "four";
		} else if (i == 5) {
			return "five";
		} else
			return "six";
	}

	public static void gameTime() {
		Scanner user = new Scanner(System.in);
		System.out.println("What is your name?");
		String userName = user.nextLine(); // asks the user for their name and
											// stores their input under userName
		System.out.println("Hello, " + userName);
		int human = 0; // sets the human and computer scores to 0
		int computer = 0;

		while (human <= 100 && computer <= 100) { // will run until either get a
													// score above 100

			if (computer <= 100 || human <= 100) {

				System.out.println("" + userName + "'s Turn:");
				human += humanTurn(human, computer, userName);
				// human's score gets added every round
				System.out.println("\nComputer's Turn:\n");
				computer += compTurn(computer, human, userName);
				// computer's score gets added every round
				Scanner in = new Scanner(System.in);
				System.out.println("Round over press ENTER to continue");
				in.nextLine();
				// once the round is over, the terminal will prompt the user to
				// press enter to continue playing the game
			}
		}
		// once either the human, or the computer has a score above 100, the
		// while loop will break, and the gameOver() function will begin
		gameOver(human, computer);
		return; // the game is done, and returns to main
	}

	public static int humanTurn(int humanScore, int compScore, String user) {
		int dice1, dice2;
		int hold = 1; // variable needed to enter the while loop below
		int turnScore = 0; // at the start of turn, the turn score is 0

		while (hold > 0) {
			dice1 = rollDice();
			dice2 = rollDice();
			// gets two random values stored into the dice
			if (dice1 == 1 && dice2 == 1)
				// if both dice are 1, the turn will be over
				turnScore = -1 * humanScore;
			else if (dice1 != 1 && dice2 != 1)
				// if neither are one, then the turn score will be the
				// cumulative value of both dice, plus the previous turn score
				// (only applicable if the dice are identical for the previous
				// turn)
				turnScore = dice1 + dice2 + turnScore;
			else
				// if one dice is zero, the turn score is set to zero
				turnScore = 0;

			int total = humanScore + turnScore;

			if (total >= 100) {
				// if the total is greater than 100, the function playerMessage
				// will be called
				hold = playerMessage(humanScore, compScore, turnScore, dice1, dice2, user);
				break; // break out of the while loop, because the turn is done,
						// and so is the game
			}
			hold = playerMessage(humanScore, compScore, turnScore, dice1, dice2, user);
			// playerMessage is prompted after each turn
		}
		return turnScore;
		// returns the turn score to the gameTime function
	}

	public static int compTurn(int compScore, int humanScore, String userName) {
		int dice1, dice2;
		int hold = 1;
		int turn = 0;
		// this function is essentially the same as humanTurn() except it
		// prompts the compChoice() function
		while (hold == 1) {
			dice1 = rollDice();
			dice2 = rollDice();

			if (dice1 == 1 && dice2 == 1)
				turn = -1 * compScore;
			else if (dice1 != 1 && dice2 != 1)
				turn = dice1 + dice2 + turn;
			else
				turn = 0;

			int total = compScore + turn;

			if (total >= 100) {
				hold = compChoice(total, humanScore, turn, dice1, dice2, userName);
				break;
			}

			hold = compChoice(total, humanScore, turn, dice1, dice2, userName);
		}
		return turn;
	}

	public static void gameOver(int playerScore, int compScore) {
		if (playerScore >= 100) {
			// if the player wins, then the winner message in cool letters
			// appears on the terminal
			System.out.println("oooo     oooo ooooo oooo   oooo oooo   oooo ooooooooooo oooooooooo ");
			System.out.println(" 88 88  88    888   8888o  88   8888o  88   888    88   888    888");
			System.out.println("  88 888 88   888   88 888o88   88 888o88   888ooo8     888oooo88 ");
			System.out.println("   888 888    888   88   8888   88   8888   888    oo   888  88o  ");
			System.out.println("    8    8   o888o o88o    88  o88o    88  o888ooo8888 o888o  88o8");
		} else {
			// if the player loses, then the try again message in cool letters
			// appears on the terminal
			System.out
					.println("ooooooooooo oooooooooo ooooo  oooo       o       ooooooo8      o      ooooo oooo   oooo");
			System.out
					.println("88  888 88   888    888  888  88        888    o888    88     888      888   8888o  88 ");
			System.out
					.println("    888      888oooo88     888         8  88   888    oooo   8  88     888   88 888o88 ");
			System.out
					.println("    888      888  88o      888        8oooo88  888o    88   8oooo88    888   88   8888 ");
			System.out
					.println("   o888o    o888o  88o8   o888o     o88o  o888o 888ooo888 o88o  o888o o888o o88o    88 ");
		}
	}

	public static int playerMessage(int playerScore, int compScore, int turn, int dice1, int dice2, String user) {
		String rolledMessage = "\nYou rolled a " + numberConvert(dice1) + " and a " + numberConvert(dice2);
		// the message begins by telling the user what they rolled
		if (dice1 == 1 || dice2 == 1) {
			if (dice1 == 1 && dice2 == 1) {
				// if both are zero, the turn is over, the terminal prints the
				// rolledMessage, and returns 0 to the humanTurn() function
				// which breaks out of the player's turn by breaking the while
				// loop condition
				rolledMessage += "\nTURN OVER!" + "\n";
				System.out.println(rolledMessage);
				return 0;
			}
			// essentially the same as above, just different messages
			rolledMessage += "\nTURN OVER! Turn sum is zero!";
			System.out.println(rolledMessage);
			return 0;
		} else if (dice1 == dice2) {
			// if both dice are equal, then the rolled message prints, and
			// returns 1 to the humanTurn(), hence making the player having to
			// roll again
			rolledMessage += "\nYou must roll again!";
			System.out.println(rolledMessage);
			return 1;
		} else {
			// if its two different dice, without a one, then the message will
			// update
			rolledMessage += "\n" + user + "'s turn sum is: " + turn + ", " + user + "'s game sum is: " + playerScore
					+ "\nComputer's game sum is: " + compScore;
			// the terminal will give the user the option to roll again
			Scanner in = new Scanner(System.in);
			String answer = "y"; // variable to enter while loop
			if ("y".equals(answer)) {
				System.out.println(rolledMessage + "\nWould you like to roll again? (Enter 'y' or 'n')");
				// prints the rolled message, along with the updated scores in
				// order for the user to make a more informed decision
				answer = input(); // enters a new function
				if (answer == "yes")
					return 1;
				// if the answer is yes, then the function will return a one,
				// hence the player will roll again
				else if (answer == "no")
					return 0;
				// if the answer is no, then the function returns a 0, and the
				// player will not roll again... entering the computer's turn

			}
			System.out.println("" + user + "'s game sum is: " + turn + "\nComputer's game sum is: " + compScore);
			// outputs the message to update the user on the score and returns a
			// 0 to end the player's turn, this is necessary because if the
			// user's input to whether they want to roll again or not is
			// invalid, then their turn will be over and the score will be
			// updated
			return 0;
		}

	}

	public static String input() {
		Scanner in = new Scanner(System.in);
		String answer = in.next();
		// needed to get the user's input on whether they want to roll again
		if ("y".equals(answer))
			// above is how to get the answer from a string
			return "yes";
		else if ("n".equals(answer))
			return "no";
		else {
			System.out.println("INVALID INPUT, TURN OVER");
			return "else";
		}
		// pretty standard function, if the user chooses to not answer 'y' or
		// 'n' their turn will be over due to not following the given
		// instructions
	}

	public static int compChoice(int compScore, int humanScore, int turn, int dice1, int dice2, String user) {
		String message = "" + "Computer rolled a " + numberConvert(dice1) + " , and a " + numberConvert(dice2);
		int remainScore = 100 - compScore;
		if (dice1 == 1 && dice2 == 1) {
			message += "\nTURN OVER";
			System.out.println(message);
			return 0;
		} else if (dice1 == 1 || dice2 == 1) {
			message += "\nTURN OVER! Turn sum is zero!" + "\nComputer's game sum is: " + compScore + ", and " + user
					+ "'s game sum is: " + humanScore;
			System.out.println(message);
			return 0;
		} else if (dice1 == dice2) {
			return 1;
			// above code is similar to the playerMessage, except directed for
			// the computer
		} else {
			if (turn < remainScore) // this is the decision making process for
									// the computer, if the turn score is less
									// than the score needed, then the computer
									// will enter the algorithm
				if (compScore < 15 || (compScore > 25 && compScore < 35) || (compScore > 50 && compScore < 65)
						|| (compScore > 77 && compScore < 88)) {
					// if the computer's score is between the above, then it
					// will roll again, and output the message to update the
					// user on how the computer rolled
					System.out.println(message);
					return 1;
				} else {
					// if the computer is not between the above scores, then
					// they will not roll again, and return a 0 in order to quit
					// their turn, and output the message
					message += "\nComputer's turn sum is: " + turn + ", and computer's game sum is: " + compScore + "\n"
							+ user + "'s game sum is: " + humanScore;
					System.out.println(message);
					return 0;
				}

		}
		// if the computer's turn score is not less than the score needed the
		// computer will not roll again, and will output the score in the
		// terminal using the below message
		message += "\nComputer's turn sum is: " + turn + ", and computer's game sum is: " + compScore + "\n" + user
				+ "'s game sum is: " + humanScore;
		System.out.println(message);

		return 0;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int game = 0; // this allows the game to begin, by entering the while
						// loop

		goAgain: while (game == 0) {
			gameTime(); // enters the game

			int output = 0; // allows the exit statement to begin

			while (output == 0) {
				System.out.println("\nWould you like to play again? (Enter 'yes' or 'no'):");
				Scanner in = new Scanner(System.in); // enables the user to
														// answer the question
														// in the terminal
				String answer = in.next(); // whatever the user inputs, the
											// answer will read it and set it as
											// the answer
				if ("yes".equals(answer)) { // if the user answers yes, then the
											// game will restart by breaking out
											// of this loop and replaying
											// gametime()
					game = 0;
					break;
				} else if ("no".equals(answer)) { // if the user's answer is no,
													// then the game will finish
													// by breaking out of the
													// main loop
					game = 1;
					break goAgain;
				} else {
					System.out.println("Invalid input!"); // if the user
															// answer's anything
															// else, the loop
															// will restart and
															// ask the user to
															// input something
															// else
					output = 0;
				}
			}
		}
	}
}

/**
 * @author Michael Magbual
 * @version 0.0.1
 * @date 10/23/22
 * @assignment Mancala
 */

package gui.model;

import java.util.Arrays;

public class MancalaModel extends BaseModel {
	public enum GameState { START, IN_GAME, END }
	
	public enum Player {
		PLAYER_ONE("Player One"),
		PLAYER_TWO("Player Two");

		private final String winner;

		Player(String winner) {
			this.winner = winner;
		}
	}
	
	
	private Player currentPlayer;
	private GameState state;


	//for checking if certain player has
	//already used their undo during a turn
	private boolean undoAlreadyUsed;

	private int playerOneUndo;
	private int playerTwoUndo;
	private boolean lastStoneInCala;

	//number of undos for each player
	private final int TOTAL_UNDOS_PER_TURN = 3;
	
	//for each individual pit
	private int[] pits;
	
	//for undo option 
	private int[] undoPits;
	
	
	//total number of pits in the game
	private static final int TOTAL_PITS = 14;
	
	
	// index for each player's mancalas
	private static final int PLAYER_ONE_MANCALA_INDEX = 6;
	private static final int PLAYER_TWO_MANCALA_INDEX = 13;

	//for displaying winner at the end of the game
	private String winner;
	
	public MancalaModel() {
		pits = new int[TOTAL_PITS];
		undoPits = new int[TOTAL_PITS];
		
		state = GameState.START;
		currentPlayer = Player.PLAYER_ONE;
		
		playerOneUndo = 0;
		playerTwoUndo = 0;
		lastStoneInCala = false;
		undoAlreadyUsed = false;
	}

	
	//figuring out who owns current pit 
	public Player whichPlayerPit(int currentPit) {
		if (currentPit == PLAYER_ONE_MANCALA_INDEX) {
			return Player.PLAYER_ONE;
		} else { 
			return Player.PLAYER_TWO;
		}
		
		
		
	}
	//for changing players when their turn's are over
	public void interchange() {
		if (currentPlayer == Player.PLAYER_TWO) {
			currentPlayer = Player.PLAYER_ONE;
		} else {
			currentPlayer = Player.PLAYER_TWO;
		}
	}

	//checking for if inside the players' mancala
	//or if the current pit is a mancala
	public boolean inCala(int currentPit) {
		return (currentPit == PLAYER_ONE_MANCALA_INDEX || currentPit == PLAYER_TWO_MANCALA_INDEX);
	}
	
	//getting stones and count 
	public void stoneCount(int mNum) {
		
		for (int i = 1; i <= pits.length; i++) {
			
			//checks that i isn't in either 
			//cala's index
			if (!(inCala(i))) {
				
				pits[i] = mNum;
			}
		}
	}
	
	//for moving stones using pits' index
	public void moveStones(int index) {

		//save current game state
		//undoPits = pits.clone();

		int stoneNum = pits[index];
		pits[index] = 0;

		int pit = index;

		//makes sure undo is still available
		undoAlreadyUsed = false;

		//resetting undos
		if(currentPlayer.equals(Player.PLAYER_ONE)) {
			playerTwoUndo = 0;
		} else {
			playerOneUndo = 0;
		}

		while (stoneNum > 0) {
			//save current game state
			undoPits = pits.clone();
			pit++;
			
			if (pit >= TOTAL_PITS) {
				pit = 0;
			}

			if (inCala(pit) && whichPlayerPit(pit) 
					!= currentPlayer) {
				continue;
			}

			pits[pit] += 1;
			stoneNum--;
		}

		//checking for capture on player one's side
		if (currentPlayer == Player.PLAYER_ONE) {
			if (pit < 6 && pits[pit] == 1 && pits[getOtherSidePit(pit)] > 0) {

				pits[6] += pits[getOtherSidePit(pit)] + 1;

				//clear pits where capture occured

				pits[pit] = 0;
				pits[getOtherSidePit(pit)] = 0;
			}
		}

		//checking for capture on player two's side
		if (currentPlayer == Player.PLAYER_TWO) {
			if (pit >= 7 && pit < 13 && pits[pit] == 1 && pits[getOtherSidePit(pit)] > 0) {

				pits[13] += pits[getOtherSidePit(pit)] + 1;

				//clear pits where capture occured

				pits[pit] = 0;
				pits[getOtherSidePit(pit)] = 0;
			}
		}

		//checks to see where last stone
		//is placed and
		findLastStones(pit);

		//check for winner and
		//if possible then print winner
		checkWinner();

		//notify listeners
		dispatchEvent("update:pits");

	}
	
	//for undoing current player's most recent move
	public void undo() {

		boolean alreadyUsedUndo = false;

		//checking if undo was already used prior
		if (!undoAlreadyUsed) {
			return;
		}

		//checking player one
		if (!lastStoneInCala && playerOneUndo < TOTAL_UNDOS_PER_TURN) {
			playerOneUndo++;
			alreadyUsedUndo = true;
		} else if(lastStoneInCala && playerOneUndo < TOTAL_UNDOS_PER_TURN) {
			playerOneUndo++;
			alreadyUsedUndo = true;
		}
		
		//checking player two
		if (!lastStoneInCala && playerTwoUndo < TOTAL_UNDOS_PER_TURN) {
			playerTwoUndo++;
			alreadyUsedUndo = true;
		} else if(lastStoneInCala && playerTwoUndo < TOTAL_UNDOS_PER_TURN) {
			playerTwoUndo++;
			alreadyUsedUndo = true;
		}

		if (alreadyUsedUndo) {
			pits = undoPits.clone();
			alreadyUsedUndo = false;
		}
	}
	
	//for checking whether pits have no stones
	public boolean checkIfPitsEmpty() {
		
		int pit1 = 0;
		int pit2 = 0;
		
		for (int i = 0; i < TOTAL_PITS; ++i) {
			//checks that i isn't in either 
			//cala's index
			if (!(inCala(i))) {
				//nested if statement to determine 
				//current player
				if (whichPlayerPit(i) == Player.PLAYER_ONE) {
					pit1 += pits[i];
				} else {
					pit2 += pits[i];
				}
			}
		}

		return (pit1 == 0 || pit2 == 0);
	}
	
	
	//for obtaining and moving the leftover stones
	//into respective players' mancalas
	public void moveLastStonesToCala() {
		for (int i = 0; i < TOTAL_PITS; ++i)
			//checks that i isn't in either 
			//cala's index 
			if (!inCala(i)) {
				//nested if statement to determine 
				//current player
				if (whichPlayerPit(i) == Player.PLAYER_ONE) {
					pits[PLAYER_ONE_MANCALA_INDEX] += pits[i];
					pits[i] = 0;
				} else {
					pits[PLAYER_TWO_MANCALA_INDEX] += pits[i];
					pits[i] = 0;
				}
			}
	}
	
	//recreating this method to check for
	//-if the last stone fell into the current player's mancala
	// -> another turn for current player
	//-if the last stone fell into an empty pit anywhere on the board
	// 	*if stone fell on own empty side
	// 		-> collect stolen stones + own stone
	public void findLastStones(int pit) {

		//if last stone placed in own current player's mancala
		if (whichPlayerPit(pit) == currentPlayer && inCala(pit)) {

			//current player has another turn
			lastStoneInCala = true;
		} 

		//if stone falls into empty pit on current player's side
		else if(whichPlayerPit(pit) == currentPlayer && inCala(pit)
			&& pits[pit] == 1 && pits[getOtherSidePit(pit)] >= 0) {

			//steal stones from opposing side and the one placed
			//on current player's side
				int stealStone = pits[pit] +
						pits[getOtherSidePit(pit)];

				pits[pit] = pits[getOtherSidePit(pit)] = 0;

			//if current player is pOne then add stolen stones
			//into pOne's mancala
			if (whichPlayerPit(pit) == Player.PLAYER_ONE) {

				pits[PLAYER_ONE_MANCALA_INDEX] += stealStone;

				//if current player is pTwo then add stolen stones
				//into pTwo's mancala
				} else {

					pits[PLAYER_TWO_MANCALA_INDEX] += stealStone;
				}

				//change current player
				lastStoneInCala = false;
				
				pits[pit] = 0;
				pits[getOtherSidePit(pit)] = 0;
				
					if (whichPlayerPit(pit) == Player.PLAYER_ONE) {
						
						pits[PLAYER_ONE_MANCALA_INDEX] += stealStone;
						
					} else {
						
						pits[PLAYER_TWO_MANCALA_INDEX] += stealStone;
					}
					
				lastStoneInCala = false;

				checkWinner();
//				interchange();
			
		} else {
			//change current player
			lastStoneInCala = false;
//			interchange();
		}
	}
	
	//created for method ^
	//getting the pit on the other side of the board
	public int getOtherSidePit(int pit) {
		if ( pit <= 12) {
			return 12 - pit;
		} else {
			return pit - 12;
		}
	}

	
	public GameState getGameState() {
		return state;
	}
	
	
	public int[] getPits() {
		return pits;
	}
	
	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	/**
	 * Initializes all pockets.
	 *
	 * @param stonedCount the number of stones to be placed into each pocket
	 */
	public void resetPockets(int stonedCount) {
		Arrays.fill(pits, stonedCount);

		// empty mancala pockets
		pits[6] = pits[13] = 0;

		dispatchEvent("update:pits");
	}

	public boolean getCanUndo() {
		return !undoAlreadyUsed;
	}

	public boolean getCanEndTurn() {
		return undoAlreadyUsed;
	}

	public boolean validPit(int pits) {

		// for if player tries to make a move on opposing pit
		if (pits < 0 || pits > TOTAL_PITS) {
			return false;
		} else return pits != PLAYER_ONE_MANCALA_INDEX && pits != PLAYER_TWO_MANCALA_INDEX;
	}



	public void checkWinner() {
		boolean topPitsEmpty = true;
		boolean bottomPitsEmpty = true;


		//checking if top pits has any stones
		for (int j = 7; j < 13; j++) {

			if(pits[j] > 0) {
				topPitsEmpty = false;
				break;
			}
		}

		//checking if bottom pits has any stones
		for (int i = 0; i < 6; i++) {

			if(pits[i] > 0) {
				bottomPitsEmpty = false;
				break;
			}
		}

		//take stones from pits that aren't empty and add them to
		//player's cala

		if (topPitsEmpty && !bottomPitsEmpty) {

			for (int i = 0; i < 6; i++) {
				pits[6] += pits[i];
				pits[i] = 0;
			}
		} else if (!topPitsEmpty && bottomPitsEmpty) {

			for (int j = 7; j < 13; j++) {

				pits[13] += pits[j];
				pits[j] = 0;
			}
		}

		//check whichever player has most stones

		//player one wins
		if (pits[6] > pits[13]) {
			winner = String.valueOf(getCurrentPlayer());

			//player two wins
		} else if (pits[13] > pits[6]) {
			winner = String.valueOf(getCurrentPlayer());

			//tie
		} else {
			winner = "It's a tie between both players! ";
		}


	}
}

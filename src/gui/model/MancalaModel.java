package gui.model;

import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/*
 * By: Michael Magbual
 * Created and started on 10/23/22
 * 
 * 
 */


public class MancalaModel {

	
	public static enum gameState {
		start, inGame, end;
	}
	
	public static enum players {
		pOne, pTwo;
	}
	
	
	private players pCur;
	private gameState gState;


	//for checking if certain player has
	//already used their undo during a turn
	private boolean undoAlr;
	
	
	private int pOneUndo;
	private int pTwoUndo;
	private boolean lastStoneInCala;

	//number of undos for each player
	private int totalUndos = 3;
	
	//for each individual pit
	private int[] pits;
	
	//for undo option 
	private int[] undoPits;
	
	
	//total number of pits in the game
	private static final int pitTotal = 14;
	
	
	//index for each player's mancalas
	private static final int calaOne = 6;
	private static final int calaTwo = 13;
	
	
	private ArrayList<ChangeListener> listeners;
	
	
	public MancalaModel() {
		listeners = new ArrayList<>();

		pits = new int[pitTotal];
		undoPits = new int[pitTotal];
		
		gState = gameState.start;
		pCur = players.pOne;
		
		
		pOneUndo = 0;
		pTwoUndo = 0;
		lastStoneInCala = false;
		undoAlr = false;
		
			
	}

	public void changeListeners() {
		for (ChangeListener CL : listeners) {
			CL.stateChanged(new ChangeEvent(this));
		}
	}
	
	
	
	public void add(ChangeListener CL) {
		listeners.add(CL);
	}
	
	//for saving current game stats
	public void saveCurState() {
		undoPits = pits.clone();
	}
	
	//figuring out who owns current pit 
	public players whichPlayerPit(int currentPit) {
		
		if (currentPit == calaOne) {
			return players.pOne;
			
		} else { 
			return players.pTwo;
		}
		
		
		
	}
	//for changing players when their turn's are over
	public void interchange() {
		if (pCur == players.pTwo) {
			
			pCur = players.pOne;
			
		} else {
			
			pCur = players.pTwo;
		}
	}
	
	
	//for game state
	public void currentGameState(String st) {
		
		if (st.equals("start")) {
			
			gState = gameState.start;
		} 
		else if (st.equals("inGame")) {

			gState = gameState.inGame;
		}
		else {

			gState = gameState.end;

		}

	}
	
	//checking for if inside the players' mancala
	//or if the current pit is a mancala
	public boolean inCala(int currentPit) {
		
		return (currentPit == calaOne 
				|| currentPit == calaTwo);
	}
	
	//getting stones and count 
	public void stoneCount(int mNum) {
		
		for (int i = 1; i <= pits.length; i++) {
			
			//checks that i isn't in either 
			//cala's index
			if (!(inCala(i))) {
				
				pits[i] = mNum;
			}
			
			this.changeListeners();
		}
	}
	
	
	//for moving stones using pits' index
	public void moveStones(int index) {

		//save current game state
		saveCurState();

		//makes sure undo is still available
		undoAlr = false;
		
		int stoneNum = pits[index];
		pits[index] = 0;
		
		int pit = index;
		
		if(pCur.equals(players.pOne)) {
			pTwoUndo = 0;
		} else {
			pOneUndo = 0;
		}


		while (stoneNum > 0) {
			pit++;
			
			if (pit >= pitTotal) {
				pit = 0;
			}
			


			if (inCala(pit) && whichPlayerPit(pit) 
					!= pCur) {
				continue;
			}

			
			pits[pit] += 1;
			stoneNum--;
			
		}

		//checks to see where last stone
		//is placed and
		findLastStones(pit);

		/*
		//checks for empty pits
		if (checkIfPitsEmpty()) {
			moveLastStonesToCala();
			gState = gameState.end;
		}

		/*
		*/


		//notify listeners
		changeListeners();
	}
	
	//for undoing current player's most recent move
	public void undo() {

		boolean alreadyUsed = false;

		//checking if undo was already used prior
		if (!undoAlr) {
			return;
		}

		
		
		//checking player one


		if (lastStoneInCala == false && pOneUndo < totalUndos) {
			
			pOneUndo++;
			alreadyUsed = true;
			
		} else if(lastStoneInCala == true && pOneUndo < totalUndos) {
			
			pOneUndo++;
			alreadyUsed = true;
		} 
		
		
		
		//checking player two 
		
		if (lastStoneInCala == false && pTwoUndo < totalUndos) {
			
			pTwoUndo++;
			alreadyUsed = true;
			
		} else if(lastStoneInCala == true && pTwoUndo < totalUndos) {
			
			pTwoUndo++;
			alreadyUsed = true;
		}



		if (alreadyUsed) {
			pits = undoPits.clone();

			alreadyUsed = false;
			changeListeners();
		}
	}
	
	//for checking whether pits have no stones
	public boolean checkIfPitsEmpty() {
		
		int pit1 = 0;
		int pit2 = 0;
		
		for (int i = 0; i < pitTotal; ++i) {
			
			//checks that i isn't in either 
			//cala's index
			if (!(inCala(i))) {
				
				//nested if statement to determine 
				//current player
				if (whichPlayerPit(i) == players.pOne) {
					
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
		for (int i = 0; i < pitTotal; ++i) {
			//checks that i isn't in either 
			//cala's index 
			if (!inCala(i)) {
				//nested if statement to determine 
				//current player
				if (whichPlayerPit(i) == players.pOne) {
					pits[calaOne] += pits[i];
					pits[i] = 0;
				} else {
					pits[calaTwo] += pits[i];
					pits[i] = 0;
				}
			}
		}
	}
	
	//recreating this method to check for
	//-if the last stone fell into the current player's mancala
	// -> another turn for current player
	//-if the last stone fell into an empty pit anywhere on the board
	// 	*if stone fell on own empty side
	// 		-> collect stolen marbles + own marble
	public void findLastStones(int pit) {

		//if last stone placed in own current player's mancala
		if (whichPlayerPit(pit) == pCur && inCala(pit)) {

			//current player has another turn
			lastStoneInCala = true;
		} 

		//if stone falls into empty pit on current player's side
		else if(whichPlayerPit(pit) == pCur && inCala(pit)
			&& pits[pit] == 1 && pits[getOtherSidePit(pit)] >= 0) {

			//steal marbles from opposing side and the one placed
			//on current player's side
				int stealStone = pits[pit] +
						pits[getOtherSidePit(pit)];
				pits[pit] = pits[getOtherSidePit(pit)] = 0;

			//if current player is pOne then add stolen stones
			//into pOne's mancala
			if (whichPlayerPit(pit) == players.pOne) {

				pits[calaOne] += stealStone;

				//if current player is pTwo then add stolen stones
				//into pTwo's mancala
				} else {

					pits[calaTwo] += stealStone;
				}

				//change current player
				lastStoneInCala = false;
				interchange();					
			
		} else {
			//change current player
			lastStoneInCala = false;
			interchange();
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

	
	public gameState getGameState() {
		return gState;
	}
	
	
	public int[] getPits() {
		return pits;
	}
	
	public players getCurrentPlayer() {
		return pCur;
	}

	/**
	 * Initializes all pockets.
	 *
	 * @param stonedCount the number of stones to be placed into each pocket
	 */
	public void resetPockets(int stonedCount) {
		Arrays.fill(pits, stonedCount);
		
		pits[6] = pits[13] = 0;
		
		changeListeners();
	}

	public boolean getCanUndo() {
		return false;
	}

	public boolean getCanProceedToNextTurn() {
		return false;
	}


	public boolean validPit(int pits) {

		// for if player tries to make a move on opposing pit
		if (pits < 0 || pits > pitTotal) {
			return false;
		} else if (pits == calaOne || pits == calaTwo) {
			return false;
		}

		return true;
	}


	public boolean stillInPlay(int CurrentPit) {

		//pits not empty
		if (pits[CurrentPit] == 0) {
			return false;
		}

		//not current player's pit
		if (whichPlayerPit(CurrentPit) != pCur) {
			return false;
		}

		//game is still not finished
		if (gState != gameState.inGame) {

			return false;
		}

		return true;
	}
}

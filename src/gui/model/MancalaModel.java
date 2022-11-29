/**
 * @author Michael Magbual
 * @version 0.0.1
 * @date 10/23/22
 * @assignment Mancala
 */

package gui.model;

import java.util.Arrays;
import java.util.Stack;

public class MancalaModel extends BaseModel {
	public enum GameState { IN_GAME, GAME_OVER }
	
	public enum Player { PLAYER_ONE, TIE, PLAYER_TWO }

	private Player currentPlayer;
	private GameState state;

	//number of undos for each player
	private final int TOTAL_UNDOS_PER_TURN = 3;
	
	//for each individual pit
	private int[] pits;
	
	//for undo option 
	private Stack<int[]> boardHistory;
	
	
	//total number of pits in the game
	private static final int TOTAL_PITS = 14;
	
	
	// index for each player's mancalas
	private static final int PLAYER_ONE_MANCALA_INDEX = 6;
	private static final int PLAYER_TWO_MANCALA_INDEX = 13;

	//for displaying winner at the end of the game
	private boolean canEndTurn;
	private int undosAvailable;

	private Player winner;
	
	public MancalaModel() {
		setup();
	}

	private void setup() {
		setPits(new int[TOTAL_PITS]);

		boardHistory = new Stack<>();

		setCanEndTurn(false);
		setWinner(null);

		setState(GameState.IN_GAME);
		setCurrentPlayer(Player.PLAYER_ONE);

		setUndosAvailable(TOTAL_UNDOS_PER_TURN);
	}

	
	//figuring out who owns current pit 
	private Player whichPlayerPit(int currentPit) {
		return currentPit == PLAYER_ONE_MANCALA_INDEX ? Player.PLAYER_ONE : Player.PLAYER_TWO;
	}

	/**
	 * for changing players when their turns are over
	 */
	public void endTurn() {
		// we can only change players if the current player
		//  has taken their turn
		if (canEndTurn) {
			checkWinState();

			boardHistory.clear();
			setCanEndTurn(false);
			setUndosAvailable(TOTAL_UNDOS_PER_TURN);
			setCurrentPlayer(currentPlayer == Player.PLAYER_ONE ? Player.PLAYER_TWO : Player.PLAYER_ONE);
		}
	}

	private void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
		dispatchEvent("update:currentPlayer");
	}

	//checking for if inside the players' mancala
	//or if the current pit is a mancala
	private boolean inMancala(int pit) {
		return (pit == PLAYER_ONE_MANCALA_INDEX || pit == PLAYER_TWO_MANCALA_INDEX);
	}

	private boolean isWithinPlayerOnePockets(int index) {
		return  index <= 5 && index >= 0;
	}

	private boolean isWithinPlayerTwoPockets(int index) {
		return  index <= 12 && index >= 7;
	}

	private boolean isMancalaIndex(int index) {
		return  index == 6 || index == 13;
	}
	
	//for moving stones using pits' index
	public void moveStonesAtIndex(int index) {
		// ignore move calls on mancala indexes
		if (isMancalaIndex(index)) return;

		if (currentPlayer == Player.PLAYER_ONE && isWithinPlayerTwoPockets(index)) {
			return;
		}

		if (currentPlayer == Player.PLAYER_TWO && isWithinPlayerOnePockets(index)) {
			return;
		}

		// if they already took a turn and have not used an undo yet
		if (canEndTurn) return;

		// can't move and empty pocket
		if (pits[index] == 0) return;

		//save current game state
		boardHistory.push(pits.clone());

		int stonesToDistribute = pits[index];
		pits[index] = 0;

		int pit = index;

		while (stonesToDistribute > 0) {
			//save current game state
			pit++;
			
			if (pit >= TOTAL_PITS) {
				pit = 0;
			}

			if (inMancala(pit) && whichPlayerPit(pit) != currentPlayer) {
				continue;
			}

			pits[pit] += 1;
			stonesToDistribute--;
		}

		//checking for capture on player one's side
		if (currentPlayer == Player.PLAYER_ONE) {
			if (pit < 6 && pits[pit] == 1 && pits[getOtherSidePit(pit)] > 0) {

				pits[PLAYER_ONE_MANCALA_INDEX] += pits[getOtherSidePit(pit)] + 1;

				//clear pits where capture occurred

				pits[pit] = 0;
				pits[getOtherSidePit(pit)] = 0;
			}
		}

		//checking for capture on player two's side
		if (currentPlayer == Player.PLAYER_TWO) {
			if (pit >= 7 && pit < 13 && pits[pit] == 1 && pits[getOtherSidePit(pit)] > 0) {

				pits[PLAYER_TWO_MANCALA_INDEX] += pits[getOtherSidePit(pit)] + 1;

				//clear pits where capture occurred

				pits[pit] = 0;
				pits[getOtherSidePit(pit)] = 0;
			}
		}

		//checks to see where last stone
		//is placed and
		findLastStones(pit);

		if (!canEndTurn) {
			// if either side is empty the game can be
			// ended by ending the current turn
			setCanEndTurn(isEitherSideEmpty());
		}

		//notify listeners
		dispatchEvent("update:pits");
		dispatchEvent("update:canUndo");
	}
	
	//for undoing current player's most recent move
	public void undo() {
		if (getCanUndo()) {
			setUndosAvailable(undosAvailable - 1);
			setCanEndTurn(false);
			setPits(boardHistory.pop());
			dispatchEvent("update:canUndo");
		}
	}

	//recreating this method to check for
	//-if the last stone fell into the current player's mancala
	// -> another turn for current player
	//-if the last stone fell into an empty pit anywhere on the board
	// 	*if stone fell on own empty side
	// 		-> collect stolen stones + own stone
	private void findLastStones(int pit) {
		//if last stone placed in own current player's mancala
		if (whichPlayerPit(pit) == currentPlayer && inMancala(pit)) return;

		//if stone falls into empty pit on current player's side
		if(whichPlayerPit(pit) == currentPlayer && inMancala(pit) && pits[pit] == 1 && pits[getOtherSidePit(pit)] >= 0) {

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
				pits[pit] = 0;
				pits[getOtherSidePit(pit)] = 0;
				
				if (whichPlayerPit(pit) == Player.PLAYER_ONE) {

					pits[PLAYER_ONE_MANCALA_INDEX] += stealStone;

				} else {

					pits[PLAYER_TWO_MANCALA_INDEX] += stealStone;
				}
		}

		setCanEndTurn(true);
	}
	
	//created for method ^
	//getting the pit on the other side of the board
	private int getOtherSidePit(int pit) {
		if ( pit <= 12) {
			return 12 - pit;
		} else {
			return pit - 12;
		}
	}

	private boolean isEitherSideEmpty() {
		boolean topPitsEmpty = true;
		boolean bottomPitsEmpty = true;

		//checking if top pits has any stones
		for (int j = PLAYER_ONE_MANCALA_INDEX + 1; j < PLAYER_TWO_MANCALA_INDEX; j++) {
			if(pits[j] > 0) {
				topPitsEmpty = false;
				break;
			}
		}

		//checking if bottom pits has any stones
		for (int i = 0; i < PLAYER_ONE_MANCALA_INDEX; i++) {
			if(pits[i] > 0) {
				bottomPitsEmpty = false;
				break;
			}
		}

		return topPitsEmpty || bottomPitsEmpty;
	}


	private void checkWinState() {
		if (!isEitherSideEmpty()) return;

		//take stones from pits that aren't empty and add them to
		//player's mancala
		for (int i = 0; i < PLAYER_ONE_MANCALA_INDEX; i++) {
			pits[PLAYER_ONE_MANCALA_INDEX] += pits[i];
			pits[i] = 0;
		}
		for (int j = PLAYER_ONE_MANCALA_INDEX + 1; j < PLAYER_TWO_MANCALA_INDEX; j++) {
			pits[PLAYER_TWO_MANCALA_INDEX] += pits[j];
			pits[j] = 0;
		}

		setState(GameState.GAME_OVER);

		//check whichever player has most stones
		if (pits[PLAYER_ONE_MANCALA_INDEX] > pits[PLAYER_TWO_MANCALA_INDEX]) {
			setWinner(Player.PLAYER_ONE);
		} else if (pits[PLAYER_TWO_MANCALA_INDEX] > pits[PLAYER_ONE_MANCALA_INDEX]) {
			setWinner(Player.PLAYER_TWO);
		} else {
			setWinner(Player.TIE);
		}

		dispatchEvent("update:pits");
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
		setup();

		Arrays.fill(pits, stonedCount);

		// empty mancala pockets
		pits[PLAYER_ONE_MANCALA_INDEX] = pits[PLAYER_TWO_MANCALA_INDEX] = 0;

		dispatchEvent("update:pits");
	}

	public boolean getCanUndo() {
		return boardHistory.size() > 0 && undosAvailable > 0;
	}

	public int getPlayerOneScore() {
		return pits[PLAYER_ONE_MANCALA_INDEX];
	}

	public int getPlayerTwoScore() {
		return pits[PLAYER_TWO_MANCALA_INDEX];
	}

	public boolean getCanEndTurn() {
		return canEndTurn;
	}

	private void setCanEndTurn(boolean canEndTurn) {
		this.canEndTurn = canEndTurn;
		dispatchEvent("update:canUndo");
		dispatchEvent("update:canEndTurn");
	}

	public int getUndosAvailable() {
		return undosAvailable;
	}

	private void setUndosAvailable(int undosAvailable) {
		this.undosAvailable = undosAvailable;
		dispatchEvent("update:undosAvailable");
	}

	public int[] getPits() {
		return pits;
	}

	public void setPits(int[] pits) {
		this.pits = pits;
		dispatchEvent("update:pits");
	}

	public GameState getState() {
		return state;
	}

	private void setState(GameState state) {
		this.state = state;
		dispatchEvent("update:state");
	}

	public Player getWinner() {
		return winner;
	}

	private void setWinner(Player winner) {
		this.winner = winner;
		dispatchEvent("update:winner");
	}
}

/**
 * @author Michael Magbual
 * @version 0.0.1
 * @date 10/23/22
 * @assignment Mancala
 */

package gui.model;

import java.util.Arrays;
import java.util.Stack;

/**
 * The Mancala data model. Contains all the information regarding the state of the game.
 *
 * @author Michael Magbual
 * @author Noah Cardoza
 */
public class MancalaModel extends BaseModel {
	/**
	 * for game state constants
	 * uses state design pattern (sort of)
	 */
	public enum GameState {
		/**
		 * In game state. When the game is in progress.
		 */
		IN_GAME,
		/**
		 * Game over game state. When the game is over.
		 */
		GAME_OVER }

	/**
	 * for current player constants and
	 * to be used for assigning winner later
	 */
	public enum Player {
		/**
		 * Player one player.
		 */
		PLAYER_ONE,
		/**
		 * Tie player.
		 */
		TIE,
		/**
		 * Player two player.
		 */
		PLAYER_TWO }

	/**
	 * for the current player
	 */
	private Player currentPlayer;

	/**
	 * for current game state
	 */
	private GameState state;

	/**
	 * number of undos for each player
	 */
	private final int TOTAL_UNDOS_PER_TURN = 3;

	/**
	 * int array for each individual pit on the board
	 */
	private int[] pits;

	/**
	 * stack for previous versions of the board
	 * prior to current player's input
	 */
	private Stack<int[]> boardHistory;


	/**
	 * total number of pits in the game
	 */
	private static final int TOTAL_PITS = 14;


	/**
	 * index for player one's mancala
	 */
	private static final int PLAYER_ONE_MANCALA_INDEX = 6;

	/**
	 * index for player two's mancala
	 */
	private static final int PLAYER_TWO_MANCALA_INDEX = 13;

	/**
	 * for checking if current player's turn can end
	 */
	private boolean canEndTurn;


	/**
	 * for checking if any undos are available to be used
	 */
	private int undosAvailable;

	/**
	 * for use of determining and assigning the game winner
	 */
	private Player winner;


	/**
	 * Instantiates a new Mancala model.
	 */
	public MancalaModel() {
		setup();
	}

	/**
	 * Used to set up the game board and reestablish the initial game board state.
	 */
	private void setup() {
		setPits(new int[TOTAL_PITS]);

		boardHistory = new Stack<>();

		setCanEndTurn(false);
		setWinner(null);

		setState(GameState.IN_GAME);
		setCurrentPlayer(Player.PLAYER_ONE);

		setUndosAvailable(TOTAL_UNDOS_PER_TURN);
	}


	/**
	 * Determines which player's an index belongs to
 	 * @param currentPit the current pit to be checked
	 * @return whichever player owns the current pit
	 */
	private Player whichPlayerPit(int currentPit) {
		return currentPit == PLAYER_ONE_MANCALA_INDEX ? Player.PLAYER_ONE : Player.PLAYER_TWO;
	}

	/**
	 * Ends the turn of the current player.
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

	/**
	 * changes the current player
	 * @param currentPlayer the current player moving stones
	 */
	private void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
		dispatchEvent("update:currentPlayer");
	}

	/**
	 * checks if current pit is a mancala
	 * @param pit the current pit
	 * @return true if in either player's mancala
	 */
	private boolean inMancala(int pit) {
		return (pit == PLAYER_ONE_MANCALA_INDEX || pit == PLAYER_TWO_MANCALA_INDEX);
	}

	/**
	 * checks if pit index resides with any of player one's indexes
	 * @param index current pit index
	 * @return true if current pit index is equal to
	 * any of the pits of player one
	 */
	private boolean isWithinPlayerOnePockets(int index) {
		return  index <= 5 && index >= 0;
	}

	/**
	 * checks if pit index resides with any of player two's indexes
	 * @param index current pit index
	 * @return true if current pit index is equal to
	 * any of the pits of player two
	 */
	private boolean isWithinPlayerTwoPockets(int index) {
		return  index <= 12 && index >= 7;
	}

	/**
	 * checks if pit index is the same as either player's mancala index
	 * @param index current pit index
	 * @return true if current pit index is equal to
	 * mancala index of either player
	 */
	private boolean isMancalaIndex(int index) {
		return  index == 6 || index == 13;
	}

	/**
	 * moves stones using pit index.
	 * checks where last stone was placed.
	 * checks if already moved and able to steal stones.
	 *
	 * @param index of the current pit.
	 */
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

	/**
	 * for undoing current player's most recent move
	 */
	public void undo() {
		if (getCanUndo()) {
			setUndosAvailable(undosAvailable - 1);
			setCanEndTurn(false);
			setPits(boardHistory.pop());
			dispatchEvent("update:canUndo");
		}
	}

	/**
	 *
	 * checks where the last stone is placed.
	 * if the last stone is placed in the current player's mancala,
	 * then give another turn for current player.
	 *
	 * if the last stone is placed on an empty pit on the current player's side
	 * and there are stones on the opposite pit of the board,
	 * then steal stones from the opposite pit and take into current player's mancala
	 * along with the last stone placed in empty pit.
	 *
	 * @param pit current pit that the last stone was placed in.
	 *            check this pit.
	 */
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

	/**
	 * finds the opposing pit of the current pit
	 * @param pit takes the current pit
	 * @return the pit on the opposite side of the current pit
	 */
	private int getOtherSidePit(int pit) {
		if ( pit <= 12) {
			return 12 - pit;
		} else {
			return pit - 12;
		}
	}


	/**
	 * checks if there are no more stones present in the pits of
	 * either player's side.
	 * @return the side that's empty.
	 */
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


	/**
	 *
	 * checks win condition by first adding up all the stones
	 * on the nonempty side then compares between the two players
	 * to see who has more stones and is the winner.
	 *
	 */
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

	/**
	 * Gets current player.
	 *
	 * @return the current player
	 */
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


	/**
	 * Gets can undo.
	 *
	 * @return the can undo
	 */
	public boolean getCanUndo() {
		return boardHistory.size() > 0 && undosAvailable > 0;
	}

	/**
	 * Gets player one score.
	 *
	 * @return the player one score
	 */
	public int getPlayerOneScore() {
		return pits[PLAYER_ONE_MANCALA_INDEX];
	}


	/**
	 * Gets player two score.
	 *
	 * @return the player two score
	 */
	public int getPlayerTwoScore() {
		return pits[PLAYER_TWO_MANCALA_INDEX];
	}


	/**
	 * Gets can end turn.
	 *
	 * @return the can end turn
	 */
	public boolean getCanEndTurn() {
		return canEndTurn;
	}


	private void setCanEndTurn(boolean canEndTurn) {
		this.canEndTurn = canEndTurn;
		dispatchEvent("update:canUndo");
		dispatchEvent("update:canEndTurn");
	}

	/**
	 * Gets undos available.
	 *
	 * @return the undos available
	 */
	public int getUndosAvailable() {
		return undosAvailable;
	}


	private void setUndosAvailable(int undosAvailable) {
		this.undosAvailable = undosAvailable;
		dispatchEvent("update:undosAvailable");
	}


	/**
	 * Get the board pits.
	 *
	 * @return the pits
	 */
	public int[] getPits() {
		return pits;
	}


	/**
	 * Sets pits.
	 *
	 * @param pits the pits
	 */
	private void setPits(int[] pits) {
		this.pits = pits;
		dispatchEvent("update:pits");
	}


	/**
	 * Gets the game state.
	 *
	 * @return the state
	 */
	public GameState getState() {
		return state;
	}


	private void setState(GameState state) {
		this.state = state;
		dispatchEvent("update:state");
	}


	/**
	 * Gets the winner.
	 *
	 * @return the winner
	 */
	public Player getWinner() {
		return winner;
	}


	private void setWinner(Player winner) {
		this.winner = winner;
		dispatchEvent("update:winner");
	}
}

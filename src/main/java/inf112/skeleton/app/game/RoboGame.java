package inf112.skeleton.app.game;

import com.badlogic.gdx.scenes.scene2d.Stage;
import inf112.skeleton.app.assets.Definitions;
import inf112.skeleton.app.assets.Definitions.ActivityType;
import inf112.skeleton.app.assets.Player;
import inf112.skeleton.app.assets.cards.ProgramCard;
import inf112.skeleton.app.assets.cards.ProgramDeck;
import inf112.skeleton.app.screens.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RoboGame extends com.badlogic.gdx.Game {

	private final ProgramDeck programDeck;
	private final List<Player> players;
	public Player localPlayer;
	final int MAX_NUMBER_OF_CARDS = 9;

	RoboRallyClient roboClient;
	RoboRallyServer roboServer;

	Activity currentActivity;
	ActivityType lastActivityType;

	boolean gameStarted;
	boolean multiplayer;

	// Declaration of screens
	MainMenuScreen mainMenuScreen;
	MultiplayerSetupScreen multiplayerSetupScreen;
	GameActionScreen gameActionScreen;
	List<ProgramCard> cards;
	/**
	 * Duration of each program card execution in seconds
	 */
	private final long PROGRAMCARD_DURATION;

	/**
	 * The standard duration of each activity that is not instant or unbound
	 */
	private final long STANDARD_DURATION;

	/**
	 * How long to wait for incoming connections
	 */
	private final long WAITCONNECTIONDURATION;

	private final long NUMBER_OF_PHASES;
	int phaseNumber;

	public RoboGame() {
		programDeck = new ProgramDeck();
		programDeck.createDeck();
		players = new LinkedList<>();
		currentActivity = new Activity(Definitions.ActivityType.OPEN_MENU, -1);
		lastActivityType = Definitions.ActivityType.EXECUTE_PROGRAMCARDS_5;
		multiplayer = false; // Will be changed if the server starts
		gameStarted = false;
		PROGRAMCARD_DURATION = 1;
		STANDARD_DURATION = 1;
		WAITCONNECTIONDURATION = 5;
		NUMBER_OF_PHASES = 5;
		phaseNumber = 0;
	}

	@Override
	public void create() {
	}

	public void launchGame() {
//        addPlayer(new Player("Player 2", 8, 4)); // TODO For testing purposes, remove

		gameActionScreen = new GameActionScreen(this, "exchange.tmx");
		setScreen(gameActionScreen);
		gameStarted = true;
	}

	public void initiateMultiplayer() {
		multiplayerSetupScreen = new MultiplayerSetupScreen(this);
		setScreen(multiplayerSetupScreen);
		multiplayer = true;
	}

	public void connectToHost(String serverIp, String nickname) {
		roboClient = new RoboRallyClient(this);
		roboClient.connectToServer(serverIp, nickname);
	}

	public void startHost(String nickname) {
		roboServer = new RoboRallyServer(this, nickname);
		roboServer.startServer();
	}

	public void addPlayer(Player player) {
		players.add(player);
		System.out.println(players.toString());
	}

	public List<Player> getPlayers() {
		return players;
	}

	@Override
	public void render() {
		super.render();
		tick();
	}

	/**
	 * The ticker is called several times a second, via the LibGDX render()-method
	 */
	void tick() {
		// TODO Check win conditions before the if-statement

		// Print the current activity
		if (lastActivityType != currentActivity.currentType) {
			System.out.println(currentActivity.currentType);
			lastActivityType = currentActivity.currentType;
		}

		switch (currentActivity.currentType) {
		case OPEN_MENU:
			openMenu();
			break;
		case WAIT_FOR_MENU_SELECTION:
			waitForMenuSelection();
			break;
		case CHECK_MULTIPLAYER:
			checkMultiplayer();
			break;
		case WAIT_FOR_CONNECTIONS:
			waitForConnections();
			break;
		case PICK_BOARD:
			pickBoard();
			break;
		case DEAL_CARDS:
			dealCards();
			break;
		case ARRANGE_CARDS_AND_ANNOUNCE_INTENT:
			arrangeCards();
			break;
		case COMPLETE_REGISTERS:
			completeRegisters();
			break;
		case HALT:
			break;
		}
	}

	private void dealCards() {
		dealProgramCards();
		gameActionScreen.showCards();
		currentActivity = new Activity(Definitions.ActivityType.ARRANGE_CARDS_AND_ANNOUNCE_INTENT, 10);
	}

	// "Handle input"
	private void arrangeCards() {

		if (currentActivity.hasTimedOut()) {
			gameActionScreen.clearCards();
			dealCards();

			for (Player player : players) {
				if (!player.hasChosenProgramCards()) {
					System.out.println(player + " has not chosen program cards"); // TODO remove
					List<ProgramCard> cards = player.getReceivedProgramCards();
					while (cards.size() > 5)
						cards.remove(0);
					System.out.println(cards); // TODO remove
					player.receiveChosenProgramCards(cards);

				}

			}			

		}



	}

	private void pickBoard() {
		if (currentActivity.hasTimedOut()) {
			currentActivity = new Activity(Definitions.ActivityType.DEAL_CARDS, STANDARD_DURATION);
		}
	}

	private void waitForConnections() {
		if (currentActivity.hasTimedOut()) {
			// TODO decide if one should add an AI player if noone has connected
			currentActivity = new Activity(Definitions.ActivityType.PICK_BOARD, STANDARD_DURATION);
		}
	}

	private void checkMultiplayer() {
		if (gameStarted) {
			if (multiplayer)
				currentActivity = new Activity(Definitions.ActivityType.WAIT_FOR_CONNECTIONS, WAITCONNECTIONDURATION);
			else
				currentActivity = new Activity(Definitions.ActivityType.PICK_BOARD, STANDARD_DURATION);
		}
	}

	private void waitForMenuSelection() {
		if (gameStarted)
			currentActivity = new Activity(Definitions.ActivityType.CHECK_MULTIPLAYER, -1);
	}

	private void openMenu() {
		mainMenuScreen = new MainMenuScreen(this);
		setScreen(mainMenuScreen);
		currentActivity = new Activity(Definitions.ActivityType.WAIT_FOR_MENU_SELECTION, -1);
	}

	public void completeRegisters() {
		if (phaseNumber < NUMBER_OF_PHASES) {
			revealCards();
			robotsMove();
			boardElementsMove();
			lasersFire();
			touchFlagsCheckpoints();
			if (checkWinner())
				announceWinner();
			phaseNumber++;
		} else {
			phaseNumber = 0;
//            gameActionScreen.hideCards();
			currentActivity = new Activity(ActivityType.DEAL_CARDS, STANDARD_DURATION);
		}
	}

	private void announceWinner() {
		System.out.println("Winner!");
	}

	public void revealCards() {

	}

	public void robotsMove() {
		for (Player player : players) {
			player.moveRobotByProgramCard(player.getProgramCard(phaseNumber));
		}
		if (currentActivity.hasTimedOut()) {
			currentActivity = new Activity(Definitions.ActivityType.EXECUTE_PROGRAMCARDS_2, PROGRAMCARD_DURATION);
		}
	}

	public void boardElementsMove() {

	}

	public void lasersFire() {

	}

	public void touchFlagsCheckpoints() {

	}

	public boolean checkWinner() {
		return false;
	}

	/**
	 * Deal the program cards
	 */
	public void dealProgramCards() {
		for (Player player : players) {
			cards = new ArrayList<>(); // Create a small deck of cards for each player
			cards.addAll(programDeck.draw(MAX_NUMBER_OF_CARDS - player.getDamage()));
			player.receiveProgramCardsToPick(cards); // Each player receives it's cards
			System.out.println();
			for (ProgramCard c : cards) {
				System.out.println(c.getProgramCardType());
			}

		}
	}
}

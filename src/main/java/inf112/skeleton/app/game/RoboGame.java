package inf112.skeleton.app.game;

import inf112.skeleton.app.assets.Definitions;
import inf112.skeleton.app.assets.Definitions.ActivityType;
import inf112.skeleton.app.assets.Player;
import inf112.skeleton.app.assets.cards.ProgramCard;
import inf112.skeleton.app.assets.cards.ProgramDeck;
import inf112.skeleton.app.screens.*;
import inf112.skeleton.app.server.RoboRallyClient;
import inf112.skeleton.app.server.RoboRallyServer;
import org.lwjgl.system.CallbackI;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class RoboGame extends com.badlogic.gdx.Game {

    private final ProgramDeck programDeck;
    final int MAX_NUMBER_OF_CARDS = 9;

	public RoboRallyClient roboClient;
	public RoboRallyServer roboServer;

    // Server metadata
    public String ip;
    public Player host;
    public Player localPlayer;
    public List<Player> players;
    public Stack<Player> turns;

    Activity currentActivity;
    ActivityType lastActivityType;

	boolean gameStarted;
	boolean multiplayer;

	long lastMoveTimestamp;
	long WAIT_BETWEEN_MOVES = 500l; // milliseconds

	// Declaration of screens
	MainMenuScreen mainMenuScreen;
	MultiplayerSetupScreen multiplayerSetupScreen;
	GameActionScreen gameActionScreen;
	PlayersScreen playersScreen;
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
	private final long WAIT_CONNECTION_DURATION;

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
		WAIT_CONNECTION_DURATION = 300;
		NUMBER_OF_PHASES = 5;
		phaseNumber = 0;
		lastMoveTimestamp = 0l;
	}

	@Override
	public void create() {
	}

	public void launchGame() {
		gameActionScreen = new GameActionScreen(this, "exchange.tmx");

		turns = new Stack<>();

		for (Player player : players) {
			turns.push(player);
		}
		setScreen(gameActionScreen);
		gameStarted = true;
	}

	public void initiateMultiplayer() {
		multiplayerSetupScreen = new MultiplayerSetupScreen(this);
		setScreen(multiplayerSetupScreen);
		multiplayer = true;
	}

    public void connectToHost(String serverIp, String name) {
        roboClient = new RoboRallyClient(this, serverIp, name);
        playersScreen = new PlayersScreen(this);
        setScreen(playersScreen);
    }

    public void startHost(String nickname) {
        roboServer = new RoboRallyServer(this);
        roboServer.startServer(nickname);
    }

    public void playerAction(List<ProgramCard> cards, Player player) {
		player.receiveChosenProgramCards(cards);

		turns.pop();
		turns.push(player);
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
			for (Player player : players) {
				if (!player.hasChosenProgramCards()) {
					List<ProgramCard> remainingCardsToPickFrom = player.getReceivedProgramCards();
					List<ProgramCard> alreadyPicked = player.getChosenProgramCards();
					for (ProgramCard p : alreadyPicked) remainingCardsToPickFrom.remove(p);
					while (alreadyPicked.size() < 5) alreadyPicked.add(remainingCardsToPickFrom.remove(0));
					player.receiveChosenProgramCards(alreadyPicked);
				}
				if (multiplayer) {
					roboClient.sendPlayerAction(player, player.getChosenProgramCards());
				}
			}
			currentActivity = new Activity(Definitions.ActivityType.COMPLETE_REGISTERS, PROGRAMCARD_DURATION);
		}
	}

	private void pickBoard() {
		if (currentActivity.hasTimedOut()) {
			currentActivity = new Activity(Definitions.ActivityType.DEAL_CARDS, STANDARD_DURATION);
		}
	}

	private void waitForConnections() {
		if (currentActivity.hasTimedOut()) {
			currentActivity = new Activity(Definitions.ActivityType.PICK_BOARD, STANDARD_DURATION);
		}
	}

	private void checkMultiplayer() {
		if (gameStarted) {
			if (multiplayer) currentActivity = new Activity(Definitions.ActivityType.WAIT_FOR_CONNECTIONS, -1);
			else currentActivity = new Activity(Definitions.ActivityType.PICK_BOARD, STANDARD_DURATION);
		}
	}

	private void waitForMenuSelection() {
		if (gameStarted) currentActivity = new Activity(Definitions.ActivityType.CHECK_MULTIPLAYER, -1);
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
			if (checkWinner()) announceWinner();
			phaseNumber++;
		} else {
			// Reset everything for the next set of phases (probably wrong term)
			for (Player player : players) player.resetProgramCards();
			gameActionScreen.resetCardPositions();
			phaseNumber = 0;
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
			while (System.currentTimeMillis() < lastMoveTimestamp + WAIT_BETWEEN_MOVES) {
				// spin waiter
			}
			lastMoveTimestamp = System.currentTimeMillis();
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
		programDeck.createDeck();
		for (Player player : players) {
			cards = new ArrayList<>(); // Create a small deck of cards for each player
			cards.addAll(programDeck.draw(MAX_NUMBER_OF_CARDS - player.getDamage()));
			player.receiveProgramCardsToPick(cards); // Each player receives it's cards
			System.out.print(player.getPlayerName() + " receives ");
			for (ProgramCard c : cards) System.out.print(c.getProgramCardType() + " ");
			System.out.println();
		}
	}
}

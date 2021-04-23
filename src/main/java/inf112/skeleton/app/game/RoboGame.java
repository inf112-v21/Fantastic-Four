package inf112.skeleton.app.game;

import inf112.skeleton.app.assets.Definitions;
import inf112.skeleton.app.assets.Definitions.ActivityType;
import inf112.skeleton.app.assets.Player;
import inf112.skeleton.app.assets.cards.ProgramCard;
import inf112.skeleton.app.assets.cards.ProgramDeck;
import inf112.skeleton.app.screens.GameActionScreen;
import inf112.skeleton.app.screens.MainMenuScreen;
import inf112.skeleton.app.screens.MultiplayerSetupScreen;
import inf112.skeleton.app.screens.PlayersScreen;
import inf112.skeleton.app.server.RoboRallyClient;
import inf112.skeleton.app.server.RoboRallyServer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicBoolean;

public class RoboGame extends com.badlogic.gdx.Game {

    private final ProgramDeck programDeck;
    final int MAX_NUMBER_OF_CARDS = 9;

	public RoboRallyClient roboClient;
	public RoboRallyServer roboServer;

    // Server metadata
    public Player host;
    public Player localPlayer;
    public List<Player> players;
    public Stack<Player> turns;
    public AtomicBoolean multiplayerReadyToStartGame;

    Activity currentActivity;
    ActivityType lastActivityType;

	boolean gameStarted;
	boolean multiplayer;

	long lastMoveTimestamp;
	long WAIT_BETWEEN_MOVES = 500L; // milliseconds

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

	private final long NUMBER_OF_PHASES;
	int phaseNumber;
	int numPlayersCompletedPickingCards;
	private boolean sentCards;

	public RoboGame() {
		programDeck = new ProgramDeck();
		programDeck.createDeck();
		players = new LinkedList<>();
		currentActivity = new Activity(Definitions.ActivityType.OPEN_MENU, -1);
		lastActivityType = Definitions.ActivityType.HALT;
		multiplayer = false; // Will be changed if the server starts
		gameStarted = false;
		PROGRAMCARD_DURATION = 1;
		STANDARD_DURATION = 1;
		NUMBER_OF_PHASES = 5;
		phaseNumber = 0;
		numPlayersCompletedPickingCards = 0;
		sentCards = false;
		lastMoveTimestamp = 0L;
		multiplayerReadyToStartGame = new AtomicBoolean(false);
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
		currentActivity = new Activity(Definitions.ActivityType.WAIT_FOR_CONNECTIONS, -1);
		multiplayerSetupScreen = new MultiplayerSetupScreen(this);
		setScreen(multiplayerSetupScreen);
		multiplayer = true;
	}

    public void connectToHost(String serverIp, String name) {
        roboClient = new RoboRallyClient(this, serverIp, name, multiplayerReadyToStartGame);
        playersScreen = new PlayersScreen(this);
        setScreen(playersScreen);
    }

    public void startHost(String nickname) {
        roboServer = new RoboRallyServer(this);
        roboServer.startServer(nickname);
    }

    public void playerAction(List<ProgramCard> cards, Player player) {
		System.out.println("playerAction for " + player.getPlayerName() + " (id " + player.id + ") " + cards);

		for (Player p : players) {
			if (p.id == player.id) {
				System.out.println("player id " + p.id + " received " + cards);
				p.receiveChosenProgramCards(cards);
				numPlayersCompletedPickingCards++;
			}
		}
		if (numPlayersCompletedPickingCards == players.size()) {
			currentActivity = new Activity(Definitions.ActivityType.COMPLETE_REGISTERS, PROGRAMCARD_DURATION);
			numPlayersCompletedPickingCards = 0;
		}
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
		if (multiplayer) {
			if (!sentCards && localPlayer.getChosenProgramCards().size() == 5) {
				roboClient.sendPlayerAction(localPlayer, localPlayer.getChosenProgramCards());
				sentCards = true;
			}
			if (numPlayersCompletedPickingCards == players.size()) {
				currentActivity = new Activity(Definitions.ActivityType.COMPLETE_REGISTERS, PROGRAMCARD_DURATION);
			}
		}
		else if (currentActivity.hasTimedOut()) {
			gameActionScreen.clearCards();
			gameActionScreen.resetButtons();
			for (Player player : players) {
				if (!player.hasChosenProgramCards()) {
					List<ProgramCard> remainingCardsToPickFrom = new ArrayList<>(player.getReceivedProgramCards());
					List<ProgramCard> alreadyPicked = new ArrayList<>(player.getChosenProgramCards());
					for (ProgramCard p : alreadyPicked) remainingCardsToPickFrom.remove(p);
					int i = 0;
					while (alreadyPicked.size() < 5) {
						alreadyPicked.add(remainingCardsToPickFrom.get(i));
						i++;
					}
					player.receiveChosenProgramCards(alreadyPicked);
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
		if (multiplayerReadyToStartGame.get()) {
			launchGame();
			multiplayerReadyToStartGame.set(false);
			currentActivity = new Activity(Definitions.ActivityType.PICK_BOARD, STANDARD_DURATION);
		}
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
		sentCards = false;
		if (phaseNumber < NUMBER_OF_PHASES) {
			robotsMove();
			if (checkWinner()) announceWinner();
			phaseNumber++;
		} else {
			// Reset everything for the next set of phases (probably wrong term)
			for (Player player : players) player.resetProgramCards();
			localPlayer.resetProgramCards();
			gameActionScreen.resetCardPositions();
			gameActionScreen.clearCards();
			phaseNumber = 0;
			currentActivity = new Activity(ActivityType.DEAL_CARDS, STANDARD_DURATION);
		}
	}

	private void announceWinner() {
		System.out.println("Winner!");
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

	public boolean checkWinner() {
		return false;
	}

	/**
	 * Deal the program cards
	 */
	public void dealProgramCards() {
		// todo if multiplayer: if host: deal. else:receveive. else deal
		programDeck.createDeck();
		if (multiplayer) {
			cards = new ArrayList<>(programDeck.draw(MAX_NUMBER_OF_CARDS - localPlayer.getDamage())); // Create a small deck of cards for each player
			localPlayer.receiveProgramCardsToPick(cards); // Each player receives it's cards
			System.out.print(localPlayer.getPlayerName() + " receives ");
			for (ProgramCard c : cards) System.out.print(c.getProgramCardType() + " ");
			System.out.println();
		}
		else {
			for (Player player : players) {
				cards = new ArrayList<>(programDeck.draw(MAX_NUMBER_OF_CARDS - player.getDamage())); // Create a small deck of cards for each player
				player.receiveProgramCardsToPick(cards); // Each player receives it's cards
				System.out.print(player.getPlayerName() + " receives ");
				for (ProgramCard c : cards) System.out.print(c.getProgramCardType() + " ");
				System.out.println();
			}
		}
	}
}

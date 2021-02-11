# Mandatory Assignment 1

## Subproblem 1

### Organisering of team
- Team leader and customer contact - Morten
- Test responsible - Daniel
- UI design lead - Edyta
- Mechanics/tech lead - Mathias

## Subproblem 2
- The team has agreed on a Kanban approach.
- Meetings and frequency: Monday at 14:00 and Thursday at 12:00.
- Communication between meetings: Ad hoc when needed, mainly through Discord.
- Work distribution: Mathias makes project structure and creates Clubhouse project board. We all need to read the
  RoboRally Rules, so that we have a joint understanding of how the game works and what methods need to be implemented.
- Follow-up of work: will be addressed each meeting.
- Sharing and storing documents: The team shares/stores documents in the project repository on GitHub. Java-classes
  has one owner, and a team member has administrative rights only for their own classes, if not explicitly stated
  otherwise.

## Subproblem 3

### Shot description of the overall motivation for the application
The application will allow one or more players to play a digital version of the board game RoboRally.
It must be possible to play single player (against AI) and multiplayer (over the internet). Players will be guided by
the application to perform legal moves without the player having to know all the rule details. The board, pieces, 
and cards etc, should be displayed for the player by the application.
The application will do quite a lot of operations without help of the users, including moving the players' pieces,
inflicting damage, keeping track of the move priority order, as well as shuffling and dealing out cards for the players.

### User stories
For this assignment, only the user stories that are relevant for the current hand-in are included. Other user stories
for future parts of the RoboRally project are described briefly in "all_user_stories.md".

### List of prioritized user stories for first iteration
Below are the selected user stories that the team chose to focus on for the first iteration of Mandatory Assignment 1.

Show a game board (MVP 1)
- As a player I wish to see a game board after I started single player game mode to be able to plan my strategy.

Show a piece on the game board (MVP 2)
- As a player I wish to see my robot on the game board to be able to make my decisions.

Move piece on game board (MVP 3)
- As a tester I wish that my robot will move on the game board, so that the move mechanism can be verified.

Robot visits flag (MVP 4)
- As a tester I wish that the robot can visit a flag to verify that a robot can occupy a cell with a flag in it.

Robot wins by visiting flag (MVP 5)
- As a tester I wish to win by visiting a flag to stop the game.

### Prioritized user stories, acceptance criteria solutions
Prioritized user stories with acceptance criteria based on the scenario-oriented format (given, when, then), and
solutions to them.

####Show a game board (MVP 1)
- User story
  - As a player I wish to see a game board after I started singleplayer game mode to be able to play the game.
- Acceptance criteria
  - Given a running game, when a player clicks on a single player button from main menu, then the game board is visible
    on the screen.
- Solution
  - tba 

####Show a piece on the game board (MVP 2)
- User story
  - As a player I wish to see my robot on the game board to be able to make my decisions.
- Acceptance criteria
  - Given a running instance of RoboRally, when a player starts a game, then the player's robot is visible on the
    game board.
  - Given a running game, when a player starts a game, then the player's robot is visible on the game board on the
    position (0,0) (see AC for MVP 3 below for definition of the game board coordinate system).
- Solution
  - tba

####Move piece on game board (MVP 3)
- User story
  - As a player I wish that my robot will move on the game board, so that the move mechanism can be verified.
- Acceptance criteria
  - Given a running game instance and a specified coordinate system where lower left corner = (0,0),
    upper left corner = (0,y), upper right corner = (x,y), and lower right corner = (x,0);
    - when a robot is located at position (1,1) of the game board and the
      up arrow key is pressed once, then the robot moves to position (1,2).
    - when a robot is located at position (1,1) of the game board and the
      down arrow key is pressed once, then the robot moves to position (1,0).
    - when a robot is located at position (1,1) of the game board and the
      left arrow key is pressed once, then the robot moves to position (0,1).
    - when a robot is located at position (1,1) of the game board and the
      right arrow key is pressed once, then the robot moves to position (2,1). 
- Solution
  - Use InputAdapter to...

####Robot visits flag (MVP 4)
- User story
  - As a tester I wish that the robot can visit a flag to verify that a robot can occupy a cell with a flag in it.
- Acceptance criteria
  - Given a running game instance, when a player tries to move their robot (e.g. with arrow keys) into a cell that is
    occupied by a flag, then the robot should be allowed this, and be placed on top of the flag.    
- Solution
  - tba

####Robot wins by visiting flag (MVP 5)
- User story
  - As a tester I wish to win by visiting a flag to stop the game.
- Acceptance criteria
  - Given a running game instance, with a player having collected n-1 flags (where n is the total number of flags),
    when that player's robot lands on the last flag, then that player is announced the winner and the game stops.
- Solution
  - 

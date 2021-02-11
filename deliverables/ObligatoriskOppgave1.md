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

### User stories based on the MVP-requirements
Below follow user stories that were designed in terms of the MVP-requirements:
- as a player I wish to see a game board to be able to play 
- as a player I wish to see my robot on the board to be able to make my decisions
- as a player I wish that my piece/robot will move on the game board,
- as a tester I wish to move a piece/robot by using keyboard keys to test the robot movement
- as a player I wish that the robot can visit a flag to drive the game forward.
- as a player I wish to win by visiting a flag to finish the game.
- as a player I wish to play multiplayer to increase the joy of playing.
- as a player I wish to be able to start a multiplayer game to invite friends.
- as a player I wish that cards are dealt so that I can play.
- as a player I wish to choose 5 cards of those I am dealt, so that the game can proceed.
- as a player I wish that my chosen cards will move my robot so that I can compete in the game.

### User stories related to the game menu
These are the current user stories that were created associated with the game menu appearance:
- as a player I wish that when I start the game, I will see a game menu right away, so that I can decide which
  game I would like to play.
- as a player I wish that when I start the game, a game instruction will appear, so that I can learn the game.
- as a player I wish that when I start the game, I will have access to game settings right away, so that I 
  can adjust sound, image, appearance, language, etc.
- as a player I wish that when I win/lose the game, I will be notified immediately, so that I can decide if I want to
  continue or stop playing.

### User stories related to the Program Sheet

- as a player I need a Program Sheet so that I have an overview of the game status (ice berg?).
- as a player I need to see the cards infront of me, so that I know which moves my robot will carry out.
- as a player I need to see how many Damage tokens my robot has received, so that I can make the right strategic
  choices.
- as a player I need to see how many Life tokens (remaining lives) I have left, so that I don't make the wrong
  strategic choices.
- as a player I need a visual confirmation whether I have an active Power Down status for the next turn.
- as a player I need an overview of which Option cards I may have in my inventory, so that I am able to use one if
  needed.
- as a player I also need to see other players' Program Sheets (as they would appear in an "over the table" game
  situation), so that I can base my choices on other players' game status.
- as a player I need to know how much time is left when organizing my Program cards, so that the cards are not
  randomly placed in the registers.

### List of prioritized user stories for first iteration
Below are the selected user stories that the team chose to focus on for the first iteration of Mandatory Assignment 1
-(En prioritert liste over hvilke brukerhistorier dere vil ha med i første iterasjon
(altså frem til levering av denne oppgaven, se Subproblem 4 for forslag))


### Acceptance criterias for user stories and work tasks for user stories
Acceptance criterias for the prioritized user stories are created based on the scenario-oriented format
(given, when, then):
- as a player I wish that when I start the game, I will see a game menu right away, so that I can decide which
  game I would like to play.
  - given a runnable RoboRally application,
    when the user starts the application,
    then the initial menu is visible
- as a tester I wish to move a piece/robot by using keyboard keys to test the robot movement
  - given a visual game board,
    when using the arrow keys on the keyboard,
    then the robot responds as expected
  - given a robot at (1,1) (surrounded by vacant cells),
    when the user presses one of the arrow or WASD keys,
    then the robot moves 1 step in the corresponding direction.
- as a player I wish that the robot can visit a flag to drive the game forward.
  - given a robot at a location next to a flag,
    when the robot moves into the same cell as the flag,
    then an EventHandler fires.

### AC EXAMPLES TO BE REVIEWED
Morten

#### MVP 1:
- as a player I wish to see a game board to be able to play
  - Given a running game instance,
    when the game loop goes in to the next iteration,
    then the board must still be visible
    AND the board must be updated according to any changes
    __(iceberg..)__
    
#### MVP 2:
- as a player I wish to see my robot on the board to be able to make my decisions
  - Given a running instance of RoboRally,
  when a player starts a game,
    then that player's robot is visible on the game board
    
#### MVP 3:
- as a player(tester) I wish that my piece/robot will move on the game board,
  - Given a running game instance,
    when a call is made to make a move of a robot,
    then a check is performed to verify that the move is valid
  - Given a running game instance, 
    when a call is made to make a valid move of a robot,
    then the robot should be removed from the old location
    AND rendered at the new location
    
#### MVP 4:
- as a player I wish that the robot can visit a flag to drive the game forward.
  - Given a running game instance,
  with a player having collected <(n-1) flags (where n is the total number of flags),
    when that player's robot lands on a flag,
    then that player's boolean value for that flag is set to true
    
#### MVP 5:
- as a player I wish to win by visiting a flag to finish the game.
  - Given a running game instance,
    with a player having collected n-1 flags(where n is the total number of flags), 
    when that player's robot lands on the last flag,
    then that player is the winner 
    AND the game stops.

Edyta
#### MVP 1:
- as a player I wish to see a game board after I started single player game mode to be able to plan my strategy
  - Given a running game, when a player clicks on a single player button from main menu, then the game board is visible on the screen

#### MVP 2:
- as a player I wish to see my robot on the board to be able to make my decisions
  - Given a running game, when a player starts a game, then the player's robot is visible on the game board on the position (0,0)
  
#### MVP 3:
- as a player I wish that my piece/robot will move on the game board,
  - Given a running game, when the player who is located at the position (1,1) on the game board press the button Upwards Arrow one time, then the players move to     the position (0,2)
  - Given a running game, when the player who is located at the position (0,0) on the game board press the button Downwards Arrow one time, then the players move     to the position (0,0)
  - Given a running game, when the player who is located at the position (0,0) on the game board press the button Leftwards Arrow one time, then the players move     to the position (1,0)
  - Given a running game, when the player who is located at the position (0,0) on the game board press the button Rightwards Arrow one time, then the players move     to the position (0,1)
   
"how is it with move out of the board?"
#### MVP 4:

- as a player I wish that the robot can visit a flag to drive the game forward.
  - Given a running game, when a player starts a game, and has the same position as flag located on the game board (x,y) == (x,y), then visited flag_collected status increased by +1.

#### MVP 5:
- as a player I wish to win by visiting a flag to finish the game. 
  - Given a running game, when a player starts a game, and has flag_collected=number_of_flag  then, the player is informed about win and the game stops. 


Mathias


Daniel



## Subproblem 4
- (Kode)
(Vi forventer at dere skriver brukerhistorier (husk å få med hvilke krav brukerhistoriene dekker i beskrivelse) og
kode for punkt 1 til 5 ved denne innleveringen)
 

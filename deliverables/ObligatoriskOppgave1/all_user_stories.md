## User stories, acceptance criterias, and solutions to them
This is I file for collecting all brainstormed user stories with associated acceptance criteria and solutions.

### User stories based on the MVP-requirements
Below follow user stories that were designed in terms of the MVP-requirements:
- as a player I wish to see a roboGame board to be able to play 
- as a player I wish to see my robot on the board to be able to make my decisions
- as a player I wish that my piece/robot will move on the roboGame board,
- as a tester I wish to move a piece/robot by using keyboard keys to test the robot movement
- as a player I wish that the robot can visit a flag to drive the roboGame forward.
- as a player I wish to win by visiting a flag to finish the roboGame.
- as a player I wish to play multiplayer to increase the joy of playing.
- as a player I wish to be able to start a multiplayer roboGame to invite friends.
- as a player I wish that cards are dealt so that I can play.
- as a player I wish to choose 5 cards of those I am dealt, so that the roboGame can proceed.
- as a player I wish that my chosen cards will move my robot so that I can compete in the roboGame.

### User stories related to the roboGame menu
These are the current user stories that were created associated with the roboGame menu appearance:
- as a player I wish that when I start the roboGame, I will see a roboGame menu right away, so that I can decide which
  roboGame I would like to play.
- as a player I wish that when I start the roboGame, a roboGame instruction will appear, so that I can learn the roboGame.
- as a player I wish that when I start the roboGame, I will have access to roboGame settings right away, so that I 
  can adjust sound, image, appearance, language, etc.
- as a player I wish that when I win/lose the roboGame, I will be notified immediately, so that I can decide if I want to
  continue or stop playing.

### User stories related to the Program Sheet

- as a player I need a Program Sheet so that I have an overview of the roboGame status (ice berg?).
- as a player I need to see the cards in front of me, so that I know which moves my robot will carry out.
- as a player I need to see how many Damage tokens my robot has received, so that I can make the right strategic
  choices.
- as a player I need to see how many Life tokens (remaining lives) I have left, so that I don't make the wrong
  strategic choices.
- as a player I need a visual confirmation whether I have an active Power Down status for the next turn.
- as a player I need an overview of which Option cards I may have in my inventory, so that I am able to use one if
  needed.
- as a player I also need to see other players' Program Sheets (as they would appear in an "over the table" roboGame
  situation), so that I can base my choices on other players' roboGame status.
- as a player I need to know how much time is left when organizing my Program cards, so that the cards are not
  randomly placed in the registers.

### List of prioritized user stories for first iteration
Below are the selected user stories that the team chose to focus on for the first iteration of Mandatory Assignment 1
- MVP 1: As a player I wish to see a roboGame board to be able to play.
- MVP 2: As a player I wish to see my robot on the roboGame board to be able to make my decisions.
- MVP 3: As a tester I wish that my robot will move on the roboGame board, so that the move mechanism can be verified.
- MVP 4: As a tester I wish that the robot can visit a flag to verify that a robot can occupy a cell with a flag in it.
- MVP 5: As a tester I wish to win by visiting a flag to stop the roboGame.


### Attempt to formulate acceptance criteria for some of the user stories

- as a player I wish that when I start the roboGame, I will see a roboGame menu right away, so that I can decide which
  roboGame I would like to play.
  - given a runnable RoboRally application,
    when the user starts the application,
    then the initial menu is visible
- as a tester I wish to move a piece/robot by using keyboard keys to test the robot movement
  - given a visual roboGame board,
    when using the arrow keys on the keyboard,
    then the robot responds as expected
  - given a robot at (1,1) (surrounded by vacant cells),
    when the user presses one of the arrow or WASD keys,
    then the robot moves 1 step in the corresponding direction.
- as a player I wish that the robot can visit a flag to drive the roboGame forward.
  - given a robot at a location next to a flag,
    when the robot moves into the same cell as the flag,
    then an EventHandler fires.


#### MVP 1:
- as a player I wish to see a roboGame board to be able to play
  - Given a running roboGame instance,
    when the roboGame loop goes in to the next iteration,
    then the board must still be visible
    AND the board must be updated according to any changes
    __(iceberg..)__
    
#### MVP 1:
- as a player I wish to see a roboGame board after I started single player roboGame mode to be able to plan my strategy
  - Given a running roboGame, when a player clicks on a single player button from main menu, then the roboGame board is visible on the screen    


#### MVP 2:
- as a player I wish to see my robot on the board to be able to make my decisions
  - Given a running instance of RoboRally,
  when a player starts a roboGame,
    then that player's robot is visible on the roboGame board
#### MVP 2:
- as a player I wish to see my robot on the board to be able to make my decisions
  - Given a running roboGame, when a player starts a roboGame, then the player's robot is visible on the roboGame board on the position (0,0)



#### MVP 3:
- as a player(tester) I wish that my piece/robot will move on the roboGame board,
  - Given a running roboGame instance,
    when a call is made to make a move of a robot,
    then a check is performed to verify that the move is valid
  - Given a running roboGame instance, 
    when a call is made to make a valid move of a robot,
    then the robot should be removed from the old location
    AND rendered at the new location
#### MVP 3 (this one)
- as a player I wish that my piece/robot will move on the roboGame board, so that the move mechanism can be verified
  - Given a running roboGame, when the player who is located at the position (1,1) on the roboGame board press the button Upwards Arrow one time, then the players move to     the position (0,2)
  - Given a running roboGame, when the player who is located at the position (0,0) on the roboGame board press the button Downwards Arrow one time, then the players move     to the position (0,0)
  - Given a running roboGame, when the player who is located at the position (0,0) on the roboGame board press the button Leftwards Arrow one time, then the players move     to the position (1,0)
  - Given a running roboGame, when the player who is located at the position (0,0) on the roboGame board press the button Rightwards Arrow one time, then the players move     to the position (0,1)
"how is it with move out of the board?"
 How to solve:
    - use InputAdapter to 



#### MVP 4:
- as a player I wish that the robot can visit a flag to drive the roboGame forward.
  - Given a running roboGame instance,
  with a player having collected <(n-1) flags (where n is the total number of flags),
    when that player's robot lands on a flag,
    then that player's boolean value for that flag is set to true
#### MVP 4:

- as a player I wish that the robot can visit a flag to drive the roboGame forward.
  - Given a running roboGame, when a player starts a roboGame, and has the same position as flag located on the roboGame board (x,y) == (x,y), then visited flag_collected status increased by +1.
    

#### MVP 5:
- as a player I wish to win by visiting a flag to finish the roboGame.
  - Given a running roboGame instance,
    with a player having collected n-1 flags(where n is the total number of flags), 
    when that player's robot lands on the last flag,
    then that player is the winner 
    AND the roboGame stops.
#### MVP 5:
- as a player I wish to win by visiting a flag to finish the roboGame.
  - Given a running roboGame, when a player starts a roboGame, and has flag_collected=number_of_flag  then, the player is informed about win and the roboGame stops.


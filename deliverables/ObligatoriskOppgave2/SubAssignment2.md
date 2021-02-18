## Subassignment 2 - Requirements
### MVP 6: Spille fra flere maskiner (vise brikker for alle spillere, flytte brikker for alle spillere)
#### Assumptions:
- One player can create an online game and that player's computer will act as the server, to which other players' computers can connect.
- The moves will be calculated locally at all instances of RoboRally (the server will not pass out the results, instead the server will provide all variables to the clients)

#### User stories:
- As a player I would like to connect to other RoboRally game instances via the Internet to be able to play with other people
- As a player I would like to play online to play with my friends
- As a player I would like to play RoboRally online to be able to play with real people

#### Acceptance criteria:
- Given a locally running RoboRally instance
with one other RoboRally instance connected via the Internet
when a robot is moved at the _local_ RoboRally instance
then the robot is moved accordingly at the _remote_ RoboRally instance

- Given a locally running RoboRally instance
with one other RoboRally instance connected via the Internet
when a robot is moved at the _remote_ RoboRally instance
then the robot is moved accordingly at the _local_ RoboRally instance

- Given a running RoboRally instance,
when the user sets up an online game via the menu
then that user's RoboRally instance accepts remote connections from other RoboRally instances

- Given a running RoboRally instance,
when the user inputs a remote IP to connect to,
AND that IP is used by a computer with a running RoboRally instance that accepts remote connections,
then the local RoboRally instance can connect to the remote RoboRally instance.

#### Work tasks:
- Decide on a suitable library for online connections
- Implement that library to
- implement a `Server` or `Master` class (for the instance acting as the server)
- implement a `Client` or `Slave` class (for the instances connecting to the server)
- Pass game "facts" (like cards available, pieces picked, locations etc) from the server's `Game` to the clients' `Game` via the `Server`/`Client`classes
- Write tests for all functions

### MVP 7: Dele ut kort
#### Assumptions:

#### User stories:
- As a player, when it is my turn, I want to draw as many cards as the rules allow, to be able to pick as many cards as the rules allow
- As a player, I want to draw a deck of cards to be able to select the cards I want to use
- As a player, I want the game to hand out cards, so that I get a hand of cards to pick moves from

#### Acceptance criteria:
- Given a running game in the correct state, when the deal method is called, then all players receive the correct amount of cards
- Given a `Deck` object containing `Card`s, when the deal method is called, then each `Player` receives an individual `Deck` of `Card`s from the original `Deck`
- Given a `Deck` of `Card`s, when a `Card` is dealt from the `Deck`, then that `Card` is removed from the `Deck`

#### Work tasks:
- Decide on the representation of cards and decks
- Implement `Card`
- Implement `Deck`
- Write tests

### MVP 8: Velge 5 kort
#### Assumptions:
- The correct amount of `Card`s are dealt (9)

#### User stories:
- As a player, I want to select 5 cards from the 9 cards I am dealt to be able to create a set of moves
- As a player, I want the card I select from the 9 cards to be unavailable when I select the next card, to avoid breaking the rules

#### Acceptance criteria:
- Given a player that has received 9 cards,
    when the player picks 1 of the 9 cards,
    then only 8 cards are available for picking next
- Given a player has has received `n` cards,
    when the player picks 1 of the `n` cards,
    then only `n-1` cards are available for picking next
- Given a player that has received `n` cards
    and picked 4 cards,
    when the players picks card number 5,
    then the player cannot pick any more cards
- Given a player that has received `n` cards
    and selected `0 < m <= 5` cards,
    when the player unselects 1 card, 
    then that card is available for selecting
    and that card is removed from the selcted pile
  
#### Work tasks:
- Implement a `Deck` for each `Player`
- Allow the player to select `m` cards from a dealt `Deck` of `n` cards
- Allow the player to unselect a card, returning it to the dealt `Deck`
- Write tests

### MVP 9: Bevege robot ut fra valgte kort
#### Assumptions:

#### User stories:
- As a user, I want my robot to move according to my selected cards, to abide by the game rules
- As a user, I want all robots to move according to their selected cards, to ensure game progress

#### Acceptance criteria:
- Given a turn of the game, 
  when it is the turn of the first `Player`'s robot,
  then that robot moves according to that `Player`'s `Card` corresponding to that turn
- Given a turn of the game,
    when it is the turn of a `Player`'s robot
    and the next `Card` in that `Player`'s program register is `Move n` (`1 <= n <= 3`)
  then the `Player`'s robot moves `n` step in the direction that the robot is facing
- Given a turn of the game,
  when it is the turn of a `Player`'s robot
  and the next `Card` in that `Player`'s program register is `Rotate left`
  then the `Player`'s robot rotates 90° counter clockwise from the direction that the robot is facing
- Given a turn of the game,
  when it is the turn of a `Player`'s robot
  and the next `Card` in that `Player`'s program register is `Rotate right`
  then the `Player`'s robot rotates 90° clockwise from the direction that the robot is facing
- Given a turn of the game,
  when it is the turn of a `Player`'s robot
  and the next `Card` in that `Player`'s program register is `U-turn`
  then the `Player`'s robot rotates 180° clockwise from the direction that the robot is facing
- Given a turn of the game,
  when it is the turn of a `Player`'s robot
  and the next `Card` in that `Player`'s program register is `Back up`
  then the `Player`'s robot is moved 1 step in the opposite direction that the robot is facing

#### Work tasks:
- Implement a game loop with rounds and turns
- Write tests
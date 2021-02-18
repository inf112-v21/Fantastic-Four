## Subassignment 2 - Requirements
### MVP 6: Spille fra flere maskiner (vise brikker for alle spillere, flytte brikker for alle spillere)
#### Assumptions:
- One player can create an online game and that player's computer will act as the server, to which other players' computers can connect.
- The moves will be calculated locally at all instances of RoboRally (the server will not pass out the results, instead the server will provide all variables to the clients)

#### User stories:
As a player I would like to connect to other RoboRally game instances via the Internet to be able to play with other people
As a player I would like to play online to play with my friends
As a player I would like to play RoboRally online to be able to own noobs

#### Acceptance criteria:
Given a locally running RoboRally instance
with one other RoboRally instance connected via the Internet
when a robot is moved at the _local_ RoboRally instance
then the robot is moved accordingly at the _remote_ RoboRally instance

Given a locally running RoboRally instance
with one other RoboRally instance connected via the Internet
when a robot is moved at the _remote_ RoboRally instance
then the robot is moved accordingly at the _local_ RoboRally instance

Given a running RoboRally instance,
when the user sets up an online game via the menu
then that user's RoboRally instance accepts remote connections from other RoboRally instances

Given a running RoboRally instance,
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
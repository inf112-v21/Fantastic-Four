## Subassignment 2 - Requirements
### MVP 6: Spille fra flere maskiner (vise brikker for alle spillere, flytte brikker for alle spillere)

Assumption 1: One player can create an online game and that player's computer will act as the server, to which other players can connect.
Assumption 2: The moves will be calculated locally at all instances of RoboRally

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

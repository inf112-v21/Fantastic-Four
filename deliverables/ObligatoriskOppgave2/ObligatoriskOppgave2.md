# Mandatory Assignment 2

Summary: We had a plan to implement MVP6 to MVP9, but especially the multiplayer part of the MVPs proved to be
more difficult than expected. Also, some of the back-end logic were difficult to incorporate into Oblig 1's code
infrastructure. This in mind (together with a closing due date) we were forced to shift our focus with regards to the
MVPs.

## Subproblem 1: Project and project structure
- The division of roles works okay, and we don't feel the need for updating these at the moment. 
- We don't feel that we need other roles, however sometimes the work that ends up in team member's hands are not
specific to that team member's role.
- What the roles mean to us:
  - Team lead: in charge of meetings, delegating mom writing, and facilitating the discussion
  - Customer contact: everybody talks to Sindre (main customer), so a specific person in this role is not strictly
    necessary.
  - Code responsible: person that is in charge of layout/structure of code, in case there are disagreements. However,
  we all contribute.
  - Test responsible: as above, however, testing has not been a high priority this assignment.
    UI design lead: in practice, this is a person that is responsible for the design of GUI, but not necessarily
    implements it all by themselves. In our case, Edyta has in fact done most of this work.
  - Mechanics/tech lead: a person that is knowledgeable about most of the construction of code. Mathias has done a
    great job in finding the right resources for running e.g. the multiplayer part.
- Our experience is that work load has been difficult to balance or distribute among all team members. Some have worked
more in the beginning and some more at the end toward the deadline. The choices that we have made have been okay, but
  some of the choices made to reach MVPs for Oblig 1 have been counter productive reaching the MVPs of Oblig 2 (e.g.
  moving a player using arrow keys in Oblig 1 vs. using program cards in Oblig 2).
- Communication is okay, but sometimes we experience that parts of the communication are lost in translation.
- Retrospect: 
  - We have sometimes waited or expected other pieces of code to be finished so that our own code could be implemented
  and work, however, hiccups along the way for each of us, has lead to difficulties in bringing together a final
    product.
  - In other words, we feel that we have worked well on each of our tasks, but we have not been focused enough on
  joining the separate pieces of code, so that when the delivery date was getting closer, we found out that we couldn't
  manage to make our original MVP goals
  - So for next time we plan from the start to have a common view and understanding of how the different parts of the
  project and codes will be combined into a working RoboRally game, this will be explicitly communicated early in next
  project leg
  - Also, a concrete solution to making this flow better, the team plans to meet physically (if possible) in a meeting
  room at campus. We expect the productivity and understanding will increase drastically by doing so.
- The number of commits to the code base has been quite varying, but the number of code lines is much more balanced. 
So all in all, we are happy with the work distribution.

  
## Subproblem 2: Requirements
Below are all user stories, requirements and proposed solutions that we ended up with using (MVP6, MVP7, MVP8 and MVP9).
At first the team was very optimistic about finishing all four MVPs. But eventually, it was clear to us that MVP6 was
quite a bit harder to solve than expected, so in the end the focus shifted toward finishing MVP7 - MVP9. These too were
not trivial to complete in terms of GUI visualisation, so the team decided to implement the back end logic for them,
and showing the actions in the terminal (e.g. actions represented by the enums DEALCARDS; PICKCARDS,
EXECUTEPROGRAMCARDS1, EXECUTEPROGRAMCARDS2, etc.).

- MVP6: Implemented backend solution, not connected to the RoboGame class.
- MVP7: Dealing cards is implemented backend, cards are not shown in the GUI. A solution for showing the 9 cards is
  made, but not implemented to the game.
- MVP8: Picking 5 cards is semi implemented: If the player does not pick 5 cards in a set time, the game picks for them.
  The GUI implementation is the same as mentioned in MVP7, and is not implemented.
- MVP9: The backend logic is ready (without collision detection). It is not shown in the GUI, but for demonstration
  purposes activities and execution of ProgramCards are printed to the terminal.


Listed below are the user stories that we developed earlier in this leg of the project - those of which we want to
use when we later implement the proper GUI version of RoboRally.

### MVP 6: Play from several computers (show and move pieces for all players)
#### Assumptions:
- One player can create an online RoboGame and that player's computer will act as the server, to which other players' computers can connect.
- The moves will be calculated locally at all instances of RoboRally (the server will not pass out the results, instead the server will provide all variables to the clients)

#### User stories:
- As a player I would like to connect to other RoboRally roboGame instances via the Internet to be able to play with other people
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
  when the user sets up an online roboGame via the menu
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
- Pass roboGame "facts" (like cards available, pieces picked, locations etc) from the server's `Game` to the clients' `Game` via the `Server`/`Client`classes
- Write tests for all functions

### MVP 7: Deal cards to players
#### Assumptions:

#### User stories:
- As a player, when it is my turn, I want to draw as many cards as the rules allow, to be able to pick as many cards as the rules allow
- As a player, I want to draw a deck of cards to be able to select the cards I want to use
- As a player, I want to see what properties cards have when I can pick them up, to be able to plan my strategy
- As a player, I want the roboGame to hand out cards, so that I get a hand of cards to pick moves from

#### Acceptance criteria:
- Given a running roboGame in the correct state, when the deal method is called, then all players receive the correct amount of cards
- Given a `Deck` object containing `Card`s, when the deal method is called, then each `Player` receives an randomly selected `Deck` of `Card`s from the original `Deck`
- Given a `Deck` object containing `Card`s, when the deal method is called, then each `Player` can get obtain information about what type of cards are they, how high priority they have.
- Given a `Deck` of `Card`s, when a `Card` is dealt from the `Deck`, then that `Card` is removed from the `Deck`

#### Work tasks:
- Decide on the representation of cards and decks
- Decide on which properties `Card` will contain
- Implement `AbstractCard`
- According to the `AbstractCard` create subclasses to extend and implement/override the abstract methods.
- Implement `AbstractDeck`
- According to the `AbstractDeck` create subclasses to extend and implement/override the abstract methods.
- Write tests

### MVP 8: Each player to choose 5 cards
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

### MVP 9: Move robot by selected program cards
#### Assumptions:

#### User stories:
- As a user, I want my robot to move according to my selected cards, to abide by the roboGame rules
- As a user, I want all robots to move according to their selected cards, to ensure roboGame progress

#### Acceptance criteria:
- Given a turn of the roboGame,
  when it is the turn of the first `Player`'s robot,
  then that robot moves according to that `Player`'s `Card` corresponding to that turn
- Given a turn of the roboGame,
  when it is the turn of a `Player`'s robot
  and the next `Card` in that `Player`'s program register is `Move n` (`1 <= n <= 3`)
  then the `Player`'s robot moves `n` step in the direction that the robot is facing
- Given a turn of the roboGame,
  when it is the turn of a `Player`'s robot
  and the next `Card` in that `Player`'s program register is `Rotate left`
  then the `Player`'s robot rotates 90° counter clockwise from the direction that the robot is facing
- Given a turn of the roboGame,
  when it is the turn of a `Player`'s robot
  and the next `Card` in that `Player`'s program register is `Rotate right`
  then the `Player`'s robot rotates 90° clockwise from the direction that the robot is facing
- Given a turn of the roboGame,
  when it is the turn of a `Player`'s robot
  and the next `Card` in that `Player`'s program register is `U-turn`
  then the `Player`'s robot rotates 180° clockwise from the direction that the robot is facing
- Given a turn of the roboGame,
  when it is the turn of a `Player`'s robot
  and the next `Card` in that `Player`'s program register is `Back up`
  then the `Player`'s robot is moved 1 step in the opposite direction that the robot is facing

#### Work tasks:
- Implement a roboGame loop with rounds and turns
- Write tests


## Subproblem 3: Code

For instructions on how to build the project, see [README.md](../../README.md).



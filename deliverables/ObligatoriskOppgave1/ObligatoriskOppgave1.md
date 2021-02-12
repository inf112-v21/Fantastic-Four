# Mandatory Assignment 1

## Subproblem 1: Organizing the team

### Team roles
- Team Lead and Customer Contact - Daniel
- Code and Test Responsible - Morten
- UI Design Lead - Edyta
- Mechanics/Tech Lead - Mathias

### Member competance
Mathias:
- Web development
- Knows Java quite well
- Knows Github (branching etc.)
- Skilled in programming and algorithms

Edyta:
- INF102, INF101, INF100
- Master's degree in Earth Science
- Decent abilities in math

Morten:
- Good grades in all uni courses
- Knows most of what has been taught during his studies, few spare-time projects
- Bachelor's degree in Subsea Technology (worked the last 9-10 years as a senior engineer)
- Good at math (however, minimal practice last 10 years)

Daniel:
- Knows most of what has been taught so far, decent results
- Master's degree in Earth Science
- Worked 5-6 years in the hydrocarbon exploration industry (interpretation of seismic data and well data)
- Good at recognition/interpretation of patterns and rules



## Subproblem 2: Specify a process for the team

- The team has agreed on a Kanban approach.
- Meetings and frequency: Monday at 14:00 and Thursday at 12:00.
- Communication between meetings: Ad hoc when needed, mainly through Discord.
- Work distribution: Mathias makes project structure and creates Clubhouse project board. We all need to read the
  RoboRally Rules, so that we have a joint understanding of how the game works and what methods need to be implemented.
- Follow-up of work: will be addressed each meeting.
- Sharing and storing documents: The team shares/stores documents in the project repository on GitHub. Java-classes
  has one owner, and a team member has administrative rights only for their own classes, if not explicitly stated
  otherwise.

## Subproblem 3: Overview of expected product

### Short description of the overall motivation for the application
The application will allow one or more players to play a digital version of the board game RoboRally.
It must be possible to play single player (against AI) and multiplayer (over the internet). Players will be guided by
the application to perform legal moves without the player having to know all the rule details. The board, pieces, 
and cards etc, should be displayed for the player by the application.
The application will do quite a lot of operations without help of the users, including moving the players' pieces,
inflicting damage, keeping track of the move priority order, as well as shuffling and dealing out cards for the players.

### User stories
For this assignment, only the user stories that are relevant for the current hand-in are included. Other user stories
for future parts of the RoboRally project are described briefly in "all_user_stories.md".

### Prioritized user stories for first iteration
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

#### Show a game board (MVP 1)
- User story
  - As a player I wish to see a game board after I started singleplayer game mode to be able to play the game.
- Acceptance criteria
  - Given a running game, when a player clicks on a single player button from main menu, then the game board is visible
    on the screen.
- Solution
  - This step can be achieved using the code created by following the libGDX and Tiled tutorial posted on mitt.uib.

#### Show a piece on the game board (MVP 2)
- User story
  - As a player I wish to see my robot on the game board to be able to make my decisions.
- Acceptance criteria
  - Given a running instance of RoboRally, when a player starts a game, then the player's robot is visible on the
    game board.
  - Given a running game, when a player starts a game, then the player's robot is visible on the game board on the
    position (0,0) (see AC for MVP 3 below for definition of the game board coordinate system).
- Solution
  - This step can also be achieved via the libGDX and Tiled tutorial.

#### Move piece on game board (MVP 3)
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
  - Add event listeners (InputAdapter in libGDX) for key-up for the arrow and WASD keys.
  - When one of the above event listeners are triggered, call a common `move()` method with the desired change in 
  coordinates as the input.
    - The `move()` method needs to both change the coordinates to the new coordinates, and remove the robot from the old
  coordinates.

#### Robot visits flag (MVP 4)
- User story
  - As a tester I wish that the robot can visit a flag to verify that a robot can occupy a cell with a flag in it.
- Acceptance criteria
  - Given a running game instance, when a player tries to move their robot (e.g. with arrow keys) into a cell that is
    occupied by a flag, then the robot should be allowed this, and be placed on top of the flag.
  - Given a running game instance, when a player moves their robot to a cell that is occupied by a flag, then the game
    should react.
- Solution
  - Add flags to a separate layer on the board (using Tiled)
  - Add a check to `Map.render()` to see if the robot in question is on a `Cell` that is not `null` in the flag layer,
  meaning that there is a collision between a flag and a robot.

#### Robot wins by visiting flag (MVP 5)
- User story
  - As a tester I wish to win by visiting a flag to stop the game.
- Acceptance criteria
  - Given a running game instance, with a player having collected n-1 flags (where n is the total number of flags),
    when that player's robot lands on the last flag, then that player is announced the winner and the game stops.
- Solution
  - Temporary solution is to only have one flag and one robot, and the player wins when the robot reaches the flag.
  
## Subproblem 4: Code

The coding part of this delivery focuses on the five first MVP-requirements as listed in Subproblem 3 (Prioritized user
stories for first iteration) above. The team has utilised the LibGDX game-development application and the Tiled
tutorial to build the simple game boards and model the robot movements.

How to start the RoboRally application:
- Run Main from a terminal, command line window, or your favourite IDE.

## Summary: Retrospective view on the project performance

All in all a good team experience where all team members contributed well on their respective areas.
The team's impression is that most of this assignment has revolved around organizing the team and writing about the
processes, and less about actual programming. However, the assumption is that there will be a lot more hands-on
coding tasks for the assignments to come!

### MOM's, communication, and Kanban project board
As this project-based programming assignment was quite a new experience for the team, a structured, well-thought-out
plan for the administrative process was not fully developed before the team had been working for a week already.
For instance, a blueprint format for the MOM's was not proposed until the third team meeting (mom_08_02_21.md),
and also the decision to write everything in English were taken in this same meeting. That being said, when the team
first utilised proper MOM-reporting, it proved valuable for logging what had been discussed earlier meetings, and for
deciding further actions.

When it comes to communication between team members, most of the important dialog happened in recurring team meetings,
while remaining issues were raised in the Discord channel. Keywords for team communication:
- Even though it has been a new experience to all, everyone has tried their best and cooperated nicely with each other.
- The tone and atmosphere between team members have been good, and all feel like their opinions have been heard.
- Some have worked more on code than others, while others have done more administrative work.

For the project methodology, the team chose to go for the Kanban approach, where a so-called Kanban project board was
central for monitoring the team's progress. Strictly speaking the Kanban approach involves limiting the number of tasks
each team member has in development at one time, however, for this particular project relay, this limitation was not
implemented. Despite this, the Kanban approach worked fine for this "warm-up" leg of the project, and the method of
using smaller tasks and sprints on a project board was motivational. On the contrary, the team is fully aware that
limiting each team member's number of subtasks could prove valuable in more complex parts of the project.
Another learning was that a project board "stick-note" that involved two test classes was proved too big, as one of the
classes (`MapTest`) was difficult to automate. So the conclusion is that even smaller stories are recommended.

Another project tool that the team recognises as valuable for such a programming project is the UML class diagram.
Ideally, such a diagram should be incorporated early in every process to get an overview of the application composition.
The team has tried to create such a diagram, but it was never completed due to remaining uncertainties in how the
classes and interfaces should e implemented. Nevertheless, a starting sketch of the UML class diagram is included in
the deliverables.

### Team description and distribution of roles
During the first team meeting, the team members were given specific roles based on a hunch of what could benefit
the team:
- Team Lead and Customer Contact - Morten
- Test Responsible - Daniel
- UI Design Lead - Edyta
- Mechanics/Tech Lead - Mathias

This division were shown to be quite adequate, however as the project evolved, and as the team members embarked various
tasks that needed attention, a natural distribution of roles were solidifying. Morten had taken most of the
responsibility for testing and writing code, while Daniel had taken on most of the administrative tasks that were
pressing. Edyta showed excellent spirit in developing user interfaces for the various game menus, which will be an
important part of the next assignments. Last but not least, Mathias has done good research on the workings of
the libGDX application (including how to implement screens, and come up with a strategy for making the game
object-oriented); in other words, facilitated further development of RoboRally for the coming assignments. In a small
team like this, a division of responsibility as described above proved to be a good technique for securing progress.

The roles for this assignment turned out to be:
- Team Lead and Customer Contact - Daniel
- Code & Test Responsible - Morten
- UI Design Lead - Edyta
- Mechanics/Tech Lead - Mathias

### Thoughts on user stories, acceptance criteria, and solutions
User stories and acceptance criteria were (and still are) a new concept for us, and proved harder to do than expected.
Also, user stories might be even harder to write for an existing game with strict rules. Especially the "value added"
part felt backwards as some of them could probably just be "because the rules of the game say so".

### Thoughts on code
The coding part of this first project leg was small and mostly achieved through the libGDX and Tiled tutorial.
There are several classes in the project that are empty, but intended to use for abstraction and modulation as the
project progresses.
# INF112 Maven template 
Simple skeleton with libgdx. 


## Known bugs


## ROBORALLY RULEBOOK
A CONDENSED VERION OF THE ROBORALLY RULEBOOK FOLLOWS. HOPEFULLY THIS WILL HELP US GET AN OVERVIEW OF THE RULES
AND MAKE IT CLEARER HOW WE CAN PROCEED WITH THE ABSTRACTION OF THE GAME (Daniel).

## SUMMARY OF PLAY
- Each turn, you will draw random Program cards (instructions for your robot).
- Secretly, choose five cards to plan your robot's moves.
- Goal: be the first to touch all flags in order.
- Robots can get in each other's way, push each other off the board, and shoot each other with lasers.

## GAME PIECES, UTILITIES AND EXPRESSIONS
- Course Manual (contains several different courses, each which is a combination of the 2 possible Docking Bay boards,
  the 8 Factory Floor boards, and the flags)
- Docking Bay boards, Factory Floor boards
- flags
- robot plastic figure
- Archive Marker (check point for where a destroyed robot will start next turn)
- Life tokens (loses one each time you are destroyed)
- Damage tokens (receives one each time you are attacked by laser)
- Power Down tokens
- Program Sheet (where a player places their cards, Life tokens, Damage tokens, etc.)
- Program cards, Option cards
- timer (hour glass)
- express conveyor belts and normal conveyor belts
- gears
- pusher
- hole
- wall
- tools: wrench, wrench and hammer
- lasers
- End of Register Phase: after the five registers have been carried out once for each player, see 4) Complete Registers.
- End of Turn: after the five registers have been repeated until the players have used all their cards. 


## SETUP
1) Choose course from Course Manual (combine Docking Bay board, Factory Floor boards and flags into correct course).
   For our game, the Course Manual could be a menu, where the users/players can visualize the courses.
2) Each player chooses a 1 robot plastic figure, 1 Archive Marker (check point), and 1 Program Sheet for that robot.
3) Each player gets 3 Life tokens and puts them on their Program Sheet (when 5 or more players, an optional rule is to
   hand out 4 Life tokens instead).
4) Put the Damage tokens, Power Down tokens, and Factory Floor Guides next to the board (i.e. somewhere in the GUI).
   Shuffle the Program cards deck and the Option cards deck and put them next to the board, face down (again, somewhere
   in the GUI).
5) Randomly determine which player will go first. That player puts their Archive Marker (check point) and robot
   on the Dock 1 starting point of the Docking Bay, with the robot facing the Factory Floor board. The player to the left
   does the same (Dock 2), and so on in clockwise order, until all robots are on their dock.
   Use dock assignments, for something later in the game!
   
## HOW TO PLAY
1) Deal the Program cards.
2) Select up to 5 of the maximum 9 cards you are dealt, and arrange them in desired order in your five registers on
   your Program sheet.
3) Announce your intent for the next turn: either power down (Power Down token is placed on your Program Sheet)
   or continue running.
4) Complete each register in order: execute the first Program Card of each player, complete movements of the board
   (board lasers, conveyor belts, holes etc.), resolve all interactions (pushing, robot laser), and touch flags and
   repair sites.
5) Clean up any end-of-turn effects.

## 1) Deal Program cards
- Shuffle deck.
- Deal cards face down.
- Number of cards a player is dealt is: n = 9 - Damage tokens.
- Max damage tokens a player can have is 9. If you get your 10th Damage token, you lose a Life token.
  If a player have 5 or more Damage tokens, registers on the Program Sheet will start to lock (see Locking Registers
  under DAMAGE AND DESTRUCTION).
- Don't look at any of your cards until all players have been dealt their hands.

## 2) Program registers
- Look at your hand.
- Choose 5 cards you want to use this turn (the rest are discarded).
- Put the 5 cards face down in the registers on your Program Sheet, in your desired order, from left to right
  (this is the order they will be executed).
- Announce that you are done. Once you are done, you cannot rearrange your cards.
- Timer:
  - When only one player remain who is not done with placing their cards, start the 30-seconds timer. If the timer
  runs out, the player's unused cards are randomly placed in the remaining empty registers. Discard any cards that
  are left over.
  - If only one player is programming registers on a given turn (due to other robots are powered down or
    out of the game), start the 30-seconds timer.  


## 3) Announce Power Down
- A player with a damaged robot may choose to power down; this power down takes effect the next turn.
- A power down is announced by putting a Power Down token on the designated spot on your Program Sheet.
- Power Downs are announced in prioritized order (according to the dock number each player has). The player which
  started on Dock 1 goes first (either passes or announces power down), and so on.
- Next turn, when the power down is activated, discard/remove all your Damage tokens.
- As long as the robot is in Power Down mode, the player doesn't receive or execute Program cards, and 
  the robot doesn't move.
- A robot can still be affected by board elements while powered down (e.g. moved by belts, shot by board lasers,
  pushed into holes). It can also be moved/pushed by other robots, or damaged by other robot's lasers.
- Consequently, robots that are powered down can also receive Damage tokens.
- If you announce a power down for the next turn but are destroyed before then, you can choose to reenter the game
  next turn either powered down or not (withdrawing Power Down announcement).
- before cards are dealt each turn, players whose robot was powered down the last turn can choose if they
  want to leave their robots powered down for the current turn. If so, all Damage tokens are again discarded
  before the turn starts.
  

## 4) Complete Registers
- Complete the 5 Program Sheet registers in order, from left to right. For each, follow the register phase sequence:
  - A) Reveal Program cards 
  - B) Robots move (one at a time)
  - C) Board elements move (robot conflicts may arise)
  - D) Lasers fire (both board lasers and robot lasers)
  - E) Touch flags (checkpoints) and repair sites

A) Reveal Program Cards
   - all players reveal their Program card for that register at the same time.

B) Robots Move
   - Movement:
     - move each robot as its Program card indicates.
     - program cards (amount):
       - u-turn: 6 kort (10 - 60, interval 10)
       - rotate left: 18 kort (70-410, interval 20)
       - rotate right: 18 kort (80-420, interval 20)
       - backup: 6 kort (430 - 480, interval 10)
       - move 1: 18 kort (490 - 650, interval 10)
       - move 2: 12 kort (670 - 780, interval 10)
       - move 3: 6 kort (790 - 840, interval 10) 
  
   - Priority:
     - the priority numbers on each Program card indicates a robot's priority for when it can move.
     - the highest number (fastest movement) move first, and so on.
   - Pushing other robots:
     - when robots collide, one will push the other
     - robots can be pushed anywhere on the board, off the side of the board, onto belts, into pit etc.
     - cannot be pushed through wall; in that case the movement will simply stop.
     - no damage will be inflicted on a robot that collides with a wall (no Damage tokens accumulated).

C) Board elements move
   - Order of board elements:
     - 1) Express conveyor belts move 1 space in the direction of the arrows.
     - 2) Express conveyor belts and normal conveyo belts move 1 space in the direction of the arrows.
     - 3) Pushers push if active (marked with register numbers 1,2,3,4 or 5)
     - 4) Gears rotate 90 degrees in the direction of the arrows.
     - More than one board element may affect a robot each turn. See Factory Floor Guides for a complete
       listing of board elements (let's stick to the standard elements initially!).
   - Conveyor belt priority:
     - Normally, all robots on belts are moved at the same time (their movement has no ranking).
     - However, sometimes more than one belt will converge into the same space, which can make robots arrive
       there at the same time.
     - Robots on belts never push other robots, so the two robots that "fight" over the same space will stay in their
       original place.
     - Also, if a robot is being dropped of the end of a belt, but the space is occupied by another robot, the robot
       on the belt stays there.
     - If things are unclear, don't move either of the robots.
   - Rotating conveyor belts:
     - If a robot is moved onto a curved/rotating conveyor belt by another conveyor belt, the robot is also rotated
       90 degrees in the same direction as the rotation of the rotating belt it lands on.
     - Also true if robot is being moved from express belt to normal belt.
     - However, if robot moves onto a rotating conveyor belt on its own or is pushed onto a curved/rotating conveyor
       belt by another robot, then a 90 degrees rotation will not occur.

D) Lasers fire
   - Board Lasers:
     - A robot that ends up in a space that has lasers going through it receives 1 Damage token
       for each laser in that place.
     - Lasers don't pass through robots, so only the closest robot will be damaged.
     - Robots can move through lasers undamaged; only robots that are still in the path of a laser
       after all the board elements have moved receive Damage tokens.
     - See DAMAGE AND DESTRUCTION for details on damage effects.  
   - Robot Lasers:
     - Every robot has a forward-pointing laser.
     - Any robot in another robot's line of sight is automatically damaged by that robot's main laser
       and receives 1 Damage token.
     - A robot's laser does not penetrate through walls or other robots.  

E) Touch flags (checkpoints) and repair sites
   - Any robot that has survived all mayhem up until this point and is on a flag, "touches" that flag.
   - Starting next turn, it can move on to the next flag.
   - The robots should have a property (e.g. field variable) which keeps track of the flags the robot have visited.  
   - Any robot on a flag or repair site, at the end of the Register Phase, updates its checkpoint by
     putting their Archive Marker on that space.
   - If a robot is destroyed before reaching another checkpoint, this is where it will reenter the game.
   - Standing on a single

All of the above (A to E) completes a single Register Phase.
Repeat this sequence for each register in your Program Sheet.


## 5) Cleanup
- Timing:
  - Cleanup happens after finishing the last register phase in a turn (register 5).
- Repairs and Upgrades:
  - Robots on a single-wrench space (repair site) discard 1 Damage token.
  - Robots on a double-wrench/hammer space (repair site) discard 1 Damage token and draw an Option card. Read this
    aloud to the other players and put it in front of you, face up. (See "Using Options to Prevent Damage").
- Wiping Registers:
  - Discard all Program cards from registers that are not locked (see "Locked Registers" for more).
- Setup for the Next Turn:
  - Players whose robots were powered down this turn announce whether their robots will remain
    powered down on the next turn.
  - Each robot that was destroyed this turn, reenters play in the space where their Archive Marker (check point) is.
    The player chooses which direction the robot faces.
  - Robots reentering the play receive 2 Damage tokens (plus any Damage tokens taken while powered down).
    A player may change their mind and decide to reenter the game powered down the next turn (to discard
    the Damage tokens).
  - Multiple archive markers on the same space:
    - If two or more robots would reenter play on the same space, they're placed back in the order they were destroyed
      (need to keep track of which robot got destroyed first).
    - The first robot that was destroyed gets the archive space
    - Next player chooses an empty adjacent space (up to 8 possible), facing any direction the player wants,
      but cannot face a direction where there is another robot within 3 spaces from the robot.
    - when choosing a space to place your robot, you can choose all types of tiles, except where there is a hole.  
    
After cleanup, next turn begins!

## DAMAGE AND DESTRUCTION

## Locked Registers
- Robots get 1 fewer Program card for each Damage token they have: 9 - Damage tokens = n
- When a robot has 5 Damage tokens, the first register (i.e. no. 5) will be locked, and so on.
- Damage tokens:
  - 0: dealt 9 cards
  - 1: dealt 8 cards
  - 2: dealt 7 cards
  - 3: dealt 6 cards
  - 4: dealt 5 cards
  - 5: dealt 4 cards, register 5 is locked (from previous turn)!
  - 6: dealt 3 cards, registers 4, and 5 are locked!  
  - 7: dealt 2 cards, registers 3, 4, and 5 are locked!
  - 8: dealt 1 cards, registers 2, 3, 4, and 5 are locked!
  - 9: dealt 0 cards, registers 1, 2, 3, 4, and 5 are locked!
  - 10: destruction 
- Once a register is locked the Program card in that register stays there until the damage locking 
  the register is repaired. When that happens, both the Damage token and the Program card are discarded.
- Unlocking of registers must happen in reverse order one by one (left to right, from register 1 to 5).
- A robot with all its registers locked still moves; the program cards from the previous turn stay in place,
  and that program is simply executed again.
- Locking registers during power down:
  - Since robots can still be damaged during power downs, they can sometimes accumlate enough Damage tokens
    for a register to lock up.
  - Registers that become locked when your robot is powered down (and thus don't have any cards in their registers),
    are immediately programmed randomly: a Program card is drawn from the deck and placed in that register, face up.
  
## Using Options to Prevent Damage
- If you want, a robot with an Option card can discard it to avoid receiving a Damage token
  (the Option takes the damage instead of the robot.)
- You can do this for as many Option cards as you have
- However, you have to make the exchnge when the Damage token is received
- Stack the discarded cards face up next to the Option deck

## Destruction
- A robot is destroyed when:
  - it receives its 10th Damage token, or
  - it moves or is moved into a pit, or
  - it moves or is moved off the edge of the board.  
- A destroyed robot immediately loses/discards:
  - an Option card (of the players choice), and
  - a Life token.
- When a robot loses a Life token:
  - it will reenter the game in the Cleanup step, on its checkpoint space (where their Archive Marker is),
  - unless the Life token was the robot's last Life token -- in that case the robot is permanently out of the game.

## WINNING THE GAME
- The winner is the first player to touch all the flags in correct order.
- The game can end as soon as the winner touches the last flag, or play can continue to determine runners up.


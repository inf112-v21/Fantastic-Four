# Manual test 2

Start: Run the application by starting Main.java in IDE. 
	A menu page has popped up so you can start testing. Proceed by clicking on either singleplayer or multiplayer, then click select to start the game and wait for cards to be shown on screen. You can now click on cards to do the tests.

## Manual test for Cards

### Card selection
 - Expect: When single card is clicked, then it's moved from card selection section to player panel at the bottom of the game window. 
 - Actual: Card is placed from card selection section to player panel.
 <br>
 
 - Expect: When particular card is selected then the blank card is shown.
 - Actual: After clicking on the card the blank card is shown. 
 
## Manual test for multiplayer
Start: Run the application by starting Main.java in IDE. 
	A menu page has popped up so you can start testing. Proceed by clicking on multiplayer and wait for Multiplayer settings window to be shown on screen.

### Set up multiplayer
 - Expect: By clicking on either "Enter IP" or "Enter Name" the texi inside boxes dissapeard and the cursor is shown.
 - Actual: The text inside boxes dissapeard and the cursor is visible. 
<br>

 - Expect: After clicking on text boxes player can complete the form with IP an Nickname.
 - Actual: It is possible to write inside text boxes.
 
#### OK
 - Expect: When "OK" button is clicked, it will send player to Players Screen.
 - Actual: Clicking "OK" button made brought player to Player Screen. 
  
#### HOST
 - Expect: When "Host" button is clicked, it makes player the host of the game and it will send player to Player Screen window.
 - Actual: Clicking "Host" button made brought player to Player Screen and made player Host of the game. 
 
## Manual test for Player Screen
 - Expect: When "Start Game" button is clicked, it will start RoboRally game window.
 - Actual: Clicking "Start Game" button game was started. 
 
#### RETURN
 - Expect: When "Return" button is clicked, the main menu screen is shown.
 - Actual: The user is was brought to main menu window.  
 
 Tests status: passed


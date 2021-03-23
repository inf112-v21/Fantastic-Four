# Manual test for main menu

Start: Run the application by starting Main.java in IDE. 
	A menu page has popped up so you can start testing.

### Single player
 - Expect: When "Single Player" is clicked it opens a game window.
 - Actual: The game map window was shown.
 
### Multiplayer
 - Expect: When "Multiplayer" is clicked it shows the user a window where it is possible to 
   declare player name and ip address in text filed.
 - Actual: The user was shown a player name and ip address text filed.

### Rules
 - Expect: When "Rules" button is clicked it show the user a window where it is possible to see game rules window.
 - Actual: The user was brought to the game rules window.

### Settings
 - Expect: When "Settings" button is clicked it will exit the game.
 - Actual: Clicking "Settings" button made program quit. *
 *At this point (Oblig2) we have not implemented SettingsScreen, so far it exits game.

### Quit
 - Expect: When "Quit" button is clicked, it will exit the game.
 - Actual: Clicking "Quit" button made program quit. 



Tests status: passed

# INF112 RoboRally

This project uses:
*   Java 15.02
*   JUnit 4.11
*   Maven

### For hosting multiplayer games online
- Log in to your router 
- Open port `62210` for both TCP and UDP and forward the traffic to your server's IP address
- Get your router's public IP (for example from 
[Google](https://www.google.com/search?q=what+is+my+ip)), and send that IP to your friends for connecting.
  (The internal IP can be used locally without port forwarding)
  
### macOS and Linux
Note for macOS: Enable `VM options: -XstartOnFirstThread` to allow this game to work, this is found in `Run
Configuration` in IntelliJ.

1. Open cmd `Ctrl+Alt+T`
2. Clone the repository `git clone git@github.com:inf112-v21/Fantastic-Four.git`
3. Change directory `cd Fantastic-Four`
4. Build game `mvn clean verify assembly:single`
5. Run the game `java -jar target/mvn-app-1.0-SNAPSHOT-jar-with-dependencies.jar`

### Windows
1. Open cmd `Ctrl+Alt+T`
2. Clone the repository `git clone git@github.com:inf112-v21/Fantastic-Four.git`
3. Open project in your favorite IDE (IntelliJ, Eclipse)
4. Run `Main.java`


      
## Known bugs
This game is still in development

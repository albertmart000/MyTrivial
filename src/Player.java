import java.util.Objects;

public class Player {
private String playerName;
private int playerScore;

public Player(String name) {
        this.playerName = name;
        this.playerScore = 0;
        }


public void setPlayerName(String playerName) {
        this.playerName = playerName;
        }

public String getPlayerName() {
        return playerName;
        }

public int getPlayerScore() {
        return playerScore;
        }

public void setPlayerScore(int playerPuntuation) {
        this.playerScore = playerPuntuation;
        }

@Override
public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return playerScore == player.playerScore && Objects.equals(playerName, player.playerName);
        }

@Override
public int hashCode() {
        return Objects.hash(playerName, playerScore);
        }

@Override
public String toString() {
        return "Player{" +
        "name='" + playerName + '\'' +
        ", playerPuntuation=" + playerScore +
        '}';
        }
        }

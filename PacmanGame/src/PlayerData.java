public class PlayerData {

    private String playerName;
    private int playerScore;
    private int playerLives;

    public PlayerData() {
        setPlayerName("NO NAME");
        setPlayerScore(0);
        setPlayerLives(PacmanGame.STARTING_LIVES);
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }

    public int getPlayerLives() {
        return playerLives;
    }

    public void setPlayerLives(int playerLives) {
        this.playerLives = playerLives;
    }

    public void resetPlayerData(){
        setPlayerScore(0);
        setPlayerLives(PacmanGame.STARTING_LIVES);
    }
}
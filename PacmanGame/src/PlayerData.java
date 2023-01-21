public class PlayerData {

    private String playerName;
    private int playerScore;
    private int playerLives;

    public PlayerData() {
        setPlayerName("NO NAME");
        setPlayerScore(0);
        setPlayerLives(PacmanGame.STARTING_LIVES);
    }

    public void setPlayerLives(int playerLives) {
        this.playerLives = playerLives;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public int getPlayerLives() {
        return playerLives;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void resetPlayerData(){
        setPlayerScore(0);
        setPlayerLives(PacmanGame.STARTING_LIVES);
    }
}
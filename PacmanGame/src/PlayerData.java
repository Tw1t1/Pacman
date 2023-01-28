public class PlayerData {

    private String playerName;
    private int playerScore;
    private int playerLives;
    private boolean won;

    public PlayerData() {
        setPlayerName("NO NAME");
        setPlayerScore(0);
        setPlayerLives(PacmanGame.STARTING_LIVES);
        setWon(false);
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

    public boolean isWon() {
        return won;
    }

    public void setWon(boolean won) {
        this.won = won;
    }

    public void resetPlayerData(){
        setPlayerScore(0);
        setPlayerLives(PacmanGame.STARTING_LIVES);
    }
}
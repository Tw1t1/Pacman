public class PlayerData {
    public static String playerName;
    public static int playerScore;
    public static int playerLives;
    
    public static void setPlayerName(String name) {
        playerName = name;
    }

    public static void setScore(String score) {
        playerName = score; 
    }

    public static int getScore() {
        return playerScore;
    }

    public static void setLives(int life) {
        playerLives = life; 
    }
}
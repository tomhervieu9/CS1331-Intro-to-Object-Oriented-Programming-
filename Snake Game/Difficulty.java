package engine;

/**
 * Represents a difficulty setting for the game.
 *
 * The game can be either EASY, NORMAL, or HARD. They have increasing point
 * values per apple and decreasing interval times (i.e the snake speeds up with
 * difficulty).
 *
 * @author Susanna, Jim
 */
public enum Difficulty {
    EASY(100, 250, 200), NORMAL(200, 200, 125), HARD(300, 150, 75);
    private final int score;
    private final int baseTime;
    private final int minTime;
    public static final int INCREMENT = 25;

    /**
     * Constructor for the enum.
     *
     * @param score the number of points per apple for this game mode.
     * @param baseTime the starting time for the game.
     * @param minTime the minimum time delay the difficulty goes to.
     */
    private Difficulty(int score, int baseTime, int minTime) {
        this.score = score;
        this.baseTime = baseTime;
        this.minTime = minTime;
    }

    /**
     * @return an int with the value of score
     */
    public int getScore() {
        return score;
    }

    /**
     * @return an int with the value of baseTime
     */
    public int getBaseTime() {
        return baseTime;
    }

    /**
     * @return an int with the value of minTime
     */
    public int getMinTime() {
        return minTime;
    }

}


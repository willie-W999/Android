public class GuessGame {
    private int target;

    public GuessGame(int target) {
        this.target = target;
    }

    public int guess(int num) {
        if (num < target) {
            return 1;
        } else if (num > target) {
            return -1;
        } else {
            return 0;
        }
    }
}

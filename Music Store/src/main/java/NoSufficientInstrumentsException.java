public class NoSufficientInstrumentsException extends Exception {
    public NoSufficientInstrumentsException() {
        super("You do not have any instruments to sell.");
    }
}
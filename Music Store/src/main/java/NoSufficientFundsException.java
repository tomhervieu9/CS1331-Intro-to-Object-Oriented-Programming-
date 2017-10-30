public class NoSufficientFundsException extends Exception {
    public NoSufficientFundsException() {
        super("You do not have enough funds to purchase this instrument.");
    }
}
public class DoNotOwnInstrumentException extends Exception {

    public DoNotOwnInstrumentException() {
        super("Sorry, you can't sell an instrument that you don't own.");
    }
}
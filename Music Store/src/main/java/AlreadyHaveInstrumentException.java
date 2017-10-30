public class AlreadyHaveInstrumentException extends Exception {

    public AlreadyHaveInstrumentException() {
        super("Sorry, you already own this instrument.");
    }
}
public abstract class Stringed extends Instrument {

    private int numOfStrings;

    public Stringed(double price, int numOfStrings) {
        super(price);
        this.numOfStrings = numOfStrings;
    }

    public int getNumberOfStrings() {
        return numOfStrings;
    }

    public String hasStrings() {
        return "Unique Property: This instrument has strings";
    }

    public abstract String play();

}
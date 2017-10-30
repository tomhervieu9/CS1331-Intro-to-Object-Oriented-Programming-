public abstract class Brass extends Instrument {

    private int numOfValves;

    public Brass(double price, int numOfValves) {
        super(price);
        this.numOfValves = numOfValves;
    }

    public int getNumberOfValves() {
        return numOfValves;
    }

    public String hasValves() {
        return "Unique Property: This instrument has valves";
    }

    public abstract String play();

}
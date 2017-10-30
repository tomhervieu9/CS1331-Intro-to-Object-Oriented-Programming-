public abstract class Woodwind extends Instrument {

    private int numOfVibratingReeds;

    public Woodwind(double price, int numOfVibratingReeds) {
        super(price);
        this.numOfVibratingReeds = numOfVibratingReeds;
    }

    public int getNumOfVibratingReeds() {
        return numOfVibratingReeds;
    }

    public String getHasVibratingReed() {
        return "Unique Property: This instrument has vibrating reeds";
    }

    public abstract String play();

}
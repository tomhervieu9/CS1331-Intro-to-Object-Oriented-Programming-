public abstract class Percussion extends Instrument {

    private String canBeHit;

    public Percussion(double price) {
        super(price);
        this.canBeHit = canBeHit;
    }

    public String getCanBeHit() {
        return "Unique Property: This instrument makes a sound when it is hit";
    }

    public abstract String play();

}
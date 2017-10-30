public class Piano extends Percussion {

    public Piano(double price) {
        super(price);
    }

    public String toString() {
        return "Piano costs: " + getPrice() + " dollars"
            + " \nSerial Number: " + getSerialNumber() + " \nCan be hit: "
            + getCanBeHit()
            + " \nSounds like: " + play() +"\n";
    }

    public String play() {
        return "poom, poom!";
    }
}
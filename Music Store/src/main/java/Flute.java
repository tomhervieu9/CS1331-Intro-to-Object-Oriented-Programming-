public class Flute extends Woodwind {

    public Flute(double price, int numOfVibratingReeds) {
        super(price, numOfVibratingReeds);
    }

    public String toString() {
        return "This flute costs: " + getPrice() + " dollars"
            + " \nSerial Number: " + getSerialNumber() + " \nNumber of Strings: "
            + getNumOfVibratingReeds() + " \nSounds like: " + play() + "\n";
    }

    public String play() {
        return "toodle-loo-toodle-loo";
    }
}
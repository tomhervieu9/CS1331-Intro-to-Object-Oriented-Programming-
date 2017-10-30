public class Trumpet extends Brass {

    public Trumpet(double price, int numOfValves) {
        super(price, numOfValves);
    }

    public String toString() {
        return "This trumpet costs: " + getPrice() + " dollars"
            + " \nSerial Number: " + getSerialNumber() + " \nNumber of Valves: "
            + getNumberOfValves() + " \nSounds like: " + play() + "\n";
    }

    public String play() {
        return "wuuuuh..wuuuuuuuuuuuuuuh";
    }
}
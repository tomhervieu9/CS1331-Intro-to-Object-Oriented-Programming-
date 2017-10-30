public class Violin extends Stringed {

    public Violin(double price, int numOfStrings) {
        super(price, numOfStrings);
    }

    public String toString() {
        return "This violin costs: " + getPrice()
            + " dollars" + " \nSerial Number: " + getSerialNumber()
            + " \nNumber of Strings: "
            + getNumberOfStrings() + " \nSounds like: " + play() +"\n";
    }

    public String play() {
        return "eeehr, uuur, eeehr";
    }
}
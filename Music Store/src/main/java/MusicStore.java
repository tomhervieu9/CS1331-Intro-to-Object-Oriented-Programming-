public class MusicStore {

    public static void main(String[] args) {
        Stringed violin1 = new Violin(20, 5);
        Stringed violin2 = new Violin(30, 6);
        Stringed violin3 = new Violin(10, 3);
        Stringed violin4 = new Violin(100, 4);
        Brass trumpet1 = new Trumpet(17, 11);
        Percussion piano1 = new Piano(110);
        Woodwind flute1 = new Flute(21, 6);
        Musician bobby = new Musician("Bob", 45.3, violin1, violin2
            , trumpet1, piano1);
        System.out.println(bobby.getInstrumentList());
        try {
            bobby.purchaseInstrument(violin4);
        } catch (NoSufficientFundsException e) {
            System.out.println(e.getMessage());
        } catch (AlreadyHaveInstrumentException a) {
            System.out.println(a.getMessage());
        }
        try {
            bobby.sellInstrument(violin3);
        } catch (DoNotOwnInstrumentException e) {
            System.out.println(e.getMessage());
        } catch (NoSufficientInstrumentsException a) {
            System.out.println(a.getMessage());
        }
        try {
            bobby.purchaseInstrument(flute1);
        } catch (NoSufficientFundsException e) {
            System.out.println(e.getMessage());
        } catch (AlreadyHaveInstrumentException a) {
            System.out.println(a.getMessage());
        }
        try {
            bobby.sellInstrument(flute1);
        } catch (DoNotOwnInstrumentException e) {
            System.out.println(e.getMessage());
        } catch (NoSufficientInstrumentsException a) {
            System.out.println(a.getMessage());
        }
        try {
            bobby.purchaseInstrument(trumpet1);
        } catch (NoSufficientFundsException e) {
            System.out.println(e.getMessage());
        } catch (AlreadyHaveInstrumentException a) {
            System.out.println(a.getMessage());
        }
        try {
            bobby.sellInstrument(trumpet1);
        } catch (DoNotOwnInstrumentException e) {
            System.out.println(e.getMessage());
        } catch (NoSufficientInstrumentsException a) {
            System.out.println(a.getMessage());
        }
        System.out.println(bobby.getInstrumentList());
        System.out.println(bobby.getName() + " has " + bobby.getFunds()
            + " dollars in funds and has the following "
            + bobby.getNumberOfInstruments()
            + " instruments:\n" + bobby.getInstrumentList());
        System.out.println(violin3.hasStrings());
    }
}

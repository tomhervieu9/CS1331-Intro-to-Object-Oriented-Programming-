import java.util.ArrayList;

public class Musician {

    private int numOfInstr;
    private String name;
    private double funds;
    private ArrayList<Instrument> instrumentList = new ArrayList<Instrument>();

    public Musician(String name, double funds, Instrument...startingInstrument)
    {
        this.name = name;
        this.funds = funds;
        for (Instrument s : startingInstrument)  {
            instrumentList.add(s);
        }
    }

    public ArrayList getInstrumentList() {
        return instrumentList;
    }

    public int getNumberOfInstruments() {
        return instrumentList.size();
    }

    public String getName() {
        return name;
    }

    public double getFunds() {
        return funds;
    }

    public void purchaseInstrument(Instrument instrument) throws
    NoSufficientFundsException, AlreadyHaveInstrumentException {
        if ((funds - instrument.getPrice()) < 0) {
            throw new NoSufficientFundsException();
        }
        for (Instrument s: instrumentList) {
            if (s.equals(instrument)) {
                throw new AlreadyHaveInstrumentException();
            }
        }
        funds = funds - instrument.getPrice();
        instrumentList.add(instrument);
    }

    public void sellInstrument(Instrument instrument) throws
    NoSufficientInstrumentsException, DoNotOwnInstrumentException {
        if (instrumentList.size() == 1) {
            throw new NoSufficientInstrumentsException();
        }
        if (!instrumentList.contains(instrument)) {
            throw new DoNotOwnInstrumentException();
        }
        funds = funds + instrument.getPrice();
        instrumentList.remove(instrument);
    }
}

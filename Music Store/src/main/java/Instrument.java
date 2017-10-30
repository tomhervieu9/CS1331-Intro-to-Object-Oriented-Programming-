public abstract class Instrument {

    private double price;
    private String serialNumber;


    public Instrument(double price) {
        this.price = price;
        createSerialNumber();
    }

    public void createSerialNumber() {
        int lastN = 0 + (int) (Math.random() * ((999999999 - 100000000) + 1));
        serialNumber = "A" + lastN;
    }
    public double getPrice() {
        return price;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public abstract String play();

    public boolean equals(Object i) {
        if (i == null) {
            return false;
        }
        if (i == this) {
            return true;
        }
        if (i instanceof Instrument) {
            Instrument instr = (Instrument) i;
            if (instr.getSerialNumber().equals(this.getSerialNumber())) {
                return true;
            }
        }
        return false;
    }
}
import java.awt.Rectangle;

public class Blaziken extends FireType {

    /**
     * Constructor
     * @param x The X position of Blaziken
     * @param y The Y position of Blaziken
     * @param bounds The bounding Rectangle
     */

    private static final int MAX_LEVEL = 180;

    public Blaziken(int x, int y, Rectangle bounds) {
        super(x, y, bounds);
        setImage("../resources/blaziken.png");
    }

    @Override
    public boolean canReproduceWithPokemon(Pokemon other) {
        if (other instanceof FireType) {
            return true;
        }
        return false;
    }

    @Override
    public Pokemon reproduceWithPokemon(Pokemon other) {
        int randNum = Pokemon.getRand().nextInt(100);
        if (canReproduceWithPokemon(other) && this.getChildren() < 2
            && other.getChildren() < 2 && this.getLevel() > 30
            && other.getLevel() > 30 && randNum < 61) {
            return (new Blaziken(this.getXPos(), this.getYPos(),
                this.getBounds()));
        } else {
            return null;
        }
    }

    @Override
    public boolean isOld() {
        if (this.getLevel() > MAX_LEVEL) {
            return true;
        }
        return false;
    }
}

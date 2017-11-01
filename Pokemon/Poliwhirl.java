import java.awt.Rectangle;

public class Poliwhirl extends WaterType {

    /**
     * Constructor
     * @param x The X position of Poliwhirl
     * @param y The Y position of Poliwhirl
     * @param bounds The bounding Rectangle
     */

    private static final int MAX_LEVEL = 200;

    public Poliwhirl(int x, int y, Rectangle bounds) {
        super(x, y, bounds);
        setImage("../resources/poliwhirl.png");
    }

    @Override
    public boolean canReproduceWithPokemon(Pokemon other) {
        if (other instanceof WaterType) {
            return true;
        }
        return false;
    }

    @Override
    public Pokemon reproduceWithPokemon(Pokemon other) {
        if (canReproduceWithPokemon(other) && this.getChildren() < 2
            && other.getChildren() < 2 && this.getLevel() > 70
            && other.getLevel() > 70) {
            return (new Poliwhirl(this.getXPos(), this.getYPos(),
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

import java.awt.Rectangle;

public class Torterra extends GrassType {

    /**
     * Constructor
     * @param x The X position of Torterra
     * @param y The Y position of Torterra
     * @param bounds The bounding Rectangle
     */
    private static final int MAX_LEVEL = 95;

    public Torterra(int x, int y, Rectangle bounds) {
        super(x, y, bounds);
        setImage("../resources/torterra.png");
    }

    @Override
    public boolean canReproduceWithPokemon(Pokemon other) {
        if (other instanceof GrassType) {
            return true;
        }
        return false;
    }

    @Override
    public Pokemon reproduceWithPokemon(Pokemon other) {
        if (canReproduceWithPokemon(other) && this.getChildren() < 2
            && other.getChildren() < 2 && this.getLevel() > 70
            && other.getLevel() > 70) {
            return (new Torterra(this.getXPos(),
                this.getYPos(), this.getBounds()));
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

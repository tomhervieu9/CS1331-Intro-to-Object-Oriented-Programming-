import java.awt.Rectangle;

public class Pikachu extends LightningType {

    /**
     * Constructor
     * @param x The X position of Pikachu
     * @param y The Y position of Pikachu
     * @param bounds The bounding Rectangle
     */

    private static final int MAX_LEVEL = 100;

    public Pikachu(int x, int y, Rectangle bounds) {
        super(x, y, bounds);
        setImage("../resources/pikachu.png");
    }

    @Override
    public boolean canReproduceWithPokemon(Pokemon other) {
        if (other instanceof LightningType) {
            return true;
        }
        return false;
    }

    @Override
    public Pokemon reproduceWithPokemon(Pokemon other) {
        if (canReproduceWithPokemon(other) && this.getChildren() < 2
                && other.getChildren() < 2 && this.getLevel() > 70
                && other.getLevel() > 70) {
            return (new Pikachu(this.getXPos(), this.getYPos(),
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

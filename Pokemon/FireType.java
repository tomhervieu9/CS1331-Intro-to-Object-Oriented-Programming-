/**
 * A fire type pokemon
 *
 * @author Farhan Tejani
 */

import java.awt.Rectangle;

public abstract class FireType extends Pokemon {

    /**
     * Constructor
     * @param x The X position of this Fire type
     * @param y The Y position of this Fire type
     * @param bounds The bounding Rectangle
     */
    public FireType(int x, int y, Rectangle bounds) {
        super(x, y, bounds);
    }

    @Override
    public boolean canHarmPokemon(Pokemon other) {
        int randNum = this.getRand().nextInt(100);
        if (other instanceof FireType) {
            if (this instanceof Blaziken) {
                if (this.getLevel() > other.getLevel()) {
                    if (randNum < 91) {
                        return true;
                    }
                } else {
                    if (randNum < 13) {
                        return true;
                    }
                }
            }
            if (randNum < 31) {
                return true;
            }
        } else if (other instanceof WaterType) {
            if (randNum < 21) {
                return true;
            }
        } else if (other instanceof GrassType) {
            if (randNum < 71) {
                return true;
            }
        } else if (other instanceof LightningType) {
            if (randNum < 41) {
                return true;
            }
        }
        return false;
    }
}

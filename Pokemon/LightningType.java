/**
 * A lightning type pokemon
 *
 * @author Farhan Tejani
 */

import java.awt.Rectangle;

public abstract class LightningType extends Pokemon {

    /**
     * Constructor
     * @param x The X position of this Lightning type
     * @param y The Y position of this Lightning type
     * @param bounds The bounding Rectangle
     */
    public LightningType(int x, int y, Rectangle bounds) {
        super(x, y, bounds);
    }

    @Override
    public boolean canHarmPokemon(Pokemon other) {
        int randNum = this.getRand().nextInt(100);
        if (other instanceof FireType) {
            if (this instanceof Pikachu && other instanceof Flareon) {
                if (randNum < 46) {
                    return true;
                }
            } else {
                if (randNum < 31) {
                    return true;
                }
            }
        } else if (other instanceof WaterType) {
            if (randNum < 51) {
                return true;
            }
        } else if (other instanceof GrassType) {
            if (randNum < 61) {
                return true;
            }
        } else if (other instanceof LightningType) {
            if (randNum < 91) {
                return true;
            }
        }
        return false;
    }
}

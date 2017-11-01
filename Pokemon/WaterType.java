/**
 * A water type pokemon
 *
 * @author Farhan Tejani
 */

import java.awt.Rectangle;

public abstract class WaterType extends Pokemon {

    /**
     * Constructor
     * @param x The X position of this Water Type Pokemon
     * @param y The Y position of this Water Type Pokemon
     * @param bounds The bounding Rectangle
     */
    public WaterType(int x, int y, Rectangle bounds) {
        super(x, y, bounds);
    }

    @Override
    public boolean canHarmPokemon(Pokemon other) {
        int randNum = this.getRand().nextInt(100);
        if (other instanceof FireType) {
            if (randNum < 91) {
                return true;
            }
        } else if (other instanceof WaterType) {
            if (this instanceof Poliwhirl && other instanceof Poliwhirl) {
                if (randNum < 29) {
                    return true;
                }
            } else {
                if (randNum < 41) {
                    return true;
                }
            }
        } else if (other instanceof GrassType) {
            if (randNum < 21) {
                return true;
            }
        } else if (other instanceof LightningType) {
            if (randNum < 51) {
                return true;
            }
        }
        return false;
    }
}

/**
 * A grass type pokemon
 *
 * @author Farhan Tejani
 */

import java.awt.Rectangle;

public abstract class GrassType extends Pokemon {

    /**
     * Constructor
     * @param x The X position of the Grass type Pokemon
     * @param y The Y position of the Grass type Pokemon
     * @param bounds The bounding Rectangle
     */
    public GrassType(int x, int y, Rectangle bounds) {
        super(x, y, bounds);
    }

    @Override
    public boolean canHarmPokemon(Pokemon other) {
        int randNum = this.getRand().nextInt(100);
        if (other instanceof FireType) {
            if (randNum < 41) {
                return true;
            }
        } else if (other instanceof WaterType) {
            if (this instanceof Torterra && other instanceof Poliwhirl) {
                if (randNum < 71) {
                    return true;
                }
            } else {
                if (randNum < 31) {
                    return true;
                }
            }
        } else if (other instanceof GrassType) {
            if (randNum < 31) {
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

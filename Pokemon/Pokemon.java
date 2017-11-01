/**
 * The abstract Pokemon for the PokeBattle Simulation
 *
 * @author Heather, Aniruddha
 */
import java.util.Random;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import java.awt.Graphics;

public abstract class Pokemon {

    private Rectangle bounds;
    private int xPos;
    private int yPos;
    private ImageIcon image;
    private int health;
    private int level;
    private int children;
    private int speed;
    private static Random rand = new Random();

    /**
     * Constructor
     *
     * Represents a Pokemon in the PokeWorld. Each Pokemon
     * has a location in the world and attributes which help
     * it reproduce and thrive.
     * @param xPos The X position of this Pokemon
     * @param yPos The Y position of this Pokemon
     * @param bounds The boundaries of the PokeWorld where
     *               the Pokemon can exist
     */
    public Pokemon(int xPos, int yPos, Rectangle bounds) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.bounds = bounds;
        this.level = 1;
        this.health = 100;
        this.children = 0;
        this.speed = 20;
    }

    /**
     * @return the bounding rectangle of the PokeWorld
     *             that this Pokemon exists in
     */
    public Rectangle getBounds() {
        return bounds;
    }

    /**
     * @return the X position of this Pokemon
     */
    public int getXPos() {
        return xPos;
    }

    /**
     * @return the Y position of this Pokemon
     */
    public int getYPos() {
        return yPos;
    }


    /**
     * @return the current health value of this Pokemon
     */
    public int getHealth() {
        return health;
    }

    /**
     * @return the current level of this Pokemon
     */
    public int getLevel() {
        return level;
    }

    /**
     * @return the number of children this Pokemon has
     */
    public int getChildren() {
        return children;
    }

    /**
     * @return the current speed of this pokemon
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * @return the Random object used by Pokemon for random events
     */
    public static Random getRand() {
        return rand;
    }

    /**
    * Sets the image attribute for this pokemon
    * @param image the ImageIcon to use to represent this Pokemon
    */
    public void setImage(ImageIcon image) {
        this.image = image;
    }

    /**
     * Sets the image attribute for this pokemon
     * @param image the path to the image to use for this Pokemon
     */
    public void setImage(String path) {
        this.image = new ImageIcon(path);
    }

    /**
     * Sets the Pokemon's health value
     * @param health The new health of the Pokemon
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Sets the Pokemon's level
     * @param level The new level of the Pokemon
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Sets the number of children of this Pokemon
     * @param children The number of children this Pokemon now has
     */
    public void setChildren(int children) {
        this.children = children;
    }

    /**
     * Sets the speed of this Pokemon
     * @param speed the updated speed
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * Should draw the Pokemon at its location.
     * @param Graphics object for drawing use
     */
    public void draw(Graphics g) {
        image.paintIcon(null, g, xPos, yPos);
    }

    /**
     * Will move the Pokemon in a random yet effective manner (it doesn't move
     * in circles). Each time a Pokemon moves, it's health should decrease and
     * its level should increase.
     */
    public void move() {
        int multiplier = 1;
        int randNum1 = rand.nextInt(multiplier * speed);
        int randNum2 = rand.nextInt(multiplier * speed);
        int randNum3 = rand.nextInt(multiplier * speed);
        int randNum4 = rand.nextInt(multiplier * speed);
        if (0 < (xPos + randNum1 - randNum2) && (xPos + randNum1
            - randNum2) < bounds.getWidth() && 0 < (yPos + randNum3
            - randNum4) && (yPos + randNum3 - randNum4) < bounds.getHeight()) {
            if (this instanceof FireType && this.xPos > 300
                && this.yPos < 300) {
                xPos += 2 * (randNum1 - randNum2);
                yPos += 2 * (randNum3 - randNum4);
                level++;
                health--;
            } else if (this instanceof WaterType && this.xPos < 300
                && this.yPos > 300) {
                xPos += (randNum1 - randNum2);
                yPos += (randNum3 - randNum4);
                level += 2;
                health--;
            } else if (this instanceof GrassType && this.xPos < 300
                && this.yPos < 300) {
                xPos += (randNum1 - randNum2);
                yPos += (randNum3 - randNum4);
                level++;
                health++;
            } else if (this instanceof LightningType && this.xPos > 300
                && this.yPos < 300) {
                xPos += 4 * (randNum1 - randNum2);
                yPos += 4 * (randNum3 - randNum4);
                level++;
                health--;
            } else {
                xPos += (randNum1 - randNum2);
                yPos += (randNum3 - randNum4);
                level++;
                health--;
            }
        }
    }
    /**
     * Returns whether or not this Pokemon is colliding with a given Pokemon.
     * This should be determined by the Pokemon's location and the dimensions of
     * its image.
     * @param other the Pokemon to check for collision with
     * @return true if the two Pokemon have collided, false otherwise
     */
    public boolean collidesWithPokemon(Pokemon other) {
        int widthImage = this.image.getIconWidth();
        int heightImage = this.image.getIconHeight();
        int widthImageOther = other.image.getIconWidth();
        int heightImageOther = other.image.getIconHeight();
        Rectangle recThis = new Rectangle(xPos, yPos, widthImage, heightImage);
        Rectangle recOther = new Rectangle(other.xPos, other.yPos,
            widthImageOther, heightImageOther);
        if (recThis.intersects(recOther)) {
            return true;
        }
        return false;
    }

    /**
     * Returns whether or not the two Pokemon can reproduce
     * @param other the Pokemon to check if this can reproduce with
     * @return true if the two Pokemon can reproduce, false otherwise
     */
    public abstract boolean canReproduceWithPokemon(Pokemon other);

    /**
     * If the two Pokemon are able to reproduce (as determined by
     * canReproduceWithPokemon(Pokemon)), this method returns a new Pokemon of
     * the same type and in the same location. If for some reason, reproduction
     * does not happen, null should be returned.
     * Reproduction rates should be controlled in some manner so that infinite
     * population growth does not occur. This can be acheived in a variety of
     * ways: reproduction probability, limiting total offspring per
     * Pokemon, etc.
     * @param other the Pokemon to reproduce with
     * @return the offspring Pokemon
     */
    public abstract Pokemon reproduceWithPokemon(Pokemon other);

    /**
     * Returns whether or not a Pokemon has surpassed some determined max level.
     * @return true if the Pokemon has passed the specified level,
     *              false otherwise
     */
    public abstract boolean isOld();

    /**
    * Returns whether or not a Pokemon can damage another Pokemon
    * @param other the Pokemon for which to check if harming is possible
    * @return true if this can harm Pokemon a, false otherwise
    */
    public abstract boolean canHarmPokemon(Pokemon other);

    /**
    * Harms another instance of a Pokemon by decreasing its health by a fixed
    * amount.
    * @param other the Pokemon to harm
    */
    public void harmPokemon(Pokemon other) {
        if (canHarmPokemon(other)) {
            other.health -= 2;
        }
    }

    /**
     * Called when an Pokemon faints.
     */
    public void faint() {
        health = 0;
    }

    /**
     * Returns whether or not the Pokemon has fainted (ie health 0).
     * @return true if this Pokemon has fainted, false otherwise
     */
    public boolean hasFainted() {
        if (health <= 0 || isOld()) {
            return true;
        }
        return false;
    }

}

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * This is the ControlPanel for PokeBattle. It allows the
 * user to pick which Pokemon it would like to add next.
 *
 * @author Sundeep, Ethan, Heather
 * @version 1.0
 */
public class ControlPanel extends JPanel {
    private JButton blaziken, poliwhirl, torterra, custom1, custom2;

    private JLabel current;

    private String pokemonSpecies;

    public ControlPanel() {
        setPreferredSize(new Dimension(150, PokeWorld.HEIGHT));

        blaziken = new JButton("Blaziken");
        blaziken.addActionListener(new ButtonListener("Blaziken"));
        add(blaziken);

        poliwhirl = new JButton("Poliwhirl");
        poliwhirl.addActionListener(new ButtonListener("Poliwhirl"));
        add(poliwhirl);

        torterra = new JButton("Torterra");
        torterra.addActionListener(new ButtonListener("Torterra"));
        add(torterra);

        custom1 = new JButton("Pikachu");
        custom1.addActionListener(new ButtonListener("Pikachu"));
        add(custom1);

        custom2 = new JButton("Flareon");
        custom2.addActionListener(new ButtonListener("Flareon"));
        add(custom2);

        //default starting pokemon
        pokemonSpecies = "Blaziken";
        add(new JLabel("Current Pokemon"));
        current = new JLabel("Blaziken");
        add(current);

    }

    /**
     * Invoked by PokeWorld to determine which Pokemon
     * was chosen
     *
     * @return The currently selected Pokemon species
     */
    public String getPokemonSpecies() {
        return pokemonSpecies;
    }

    /**
     * Listener for clicks on the Pokemon switcher buttons
     */
    public class ButtonListener implements ActionListener {
        private String name;

        public ButtonListener(String className) {
            name = className;
        }

        public void actionPerformed(ActionEvent e) {
            pokemonSpecies = name;
            current.setText(name);
        }
    }
}

import javax.swing.JPanel;
import javax.swing.JProgressBar;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.plaf.DimensionUIResource;
import java.awt.GridBagConstraints;

class BattlePanel extends JPanel{
    private JButton AttackBtn;
    private JButton PokemonBtn;
    private JButton BagBtn;
    private JButton RunBtn;
    private JLabel BattleBackground;
    private JLabel Pokemon1;
    private JLabel Pokemon2;
    private JLabel Pokemon1Stats;
    private JLabel Pokemon2Stats;
    private GridBagConstraints gbc = new GridBagConstraints();
    private JProgressBar healthbar1;
    private JProgressBar healthbar2;

    public BattlePanel(){
        setLayout(new GridBagLayout());
        setupLbls();
        setupBtns();
    }

    private void setupLbls(){
        ImageIcon BackgroundPic = new ImageIcon(new ImageIcon("BattleBackground.jpg").getImage().getScaledInstance(700, 550, java.awt.Image.SCALE_SMOOTH));
        ImageIcon poke1 = new ImageIcon(new ImageIcon(PokemonGame.p1_pokemon_out.getBackImagePath().toString()).getImage().getScaledInstance(250, 250, java.awt.Image.SCALE_SMOOTH));
        ImageIcon poke2 = new ImageIcon(new ImageIcon(PokemonGame.p2_pokemon_out.getFrontImagePath().toString()).getImage().getScaledInstance(250, 250, java.awt.Image.SCALE_SMOOTH));

        BattleBackground = new Label(null, BackgroundPic, null);
        Pokemon1 = new Label(null, poke1, null);
        Pokemon2 = new Label(null, poke2, null);
        Pokemon1Stats = new Label(PokemonGame.p1_pokemon_out.fullStatus(), null, new DimensionUIResource(175, 100));
        Pokemon2Stats = new Label(PokemonGame.p2_pokemon_out.fullStatus(), null, new DimensionUIResource(175, 100));

        setupHealthbar();

        gbc.insets = new Insets(350, 0, 0, 0);
        add(Pokemon1Stats, gbc);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 55, 450, 0);
        add(Pokemon2Stats, gbc);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(200, 0, 0, 400);
        add(Pokemon1, gbc);
        gbc.insets = new Insets(0, 400, 300, 0);
        add(Pokemon2, gbc);
        gbc.insets = new Insets(0, 0, 100, 0);
        add(BattleBackground, gbc);
    }

    private void setupBtns(){
        AttackBtn = new BattleButton("Attack");
        BagBtn = new BattleButton("Bag");  
        PokemonBtn = new BattleButton("Pokemon");
        RunBtn = new BattleButton("Run");

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(-200, 0, 0, 350);
        add(AttackBtn, gbc);
        gbc.insets = new Insets(-200, 350, 0, 0);
        add(BagBtn, gbc);
        gbc.insets = new Insets(0, 0, 0, 350);
        add(PokemonBtn, gbc);
        gbc.insets = new Insets(0, 350, 0, 0);
        add(RunBtn, gbc);
    }

    private void setupHealthbar(){
        healthbar1 = new ProgressBar(0, PokemonGame.p1_pokemon_out.getOriginalhp(), PokemonGame.p1_pokemon_out);
        healthbar2 = new ProgressBar(0, PokemonGame.p2_pokemon_out.getOriginalhp(), PokemonGame.p2_pokemon_out);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 28, 471, 0);
        add(healthbar2, gbc);
        gbc.insets = new Insets(329, 0, 0, 27);
        add(healthbar1, gbc);
    }

}

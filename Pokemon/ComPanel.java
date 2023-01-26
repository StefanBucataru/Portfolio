import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.plaf.DimensionUIResource;

import java.awt.Color;
import java.awt.GridBagConstraints;

class ComPanel extends JPanel{
    private JButton NextBtn;
    private JLabel BattleBackground;
    private JLabel Pokemon1;
    private JLabel Pokemon2;
    private JLabel Pokemon1Stats;
    private JLabel Pokemon2Stats;
    private JLabel Message;
    private GridBagConstraints gbc = new GridBagConstraints();
    private JProgressBar healthbar1;
    private JProgressBar healthbar2;

    public ComPanel(){
        setLayout(new GridBagLayout());
    }

    public void setup(){
        setupBtns();
        setupLbls();
    }

    private void setupLbls(){
        ImageIcon BackgroundPic = null;
        ImageIcon poke1 = null;
        ImageIcon poke2 = null;

        BattleBackground = new JLabel();
        Pokemon1 = new JLabel();
        Pokemon2 = new JLabel();
        Pokemon1Stats = new JLabel();
        Pokemon2Stats = new JLabel();
        Message = new JLabel("", SwingConstants.CENTER);

        BackgroundPic = new ImageIcon("BattleBackground.jpg");
        poke1 = new ImageIcon(PokemonGame.p1_pokemon_out.getBackImagePath().toString());
        poke2 = new ImageIcon(PokemonGame.p2_pokemon_out.getFrontImagePath().toString());

        BackgroundPic = new ImageIcon(BackgroundPic.getImage().getScaledInstance(700, 550, java.awt.Image.SCALE_SMOOTH));
        poke1 = new ImageIcon(poke1.getImage().getScaledInstance(250, 250, java.awt.Image.SCALE_SMOOTH));
        poke2 = new ImageIcon(poke2.getImage().getScaledInstance(250, 250, java.awt.Image.SCALE_SMOOTH));

        setupHealthbar();

        Message.setPreferredSize(new DimensionUIResource(700, 200));
        Pokemon1Stats.setPreferredSize(new DimensionUIResource(175, 100));
        Pokemon2Stats.setPreferredSize(new DimensionUIResource(175, 100));
        Pokemon1Stats.setText(PokemonGame.p1_pokemon_out.fullStatus());
        Pokemon2Stats.setText(PokemonGame.p2_pokemon_out.fullStatus());

        Pokemon1.setIcon(poke1);
        Pokemon2.setIcon(poke2);
        BattleBackground.setIcon(BackgroundPic);
        
        Message.setOpaque(true);
        Message.setBackground(Color.decode("#00afff"));

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets =  new Insets(-100, 0, 0, 0);
        add(Message, gbc);
        gbc.insets = new Insets(350, 0, 0, 0);
        gbc.gridy = 0;
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
        NextBtn = new JButton();
        
        NextBtn.setText("Next");
        NextBtn.setPreferredSize(new DimensionUIResource(100, 50));
        NextBtn.addActionListener((e) -> {PokemonGame.AiTurn();});

        gbc.gridx = 0; 
        gbc.gridy = 1;
        gbc.insets = new Insets(50, 600, 0, 0);
        add(NextBtn, gbc);
    }

    public void setPassMessage(String person){
        if (person.equals("Computer")){
            NextBtn.removeActionListener(NextBtn.getActionListeners()[0]);
            NextBtn.addActionListener((e) -> {
            JPanel BattlePanel = new BattlePanel();
            GUI.panels.add(BattlePanel, "BattleMenu");
            GUI.c.show(GUI.panels, "BattleMenu");
            });
        }
        Message.setText(String.format("%s passed.", person));
    }

    public void setMessageDeath(Pokemon pokeout, Pokemon pokein){
        Message.setText(String.format("%s died and was swaped for %s.", pokeout.getName(), pokein.getName()));
    }

    public void setMessage(Pokemon pokeout, Pokemon pokein){
        Message.setText(String.format("%s was swaped for %s.", pokeout.getName(), pokein.getName()));
        if (pokein.equals(PokemonGame.p2_pokemon_out)){
            NextBtn.removeActionListener(NextBtn.getActionListeners()[0]);
            NextBtn.addActionListener((e) -> {
            JPanel BattlePanel = new BattlePanel();
            GUI.panels.add(BattlePanel, "BattleMenu");
            GUI.c.show(GUI.panels, "BattleMenu");
            });
        }
    }

    public void setMessage(Item item, Pokemon poke){
        Message.setText(String.format("You used %s to heal %s.", item.getName(), poke.getName()));
    }

    public void setMessage(Move Attack, Pokemon attacker, int damage){
        Message.setText(String.format("%s used %s dealing %d damage!", attacker.getName(), Attack.getName(), damage));
        if (attacker.equals(PokemonGame.p2_pokemon_out) && PokemonGame.p1_pokemon_out.getHp() == 0){
            NextBtn.removeActionListener(NextBtn.getActionListeners()[0]);
            NextBtn.addActionListener((e) -> {
                JPanel forceSwapPanel = new ForceSwapPanel();
                GUI.panels.add(forceSwapPanel, "forceSwapMenu");
                GUI.c.show(GUI.panels, "forceSwapMenu");
            });
        }
        else if (attacker.equals(PokemonGame.p2_pokemon_out)){
            NextBtn.removeActionListener(NextBtn.getActionListeners()[0]);
            NextBtn.addActionListener((e) -> {
            JPanel BattlePanel = new BattlePanel();
            GUI.panels.add(BattlePanel, "BattleMenu");
            GUI.c.show(GUI.panels, "BattleMenu");
            });
        }
    }

    public void setMessage(String item, Pokemon poke){
        Message.setText(String.format("Computer used %s to heal %s", item, poke));
        if (poke.equals(PokemonGame.p2_pokemon_out)){
            NextBtn.removeActionListener(NextBtn.getActionListeners()[0]);
            NextBtn.addActionListener((e) -> {
            JPanel BattlePanel = new BattlePanel();
            GUI.panels.add(BattlePanel, "BattleMenu");
            GUI.c.show(GUI.panels, "BattleMenu");
            });
        }
    }

    private void setupHealthbar(){
        healthbar1 = new JProgressBar(0, PokemonGame.p1_pokemon_out.getOriginalhp());
        healthbar2 = new JProgressBar(0, PokemonGame.p2_pokemon_out.getOriginalhp());

        healthbar1.setPreferredSize(new DimensionUIResource(100, 12));
        healthbar1.setValue(PokemonGame.p1_pokemon_out.getHp());
        healthbar1.setStringPainted(true);
        healthbar1.setString(String.format("%d/%d", PokemonGame.p1_pokemon_out.getHp(), PokemonGame.p1_pokemon_out.getOriginalhp()));
        healthbar1.setBackground(Color.decode("#4e5a59"));

        healthbar2.setPreferredSize(new DimensionUIResource(100, 12));
        healthbar2.setValue(PokemonGame.p2_pokemon_out.getHp());
        healthbar2.setStringPainted(true);
        healthbar2.setString(String.format("%d/%d", PokemonGame.p2_pokemon_out.getHp(), PokemonGame.p2_pokemon_out.getOriginalhp()));
        healthbar2.setBackground(Color.decode("#4e5a59"));

        setupHealthbarColor();

        gbc.gridx = 0;
        gbc.gridy = 0;

        gbc.insets = new Insets(0, 28, 471, 0);
        add(healthbar2, gbc);

        gbc.insets = new Insets(329, 0, 0, 27);
        add(healthbar1, gbc);
    }

    private void setupHealthbarColor(){
        if (Float.valueOf(healthbar1.getValue())/Float.valueOf(PokemonGame.p1_pokemon_out.getOriginalhp()) > 0.75){
            healthbar1.setForeground(Color.decode("#66e797"));
        }
        else if (Float.valueOf(healthbar1.getValue())/Float.valueOf(PokemonGame.p1_pokemon_out.getOriginalhp()) > 0.50){
            healthbar1.setForeground(Color.decode("#DAE000"));
        }
        else if (Float.valueOf(healthbar1.getValue())/Float.valueOf(PokemonGame.p1_pokemon_out.getOriginalhp()) > 0.25){
            healthbar1.setForeground(Color.decode("#EB7B04"));
        }
        else{
            healthbar1.setForeground(Color.decode("#EB4104"));
        }

        if (Float.valueOf(healthbar2.getValue())/Float.valueOf(PokemonGame.p2_pokemon_out.getOriginalhp()) > 0.75){
            healthbar2.setForeground(Color.decode("#66e797"));
        }
        else if (Float.valueOf(healthbar2.getValue())/Float.valueOf(PokemonGame.p2_pokemon_out.getOriginalhp()) > 0.50){
            healthbar2.setForeground(Color.decode("#DAE000"));
        }
        else if (Float.valueOf(healthbar2.getValue())/Float.valueOf(PokemonGame.p2_pokemon_out.getOriginalhp()) > 0.25){
            healthbar2.setForeground(Color.decode("#EB7B04"));
        }
        else{
            healthbar2.setForeground(Color.decode("#EB4104"));
        }
    }
}

import java.nio.file.Paths;

import javax.swing.JButton;
import javax.swing.plaf.DimensionUIResource;

class AttackButton extends JButton{ 

    public AttackButton(int move){
        setText(String.format("<html>%s<img src=\"file:"+Paths.get("Types", PokemonGame.p1_pokemon_out.getMoves()[move].getType() + ".png").toString()+"\" width=25 height=25></html>", PokemonGame.p1_pokemon_out.getMoves()[move].toString()));
        addActionListener((e) -> {PokemonGame.attack(PokemonGame.p1_pokemon_out, PokemonGame.p2_pokemon_out, move, 0);});
        setPreferredSize(new DimensionUIResource(350, 100));
    }
}
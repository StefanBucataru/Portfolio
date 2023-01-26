import java.nio.file.Paths;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;

class ForceSwapButton extends JButton{
    public ForceSwapButton(int poke){
            setText(String.format("<html>%s %s/%s Hp<img src=\"file:"+Paths.get("Types", PokemonGame.players[0].getPokemons().get(poke).getType() + ".png").toString()+"\" width=25 height=25></html>", PokemonGame.players[0].getPokemons().get(poke).getName(), PokemonGame.players[0].getPokemons().get(poke).getHp(), PokemonGame.players[0].getPokemons().get(poke).getOriginalhp()));
            if (PokemonGame.players[0].getPokemons().get(poke).getHp() <= 0){
                setEnabled(false);
            }
            addActionListener((e) -> {
                Pokemon temp = PokemonGame.p1_pokemon_out;
                PokemonGame.p1_pokemon_out = PokemonGame.players[0].getPokemons().get(poke);
                PokemonGame.players[0].getPokemons().set(poke, temp); 
                JPanel BattlePanel = new BattlePanel(); 
                GUI.panels.add(BattlePanel, "BattleMenu"); 
                GUI.c.show(GUI.panels, "BattleMenu");});

        setPreferredSize(new DimensionUIResource(350, 100));
    }

    public ForceSwapButton(){
        setText(String.format("<html>%s %s/%s Hp<img src=\"file:"+Paths.get("Types", PokemonGame.p1_pokemon_out.getType() + ".png").toString()+"\" width=25 height=25></html>", PokemonGame.p1_pokemon_out.getName(), PokemonGame.p1_pokemon_out.getHp(), PokemonGame.p1_pokemon_out.getOriginalhp()));
        if (PokemonGame.p1_pokemon_out.getHp() <= 0){
            setEnabled(false);
        }
    }
}

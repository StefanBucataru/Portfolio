import java.nio.file.Paths;
import javax.swing.JButton;
import javax.swing.plaf.DimensionUIResource;

class PokemonButton extends JButton{

    public PokemonButton(int poke){
        setText(String.format("<html>%s %s/%s Hp<img src=\"file:"+Paths.get("Types", PokemonGame.players[0].getPokemons().get(poke).getType() + ".png").toString()+"\" width=25 height=25></html>", PokemonGame.players[0].getPokemons().get(poke).getName(), PokemonGame.players[0].getPokemons().get(poke).getHp(), PokemonGame.players[0].getPokemons().get(poke).getOriginalhp()));
        if (PokemonGame.players[0].getPokemons().get(poke).getHp() <= 0){
            setEnabled(false);
        }
        addActionListener((e) -> {PokemonGame.swapPokePlayer(poke);});
        setPreferredSize(new DimensionUIResource(350, 100));
    }
    
}

import javax.swing.JButton;
import javax.swing.plaf.DimensionUIResource;

class BagButton extends JButton{
    
    public BagButton(int item){
        setText(String.format("<html>%s <img src=\"file:"+PokemonGame.players[0].getBag()[item].getImage().toString()+"\" width=25 height=25></html>", PokemonGame.players[0].getBag()[item].getName()));
            
            if (PokemonGame.players[0].getBag()[item].getEffect().equals("All") && !PokemonGame.p1_pokemon_out.getStatus().equals("Normal Status")){
                if (PokemonGame.players[0].getBag()[item].isUsed()){
                    setText(String.format("<html>%s <img src=\"file:"+PokemonGame.players[0].getBag()[item].getImage().toString()+"\" width=25 height=25> (Used)</html>", PokemonGame.players[0].getBag()[item].getName()));
                    setEnabled(false);
                }
            }
            else if (PokemonGame.players[0].getBag()[item].isUsed()){
                setEnabled(false);
                setText(String.format("<html>%s <img src=\"file:"+PokemonGame.players[0].getBag()[item].getImage().toString()+"\" width=25 height=25> (Used)</html>", PokemonGame.players[0].getBag()[item].getName()));
            }
            else if (!PokemonGame.players[0].getBag()[item].getEffect().equals(PokemonGame.p1_pokemon_out.getStatus())){
                setEnabled(false);
                setText(String.format("<html>%s <img src=\"file:"+PokemonGame.players[0].getBag()[item].getImage().toString()+"\" width=25 height=25> (Wrong Cure)</html>", PokemonGame.players[0].getBag()[item].getName()));
            }
            
        addActionListener((e) -> {PokemonGame.useItem(item);});
        setPreferredSize(new DimensionUIResource(350, 100));
    }
}

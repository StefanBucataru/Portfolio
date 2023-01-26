import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;

class BattleButton extends JButton{
    
    public BattleButton(String text){
        setText(text);
        if (text.equals("Attack")){
            AttackBtn();
        }
        else if (text.equals("Bag")) {
            BagBtn();
        }
        else if (text.equals("Pokemon")){
            PokemonBtn();
        }
        else if (text.equals("Run")){
            RunBtn();
        }
        setPreferredSize(new DimensionUIResource(350, 100));
    }

    private void AttackBtn(){
        if(!PokemonGame.p1_pokemon_out.getStatus().equals("Normal Status")){
            addActionListener((e) -> {
            ComPanel ComMenu = new ComPanel();
            ComMenu.setup();
            ComMenu.setPassMessage("You");
            GUI.panels.add(ComMenu, "ComMenu");
            GUI.c.show(GUI.panels, "ComMenu");});

            if (PokemonGame.p1_pokemon_out.getStatuswait() + 1 >= 3){
                PokemonGame.p1_pokemon_out.setStatus(null);
                PokemonGame.p1_pokemon_out.setStatuswait(0);
            }
            else{
                PokemonGame.p1_pokemon_out.setStatuswait(PokemonGame.p1_pokemon_out.getStatuswait()+1);
            }
            setText("Pass");
        }
        else{
            addActionListener((e) -> {
                JPanel AttackPanel = new AttackPanel();
                GUI.panels.add(AttackPanel, "AttackMenu");
                GUI.c.show(GUI.panels, "AttackMenu");
            });
            setText("Attack");
        }
    }

    private void BagBtn(){
        addActionListener((e) -> {
            JPanel BagPanel = new BagPanel();
            GUI.panels.add(BagPanel, "BagMenu");
            GUI.c.show(GUI.panels, "BagMenu");});
    }

    private void PokemonBtn(){
        addActionListener((e) -> {
            JPanel PokemonPanel = new PokemonPanel();
            GUI.panels.add(PokemonPanel, "PokemonMenu");
            GUI.c.show(GUI.panels, "PokemonMenu");});
    }

    private void RunBtn(){
        addActionListener((e) -> {System.exit(0);});
    }
}

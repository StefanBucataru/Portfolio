import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;

class StartButton extends JButton{
    
    public StartButton(String name){
        if (name.equals("Battle!")){
            addActionListener(e -> {JPanel BattlePanel = new BattlePanel(); GUI.panels.add(BattlePanel, "BattleMenu"); GUI.c.show(GUI.panels, "BattleMenu");});
        }
        else{
            addActionListener(e -> {System.exit(0);});
        }
        setText(name);
        setPreferredSize(new DimensionUIResource(350, 50));
    }
}

import javax.swing.JButton;
import javax.swing.plaf.DimensionUIResource;

class BackButton extends JButton{
    
    public BackButton(){
        setText("Back");
        addActionListener((e) -> {GUI.c.show(GUI.panels, "BattleMenu");});
        setPreferredSize(new DimensionUIResource(350, 100));
    }
}

import javax.swing.JProgressBar;
import javax.swing.plaf.DimensionUIResource;
import java.awt.Color;

class ProgressBar extends JProgressBar{
    
    public ProgressBar(int min, int original, Pokemon poke){
        super(min, original);
        setPreferredSize(new DimensionUIResource(100, 12));
        setValue(poke.getHp());
        setStringPainted(true);
        setString(String.format("%d/%d", poke.getHp(), poke.getOriginalhp()));
        setBackground(Color.decode("#4e5a59"));
        if (Float.valueOf(getValue())/Float.valueOf(poke.getOriginalhp()) > 0.75){
            setForeground(Color.decode("#66e797"));
        }
        else if (Float.valueOf(getValue())/Float.valueOf(poke.getOriginalhp()) > 0.50){
            setForeground(Color.decode("#DAE000"));
        }
        else if (Float.valueOf(getValue())/Float.valueOf(poke.getOriginalhp()) > 0.25){
            setForeground(Color.decode("#EB7B04"));
        }
        else{
            setForeground(Color.decode("#EB4104"));
        }
    }
}

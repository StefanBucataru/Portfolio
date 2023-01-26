import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.plaf.DimensionUIResource;

class Label extends JLabel{
    
    public Label(String text, ImageIcon icon, DimensionUIResource dim){
        setText(text);
        setIcon(icon);
        setPreferredSize(dim);
    }
}

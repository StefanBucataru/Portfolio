import javax.swing.JFrame;
import java.awt.Toolkit;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.JPanel;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.CardLayout; 

class GUI extends JFrame{
    
    public static CardLayout c = new CardLayout();
    public static JPanel panels = new JPanel(c);

    public GUI(){
        //Setting color of progress bars
        UIManager.put("ProgressBar.selectionForeground", Color.BLACK);
        UIManager.put("ProgressBar.selectionBackground", Color.WHITE);
        setTitle("Pok\u00E9mon Battle");
        setIconImage(Toolkit.getDefaultToolkit().getImage(randomIcon().toString()));
        setSize(700, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        panels.add(new StartScreen(), "Startmenu");
        c.show(panels, "Startmenu");
        add(panels);
        pack();
    }

    private Path randomIcon(){
        int ran = ThreadLocalRandom.current().nextInt(1, 8);
        return Paths.get("Icons", String.valueOf(ran) + ".png");
    }
}


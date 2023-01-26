import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;



class StartScreen extends JPanel{
    private Label image;
    private JButton StartBtn;
    private JButton QuitBtn;
    private GridBagConstraints gbc = new GridBagConstraints(); 
    
    public StartScreen(){
        setLayout(new GridBagLayout());
        setupLbls();
        setupBtns();
    }
    
    private void setupLbls(){
        ImageIcon pic = new ImageIcon("StartScreen.png");
        pic = new ImageIcon(pic.getImage().getScaledInstance(700, 700, java.awt.Image.SCALE_SMOOTH));

        image = new Label(null, pic, null);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(image, gbc);
    }

    private void setupBtns(){
        StartBtn = new StartButton("Battle!");
        QuitBtn = new StartButton("Quit!");
        
        gbc.insets = new Insets(0, 0, 0, 350);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(StartBtn, gbc);
        gbc.insets = new Insets(0, 350, 0, 0);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(QuitBtn, gbc);
    }
}

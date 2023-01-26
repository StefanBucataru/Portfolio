import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;
import java.awt.Insets;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

class EndPanel extends JPanel{
    private JButton QuitBtn;
    private JLabel PokeBall;
    private JLabel Text;
    private String winner;
    private GridBagConstraints gbc = new GridBagConstraints();

    public EndPanel(String winner){
        this.winner = winner;
        setLayout(new GridBagLayout());
        setUpLbls();
        setupBtns();
    }

    private void setUpLbls(){
        PokeBall = new JLabel();
        Text = new JLabel();


        PokeBall.setIcon(new ImageIcon(new ImageIcon("EndBackground.jpg").getImage().getScaledInstance(700, 700, java.awt.Image.SCALE_SMOOTH)));
        Text.setPreferredSize(new DimensionUIResource(250, 25));
        Text.setFont(Text.getFont().deriveFont(25.0f));

        if (winner.equals("You")){
            Text.setText(String.format("<html><font color='#FFFFFF'>%s have won!</font></html>", winner));
        }
        else{
            Text.setText(String.format("<html><font color='#FFFFFF'>%s has won!</font></html>", winner));
        }

        gbc.insets = new Insets(0, 0, 0, 425);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(Text, gbc);
        gbc.insets = new Insets(0, 0, 0, 0);
        add(PokeBall, gbc);
    }

    private void setupBtns(){
        QuitBtn = new JButton("Quit!");
        QuitBtn.addActionListener(e -> {System.exit(0);});
        QuitBtn.setPreferredSize(new DimensionUIResource(700, 50));
    
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(QuitBtn, gbc);
    }
}
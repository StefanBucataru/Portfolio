import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.plaf.DimensionUIResource;
import java.awt.Color;
import java.awt.GridBagConstraints;

class ForceSwapPanel extends JPanel{
    private JButton Poke1Btn;
    private JButton Poke2Btn;
    private JButton Poke3Btn;
    private JButton Poke4Btn;
    private JLabel Message;
    private GridBagConstraints gbc = new GridBagConstraints();
    
    public ForceSwapPanel(){
        setLayout(new GridBagLayout());
        setupLbls();
        setupBtns();
    }

    private void setupLbls(){
        Message = new JLabel("", SwingConstants.CENTER);
        Message.setPreferredSize(new DimensionUIResource(700, 550));
        Message.setText("Your pok\u00E9mon has died! Please pick another one!");
        Message.setOpaque(true);
        Message.setBackground(Color.decode("#00afff"));
        Message.setFont(Message.getFont().deriveFont(29.0f));

        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.insets = new Insets(0, 0, 100, 0);
        add(Message, gbc);
    }

    private void setupBtns(){
        Poke1Btn = new ForceSwapButton(0);
        Poke2Btn = new ForceSwapButton(1);
        Poke3Btn = new ForceSwapButton(2);
        Poke4Btn = new ForceSwapButton();

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(-200, 0, 0, 350);
        add(Poke1Btn, gbc);
        gbc.insets = new Insets(-200, 350, 0, 0);
        add(Poke2Btn, gbc);

        gbc.insets = new Insets(0, 0, 0, 350);
        add(Poke3Btn, gbc);
        gbc.insets = new Insets(0, 350, 0, 0);
        add(Poke4Btn, gbc);
    }
}

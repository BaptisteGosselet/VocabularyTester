import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IGQuiz extends JFrame {
    class PauseThread extends Thread{
        public void run() {
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //
        }
    }
    
    JLabel letterLabel =new JLabel();
    JButton[] buttons = new JButton[4];
    private JButton newTestButton = new JButton("Nouveau Test");
    private JLabel scoreLabel = new JLabel("00/20");
    
    ButtonGroup modeGroup;
    ButtonGroup formatGroup;
    
    public IGQuiz(){
    
        //Frame Configuration
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 400);
        this.setTitle("Vocabulary Tester");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        setLayout(null);
        Container c=getContentPane();
                
        // Label
        letterLabel.setText("Vocabulaire");
        letterLabel.setBounds(100, 50, 300, 40);
        letterLabel.setHorizontalAlignment(JLabel.CENTER);
        letterLabel.setVerticalAlignment(JLabel.CENTER);
        letterLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        c.add(letterLabel);
    
        // GridLayout
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(100, 150, 300, 150);
        buttonPanel.setLayout(new GridLayout(2, 2, 10, 10));
        c.add(buttonPanel);
    
        // Boutons
        for (int i = 0; i < 4; i++) {
            buttons[i] = new JButton("Réponse " + (i + 1));
            buttonPanel.add(buttons[i]);
            buttons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Button '" + ((JButton)e.getSource()).getText() + "' was clicked");
                }
            });
        }
    
        // Bouton nouveau test
        newTestButton.setBounds(10, 330, 150, 30);
        newTestButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(((JButton) e.getSource()).getText());
        }
        });
        c.add(newTestButton);
    
        // Label score
        scoreLabel.setBounds(350, 330, 100, 30);
        c.add(scoreLabel);
    
    
    
        this.setVisible(true);
    
    }

}
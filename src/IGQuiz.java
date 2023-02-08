import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class IGQuiz extends JFrame {
    
    JLabel letterLabel =new JLabel();
    JButton[] buttons = new JButton[4];
    private JButton newTestButton = new JButton("Nouveau Test");
    private JButton resetButton = new JButton("Recommencer");
    private JLabel scoreLabel = new JLabel();
    
    ButtonGroup modeGroup;
    ButtonGroup formatGroup;
    
    public IGQuiz(){
    
        //Frame Configuration
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(750, 600);
        this.setTitle("Vocabulary Tester");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        setLayout(null);
        Container c=getContentPane();
                
        // Label
        letterLabel.setText("V. Tester");
        letterLabel.setFont(new Font("MS Gothic", Font.BOLD, 40));
        letterLabel.setHorizontalAlignment(JLabel.CENTER);
        letterLabel.setVerticalAlignment(JLabel.CENTER);
        c.add(letterLabel);
    
        // GridLayout
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 2, 10, 10));
        c.add(buttonPanel);
    
        // Boutons
        for (int i = 0; i < 4; i++) {
            buttons[i] = new JButton("Réponse " + (i + 1));
            buttons[i].setFont(new Font("MS Mincho", Font.PLAIN, 18));
            buttonPanel.add(buttons[i]);
        }

        buttons[0].addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {clickOnButton(0);}});
        buttons[1].addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {clickOnButton(1);}});
        buttons[2].addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {clickOnButton(2);}});
        buttons[3].addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {clickOnButton(3);}});
    
        // Bouton nouveau test
        newTestButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            newTest();
        }
        });
        c.add(newTestButton);

        // Bouton reset test
        resetButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(((JButton) e.getSource()).getText());
        }
        });
        c.add(resetButton);
    
        // Label score
        c.add(scoreLabel);
        
        //Bounds
        letterLabel.setBounds(150, 75, 450, 60);
        buttonPanel.setBounds(150, 225, 450, 225);
        newTestButton.setBounds(15, 495, 225, 45);
        resetButton.setBounds(255, 495, 225, 45);
        scoreLabel.setBounds(525, 495, 150, 45);

        this.setVisible(true);
    
    }

    private void clickOnButton(int index) {
        System.out.println(index);
        for (JButton button : this.buttons) {
            button.setEnabled(false);
            button.setBackground(Color.GREEN);
        }

        new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetButton();
                ((Timer) e.getSource()).stop();
            }
        }).start();
    }
    
    private void resetButton() {
        for (JButton button : this.buttons) {
            button.setEnabled(true);
            button.setBackground(null);
        }
    }

    private void newTest() {
        JFileChooser fileChooser = new JFileChooser("./quiz");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text files", "txt"));
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String filePath = selectedFile.getAbsolutePath();
    
            String numberOfQuestions = JOptionPane.showInputDialog("Nombre de questions à générer : ");
            int numberOfQuestionsInt = Integer.parseInt(numberOfQuestions);
    
            System.out.println("Chemin du fichier sélectionné : " + filePath);
            System.out.println("Nombre de questions à générer : " + numberOfQuestionsInt);
        }
    }

}
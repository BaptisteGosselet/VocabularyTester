import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class IGQuiz extends JFrame {
    
    QuizController qc;

    JLabel letterLabel =new JLabel();
    JButton[] buttons = new JButton[4];
    private JButton newTestButton = new JButton("Nouveau Test");
    private JButton resetButton = new JButton("Recommencer");
    private JLabel scoreLabel = new JLabel();

    private Question currentQuestion; 
    
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
            buttons[i] = new JButton();
            buttons[i].setFont(new Font("MS Mincho", Font.PLAIN, 18));
            buttons[i].setEnabled(false);
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
            if(qc != null){
                qc.reinitQuestion();
                clickOnButton(-1);
            }
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

        int index_reponse = this.currentQuestion.getIndexReponse();
        if(index == index_reponse) this.qc.addPts();

        for(int i=0; i<this.buttons.length; i++){
            buttons[i].setEnabled(false);
            if(i == index_reponse) buttons[i].setBackground(Color.GREEN);
            else buttons[i].setBackground(Color.RED);
        }


        new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nextQuestion();
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
        this.qc = new QuizController();
        JFileChooser fileChooser = new JFileChooser("./quiz");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text files", "txt"));
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String filePath = selectedFile.getAbsolutePath();
            int number_of_words = qc.readFile(filePath);

            String numberOfQuestions = JOptionPane.showInputDialog("Nombre de questions à générer : ");
            int numberOfQuestionsInt = Integer.parseInt(numberOfQuestions);
    
            Object[] options = {"Recto / Verso", "Recto -> Verso", "Verso -> Recto"};
            int mode = JOptionPane.showOptionDialog(null, "Choisir le mode", "Mode", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
    
            qc.genererQuestion(numberOfQuestionsInt, mode);
            nextQuestion();
        }
    }

    private void nextQuestion(){
        this.currentQuestion = this.qc.next();        
        if(this.currentQuestion != null){
            this.letterLabel.setText(currentQuestion.getQuestion());
            for(int i=0;i<buttons.length ;i++){
                buttons[i].setText(currentQuestion.getChoix(i));
            }
            resetButton();
        }
        scoreLabel.setText(qc.getScore());
    }
    
}
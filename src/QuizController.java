import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class QuizController {

    int cpt = 0;
    int nbQuestion;
    int mode;

    ArrayList<Mot> mots;
    Question[] questions;
    
    public QuizController(String filename, int nQuestion, int nMode){
        this.nbQuestion = nQuestion;
        this.mode = nMode;
        this.questions = new Question[nbQuestion];
        this.mots = new ArrayList<Mot>();

        lireQuestionnaire(filename);
    }

    public void lireQuestionnaire(String filename){
        
        Scanner sc;
        try {
            sc = new Scanner(new File(filename));
            sc.useDelimiter("#|\n");
            String m1;
            String m2;
            while(sc.hasNext()){
                m1 = sc.next();
                m2 = sc.next();
                this.mots.add(new Mot(m1, m2));
            }

            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    
}

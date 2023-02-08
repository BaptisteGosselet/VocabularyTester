import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class QuizController {

    int cpt = 0;
    int pts = 0;
    int mode;

    ArrayList<Mot> mots;
    Question[] questions;
    
    public QuizController(){
    }

    public int readFile(String filename){
        this.mots = new ArrayList<Mot>();
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
        return this.mots.size();
    }

    public void genererQuestion(int nQuestion, int nMode){
        this.questions = new Question[nQuestion];
        this.mode = nMode;

        for(int i=0; i<this.questions.length; i++){
            ArrayList<Mot> copy = new ArrayList<Mot>();
            copy.addAll(mots);
            Collections.shuffle(copy);
            this.questions[i] = new Question(copy.get(0),copy.get(1),copy.get(2),copy.get(3), mode);
        }
    }

    public void reinitQuestion(){
        this.genererQuestion(this.questions.length, this.mode);
        cpt = 0;
        pts = 0;
    }

    public Question next(){
        Question q = null;
        if(cpt < this.questions.length){
            q = this.questions[cpt++];
        }
        return q;
    }
    

    
}

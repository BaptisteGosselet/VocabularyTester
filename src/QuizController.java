public class QuizController {

    int cpt = 0;
    int nbQuestion;
    int mode;
    
    public QuizController(String filename, int nQuestion, int nMode){
        this.nbQuestion = nQuestion;
        this.mode = nMode;
        lireQuestionnaire(filename);
    }

    public void lireQuestionnaire(String filename){
        System.out.println(filename);
    }

    

}

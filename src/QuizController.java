public class QuizController {

    int cpt = 0;
    int nbQuestion;
    
    public QuizController(String filename, int nQuestion){
        this.nbQuestion = nQuestion;
        lireQuestionnaire(filename);
    }

    public void lireQuestionnaire(String filename){
        System.out.println(filename);
    }

    

}

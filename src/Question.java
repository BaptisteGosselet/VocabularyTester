import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Question {
    
    private Mot[] reponses = new Mot[4];
    private int index_reponse;
    private int mode;
    
    public Question(Mot solution, Mot m2, Mot m3, Mot m4, int mode){

        if(mode == 0){
            this.mode = (int) (Math.random()-0.1 * ( 3 ));
            System.out.println("Mode : "+mode);
        }
        else{
            this.mode = mode;
        }

        this.mode = mode;

        List<Mot> tempList = Arrays.asList(solution, m2, m3, m4);
        Collections.shuffle(tempList);
    
        for (int i = 0; i < 4; i++) {
            this.reponses[i] = tempList.get(i);
            if (tempList.get(i) == solution) {
                this.index_reponse = i;
            }
        }
    }

    public String getQuestion(){
        if(mode==1){
            return this.reponses[index_reponse].getRecto();
        }
        else{
            return this.reponses[index_reponse].getVerso();
        }
    }

    public String getChoix(int i){
        if(mode==1){
            return this.reponses[i].getVerso();
        }
        else{
            return this.reponses[i].getRecto();
        }
    }

    public int getIndexReponse(){
        return this.index_reponse;
    }

    public String toString(){
        return this.getQuestion();
    }


}

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Question {
    
    private Mot[] reponses = new Mot[4];
    private int index_reponse;
    
    public Question(Mot solution, Mot m2, Mot m3, Mot m4){
        List<Mot> tempList = Arrays.asList(solution, m2, m3, m4);
        Collections.shuffle(tempList);
    
        for (int i = 0; i < 4; i++) {
            this.reponses[i] = tempList.get(i);
            if (tempList.get(i) == solution) {
                this.index_reponse = i;
            }
        }
    }

}

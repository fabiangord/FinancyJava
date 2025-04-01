package Models;

public class Investment{
    public String concept;
    public long goal; 

    public Investment(String concept, long goal) {
        this.concept = concept;
        this.goal = goal;
    }

    public String getConcept(){
        return concept;
    }

    public long getGoal(){
        return goal;
    }
}

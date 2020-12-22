package producer;

public class WorkState {
    private boolean workDone = false;

    public void finishWork(){
        workDone = true;
        System.out.println("Work finished");
    }

    public boolean getWorkInfo(){return workDone;}
}

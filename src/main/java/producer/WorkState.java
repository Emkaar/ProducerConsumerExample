package producer;

public class WorkState {
    private volatile boolean workDone = false;

    public void finishWork(){
        workDone = true;
        System.out.println("Work finished, finish processing and close");
    }

    public synchronized boolean workDone(){return workDone;}
}

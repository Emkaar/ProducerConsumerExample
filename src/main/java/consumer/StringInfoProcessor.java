package consumer;

import consumer.InfoProcessor;
import producer.FileToArrayReader;
import producer.WorkState;

import java.util.HashMap;
import java.util.concurrent.ArrayBlockingQueue;

public class StringInfoProcessor implements Runnable{
    ArrayBlockingQueue<String[]> arrayBlockingQueue;
    WorkState workState;

    public StringInfoProcessor(ArrayBlockingQueue arrayBlockingQueue, WorkState state) {
        this.arrayBlockingQueue = arrayBlockingQueue;
        this.workState = state;
    }


    public void process() {
        String[] processingArray;
        HashMap<String,HashMap<Character, Integer>> resultMap = new HashMap<>();
        try {
            processingArray = arrayBlockingQueue.take();
            System.out.println("I processing");
            for (int i = 0; i < processingArray.length; i++) {
                if(processingArray[i] == null){
                    continue;
                }
                HashMap<Character, Integer> resultMapValue = new HashMap<>();
                String resultMapKey = processingArray[i];
                for (int j = 0; j < resultMapKey.length(); j++) {
                    Integer charValue = resultMapValue.get(resultMapKey.charAt(j));
                    if(charValue == null){
                        charValue = 1;
                    }else {
                        charValue++;
                    }
                    resultMapValue.put(resultMapKey.charAt(j), charValue);
                }
                resultMap.put(resultMapKey, resultMapValue);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread());
        InformationPoint.addInfo(resultMap);
    }

    @Override
    public void run() {

//        while (!workState.getWorkInfo() && !arrayBlockingQueue.isEmpty()){
        while (true){
                System.out.println("consumer run started");
                process();
                System.out.println("I consume");
            }
    }
}

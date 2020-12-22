package consumer;

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
        System.out.println("Consumer run " + Thread.currentThread());
//        while (!workState.workDone() && !arrayBlockingQueue.isEmpty()){
        while (!arrayBlockingQueue.isEmpty() || !workState.workDone()){
//            synchronized (this) {
//                if (!arrayBlockingQueue.isEmpty() && workState.workDone()) {
//                    break;
//                }
//            }
                System.out.println("consumer started working " + Thread.currentThread());
                process();
                System.out.println("I consumed");
            }
        System.out.println("consumer close " + Thread.currentThread());
    }
}

package consumer;

import producer.WorkState;

import java.util.HashMap;
import java.util.concurrent.ArrayBlockingQueue;

public class StringInfoProcessor implements Runnable{
    private static Object lock = new Object();
    ArrayBlockingQueue<String[]> arrayBlockingQueue;
    WorkState workState;

    public StringInfoProcessor(ArrayBlockingQueue arrayBlockingQueue, WorkState state) {
        this.arrayBlockingQueue = arrayBlockingQueue;
        this.workState = state;
    }


    public void process() {
        while (true) {
            String[] processingArray = null;
            HashMap<String,HashMap<Character, Integer>> resultMap = new HashMap<>();
            try {
                synchronized (lock) {
                    if (!arrayBlockingQueue.isEmpty() || !workState.workDone()) {
                        System.out.println("try to take element " + Thread.currentThread());
                        processingArray = arrayBlockingQueue.take();
                    }
                }
                if (processingArray == null){
                    break;
                }
                System.out.println("I've got element and processing" + Thread.currentThread());
                for (int i = 0; i < processingArray.length; i++) {
                    if (processingArray[i] == null) {
                        continue;
                    }
                    HashMap<Character, Integer> resultMapValue = new HashMap<>();
                    String resultMapKey = processingArray[i];
                    for (int j = 0; j < resultMapKey.length(); j++) {
                        Integer charValue = resultMapValue.get(resultMapKey.charAt(j));
                        if (charValue == null) {
                            charValue = 1;
                        } else {
                            charValue++;
                        }
                        resultMapValue.put(resultMapKey.charAt(j), charValue);
                    }
                    resultMap.put(resultMapKey, resultMapValue);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            InformationPoint.addInfo(resultMap);
        }
        System.out.println(Thread.currentThread() + " consumer finished");
    }

    @Override
    public void run() {
        process();
    }
}

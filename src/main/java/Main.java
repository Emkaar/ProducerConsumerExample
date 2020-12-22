import consumer.InformationPoint;
import consumer.StringInfoProcessor;
import producer.FileToArrayReader;
import producer.WorkState;

import java.io.File;
import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue<String[]> arrayBlockingQueue = new ArrayBlockingQueue(10);
        WorkState state = new WorkState();
        FileToArrayReader fileToArrayReader = new FileToArrayReader(new File("test9346.txt"), arrayBlockingQueue, 50, state);
        ExecutorService executorService = Executors.newFixedThreadPool(11);
        executorService.submit(fileToArrayReader);
        for (int i = 0; i < 10; i++) {
            executorService.submit(new StringInfoProcessor(arrayBlockingQueue, state));
        }
        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.SECONDS);
//        Thread producer = new Thread(fileToArrayReader);
//        Thread consumer = new Thread(new StringInfoProcessor(arrayBlockingQueue, state));
//        producer.start();
//        consumer.start();
//
//        producer.join();
//        consumer.join();
        System.out.println(InformationPoint.getLiteralQuantity().toString());
        System.out.println(InformationPoint.getLiteralQuantity().size());

    }
}

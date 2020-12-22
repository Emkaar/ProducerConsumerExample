package producer;

import java.io.*;
import java.util.concurrent.ArrayBlockingQueue;


public class FileToArrayReader implements Runnable{

    private WorkState state;
    private File readingFile;
    private Integer readingRowQuantity = 100;
    private ArrayBlockingQueue<String[]> stringQueue;

    public FileToArrayReader(File readingFile, ArrayBlockingQueue<String[]> stringQueue, Integer readingRowQuantity, WorkState state) {
        this.readingFile = readingFile;
        this.readingRowQuantity = readingRowQuantity;
        this.stringQueue = stringQueue;
        this.state = state;
    }

    public FileToArrayReader(File readingFile, ArrayBlockingQueue<String[]> stringQueue) {
        this.readingFile = readingFile;
        this.stringQueue = stringQueue;
    }

    public synchronized String[] getStringArray() throws InterruptedException {
        return stringQueue.take();
    }

    public ArrayBlockingQueue<String[]> getStringQueue() {
        return stringQueue;
    }

    public void setReadingFile(File readingFile) {
        this.readingFile = readingFile;
    }

    public void setReadingRowQuantity(Integer readingRowQuantity) {
        this.readingRowQuantity = readingRowQuantity;
    }

    public void read() {
        try(BufferedReader reader = new BufferedReader(new FileReader(readingFile))){
            String line = reader.readLine();
            System.out.println("I started reading");
            while (line != null) {
                String[]newStringArray = new String[readingRowQuantity];
                for (int i = 0; i < readingRowQuantity; i++, line = reader.readLine()) {
                    if(line != null) {
                        newStringArray[i] = line;
                    }
                }
                System.out.println("I read new line");
                try {
                    //synchronized (stringQueue) {
                        if (line == null) {
//                            stringQueue.put(new String[]{"EXIT_CODE"});
                            state.finishWork();
                            System.out.println(stringQueue.size() + " queue size");
                        }
                        stringQueue.put(newStringArray);
                  //  }
                } catch (InterruptedException ex){
                    ex.printStackTrace();
                }
                System.out.println("queue size is " + stringQueue.size());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }



    @Override
    public void run() {
        read();
        System.out.println("producer finished");
    }
}

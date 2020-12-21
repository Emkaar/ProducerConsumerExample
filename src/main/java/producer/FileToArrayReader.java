package producer;

import java.io.*;
import java.util.concurrent.ArrayBlockingQueue;


public class FileToArrayReader{

    private static File readingFile = new File("text.txt");
    private static Integer readingRowQuantity = 100;
    private static ArrayBlockingQueue<String[]> stringQueue = new ArrayBlockingQueue<>(10);

    public static String[] getStringArray() throws InterruptedException {
        return stringQueue.take();
    }

    public static ArrayBlockingQueue<String[]> getStringQueue() {
        return stringQueue;
    }

    public static void setReadingFile(File readingFile) {
        FileToArrayReader.readingFile = readingFile;
    }

    public static void setReadingRowQuantity(Integer readingRowQuantity) {
        FileToArrayReader.readingRowQuantity = readingRowQuantity;
    }

    public static void read() {
        stringQueue.clear();
        try(BufferedReader reader = new BufferedReader(new FileReader(readingFile))){
            String line = reader.readLine();
            while (line != null) {
                String[]newStringArray = new String[readingRowQuantity];
                for (int i = 0; i < readingRowQuantity && line != null; i++, line = reader.readLine()) {
                        newStringArray[i] = line;
                }
                stringQueue.put(newStringArray);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}

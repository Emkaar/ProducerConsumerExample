import consumer.InformationPoint;
import consumer.StringInfoProcessor;
import producer.FileToArrayReader;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        FileToArrayReader.setReadingRowQuantity(3);
        StringInfoProcessor processor = new StringInfoProcessor();
        FileToArrayReader.read();
        processor.process();
        System.out.println(InformationPoint.getLiteralQuantity().toString());
    }
}

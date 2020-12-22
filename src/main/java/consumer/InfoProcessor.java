package consumer;

import producer.FileToArrayReader;

import java.util.List;

public interface InfoProcessor {
    void process(FileToArrayReader fileToArrayReader);
}

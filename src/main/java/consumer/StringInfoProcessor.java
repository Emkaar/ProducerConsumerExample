package consumer;

import consumer.InfoProcessor;
import producer.FileToArrayReader;

import java.util.HashMap;

public class StringInfoProcessor implements InfoProcessor {

    @Override
    public void process() {
        String[] processingArray;
        HashMap<String,HashMap<Character, Integer>> resultMap = new HashMap<>();
        try {
            processingArray = FileToArrayReader.getStringArray();
            for (int i = 0; i < processingArray.length; i++) {
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
        InformationPoint.addInfo(resultMap);
    }
}

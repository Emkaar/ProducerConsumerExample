package consumer;

import java.util.HashMap;
import java.util.Map;

public class InformationPoint {
    private volatile static HashMap<String, HashMap<Character, Integer>> literalQuantity = new HashMap<>();

    public static synchronized void addInfo(Map<String, HashMap<Character, Integer>> newInfoMap){
        literalQuantity.putAll(newInfoMap);
    }

    public static HashMap<String, HashMap<Character, Integer>> getLiteralQuantity() {
        return literalQuantity;
    }
}

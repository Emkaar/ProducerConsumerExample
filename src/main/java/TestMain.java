import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class TestMain {
    public static void main(String[] args) {
        Set<String> stringSet = new HashSet<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("newtext1.txt")));
            PrintWriter printWriter = new PrintWriter(new FileWriter("newtext1.txt", true));
            String line = bufferedReader.readLine();
            while (line != null){
                stringSet.add(line);
                line = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        System.out.println(stringSet.size());
    }
}

import java.io.*;
import java.util.Arrays;

public class TestMain {
    public static void main(String[] args) {
        String[]strings = new String[12];
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("text.txt")));
            String line = bufferedReader.readLine();
            for (int i = 0; i < 100 && line != null; i++, line = bufferedReader.readLine()) {

                    strings[i] = line;

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        System.out.println(Arrays.toString(strings));
    }
}

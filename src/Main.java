import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        SlangWord slangWord = new SlangWord();

        slangWord.readFromFile(SlangWord.FILE_COPY);


        long start1 = System.currentTimeMillis();
        String[][] data = slangWord.findByDefinition("homework");
        SlangWord.print2dArray(data);
        long stop1 = System.currentTimeMillis();
        System.out.println(stop1 - start1);

        System.out.println(slangWord.getAllKeys(data));
    }
}
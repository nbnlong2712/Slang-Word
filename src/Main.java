import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        SlangWord slangWord = new SlangWord();
        slangWord.readFromFile(SlangWord.FILE_COPY);
        //slangWord.readFromFile("test-copy.txt");


        /*long start1 = System.currentTimeMillis();
        String[][] data = slangWord.findByDefinition("homework");
        SlangWord.print2dArray(data);
        long stop1 = System.currentTimeMillis();
        System.out.println(stop1 - start1);

        System.out.println(slangWord.getAllKeys(data));*/

        //slangWord.addNew(">,<", "haha");

        //slangWord.saveAllToFile("test-copy.txt");

        System.out.println(slangWord.quizBySlangWord());

        //SlangWord.print2dArray(slangWord.convertData());
    }
}
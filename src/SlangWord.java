import java.io.*;
import java.util.*;

public class SlangWord {
    private TreeMap<String, List<String>> map = new TreeMap<>();
    private int sizeMap;
    public static final String FILE_ROOT = "slangword-goc.txt";
    public static final String FILE_COPY = "slangword.txt";
    public static final String FILE_HISTORY = "history.txt";

    //----------------CONSTRUCTOR---------------
    public SlangWord() {
    }

    public TreeMap<String, List<String>> getMap() {
        return map;
    }

    public void setMap(TreeMap<String, List<String>> map) {
        this.map = map;
    }

    public int getSizeMap() {
        return sizeMap;
    }

    public void setSizeMap(int sizeMap) {
        this.sizeMap = sizeMap;
    }

    //-------------------------------------------

    void readFromFile(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();
        sizeMap = 0;

        while ((line = reader.readLine()) != null) {
            if (!line.contains("`"))
                continue;
            String[] data = line.split("`");
            String key = data[0].trim();
            List<String> meaning = new ArrayList<>();
            if (map.containsKey(key))
                meaning = map.get(key);
            if (data[1].contains("|")) {
                Collections.addAll(meaning, data[1].split("\\|"));
                sizeMap += data[1].split("\\|").length;
            }
            else {
                meaning.add(data[1]);
                sizeMap++;
            }
            map.put(key, meaning);
        }
    }

    public String[][] convertData() {
        String[][] datas = new String[sizeMap][2];
        List<String> data = new ArrayList<>(map.keySet());
        int position = 0;

        for (int i = 0; i < sizeMap; i++) {
            String key = data.get(position);
            List<String> values = map.get(key);
            datas[i][0] = key;
            datas[i][1] = values.get(0);
            for (int j = 1; j < values.size(); j++) {
                if (i < sizeMap)
                    i++;
                datas[i][0] = key;
                datas[i][1] = values.get(j);
            }
            if (position < map.size() - 1) {
                position++;
            }
        }
        return datas;
    }

    public String[][] findByKey(String key){
        if (map.get(key)==null)
            return null;
        int length = map.get(key).size();
        String[][] datas = new String[length][2];
        List<String> values = map.get(key);
        for (int i=0; i<length;i++)
        {
            datas[i][0] = key;
            datas[i][1] = values.get(i);
        }
        return datas;
    }

    public static void printArray(String[] arr) {
        for (String str : arr) {
            System.out.print(str + ", ");
        }
    }

    public static void print2dArray(String[][] arr) {
        for (String[] str : arr) {
            for (String str1 : str) {
                System.out.print(str1 + " ");
            }
        }
    }

}

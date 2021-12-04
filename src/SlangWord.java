import java.io.*;
import java.util.*;

public class SlangWord {
    private TreeMap<String, List<String>> map = new TreeMap<>();
    private int sizeMap;
    public static final String FILE_ROOT = "slang.txt";
    public static final String FILE_COPY = "slang-copy.txt";
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
        map.clear();
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
            } else {
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

    public String[][] findByKey(String key) {
        if (map.get(key) == null)
            return null;
        int length = map.get(key).size();
        String[][] datas = new String[length][2];
        List<String> values = map.get(key);
        for (int i = 0; i < length; i++) {
            datas[i][0] = key;
            datas[i][1] = values.get(i);
        }
        return datas;
    }

    public String[][] findByDefinition(String definition) {
        List<String> slangs = new ArrayList<>();
        List<String> definitions = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            List<String> values = entry.getValue();
            for (String value : values) {
                if (value.toLowerCase(Locale.ROOT).contains(definition.toLowerCase(Locale.ROOT).trim())) {
                    slangs.add(entry.getKey());
                    definitions.add(value);
                }
            }
        }
        String[][] arr = new String[slangs.size()][2];
        for (int i = 0; i < arr.length; i++) {
            arr[i][0] = slangs.get(i);
            arr[i][1] = definitions.get(i);
        }
        return arr;
    }

    public void saveHistory(String slang) throws IOException {
        FileWriter fw = new FileWriter(FILE_HISTORY, true);
        fw.write(slang + "\n");
        fw.flush();
        fw.close();
    }

    public String[][] loadHistory() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(FILE_HISTORY));
        List<String> keys = new ArrayList<>();
        List<String> keyArr = new ArrayList<>();
        List<String> valueArr = new ArrayList<>();
        String line = br.readLine();
        while (line != null) {
            keys.add(line);
            line = br.readLine();
        }
        Set<String> set = new HashSet<String>(keys);
        for (String key : set) {
            List<String> values = map.get(key);
            if (values != null) {
                for (String value : values) {
                    keyArr.add(key);
                    valueArr.add(value);
                }
            }
        }
        String[][] arr = new String[keyArr.size()][2];
        for (int i = 0; i < arr.length; i++) {
            arr[i][0] = keyArr.get(i);
            arr[i][1] = valueArr.get(i);
        }
        return arr;
    }

    public List<String> getAllKeys(String[][] arr) {
        Set<String> set = new HashSet<String>();
        for (String[] str : arr) {
            set.add(str[0]);
        }
        return new ArrayList<>(set);
    }

    public void saveAllToFile(String file) throws IOException {
        BufferedWriter bw1 = new BufferedWriter(new FileWriter(file, false));
        bw1.write("Slag`Meaning\n");
        bw1.flush();
        bw1.close();
        BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            StringBuilder line = new StringBuilder(entry.getKey() + "`");
            List<String> values = entry.getValue();
            line.append(values.get(0));
            if (values.size() > 1) {
                for (int i = 1; i < values.size(); i++) {
                    line.append("|").append(values.get(i));
                }
            }
            line.append("\n");
            bw.write(line.toString());
        }
        bw.flush();
        bw.close();
    }

    public void addNew(String slang, String definition) throws IOException {
        List<String> values = new ArrayList<>();
        values.add(definition);
        sizeMap++;
        map.put(slang, values);
        this.saveAllToFile(FILE_COPY);
    }

    public void addOverwrite(String slang, String definition) throws IOException {
        List<String> values = map.get(slang);
        values.set(0, definition);
        map.put(slang, values);
        this.saveAllToFile(FILE_COPY);
    }

    public void addDuplicate(String slang, String definition) throws IOException {
        List<String> values = map.get(slang);
        values.add(definition);
        sizeMap++;
        map.put(slang, values);
        this.saveAllToFile(FILE_COPY);
    }

    public void editSlangSword(String slang, String oldDefinition, String newDefinition) throws IOException {
        int position = 0;
        List<String> values = map.get(slang);
        for (int i = 0; i < values.size(); i++) {
            if (values.get(i).equals(slang)) {
                position = i;
                break;
            }
        }
        values.set(position, newDefinition);
        this.saveAllToFile(FILE_COPY);
    }

    public void deleteSlangWord(String slang, String definition) {
        List<String> values = map.get(slang);
        if (values.size() == 1)
            map.remove(slang);
        else {
            for (int i = 0; i < values.size(); i++) {
                if (values.get(i).equals(definition)) {
                    values.remove(i);
                    break;
                }
            }
            map.replace(slang, values);
        }
        sizeMap--;
        try {
            this.saveAllToFile(FILE_COPY);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean checkDuplicate(String slang) {
        for (String str : map.keySet()) {
            if (str.equals(slang))
                return true;
        }
        return false;
    }

    public static void printArray(String[] arr) {
        for (String str : arr) {
            System.out.print(str + ", ");
        }
    }

    public static void print2dArray(String[][] arr) {
        for (String[] str : arr) {
            System.out.println(str[0] + ": " + str[1]);
        }
    }
}

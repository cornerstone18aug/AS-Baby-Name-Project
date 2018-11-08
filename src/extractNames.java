import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class extractNames {

    public static void main(String[] args) {
        String readFile = readFile();

        Pattern patternYear = Pattern.compile("Popularity\\sin\\s(\\d\\d\\d\\d)");
        Pattern patternNameAndRank = Pattern.compile("<td>(\\d+)</td><td>(\\w+)</td>\\<td>(\\w+)</td>");

        Matcher matcherYear = patternYear.matcher(readFile);
        Matcher matcherNameAndRank = patternNameAndRank.matcher(readFile);


        Map<String, String> map_babynames = new HashMap<>();
        List<String> listYear = new ArrayList<>();
        while (matcherYear.find()) {
            listYear.add(matcherYear.group(1));
        }

        while (matcherNameAndRank.find()) {
            map_babynames.put(matcherNameAndRank.group(1), matcherNameAndRank.group(2) + matcherNameAndRank.group(3));
        }

        List<Map.Entry<String, String>> list_babynames = new ArrayList<Map.Entry<String, String>>(map_babynames.entrySet());
        Collections.sort(list_babynames, new Comparator<Map.Entry<String, String>>() {
            public int compare(Map.Entry<String, String> obj1, Map.Entry<String, String> obj2) {
                return obj1.getValue().compareTo(obj2.getValue());
            }
        });

        for (Map.Entry<String, String> babyname : list_babynames) {
            System.out.printf("%3s:  %s", babyname.getKey(), babyname.getValue());
            System.out.println();
        }
    }

//        for(Map.Entry<String, String> entry : hashMap.entrySet()) {
//            String key = entry.getKey();
//            String value = entry.getValue();
//            System.out.println(key + value);
//        }    }

    private static String readFile() {

        try {
            FileReader fileReader = new FileReader("src/babynames/baby1990.html");
            BufferedReader br = new BufferedReader(fileReader);
            String strLine = "";
            for (String line = br.readLine(); line != null; line = br.readLine()) {
                strLine += line;
            }
            fileReader.close();
            br.close();
            return strLine;
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            return "";
        }
    }

}


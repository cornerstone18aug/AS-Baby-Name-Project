import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class extractNames {

    public static void main(String[] args) {
        String readFile = readFile();

        Pattern patternYear = Pattern.compile("Popularity\\sin\\s(\\d\\d\\d\\d)");
        Pattern patternNameAndRank = Pattern.compile("<td>(\\d+)</td><td>(\\w+)</td>\\<td>(\\w+)</td>");

        Matcher matcherYear = patternYear.matcher(readFile);
        Matcher matcherNameAndRank = patternNameAndRank.matcher(readFile);


        HashMap<String, String> hashMap = new HashMap<>();
        List<String> listYear = new ArrayList<>();
        List<String> listRank = new ArrayList<>();
        List<String> listName = new ArrayList<>();
        while (matcherYear.find()) {
            listYear.add(matcherYear.group(1));
        }

        while (matcherNameAndRank.find()) {
//            listRank.add(matcherNameAndRank.group(1));
//            listName.add(matcherNameAndRank.group(2) + matcherNameAndRank.group(3));
            hashMap.put(matcherNameAndRank.group(1), matcherNameAndRank.group(2) + matcherNameAndRank.group(3));
        }

        for(Map.Entry<String, String> entry : hashMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println(key + value);
            // do what you have to do here
            // In your case, another loop.
        }    }

    private static String readFile() {

        try {
            FileReader fileReader = new FileReader("src/babynames/baby1990.html");
//            FileInputStream fstream = new FileInputStream("src/Baby/babynames/baby1990.html");
            BufferedReader br = new BufferedReader(fileReader);
            String strLine = "";
            for (String line = br.readLine(); line != null; line = br.readLine()) {
                strLine += line;
            }
            fileReader.close();
            br.close();
//            fstream.close();
            return strLine;
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            return "";
        }
    }

}


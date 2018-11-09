import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class extractNames {

    public static void main(String[] args) throws IOException {
        for (int i=0; i<=9; i++) {
            String readFile = readFile(i);

            Pattern patternYear = Pattern.compile("Popularity\\sin\\s(\\d\\d\\d\\d)");
            Pattern patternNameAndRank = Pattern.compile("<td>(\\d+)</td><td>(\\w+)</td>\\<td>(\\w+)</td>");

            Matcher matcherYear = patternYear.matcher(readFile);
            Matcher matcherNameAndRank = patternNameAndRank.matcher(readFile);

            Map<String, String> map_babynames = new HashMap<>();
            String listYear = "";
            while (matcherYear.find()) {
                listYear = matcherYear.group(1);
            }

            while (matcherNameAndRank.find()) {
                map_babynames.put(matcherNameAndRank.group(1), matcherNameAndRank.group(2) + " " + matcherNameAndRank.group(3));
            }

            List<Map.Entry<String, String>> list_babynames = new ArrayList<Map.Entry<String, String>>(map_babynames.entrySet());
            Collections.sort(list_babynames, (obj1, obj2) -> obj1.getValue().compareTo(obj2.getValue()));

            System.out.println(listYear);
            FileWriter newfile = null;
            try {
                newfile = new FileWriter("src/babynames/baby" + listYear + "html.summary");
                newfile.write(listYear + "\n");
                for (Map.Entry<String, String> babyname : list_babynames) {
                    newfile.write(babyname.getValue() + " " + babyname.getKey() + "\n");
                    System.out.printf("%3s:  %s", babyname.getKey(), babyname.getValue());
                    System.out.println();
                }
                newfile.flush();
            }catch (IOException e) {
                System.out.println(e.getMessage());
            } finally {
                newfile.close();
            }
        }
    }

    private static String readFile(int i) {

            try {
                String year = Integer.toString(1990 + (i * 2));
                    FileReader fileReader = new FileReader("src/babynames/baby" + year + ".html");
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
                return null;
            }
        }

}


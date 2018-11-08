public class extractNames {

    public static void main(String[] args) {
        String s = readFile();
        System.out.println(s);
    }

    private static String readFile() {

        try {
            FileReader fileReader = new FileReader("src/Baby/babynames/baby1990.html");
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


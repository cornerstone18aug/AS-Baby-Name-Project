import java.io.*;

public class extractNames {

    public static void main(String[] args) {

        System.out.println(Readfile());

    }

    public static String Readfile(){
        try{
            FileInputStream fstream = new FileInputStream("src/babynames/baby1990.html");
            InputStreamReader in = new InputStreamReader(fstream);
            BufferedReader br = new BufferedReader(in);
            String strLine = null;
            while (br.readLine() != null)   {
                strLine+=br.readLine();
            }
            in.close();
            return strLine;
        }catch (Exception e){
            System.err.println("Error: " + e.getMessage());
            return "";
        }

    }
}


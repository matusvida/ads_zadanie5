import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Matus on 06.06.2016.
 */
public class Loader {

    public static List<String> loadLine(String fileName) throws IOException {

        List<String> list = new ArrayList<String>();
        BufferedReader br = null;
        String line;
        Path filePath = Paths.get(fileName);
        try{
            br = new BufferedReader(new InputStreamReader(
                    new FileInputStream(fileName), "UTF-8"));
            while((line = br.readLine()) != null){
                list.add(line);
            }
        }
        catch (FileNotFoundException e){
            System.out.println("Subor sa nenasiel");
            e.printStackTrace();
        }
        return list;
    }

    public static List<String> loadText(String fileName) throws IOException {

        List<String> list = new ArrayList<String>();
        Scanner sc = null;
        try{
            sc = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (sc.hasNext()) {
            list.add(sc.next());
        }
        return list;
    }
}

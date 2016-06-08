import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by matus.vida on 6/8/2016.
 */
public class Writer {

    public static void write(List<String> list) throws IOException {

        FileWriter fw = new FileWriter("output.txt");
        for(int i=0; i<list.size(); i++){
            fw.write(list.get(i)+" ");
        }
        fw.close();
    }
}

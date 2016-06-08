import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Matus
 * @created 4.6.2016
 */
class Spelling {

    public static void main(String[] args) {

        List<String> dictionary = new ArrayList<String>();
        List<String> text = new ArrayList<String>();

        try {
            dictionary = Loader.loadLine("testSlovnik.txt");
            text = Loader.loadText("testText.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        compare(dictionary, text);


    }

    public static void compare(List<String> dictionary, List<String> text){

        List<Integer> costs = new ArrayList<Integer>();
        int minIndex;

        for(int i=0; i<text.size(); i++){
            if(!dictionary.contains(text.get(i))){
                for(int j=0; j<dictionary.size(); j++){
                    costs.add(j, getLevenshteinDistance(text.get(i), dictionary.get(j)));
                }
                minIndex = costs.indexOf(Collections.min(costs));
                System.out.println(minIndex);
            }
        }
    }

    public static int getLevenshteinDistance(String s, String t) {
        if (s == null || t == null) {
            throw new IllegalArgumentException("Strings must not be null");
        }

        int n = s.length(); // length of s
        int m = t.length(); // length of t
        s = s.toLowerCase();

        if (n == 0) {
            return m;
        } else if (m == 0) {
            return n;
        }

        if (n > m) {
            // swap the input strings to consume less memory
            String tmp = s;
            s = t;
            t = tmp;
            n = m;
            m = t.length();
        }

        int p[] = new int[n+1]; //'previous' cost array, horizontally
        int d[] = new int[n+1]; // cost array, horizontally
        int _d[]; //placeholder to assist in swapping p and d

        // indexes into strings s and t
        int i; // iterates through s
        int j; // iterates through t

        char t_j; // jth character of t

        int cost; // cost

        for (i = 0; i<=n; i++) {
            p[i] = i;
        }

        for (j = 1; j<=m; j++) {
            t_j = t.charAt(j-1);
            d[0] = j;

            for (i=1; i<=n; i++) {
                cost = s.charAt(i-1)==t_j ? 0 : 1;
                // minimum of cell to the left+1, to the top+1, diagonally left and up +cost
                d[i] = Math.min(Math.min(d[i-1]+1, p[i]+1),  p[i-1]+cost);
            }

            // copy current distance counts to 'previous row' distance counts
            _d = p;
            p = d;
            d = _d;
        }

        // our last action in the above loop was to switch d and p, so p now
        // actually has the most recent cost counts
        return p[n];
    }


}

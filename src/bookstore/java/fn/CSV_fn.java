
package bookstore.java.fn;

import java.util.ArrayList;
import java.util.Arrays;

public class CSV_fn {

    /**
     * Splits the given data into an array list
     * <p>
     * Ex:
     * <p>
     * [Text File]
     * apple,ball,cat,dog
     * mango,nest,apple,ball
     * <p>
     * [Returned 2D list]
     * data = [[apple, ball, cat, dog], [mango, nest, apple, ball]]
     *
     * @param data CSV Text
     * @return array list with all the data
     */
    public static ArrayList<String[]> parse(String data) {

        ArrayList<String[]> d = new ArrayList<>();

        String[] s = data.split("\n");

        for (String x : s) {
            d.add(x.split(","));
        }

        return d;
    }

}

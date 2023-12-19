
package bookstore.java.fn;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class File_rw {

    /**
     * Reads text from the file on the following path
     *
     * @param filePath Path of the file
     * @return File text
     */
    public static String readTextFromFile(String filePath) {

        File file = new File(filePath);

        if (!file.isFile())
            return null;

        String text = "";

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                text += line + '\n';
            }

            if (!text.isEmpty())
                text = text.substring(0, text.length() - 1);

        } catch (Exception e) {
        }

        return text;
    }

    /**
     * Writes given text in the given file
     *
     * @param text     Text data
     * @param filePath File path
     */
    public static void writeTextToFile(String text, String filePath) {

        File file = new File(filePath);

        try (BufferedWriter br = new BufferedWriter(new FileWriter(file))) {
            br.write(text);
            br.flush();
            br.close();
        } catch (Exception e) {
        }
    }

}

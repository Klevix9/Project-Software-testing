
package bookstore;

import java.net.URL;

public class ResourceLoader {

    public URL loadStylesheet(String name) {

        URL filePath = null;

        try {
            filePath = getClass().getResource(
                    "res/css/" + name + ".css"
            );
        } catch (Exception e) {
            System.out.println("Error!");
            ;
        }

        return filePath;
    }

}

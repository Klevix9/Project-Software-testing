
package bookstore.java;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        // set scene
        GUI gui = new GUI();
        Scene scene = new Scene(gui.getMainLayout(), 500, 450);

        // set and show stage
        primaryStage.setTitle("Book Store");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}

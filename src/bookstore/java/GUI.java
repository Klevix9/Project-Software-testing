
package bookstore.java;

import bookstore.ResourceLoader;
import bookstore.java.ctrl.AdminCtrl;
import bookstore.java.ctrl.LibrarianCtrl;
import bookstore.java.ctrl.LoginCtrl;
import bookstore.java.ctrl.ManagerCtrl;
import bookstore.java.model.AdminModel;
import bookstore.java.model.LibrarianModel;
import bookstore.java.model.LoginModel;
import bookstore.java.model.ManagerModel;
import bookstore.java.view.AdminView;
import bookstore.java.view.LibrarianView;
import bookstore.java.view.LoginView;
import bookstore.java.view.ManagerView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;

public class GUI {

    private final StoreLogic logic;
    private BorderPane mainLayout;

    private LoginView loginView;
    private LibrarianView libView;
    private ManagerView mngView;
    private AdminView adminView;

    // ************ Constructor/Initializer ************ //

    // constructor
    public GUI() {

        // initializes store logic
        logic = new StoreLogic();

        // initializes view components
        init();

        // UI
        //ui();
    }

    // initializes components of the gui
    private void init() {

        this.mainLayout = new BorderPane();
        this.mainLayout.getStyleClass().add("main_layout");
        this.mainLayout.getStylesheets().add(
                new ResourceLoader().loadStylesheet("myStyle").toExternalForm()
        );

        // Menu
        MenuBar menuBar = new MenuBar();
        menuBar.getStyleClass().add("menu_layout");

        Menu fileMenu = new Menu("File");

        MenuItem exit = new MenuItem("Exit App!");
        exit.setOnAction(e -> System.exit(0));

        fileMenu.getItems().add(exit);

        menuBar.getMenus().add(fileMenu);

        // Login Page
        LoginModel loginModel = new LoginModel(logic, this);
        loginView = new LoginView(loginModel);
        LoginCtrl loginCtrl = new LoginCtrl(loginModel, loginView);

        // Librarian Page
        LibrarianModel libModel = new LibrarianModel(logic, this);
        libView = new LibrarianView(libModel);
        LibrarianCtrl libCtrl = new LibrarianCtrl(libModel, libView);

        // Manager Page
        ManagerModel mngModel = new ManagerModel(logic, this);
        mngView = new ManagerView(mngModel);
        ManagerCtrl mngCtrl = new ManagerCtrl(mngModel, mngView);

        // Admin Page
        AdminModel adminModel = new AdminModel(logic, this);
        adminView = new AdminView(adminModel);
        AdminCtrl adminCtrl = new AdminCtrl(adminModel, adminView);

        this.mainLayout.setTop(menuBar);
        this.mainLayout.setCenter(loginView);

    }

    // ********************** END ********************** //

    // ****************** Getter/Setter ****************** //

    public BorderPane getMainLayout() {
        return mainLayout;
    }

    public void setLayoutToLogin() {
        this.mainLayout.setCenter(loginView);
    }

    public void setLayoutToLibrarian() {
        libView.showLayout(mainLayout);
    }

    public void setLayoutToManager() {
        mngView.showLayout(mainLayout);
    }

    public void setLayoutToAdministrator() {
        adminView.showLayout(mainLayout);
    }

    // ********************** END ********************** //

}

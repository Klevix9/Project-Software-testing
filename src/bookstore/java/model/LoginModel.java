
package bookstore.java.model;

import bookstore.java.GUI;
import bookstore.java.StoreLogic;
import bookstore.java.cmp.User;

public class LoginModel {

    private final StoreLogic logic;
    private final GUI gui;
    private User currUser;

    private String emailPh;
    private String password;

    // *************** Constructor/Initializer ************** //

    public LoginModel(StoreLogic logic, GUI gui) {
        this.logic = logic;
        this.currUser = null;
        this.emailPh = "";
        this.password = "";
        this.gui = gui;
    }

    // ************************ END ************************* //

    // ************************ Fn ************************** //

    public boolean login() {
        currUser = logic.getUser(emailPh, password);
        logic.loginWith(currUser);
        return currUser != null;
    }

    /**
     * res can have three values 1, 2, 3
     * 1 means librarian, 2 means manager and 3 means administrator
     *
     * @param res
     */
    public void changeView(int res) {
        switch (res) {
            case 1:
                gui.setLayoutToLibrarian();
                break;
            case 2:
                gui.setLayoutToManager();
                break;
            case 3:
                gui.setLayoutToAdministrator();
                break;
            default:
                break;
        }
    }

    // ************************ END ************************* //

    // ******************* Getter/Setter ******************** //

    public void setEmailPh(String emailPh) {
        this.emailPh = emailPh;
    }

    public String getEmailPh() {
        return emailPh;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public User getCurrUser() {
        return currUser;
    }

    // *********************** END ************************* //

}

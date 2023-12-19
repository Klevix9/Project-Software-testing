
package bookstore.java.ctrl;

import bookstore.java.cmp.Message;
import bookstore.java.cmp.User;
import bookstore.java.model.LoginModel;
import bookstore.java.view.LoginView;

public class LoginCtrl {

    private final LoginModel model;
    private final LoginView view;

    // *************** Constructor/Initializer ************** //

    public LoginCtrl(LoginModel model, LoginView view) {

        this.model = model;
        this.view = view;

        // set actions
        this.view.setLoginBtnAction(e -> loginBtnAction());
    }

    // ************************ END ************************* //

    // ************************ Fn ************************** //

    public void loginBtnAction() {

        model.setEmailPh(view.getEmailPhFieldText());
        model.setPassword(view.getPasswordFieldText());

        if (model.login()) {

            // login successful
            User user = model.getCurrUser();
            Message.showInfo(
                    "You logged in as " + user.getName() + "\nAccess Level: "
                            + user.getAccessLevel().toString(), "Login Successful"
            );

            switch (user.getAccessLevel()) {
                case LIBRARIAN:
                    view.setEmailPhFieldText("");
                    view.setPasswordFieldText("");
                    model.changeView(1);
                    break;
                case MANAGER:
                    view.setEmailPhFieldText("");
                    view.setPasswordFieldText("");
                    model.changeView(2);
                    break;
                case ADMINISTRATOR:
                    view.setEmailPhFieldText("");
                    view.setPasswordFieldText("");
                    model.changeView(3);
                    break;
                default:
                    break;
            }

        } else {
            Message.showError("Email or password is incorrect");
        }

    }

    // ************************ END ************************* //

}

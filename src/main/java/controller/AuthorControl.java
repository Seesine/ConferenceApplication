package controller;

import javafx.fxml.FXML;
import main.Main;

/**
 * Created by Dragos on 5/8/2017.
 */
public class AuthorControl
{
    final Main loginManager;

    public AuthorControl(final Main loginManager)
    {
        this.loginManager = loginManager;
    }

    @FXML
    public void setLogoutAction()
    {
        loginManager.logOut();
    }
}

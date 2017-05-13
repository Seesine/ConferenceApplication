package controller;

import javafx.fxml.FXML;
import main.Main;

/**
 * Created by Dragos on 5/8/2017.
 */
public class AttendantControl
{
    final Main loginManager;
    public AttendantControl(final Main loginManager)
    {
        this.loginManager = loginManager;
    }
    @FXML
    public void setLogoutAction()
    {
        loginManager.logOut();
    }
}

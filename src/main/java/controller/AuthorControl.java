package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import main.Main;
import model.File;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Dragos on 5/8/2017.
 */
public class AuthorControl
{
    final Main loginManager;
    @FXML private ComboBox confCombo;
    @FXML private ComboBox sesCombo;
    @FXML private ComboBox authorCombo;
    @FXML private Button confirmButton;
    @FXML private Button addButton;
    @FXML private Button uploadButton;
    @FXML private Label abstractLabel;
    @FXML private Label proposalLabel;
    @FXML private TextArea absText;
    @FXML private TableView fileTable;

    @FXML private TableColumn<File, String> titleColumn;
    @FXML private TableColumn<File, String> linkColumn;

    public AuthorControl(final Main loginManager)
    {
        this.loginManager = loginManager;
    }

    public void initialize(URL location, ResourceBundle resources)
    {

    }

    @FXML
    public void setLogoutAction()
    {
        loginManager.logOut();
    }

    @FXML
    public void setUpload()
    {

    }

    @FXML
    public void setAddAuthor()
    {

    }

    @FXML
    public void setConfirm()
    {

    }
}

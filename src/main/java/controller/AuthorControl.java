package controller;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import main.Main;
import model.File;
import repository.AuthorsRepository;
import services.AuthorService;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Dragos on 5/8/2017.
 */
public class AuthorControl
{
    AuthorsRepository repo;
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

    @FXML private TableColumn<File, String> titlu;
    @FXML private TableColumn<File, String> filedoc;

    private AuthorService service;
    private ObservableList files;
    private List<File> lista = new ArrayList<File>();

    public AuthorControl(AuthorsRepository repo,final Main loginManager)
    {
        this.repo = repo;
        this.loginManager = loginManager;

    }

    public void initialize()
    {
        this.service = new AuthorService(this.repo);
        lista = service.getAllFiles();
        this.files = FXCollections.observableArrayList(lista);

        files.addListener(new ListChangeListener()
        {
            @Override
            public void onChanged(Change change)
            {

            }
        });


        fileTable.setItems(files);
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

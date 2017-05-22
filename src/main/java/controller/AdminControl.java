package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import main.Main;
import model.DefaultUser;
import repository.*;

import javax.xml.soap.Text;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Dragos on 5/8/2017.
 */
public class AdminControl
{
    final Main loginManager;
    @FXML private ComboBox<DefaultUser> usersComboBox;
    @FXML private ComboBox<String> accessComboBox;
    @FXML private ComboBox<String> infoComboBox;
    @FXML private TextField infoTextField;
    @FXML private Button registerButton;
    @FXML private Button deleteButton;
    @FXML private Button logoutButton;

    static List<String> infoAttendant = new ArrayList<String>(Arrays.asList(""));
    static List<String> infoAuthors = new ArrayList<String>(Arrays.asList("Name"));
    static List<String> infoCM = new ArrayList<String>(Arrays.asList("Name", "Affiliation", "Email", "WebPage"));

    // Logins Repositories
    private CMRepository CMLRepository;
    private AttendantRepository ATLRepository;
    private AuthorsRepository AULRepository;
    private ReviewerRepository RVWRepo;
    private AdminRepository ADRepo;
    private DefaultUserRepository DURepo;

    public AdminControl(final Main loginManager, CMRepository CMLRepository, AttendantRepository ATLRepository, AuthorsRepository AULRepository, ReviewerRepository RVWRepo, AdminRepository ADRepo, DefaultUserRepository DURepo)
    {
        this.loginManager = loginManager;
        this.CMLRepository = CMLRepository;
        this.ATLRepository = ATLRepository;
        this.AULRepository = AULRepository;
        this.RVWRepo = RVWRepo;
        this.ADRepo = ADRepo;
        this.DURepo = DURepo;
    }
    @FXML public void initialize() {
        List<String> laccess = new ArrayList<String>();
        laccess.add("Attendant");
        laccess.add("Author");
        laccess.add("CM");
        laccess.add("Reviewer");
        ObservableList<String> oaccess = FXCollections.observableList(laccess);
        accessComboBox.setItems(oaccess);
        infoComboBox.setItems(FXCollections.observableArrayList(infoAttendant));

        ObservableList<DefaultUser> odu = FXCollections.observableArrayList(DURepo.getAll());
        usersComboBox.setItems(odu);

        accessComboBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue value, String o, String n) {
                if (o == null || !o.equals(n)) {
                    ObservableList<String> obsl;
                    if (n.equals("Attendant")) {
                        infoComboBox.getItems().removeAll();
                        obsl = FXCollections.observableArrayList(infoAttendant);
                        infoComboBox.setItems(obsl);
                    } else if (n.equals("Author")) {
                        obsl = FXCollections.observableArrayList(infoAuthors);
                        infoComboBox.getItems().removeAll();
                        infoComboBox.setItems(obsl);
                    } else if (n.equals("CM") || n.equals("Reviewer")) {
                        obsl = FXCollections.observableArrayList(infoCM);
                        infoComboBox.getItems().removeAll();
                        infoComboBox.setItems(obsl);
                    }
                }
            }
        });
    }
    @FXML
    public void setLogoutAction()
    {
        loginManager.logOut();
    }
    @FXML public void registerButtonOnAction() {

    }
    @FXML public void deleteButtonOnAction() {

    }
}

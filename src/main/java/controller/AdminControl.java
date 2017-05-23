package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;
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

    String info1, info2, info3, info4;

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
                    if (n.equals("Attendant")) {
                        if (infoComboBox.getItems().size() > 0)
                            infoComboBox.getItems().removeAll();
                        infoComboBox.getItems().setAll(infoAttendant);
                        infoComboBox.setValue("");
                    } else if (n.equals("Author")) {
                         if (infoComboBox.getItems().size() > 0)
                            infoComboBox.getItems().removeAll();
                        infoComboBox.getItems().setAll(infoAuthors);
                        infoComboBox.setValue("Name");
                    } else if (n.equals("CM") || n.equals("Reviewer")) {
                        if (infoComboBox.getItems().size() > 0)
                            infoComboBox.getItems().removeAll();
                        infoComboBox.getItems().setAll(infoCM);
                        infoComboBox.setValue("Name");
                    }
                }
            }
        });
        usersComboBox.setCellFactory(
                new Callback<ListView<DefaultUser>, ListCell<DefaultUser>>() {
                    @Override public ListCell<DefaultUser> call(ListView<DefaultUser> param) {
                        final ListCell<DefaultUser> cell = new ListCell<DefaultUser>() {
                            {
                                super.setPrefWidth(100);
                            }
                            @Override public void updateItem(DefaultUser item,
                                                             boolean empty) {
                                super.updateItem(item, empty);
                                if (item != null) {
                                    setText(item.getUsername());
                                }
                                else {
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                });
        infoComboBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue value, String o, String n) {
                if (o == null || !o.equals(n)) {
                    String selectedAccess = accessComboBox.getValue();
                    if (selectedAccess == null || selectedAccess.equals(""))
                        return;
                    if (n.equals("Name")) {
                        infoTextField.setText(info1);
                    } else if (n.equals("Affiliation")) {
                        infoTextField.setText(info2);
                    } else if (n.equals("Email")) {
                        infoTextField.setText(info3);
                    } else if (n.equals("WebPage")) {
                        infoTextField.setText(info4);
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
        String selectedAccess = accessComboBox.getValue();
        if (selectedAccess == null || selectedAccess.equals(""))
            return;
        DefaultUser du = usersComboBox.getValue();
        if (du == null)
            return;
        if (selectedAccess.equals("Attendant")) {
            if (ATLRepository.login(du.getUsername(), du.getPassword())) {
                showMessage(Alert.AlertType.ERROR, "Attendant Register", "User already exists in Attendant !");
                return;
            }
            ATLRepository.save(du.getUsername(), du.getPassword());
        }
        else if (selectedAccess.equals("Author")) {
            if (AULRepository.login(du.getUsername(), du.getPassword())) {
                showMessage(Alert.AlertType.ERROR, "Author Register", "User already exists in authors !");
                return;
            }
            AULRepository.save(du.getUsername(), du.getPassword(), info1);
        }
        else if (selectedAccess.equals("CM")) {
            if (AULRepository.login(du.getUsername(), du.getPassword())) {
                showMessage(Alert.AlertType.WARNING, "CM Register", "User already exists in authors (skipped) !");
            }
            if (CMLRepository.login(du.getUsername(), du.getPassword())) {
                showMessage(Alert.AlertType.ERROR, "CM Register", "User already exists in CM !");
                return;
            }
            AULRepository.save(du.getUsername(), du.getPassword(), info1);
            CMLRepository.save(du.getUsername(), du.getPassword(), info1, info2, info3, info4);
        }
        else if (selectedAccess.equals("Reviewer")) {
            CMLRepository.save(du.getUsername(), du.getPassword(), info1, info2, info3, info4);
        }
    }
    @FXML public void deleteButtonOnAction() {
    }
    @FXML public void infoTextFieldOnKeyReleased(KeyEvent e) {
        if (e.getCode() != KeyCode.ENTER)
            return;
        String selectedInfo = infoComboBox.getValue();
        if (selectedInfo == null)
            return;
        if (selectedInfo.equals("Name")) {
            info1 = infoTextField.getText();
        } else if (selectedInfo.equals("Affiliation")) {
            info2 = infoTextField.getText();
        } else if (selectedInfo.equals("Email")) {
            info3 = infoTextField.getText();
        } else if (selectedInfo.equals("WebPage")) {
            info4 = infoTextField.getText();
        }
    }
    private static void showMessage(Alert.AlertType type, String header, String message)
    {
        Alert messages=new Alert(type);
        messages.setHeaderText(header);
        messages.setContentText(message);
        messages.showAndWait();
    }

}

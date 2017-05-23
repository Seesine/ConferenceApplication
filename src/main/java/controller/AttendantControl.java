package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import main.Main;
import model.Conference;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import repository.AttendantRepository;
import repository.ConfRepository;
import model.Sections;
import services.AuthorService;
import sun.swing.SwingUtilities2;
import services.AttendantService;

import javafx.scene.control.TableView;
import javax.xml.soap.Text;
import java.util.List;

/**
 * Created by Dragos on 5/8/2017.
 */
public class AttendantControl
{
    final Main loginManager;
    private ConfRepository confRepo = new ConfRepository();
    private SessionFactory factory;
    private AttendantRepository attRepo = new AttendantRepository(factory);
    private AttendantService service = new AttendantService(attRepo);
    public AttendantControl(final Main loginManager) {
        this.loginManager = loginManager;
        //this.confRepo = confRepo;
    }
    @FXML
    public void setLogoutAction()
    {
        loginManager.logOut();
    }
    @FXML
    TextField textFieldAuthor = new TextField();
    @FXML
    TextField textFieldTopic = new TextField();
    @FXML
    TextField textFieldDate = new TextField();
    @FXML
    TextField textFieldHour = new TextField();

    //@FXML
    //private TableView<Session> sectionTableView;

    //@FXML
    //private TableColumn<Session, String> sesiune;

    @FXML
    ComboBox<Sections> sessionComboBox;
    @FXML
    ComboBox<Conference> conferenceComboBox;

    private ObservableList<Conference> conferences;
    public void initialize(){
        //conferenceComboBox.getItems().clear();
        //ObservableList<Conference> obs = FXCollections.observableArrayList(confRepo.getAll());
        //conferenceComboBox.setItems(obs);
        //conferenceComboBox.getItems().addAll(confRepo.getAll());
        //conferenceComboBox.setPromptText("Conferinta");
        //conferenceComboBox.setEditable(true);

        conferences = FXCollections.observableArrayList(service.getAllConf());
        conferenceComboBox.setItems(conferences);

        conferenceComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener()
        {
            @Override
            public void changed(ObservableValue ov, Object t, Object t1)
            {
                ObservableList combox2 = FXCollections.observableArrayList((List) service.findByConfId(conferenceComboBox.getValue().getIdConference()));
                sessionComboBox.setItems(combox2);
                //List<String> lst = service.returnDeadline(conferenceComboBox.getValue().getIdConference());
            }
        });
    }
}

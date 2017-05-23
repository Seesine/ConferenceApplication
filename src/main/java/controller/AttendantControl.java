package controller;

import javafx.collections.FXCollections;
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
import repository.ConfRepository;
import model.Sections;
import sun.swing.SwingUtilities2;

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
    public AttendantControl(final Main loginManager, SessionFactory factory) {
        this.loginManager = loginManager;
        this.factory = factory;
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

    @FXML
    private TableView<Session> sectionTableView;

    @FXML
    private TableColumn<Session, String> sesiune;

    @FXML
    ComboBox<Conference> conferenceComboBox = new ComboBox<>();

    private SessionFactory factory;
    public void initialize(){
        conferenceComboBox.getItems().clear();
        ObservableList<Conference> obs = FXCollections.observableArrayList(confRepo.getAll());
        conferenceComboBox.setItems(obs);
        //conferenceComboBox.getItems().addAll(confRepo.getAll());
        //conferenceComboBox.setPromptText("Conferinta");
        //conferenceComboBox.setEditable(true);

        try{
            Conference conf = conferenceComboBox.getSelectionModel().getSelectedItem();
            int id = conf.getIdConference();
            Transaction tx = null;
            Session ses = factory.openSession();
            try{
                tx = ses.beginTransaction();
                Query query = ses.createQuery("FROM Sections WHERE id = :id");
                query.setParameter("id", id);
                List<Sections> sectionsList = query.list();
                tx.commit();
            }
            catch(HibernateException ex1){
                if (tx != null) tx.rollback();
                ex1.printStackTrace();
            }

            

        }
        catch (Exception ex2) {
            ex2.printStackTrace();
        }
    }
}
